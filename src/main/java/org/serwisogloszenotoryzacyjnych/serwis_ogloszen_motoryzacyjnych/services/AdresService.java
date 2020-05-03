package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services;

import java.util.List;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Adres;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.AdresRepository;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AdresService {

    @Autowired
    AdresRepository adresRepository;

    public Adres get(int id){
        return adresRepository.findSingleById(id);
    }

    public Adres get(String miasto){
        return adresRepository.findByMiasto(miasto);
    }

    public List <Adres> get(){
        return adresRepository.findAll();
    }

    public boolean store(Adres adres){
        adres.setId(null);
        if(adres.getMiasto()!=null
                && adres.getKodPocztowy()!=null
                && adres.getUlica()!=null
                && adres.getNrBudynku()!=null
                && adresRepository.findByMiasto(adres.getMiasto())==null
                && adresRepository.findByUlica(adres.getUlica())==null
                && adresRepository.findByNrBudynku(adres.getNrBudynku())==null
        ){
            adresRepository.save(adres);
            return true;
        }
        return false;
    }


    public boolean update(Adres adres){
        adres.setId(null);
        if(adres.getMiasto()!=null
                && adres.getKodPocztowy()!=null
                && adres.getUlica()!=null
                && adres.getNrBudynku()!=null
                && adresRepository.findByMiasto(adres.getMiasto())==null
                && adresRepository.findByUlica(adres.getUlica())==null
                && adresRepository.findByNrBudynku(adres.getNrBudynku())==null
        ){
            adresRepository.save(adres);
            return true;
        }
        return false;
    }

    public boolean remove(Adres adres){
        if(adres.getId()!=null){
            adresRepository.delete(adres);
            return true;
        }
        return false;
    }


}