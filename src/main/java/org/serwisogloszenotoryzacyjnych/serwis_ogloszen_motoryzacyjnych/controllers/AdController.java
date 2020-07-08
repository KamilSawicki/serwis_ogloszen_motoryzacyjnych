package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.minidev.json.*;
import netscape.javascript.JSObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Image;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ad")
@CrossOrigin("*")
public class AdController extends Controller {

    @PostMapping
    public JSONObject index(@RequestHeader("api_token") String apiToken, @RequestBody JSONObject request) {

        String brand;
        String model;
        int mileageSince;
        int mileageTo;
        int priceSince;
        int priceTo;
        int yearbookSince;
        int yearbookTo;
        String fuel;
        String status;

        try {
            brand = request.get("brand").toString();
        } catch (Exception e) {
            brand = null;
        }
        try {
            model = request.get("model").toString();
        } catch (Exception e) {
            model = null;
        }
        try {
            mileageSince = Integer.parseInt(request.get("mileage_since").toString());
        } catch (Exception e) {
            mileageSince = 0;
        }
        try {
            mileageTo = Integer.parseInt(request.get("mileage_to").toString());
        } catch (Exception e) {
            mileageTo = 0;
        }
        try {
            priceSince = Integer.parseInt(request.get("price_since").toString());
        } catch (Exception e) {
            priceSince = 0;
        }
        try {
            priceTo = Integer.parseInt(request.get("price_to").toString());
        } catch (Exception e) {
            priceTo = 0;
        }
        try {
            yearbookSince = Integer.parseInt(request.get("yearbook_since").toString());
        } catch (Exception e) {
            yearbookSince = 0;
        }
        try {
            yearbookTo = Integer.parseInt(request.get("yearbook_to").toString());
        } catch (Exception e) {
            yearbookTo = 0;
        }
        try {
            fuel = request.get("fuel").toString();
        } catch (Exception e) {
            fuel = null;
        }
        try {
            status = request.get("status").toString();
        } catch (Exception e) {
            status = "all";
        }

        JSONObject response = new JSONObject();
        List<JSONObject> offers = new ArrayList<>();
        List<Ad> ads = adService.getAccepted(brand,
                model,
                mileageSince,
                mileageTo,
                priceSince,
                priceTo,
                yearbookSince,
                yearbookTo,
                fuel,
                status);

        for (int i = 0; i < ads.size(); i++) {
            JSONObject ad = new JSONObject();
            ad.put("id", ads.get(i).getId());
            ad.put("title", ads.get(i).getMarka() + " " + ads.get(i).getModel());
            ad.put("capacity", ads.get(i).getPojemnosc_silnika());
            ad.put("fuel", ads.get(i).getPaliwo());
            ad.put("mileage", ads.get(i).getPrzebieg());
            ad.put("price", ads.get(i).getCena());
            ad.put("accepted", ads.get(i).getAccepted());
            if (imageService.get(ads.get(i)).size() > 0)
                ad.put("image", imageService.get(ads.get(i)).get(0).getUrl());
            else
                ad.put("image", null);
            offers.add(ad);
        }
        response.put("offers", offers);
        return response;
    }

    @GetMapping("/models")
    public JSONObject models(){
        JSONObject response = new JSONObject();
        List<JSONObject> result=new ArrayList<>();

        List<String> brands = adService.getBrands();

        for(String brand : brands){
            JSONObject item=new JSONObject();
            item.put("name", brand);
            item.put("models", adService.getModels(brand));
            result.add(item);
        }

        response.put("models", result);

        return response;
    }

