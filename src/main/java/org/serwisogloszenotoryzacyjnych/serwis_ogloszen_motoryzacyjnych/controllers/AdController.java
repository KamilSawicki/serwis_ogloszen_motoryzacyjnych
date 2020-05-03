package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.util.List;
import java.util.regex.Pattern;
import net.minidev.json.JSONObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ad")
public class AdController extends Controller {

    @GetMapping
    public List<Ad> index() {
        return adService.get();
    }

    @PostMapping("/ad")
    public JSONObject register(@RequestBody JSONObject json) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        Ad ad = adService.get(json.get("marka").toString());

        if (ad == null) {

            if (!Pattern.matches("[a-zA-z]{3,}", ad.getMarka())) {
                result.put("status", false);
                result.put("message", "Niepoprawna marka");
                correct = false;
            }

            if (!Pattern.matches("[a-zA-z]{3,}", ad.getModell())) {
                result.put("status", false);
                result.put("message", "Niepoprawny model");
                correct = false;
            }

            if (!Pattern.matches("[0-9a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ. -]{10,}", ad.getOpis())) {
                result.put("status", false);
                result.put("message", "Niepoprawny opis");
                correct = false;
            }

            if (ad.getCena()<0) {
                result.put("status", false);
                result.put("message", "Niepoprawna cena");
                correct = false;
            }

            if (ad.getRok_produkcji()<0 && ad.getRok_produkcji()>2020) {
                result.put("status", false);
                result.put("message", "Niepoprawny rok produkcji");
                correct = false;
            }

            if (!Pattern.matches("(Diesel|Benzyna|LPG+Benzyna|Hybryda|Elektryczny)", ad.getPaliwo())) {
                result.put("status", false);
                result.put("message", "Niepoprawne paliwo");
                correct = false;
            }

            if (!Pattern.matches("[a-zA-z]{3,}", ad.getRodzaj_nadwozia())) {
                result.put("status", false);
                result.put("message", "Niepoprawny rodzaj nadwozia");
                correct = false;
            }

            if (ad.getPojemnosc_silnika()<0) {
                result.put("status", false);
                result.put("message", "Niepoprawna pojemność");
                correct = false;
            }

            if (ad.getMoc_silnika()<0 && ad.getMoc_silnika()>2000) {
                result.put("status", false);
                result.put("message", "Niepoprawna moc silnika");
                correct = false;
            }

            if (ad.getPrzebieg()<0 && ad.getPrzebieg()>2000000) {
                result.put("status", false);
                result.put("message", "Niepoprawna przebieg");
                correct = false;
            }

            if (!Pattern.matches("(Nowy|Używany)", ad.getStatus())) {
                result.put("status", false);
                result.put("message", "Niepoprawny stan pojazdu");
                correct = false;
            }



            if (correct) {
                adService.store(ad);
                result.put("status", true);
                result.put("message", "Utworzono ogłoszenie");
            }
        }
        return result;
    }

    @GetMapping("/{id}")
    public JSONObject read(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Ad ad = adService.get(id);
        result.put("status", false);
        result.put("message", "Ogłoszenie: " + ad);
        return result;
    }

    @PutMapping("/{id}")
    public JSONObject update(@RequestBody JSONObject json, @PathVariable int id) {
        JSONObject result = new JSONObject();
        Ad ad = adService.get(id);
        boolean correct = true;


        if (!Pattern.matches("[a-zA-z]{3,}", ad.getMarka())) {
            result.put("status", false);
            result.put("message", "Niepoprawna marka");
            correct = false;
        }

        if (!Pattern.matches("[a-zA-z]{3,}", ad.getModell())) {
            result.put("status", false);
            result.put("message", "Niepoprawny model");
            correct = false;
        }

        if (!Pattern.matches("[0-9a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ. -]{10,}", ad.getOpis())) {
            result.put("status", false);
            result.put("message", "Niepoprawny opis");
            correct = false;
        }

        if (ad.getCena()<0) {
            result.put("status", false);
            result.put("message", "Niepoprawna cena");
            correct = false;
        }

        if (ad.getRok_produkcji()<0 && ad.getRok_produkcji()>2020) {
            result.put("status", false);
            result.put("message", "Niepoprawny rok produkcji");
            correct = false;
        }

        if (!Pattern.matches("(Diesel|Benzyna|LPG+Benzyna|Hybryda|Elektryczny)", ad.getPaliwo())) {
            result.put("status", false);
            result.put("message", "Niepoprawne paliwo");
            correct = false;
        }

        if (!Pattern.matches("[a-zA-z]{3,}", ad.getRodzaj_nadwozia())) {
            result.put("status", false);
            result.put("message", "Niepoprawny rodzaj nadwozia");
            correct = false;
        }

        if (ad.getPojemnosc_silnika()<0 && ad.getPojemnosc_silnika()>10000) {
            result.put("status", false);
            result.put("message", "Niepoprawna pojemność");
            correct = false;
        }

        if (ad.getMoc_silnika()<0 && ad.getMoc_silnika()>2000) {
            result.put("status", false);
            result.put("message", "Niepoprawna moc silnika");
            correct = false;
        }

        if (ad.getPrzebieg()<0 && ad.getPrzebieg()>2000000) {
            result.put("status", false);
            result.put("message", "Niepoprawna przebieg");
            correct = false;
        }

        if (!Pattern.matches("(Nowy|Używany)", ad.getStatus())) {
            result.put("status", false);
            result.put("message", "Niepoprawny stan pojazdu");
            correct = false;
        }


        if (correct) {
            adService.store(ad);
            result.put("status", true);
            result.put("message", "Zaaktualizowano ogłoszenie");
        }
        return result ;
    }



@DeleteMapping("/{id}")
        public JSONObject delete(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Ad ad = adService.get(id);
        adService.remove(ad);

        result.put("status", false);
        result.put("message", "Usunięto ogłoszenie");

        return result;
    }

}
