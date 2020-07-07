package com.everoncarcharging.model;

import com.everoncarcharging.util.ChargingSessionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Puja on 04/07/20.
 */
public class ChargingSession {

    private UUID id;


    private String stationId;

    private LocalDateTime startedAt;

    private LocalDateTime stoppedAt;

    private ChargingSessionStatus status;


    public ChargingSession(UUID id, String stationId, LocalDateTime startedAt, LocalDateTime stoppedAt, ChargingSessionStatus status) {
        this.id = id;
        this.stationId = stationId;
        this.startedAt = startedAt;
        this.stoppedAt = stoppedAt;
        this.status = status;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public void setStoppedAt(LocalDateTime stoppedAt) {
        this.stoppedAt = stoppedAt;
    }

    public ChargingSessionStatus getStatus() {
        return status;
    }

    public void setStatus(ChargingSessionStatus status) {
        this.status = status;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ChargingSession() {

    }

    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;

    }

    public String getStationId() {
        return stationId;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public LocalDateTime getStoppedAt() {
        return stoppedAt;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ChargingSession that = (ChargingSession) o;

        if (!id.equals(that.id)) return false;
        if (!stationId.equals(that.stationId)) return false;
        if (!startedAt.equals(that.startedAt)) return false;
        if (!stoppedAt.equals(that.stoppedAt)) return false;
        if (status != that.status) return false;
        return updatedAt.equals(that.updatedAt);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + stationId.hashCode();
        result = 31 * result + startedAt.hashCode();
        result = 31 * result + stoppedAt.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + updatedAt.hashCode();
        return result;
    }


}
