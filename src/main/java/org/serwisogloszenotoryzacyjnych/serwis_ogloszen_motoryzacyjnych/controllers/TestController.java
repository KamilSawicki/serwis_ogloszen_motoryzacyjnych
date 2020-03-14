package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.controllers;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController extends Controller {

    @GetMapping("/")
    public int test(){
        return session.getCounter();
    }

    @GetMapping("/inc")
    public void inc(){
        session.inc();
    }
}
