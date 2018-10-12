package client

import khttp.get
import java.util.*

object NaiveClient

fun main(args: Array<String>) {
    val value = get("http://127.0.0.1:8080/random")
        .content.toString(Charsets.UTF_8).toInt()

    println(value)

    val s = Scanner(System.`in`);
    val user = s.next()
    println(user)
}