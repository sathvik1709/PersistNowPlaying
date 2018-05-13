package com.sathvik1709.nowplayingpersistclient.activities.fav_activity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchiveListAdapter
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_favs.*
import javax.inject.Inject

class FavsActivity : AppCompatActivity() , FavsListContract.View {

    @Inject
    lateinit var favsActivityPresenter : FavsListContract.Presenter

    @Inject
    lateinit var dateTimeUtil: DateTimeUtil

    lateinit var noFavsSongsLayout: LinearLayout
    lateinit var favsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favs)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Favourites"

        noFavsSongsLayout = no_favs_songs_layout
        favsRecyclerView = favs_recycler_view


        favsActivityPresenter.loadList()
    }

    override fun showNoListView() {
        noFavsSongsLayout.visibility = View.VISIBLE
        favsRecyclerView.visibility = View.GONE
    }

    override fun showFavsListView(songsList: List<SongEntity>) {
        noFavsSongsLayout.visibility = View.GONE
        favsRecyclerView.visibility = View.VISIBLE

        var viewAdapter = ArchiveListAdapter(songsList, dateTimeUtil, false)

        favsRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                // Respond to the action bar's Up/Home button
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
