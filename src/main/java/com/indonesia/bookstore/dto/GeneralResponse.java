package com.indonesia.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

@Data
@Builder
@AllArgsConstructor
public class GeneralResponse {

    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object data){

        HashMap<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("status", status);
        response.put("data", data);

        return new ResponseEntity<Object>(response, status);
    }
}
