package com.example.mks.compose_type_safe_navigation.util

import com.example.mks.compose_type_safe_navigation.Nav.dummy
import kotlinx.serialization.Serializable

sealed class Desti {



 @Serializable
 data object login:Desti()


 @Serializable
 data class home(val dummy: dummy) :Desti()


}