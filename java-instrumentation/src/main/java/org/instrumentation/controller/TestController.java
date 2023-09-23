package org.instrumentation.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class TestController {

    // Handling GET request
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello, World!";
    }

    // Handling GET request with a path variable
    @GetMapping("/greet/{name}")
    public String greetUser(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    // Handling POST request with request body
    @PostMapping("/add")
    public String addData(@RequestBody String data) {
        // Process the data and return a response
        return "Received data: " + data;
    }

    // Handling PUT request with request parameters
    @PutMapping("/update")
    public String updateData(@RequestParam String id, @RequestParam String newValue) {
        // Update data with the provided parameters
        return "Updated data with ID " + id + " to: " + newValue;
    }

    // Handling DELETE request
    @DeleteMapping("/delete/{id}")
    public String deleteData(@PathVariable String id) {
        // Delete data with the specified ID
        return "Deleted data with ID " + id;
    }
}
