package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.Session;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.UserService;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;

public class Controller {
    @Autowired
    public Session session;

    @Autowired
    public UserService userService;

    @Autowired
    public AdService adService;
}
