package ir.majidkhosravi.mymap.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.domain.models.PureResult
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.domain.usecases.MapUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val useCase: MapUseCase,
    private val globalDispatcher: GlobalDispatcher,
) : BaseViewModel() {


    private val _items: MutableLiveData<List<VehicleModel>> = MutableLiveData()

    val items: LiveData<List<VehicleModel>>
        get() = _items


    fun getList(params: MapParams) {
        viewModelScope.launch(globalDispatcher.main) {
            useCase(params).collect {
                when (it) {
                    is PureResult.Success -> {
                        _items.value = it.value.list
                    }
                    is PureResult.Loading -> {
                        Log.e("Majid", "Loading....")

                    }
                    is PureResult.Error -> {
                        Log.e("Majid", "Error: " + it.error)
                    }
                }
            }
        }
    }


}

