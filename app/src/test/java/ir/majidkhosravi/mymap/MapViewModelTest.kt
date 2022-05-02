package ir.majidkhosravi.mymap

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.viewModelScope
import ir.majidkhosravi.common.utils.GlobalDispatcher
import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.data.utils.NetworkConstants
import ir.majidkhosravi.domain.models.PureResult
import ir.majidkhosravi.domain.usecases.MapParams
import ir.majidkhosravi.domain.usecases.MapUseCase
import ir.majidkhosravi.mymap.data.FakeMapRemoteDateSource
import ir.majidkhosravi.mymap.data.FakeMapRepository
import ir.majidkhosravi.mymap.data.VehiclesMockProvider
import ir.majidkhosravi.mymap.viewModel.MapViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * I have written two test case here about fetch vehicles list from [FakeMapRemoteDateSource] and its repository.
 *
 */

@ExperimentalCoroutinesApi
class MapViewModelTest {

    private val poiList = VehiclesMockProvider.poiList
    private val error = VehiclesMockProvider.error

    private lateinit var fakeMapRepository: FakeMapRepository
    private lateinit var remoteDataSource: FakeMapRemoteDateSource


    private lateinit var testDispatcher: TestCoroutineDispatcher

    private lateinit var viewModel: MapViewModel
    private lateinit var useCase: MapUseCase


    private var globalDispatcher: GlobalDispatcher = GlobalDispatcher(
        main = Dispatchers.Main,
        io = Dispatchers.IO,
        default = Dispatchers.Default
    )

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        remoteDataSource = FakeMapRemoteDateSource(poiList = poiList)
        fakeMapRepository = FakeMapRepository(remoteDataSource)
        useCase = MapUseCase(fakeMapRepository)
        viewModel = MapViewModel(
            useCase = useCase,
            globalDispatcher = globalDispatcher
        )
    }

    @Before
    fun setupCoroutine() {
        testDispatcher = TestCoroutineDispatcher()
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun getVehiclesList_withCorrectResponse() {
        viewModel.fetchVehiclesList(
            MapParams(
                lat1 = NetworkConstants.p1Lat,
                lon1 = NetworkConstants.p1Lon,
                lat2 = NetworkConstants.p2Lat,
                lon2 = NetworkConstants.p2Lon
            )
        )

        val result = viewModel.adapterRows.getOrAwaitValue()
        MatcherAssert.assertThat(result, IsEqual(poiList.list))
    }

    @Test
    fun getVehiclesList_withErrorResponse() {
        viewModel.viewModelScope.launch(globalDispatcher.main) {
            useCase(null).collect {
                if (it is PureResult.Error) {
                    MatcherAssert.assertThat(it.error, IsEqual(error))
                }
            }
        }
    }

}