    @GetMapping("/waiting-offers")
    public JSONObject waitingIndex(@RequestHeader("api_token") String apiToken) {
        JSONObject response = new JSONObject();
        List<JSONObject> offers = new ArrayList<>();
        List<Ad> ads = adService.getNotAccepted();
        for (int i = 0; i < ads.size(); i++) {
            JSONObject ad = new JSONObject();
            ad.put("id", ads.get(i).getId());
            ad.put("title", ads.get(i).getMarka() + " " + ads.get(i).getModel());
            ad.put("capacity", ads.get(i).getPojemnosc_silnika());
            ad.put("fuel", ads.get(i).getPaliwo());
            ad.put("mileage", ads.get(i).getPrzebieg());
            ad.put("price", ads.get(i).getCena());
            ad.put("accepted", ads.get(i).getAccepted());
            if (imageService.get(ads.get(i)).size() > 0)
                ad.put("image", imageService.get(ads.get(i)).get(0).getUrl());
            else
                ad.put("image", null);
            offers.add(ad);
        }
        response.put("offers", offers);
        return response;
    }

    @PutMapping("/accept/{id}")
    public JSONObject accept(@PathVariable int id, @RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();

        if (auth(apiToken)) {
            Ad ad = adService.get(id);
            if (user.isAdmin()) {
                if (ad != null) {
                    ad.setAccepted(true);
                    if (adService.update(ad)) {
                        result.put("status", true);
                        return result;
                    }
                }
            }
        }

        result.put("status", false);

        return result;
    }

    @GetMapping("/newest")
    public JSONObject newest() {
        List<Ad> newest = adService.newest4();
        List<JSONObject> newestOffers = new ArrayList<>();
        JSONObject result = new JSONObject();

        for (Ad ad : newest) {
            JSONObject adJson = new JSONObject();
            adJson.put("yearbook", ad.getRok_produkcji());
            adJson.put("price", ad.getCena());
            adJson.put("mileage", ad.getPrzebieg());
            adJson.put("id", ad.getId());
            if (imageService.get(ad).size() > 0)
                adJson.put("image", imageService.get(ad).get(0).getUrl());
            else
                adJson.put("image", null);
            newestOffers.add(adJson);
        }

        result.put("newest", newestOffers);

        return result;
    }

