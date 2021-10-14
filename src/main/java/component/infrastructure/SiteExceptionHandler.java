package component.infrastructure;

import component.application.UnderstockedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class SiteExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> handleUnderstockedException(UnderstockedException e){
        return new ResponseEntity<String>("库存不足", HttpStatus.NOT_ACCEPTABLE);
    }
}
