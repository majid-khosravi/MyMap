package ir.majidkhosravi.mymap.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.domain.models.PureResult
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


    private val showLoading: MutableLiveData<Boolean> = MutableLiveData(false)


    private val adapterRows = MutableLiveData<List<VehicleModel>>(ArrayList())

    fun getList(params: MapParams): LiveData<List<VehicleModel>> {
        viewModelScope.launch(globalDispatcher.main) {
            useCase(params).collect {
                when (it) {
                    is PureResult.Success -> {
                        showLoading.value = false
                        adapterRows.value = it.value.list
                    }
                    is PureResult.Loading -> {
                        showLoading.value = true
                    }
                    is PureResult.Error -> {
                        showLoading.value = false
                    }
                }
            }
        }
        return adapterRows
    }


    fun showLoading(): LiveData<Boolean> = showLoading

}

