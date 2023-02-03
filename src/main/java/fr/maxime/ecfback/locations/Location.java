package fr.maxime.ecfback.locations;

import fr.maxime.ecfback.locataires.Locataire;
import fr.maxime.ecfback.vehicules.Vehicule;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Location {

    private String id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Double prixTotal;
    @DBRef
    private Locataire locataire;
    @DBRef
    private Vehicule vehicule;
}
