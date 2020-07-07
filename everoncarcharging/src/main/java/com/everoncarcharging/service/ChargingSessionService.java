package com.everoncarcharging.service;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.model.ChargingSession;

import java.util.List;

/**
 * Created by Puja on 04/07/20.
 */
public interface ChargingSessionService {

     ChargingSessionResponse createChargingSession(ChargingSessionRequest chargingSessionRequest) throws ChargingSessionException;

    ChargingSessionResponse updateChargingSession(String id) throws ChargingSessionException;

    List<ChargingSessionResponse> findAllChargingSessions();


}
