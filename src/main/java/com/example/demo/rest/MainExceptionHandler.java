package com.example.demo.rest;

//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestControllerAdvice;
//import org.springframework.web.servlet.NoHandlerFoundException;
//
//import com.example.demo.common.ErrorResponse;
//
//@RestControllerAdvice
//public class MainExceptionHandler {
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	@ResponseBody
//	public ErrorResponse notfound() {
//		return new ErrorResponse("custom_404", "message for 404 error code");
//	}
//
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
//	@ResponseBody
//	public ErrorResponse badRequest() {
//		return new ErrorResponse("custom_400", "message for 400 error code");
//	}
//}