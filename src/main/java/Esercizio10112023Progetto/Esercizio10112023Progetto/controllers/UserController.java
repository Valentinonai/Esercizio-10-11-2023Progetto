package Esercizio10112023Progetto.Esercizio10112023Progetto.controllers;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.UserPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping()
    public Page<User> getAllUsers(@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "5")int size, @RequestParam(defaultValue = "id") String order){
        return userService.getAllUsers(page,size>20?5:size,order);
    }
    @GetMapping("/{id}")
    public User getSingleUser(@PathVariable int id){
        return userService.getSingleUser(id);
    }
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(UserPayload userPayload){
        return userService.createUser(userPayload);
    }
    @PutMapping("/{id}")
    public User modifyUser(UserPayload userPayload,@PathVariable int id){
        return userService.modifyUser(userPayload,id);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id)
    {
        userService.deleteUser(id);
    }
}
