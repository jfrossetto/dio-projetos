package br.com.jfr.heroes.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/status")
public class StatusController implements Serializable {

    @GetMapping
    public ResponseEntity<String> showStatus() {
        return ResponseEntity.ok("online");
    }
}
