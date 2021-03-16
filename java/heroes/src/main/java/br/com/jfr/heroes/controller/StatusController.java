package br.com.jfr.heroes.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/status")
@Tag(name = "/status" , description = "health check da api")
public class StatusController implements Serializable {

    @Operation(summary = "Retorna status")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "api no ar")
    })
    @GetMapping
    public ResponseEntity<String> showStatus() {
        return ResponseEntity.ok("online");
    }
}
