package com.everoncarcharging.controller;

import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.exceptionhandler.ChargingSessionExceptionHandler;
import com.everoncarcharging.model.ChargingSession;
import com.everoncarcharging.service.ChargingSessionAggregatorService;
import com.everoncarcharging.service.ChargingSessionService;
import com.everoncarcharging.util.ChargingSessionStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.api.scripting.JSObject;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;


import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by Puja on 05/07/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestChargingSessionController {

    private MockMvc mockMvc;

    @Mock
    ChargingSessionService chargingSessionService;

    @Mock
    ChargingSessionAggregatorService chargingSessionAggregatorService;



    @InjectMocks
    private ChargingSessionController chargingSessionController;



    @Before
    public void setUp() throws Exception{
        mockMvc= MockMvcBuilders.standaloneSetup(chargingSessionController).build();
    }

    @Test
    public void testCreateChargingSession() throws Exception {

        ChargingSessionRequest chargingRequest=new ChargingSessionRequest();
        chargingRequest.setStationId("merlin");
        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();
        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");
        chargingSessionResponse.setId(uuid);
        chargingSessionResponse.setStationId(chargingRequest.getStationId());
        chargingSessionResponse.setStartedAt(LocalDateTime.now());
        chargingSessionResponse.setStatus(ChargingSessionStatus.IN_PROGRESS);

     //  given(chargingSessionService.createChargingSession(Mockito.any(ChargingSessionRequest.class))).willReturn(chargingSessionResponse);
       when(chargingSessionService.createChargingSession(Mockito.any(ChargingSessionRequest.class))).thenReturn(chargingSessionResponse);


        mockMvc.perform(post("/chargingSessions").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(chargingRequest)))
                .andExpect(status().isOk());






    }
    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Test
    public void testUpdateChargingSessionById() throws Exception {

        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();
        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");
        chargingSessionResponse.setId(uuid);
        chargingSessionResponse.setStationId("merlin-jasmine");
        chargingSessionResponse.setStartedAt(LocalDateTime.now());
        chargingSessionResponse.setStatus(ChargingSessionStatus.FINISHED);

        when(chargingSessionService.updateChargingSession(uuid.toString())).thenReturn(chargingSessionResponse);

        mockMvc.perform(put("/chargingSessions/"+uuid.toString()).
                contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(chargingSessionResponse.getId().toString())))
                .andExpect(jsonPath("$.stationId", is(chargingSessionResponse.getStationId().toString())))
                .andExpect(jsonPath("$.status", is(chargingSessionResponse.getStatus().toString())));

        verify(chargingSessionService).updateChargingSession(uuid.toString());
    }



    @Test
    public void testFindChargingSessions() throws Exception{
        List<ChargingSessionResponse> chargingSessionResponseList=new ArrayList<>();
        ChargingSessionResponse chargingSessionResponse=new ChargingSessionResponse();
        UUID uuid=UUID.fromString("3e420d27-f2a3-48a6-a6db-4d9ed280a6e2");
        chargingSessionResponse.setId(uuid);
        chargingSessionResponse.setStationId("merlin-jasmine");
        chargingSessionResponse.setStartedAt(LocalDateTime.now());
        chargingSessionResponse.setStatus(ChargingSessionStatus.IN_PROGRESS);
        chargingSessionResponseList.add(chargingSessionResponse);

        when(chargingSessionService.findAllChargingSessions()).thenReturn(chargingSessionResponseList);

        mockMvc.perform(get("/chargingSessions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", Matchers.is(chargingSessionResponse.getId().toString())))
                .andExpect(jsonPath("$[0].stationId", Matchers.is(chargingSessionResponse.getStationId().toString())))
                .andExpect(jsonPath("$[0].status", Matchers.is(chargingSessionResponse.getStatus().toString())));

        verify(chargingSessionService).findAllChargingSessions();


    }


}
