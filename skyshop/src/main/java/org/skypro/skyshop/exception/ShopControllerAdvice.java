package org.skypro.skyshop.exception;

import org.skypro.skyshop.model.basket.ShopError;  // Импорт модели (по твоей структуре)
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice  // Глобальный для контроллеров
public class ShopControllerAdvice {

    // Обработчик исключения
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        ShopError error = new ShopError("PRODUCT_NOT_FOUND", ex.getMessage());  // Code и message (самостоятельно выбрал code)
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  // 404 по заданию
    }
}
