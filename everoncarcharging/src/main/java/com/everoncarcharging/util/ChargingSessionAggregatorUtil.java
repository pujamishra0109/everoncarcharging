package com.everoncarcharging.util;

import java.time.LocalDateTime;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by Puja on 04/07/20.
 */
public  class ChargingSessionAggregatorUtil {
     public  final PriorityBlockingQueue<LocalDateTime> startedSessions=new PriorityBlockingQueue<>();
     public  final PriorityBlockingQueue<LocalDateTime> stoppedSessions=new PriorityBlockingQueue<>();


}
