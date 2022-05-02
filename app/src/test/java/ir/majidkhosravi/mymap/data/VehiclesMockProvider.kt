package ir.majidkhosravi.mymap.data

import ir.majidkhosravi.common.models.CoordinateModel
import ir.majidkhosravi.common.models.PoiList
import ir.majidkhosravi.common.models.VehicleModel
import ir.majidkhosravi.data.models.ErrorModel

/**
 * It's just a muck date provider that uses in our test cases
 */

object VehiclesMockProvider {

    private val vehicle1 = VehicleModel(
        id = 885185,
        coordinate = CoordinateModel(latitude = 53.63824927285555, longitude = 10.012222888498863),
        fleetType = "TAXI",
        heading = 259.5003295903374
    )

    private val vehicle2 = VehicleModel(
        id = 754413,
        coordinate = CoordinateModel(latitude = 53.45762197182161, longitude = 9.947030863004002),
        fleetType = "POOLING",
        heading = 54.32931449149854
    )

    private val vehicle3 = VehicleModel(
        id = 314448,
        coordinate = CoordinateModel(latitude = 53.4548078157469, longitude = 10.03852422333167),
        fleetType = "TAXI",
        heading = 95.9215333936495
    )

    private val vehicle4 = VehicleModel(
        id = 105270,
        coordinate = CoordinateModel(latitude = 53.64208036980284, longitude = 10.096776723210677),
        fleetType = "POOLING",
        heading = 222.07294704454654
    )


    private val vehicle5 = VehicleModel(
        id = 649329,
        coordinate = CoordinateModel(latitude = 53.443500015422124, longitude = 9.95526782082392),
        fleetType = "POOLING",
        heading = 215.81489500851964
    )


    val poiList = PoiList(list = mutableListOf(
        vehicle1, vehicle2, vehicle3, vehicle4, vehicle5
    ))


    val error = ErrorModel(message = "We had an error in the vehicles list fetching!")

}
