
import Ej1.Preferencias
import Ej1.usuario
import java.net.URL
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

val PREFER_ADMIN = Preferencias(true, URL("https://example.com/default-avatar.png"))
val USUARIO_ADMIN = usuario("admin", "admin", PREFER_ADMIN, 666666667)

        fun main(args: Array<String>) {
            runBlocking {

                val loggedIn = login("admin", "admin")
                println("Login exitoso: $loggedIn")

                if (loggedIn) {
                    val perfil = cargarPerfil("admin")
                    println("Perfil cargado: ${perfil.id}")

                    val pfpUrl = cargarPfp(perfil)
                    println("URL de la imagen de perfil: $pfpUrl")
                } else {
                    println("No se pudo cargar el perfil: credenciales incorrectas")
                }
            }
        }

suspend fun login(id: String, pass: String): Boolean {
    return if (id == USUARIO_ADMIN.id && pass == USUARIO_ADMIN.passwd) {
        val perfil = cargarPerfil(id)
        println("Perfil cargado exitosamente: $perfil")
        true
    } else {
        println("Credenciales incorrectas")
        false
    }
}

suspend fun cargarPerfil(userId: String): usuario {
    delay(1000)
    return USUARIO_ADMIN
}

suspend fun cargarPfp(usuario: usuario): URL {
    delay(1000)
    return usuario.preferencias.pfp
}