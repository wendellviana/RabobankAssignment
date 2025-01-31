package nl.rabobank.controller.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

import nl.rabobank.exception.AccountNotFoundException;

@ControllerAdvice
public class AccountNotFoundAdvice {
  @ResponseBody
  @ExceptionHandler(AccountNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String accountNotFoundHandler(AccountNotFoundException ex) {
    return ex.getMessage();
  }
    
}