    @PostMapping("/store")
    public JSONObject store(@RequestBody JSONObject json, @RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        boolean authStatus = auth(apiToken);

        if (!authStatus) {
            result.put("status", false);
            result.put("message", "Musisz się zalogować");
            correct = false;
        }

        try {
            if (!Pattern.matches("[a-zA-z -]{2,}", json.get("brand").toString())) {
                correct = false;
            }


            if (!Pattern.matches("[a-zA-z0-9 -]{2,}", json.get("model").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny model");
                correct = false;
            }

            if (!Pattern.matches("[0-9a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ., -]{10,}", json.get("description").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny opis");
                correct = false;
            }

            try {
                if (Integer.parseInt(json.get("price").toString()) < 0) {
                    result.put("status", false);
                    result.put("message", "Niepoprawna cena");
                    correct = false;
                }
            } catch (NumberFormatException e) {
                result.put("status", false);
                result.put("message", "Niepoprawna cena");
                correct = false;
            }

            try {
                if (Integer.parseInt(json.get("yearbook").toString()) < 1900) {
                    result.put("status", false);
                    result.put("message", "Niepoprawny rok produkcji");
                    correct = false;
                }
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
                Date date = new Date();
                if (Integer.parseInt(json.get("yearbook").toString()) > Integer.parseInt(formatter.format(date))) {
                    result.put("status", false);
                    result.put("message", "Niepoprawny rok produkcji");
                    correct = false;
                }
            } catch (NumberFormatException e) {
                result.put("status", false);
                result.put("message", "Niepoprawny rok produkcji");
                correct = false;
            }

            if (!Pattern.matches("(on|pb|lpg)", json.get("fuel").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawne paliwo");
                correct = false;
            }

            if (!Pattern.matches("[a-zA-z]{3,}", json.get("body_type").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny rodzaj nadwozia");
                correct = false;
            }

            if (!Pattern.matches("[0-9]{3,}", json.get("engine_capacity").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawna pojemność");
                correct = false;
            }

            if (!Pattern.matches("[0-9]{2,}", json.get("power").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawna moc silnika");
                correct = false;
            }

            if (!Pattern.matches("[0-9]+", json.get("mileage").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny przebieg");
                correct = false;
            }

            if (!Pattern.matches("(new|used)", json.get("type").toString())) {
                result.put("status", false);
                result.put("message", "Niepoprawny stan pojazdu");
                correct = false;
            }
        } catch (NullPointerException e) {
            result.put("status", false);
            result.put("message", "Muszisz wprowadzić wszystkie wartości");
            correct = false;
        }

        if (correct) {

            Ad ad = new Ad(json);
            ad.setId_uzytkownika(user.getId());

            ad = adService.store(ad);

            ArrayList images = (ArrayList) json.get("images");

            for (int i = 0; i < images.size(); i++) {
                Image img = new Image();
                img.setIdAd(ad.getId());
                img.setUrl(images.get(i).toString());
                imageService.store(img);
            }

            result.put("status", true);
            result.put("message", "Utworzono ogłoszenie");
            result.put("offer_id", ad.getId());

        }
        return result;
    }

    @GetMapping("/{id}")
    public JSONObject read(@PathVariable int id) {
        JSONObject result = new JSONObject();
        JSONObject offer = new JSONObject();
        Ad ad = adService.get(id);

        offer.put("brand", ad.getMarka());
        offer.put("model", ad.getModel());
        offer.put("description", ad.getOpis());
        offer.put("yearbook", ad.getRok_produkcji());
        offer.put("fuel", ad.getPaliwo());
        offer.put("body", ad.getRodzaj_nadwozia());
        offer.put("capacity", ad.getPojemnosc_silnika());
        offer.put("mileage", ad.getPrzebieg());
        offer.put("status", ad.getStatus());
        offer.put("price", ad.getCena());
        offer.put("accepted", ad.getAccepted());

        JSONObject user = new JSONObject();
        User offerUser = userService.get(ad.getId_uzytkownika());
        user.put("name", offerUser.getFirstName());
        user.put("city", offerUser.getCity());
        user.put("number", offerUser.getPhone());
        user.put("userId", offerUser.getId());
        offer.put("user", user);

        List<String> imagesUrl = new ArrayList<>();
        for (Image img : imageService.get(ad)) {
            imagesUrl.add(img.getUrl());
        }
        offer.put("images", imagesUrl);

        result.put("offer", offer);

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

        if (!Pattern.matches("[a-zA-z]{3,}", ad.getModel())) {
            result.put("status", false);
            result.put("message", "Niepoprawny model");
            correct = false;
        }

        if (!Pattern.matches("[0-9a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ. -]{10,}", ad.getOpis())) {
            result.put("status", false);
            result.put("message", "Niepoprawny opis");
            correct = false;
        }

        if (ad.getCena() < 0) {
            result.put("status", false);
            result.put("message", "Niepoprawna cena");
            correct = false;
        }

        if (ad.getRok_produkcji() < 0 && ad.getRok_produkcji() > 2020) {
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

        if (ad.getPojemnosc_silnika() < 0 && ad.getPojemnosc_silnika() > 10000) {
            result.put("status", false);
            result.put("message", "Niepoprawna pojemność");
            correct = false;
        }

        if (ad.getMoc_silnika() < 0 && ad.getMoc_silnika() > 2000) {
            result.put("status", false);
            result.put("message", "Niepoprawna moc silnika");
            correct = false;
        }

        if (ad.getPrzebieg() < 0 && ad.getPrzebieg() > 2000000) {
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
        return result;
    }


    @DeleteMapping("/{id}")
    public JSONObject delete(@PathVariable int id, @RequestHeader("api_token") String apiToken) {
        JSONObject result = new JSONObject();
        Ad ad = adService.get(id);

        if (auth(apiToken)) {
            if (user.getId() == ad.getId_uzytkownika() || user.isAdmin()) {
                adService.remove(ad);
                result.put("message", "Usunięto ogłoszenie");
                result.put("status", true);
                return result;
            }
        }

        result.put("message", "Brak uprawinień");
        result.put("status", false);
        return result;
    }

}
