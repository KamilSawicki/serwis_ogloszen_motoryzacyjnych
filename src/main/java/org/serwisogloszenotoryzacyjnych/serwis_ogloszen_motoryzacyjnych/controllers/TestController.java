package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.springframework.web.bind.annotation.*;
import net.minidev.json.*;

@RestController
@CrossOrigin
@RequestMapping("/test")
public class TestController extends Controller {
    @GetMapping
    public JSONObject index(@RequestHeader("api_token") String apiToken){
        JSONObject response = new JSONObject();

        if(auth(apiToken)){
            response.put("status", true);
            response.put("user", this.user);
        }

        return response;
    }
}
