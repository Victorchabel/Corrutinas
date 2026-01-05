package Ej2

import kotlinx.coroutines.*

fun main() = runBlocking {
    println("Iniciando temporizador (pulsa Enter para detener)")

    val job = launch {
        var segundo = 0
        while (true) {
            println(segundo)
            segundo++
            delay(1000)
        }
    }

    // Thread separado para no bloquear las corrutinas
    Thread {
        readLine() // Espera un Enter para bloquear
        runBlocking {
            job.cancel()
            println("Temporizador acabado")
        }
    }.start()

    job.join()
}