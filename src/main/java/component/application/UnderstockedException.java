package component.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 库存不足
 */
@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class UnderstockedException extends Exception {

}
