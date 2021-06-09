package com.project1.fatigueapplication.view.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.adapter.DataAdapter
import com.project1.fatigueapplication.data.viewdata.DataParcel

class OurteamActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ourteam)

        val datauser = listOf<DataParcel>(
            DataParcel(
                R.drawable.tyo,
                "Adwityo S.P.",
                "A1171500",
                "a1171500@bangkit.academy"
            ),
            DataParcel(
                R.drawable.fikri,
                "Fikri Aziz Athoillah",
                "A1091430",
                "a1091430@bangkit.academy"
            ),
            DataParcel(
                R.drawable.alex,
                "Alexander Diva Grael Bangun",
                "C1191520",
                "c1191520@bangkit.academy",
            )
        )
        val recyclerView = findViewById<RecyclerView>(R.id._imageRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = DataAdapter(this, datauser) {
        }
    }
}