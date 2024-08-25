package com.example.mks.compose_type_safe_navigation.Nav

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.mks.compose_type_safe_navigation.Screens.home
import com.example.mks.compose_type_safe_navigation.Screens.login
import com.example.mks.compose_type_safe_navigation.util.Desti
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.reflect.KClass
import kotlin.reflect.typeOf


@Serializable
@Parcelize
data class dummy(
  val name:String,
  val age:Int
): Parcelable



val dummytype  = object : NavType<dummy>(false){
    override fun get(bundle: Bundle, key: String): dummy? {
     return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
      {
         bundle.getParcelable(key,dummy::class.java)
      }
     else{
        bundle.getParcelable(key)
     }
    }

    override fun parseValue(value: String): dummy {
         return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: dummy) {
        bundle.putParcelable(key, value)
    }

    override fun serializeAsValue(value: dummy): String {
        return Json.encodeToString(value)
    }
}



class custom_navtype<T:Parcelable>(
    private val clazz: KClass<T>,
    private val serializable: KSerializer<T>
   ):NavType<T>(false){

    override fun get(bundle: Bundle, key: String): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        {
            bundle.getParcelable(key,clazz.java)
        }
        else{
            bundle.getParcelable(key)
        }
    }

    override fun parseValue(value: String): T {
      return  Json.decodeFromString(serializable,value)
    }

    override fun put(bundle: Bundle, key: String, value: T) {
       bundle.putParcelable(key,value)
    }

    override fun serializeAsValue(value: T): String {
        return Json.encodeToString(serializable,value)
    }


}






@SuppressLint("SuspiciousIndentation")
@Composable
fun nav(){
  val navController  = rememberNavController()

      NavHost(navController = navController , startDestination = Desti.login){

      composable<Desti.login>{
       login {
       navController.navigate(Desti.home(dummy("ali",13)))
       }
      }

     composable<Desti.home>(
     typeMap = mapOf(typeOf<dummy>() to custom_navtype<dummy>(
      dummy::class,
      dummy.serializer()
     ))
     ){
      val dummy = it.toRoute<Desti.home>()
      home(dummy)
      }




      }














}