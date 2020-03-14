package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Session {
    int counter;

    public Session(){
        counter=0;
    }

    public int getCounter() {
        return counter;
    }

    public void inc(){
        counter++;
    }
}
