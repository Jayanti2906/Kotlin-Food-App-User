package com.jayanti.myswayuser

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_second)
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val dataList = ArrayList<ImageUploadInfo>()



        val rvAdapter = RvAdapter(this, dataList)

        recyclerView.adapter = rvAdapter;
    }
}

