package semabaef.ShopService.utils.validators;


import org.springframework.stereotype.Component;
import semabaef.ShopService.exceptions.ExceptionDescription;
import semabaef.ShopService.exceptions.RestApplicationException;
import semabaef.ShopService.models.enums.VehicleType;

@Component
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
    public VehicleType validateVehicleType(String text) {
       VehicleType res;
        try {
         res =   Enum.valueOf(VehicleType.class, text);
        } catch (Exception e) {
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
        }
        return res;
    }

    public void validateString(String text) {
        if (text.isEmpty())
            throw new RestApplicationException(ExceptionDescription.INVALID_REQUEST_ARGUMENTS);
    }
}
