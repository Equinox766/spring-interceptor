package org.equinox.interceptor.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class AppController {
    @GetMapping("/foo")
    public ResponseEntity<?> foo(HttpServletRequest request) {
        Map<String, Object> data = new HashMap<>();
        data.put("tittle", "handler foo del controller");
        data.put("time", new Date());
        data.put("message", request.getAttribute("message"));
        return ResponseEntity.ok(data);
    }

    @GetMapping("/baz")
    public Map<String, Object> baz() {
        return Collections.singletonMap("message", "handler baz del controller");
    }

    @GetMapping("/bar")
    public Map<String, Object> bar() {
        return Collections.singletonMap("message", "handler bar del controller");
    }
}
