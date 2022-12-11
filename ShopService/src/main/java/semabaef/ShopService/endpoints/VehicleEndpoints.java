package semabaef.ShopService.endpoints;

public interface VehicleEndpoints {

    final String MAIN_URL = "/shop" ;
    final String SEARCH_BY_TYPE = "/search/by-type/{type}";
    final String SEARCH_BY_ENGINE_POWER_IN_RANGE = "/search/by-engine-power/{from}/{to}";

    final String VEHICLES = "/vehicles";

    final String VEHICLES_ID = "/vehicles/{id}";

}
