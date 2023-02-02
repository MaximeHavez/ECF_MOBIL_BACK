package fr.maxime.ecfback.vehicules;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface VehiculeRepository extends MongoRepository<Vehicule, String> {

    List<Vehicule> findAllByMarque(String marque);
    List<Vehicule> findAllByModele(String modele);
    List<Vehicule> findAllByMarqueAndModele(String marque, String modele);
    List<Vehicule> findAllByType(String type);
    Vehicule findByImmatriculation(String immatriculation);
    List<Vehicule> findAllByEtat(String etat);
    List<Vehicule> findAllByPrix(String prix);
    List<Vehicule> findAllByStatus(String status);

}
