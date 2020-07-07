package com.everoncarcharging.repository;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.model.ChargingSession;

import java.time.LocalDateTime;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Puja on 04/07/20.
 */
public interface ChargingSessionAggregatorRepository {

    ChargingSessionSummaryResponse getSessionSummary();
     void startSession(LocalDateTime localDateTime);
    void stopSession(LocalDateTime localDateTime);
    public PriorityBlockingQueue getStartSessionQueue();
    public PriorityBlockingQueue getStoppedSession();



    }
