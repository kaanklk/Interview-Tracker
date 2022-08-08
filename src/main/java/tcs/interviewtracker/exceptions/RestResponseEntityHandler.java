package tcs.interviewtracker.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.MethodNotAllowed;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class RestResponseEntityHandler {

    @ResponseBody
    @ExceptionHandler(value = ResourceAlreadyExistsException.class)
    protected ResponseEntity<ErrorObject> handleAlreadyExists(ResourceAlreadyExistsException ex) {

        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.CONFLICT.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.CONFLICT);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleUnspecifiedException(Exception ex) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    protected ResponseEntity<ErrorObject> handleResourceNotFoundException(ResourceNotFoundException ex) {

        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.NOT_FOUND);
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

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorObject> handleMethodArgumentMismatchException(
            MethodArgumentTypeMismatchException ex) {
        ErrorObject errorObj = new ErrorObject();
        errorObj.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorObj.setMessage(ex.getMessage());
        errorObj.setTimestamp(new Date());

        return new ResponseEntity<ErrorObject>(errorObj, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = BadRequestException.class)
    protected ResponseEntity<Object> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}