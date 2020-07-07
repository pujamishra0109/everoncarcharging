package com.everoncarcharging.exception;

/**
 * Created by Puja on 05/07/20.
 */
public class ChargingSessionValidationException extends ChargingSessionException {

    String param;

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public ChargingSessionValidationException(Exception cause, String param) {
        super(cause);
        this.param = param;
    }
    public ChargingSessionValidationException(String message) {
        super(message);
     }

    public ChargingSessionValidationException(String message, Exception cause, String param) {
        super(message, cause);
        this.param = param;
    }

    public ChargingSessionValidationException(String message, Throwable cause, Exception cause1, String param) {
        super(message, cause, cause1);
        this.param = param;
    }

    public ChargingSessionValidationException(Throwable cause, Exception cause1, String param) {
        super(cause, cause1);
        this.param = param;
    }

    public ChargingSessionValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Exception cause1, String param) {
        super(message, cause, enableSuppression, writableStackTrace, cause1);
        this.param = param;
    }
}
