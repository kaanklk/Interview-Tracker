package tcs.interviewtracker.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;

@ControllerAdvice
public class RestResponseEntityHandler {

    @ResponseBody
    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    protected ResponseEntity<Object> handleAlreadyExists(ResourceAlreadyExistsException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleUnspecifiedException(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodNotAllowed.class)
    protected ResponseEntity<Object> handleMethodNotAllowedException(MethodNotAllowed ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ResponseBody
    @ExceptionHandler(value = Unauthorized.class)
    protected ResponseEntity<Object> handleUnauthorizedException(Unauthorized ex) {
        return new ResponseEntity<>("Trying to access a protected resource without proper authorization.",
                HttpStatus.UNAUTHORIZED);
    }

    @ResponseBody
    @ExceptionHandler(value = Forbidden.class)
    protected ResponseEntity<Object> handleForbiddenException(Forbidden ex) {
        return new ResponseEntity<>("The user does not have the necessary permissions for the resource.",
                HttpStatus.FORBIDDEN);
    }
}