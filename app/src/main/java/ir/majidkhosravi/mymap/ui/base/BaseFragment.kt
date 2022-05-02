package ir.majidkhosravi.mymap.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


/**
 *  Author information:
 *  Name: Majid Khosravi
 *  Email: majid.khosravi89@gmail.com
 */


/**
 * A basic class that is generated to handle some common functions and behaviors of our fragments
 * The whole of our fragments should extend this abstract class and then they have to implement those functions.
 *
 */

abstract class BaseFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(getLayoutResource(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        doOtherTasks()
        startObservation()
    }


    /**
     * In this abstract function, any sub-classes must assign a layout resource id
     * this layout id will inflate and use in the 'onCreateView', when we're creating the view of fragments
     */

    @LayoutRes
    protected abstract fun getLayoutResource(): Int



    /**
     * We have defined this abstract function to do any jobs when our view was created.
     * This function has called in the 'onViewCreated' method and every child fragment must have this.
     */

    protected abstract fun doOtherTasks()


    /**
     * Every fragment that includes @{link ViewModel}, has some observing methods and
     * according to this concern we have defined this open function.
     * Implement this function is optional and aspect of this property we have opened it
     * It has this meaning, every sub-classes can make the decision to have or not this method.
     */
    open fun startObservation(){}

}