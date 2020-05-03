package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.Session;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.AdresService;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    @Autowired
    public Session session;

    @Autowired
    public UserService userService;
<<<<<<< HEAD

    @Autowired
    public AdService adService;
=======
>>>>>>> 20bfb839680b14d693053b739e9ed1e9a3c8fb81
    
    @Autowired
    public AdresService adresService;
}
