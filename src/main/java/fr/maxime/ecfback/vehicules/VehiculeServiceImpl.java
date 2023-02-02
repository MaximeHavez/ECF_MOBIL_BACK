package fr.maxime.ecfback.vehicules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
public class VehiculeServiceImpl implements VehiculeService {

    Logger logger = LoggerFactory.getLogger(VehiculeServiceImpl.class);

    private final VehiculeRepository repository;

    public VehiculeServiceImpl(VehiculeRepository repository) {
        logger.info("Création du service Vehicule");
        this.repository = repository;
    }

    /**
     * Cette fonction permet de récupérer la liste de tous les véhicules présents dans la base de données<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules
     * @return une liste de vehicules
     */
    @Override
    public List<Vehicule> findAll() {
        return repository.findAll();
    }

    /**
     * Cette fonction permet de sauvegarder un nouveau véhicule en base de données<br>
     * <b>Requête Postman en POST</b> : localhost:8080/vehicules
     * @param entity Un véhicule
     * @return Le nouveau vehicule enregistré dans la base de données
     */
    @Override
    public Vehicule save(Vehicule entity) {
        return repository.save(entity);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en passant par son id<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     * @param id L'id du véhicule
     * @return le véhicule recherché
     */
    @Override
    public Vehicule findById(String id) {
        return repository.findById(id).orElseThrow(()-> {
            logger.warn("Id invalide : " + id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    /**
     * Cette fonction permet de mettre à jour le véhicule grâce à son id
     * <b>Requête Postman en PUT</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     * @param id L'id du véhicule
     * @return Le véhicule mis à jour
     */
    @Override
    public Vehicule update(Vehicule vehicule, String id) {
        if (!Objects.equals(vehicule.getId(), id)) {
            logger.warn("In invalide : " + id + "id vehicule : " + vehicule.getId() );
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.repository.save(vehicule);
    }

    /**
     * Cette fonction permet de supprimer un véhicule de la base de données en passant par son id<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     * @param id L'id du véhicule à supprimer
     */
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à sa marque<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/marque?marque=<span style="color:orange">marque</span>
     * @param marque La marque du véhicule
     * @return Une liste de véhicules
     */
    public List<Vehicule> findAllByMarque(String marque) {
        return repository.findAllByMarque(marque);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à son modèle<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/modele?modele=<span style="color:orange">modele</span>
     * @param modele Le modèle du véhicule
     * @return Une liste de véhicules
     */
    public List<Vehicule> findAllByModele(String modele) {
        return repository.findAllByModele(modele);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à sa marque et son modèle<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/marque&modele?marque=<span style="color:orange">marque</span>&modele=<span style="color:orange">modele</span>
     * @param marque La marque du véhicule
     * @param modele Le modèle du véhicule
     * @return Une liste de véhicules
     */
    public List<Vehicule> findAllByMarqueAndModele(String marque, String modele) {
        return repository.findAllByMarqueAndModele(marque, modele);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son type<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/type?type=<span style="color:orange">type</span>
     * @param type Le type de véhicule (Voiture, Camion, etc)
     * @return Une liste de véhicules
     */
    public List<Vehicule> findAllByType(String type) {
        return repository.findAllByType(type);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son immatriculation<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/immatriculation?immatriculation=<span style="color:orange">immatriculation</span>
     * @param immatriculation L'immatriculation du véhicule
     * @return Le véhicule recherché
     */
    public Vehicule findByImmatriculation(String immatriculation) {
        return repository.findByImmatriculation(immatriculation);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son état<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/etat?etat=<span style="color:orange">etat</span>
     * @param etat L'état du véhicule (A, B, C, D)
     * @return Une liste de véhicules
     */
    public List<Vehicule> findAllByEtat(String etat) {
        return repository.findAllByEtat(etat);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son prix de location à la journée<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/prix?prix=<span style="color:orange">prix</span>
     * @param prix Le prix de location à la journée du véhicule
     * @return Ue liste de véhicule
     */
    public List<Vehicule> findAllByPrix(String prix) {
        return repository.findAllByPrix(prix);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son status de location<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/status?status=<span style="color:orange">status</span>
     * @param status Le status de location (Loué, Disponible)
     * @return Une liste de véhicule
     */
    public List<Vehicule> findAllByStatus(String status) {
        return repository.findAllByStatus(status);
    }
}
