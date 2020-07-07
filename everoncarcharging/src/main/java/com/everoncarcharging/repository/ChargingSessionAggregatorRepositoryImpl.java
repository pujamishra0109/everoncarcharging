package com.everoncarcharging.repository;

import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.util.ChargingSessionAggregatorUtil;
import com.everoncarcharging.util.ChargingSessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Puja on 04/07/20.
 */
@Service
@Slf4j
public class ChargingSessionAggregatorRepositoryImpl implements ChargingSessionAggregatorRepository {

    /**
     *  PriorityBlockingQueue is used to get the operation for adding and removing in log(n) time complexity and
     *  O(1) to get the size of the queue for the summary of the sessions
     */
    private static  final PriorityBlockingQueue<LocalDateTime> startedSessions=new PriorityBlockingQueue<>();
    private static   final PriorityBlockingQueue<LocalDateTime> stoppedSessions=new PriorityBlockingQueue<>();

    private static final Logger logger= LoggerFactory.getLogger(ChargingSessionAggregatorRepositoryImpl.class);

    /**
     *
     * @return ChargingSessionSummaryResponse that contains the total count,started sessions and stopped sessions
     * I takes O(1) time complexity to get the size of the queue
     */
    public ChargingSessionSummaryResponse getSessionSummary(){
       return new ChargingSessionSummaryResponse(startedSessions.size()+stoppedSessions.size(),startedSessions.size(),stoppedSessions.size());
    }

    /**
     * It add the time to the  started session queue so that when expired, It can be removed
     * It takes log(n) time complexity for add operation for a PriorityQueue
     * @param localDateTime
     */
     public void startSession(LocalDateTime localDateTime){
       startedSessions.offer(localDateTime);


   }

    /**
     * /**
     * It add the time to the stopped session  queue so that when expired, It can be removed
        It takes log(n) for add operation for add operation for a PriorityQueue
     * @param localDateTime
     */
    public void stopSession(LocalDateTime localDateTime){
        stoppedSessions.offer(localDateTime);

    }

    /**
     *
     * @return PriorityQueue to iterate over all the started sessions
     */
    public PriorityBlockingQueue getStartSessionQueue(){
        return startedSessions;
    }

    /**
     *
     * @return PriorityQueue to iterate over all the stopped sessions

     */
    public PriorityBlockingQueue getStoppedSession(){
        return stoppedSessions;
    }

}
