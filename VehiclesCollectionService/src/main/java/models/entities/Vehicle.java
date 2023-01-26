package models.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import models.enums.VehicleType;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotNull
    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "coordinates_id")
    private Coordinates coordinates;

    @NotNull
    @Column(name = "creation_date")
    private java.time.LocalDateTime creationDate;

    @Column(name = "creation_day")
    private String creationDay;

    @Range(min = 1)
    @Column(name = "engine_power")
    private Integer enginePower;

    @NotNull
    @Range(min = 1)
    @Column(name = "number_of_wheels")
    private Long numberOfWheels;

    @Range(min = 0)
    @Column(name = "distance_travelled")
    private Double distanceTravelled;

    @NotNull
    @Column(name = "type")
    @Enumerated(EnumType.ORDINAL)
    private VehicleType type;
}
