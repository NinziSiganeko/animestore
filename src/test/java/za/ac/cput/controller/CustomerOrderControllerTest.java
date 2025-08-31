package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.CustomerOrder;
import za.ac.cput.factory.AdminFactory;
import za.ac.cput.factory.CustomerOrderFactory;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerOrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private CustomerOrder customerOrder;
    private CustomerOrder customerOrder2;
    private static final String BASE_URL = "http://localhost:8080/customerOrder";


    @BeforeEach
    void setUp() {
        customerOrder = CustomerOrderFactory.createOrder(1, LocalDate.now(), "pending");
        customerOrder2  = CustomerOrderFactory.createOrder(2, LocalDate.now(), "received");

    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<CustomerOrder> postResponse = restTemplate.postForEntity(url, customerOrder,CustomerOrder.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println("Created: " + postResponse.getBody());

        ResponseEntity<CustomerOrder> postResponse2 = restTemplate.postForEntity(url, customerOrder2,CustomerOrder.class);
        assertNotNull(postResponse2);
        assertNotNull(postResponse2.getBody());
        System.out.println("Created: " + postResponse2.getBody());
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + 1;
        ResponseEntity<CustomerOrder> response = restTemplate.getForEntity(url, CustomerOrder.class);
        assertNotNull(response.getBody());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        CustomerOrder newCustomerOrder = new CustomerOrder.Builder().copy(customerOrder).setStatus("Pending 2").build();
        ResponseEntity<CustomerOrder> postResponse = restTemplate.postForEntity(url, newCustomerOrder, CustomerOrder.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        System.out.println(postResponse.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + 1;
        restTemplate.delete(url);
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        System.out.println("All Customer Orders:");
        System.out.println(response.getBody());
    }
}