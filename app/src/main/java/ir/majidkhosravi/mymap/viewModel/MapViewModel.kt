package ir.majidkhosravi.mymap.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.domain.models.PureResult
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.domain.usecases.MapUseCase
import ir.majidkhosravi.mymap.utils.UiAction
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The MapViewModel is a shared ViewModel between two fragment
 * it takes a list and in other words this class with an UseCase can fetch a list of vehicles
 */

/**
 * This ViewModel uses the {@link HiltViewModel} annotation to mark constructor dependency injection by Hilt
 * that annotation will be available for creation by the {@code HiltViewModel} containing a constructor
 * annotated with {@link javax.inject.Inject} will have its dependencies defined in the constructor
 * parameters injected by Dagger's Hilt.
 */


@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCase: MapUseCase,
    private val globalDispatcher: GlobalDispatcher,
) : BaseViewModel() {


    private val _adapterRows = SingleLiveEvent<List<VehicleModel>?>(value = null)
    val adapterRows: LiveData<List<VehicleModel>?>
        get() = _adapterRows

    fun fetchVehiclesList(params: MapParams?) {
        viewModelScope.launch(globalDispatcher.main) {
            useCase(params).collect {
                when (it) {
                    is PureResult.Success -> {
                        setUiAction(UiAction.ShowLoading(show = false))
                        _adapterRows.value = it.value.list
                    }
                    is PureResult.Loading -> {
                        setUiAction(UiAction.ShowLoading(show = true))
                    }
                    is PureResult.Error -> {
                        it.error.message?.let { message ->
                            setUiAction(UiAction.ShowErrorMessage(error = message))
                        }
                    }
                }
            }
        }
    }

}

