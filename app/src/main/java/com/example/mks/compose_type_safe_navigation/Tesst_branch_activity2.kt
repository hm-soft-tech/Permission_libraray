package com.example.mks.compose_type_safe_navigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Tesst_branch_activity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tesst_branch2)
        add()
    }



 fun add(){
  val b =20+30
  Toast.makeText(this,"$b",Toast.LENGTH_LONG).show()
  }



}