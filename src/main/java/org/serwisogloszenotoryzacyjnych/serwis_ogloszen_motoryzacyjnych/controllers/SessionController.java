package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import net.minidev.json.*;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.util.Base64;

@RestController
@CrossOrigin
public class SessionController extends Controller {

    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject json) {
        JSONObject response = new JSONObject();

        User user = userService.get(json.get("username").toString());

        if (user == null) {
            response.put("status", false);
            response.put("message", "Brak użytkownika o podanej nazwie");

            return response;
        }

        if (user.getPassword().equals(json.get("password").toString())) {
            final SecureRandom secureRandom = new SecureRandom();
            final Base64.Encoder base64Encoder = Base64.getUrlEncoder();
            byte[] randomBytes = new byte[64];
            secureRandom.nextBytes(randomBytes);
            user.setToken(base64Encoder.encodeToString(randomBytes));
            userService.update(user);

            response.put("api_token", user.getToken());
            response.put("user_id", user.getId());
            response.put("user_admin", user.isAdmin());
            response.put("status", true);
        } else {
            response.put("status", false);
            response.put("message", "Niepoprawny login lub hasło");
        }
        return response;
    }

    @GetMapping("/session")
    public JSONObject status(@RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();

        if(auth(apiToken)){
            result.put("username", user.getUsername());
            result.put("admin", user.isAdmin());
            result.put("status", true);
        }
        else {
            result.put("status", false);
        }
        return result;
    }

    @GetMapping("/logout")
    public JSONObject logout(@RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();

        if(auth(apiToken)){
            user.setToken("");
            userService.update(user);

            result.put("status", true);
        }else {
            result.put("status", false);
        }

        return result;
    }

}
