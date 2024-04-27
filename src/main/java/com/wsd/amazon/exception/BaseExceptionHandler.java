package com.wsd.amazon.exception;

import com.wsd.amazon.utils.ApiResponse;
import com.wsd.amazon.utils.BaseService;
import com.wsd.amazon.utils.ResponseUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Objects;

@ControllerAdvice
public class BaseExceptionHandler extends BaseService {
    private static final String E100000 = "E100000";
    private static final String E100100 = "E100100";
    private static final String E100200 = "E100200";
    private static final String E100300 = "E100300";
    private static final String DB_EXCEPTION_MSG = "Database Exception.";
    private static final String SERVER_EXCEPTION_MSG = "Internal Service Exception.";
    private static final String ROOT_CAUSE = "Root Cause";
    private static final String COLON = ":";
    private static final String SPACE = " ";

    @ExceptionHandler({BaseException.class})
    public final ResponseEntity<ApiResponse<Void>> handleBaseException(BaseException ex) {
        logger.error(ex.getLocalizedMessage(), ex);
        ApiResponse<Void> apiResponse = ResponseUtil.buildErrorResponse(E100200, ex.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler({DataAccessException.class})
    public final ResponseEntity<ApiResponse<Void>> handleDBException(DataAccessException ex) {
        logger.error(ex.getLocalizedMessage(), ex);
        String rootCause = Objects.nonNull(ex.getRootCause()) ? ex.getRootCause().toString() : SPACE;
        logger.error(ROOT_CAUSE + COLON + SPACE + rootCause);
        ApiResponse<Void> apiResponse = ResponseUtil.buildErrorResponse(E100100, DB_EXCEPTION_MSG);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public final ResponseEntity<ApiResponse<Void>> handleValidationErrors(MethodArgumentNotValidException ex) {
        logger.error(ex.getLocalizedMessage(), ex);
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
        logger.error(ROOT_CAUSE + COLON + SPACE + errors.getFirst());
        ApiResponse<Void> apiResponse = ResponseUtil.buildErrorResponse(E100300, errors.getFirst());
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @ExceptionHandler({Exception.class})
    public final ResponseEntity<ApiResponse<Void>> handleCommonException(Exception ex) {
        logger.error(ex.getLocalizedMessage(), ex);
        ApiResponse<Void> apiResponse = ResponseUtil.buildErrorResponse(E100000, SERVER_EXCEPTION_MSG);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
