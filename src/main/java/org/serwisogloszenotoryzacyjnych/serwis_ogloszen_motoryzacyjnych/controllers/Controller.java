package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.AdService;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.AdresService;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.ImageService;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    public User user=new User();

    @Autowired
    public UserService userService;
    
    @Autowired
    public AdresService adresService;

    @Autowired
    public AdService adService;

    @Autowired
    public ImageService imageService;

    public boolean auth(String ApiToken){
        try{
            if (ApiToken != null) {
                user=userService.getByToken(ApiToken);
                if(user!=null){
                    return true;
                }
            }
            System.out.println("Nie zalogowany");
            return false;
        }catch(NullPointerException e){
            System.out.println("Nie podano api_token");
            return false;
        }
    }
}
