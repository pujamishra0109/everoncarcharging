package com.everoncarcharging.repository;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.model.ChargingSession;

import java.util.List;

/**
 * Created by Puja on 04/07/20.
 */
public interface ChargingSessionRepository {

    ChargingSession createChargingSession(ChargingSessionRequest chargingSessionRequest) throws ChargingSessionException;

    ChargingSession updateChargingSession(String id) throws ChargingSessionException;

    List<ChargingSession> findAllChargingSessions();


}
