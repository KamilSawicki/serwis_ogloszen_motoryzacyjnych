package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import net.minidev.json.*;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController extends Controller {
    @PostMapping("/login")
    public JSONObject login(@RequestBody JSONObject json) {
        JSONObject response =new JSONObject();

        User user = userService.get(json.get("username").toString());

        if(user==null){
            response.put("status", false);
            response.put("message", "Brak użytkownika o podanej nazwie");

            return response;
        }

        System.out.println("|"+user.getPassword()+"| |"+json.get("password").toString()+"|");

        if(user.getPassword().equals(json.get("password").toString())){
            session.setUser(user);

            response.put("status", true);
        }
        else{
            response.put("status", false);
            response.put("message", "Niepoprawny login lub hasło");
        }
        return response;
    }

    @GetMapping("session_status")
    public JSONObject status(){
        JSONObject result=new JSONObject();

        result.put("status", session.getStatus());
        result.put("username", session.getUser().getUsername());
        result.put("id", session.getUser().getId());

        return result;
    }

    @GetMapping("/logout")
    public JSONObject logout(){
        session.reset();

        JSONObject result = new JSONObject();
        result.put("status", true);

        return result;
    }

}
