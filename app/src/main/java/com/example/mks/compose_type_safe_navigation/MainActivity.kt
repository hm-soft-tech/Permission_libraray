package com.example.mks.compose_type_safe_navigation

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.example.mks.compose_type_safe_navigation.Nav.nav
import com.example.mks.compose_type_safe_navigation.ui.theme.Compose_type_Safe_NavigationTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Compose_type_Safe_NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                  nav()
                 // permissionexample()

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun permission(context: Context){
}



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun permissionexample(){

 val permissionstate = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)
 val lifecycle = LocalLifecycleOwner.current

  DisposableEffect(key1 = lifecycle,){
   val lifecycleObserver  = LifecycleEventObserver { source, event ->
         when(event){

         Lifecycle.Event.ON_START->{

          permissionstate.launchPermissionRequest()

         }
        else -> {}
         }
     }

   lifecycle.lifecycle.addObserver(lifecycleObserver)
   onDispose {

    lifecycle.lifecycle.removeObserver(lifecycleObserver)

   }


  }

    Column(
       modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
      if (permissionstate.status.isGranted)
      {
       Text(text = "Permission granted")

      }
     else
        {
         val text  = if (permissionstate.status.shouldShowRationale)
         {
             "Permission required"
         }
         else{
             "please provide permission"
         }

            Text(text = text)

        }





    }









}



@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun multiplepermissionexample(){

    val permissionstate = rememberMultiplePermissionsState(permissions = listOf(
     Manifest.permission.POST_NOTIFICATIONS,
     Manifest.permission.CAMERA

    ))
    val lifecycle = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycle,){
        val lifecycleObserver  = LifecycleEventObserver { source, event ->
            when(event){

                Lifecycle.Event.ON_START->{

                    permissionstate.launchMultiplePermissionRequest()

                }
                else -> {}
            }
        }

        lifecycle.lifecycle.addObserver(lifecycleObserver)
        onDispose {

            lifecycle.lifecycle.removeObserver(lifecycleObserver)

        }


    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

       permissionstate.permissions.forEach {
         when(it.permission)
         {
         Manifest.permission.POST_NOTIFICATIONS->{

             if (it.status.isGranted)
             {
                 Text(text = "Permission granted")

             }
             else
             {
                 val text  = if (it.status.shouldShowRationale)
                 {
                     "Permission required"
                 }
                 else{
                     "please provide permission"
                 }

                 Text(text = text)

             }


         }
          Manifest.permission.CAMERA->{
              if (it.status.isGranted)
              {
                  Text(text = "Permission granted")
              }
              else
              {
                  val text  = if (it.status.shouldShowRationale)
                  {
                      "Permission required"
                  }
                  else{
                      "please provide permission"
                  }

                  Text(text = text)

              }





          }



         }

       }







    }


}








@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Compose_type_Safe_NavigationTheme {
        Greeting("Android")
    }
}