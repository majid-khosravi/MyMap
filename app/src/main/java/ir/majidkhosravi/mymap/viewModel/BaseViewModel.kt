package ir.majidkhosravi.mymap.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ir.majidkhosravi.mymap.ui.adapter.UiAction

open class BaseViewModel : ViewModel() {


    private val _uiAction = SingleLiveEvent<UiAction?>(value = null)
    val uiAction: LiveData<UiAction?>
        get() = _uiAction


    protected fun setUiAction(action: UiAction) {
        _uiAction.value = action
    }
}