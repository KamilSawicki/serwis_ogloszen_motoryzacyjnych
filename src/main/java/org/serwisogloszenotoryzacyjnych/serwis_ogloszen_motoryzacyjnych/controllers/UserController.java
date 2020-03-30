package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.util.List;
import java.util.regex.Pattern;
import net.minidev.json.JSONObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends Controller {

    @GetMapping
    public List<User> index() {
        return userService.get();
    }

    @PostMapping("/register")
    public JSONObject register(@RequestBody JSONObject json) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        User user = userService.get(json.get("username").toString());

        if (user == null) {

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
                result.put("message", "Utworzono uzytkownika");
            }
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
        return result ;
    }
    


@DeleteMapping("/{id}")
        public JSONObject delete(@PathVariable int id) {
        JSONObject result = new JSONObject();
        User user = userService.get(id);
        userService.remove(user);

        result.put("status", false);
        result.put("message", "Usunięto uzytkownika");

        return result;
    }

}
