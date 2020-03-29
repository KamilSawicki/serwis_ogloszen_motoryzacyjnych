package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
   
    public Image findSingleById(int id);

    public List<Image> findAll();
    
    public List<Image> findByIdAd();
}
