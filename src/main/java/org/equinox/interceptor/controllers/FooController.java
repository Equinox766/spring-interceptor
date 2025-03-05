package org.equinox.interceptor.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class FooController {
    @GetMapping("/foo")
    public Map<String, Object> foo() {
        return Collections.singletonMap("message", "handler foo del controller");
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
