package com.example.mks.compose_type_safe_navigation.Screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.mks.compose_type_safe_navigation.util.Desti


@Composable
fun login(move:()->Unit){

 Column(horizontalAlignment = Alignment.CenterHorizontally
 , verticalArrangement = Arrangement.Center
 ) {

    Text(text = "Move to next screen", fontSize = 18.sp,
    modifier = Modifier.clickable {
        move.invoke()
      }
        )

 }

}


@Composable
fun home(home:Desti.home){

    Column(horizontalAlignment = Alignment.CenterHorizontally
        , verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Next screen ${home.dummy.name} "+home.dummy.age, fontSize = 18.sp,

        )

    }

}
