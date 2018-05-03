package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.activities.no_permission_activity.NoPermissionActivity
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import com.sathvik1709.nowplayingpersistclient.util.RunningServicesUtil
import dagger.android.AndroidInjection
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_archived_list.*
import javax.inject.Inject



class ArchivedListActivity : AppCompatActivity(), ArchivedListContract.View {

    @Inject
    lateinit var presenter: ArchivedListContract.Presenter

    @Inject
    lateinit var dateTimeUtil: DateTimeUtil


    lateinit var archiveRecyclerView : RecyclerView
    lateinit var noSongsLayout : LinearLayout
    lateinit var gotoNPSettings : Button
    lateinit var recyclerViewOnClickSubject : Disposable

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archived_list)

        archiveRecyclerView = archive_recycler_view
        noSongsLayout = no_songs_layout
        gotoNPSettings = goto_now_playing_settings

        presenter.loadList();

        if(!RunningServicesUtil().isNLServiceRunning(this)){
            val intent = Intent(this, NoPermissionActivity::class.java)
            startActivity(intent)
        }

        gotoNPSettings.setOnClickListener {
            val nowPlayingSettingsIntent = Intent(Intent.ACTION_MAIN)
            nowPlayingSettingsIntent.component = ComponentName.unflattenFromString(getString(R.string.now_playing_settings_package))
            nowPlayingSettingsIntent.addCategory(Intent.CATEGORY_LAUNCHER)
            nowPlayingSettingsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(nowPlayingSettingsIntent)
        }

        //Toast.makeText(this,RunningServicesUtil().isNLServiceRunning(this).toString(), Toast.LENGTH_LONG).show()
    }

    override fun showNoListView() {
        noSongsLayout.visibility = View.VISIBLE
        archiveRecyclerView.visibility = View.GONE
    }

    override fun showFullListView(list: List<SongEntity>) {
        noSongsLayout.visibility = View.GONE
        archiveRecyclerView.visibility = View.VISIBLE

        setUpList(list)
    }

    private fun setUpList(songsList : List<SongEntity>) {

        if(archiveRecyclerView.adapter != null){
            archiveRecyclerView.adapter.notifyDataSetChanged()
            return
        }

        viewManager = LinearLayoutManager(this)
        viewAdapter = ArchiveListAdapter(songsList, dateTimeUtil)

        recyclerViewOnClickSubject = (viewAdapter as ArchiveListAdapter).onClickSubject.subscribe({
            s ->
            run {
                s.isFav = !s.isFav
                presenter.updateSongFav(s)
            }
        })

        archiveRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadList()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(recyclerViewOnClickSubject.isDisposed){
            recyclerViewOnClickSubject.dispose()
        }
    }

}
