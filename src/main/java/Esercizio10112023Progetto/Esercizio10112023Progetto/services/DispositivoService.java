package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.*;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.NotFound;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.Unauthorized;
import Esercizio10112023Progetto.Esercizio10112023Progetto.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
    @Autowired
    private UserService userService;

    public Page<Dispositivo> getAllDispositivi(int page, int size, String order){
        Pageable p= PageRequest.of(page,size, Sort.by(order));
        return dispositivoRepository.findAll(p);

    }

    public Dispositivo getSingleDispositivo(int id){
        return dispositivoRepository.findById(id).orElseThrow(()->new NotFound(("Elemento non trovato")));
    }

    public Dispositivo createDispositivo(DispositivoPayload d){
        Dispositivo dispositivo=Dispositivo.builder().tipo(d.tipo()).stato(Stato.DISPONIBILE).build();
        return dispositivoRepository.save(dispositivo);
    }

    public Dispositivo setUserDispositivo(SetUserDispositivoPayload d, int id){

        Dispositivo dispositivo=this.getSingleDispositivo(id);
        User u=userService.getSingleUser(d.user_id());
        if(dispositivo.getStato()== Stato.DISPONIBILE&& d.stato()== Stato.ASSEGNATO){
            dispositivo.setStato(d.stato());
            dispositivo.setUser(u);
            dispositivoRepository.save(dispositivo);
            return dispositivo;
        }
        else throw new Unauthorized("Il dispositivo non è disponibile");


    }

    public void deleteDispositivo(int id){
        Dispositivo d=this.getSingleDispositivo(id);
        dispositivoRepository.delete(d);
    }


    public Dispositivo setStato(int id,SetStatoDispositivoPayload body){
        Dispositivo d=this.getSingleDispositivo(id);
        switch (body.stato()){
            case ASSEGNATO -> {
                throw new Unauthorized("EndPoint sbagliato");
            }
            case DISMESSO -> {
                d.setUser(null);
                d.setStato(Stato.DISMESSO);
                dispositivoRepository.save(d);
                return d;
            }
            case DISPONIBILE -> {
                d.setUser(null);
                d.setStato(Stato.DISPONIBILE);
                dispositivoRepository.save(d);
                return d;
            }
            case MANUTENZIONE -> {
                d.setUser(null);
                d.setStato(Stato.MANUTENZIONE);
                dispositivoRepository.save(d);
                return d;
            }
        }
        throw new BadRequest("Stato scelto inesistente");
    }
}
