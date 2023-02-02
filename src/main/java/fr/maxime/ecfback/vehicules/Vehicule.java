package fr.maxime.ecfback.vehicules;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Vehicule {

    private String id;
    private String marque;
    private String modele;
    private String immatriculation;
    private String type;
    private Double prix;
    private String etat;
    private String status;

}
