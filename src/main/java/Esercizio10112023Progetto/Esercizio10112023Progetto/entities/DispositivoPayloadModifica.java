package Esercizio10112023Progetto.Esercizio10112023Progetto.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record DispositivoPayloadModifica(

        Stato stato,
        @NotNull(message = "devi attribuire un utente")
        int id
) {
}
