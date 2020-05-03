package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.util.List;
import java.util.regex.Pattern;
import net.minidev.json.JSONObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Adres;
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
@RequestMapping("/adres")
public class AdresController extends Controller {

    @GetMapping
    public List<Adres> index() {
        return adresService.get();
    }

    @PostMapping("/addadres")
    public JSONObject add(@RequestBody JSONObject json) {
        JSONObject result = new JSONObject();

        boolean correct = true;
        Adres adres = adresService.get(json.get("id").toString());

        if (adres == null) {

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", adres.getMiasto())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa miasta");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", adres.getUlica())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa ulicy");
                correct = false;
            }

            if (!Pattern.matches("^[\\d]{2}-[\\d]{3}+$", adres.getKodPocztowy())) {
                result.put("status", false);
                result.put("message", "Niepoprawny kod pocztowy");
                correct = false;
            }

            if (!Pattern.matches("^[\\d]{3}+$", adres.getNrBudynku())) {
                result.put("status", false);
                result.put("message", "Niepoprawne numer budynku");
                correct = false;
            }


            if (correct) {
                adresService.store(adres);
                result.put("status", true);
                result.put("message", "Dodano nowy adres");
            }
        }
        return result;

    }

    @GetMapping("/{id}")
    public JSONObject read(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Adres adres = adresService.get(id);
        result.put("status", false);
        result.put("message", "Adres: " + adres);

        return result;
    }

   /* @GetMapping("/{id}/miasto")
    public JSONObject readMiasto(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Adres adres = adresService.get(userService.get().;
        result.put("status", false);
        result.put("message", "Adres: " + adres);

        return result;
    }*/

    @PutMapping("/{id}")
    public JSONObject update(@RequestBody JSONObject json, @PathVariable int id) {
        JSONObject result = new JSONObject();

        boolean correct = true;
        Adres adres = adresService.get(id);

        if (adres == null) {

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", adres.getMiasto())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa miasta");
                correct = false;
            }

            if (!Pattern.matches("^[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+\\x20?\\-?[a-zA-ZąćęłńóśźżĄĘŁŃÓŚŹŻ]+$", adres.getUlica())) {
                result.put("status", false);
                result.put("message", "Niepoprawna nazwa ulicy");
                correct = false;
            }

            if (!Pattern.matches("^[\\d]{2}-[\\d]{3}+$", adres.getKodPocztowy())) {
                result.put("status", false);
                result.put("message", "Niepoprawny kod pocztowy");
                correct = false;
            }

            if (!Pattern.matches("^[\\d]{3}+$", adres.getNrBudynku())) {
                result.put("status", false);
                result.put("message", "Niepoprawne numer budynku");
                correct = false;
            }


            if (correct) {
                adresService.store(adres);
                result.put("status", true);
                result.put("message", "Dodano nowy adres");
            }
        }
        return result;

    }

    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Adres adres = adresService.get(id);
        adresService.remove(adres);

        result.put("status", false);
        result.put("message", "Usunięto adres");

        return result;
    }


}