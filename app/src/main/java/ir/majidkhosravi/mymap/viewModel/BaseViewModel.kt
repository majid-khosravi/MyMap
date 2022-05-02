package ir.majidkhosravi.mymap.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ir.majidkhosravi.mymap.utils.UiAction

/**
 * We have added an item to take and provide it to observe as an UI-Action
 * the UiAction class can help us to pass and transformation of the UI states,
 * view click listeners and any type that needs to observe in views.
 */


open class BaseViewModel : ViewModel() {


    private val _uiAction = SingleLiveEvent<UiAction?>(value = null)
    val uiAction: LiveData<UiAction?>
        get() = _uiAction


    protected fun setUiAction(action: UiAction) {
        _uiAction.value = action
    }
}