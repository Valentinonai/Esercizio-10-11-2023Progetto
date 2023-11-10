package Esercizio10112023Progetto.Esercizio10112023Progetto.services;

import Esercizio10112023Progetto.Esercizio10112023Progetto.repositories.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DispositivoService {
    @Autowired
    private DispositivoRepository dispositivoRepository;
}
