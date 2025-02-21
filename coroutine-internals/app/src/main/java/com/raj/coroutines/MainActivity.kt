package com.raj.coroutines

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raj.coroutines.ui.theme.CoroutiuneInternalsTheme
import java.util.concurrent.ArrayBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutiuneInternalsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    /**
                     * https://www.geeksforgeeks.org/blockingqueue-interface-in-java/
                     * BlockingQueue<Runnable> are ArrayBlockingQueue, LinkedBlockingDeque<E>, PriorityBlockingQueue<E>
                     *
                     * https://outcomeschool.com/blog/threadpoolexecutor-in-android
                     *
                     * https://www.geeksforgeeks.org/how-to-use-threadpoolexecutor-in-android/
                     *
                     */
                    /*val threadPool2 = ThreadPoolExecutor(
                        corePoolSIze = 40,
                        maximumPoolSize = 80,
                        keepAliveTime = 20L,
                        unit = TimeUnit.SECONDS,
                        workQueue = ArrayBlockingQueue<Runnable>(1000)
                    )*/

                    /**
                     * https://developer.android.com/reference/java/util/concurrent/ThreadPoolExecutor
                     *
                     * threadPool2.allowsCoreThreadTimeOut(true) - If no new task arrived withIn KeepAliveTime
                     * then same timeout and termination policy applies on core threads also.
                     */
                    //threadPool2.allowsCoreThreadTimeOut(true)

                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CoroutiuneInternalsTheme {
        Greeting("Android")
    }
}