package ru.spring.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.spring.model.User;

import java.util.List;
import java.util.Objects;

@RestController
public class UserRestController {

    private final String url = "http://91.241.64.178:7081/api/users";
    private String cookie;
    private StringBuilder code = new StringBuilder();
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getAllUsers() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null,
                new ParameterizedTypeReference<>() {});
        HttpHeaders requestHeaders = responseEntity.getHeaders();
        cookie = Objects.requireNonNull(requestHeaders.getFirst(HttpHeaders.SET_COOKIE)).substring(11, 43);

        return responseEntity.getBody();
    }

    public void addNewUser(@RequestBody User user) {
       // requestHeaders.add("Cookie", "JSESSIONID=" + cookie);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST,
                userHttpEntity(user), String.class);
        code.append(response.getBody());
    }

    public void editUser(@RequestBody User user) {
        //requestHeaders.add("Cookie", "JSESSIONID=" + cookie);
        //HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT,
                userHttpEntity(user), String.class);
        code.append(response.getBody());
    }

    public void deleteUser(User user) {
        //requestHeaders.add("Cookie", "JSESSIONID=" + cookie);
       // HttpEntity<User> requestEntity = new HttpEntity<>(user, requestHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url + "/" + user.getId(), HttpMethod.DELETE,
                userHttpEntity(user), String.class);
        code.append(response.getBody());
    }

    private HttpEntity<User> userHttpEntity(User user){
        HttpHeaders requestHeaders1 = new HttpHeaders();
        requestHeaders1.add("Cookie", "JSESSIONID=" + cookie);
        return new HttpEntity<>(user, requestHeaders1);
    }

    public String getCookie() {
        return cookie;
    }

    public StringBuilder getCode() {
        return code;
    }
}
