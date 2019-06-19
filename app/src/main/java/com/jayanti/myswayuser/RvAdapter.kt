package com.jayanti.myswayuser

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var card_num = 0
lateinit var foodtypeText: TextView

class RvAdapter(val context: Context, var dataList: ArrayList<Model>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.card_view, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {


    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foodtypeText = itemView.findViewById<TextView>(R.id.foodType)
        val menuText = itemView.findViewById<TextView>(R.id.Menu)


        init {
            Log.d("SEEEEEEEEEEEE.......","before get menu")
            getMenuData(foodtypeText,menuText)

        }

    }
}

private fun getMenuData(timing: TextView, food: TextView) {
    val ref = FirebaseDatabase.getInstance().getReference("")
    val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (it in dataSnapshot.children.iterator()) {
                var str = it.value.toString()
                var items = str.substring(1, str.length-1).split("[=,]+".toRegex()).map { x -> x.trim() }
                when(card_num){
                    0 -> {var idx = items.indexOf("BREAKFAST")
                        timing.setText("${items[idx]}")
                        food.setText("${items[idx+1]}")
                        //Log.e("break","$idx")
                    }
                    1 -> {var idx = items.indexOf("LUNCH")
                        timing.setText("${items[idx]}")
                        food.setText("${items[idx+1]}")
                        //Log.e("LU","$idx")
                    }
                    2 -> {var idx = items.indexOf("SNACKS")
                        timing.setText("${items[idx]}")
                        food.setText("${items[idx+1]}")
                        //Log.e("SN","$idx")
                    }
                    3 -> {var idx = items.indexOf("DINNER")
                        timing.setText("${items[idx]}")
                        food.setText("${items[idx+1]}")
                        //Log.e("DI","$idx")
                    }
                }
                //Log.e("TIMING","${items[card_num]}")
                //Log.e("FOOD","${items[card_num+1]}")

            }
            card_num = (card_num + 1)%4
        }

        override fun onCancelled(databaseError: DatabaseError) {
            println("loadPost:onCancelled ${databaseError.toException()}")
        }
    }

    ref.child("MenuData").addListenerForSingleValueEvent(userListener)
}

