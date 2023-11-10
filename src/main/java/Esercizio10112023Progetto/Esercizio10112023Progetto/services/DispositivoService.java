package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Dispositivo;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.DispositivoPayload;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.DispositivoPayloadModifica;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Stato;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.NotFound;
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

//    public Dispositivo modifyDispositivo(DispositivoPayloadModifica d,int id){
//
//        Dispositivo dispositivo=this.getSingleDispositivo(id);
//        dispositivo.setTipo(d.tipo());
//        dispositivo.setStato(d.stato());
//        dispositivoRepository.save(dispositivo);
//        return dispositivo;
//    }

    public void deleteDispositivo(int id){
        Dispositivo d=this.getSingleDispositivo(id);
        dispositivoRepository.delete(d);
    }
}
