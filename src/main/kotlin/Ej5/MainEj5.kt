package Ej5

import kotlinx.coroutines.*

//Simular la descarga de varios archivos al mismo tiempo.
class MainEj5 {
    data class Archivo(val nombre: String, val tamanio: Int, var estado: Float)

    suspend fun descarga(archivo: Archivo) {
        while (archivo.estado < 100) {
            delay(500)
            archivo.estado += (0..3).random().toInt() // Generea numeros enteros entre el 0 y el 3
            println("Descargando ${archivo.nombre}: ${archivo.estado.toInt()}%")
        }
        println("¡${archivo.nombre} descargado al 100%!")
    }
    suspend fun verEstado(archivo: Archivo) {
        while (archivo.estado < 100) {
            delay(100)
            println("Estado de ${archivo.nombre}: ${archivo.estado.toInt()}%")
        }
    }
}

suspend fun main() = runBlocking {
    val main = MainEj5()
    val archivos = listOf(
        MainEj5.Archivo("Juego", 100, 0f),
        MainEj5.Archivo("Pelicula", 200, 0f),
        MainEj5.Archivo("Musica", 300, 0f)
    )

    val jobs = archivos.map { archivo -> //lambda que crea una corrutina para cada archivo
        launch { main.descarga(archivo) }
    }

    jobs.forEach { job ->
        job.join()
    }

    println("Todas las descargas han finalizado")
}