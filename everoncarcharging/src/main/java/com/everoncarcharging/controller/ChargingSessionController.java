package com.everoncarcharging.controller;

/**
 * Created by Puja on 04/07/20.
 */
import com.everoncarcharging.dto.ChargingSessionRequest;
import com.everoncarcharging.dto.ChargingSessionResponse;
import com.everoncarcharging.dto.ChargingSessionSummaryResponse;
import com.everoncarcharging.exception.ChargingSessionException;
import com.everoncarcharging.exception.ChargingSessionValidationException;
import com.everoncarcharging.exceptionhandler.ChargingSessionExceptionHandler;
import com.everoncarcharging.service.ChargingSessionAggregatorService;
import com.everoncarcharging.service.ChargingSessionService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;


@RestController
public class ChargingSessionController {

    @Autowired
    ChargingSessionService chargingSessionService;

    @Autowired
    ChargingSessionAggregatorService chargingSessionAggregatorService;

    @Autowired
    ChargingSessionExceptionHandler exceptionHandler;

    /**
     * It creates Sessions using the stationId sent in the request
     * @param chargingSessionRequest that contains the stationId
     * @return ChargingSessionResponse that hold the sessionID created,stationId with start time,end time and status
     */
    @RequestMapping(value = "/chargingSessions", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<?> createChargingSession(@Valid @RequestBody ChargingSessionRequest chargingSessionRequest) {

          try {
              ChargingSessionResponse response=chargingSessionService.createChargingSession(chargingSessionRequest);
              chargingSessionAggregatorService.startSession();
              if(Objects.nonNull(response))
                  return new ResponseEntity<ChargingSessionResponse>(response, HttpStatus.OK);
              return new ResponseEntity<ChargingSessionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);

          }
          catch (ChargingSessionValidationException e){
              return exceptionHandler.handleChargingSessionException(e);
          }
          catch (ChargingSessionException e){
              return exceptionHandler.handleChargingSessionException(e);
          }

          catch (Exception e){
              return exceptionHandler.handleChargingSessionException(e);
          }

    }

    /**
     * Retrieves all the charging sessions
     * @return List of ChargingSessionResponse
     */

    @RequestMapping(value = "/chargingSessions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<ChargingSessionResponse>> findChargingSessions() {

        List<ChargingSessionResponse> response=chargingSessionService.findAllChargingSessions();
        if(Objects.nonNull(response) && response.size()>0)
            return new ResponseEntity<List<ChargingSessionResponse>>(response, HttpStatus.OK);

        return new ResponseEntity<List<ChargingSessionResponse>>(chargingSessionService.findAllChargingSessions(), HttpStatus.NO_CONTENT);

    }

    /**
     * It changes the station of a session from in progress to finished if it's in in-progress
     * @param id which is the sessionId created
     * @return ChargingSessionResponse
     */

    @RequestMapping(value = "/chargingSessions/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> updateChargingSessionById(@PathVariable(required = true) String id) {

        try {
            ChargingSessionResponse response=chargingSessionService.updateChargingSession(id);

            chargingSessionAggregatorService.stopSession();
                return new ResponseEntity<ChargingSessionResponse>(response, HttpStatus.OK);


        }
        catch (ChargingSessionValidationException e){
            return exceptionHandler.handleChargingSessionException(e);
        }


        catch (ChargingSessionException e){
            return exceptionHandler.handleChargingSessionException(e);
        }
        catch (IllegalArgumentException e){
            return exceptionHandler.handleChargingSessionException(e);
        }
        catch (Exception e){
            return exceptionHandler.handleChargingSessionException(e);
        }


    }

    /**
     * Returns the summary of the session for last minute
     * @return ChargingSessionSummaryResponse that has total number of sessions,started session and stopped session for last one minute
     */
    @RequestMapping(value = "/chargingSessions/summary", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ChargingSessionSummaryResponse> findChargingSummary() {
        return new ResponseEntity<ChargingSessionSummaryResponse>(chargingSessionAggregatorService.getSessionSummary(), HttpStatus.OK);

    }


}
