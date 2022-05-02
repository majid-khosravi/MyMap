package ir.majidkhosravi.mymap.ui.fragments

import android.os.Handler
import androidx.navigation.fragment.findNavController
import ir.majidkhosravi.mymap.R
import ir.majidkhosravi.mymap.ui.base.BaseFragment
import java.util.concurrent.TimeUnit


/**
 * This fragment plays the role of entrance for this application
 * when the user taps on the application in the launcher we show this fragment for 2 second
 * and we can take some configuration of application here if we have this option.
 *
 */

class SplashFragment : BaseFragment() {

    companion object {
        private val SPLASH_DELAY = TimeUnit.SECONDS.toMillis(2)
    }

    private val handler = Handler()

    private val runnable: Runnable = Runnable {
        findNavController().navigate(R.id.action_splashFragment_to_vehiclesListFragment)
    }

    override fun getLayoutResource(): Int = R.layout.fragment_splash

    override fun doOtherTasks() {
        handler.postDelayed(runnable, SPLASH_DELAY)
    }

    override fun onDetach() {
        handler.removeCallbacks(runnable)
        super.onDetach()
    }
}