package fr.maxime.ecfback.locataires;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Locataire {

    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;

}
