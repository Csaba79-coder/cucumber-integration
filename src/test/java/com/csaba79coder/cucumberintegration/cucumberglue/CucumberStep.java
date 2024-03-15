package com.csaba79coder.cucumberintegration.cucumberglue;

import com.csaba79coder.cucumberintegration.model.UserRegistrationRequest;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
@Getter
@Setter
public class CucumberStep {

    @LocalServerPort
    private int port;

    private final RestTemplate restTemplate = new RestTemplate();

    private ResponseEntity<String> lastResponse;

    public void sendRegisterRequest(String username, String password) {
        String url = "http://localhost:" + port + "/api/register";
        UserRegistrationRequest request = new UserRegistrationRequest(username, password);
        lastResponse = restTemplate.postForEntity(url, request, String.class);
    }
}
