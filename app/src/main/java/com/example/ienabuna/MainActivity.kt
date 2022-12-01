package com.example.ienabuna

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvAcehfoods: RecyclerView
    private var list: ArrayList<IeNaBuNa> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setActionBarTitle(title)

        rvAcehfoods = findViewById(R.id.rv_acehfoods)
        rvAcehfoods.setHasFixedSize(true)

        list.addAll(IeNaBuNaData.listData)
        showRecyclerlist()
    }

    private fun showSelectedFood(food: IeNaBuNa) {
        val moveWithDataIntent = Intent(this, DetailIeNaBuNa::class.java)
        moveWithDataIntent.putExtra(DetailIeNaBuNa.EXTRA_NAME, food.name)
        moveWithDataIntent.putExtra(DetailIeNaBuNa.EXTRA_DETAIL, food.detail)
        moveWithDataIntent.putExtra(DetailIeNaBuNa.EXTRA_PHOTO, food.photo)
        startActivity(moveWithDataIntent)
    }

    private fun showRecyclerlist(){
        rvAcehfoods.layoutManager = LinearLayoutManager(this)
        val listIeNaBuNaAdapter = ListIeNaBuNaAdapter(list)
        rvAcehfoods.adapter = listIeNaBuNaAdapter

        listIeNaBuNaAdapter.setOnItemClickCallback(object : ListIeNaBuNaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: IeNaBuNa) {
                showSelectedFood(data)
            }
        })
    }

    private fun setActionBarTitle(title: String){
        supportActionBar?.title = title
    }

    private var title: String = "IeNa BuNa"

    override fun onCreateOptionsMenu (menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) : Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int){
        when(selectedMode){
            R.id.img_profil -> {
                val moveIntent = Intent(this@MainActivity, About::class.java)
                startActivity(moveIntent)
            }
        }
    }

}