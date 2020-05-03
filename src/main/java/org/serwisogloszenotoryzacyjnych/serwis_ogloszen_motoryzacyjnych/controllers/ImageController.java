package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import java.util.List;
import java.util.regex.Pattern;
import net.minidev.json.JSONObject;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Image;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController extends Controller {
    
    @GetMapping
    public List<Image> index() {
        return imageService.get();
    }
    
    @PostMapping("/addimage")
    public JSONObject addImage(@RequestBody JSONObject json) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        Image image = imageService.get(json.get("url").toString());

        if (image == null) {

            if (!Pattern.matches("[0-9a-zA-Z.-:/?+!@#$%&*]{10,}", image.getUrl())) {
                result.put("status", false);
                result.put("message", "Niepoprawny adres");
                correct = false;
            }

            if (image.getIdAd()<=0) {
                result.put("status", false);
                result.put("message", "Niepoprawne ID ogłoszenia");
                correct = false;
            }
            
            if (correct) {
            imageService.store(image);
            result.put("status", true);
            result.put("message", "Dodano obraz");
            }
        }
        return result;
    }
    
    @GetMapping("/{id}")
    public JSONObject read(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Image image = imageService.get(id);
        result.put("status", false);
        result.put("message", "Obraz: " + image);

        return result;
    }
    
    @PutMapping("/{id}")
    public JSONObject update(@RequestBody JSONObject json, @PathVariable int id) {
        JSONObject result = new JSONObject();
        boolean correct = true;
        Image image = imageService.get(id);

        if (image == null) {

            if (!Pattern.matches("[0-9a-zA-Z.-:/?+!@#$%&*]{10,}", image.getUrl())) {
                result.put("status", false);
                result.put("message", "Niepoprawny adres");
                correct = false;
            }

            if (image.getIdAd()<=0) {
                result.put("status", false);
                result.put("message", "Niepoprawne ID ogłoszenia");
                correct = false;
            }
            
            if (correct) {
            imageService.update(image);
            result.put("status", true);
            result.put("message", "Zaktualizowano obraz");
            }
        }
        return result;
    }
    
    @DeleteMapping("/{id}")
        public JSONObject delete(@PathVariable int id) {
        JSONObject result = new JSONObject();
        Image image = imageService.get(id);
        imageService.remove(image);

        result.put("status", false);
        result.put("message", "Usunięto obraz");

        return result;
    }
}
