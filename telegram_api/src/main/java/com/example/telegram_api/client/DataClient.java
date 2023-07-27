package com.example.telegram_api.client;

import com.example.telegram_api.models.LoginRequest;
import com.example.telegram_api.models.UsernameModel;
import com.example.telegram_api.models.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "database-service", url = "${application.config.database-url}")
public interface DataClient {
    @PostMapping("/signUp")
    void signUp(@RequestBody Users user);

    @PostMapping("/login")
    Boolean login(@RequestBody LoginRequest request);

    @PostMapping("/activate/{code}")
    void activate(@PathVariable String code, @RequestBody UsernameModel username);
}
