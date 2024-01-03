package com.tutorial.springfundamental.service;

import com.tutorial.springfundamental.dto.CustomerRequest;
import com.tutorial.springfundamental.entity.Customer;
import com.tutorial.springfundamental.entity.Keyboard;
import com.tutorial.springfundamental.exception.InvalidException;
import com.tutorial.springfundamental.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerCaptor;

    @Test
    void testForGetCustomerById() throws ParseException {
        // given
        var customerId = UUID.fromString("3c257327-0852-4eae-a2d2-5922614c2fb6");
        var customer = mockCustomer(customerId);

        // when
        when(customerRepository.findById(any(UUID.class))).thenReturn(Optional.of(customer));

        var actual = customerService.getCustomerById(UUID.randomUUID().toString());

        // then
        assertThat(actual).isNotNull();
        assertThat(actual.getId()).isEqualTo(customerId);

        verify(customerRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void testForCreateCustomer() throws ParseException {
        // given
        var customerRequest = mockCustomerRecord();

        // when
        customerService.saveCustomer(customerRequest);

        // then
        verify(customerRepository, times(1)).save(customerCaptor.capture());

        var actual = customerCaptor.getValue();
        assertThat(actual).isNotNull();
        assertThat(actual.getUsername()).isEqualTo("MonikaRai");
        assertThat(actual.getEmail()).isEqualTo("KaiQiu@gmail.com");

        var isPasswordMatch = BCrypt.checkpw("KfCunu4i", actual.getPassword());
        assertThat(isPasswordMatch).isTrue();

        var sdf = new SimpleDateFormat("yyyy-MM-dd");
        assertThat(actual.getDateOfBirth()).isEqualTo(sdf.parse("1997-01-03"));
    }

    @Test
    void testForCreateCustomer_dateOfBirthInvalidate() {
        // given
        var customerRequest = new CustomerRequest(
                "MonikaRai", "KfCunu4i", "KaiQiu@gmail.com", "2024-01-03"
        );

        // when
        assertThatThrownBy(() -> customerService.saveCustomer(customerRequest))
                .isInstanceOf(InvalidException.class)
                .hasMessageContaining("Date of birth cannot be less than 12 years from now");
    }

    @Test
    void testForCreateCustomer_dbException() {
        // given
        var customerRequest = mockCustomerRecord();

        // when
        when(customerRepository.save(any(Customer.class))).thenThrow(new RuntimeException("DB is down"));

        // then
        assertThatThrownBy(() -> customerService.saveCustomer(customerRequest))
                .isInstanceOf(InvalidException.class)
                .hasMessageContaining("Failed to create user");
    }

    private Customer mockCustomer(UUID customerId) throws ParseException {
        var sdf = new SimpleDateFormat("yyyy-MM-dd");

        var customer = new Customer();
        customer.setId(customerId);
        customer.setUsername("MonikaRai");
        customer.setPassword("KfCunu4i");
        customer.setEmail("KaiQiu@gmail.com");
        customer.setDateOfBirth(sdf.parse("1997-01-03"));
        return customer;
    }

    private CustomerRequest mockCustomerRecord() {
        return new CustomerRequest("MonikaRai", "KfCunu4i", "KaiQiu@gmail.com", "1997-01-03");
    }
}
