package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Image{

    @Id
    @GeneratedValue
    public Integer id;
    
    public String url;
    public Integer idAd;
    
    public Image() {
        url=null;
        idAd=null;
        id=null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getIdAd() {
        return idAd;
    }

    public void setIdAd(Integer idAd) {
        this.idAd = idAd;
    }
    
}
