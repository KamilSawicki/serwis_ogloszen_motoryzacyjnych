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
<<<<<<< HEAD

    public Adres findByKodPocztowy(String kodPocztowy);

    public Adres findByNrBudynku(String nrBudynku);


=======
    
    public Adres findByKodPocztowy(String kodPocztowy);
    
    public Adres findByNrBudynku(String nrBudynku);
    
>>>>>>> 20bfb839680b14d693053b739e9ed1e9a3c8fb81
}
