package semabaef.ShopService.soap.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import semabaef.ShopService.models.enums.VehicleType;
import semabaef.ShopService.services.VehicleService;
import semabaef.ShopService.utils.validators.CommonValidator;
import semabaef.shop.search.SearchVehiclesByEnginePowerInRangeRequest;
import semabaef.shop.search.SearchVehiclesByEnginePowerInRangeResponse;
import semabaef.shop.search.SearchVehiclesByTypeRequest;
import semabaef.shop.search.SearchVehiclesByTypeResponse;


@Endpoint
@RequiredArgsConstructor
public class VehicleSearchEndpoint {
    private static final String NAMESPACE_URI = "http://semabaef/shop/search";
    private final VehicleService vehicleService;
    private final CommonValidator validator;


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchVehiclesByTypeRequest")
    @ResponsePayload
    public SearchVehiclesByTypeResponse searchVehiclesByType(@RequestPayload SearchVehiclesByTypeRequest req) {
        VehicleType type = validator.validateVehicleType(req.getType());
        SearchVehiclesByTypeResponse response = new SearchVehiclesByTypeResponse();
        response.getList().addAll(vehicleService.searchVehiclesByType(type));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "searchVehiclesByEnginePowerInRangeRequest")
    @ResponsePayload
    public SearchVehiclesByEnginePowerInRangeResponse searchVehiclesByEnginePowerInRange(@RequestPayload SearchVehiclesByEnginePowerInRangeRequest req) {
        Integer min = validator.validateInteger(req.getFrom());
        Integer max = validator.validateInteger(req.getTo());
        SearchVehiclesByEnginePowerInRangeResponse response = new SearchVehiclesByEnginePowerInRangeResponse();
        response.getList().addAll(vehicleService.searchVehiclesByEnginePowerInRange(min, max));
        return response;
    }



}
