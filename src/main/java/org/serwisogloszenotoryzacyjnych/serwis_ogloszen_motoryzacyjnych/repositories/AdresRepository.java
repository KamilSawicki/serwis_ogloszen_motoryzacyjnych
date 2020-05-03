package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories;

import java.util.List;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.Adres;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresRepository extends JpaRepository<Adres, Integer> 
{
    public Adres findSingleById(int id);

    public List<Adres> findAll();
    
    public Adres findByUlica(String ulica);
    
    public Adres findByMiasto(String miasto);
    
    public Adres findByKod(String kod_pocztowy);
    
    public Adres findByNumer(String numer_budynku);
    
}
