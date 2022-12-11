package semabaef.ShopService.models.document;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;
import semabaef.ShopService.models.enums.VehicleType;

import static semabaef.ShopService.models.ModelConstants.SETTING_PATH;
import static semabaef.ShopService.models.ModelConstants.VEHICLE_INDEX;

@Data
@Document(indexName = VEHICLE_INDEX)
@Setting(settingPath = SETTING_PATH)
public class Vehicle {

    @Id
    @Field(name = "id", type = FieldType.Keyword)
    private String id;

    @Field(name = "name", type = FieldType.Text)
    private String name;

    @Field(name = "x", type = FieldType.Float)
    private Float x;

    @Field(name = "y", type = FieldType.Long)
    private Long y;

    @Field(name = "creation_date", type = FieldType.Text)
    private String creationDate;

    @Field(name = "engine_power",type = FieldType.Integer)
    private Integer enginePower;


    @Field(name = "number_of_wheels", type = FieldType.Long)
    private Long numberOfWheels;


    @Field(name = "distance_travelled", type = FieldType.Double)
    private Double distanceTravelled;


    @Field(name = "type", type = FieldType.Text)
    private VehicleType type;
}
