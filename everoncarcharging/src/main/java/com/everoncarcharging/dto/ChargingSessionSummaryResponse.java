package com.everoncarcharging.dto;

/**
 * Created by Puja on 04/07/20.
 */
public class ChargingSessionSummaryResponse {

    private int totalCount;

    private int startedCount;

    private int stoppedCount;

    public ChargingSessionSummaryResponse() {
    }

    public ChargingSessionSummaryResponse(int totalCount, int startedCount, int stoppedCount) {
        this.totalCount = totalCount;
        this.startedCount = startedCount;
        this.stoppedCount = stoppedCount;
    }

    public int getStoppedCount() {
        return stoppedCount;
    }

    public void setStoppedCount(int stoppedCount) {
        this.stoppedCount = stoppedCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setStartedCount(int startedCount) {
        this.startedCount = startedCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getStartedCount() {
        return startedCount;
    }

    @Override
    public String toString() {
        return "ChargingSessionSummaryResponse{" +
                "totalCount=" + totalCount +
                ", startedCount=" + startedCount +
                ", stoppedCount=" + stoppedCount +
                '}';
    }
}
