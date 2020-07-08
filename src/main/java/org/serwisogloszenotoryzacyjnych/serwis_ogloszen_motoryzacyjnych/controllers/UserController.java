package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import net.minidev.json.JSONObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController extends Controller {

    @GetMapping
    public JSONObject index(@RequestHeader("api_token") String apiToken) {

        auth(apiToken);
        JSONObject result=new JSONObject();

        if(!user.isAdmin()){
            result.put("status", false);
            result.put("message", "Brak uprawnień");
            return result;
        }

        List<JSONObject> resultList=new ArrayList<>();

        for(User user : userService.get()){
            JSONObject item=new JSONObject();

            item.put("id", user.getId());
            item.put("username", user.getUsername());
            item.put("firstName", user.getFirstName());
            item.put("lastName", user.getLastName());
            item.put("phoneNumber", user.getPhone());
            item.put("admin", user.isAdmin());

            resultList.add(item);
        }

        result.put("users", resultList);
        return result;
    }

    @PutMapping("/set-admin/{userId}")
    public JSONObject setAdmin(@RequestHeader("api_token") String apiToken, @PathVariable("userId") int userId, @RequestBody JSONObject request){
        JSONObject result = new JSONObject();

        if(auth(apiToken)){
            userService.setAdmin(userId, Boolean.parseBoolean(request.get("privileges").toString()));
            result.put("status", true);
        }else {
            result.put("status", false);
            result.put("message", "Brak uprawnień");
        }

        return result;
    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody JSONObject json) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        User user = userService.get(json.get("username").toString());

        if (user == null) {

            if (!Pattern.matches("[a-zA-Z0-9_-]{3,15}", json.get("username").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa");
                correct = false;
            }

            if (!Pattern.matches("^.{8,}$", json.get("password").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawne haslo");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", json.get("firstname").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawne imie");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", json.get("lastname").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawne nazwisko");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", json.get("email").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny email");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-Z-]+$", json.get("city").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa miejscowości");
                correct = false;
            }

            if (!Pattern.matches("^[0-9]{9}$", json.get("phone").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny numer telefonu");
                correct = false;
            }

            if (correct) {
                User newUser = new User();
                newUser.setUsername(json.get("username").toString());
                newUser.setPassword(json.get("password").toString());
                newUser.setEmail(json.get("email").toString());
                newUser.setFirstName(json.get("firstname").toString());
                newUser.setLastName(json.get("lastname").toString());
                newUser.setCity(json.get("city").toString());
                newUser.setPhone(json.get("phone").toString());
                userService.store(newUser);
                result.put("status", true);
                result.put("message", "Utworzono uzytkownika");
            }
        }else{
            result.put("message", "Nazwa użytkownika w użyciu");
            result.put("status", false);
        }
        return result;
    }

    @GetMapping("/{id}")
    public JSONObject read(@PathVariable int id) {
        JSONObject result = new JSONObject();
        User user = userService.get(id);
        result.put("status", false);
        result.put("message", "Użytkownik: " + user);

        return result;
    }

    @PutMapping("/{id}")
    public JSONObject update(@RequestBody JSONObject json, @PathVariable int id) {
        JSONObject result = new JSONObject();
        User user = userService.get(id);
        boolean correct = true;
        if (!Pattern.matches("[a-z0-9_-]{3,15}", user.getUsername())) {
            result.put("status", false);
            result.put("message", "Niepoprawna nazwa");
            correct = false;
        }

        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$", user.getPassword())) {
            result.put("status", false);
            result.put("message", "Niepoprawne haslo");
            correct = false;
        }

        if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", user.getFirstName())) {
            result.put("status", false);
            result.put("message", "Niepoprawne imie");
            correct = false;
        }

        if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", user.getLastName())) {
            result.put("status", false);
            result.put("message", "Niepoprawne nazwisko");
            correct = false;
        }

        if (!Pattern.matches("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$", user.getEmail())) {
            result.put("status", false);
            result.put("message", "Niepoprawny email");
            correct = false;
        }

        if (correct) {
            userService.store(user);
            result.put("status", true);
            result.put("message", "Zaaktualizowano uzytkownika");
        }
        return result;
    }


    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable int id, @RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();


        result.put("status", false);
        result.put("message", "Usunięto uzytkownika");

        if(auth(apiToken)){
            User user = userService.get(id);
            if(userService.remove(user)){
                result.put("status", true);
            }
            else{
                result.put("status", false);
                result.put("message", "Nie znaleziono użytkownika o podanym ID");
            }
        }else{
            result.put("status", false);
            result.put("message", "Brak uprawnień");
        }

        return result;
    }

}
