package nl.rabobank.controller;

import io.swagger.v3.oas.annotations.Operation;
import nl.rabobank.dto.PowerOfAttorney;
import nl.rabobank.service.PowerOfAttorneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "rest/poa", produces = MediaType.APPLICATION_JSON_VALUE)
public class PowerOfAttorneyController {

    private PowerOfAttorneyService powerOfAttorneyService;

    @Autowired
    public PowerOfAttorneyController(PowerOfAttorneyService powerOfAttorneyService) {
        this.powerOfAttorneyService = powerOfAttorneyService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Adds new authorization for User")
    public void createPowerOfAttorney(@RequestBody PowerOfAttorney powerOfAttorney){
        powerOfAttorneyService.authorizeAccessOnAccount(powerOfAttorney);
    }
}
