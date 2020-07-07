package com.everoncarcharging.service;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.repository.ChargingSessionAggregatorRepository;
import com.everoncarcharging.util.ChargingSessionAggregatorUtil;
import com.everoncarcharging.util.ChargingSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Created by Puja on 04/07/20.
 */
@Service
@Slf4j
public class ChargingSessionAggregatorServiceImpl implements ChargingSessionAggregatorService {

    @Autowired
    ChargingSessionAggregatorRepository chargingSessionAggregatorRepository;

    @Autowired
    IModelMapper iModelMapper;

    /**
     *
     * @return ChargingSessionSummaryResponse that contains the total count,started sessions and stopped sessions
     */
    public ChargingSessionSummaryResponse getSessionSummary()
    {

        return chargingSessionAggregatorRepository.getSessionSummary();
    }

    /**
      * It add the time to the  started session queue so that when expired, It can be removed
     */

    public void startSession(){
        final LocalDateTime startTime = LocalDateTime.now();
        chargingSessionAggregatorRepository.startSession(startTime);


    }

    /**
      It add the time to the stopped session  queue so that when expired, It can be removed
     */
    public void stopSession(){
        final LocalDateTime stoppedTime = LocalDateTime.now();
        chargingSessionAggregatorRepository.stopSession(stoppedTime);


    }
}
