package Esercizio10112023Progetto.Esercizio10112023Progetto.repositories;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
