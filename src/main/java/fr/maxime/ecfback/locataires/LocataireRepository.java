package fr.maxime.ecfback.locataires;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface LocataireRepository extends MongoRepository<Locataire, String> {

    List<Locataire> findAllByNom(String nom);

    List<Locataire> findAllByPrenom(String prenom);

    List<Locataire> findAllByNomAndPrenom(String nom, String prenom);

    List<Locataire> findByEmail(String email);



}
