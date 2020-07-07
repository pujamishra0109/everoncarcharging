package com.everoncarcharging.util;

import com.everoncarcharging.model.ChargingSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Puja on 04/07/20.
 */

public  class ChargingSessionUtil {


    public  final LocalDateTime startedAt = LocalDateTime.now();
    public  final UUID uuid = UUID.randomUUID();




}
