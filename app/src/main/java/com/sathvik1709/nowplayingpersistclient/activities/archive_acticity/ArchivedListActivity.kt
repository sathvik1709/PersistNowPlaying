package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.activities.no_permission_activity.NoPermissionActivity
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import com.sathvik1709.nowplayingpersistclient.util.RunningServicesUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_archived_list.*
import javax.inject.Inject

class ArchivedListActivity : AppCompatActivity(), ArchivedListContract.View {

    @Inject
    lateinit var presenter: ArchivedListContract.Presenter

    @Inject
    lateinit var dateTimeUtil: DateTimeUtil


    lateinit var archiveRecyclerView : RecyclerView
    lateinit var noSongsLayout : LinearLayout

    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_archived_list)

        archiveRecyclerView = archive_recycler_view
        noSongsLayout = no_songs_layout

        presenter.loadList();

        if(RunningServicesUtil().isNLServiceRunning(this)){
            var intent : Intent
            intent = Intent(this, NoPermissionActivity::class.java)
            startActivity(intent)
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
        viewManager = LinearLayoutManager(this)
        viewAdapter = ArchiveListAdapter(songsList, dateTimeUtil)

        archiveRecyclerView.apply {
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadList()
    }

}
