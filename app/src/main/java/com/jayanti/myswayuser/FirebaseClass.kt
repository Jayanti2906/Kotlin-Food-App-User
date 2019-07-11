package com.jayanti.myswayuser

import android.widget.EditText
import com.google.firebase.database.FirebaseDatabase

class FirebaseClass {

    fun saveData(plainText: EditText)
    {
        val name = plainText.text.toString().trim()
        if(name.isEmpty())
        {
            plainText.error= "Please enter your name"
            return
        }
        val ref = FirebaseDatabase.getInstance().getReference("Reserved")
        val bookId:String? = ref.push().key
        val rdata = ReservedData(bookId,name)
        ref.child(bookId!!).setValue(rdata).addOnCompleteListener {

            bool_check = true
        }
    }

}