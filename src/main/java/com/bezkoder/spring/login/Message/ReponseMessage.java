package com.bezkoder.spring.login.Message;

import ch.qos.logback.core.joran.action.Action;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReponseMessage {

    private String message;
    private Boolean status;
    private  Object data;


}
