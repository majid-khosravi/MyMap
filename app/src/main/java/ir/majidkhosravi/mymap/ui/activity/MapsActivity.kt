package ir.majidkhosravi.mymap.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ir.majidkhosravi.mymap.R
import kotlinx.coroutines.InternalCoroutinesApi

/**
 *  Author information:
 *  Name: Majid Khosravi
 *  Email: majid.khosravi89@gmail.com
 */


/**
 * Here is an activity class that extended [AppCompatActivity]
 * and I've implemented it for some jobs:
 *
 * 1- I had the [InternalCoroutinesApi] annotation of coroutines,
 * this annotation marks declarations that are internal in coroutines API,
 * which means that should not be used outside of [kotlinx.coroutines],
 * because their signatures and semantics will change between future releases
 * without any warnings and without providing any migration aid
 *
 * 2- I've defined the [AndroidEntryPoint] of the Hilt DI library
 * this annotation marks an Android component class to be setup for injection
 * with the standard [Hilt] [Dagger] Android components.
 *
 * 3- Also, I have put the [NavHostFragment] into the layout of this activity.
 * I've created this project as a SingleActivity, it has this meaning that I'll have
 * only one Activity class along with many fragments that those will handle with [NavigationComponent]
 */


@InternalCoroutinesApi
@AndroidEntryPoint
class MapsActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
    }

}