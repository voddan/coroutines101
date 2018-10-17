package clients.random.single

import clients.random.several.SumOfTwo
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicLong


/**
 * Coroutines need proper synchronization
 * on shared mutable state
 * just like threads
 */
object MaxLaunchSumAtomic


suspend fun main(args: Array<String>) {
    val sum = AtomicLong(0L)

    coroutineScope {
        repeat(1000_000) { i ->
            launch {
                sum.incrementAndGet()
            }
        }
    }

    println("The total sum is ${sum.get()}")
}






private val `back` = MaxLaunch

private val `next point` = SumOfTwo
