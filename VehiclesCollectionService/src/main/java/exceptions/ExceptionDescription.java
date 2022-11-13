package exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionDescription {
    INVALID_REQUEST_ARGUMENTS("Неверный запрос"),

    OBJECT_NOT_FOUND("Объекты не были найдены");


    private final String message;

}
