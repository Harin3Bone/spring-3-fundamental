package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.constants.ErrorMessage;
import com.tutorial.springfundamental.dto.CustomerRequest;
import com.tutorial.springfundamental.entity.Customer;
import com.tutorial.springfundamental.exception.InvalidException;
import com.tutorial.springfundamental.exception.NotFoundException;
import com.tutorial.springfundamental.repository.CustomerRepository;
import com.tutorial.springfundamental.utils.DateFormatUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(String id) {
        log.info("Get user by id: {}", id);
        return customerRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND.formatted("Customer")));
    }

    public Customer saveCustomer(CustomerRequest request) {
        // Validate pre-requisite
        try {
            log.info("Create user with username: {}", request.username());
            validateCustomer(request);

            // Create user
            var customer = new Customer();
            customer.setUsername(request.username());
            customer.setPassword(request.password());
            customer.setEmail(request.email());
            customer.setDateOfBirth(Date.valueOf(request.dateOfBirth()));

            return customerRepository.save(customer);
        } catch (InvalidException e) {
            log.error("Invalid request: {}", e.getMessage());
            throw e;
        }
    }

    private void validateCustomer(CustomerRequest request) {
        // Validate Age
        var dateOfBirth = DateFormatUtils.stringToLocalDate(request.dateOfBirth());
        var currentDate = LocalDate.now();
        var period = Period.between(dateOfBirth, currentDate);

        if (period.getYears() < 12) {
            log.debug("Input date of birth: {}", request.dateOfBirth());
            throw new InvalidException("Date of birth cannot be less than 12 years from now");
        }
    }
}
