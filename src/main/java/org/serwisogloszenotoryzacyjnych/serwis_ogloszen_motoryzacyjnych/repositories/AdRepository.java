package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Ad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdRepository extends JpaRepository<Ad, Integer> {

    public Ad findSingleById(int id);

    public List<Ad> findAll();
    public List<Ad> findByAccepted(boolean accepted);
    public List<Ad> findTop4ByAcceptedOrderByIdDesc(boolean accepted);

    public Ad findSingleByMarka(String marka);

    public Ad findSingleByModel(String model);

    @Query("SELECT a FROM Ad a "
            +"WHERE a.marka LIKE ?1 "
            +"and a.model LIKE ?2 "
            +"and a.paliwo LIKE ?3 "
            +"and a.status LIKE ?10 "
            +"and a.przebieg BETWEEN ?4 AND ?5 "
            +"and a.cena BETWEEN ?6 AND ?7 "
            +"and a.rok_produkcji BETWEEN ?8 AND ?9 "
            +"and a.accepted=true"
    )
    public List<Ad> getFiltered(String brand, String model, String fuel, int mileageSince, int mileageTo, int priceSince, int priceTo, int yearbookSince, int yearbookTo, String status);

    @Query("SELECT a.marka from Ad a GROUP BY a.marka")
    public List<String> findMarka();

    @Query("SELECT a.model from Ad a WHERE a.marka=?1 GROUP BY a.model")
    public List<String> findModel(String marka);
}
