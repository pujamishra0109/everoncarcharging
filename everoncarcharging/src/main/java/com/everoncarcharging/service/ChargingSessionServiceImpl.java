package com.everoncarcharging.service;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.exception.ChargingSessionValidationException;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.repository.ChargingSessionRepository;
import com.everoncarcharging.util.ChargingSessionStatus;
import com.everoncarcharging.util.ChargingSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Puja on 04/07/20.
 */
@Service
public class ChargingSessionServiceImpl implements ChargingSessionService {

    @Autowired
    IModelMapper iModelMapper;

    @Autowired
    private ChargingSessionRepository chargingSessionRepository;


    /**
     *
     * @param chargingSessionRequest
     * @return ChargingSessionResponse that contains the sessionId,stationId,startedTime,endTime and Status

     * @throws ChargingSessionException
     */

    public ChargingSessionResponse createChargingSession(ChargingSessionRequest chargingSessionRequest) throws ChargingSessionException {
            Objects.requireNonNull(chargingSessionRequest.getStationId());
            if(chargingSessionRequest.getStationId().length()==0)
                throw new ChargingSessionValidationException("StationId " + chargingSessionRequest.getStationId() + "cannot be empty");

          return   iModelMapper.mapToChargingSessionResponse(chargingSessionRepository.createChargingSession(chargingSessionRequest));

    }

/* It takes the sessionId as the paramteter and changes the status of a session to finished
 * @param id sessionId
 * @return the ChargingSessionResponse with an updated status
 * @throws ChargingSessionException
 */

public ChargingSessionResponse updateChargingSession(String id) throws ChargingSessionException
    {
            Objects.requireNonNull(id);
            if(Objects.isNull(id)|| id.length()==0)
                throw new ChargingSessionException("SessionId " + id + "cannot be empty");

         return  iModelMapper.mapToChargingSessionResponse(chargingSessionRepository.updateChargingSession(id));
    }
    /**
     * Gets all the list of the ChargingSessionResponse either in finished or in-progress state
     * @return  all the list of ChargingSessionResponse
     */

    public List<ChargingSessionResponse> findAllChargingSessions(){
                return getListChargingSessionResponse(chargingSessionRepository.findAllChargingSessions());
    }
    public List<ChargingSessionResponse> getListChargingSessionResponse(List<ChargingSession>chargingSessionList){

        List<ChargingSessionResponse>chargingSessionResponseList=new ArrayList<>();
        chargingSessionList.forEach(chargingSession->chargingSessionResponseList.add(iModelMapper.mapToChargingSessionResponse(chargingSession)));
        return chargingSessionResponseList;
    }


    /**
     * Converts the chargingSessionRequest to the ChargingSession Object
     * @param chargingSessionRequest
     * @return ChargingSession
     */


}
