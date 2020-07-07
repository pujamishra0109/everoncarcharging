package com.everoncarcharging.mapper;

import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.util.ChargingSessionStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

/**
 * Created by Puja on 05/07/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestChargingSessionMapper {

    @Autowired
    IModelMapper iModelMapper;

    @Test
    public void mapToChargingSessionResponse(){
    ChargingSession chargingSession=this.getChargingSessionResponse();
        ChargingSessionResponse chargingSessionResponse=iModelMapper.mapToChargingSessionResponse(chargingSession);
        Assert.assertTrue(chargingSessionResponse.getStationId().equals(chargingSession.getStationId().toString()));
        Assert.assertTrue(chargingSessionResponse.getStartedAt().equals(chargingSession.getStartedAt()));
        Assert.assertTrue(chargingSessionResponse.getStoppedAt().equals(chargingSession.getStoppedAt()));
        Assert.assertTrue(chargingSessionResponse.getStatus().equals(chargingSession.getStatus()));


    }

    public ChargingSession getChargingSessionResponse(){
        ChargingSession chargingSession=new ChargingSession();
        chargingSession.setStationId("A");
        chargingSession.setStatus(ChargingSessionStatus.IN_PROGRESS);
        chargingSession.setStartedAt(LocalDateTime.now());
        chargingSession.setStoppedAt(LocalDateTime.now());
        return chargingSession;
    }


}
