package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.UserPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.NotFound;
import Esercizio10112023Progetto.Esercizio10112023Progetto.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public Page<User> getAllUsers(int page,int size,String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return userRepository.findAll(p);
    }

    public User getSingleUser(int id){
        User u=userRepository.findById(id).orElseThrow(()->new NotFound("User non trovato"));
        return u;
    }

    public User createUser(UserPayload userPayload){
        User u=User.builder().nome(userPayload.nome()).cognome(userPayload.cognome()).email(userPayload.email()).username(userPayload.nome()+"_"+userPayload.cognome()).imageUrl("https://picsum.photos/200/300").build();
        userRepository.save(u);
        return u;
    }

    public User modifyUser(UserPayload userPayload,int id){
        User u=this.getSingleUser(id);
        u.setNome(userPayload.nome());
        u.setCognome(userPayload.cognome());
        u.setEmail(userPayload.email());
        u.setUsername(userPayload.nome()+"_"+userPayload.cognome());
        userRepository.save(u);
        return u;
    }

    public void deleteUser(int id){
        User u=this.getSingleUser(id);
        userRepository.delete(u);
    }

}
