package com.everoncarcharging.exception;

/**
 * Created by Puja on 05/07/20.
 */
public class ChargingSessionRunTimeException extends RuntimeException {
    public ChargingSessionRunTimeException() {
        super();
    }

    public ChargingSessionRunTimeException(String message) {
        super(message);
    }

    public ChargingSessionRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ChargingSessionRunTimeException(Throwable cause) {
        super(cause);
    }

    protected ChargingSessionRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
