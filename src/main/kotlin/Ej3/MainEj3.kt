package Ej3

import kotlinx.coroutines.delay
    val USER = usuario("Lucas","67",678912345)
//Una función suspendida que simule una llamada a una API (con delay) y devuelva datos ficticios.

    suspend fun main(){
        println("Llamando API Lenta")
        val res = APILenta()
        println(res)
    }

    suspend fun APILenta(): String{
        delay(5000)
        return USER.id+" Numero: " +USER.numTel
    }
