package com.hansel.coroutine

import kotlinx.coroutines.*
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext

class TestCoroutine {

}

//fun main() {
//    GlobalScope.launch {
//        delay(1000)
//        println("World!")
//    }
//    println("Hello")
//    Thread.sleep(2000)
//}

//fun main() = runBlocking {// 开始执行主协程
//   GlobalScope.launch {// 在后台启动一个新的协程并继续
//       delay(1000)
//       println("Hansel")
//   }
//   println("Greet")// 主协程在这里会立即执行
//   delay(2000) // 延迟 2 秒来保证 JVM 存活
//}
/**
 * runBlocking的用法: main方法和test使用，阻塞线程
 * Runs new coroutine and **blocks** current thread _interruptibly_ until its completion.
 * This function should not be used from coroutine. It is designed to bridge regular blocking code
 * to libraries that are written in suspending style, to be used in `main` functions and in tests.
 *
 */
//等价于上面的main = runBlocking{ }
//fun main() {
//    GlobalScope.launch {
//        delay(1000)
//        println("liyang")
//        println(Thread.currentThread().name + " ===== ")
//    }
//    println("LiLei")// main thread continue here immediately
//    runBlocking {
//        println(Thread.currentThread().name)
//        delay(2000) // but this expression block main thread here
//    }
//}
//

//fun main() = runBlocking {
// 这里使用Lazy模式后，不会立即执行,添加job.join后执行.默认CoroutineStart.Default模式立即执行
//    val job  = GlobalScope.launch (EmptyCoroutineContext,CoroutineStart.LAZY){
//        println("Hello")
//    }

//    job.join()// wait until child coroutine completes
//    val complete = job.isCompleted

//    println("world" + " isComplete : ")
//}

/**
 * Structured concurrency结构化的多协程处理
 * 优化多个协程启动后的取消问题，不在全局的GlobleScope域内启动，
 * 在一个外部的作用域内部，执行多个协程。不用关心每个全局的协程最后
 * 的取消问题，因为join需要等待外部的协程内部的子协程全部complete
 * We can launch coroutines in this scope without having to join them explicitly,
 * because an outer coroutine (runBlocking in our example) does not complete
 * until all the coroutines launched in its scope complete.
 * Thus, we can make our example simpler:
 */
//fun main() = runBlocking {
//    println(Thread.currentThread().name + "  :: " + CoroutineName.toString())
//    launch {
//        println(Thread.currentThread().name + " === " + CoroutineName.toString())
//        println("Hello")
//    }
//    println("world")
//}
//===========================自定义scope==================================
//fun main() = runBlocking {
//    println(" 1 ================= runBlocking start ")
//
//        coroutineScope {
//            不会阻塞线程
//        delay(1000)
//            println("2 ================== custom coroutine ")
//            launch {
//                println(" 3 ==================children start ========")
//            }
//        }
//        launch {
//            println(" 4 ================== runBlocking scope launch")
//        }
//     end 先于runBlocking的协程launch执行
//        println("5 ================ end")
//}


//suspend fun main(){
//    val async = GlobalScope.async {
//        println(" defer ==== ")
//    }
//    async.await()
//}

//fun main() = runBlocking{
//    launch {
//        doWork()
//    }
//    println("Hello")
//}
//
//suspend fun doWork(){
//    println("do something")
//}
/** Global coroutines are like daemon threads 全局协程是守护线程的，线程执行完即退出*/
//fun main() {
//    GlobalScope.launch {
//        repeat(1000) {
//            println(" sleeping $it ... ")
//            delay(500)
//        }
//    }
//    Thread.sleep(2000)
//}



