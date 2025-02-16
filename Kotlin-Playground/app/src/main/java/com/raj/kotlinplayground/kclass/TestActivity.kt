package com.raj.kotlinplayground.kclass

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlin.reflect.KClass

class TestActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        testActivity(this)//Passing Kotlin's KClass<T>
        testCompanion1(TestActivity)//Passing TestActivity.Companion
        testKClass(TestActivity::class)//Passing Kotlin's KClass<T>
        /**
         * Type mismatch.
         * Required: Class<TypeVariable(T)>
         * Found: TestActivity
         */
        //testJavaClass(this) //Gives above error
        genericTestJavaClass(TestActivity::class.java)//Passing java's Class<T>
    }

    companion object {
        fun show(fromActivity: Activity) {
            /**
             * Intent's source code is in java so it doesn't understands KClass<T>.
             * It understands java Class<T> so we used .java extension/adapter
             * to convert it to java Class<T>.
             */
            Intent(fromActivity, TestActivity::class.java)
        }

        fun testKClass(fromKotlinClass: KClass<TestActivity>) {

        }

        fun <T> genericTestJavaClass(fromJavaClass: Class<T>) {}


        fun testActivity(fromKotlinClass: TestActivity) {

        }

        fun testCompanion1(fromKotlinClass: TestActivity.Companion) {

        }
    }
}