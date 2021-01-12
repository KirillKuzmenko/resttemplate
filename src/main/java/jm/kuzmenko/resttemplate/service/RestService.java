package jm.kuzmenko.resttemplate.service;

import jm.kuzmenko.resttemplate.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class RestService {

    private static final String URL_API = "http://91.241.64.178:7081/api/users";

    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders httpHeaders = new HttpHeaders();

    private String session = null;

    public void getAllUser() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(URL_API, String.class);
        session = responseEntity.getHeaders().get("Set-Cookie").get(0);
    }

    public String addUser(User user) {
        httpHeaders.add("Cookie", session);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_API, requestBody, String.class);
        return responseEntity.getBody();
    }

    public String editUser(User user) {
        httpHeaders.add("Cookie", session);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> requestBody = new HttpEntity<>(user, httpHeaders);
        //ResponseEntity<String> responseEntity = restTemplate.postForEntity(URL_API, requestBody, String.class);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL_API, HttpMethod.PUT, requestBody, String.class);
        return responseEntity.getBody();
    }

    public String deleteUser(Long id) {
        httpHeaders.add("Cookie", session);
        HttpEntity<String> requestBody = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL_API + "/" + id, HttpMethod.DELETE, requestBody, String.class);
        return responseEntity.getBody();
    }
}
