package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    public Ad findSingleById(int id);

    public List<Ad> findAll();

    public Ad findSingleByMarka(String marka);

    public Ad findSingleByModell(String modell);

}
