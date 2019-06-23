package com.jayanti.myswayuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

lateinit var ref : DatabaseReference
var bool_check = false
var foodType=""
var dateString = ""
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ref = FirebaseDatabase.getInstance().getReference("")

        var nameText = findViewById<EditText>(R.id.editText)
        var goButton= findViewById<Button>(R.id.gobutton)
        var checkMenuButton = findViewById<Button>(R.id.menuButton)
        var platecountText = findViewById<TextView>(R.id.platecountText)
        var foodText = findViewById<TextView>(R.id.foodTypeText)
        var dateText = findViewById<TextView>(R.id.DateText)
        var calenderImage =findViewById<ImageView>(R.id.calenderImg)


        dateString= SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis()).toString()
        dateText.text = dateString

        val sdt = SimpleDateFormat("HH")
        var curtime  = sdt.format(Date()).toInt()
        if(curtime >= 8 && curtime < 12) {foodType="Breakfast" }
        else if(curtime >= 12 && curtime <= 15) {foodType="Lunch" }
        else if(curtime >= 15 && curtime <19) {foodType="Evening Snacks" }
        else if(curtime>=19 && curtime < 24)  { foodType="Dinner"}
        else { foodType= "No food" }
        foodText.setText("$foodType  ")

        initPlateCount()

        var objFirebase = FirebaseClass()

        goButton.setOnClickListener {
            objFirebase.saveData(nameText)

        }

        calenderImage.setOnClickListener {
            var obj = DateSelectorClass(this,dateText)
            obj.showCalender()
        }

        checkMenuButton.setOnClickListener {


            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)



        }

    }
    private fun initPlateCount() {
        val userListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {

                for (it in dataSnapshot.children.iterator()) {
                    var d1 = it.value.toString().split('=')[1]
                    val re = Regex("[^A-Za-z0-9 ]")
                    d1 = re.replace(d1, "")
                    //  textview = findViewById(R.id.textView)
                    if(foodType=="No food"){platecountText.setText("0")}
                    else{
                        platecountText.setText(d1)}
                    Log.e("LOOK HERE", "${d1}")
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        ref.child("PlateCount").addListenerForSingleValueEvent(userListener)
    }
}

