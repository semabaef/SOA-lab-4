package services;


import models.entities.Coordinates;

import javax.ejb.Local;
import java.util.List;
@Local
public interface CoordinatesService {
    Coordinates checkAndSaveCoordinatesIfAreNotInBD(Coordinates coordinates);

}
