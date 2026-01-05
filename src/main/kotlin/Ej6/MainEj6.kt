package Ej6

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration

// Enviar notificaciones periódicas mientras el usuario está activo.
// Practicar: while(isActive), cancelar corrutinas y Job
class MainEj6 {
    data class Notificacion(val Nombre: String, val mensaje: String)

    suspend fun enviarNotificaciones(notificacion: Notificacion, duracion: Long) {
        coroutineScope {
            val trabajo = launch {
                while (isActive) {
                    delay(3000)
                    println("Notificacion enviada: ${notificacion.mensaje}")
                }
                println("Notificaciones canceladas")
            }
            delay(duracion)
            trabajo.cancel()
        }

    }

    suspend fun main() = runBlocking {
        val main = MainEj6()
        main.enviarNotificaciones(MainEj6.Notificacion("Notificacion", "Hola"), 5000)
    }
}

fun main() = runBlocking {
    val main = MainEj6()
    val notificacion = MainEj6.Notificacion("Lucas", "¡Nuevo mensaje!")
    
    println("Iniciando envío de notificaciones...")
    main.enviarNotificaciones(notificacion, 10000L)

}
