package dev.andreisima.notification_scheduler.common.handlers;

import dev.andreisima.notification_scheduler.adapters.out.web.jsonapi.JsonApiErrorResponse;
import dev.andreisima.notification_scheduler.common.exceptions.NotificationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotificationNotFoundException.class)
    public ResponseEntity<JsonApiErrorResponse> handleNotFound(NotificationNotFoundException ex) {
        var error = new JsonApiErrorResponse(List.of(
                new JsonApiErrorResponse.ErrorObject(
                        "404",
                        "Notification Not Found",
                        ex.getMessage()
                )
        ));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<JsonApiErrorResponse> handleGeneric(Exception ex) {
        var error = new JsonApiErrorResponse(List.of(
                new JsonApiErrorResponse.ErrorObject(
                        "500",
                        "Internal Server Error",
                        ex.getMessage()
                )
        ));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
