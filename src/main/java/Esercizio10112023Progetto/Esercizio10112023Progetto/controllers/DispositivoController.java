package Esercizio10112023Progetto.Esercizio10112023Progetto.controllers;

import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.Dispositivo;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.DispositivoPayloadCreazione;
import Esercizio10112023Progetto.Esercizio10112023Progetto.entities.DispositivoPayloadModifica;
import Esercizio10112023Progetto.Esercizio10112023Progetto.exceptions.BadRequest;
import Esercizio10112023Progetto.Esercizio10112023Progetto.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispositivi")
public class DispositivoController {
    @Autowired
    private DispositivoService dispositivoService

    @GetMapping()
    public Page<Dispositivo> getAllDispositivi(@RequestParam(defaultValue = "0") int page,@RequestParam(defaultValue = "5") int size,@RequestParam(defaultValue = "id") String order){
        return dispositivoService.getAllDispositivi(page,size,order);
    }

    @GetMapping("/{id}")
    public Dispositivo getSingleDispositivo(@PathVariable int id){
        return dispositivoService.getSingleDispositivo(id);

    }

    @PostMapping("")
    public Dispositivo createDispositivo(@RequestBody @Validated DispositivoPayloadCreazione dispositivo, BindingResult validation){
        if(validation.hasErrors())
            throw new BadRequest(validation.getAllErrors());
        return dispositivoService.createDispositivo(dispositivo);
    }
    @PutMapping("/{id}")
    public Dispositivo modifyDispositivo(@RequestBody @Validated DispositivoPayloadModifica dispositivo,BindingResult validation){
        if(validation.hasErrors())  throw new BadRequest(validation.getAllErrors());
        return dispositivoService.modifyDispositivo(dispositivo);
    }
    @DeleteMapping("/{id}")
    public void deleteDispositivo(@PathVariable int id){
        dispositivoService.deleteDispositivo(id);
    }
}
