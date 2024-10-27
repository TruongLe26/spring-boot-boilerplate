package com.truonglq.demo.exceptions;

import com.truonglq.demo.dtos.responses.ApiResponse;
import com.truonglq.demo.dtos.responses.StandardApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException exception) {
//        ApiResponse apiResponse = new ApiResponse();
//
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
////        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//        apiResponse.setMessage(exception.getMessage());
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    ResponseEntity<StandardApiResponse<Void>> handleGeneralExistedException(EntityAlreadyExistsException exception) {
        // Changed from HttpStatus.CONFLICT.value() to class own status code
        StandardApiResponse<Void> apiResponse = StandardApiResponse.error(exception.getStatusCode(), exception.getMessage());
        return ResponseEntity.status(exception.getStatusCode()).body(apiResponse);
    }

    @ExceptionHandler(BadCredentialsException.class)
    ResponseEntity<StandardApiResponse<Void>> handleBadCredentialsException(BadCredentialsException exception) {
        StandardApiResponse<Void> apiResponse = StandardApiResponse.error(ErrorCode.UNAUTHENTICATED.getCode(), ErrorCode.UNAUTHENTICATED.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String enumKey = exception.getFieldError().getDefaultMessage();

        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException e) {
        }

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }

}
