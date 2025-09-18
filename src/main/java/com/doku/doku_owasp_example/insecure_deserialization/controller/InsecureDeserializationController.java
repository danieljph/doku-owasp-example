package com.doku.doku_owasp_example.insecure_deserialization.controller;

import com.doku.doku_owasp_example.insecure_deserialization.model.MyEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@RequiredArgsConstructor
@RestController
public class InsecureDeserializationController
{
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @PostMapping(value = "/simulates-json-insecure-deserialization", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> simulatesJsonInsecureDeserialization(@RequestBody MyEntity requestBodyRaw)
    {
        //MyEntity myEntity = objectMapper.readValue(requestBodyRaw, MyEntity.class);
        return ResponseEntity.ok("Your age is: ");
    }
}
