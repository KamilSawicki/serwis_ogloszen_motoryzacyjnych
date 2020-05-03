
package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Adres {

    @Id
    @GeneratedValue
    public Integer id;

    public String miasto;
    public String kodPocztowy;
    public String ulica;
<<<<<<< HEAD
    public String nrBudynku;
=======
    public String nrBudynku;    
>>>>>>> 20bfb839680b14d693053b739e9ed1e9a3c8fb81

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMiasto() {
        return miasto;
    }

    public void setMiasto(String miasto) {
        this.miasto = miasto;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    public void setKodPocztowy(String kodPocztowy) {
        this.kodPocztowy = kodPocztowy;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getNrBudynku() {
        return nrBudynku;
    }

    public void setNrBudynku(String nrBudynku) {
        this.nrBudynku = nrBudynku;
    }
}