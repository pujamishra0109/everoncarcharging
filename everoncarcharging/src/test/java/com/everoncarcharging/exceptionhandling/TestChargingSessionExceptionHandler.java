package com.everoncarcharging.exceptionhandling;

import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.exception.ChargingSessionValidationException;
import com.everoncarcharging.exceptionhandler.ChargingSessionExceptionHandler;
import com.everoncarcharging.model.ChargingSessionError;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Puja on 05/07/20.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestChargingSessionExceptionHandler
{
    @Autowired
    ChargingSessionExceptionHandler exceptionHandler;

    @Test
    public void testHandleExceptionTypeIllegalArgumentException(){
        IllegalArgumentException e = new IllegalArgumentException();
        ResponseEntity<Object> responseEntity = exceptionHandler.handleChargingSessionException(e);
        Assert.assertTrue(null!=responseEntity.getBody());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getMessage()==e.getMessage());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getStatus().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(responseEntity.getStatusCodeValue() == 400);

    }
    @Test
    public void testHandleExceptionTypeChargeSessionException(){
        ChargingSessionException e = new ChargingSessionException("StationId  test is  already occupied and has exceeded the max limit");
        ResponseEntity<Object> responseEntity = exceptionHandler.handleChargingSessionException(e);
        Assert.assertTrue(null!=responseEntity.getBody());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getMessage()==e.getMessage());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getStatus().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(responseEntity.getStatusCodeValue() == 400);

    }
    @Test
    public void testHandleExceptionTypeChargeSessionValidationException(){
        ChargingSessionValidationException e = new ChargingSessionValidationException("StationId A  cannot be empty");
        ResponseEntity<Object> responseEntity = exceptionHandler.handleChargingSessionException(e);
        Assert.assertTrue(null!=responseEntity.getBody());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getMessage()==e.getMessage());
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getStatus().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(((ChargingSessionError) responseEntity.getBody()).getError().equals("Please enter a valid input"));
        Assert.assertTrue(responseEntity.getStatusCodeValue() == 400);

    }



}
