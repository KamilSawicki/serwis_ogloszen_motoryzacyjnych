package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    public User findSingleById(int id);

    public List<User> findAll();

    public User findSingleByUsername(String username);

    public User findSingleByEmail(String email);

    public User findSingleByToken(String token);

    public List<User> findByAdmin(boolean admin);

}
