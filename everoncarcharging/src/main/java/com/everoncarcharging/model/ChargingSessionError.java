package com.everoncarcharging.model;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Created by Puja on 05/07/20.
 */
public class ChargingSessionError {

    private HttpStatus status;
    private String message;
    private String error;

    public ChargingSessionError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public ChargingSessionError() {

    }

    public ChargingSessionError(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
