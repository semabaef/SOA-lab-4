package models.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;


import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Coordinates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "x")
    private Float x;

    @Column(name = "y")
    private Long y;

    @OneToMany(mappedBy = "coordinates", fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Set<Vehicle> vehicles;
}
