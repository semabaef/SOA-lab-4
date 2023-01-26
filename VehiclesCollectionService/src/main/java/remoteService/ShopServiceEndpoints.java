package remoteService;

public interface ShopServiceEndpoints {
    final String BASE_URL = "https://localhost:6334/shop" ;

    final String VEHICLES = "/vehicles";

    final String VEHICLES_ID = "/vehicles/{id}";
}
