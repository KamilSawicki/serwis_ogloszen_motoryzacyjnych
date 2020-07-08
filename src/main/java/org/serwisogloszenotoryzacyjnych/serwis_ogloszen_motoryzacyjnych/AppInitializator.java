package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
class AppInitializator {
    @Autowired
    UserService userService;
    @PostConstruct
    private void init() {
        if(userService.getAdmins().size()==0){
            User admin=new User();
            admin.setUsername("Admin");
            admin.setEmail("NoEmail");
            admin.setPassword("Admin");
            admin.setAdmin(true);
            userService.store(admin);
            System.out.println("________________>>>INIT");
        }
    }

}