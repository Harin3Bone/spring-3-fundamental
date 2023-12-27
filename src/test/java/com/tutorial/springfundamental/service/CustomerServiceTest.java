package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.entity.Customer;
import com.tutorial.springfundamental.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testForGetCustomerById() {
        // given
        var customerId = UUID.fromString("3c257327-0852-4eae-a2d2-5922614c2fb6");

        var customer = new Customer();
        customer.setId(customerId);
        customer.setUsername("MonikaRai");
        customer.setPassword("KfCunu4i");
        customer.setEmail("KaiQiu@gmail.com");
        customer.setDateOfBirth(Date.from(new Date().toInstant()));

        // when
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.of(customer));

        var actual = customerService.getCustomerById(UUID.randomUUID().toString());

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(customerId);

        verify(customerRepository, times(1)).findById(any(UUID.class));
    }

}
