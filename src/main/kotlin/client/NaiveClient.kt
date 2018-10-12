package client

import khttp.get
import khttp.post
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

object RouletteGameClient {

    @JvmStatic fun main(args: Array<String>) {

        println("Welcome to play Roulette!")
        println("Do you bet ODD (1) or EVEN (2)? ")

        val guess = input.nextInt()

        val value = get("http://127.0.0.1:8080/random/slow").content.toString(Charsets.UTF_8).toInt()

        println("Roulette landed on $value")

        if(value % 2 == guess % 2)
            println("Congrats, your guess $guess was correct! ")
        else
            println("Sorry, your guess $guess was incorrect :( ")

    }

    val input = Scanner(System.`in`);
}


object BankClient {
    @JvmStatic fun main(args: Array<String>) {

        println("Welcome to our bank client!")

        val value = get("http://127.0.0.1:8080/sum")
            .content.toString(Charsets.UTF_8).toInt()

        println("You have $value on your account.")

        println("Increase or decrease your deposit by typing a number:")

        val num = input.nextInt()

        val response = post("http://127.0.0.1:8080/sum", data = num)
            .content.toString(Charsets.UTF_8)

        println(response)

    }

    val input = Scanner(System.`in`);
}

object InteractiveBankClient {
    @JvmStatic fun main(args: Array<String>) {

        println("Welcome to our interactive bank client!")

        val value = get("http://127.0.0.1:8080/sum")
            .content.toString(Charsets.UTF_8).toInt()

        println("You have $value on your account.")

        println("Increase or decrease your deposit by typing a number:")

        while(true) {
            val num = input.nextInt()

            val response = post("http://127.0.0.1:8080/sum/slow", data = num)
                .content.toString(Charsets.UTF_8)

            println(response)
        }

    }

    val input = Scanner(System.`in`);
}