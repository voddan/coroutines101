package client

fun veryBusyCpuTasks(times: Int = 10) {
    print("I am so busy..")
    repeat(times) {
        print(".")
        Thread.sleep(100)
    }
    println()
}