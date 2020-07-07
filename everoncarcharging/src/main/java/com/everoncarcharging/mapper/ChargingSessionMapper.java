package com.everoncarcharging.mapper;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.util.ChargingSessionStatus;
import com.everoncarcharging.util.ChargingSessionUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Created by Puja on 04/07/20.
 */
@Service
public class ChargingSessionMapper implements IModelMapper {

    ModelMapper modelMapper=new ModelMapper();

    /**
     *
     * @param chargingSession
     * @return
     */


    public ChargingSessionResponse mapToChargingSessionResponse(ChargingSession chargingSession){
        return modelMapper.map(chargingSession,ChargingSessionResponse.class);

    }



}
