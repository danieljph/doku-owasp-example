package com.doku.doku_owasp_example.insecure_deserialization.controller;

import com.doku.doku_owasp_example.insecure_deserialization.model.MyEntity;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@RequiredArgsConstructor
@RequestMapping("/insecure-deserialization")
@RestController
public class InsecureDeserializationController
{
    @SneakyThrows
    @PostMapping(value = "/simulate-on-jackson-object-mapper", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> simulateOnJacksonObjectMapper(@RequestBody MyEntity requestBodyRaw)
    {
        return ResponseEntity.ok("Your age is: " + requestBodyRaw.getUmur());
    }
}
