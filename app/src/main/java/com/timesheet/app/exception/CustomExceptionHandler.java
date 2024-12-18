package com.timesheet.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<?> categoryNotFoundException(){
        return new ResponseEntity<>("Category not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<?> countryNotFoundException(){
        return new ResponseEntity<>("Country not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> clientNotFoundException(){
        return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<?> employeeNotFoundException(){
        return new ResponseEntity<>("Employee not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProjectNotFoundException.class)
    public ResponseEntity<?> projectNotFoundException(){
        return new ResponseEntity<>("Project not found", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateUsernameException.class)
    public ResponseEntity<?> duplicateUsernameException(){
        return new ResponseEntity<>("The username is already in use. Please try a different one.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<?> optimisticLockException(){
        return new ResponseEntity<>("Optimistic lock failure - the data has been modified by another transaction. Please reload and try again.", HttpStatus.BAD_REQUEST);
    }
}
