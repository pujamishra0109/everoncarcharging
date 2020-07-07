package com.everoncarcharging.mapper;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.model.ChargingSession;

import java.util.List;

/**
 * Created by Puja on 04/07/20.
 */
public interface IModelMapper {

    ChargingSessionResponse mapToChargingSessionResponse(ChargingSession chargingSession);

}
