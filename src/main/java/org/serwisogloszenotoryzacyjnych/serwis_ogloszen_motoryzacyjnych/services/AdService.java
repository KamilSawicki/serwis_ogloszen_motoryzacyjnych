package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdService {

    @Autowired
    AdRepository adRepository;

    public Ad get(int id){
        return adRepository.findSingleById(id);
    }

    public Ad get(String marka){
        return adRepository.findSingleByMarka(marka);
    }

    public List<Ad> get(){
        return adRepository.findAll();
    }

    public boolean store(Ad ad){
        ad.setId(null);
        if(ad.getMarka()!=null
                && ad.getModell()!=null
                && adRepository.findSingleByMarka(ad.getMarka())==null
                && adRepository.findSingleByModell(ad.getModell())==null
        ){
            adRepository.save(ad);
            return true;
        }
        return false;
    }

    public boolean update(Ad ad){
        if(ad.getId() != null) {
            if (ad.getMarka() != null
                    && ad.getModell() != null
                    && adRepository.findSingleByMarka(ad.getMarka()) == null
                    && adRepository.findSingleByModell(ad.getModell()) == null
            ) {
                adRepository.save(ad);
                return true;
            }
        }
            return false;
    }

    public boolean remove(Ad ad){
            if(ad.getId() != null){

            /*Insert all of dependencies*/

            adRepository.delete(ad);
            return true;
        }
        return false;
    }
}
