package com.everoncarcharging.exception;

/**
 * Created by Puja on 05/07/20.
 */
public class ChargingSessionException extends Exception {
    private Exception cause;


    @Override
    public Exception getCause() {
        return cause;
    }

    public ChargingSessionException(Exception cause) {
        this.cause = cause;
    }

    public ChargingSessionException(String message) {
        super(message);
    }

    public ChargingSessionException(String message, Exception cause) {
        super(message);
        this.cause = cause;
    }



    public ChargingSessionException(String message, Throwable cause, Exception cause1) {
        super(message, cause);
        this.cause = cause1;
    }

    public ChargingSessionException(Throwable cause, Exception cause1) {
        super(cause);
        this.cause = cause1;
    }

    public ChargingSessionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Exception cause1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.cause = cause1;
    }

    public void setCause(Exception cause) {
        this.cause = cause;
    }
}
