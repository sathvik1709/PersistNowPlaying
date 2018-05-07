package com.sathvik1709.nowplayingpersistclient.activities.fav_activity


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_favs.*
import javax.inject.Inject

class FavsActivity : AppCompatActivity() , FavsListContract.View {

    @Inject
    lateinit var favsActivityPresenter : FavsListContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favs)
        setSupportActionBar(toolbar)
        toolbar.title = "Favorites"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        favsActivityPresenter.loadList()
    }

    override fun showNoListView() {

    }

    override fun showFavsListView(list: List<SongEntity>) {

    }
}
