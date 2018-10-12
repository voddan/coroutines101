package client

import khttp.get

object CoroutineClient

fun main(args: Array<String>) {
    get("http://127.0.0.1")
}