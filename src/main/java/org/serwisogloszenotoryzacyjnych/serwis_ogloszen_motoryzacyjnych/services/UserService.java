package org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.services;

import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.models.User;
import org.serwisogloszenotoryzacyjnych.serwis_ogloszen_motoryzacyjnych.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User get(int id){
        return userRepository.findSingleById(id);
    }

    public User get(String username){
        return userRepository.findSingleByUsername(username);
    }

    public List<User> get(){
        return userRepository.findAll();
    }

    public boolean store(User user){
        user.setId(null);
        if(user.getEmail()!=null
                && user.getUsername()!=null
                && user.getPassword()!=null
                && userRepository.findSingleByUsername(user.getUsername())==null
                && userRepository.findSingleByEmail(user.getEmail())==null
        ){
            userRepository.save(user);
            return true;
        }
        return false;
    }

    public boolean update(User user){
        if(user.getId() != null){
            if(user.getEmail()!=null
                    && user.getUsername()!=null
                    && user.getPassword()!=null
                    && userRepository.findSingleByUsername(user.getUsername())==null
                    && userRepository.findSingleByEmail(user.getEmail())==null
            ){
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    public boolean remove(User user){
        if(user.getId()!=null){

            /*Insert all of dependencies*/

            userRepository.delete(user);
            return true;
        }
        return false;
    }
}
