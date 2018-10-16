package clients.random.single

import clients.execute
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache
import io.ktor.client.request.get
import org.apache.commons.io.IOUtils
import org.apache.http.impl.nio.client.HttpAsyncClients
import org.apache.http.client.methods.HttpGet


/**
 * Our server at http://127.0.0.1:8080/random
 * returns an integer value on GET
 * and that's it.
 */
object LetsSuspend


private val URL = "http://127.0.0.1:8080/random"

private val client = HttpClient(Apache)

/**
 * import io.ktor.client.HttpClient
 * import io.ktor.client.engine.apache.Apache
 * */
suspend fun main(args: Array<String>) {
    val value = client.get<String>(URL).toInt()

    println("We got $value")
}










/**
 * Continuation is a callback that contains the rest of the execution
 * */
object Same_Thing_With_Callbacks {
    val client = HttpAsyncClients.createDefault()

    @JvmStatic
    fun main(args: Array<String>) {
        client.start()

        val future = client.execute(HttpGet(URL)) { result ->
            val value = IOUtils.toString(result.entity.content, Charsets.UTF_8).toInt()

            println("We got $value")
        }

        future.get()
        client.close()
    }

}






private val `next point` = LaunchingACoroutine