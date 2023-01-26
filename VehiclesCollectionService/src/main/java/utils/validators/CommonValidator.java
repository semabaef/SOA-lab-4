package utils.validators;

import exceptions.ExceptionDescription;
import exceptions.RestApplicationException;
import models.enums.VehicleType;

import javax.ejb.Stateless;

@Stateless
public class CommonValidator {

    public Long validateLong(String num) {
        Long res;
        if (num.isEmpty())
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        try {
            res = Long.valueOf(num);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return res;
    }

    public Integer validateInteger(String num) {
        Integer res;
        if (num.isEmpty())
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        try {
            res = Integer.valueOf(num);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return res;
    }
    public void validateVehicleType(String text) {
        try {
            Enum.valueOf(VehicleType.class, text);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
    }

    public void validateString(String text) {
        if (text.isEmpty())
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
    }
}
