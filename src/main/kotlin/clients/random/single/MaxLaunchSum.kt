package clients.random.single

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * How many coroutines can we launch
 * without being limited by a server
 * or stdout?
 */
object MaxLaunchSum






suspend fun main(args: Array<String>) {
    var sum = 0L

    coroutineScope {
        repeat(1000_000) {
            launch {
                sum += 1
            }
        }
    }

    println("The total sum is $sum")
}





private val `next point` = MaxLaunchSumAtomic
