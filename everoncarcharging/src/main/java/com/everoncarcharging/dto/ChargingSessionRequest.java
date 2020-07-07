package com.everoncarcharging.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Puja on 04/07/20.
 */
@Valid
public class ChargingSessionRequest {

    @NotNull
    private String stationId;

    public String getStationId() {
        return stationId;
    }

    public ChargingSessionRequest(@NotNull String stationId) {
        this.stationId = stationId;
    }

    public ChargingSessionRequest() {
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    @Override
    public String toString() {
        return "ChargingSessionRequest{" +
                "stationId='" + stationId + '\'' +
                '}';
    }
}
