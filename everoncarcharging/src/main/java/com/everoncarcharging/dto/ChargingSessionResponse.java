package com.everoncarcharging.dto;

import com.everoncarcharging.util.ChargingSessionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Puja on 04/07/20.
 */
public class ChargingSessionResponse {

    private UUID id;

    private String stationId;

    private LocalDateTime startedAt;

    private LocalDateTime stoppedAt;

    private ChargingSessionStatus status;


    public ChargingSessionStatus getStatus() {
        return status;
    }

    public void setStatus(ChargingSessionStatus status) {
        this.status = status;
    }



    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public ChargingSessionResponse() {

    }

    @Override
    public String toString() {
        return "ChargingSessionResponse{" +
                "id=" + id +
                ", stationId='" + stationId + '\'' +
                ", startedAt=" + startedAt +
                ", stoppedAt=" + stoppedAt +
                ", status=" + status +
                '}';
    }
}
