package com.jayanti.myswayuser

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

var card_num = 0
lateinit var foodtypeText: TextView
var Datestr = dateString.replace('.','_')
var foodArrayIndex =0

class RvAdapter(val context: Context, var dataList: ArrayList<ImageUploadInfo>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.card_view, p0, false)
        foodArrayIndex =0
        return ViewHolder(v,context)

    }

    override fun getItemCount(): Int {
        return 4
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {


    }

    class ViewHolder(itemView: View,context: Context) : RecyclerView.ViewHolder(itemView) {
        var foodtypeText = itemView.findViewById<TextView>(R.id.foodType)
        val menuText = itemView.findViewById<TextView>(R.id.Menu)
        val imageSlot = itemView.findViewById<ImageView>(R.id.imageSlot)


        init {
            Datestr = dateString.replace('.','_')


            getMenuData(foodtypeText,imageSlot,menuText,context)

        }

    }
}

private fun getMenuData(timing: TextView,imageView: ImageView ,food: TextView,context: Context) {


    val ref = FirebaseDatabase.getInstance().getReference("")

    val userListener = object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            for (it in dataSnapshot.children.iterator()) {

                Log.e("DATA SNAPSHOT", "$dataSnapshot")
                var etable = it.value.toString()
                var date_selected = it.key.toString()
                //Log.e("HERE","$etable")
                Log.e("DATE IS","$Datestr")
               if(date_selected == Datestr)
               {
                   Log.e("DATE SELECTED HERE >>>>","$date_selected")
                    var items = etable.substring(1, etable.length-1).split("[=,]+".toRegex()).map { x -> x.trim() }
                    Log.e("ITEMSSSSSSSSSS" ," $items")

                    when(card_num){
                    0 -> {var idx = items.indexOf("BREAKFAST")
                        timing.setText("${items[idx]}")
                        Log.e("LOOK BREAKFAST>>","${items[idx+2]+"="+items[idx+3]+"="+items[idx+4]}")
                        Glide.with(context).load(items[idx+2]+"="+items[idx+3]+"="+items[idx+4]).into(imageView)
                        var foodItems = (items[idx+6]).toString()
                        var foodit = foodItems.replace("}"," ")

                        food.setText("${foodit}")
                        //Log.e("break","$idx")
                    }
                    1 -> {var idx = items.indexOf("LUNCH")
                        timing.setText("${items[idx]}")
                        var foodItems = (items[idx+6]).toString()
                        var foodit = foodItems.replace("}"," ")
                        food.setText("${foodit}")
                        Glide.with(context).load(items[idx+2]+"="+items[idx+3]+"="+items[idx+4]).into(imageView)
                        Log.e("LOOOK LUNCH>>","${items[idx+2]+"="+items[idx+3]+"="+items[idx+4]}")
                    }
                    2 -> {var idx = items.indexOf("SNACKS")
                        timing.setText("${items[idx]}")
                        var foodItems = (items[idx+6]).toString()
                        var foodit = foodItems.replace("}"," ")
                        food.setText("${foodit}")
                        Glide.with(context).load(items[idx+2]+"="+items[idx+3]+"="+items[idx+4]).into(imageView)
                        Log.e("LOOOK SNACKS>>","${items[idx+2]+"="+items[idx+3]+"="+items[idx+4]}")
                    }
                    3 -> {var idx = items.indexOf("DINNER")
                        timing.setText("${items[idx]}")
                        var foodItems = (items[idx+6]).toString()
                        var foodit = foodItems.replace("}"," ")
                        Glide.with(context).load(items[idx+2]+"="+items[idx+3]+"="+items[idx+4]).into(imageView)
                        Log.e("LOOOK DINNER>>","${items[idx+2]+"="+items[idx+3]+"="+items[idx+4]}")

                        food.setText("${foodit}")
                    }
                }

               }


            }
            card_num = (card_num + 1)%4
        }

        override fun onCancelled(databaseError: DatabaseError) {
            println("loadPost:onCancelled ${databaseError.toException()}")
        }
    }

    ref.child("MenuData").child("Food Title").orderByChild(Datestr).addListenerForSingleValueEvent(userListener)
}

