package com.everoncarcharging.service;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.repository.ChargingSessionRepository;
import com.everoncarcharging.util.ChargingSessionStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Puja on 05/07/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestChargingSessionServiceImpl {


    @Mock
    ChargingSessionRepository chargingSessionRepository;

    @Spy
    @InjectMocks
    ChargingSessionServiceImpl chargingSessionService;

    @Mock
    IModelMapper iModelMapper;


    public TestChargingSessionServiceImpl() {
    }

    @Test
    public void testUpdateChargingSessionTest() throws ChargingSessionException {

        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");

        ChargingSession chargingSession=new ChargingSession();
        chargingSession.setId(uuid);
        chargingSession.setStationId("merlin-jasmine");
        chargingSession.setStartedAt(LocalDateTime.now());
        chargingSession.setStatus(ChargingSessionStatus.FINISHED);

        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();
        chargingSessionResponse.setId(chargingSession.getId());
        chargingSessionResponse.setStationId(chargingSession.getStationId());
        chargingSessionResponse.setStartedAt(chargingSession.getStartedAt());
        chargingSessionResponse.setStatus(chargingSession.getStatus());




        when(chargingSessionRepository.updateChargingSession(anyString())).thenReturn(chargingSession);

        when(iModelMapper.mapToChargingSessionResponse(chargingSession)).thenReturn(chargingSessionResponse);

        // assertEquals(chargingSessionResponse,chargingSessionService.updateChargingSession(uuid.toString()));
        System.out.print("UUID from string " +uuid.toString());
        ChargingSessionResponse chargingSessionResponse1=chargingSessionService.updateChargingSession(uuid.toString());
        if(Objects.isNull(chargingSessionResponse1))
            System.out.print("Object is null");
        assertNotNull(chargingSessionResponse1);
        assertEquals(chargingSessionResponse.getStationId(),chargingSessionResponse1.getStationId());
        assertEquals(chargingSessionResponse.getId(),chargingSessionResponse1.getId());
        assertEquals(chargingSessionResponse.getStartedAt(),chargingSessionResponse1.getStartedAt());

        verify(chargingSessionRepository).updateChargingSession(uuid.toString());

    }

    @Test
    public void testcreateChargingSessionTest() throws ChargingSessionException {


        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();

        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");
        ChargingSession chargingSession=new ChargingSession();
        chargingSession.setId(uuid);
        chargingSession.setStationId("merlin-jasmine");
        chargingSession.setStartedAt(LocalDateTime.now());
        chargingSession.setStatus(ChargingSessionStatus.FINISHED);

        chargingSessionResponse.setId(chargingSession.getId());
        chargingSessionResponse.setStationId(chargingSession.getStationId());
        chargingSessionResponse.setStartedAt(chargingSession.getStartedAt());
        chargingSessionResponse.setStatus(chargingSession.getStatus());

        ChargingSessionRequest chargingSessionRequest=new ChargingSessionRequest();
        chargingSessionRequest.setStationId("merlin-jasmine");
        System.out.print(chargingSession+" "+ chargingSessionResponse);


        when(chargingSessionRepository.createChargingSession(chargingSessionRequest)).thenReturn(chargingSession);
        when(iModelMapper.mapToChargingSessionResponse(chargingSession)).thenReturn(chargingSessionResponse);

        ChargingSessionResponse chargingSessionResponse1=chargingSessionService.createChargingSession(chargingSessionRequest);
        assertEquals(chargingSessionResponse.getStatus(),chargingSessionResponse1.getStatus());

        verify(chargingSessionRepository).createChargingSession(chargingSessionRequest);

    }

    @Test
    public void testFindAllChargingSessions(){
        List<ChargingSession> chargingSessionList=new ArrayList<>();
        ChargingSession chargingSession=new ChargingSession();
        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");
        chargingSession.setId(uuid);
        chargingSession.setStationId("merlin-jasmine");
        chargingSession.setStartedAt(LocalDateTime.now());
        chargingSession.setStatus(ChargingSessionStatus.IN_PROGRESS);
        chargingSessionList.add(chargingSession);

        List<ChargingSessionResponse>chargingSessionResponseList=new ArrayList<>();
        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();
        chargingSessionResponse.setId(chargingSession.getId());
        chargingSessionResponse.setStationId(chargingSession.getStationId());
        chargingSessionResponse.setStartedAt(chargingSession.getStartedAt());
        chargingSessionResponse.setStatus(chargingSession.getStatus());
        chargingSessionResponseList.add(chargingSessionResponse);


        when(chargingSessionRepository.findAllChargingSessions()).thenReturn(chargingSessionList);

        Mockito.doReturn(chargingSessionResponseList).when(chargingSessionService).getListChargingSessionResponse(chargingSessionList);

        List<ChargingSessionResponse>chargingSessionResponseList1=chargingSessionService.findAllChargingSessions();
        ChargingSessionResponse firstChargingSessionRes=chargingSessionResponseList1.get(0);
        if(Objects.isNull(firstChargingSessionRes))
            System.out.print("Object is null for findAll");
        assertEquals(chargingSessionList.get(0).getId(),firstChargingSessionRes.getId());
        assertEquals(chargingSessionList.get(0).getStationId(),firstChargingSessionRes.getStationId());
        assertEquals(chargingSessionList.get(0).getStatus(),firstChargingSessionRes.getStatus());

        verify(chargingSessionRepository).findAllChargingSessions();



    }



}
