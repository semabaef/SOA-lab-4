package services.impl;

import dao.CoordinatesDao;
import models.entities.Coordinates;
import services.CoordinatesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class CoordinatesServiceImpl implements CoordinatesService {


    @EJB
    private CoordinatesDao coordinatesDao;

    @Override
    public Coordinates checkAndSaveCoordinatesIfAreNotInBD(Coordinates coordinates) {
        Coordinates oldCoordinates = coordinatesDao.findByXAndY(coordinates.getX(), coordinates.getY());
        if (oldCoordinates == null) {
            coordinatesDao.save(coordinates);
            return coordinates;
        }
        return oldCoordinates;
    }

}
