package clients.random.single

import khttp.get


/**
 * Our server at http://127.0.0.1:8080/random
 * returns an integer value on GET
 * and that's it.
 */
object AsUsual


private val URL = "http://127.0.0.1:8080/random"


/**
 * import khttp.get
 * */
fun main(args: Array<String>) {
    val value = get(URL).content.toString(Charsets.UTF_8).toInt()

    println("We got $value!")
}











private val `next point` = LetsSuspend
