package fr.maxime.ecfback.locations;

import fr.maxime.ecfback.locataires.Locataire;
import fr.maxime.ecfback.locataires.LocataireServiceImpl;
import fr.maxime.ecfback.vehicules.Vehicule;
import fr.maxime.ecfback.vehicules.VehiculeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class LocationServiceImpl implements LocationService {

    Logger logger = LoggerFactory.getLogger(LocationServiceImpl.class);

    private final LocationRepository repository;
    private final VehiculeServiceImpl vehiculeService;

    public LocationServiceImpl(LocationRepository repository,
                               VehiculeServiceImpl vehiculeService,
                               LocataireServiceImpl locataireService) {
        logger.info("Création du service Location");
        this.repository = repository;
        this.vehiculeService = vehiculeService;
    }

    /**
     * Cette fonction permet de récupérer la liste de toutes les locations présentes dans la base de données<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations
     * @return une liste de locations
     */
    @Override
    public List<Location> findAll() {
        return repository.findAll();
    }

    /**
     * Cette fonction permet de sauvegarder une nouvelle location en base de données<br>
     * <b>Requête Postman en POST</b> : localhost:8080/locations
     * @param entity Une location
     * @return La nouvelle location enregistrée dans la base de données
     */
    @Override
    public Location save(Location entity) {
        return repository.save(entity);
    }

    /**
     * Cette fonction permet de retrouver une location en passant par son id<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/<span style="color:orange">id</span>
     * @param id L'id de la location
     * @return la location recherchée
     */
    @Override
    public Location findById(String id) {
        return repository.findById(id).orElseThrow(()-> {
            logger.warn("Locations : FindById invalide : " + id);
            return new ResponseStatusException(HttpStatus.NOT_FOUND);
        });
    }

    @Override
    public Location update(Location location, String id) {
        if (!Objects.equals(location.getId(), id)) {
            logger.warn("In invalide : " + id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.repository.save(location);
    }

    /**
     * Cette fonction permet de supprimer une location de la base de données en passant par son id<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/locations/<span style="color:orange">id</span>
     * @param id L'id de la location à supprimer
     */
    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de début de location<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datedebut?dateDebut=<span style="color:orange">dateDebut</span>
     * @param dateDebut La date de début de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    public List<Location> findAllByDateDebut(LocalDate dateDebut) {
        return repository.findAllByDateDebut(dateDebut);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de fin de location<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datefin?dateFin=<span style="color:orange">dateFin</span>
     * @param dateFin La date de fin de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    public List<Location> findAllByDateFin(LocalDate dateFin) {
        return repository.findAllByDateFin(dateFin);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de début et de fin de location
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datedebut&fin?dateDebut=<span style="color:orange">dateDebut</span>&dateFin=<span style="color:orange">dateFin</span>
     * @param dateDebut La date de début de location (Format : "YYYY-MM-DD")
     * @param dateFin La date de fin de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    public List<Location> findAllByDateDebutAndDateFin(LocalDate dateDebut, String dateFin) {
        return repository.findAllByDateDebutAndDateFin(dateDebut, dateFin);
    }

    /**
     * Cette fonction permet de retrouver une location grâce au nom du locataire<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/nom?nom=<span style="color:orange">nom</span>
     * @param nom
     * @return
     */
    public List<Location> findAllByLocataireName(String nom) {
        List<Location> locations = this.repository.findAll();
        List<Location> locationlist = new ArrayList<>();
        for (Location location: locations
             ) {
            if (Objects.equals(location.getLocataire().getNom(), nom)) {
                locationlist.add(location);
            }
        }
        return locationlist;
    }

    /**
     * Cette fonction permet de retrouver une location grâce au numéro d'immatriculation du véhicule
     * <b>Requête Postman en GET</b> : localhost:8080/locations/immatriculation?immatriculation=<span style="color:orange">immatriculation</span>
     * @param immatriculation L'immatriculation du véhicule
     * @return Une liste de location
     */
    public List<Location> findAllByImmatriculation(String immatriculation){
        List<Location> locations = this.repository.findAll();
        List<Location> locationlist = new ArrayList<>();
        for (Location location: locations
        ) {
            if (Objects.equals(location.getVehicule().getImmatriculation(), immatriculation)) {
                locationlist.add(location);
            }
        }
        return locationlist;
    }

    /**
     * Cette fonction permet de calculer le prix total de la location en fonction du prix à la journée du véhicule
     * et de la durée de la location, calculée en comparant la date de début et de fin de la location.
     * @param idVoiture L'id du véhicule
     * @param idLocation L'id de la location
     * @param dateDebut La date de début de la location (Format : "YYYY-MM-DD")
     * @param dateFin La date de fin de la location (Format : "YYYY-MM-DD")
     * @return Le prix total de la location
     */
    @Override
    public Double calculPrixTotal(String idVoiture, String idLocation, LocalDate dateDebut, LocalDate dateFin) {
        Double prixUnitaire = this.vehiculeService.findById(idVoiture).getPrix();
        Long duration = ChronoUnit.DAYS.between(dateDebut, dateFin);
        Double prixTotal = prixUnitaire*duration;
        return prixTotal;
    }






}
