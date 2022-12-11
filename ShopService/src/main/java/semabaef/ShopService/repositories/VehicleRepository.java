package semabaef.ShopService.repositories;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import semabaef.ShopService.models.document.Vehicle;
import semabaef.ShopService.models.enums.VehicleType;

import java.util.List;

public interface VehicleRepository extends ElasticsearchRepository<Vehicle, Long> {

    // для примера сделал через @Query:
    @Query("{\"match\": {\"type\": {\"query\": \"?0\"}}}")
    List<Vehicle> findAllByVehicleType(VehicleType type);

    List<Vehicle> findAllByEnginePowerIsBetween(Integer min, Integer max);
}
