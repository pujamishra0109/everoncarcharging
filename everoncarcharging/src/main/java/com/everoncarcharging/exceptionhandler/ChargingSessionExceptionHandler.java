package com.everoncarcharging.exceptionhandler;

import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.exception.ChargingSessionRunTimeException;
import com.everoncarcharging.exception.ChargingSessionValidationException;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.model.ChargingSessionError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

/**
 * Created by Puja on 05/07/20.
 */
@Service
@ControllerAdvice
public class ChargingSessionExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * It handles any ChargingSessionException
     * @param e of type ChargingSessionException
     * @return ResponseEntity Object to handle any ChargingSessionException
     */
    public ResponseEntity<Object> handleChargingSessionException(ChargingSessionException e){
        ChargingSessionError chargingSessionError=new ChargingSessionError(HttpStatus.BAD_REQUEST,e.getMessage());
        if(Objects.nonNull(e.getCause()) && e.getCause().getMessage()!=null)
            chargingSessionError.setError(e.getCause().getMessage());
        else
            chargingSessionError.setError("Please enter a valid input");

        return createResponseEntity(chargingSessionError);
    }


    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    /**
     * Handles any ChargingSessionValidationException
     * @param e of type ChargingSessionValidationException
     * @return ResponseEntity of ChargingSessionValidationException
     */
    public ResponseEntity<Object> handleChargingSessionException(ChargingSessionValidationException e){
        ChargingSessionError chargingSessionError=new ChargingSessionError(HttpStatus.BAD_REQUEST,e.getMessage());
        if(Objects.nonNull(e.getCause()) && e.getCause().getMessage()!=null)
            chargingSessionError.setError(e.getCause().getMessage());
        else
            chargingSessionError.setError("Please enter a valid input");

        return createResponseEntity(chargingSessionError);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ChargingSessionRunTimeException.class)
    public  ResponseEntity<Object> handleChargingSessionException(ChargingSessionRunTimeException ex,
                                                                   WebRequest request) {

        ChargingSessionError response = new ChargingSessionError(HttpStatus.NOT_FOUND, ex.getMessage(),ex.getCause().getMessage());

        return createResponseEntity(response);
    }


    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleChargingSessionException(Exception e){
        ChargingSessionError chargingSessionError=new ChargingSessionError(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(),e.getCause().getMessage());
        return createResponseEntity(chargingSessionError);
    }

    public ResponseEntity<Object> handleChargingSessionException(IllegalArgumentException e){

        ChargingSessionError chargingSessionError=new ChargingSessionError(HttpStatus.BAD_REQUEST,e.getMessage());
        if(Objects.nonNull(e.getCause()))
            chargingSessionError.setError(e.getCause().getMessage());
        else
            chargingSessionError.setError(e.getMessage());
        return createResponseEntity(chargingSessionError);
    }


    /**
     * BUILD
     * @param chargingSessionError
     * @return
     */
    public ResponseEntity<Object> createResponseEntity(ChargingSessionError chargingSessionError) {
        return new ResponseEntity<>(chargingSessionError, chargingSessionError.getStatus());
    }
}
