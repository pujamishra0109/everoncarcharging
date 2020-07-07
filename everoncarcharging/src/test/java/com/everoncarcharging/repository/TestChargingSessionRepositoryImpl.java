package com.everoncarcharging.repository;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.util.ChargingSessionStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Puja on 05/07/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestChargingSessionRepositoryImpl {
    private static final Logger log = LoggerFactory.getLogger(TestChargingSessionRepositoryImpl.class);

    @Mock
    IModelMapper iModelMapper;

    @Spy
    @InjectMocks
    ChargingSessionRepositoryImpl chargingSessionRepository;

    @Test
    public void testCreateChargingSession() throws Exception{
        ChargingSessionRequest chargingSessionRequest=new ChargingSessionRequest("merlin");
        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");

        ChargingSession chargingSession=new ChargingSession();
        chargingSession.setId(uuid);
        chargingSession.setStationId("merlin");
        chargingSession.setStartedAt(LocalDateTime.now());
        chargingSession.setStoppedAt(LocalDateTime.now());
        chargingSession.setStatus(ChargingSessionStatus.FINISHED);
        Mockito.doReturn(chargingSession).when(chargingSessionRepository).mapToChargingSession(chargingSessionRequest);

        ChargingSession chargingSession1=chargingSessionRepository.createChargingSession(chargingSessionRequest);
        Assert.assertEquals(chargingSession1.getStationId(),chargingSession.getStationId());
        Assert.assertEquals(chargingSession1.getId(),chargingSession.getId());
        Assert.assertEquals(chargingSession1.getStatus(),chargingSession.getStatus());


    }


}