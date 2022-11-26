package exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionDescription {

    EMPTY_СONTENT("Пустое содержимое", 204),
    INVALID_REQUEST_ARGUMENTS("Неверный запрос", 400),
    OBJECT_VEHICLE_NOT_FOUND("Объект vehicle не были найдены", 404),
    OBJECT_VEHICLES_NOT_FOUND("Объекты vehicle не был найден по данному id", 404),
    INTERNAL_SERVER_ERROR("Внутренняя ошибка сервера", 500);
    private final String message;

    private final Integer code;

}