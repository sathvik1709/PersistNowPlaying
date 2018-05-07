package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.activities.fav_activity.FavsActivity
import com.sathvik1709.nowplayingpersistclient.activities.no_permission_activity.NoPermissionActivity
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import com.sathvik1709.nowplayingpersistclient.util.RunningServicesUtil
import com.sathvik1709.nowplayingpersistclient.util.getNotificationsAccessSettings
import com.sathvik1709.nowplayingpersistclient.util.getNowPlayingSettingsIntent
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
    lateinit var recyclerViewOnFavClickSubject : Disposable

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
            startActivity(Intent().getNowPlayingSettingsIntent(getString(R.string.now_playing_settings_package)))
        }
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
            if(songsList.size != archiveRecyclerView.adapter.itemCount){
                var newViewAdapter = createAdapter(songsList)
                archiveRecyclerView.swapAdapter(newViewAdapter, false)
            }else {
                archiveRecyclerView.adapter.notifyDataSetChanged()
            }
            return
        }

        archiveRecyclerView.adapter = createAdapter(songsList)

    }

    private fun createAdapter(songsList: List<SongEntity>) : ArchiveListAdapter{
        var viewAdapter = ArchiveListAdapter(songsList, dateTimeUtil)

        recyclerViewOnFavClickSubject = (viewAdapter as ArchiveListAdapter).onFavClicked.subscribe({
            s ->
            run {
                s.isFav = !s.isFav
                presenter.updateSongFav(s)
            }
        })

        recyclerViewOnClickSubject =  (viewAdapter as ArchiveListAdapter).onViewClicked.subscribe({
            s ->
            run {
                val intent = Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH)
                intent.putExtra(SearchManager.QUERY, s.songName + " by" + s.albumName)
                startActivity(intent)
            }
        })

        archiveRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        return viewAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadList()
    }

    override fun onDestroy() {
        super.onDestroy()
        if(recyclerViewOnFavClickSubject.isDisposed){
            recyclerViewOnFavClickSubject.dispose()
        }
        if (recyclerViewOnClickSubject.isDisposed){
            recyclerViewOnClickSubject.dispose()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.archive_list_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        when (item.getItemId()) {
            R.id.open_notification_access_settings -> {
                startActivity(Intent().getNotificationsAccessSettings())
                return true
            }
            R.id.open_now_playing_settings -> {
                startActivity(Intent().getNowPlayingSettingsIntent(getString(R.string.now_playing_settings_package)))
                return true
            }
            R.id.favs_list -> {
                startActivity(Intent(this, FavsActivity::class.java))
                return true
            }
        }
        return false
    }

}
