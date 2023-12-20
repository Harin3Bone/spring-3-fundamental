package com.tutorial.springfundamental.controller;

import com.tutorial.springfundamental.dto.CustomerRecord;
import com.tutorial.springfundamental.entity.Customer;
import com.tutorial.springfundamental.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getUserById(@PathVariable("id") String id) {
        return customerService.getCustomerById(id);
    }

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createUser(@Valid @RequestBody CustomerRecord request) {
        return customerService.saveCustomer(request);
    }

}
