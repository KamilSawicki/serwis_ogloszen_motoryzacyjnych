package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Image;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    
    @Autowired
    ImageRepository imageRepository;
    
    public Image get(int id) {
        return imageRepository.findSingleById(id);
    }
    
    public List<Image> get(){
        return imageRepository.findAll();
    }

    public List<Image> getByAd(int idAd){
        return imageRepository.findByIdAd();
    }
    
    public boolean store(Image image){
        image.setId(null);
        if(image.getUrl()!=null
                && image.getIdAd()!=null
        ){
            imageRepository.save(image);
            return true;
        }
        return false;
    }
    
    public boolean update(Image image){
        if(image.getId() != null){
            if(image.getUrl()!=null
                    && image.getIdAd()!=null
            ){
                imageRepository.save(image);
                return true;
            }
        }
        return false;
    }
    
    public boolean remove(Image image){
        if(image.getId()!=null
                && imageRepository.findSingleById(image.getId())!=null
        ){

            /*Insert all of dependencies*/

            imageRepository.delete(image);
            return true;
        }
        return false;
    }
}
