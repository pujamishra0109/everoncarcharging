package com.everoncarcharging.repository;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.util.ChargingSessionStatus;
import com.everoncarcharging.util.ChargingSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Puja on 04/07/20.
 */
@Component
public class ChargingSessionRepositoryImpl implements ChargingSessionRepository
{
    @Autowired
    IModelMapper iModelMapper;

    @Value("${number_of_sessions}")
    private int number_of_session;
    /**
     * Charging sessions are stored in map using the sessionId as the key to get constatnt time operation and
     * ConcurrentHashMap is used for thread safe to avoid concurrent modification exception
     */
    private static final Map<UUID,ChargingSession> chargingSessionMap=new ConcurrentHashMap<>();

    /**
     * stationIdUUID is a ConcurrentMap to store the stationId to UUID mapping so as to keep the track of the the number of stations that are available
     */
    private static final Map<String,Set<UUID>> stationIdUUID=new ConcurrentHashMap<>();



    private static final Logger logger= LoggerFactory.getLogger(ChargingSessionRepositoryImpl.class);


    /**
     *
     * @param chargingSessionRequest
     * @return
     * @throws ChargingSessionException
     */
    @Override
    public ChargingSession createChargingSession(ChargingSessionRequest chargingSessionRequest) throws ChargingSessionException{
        //throw exception for number of station
        ChargingSession chargingSession = mapToChargingSession(chargingSessionRequest);

        if (checkSessionOfStation(chargingSession))
              throw new ChargingSessionException("StationId " + chargingSession.getStationId() + " is  already occupied and has exceeded the max limit");
           chargingSessionMap.put(chargingSession.getId(), chargingSession);
        if(!stationIdUUID.containsKey(chargingSession.getStationId()))
            stationIdUUID.put(chargingSession.getStationId(),new HashSet<>());
            stationIdUUID.get(chargingSession.getStationId()).add(chargingSession.getId());

          return chargingSession;
    }


    public  ChargingSession mapToChargingSession(ChargingSessionRequest chargingSessionRequest){
        final LocalDateTime startedAt = LocalDateTime.now();
        final UUID uuid = UUID.randomUUID();
        return new ChargingSession(uuid,chargingSessionRequest.getStationId(),startedAt,null, ChargingSessionStatus.IN_PROGRESS);

    }

    /**
     * It takes the sessionId as the paramteter and changes the status of a session to finished
     * @param id sessionId
     * @return the ChargingSessionResponse with an updated status
     * @throws ChargingSessionException
     *
     */



    public ChargingSession updateChargingSession(String id) throws ChargingSessionException{
          validate(id);
        final LocalDateTime stoppedAt = LocalDateTime.now();
        chargingSessionMap.get(UUID.fromString(id)).setStoppedAt(stoppedAt);
        chargingSessionMap.get(UUID.fromString(id)).setStatus(ChargingSessionStatus.FINISHED);
        return chargingSessionMap.get(UUID.fromString(id));
    }

    /**
     * Gets all the list of the ChargingSessionResponse either in finished or in-progress state
     * @return  all the list of ChargingSessionResponse
     */

    public List<ChargingSession> findAllChargingSessions(){
            List<ChargingSession>chargingSessionList=  new ArrayList<>(chargingSessionMap.values());
            logger.debug("Size of the list",chargingSessionList.size());
        return chargingSessionList;


    }

    /**
     *
     * @param chargingSession
     * @return true if the maximum limit for a station has already reached.
     */
    boolean checkSessionOfStation(ChargingSession chargingSession){
        if(!stationIdUUID.containsKey(chargingSession.getStationId()))
            return false;
        if(stationIdUUID.get(chargingSession.getStationId()).size()==number_of_session){
                return true;
        }
            return false;

    }
    void validate(String id) throws ChargingSessionException
    {
        try {
            if (!chargingSessionMap.containsKey(UUID.fromString(id)))
                throw new ChargingSessionException("Session Id  " + id + " is invalid");
            if (chargingSessionMap.get(UUID.fromString(id)).getStatus() == ChargingSessionStatus.FINISHED)
                throw new ChargingSessionException("Session Id  " + id + " is already completed");
            if (stationIdUUID.containsKey(chargingSessionMap.get(UUID.fromString(id)).getStationId()))
                stationIdUUID.remove(chargingSessionMap.get(UUID.fromString(id)).getStationId());
        }
        catch (IllegalArgumentException e){
            throw e;
        }

    }


}