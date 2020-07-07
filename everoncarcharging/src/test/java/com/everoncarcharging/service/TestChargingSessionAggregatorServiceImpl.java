package com.everoncarcharging.service;

import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.mapper.IModelMapper;
import com.everoncarcharging.repository.ChargingSessionAggregatorRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Puja on 05/07/20.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestChargingSessionAggregatorServiceImpl {

    @InjectMocks
    ChargingSessionAggregatorServiceImpl chargingSessionAggregatorService;

    @Mock
    ChargingSessionAggregatorRepository chargingSessionAggregatorRepository;

    @Mock
    IModelMapper iModelMapper;

    @Test
    public void testGetSessionSummary(){
        ChargingSessionSummaryResponse chargingSessionSummaryResponse=new ChargingSessionSummaryResponse();
        chargingSessionSummaryResponse.setStartedCount(2);
        chargingSessionSummaryResponse.setStoppedCount(1);
        chargingSessionSummaryResponse.setTotalCount(3);
        when(chargingSessionAggregatorRepository.getSessionSummary()).thenReturn(chargingSessionSummaryResponse);
        ChargingSessionSummaryResponse chargingSessionSummaryResponse1=chargingSessionAggregatorService.getSessionSummary();
        Assert.assertEquals(Integer.valueOf(chargingSessionSummaryResponse.getStartedCount()),Integer.valueOf(chargingSessionSummaryResponse1.getStartedCount()));
        Assert.assertEquals(Integer.valueOf(chargingSessionSummaryResponse.getStoppedCount()),Integer.valueOf(chargingSessionSummaryResponse1.getStoppedCount()));
        Assert.assertEquals(Integer.valueOf(chargingSessionSummaryResponse.getTotalCount()),Integer.valueOf(chargingSessionSummaryResponse1.getTotalCount()));
        verify(chargingSessionAggregatorRepository).getSessionSummary();

    }


}
