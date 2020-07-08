package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Image;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class AdService {

    @Autowired
    AdRepository adRepository;

    @Autowired
    ImageService imageService;

    public Ad get(int id) {
        return adRepository.findSingleById(id);
    }

    public Ad get(String marka) {
        return adRepository.findSingleByMarka(marka);
    }

    public List<Ad> get() {
        List<Ad> ads = adRepository.findAll();
        Collections.reverse(ads);
        return ads;
    }

    public List<Ad> getAccepted() {
        List<Ad> ads = adRepository.findByAccepted(true);
        Collections.reverse(ads);
        return ads;
    }

    public List<Ad> getAccepted(String brand,
                                String model,
                                int mileageSince,
                                int mileageTo,
                                int priceSince,
                                int priceTo,
                                int yearbookSince,
                                int yearbookTo,
                                String fuel,
                                String status) {
        List<Ad> ads = adRepository.getFiltered(
                brand!=null?brand:"%",
                model!=null?model:"%",
                fuel!=null?fuel:"%",
                mileageSince,
                mileageTo!=0?mileageTo:2147483647,
                priceSince,
                priceTo!=0?priceTo:2147483647,
                yearbookSince,
                yearbookTo!=0?yearbookTo:2147483647,
                status.equals("all")?"%":status);
        System.out.println("-->"+status);

        Collections.reverse(ads);
        return ads;
    }

    public List<Ad> getNotAccepted() {
        List<Ad> ads = adRepository.findByAccepted(false);
        Collections.reverse(ads);
        return ads;
    }

    public List<Ad> newest4() {
        return adRepository.findTop4ByAcceptedOrderByIdDesc(true);
    }

    public Ad store(Ad ad) {
        return adRepository.save(ad);
    }

    public boolean update(Ad ad) {
        if (ad.getId() != null) {
            if (ad.getMarka() != null
                    && ad.getModel() != null
            ) {
                adRepository.save(ad);
                return true;
            }
        }
        return false;
    }

    public boolean remove(Ad ad) {
        if (ad.getId() != null) {

            List<Image> images = imageService.get(ad);

            for (Image image : images) {
                imageService.remove(image);
            }

            adRepository.delete(ad);
            return true;
        }
        return false;
    }

    public List<String> getBrands(){
        return adRepository.findMarka();
    }

    public List<String> getModels(String brand){
        return adRepository.findModel(brand);
    }
}
