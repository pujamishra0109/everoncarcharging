package com.everoncarcharging.util;

import com.everoncarcharging.repository.ChargingSessionAggregatorRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Puja on 04/07/20.
 */
@Slf4j
@Component
public class ChargingSessionScheduler

{
    @Autowired
    ChargingSessionAggregatorRepository chargingSessionAggregatorRepository;

    private final Logger LOGGER= LoggerFactory.getLogger(ChargingSessionScheduler.class);
    private static final Duration ONE_MINUTE = Duration.ofMinutes(1L);


    /**
     * Scheduler that runs every 1sec and removes the sessions that are expired.
     */
    @Scheduled(fixedDelay = 1000)
  private void removeExpired() {

        LocalDateTime aMinuteAgo = LocalDateTime.now().minusMinutes(1);

        PriorityBlockingQueue<LocalDateTime> startQueue=chargingSessionAggregatorRepository.getStartSessionQueue();
        PriorityBlockingQueue<LocalDateTime> stoppedQueue=chargingSessionAggregatorRepository.getStoppedSession();

        while (!startQueue.isEmpty() && startQueue.peek().isBefore(aMinuteAgo)) {
               LOGGER.debug("Removing started {}", startQueue.peek());
            startQueue.remove();
        }

        while (!stoppedQueue.isEmpty() && stoppedQueue.peek().isBefore(aMinuteAgo)) {
            LOGGER.debug("Removing stopped {}", stoppedQueue.peek());
            stoppedQueue.remove();
        }
    }
}
