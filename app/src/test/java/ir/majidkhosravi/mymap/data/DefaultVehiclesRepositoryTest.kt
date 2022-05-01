package ir.majidkhosravi.mymap.data

import ir.majidkhosravi.data.models.ApiResult
import ir.majidkhosravi.domain.models.UseCaseParams
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DefaultVehiclesRepositoryTest {

    private val poiList = VehiclesMockProvider.poiList
    private val error = VehiclesMockProvider.error
    private lateinit var remoteDataSource: FakeMapRemoteDateSource


    @Before
    fun createRepository() {
        remoteDataSource = FakeMapRemoteDateSource(poiList = poiList)
    }

    @Test
    fun getApiResult_fetchFromRemoteDateSource() = runBlockingTest {
        val result = remoteDataSource.getVehicleResponse(UseCaseParams())
        MatcherAssert.assertThat(result, IsEqual(ApiResult.Success(poiList)))
    }

    @Test
    fun getApiResult_fetchFromRemoteDateSource_withFailure() = runBlockingTest {
        val result = remoteDataSource.getVehicleResponse(null)
        MatcherAssert.assertThat(result, IsEqual(ApiResult.Failure(error)))
    }
}