package Ej4

import kotlinx.coroutines.*

// Obtener temperatura, humedad y viento en paralelo.

class MainEj4 {
    data class Datos(
        var temperatura: Float,
        val humedad: Float,
        val viento: Float
    )

    suspend fun pedirTemp(): Float {
        delay(500)
        return 44F
    }
    suspend fun pedirHum(): Float {
        delay(700)
        return 9.6F
    }
    suspend fun pedirViento(): Float {
        delay(1000)
        return 89.1F
    }

    suspend fun pedirTodo(): Datos {
        val temp = CoroutineScope(Dispatchers.IO).async {
            pedirTemp()
        }
        val hum = CoroutineScope(Dispatchers.IO).async {
            pedirHum()
        }
        val viento = CoroutineScope(Dispatchers.IO).async {
            pedirViento()
        }
        return Datos(temp.await(), hum.await(), viento.await())
    }
    suspend fun pedirTodo2(): Datos = coroutineScope { //Sugerido por la IA Deepseek
        val temperatura = async { pedirTemp() }
        val humedad = async { pedirHum() }
        val viento = async { pedirViento() }

        Datos(temperatura.await(), humedad.await(), viento.await())
    }

    companion object {
        fun main(args: Array<String>) {
            runBlocking {
            val mainEj4 = MainEj4()
            
            println("Ejecutando pedirTodo()")
            val startTime1 = System.currentTimeMillis()
            val resultado1 = mainEj4.pedirTodo()
            val endTime1 = System.currentTimeMillis()
            println("Resultado 1 - Temperatura: ${resultado1.temperatura}°C, Humedad: ${resultado1.humedad}%, Viento: ${resultado1.viento}km/h")
            println("Tiempo de ejecución: ${endTime1 - startTime1}ms")
            
            println("---\n")
            println("Ejecutando pedirTodo2() sugerencia de Deepseek")
            val startTime2 = System.currentTimeMillis()
            val resultado2 = mainEj4.pedirTodo2()
            val endTime2 = System.currentTimeMillis()
            println("Resultado 2 - Temperatura: ${resultado2.temperatura}°C, Humedad: ${resultado2.humedad}%, Viento: ${resultado2.viento}km/h")
            println("Tiempo de ejecución: ${endTime2 - startTime2}ms")
        }
    }
    }
}