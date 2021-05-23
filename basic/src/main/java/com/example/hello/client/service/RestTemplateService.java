package com.example.hello.client.service;

import com.example.hello.client.dto.Req;
import com.example.hello.client.dto.UserRequest;
import com.example.hello.client.dto.UserResponse;
import com.example.hello.dto.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

// Server to Server
@Service
public class RestTemplateService {

    public UserResponse hello() {

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/hello")
                .queryParam("name", "steve")
                .queryParam("age", 10)
                .encode()
                .build()
                .toUri();

        System.out.println(uri.toString());

        RestTemplate restTemplate = new RestTemplate();
//        String result = restTemplate.getForObject(uri, String.class);
//        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        ResponseEntity<UserResponse> result = restTemplate.getForEntity(uri, UserResponse.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getBody());

        return result.getBody();
    }


    public UserResponse post() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // pathVariable 와 순서대로 매핑된다.
                .toUri();
        System.out.println(uri.toString());

        // 보낼 데이터
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.postForEntity(uri, req, UserResponse.class);

        System.out.println(response.getStatusCode());
        System.out.println(response.getBody());
        System.out.println(response.getHeaders());

        return response.getBody();
    }


    public UserResponse exchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // pathVariable 와 순서대로 매핑된다.
                .toUri();
        System.out.println(uri.toString());

        // 보낼 데이터
        UserRequest req = new UserRequest();
        req.setName("steve");
        req.setAge(10);

        // add header
        RequestEntity<UserRequest> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<UserResponse> response = restTemplate.exchange(requestEntity, UserResponse.class);
        return response.getBody();
    }

    // body 가 유동적일 경우 제네릭을 사용해 재사용성을 높이는 방법
    public Req<UserResponse> genericExchange() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9090")
                .path("/api/server/user/{userId}/name/{userName}")
                .encode()
                .build()
                .expand(100, "steve") // pathVariable 와 순서대로 매핑된다.
                .toUri();
        System.out.println(uri.toString());

        // 보낼 데이터
        UserRequest userRequest = new UserRequest();
        userRequest.setName("steve");
        userRequest.setAge(10);

        Req<UserRequest> req = new Req<>();
        req.setHeader(
                new Req.Header()
        );

        // body 에 들어갈 클래스만 바꿔주면 된다.
        req.setBody(
                userRequest
        );

        // add header
        RequestEntity<Req<UserRequest>> requestEntity = RequestEntity
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-authorization", "abcd")
                .header("custom-header", "ffffff")
                .body(req);

        RestTemplate restTemplate = new RestTemplate();

        // 제네릭 클래스는 .class 를 붙일 수 없다.
        // 따라서 ParameterizedTypeReference 를 사용한 것
        ResponseEntity<Req<UserResponse>> response
                = restTemplate.exchange(requestEntity, new ParameterizedTypeReference<>(){});

        return response.getBody();
    }
}
