package fr.maxime.ecfback.locations;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@CrossOrigin
@RequestMapping("/locations")
public class LocationController {

    private final LocationServiceImpl service;

    public LocationController(LocationServiceImpl service) {
        this.service = service;
    }

    /**
     * Cette fonction permet de récupérer la liste de toutes les locations présentes dans la base de données<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations
     *
     * @return une liste de locations
     */
    @GetMapping("")
    public List<Location> findAll() {
        return service.findAll();
    }

    /**
     * Cette fonction permet de sauvegarder une nouvelle location en base de données<br>
     * Elle calcule le prix total de la location grâçe à la fonction calculPrixTotal du service<br>
     * Si le véhicule est déjà loué, elle retourne une erreur 406 - NOT_ACCEPTABLE
     * et enregistre le résultat dans l'objet Location
     * <b>Requête Postman en POST</b> : localhost:8080/locations
     *
     * @param entity Une location
     * @return La nouvelle location enregistrée dans la base de données
     */
    @PostMapping("")
    public Location save(@RequestBody Location entity) {
        String idVehicule = entity.getVehicule().getId();
        String idLocation = entity.getId();
        LocalDate dateDebut = entity.getDateDebut();
        LocalDate dateFin = entity.getDateFin();
        Double prixTotal = service.calculPrixTotal(idVehicule, idLocation, dateDebut, dateFin);
        entity.setPrixTotal(prixTotal);

        if (Objects.equals(entity.getVehicule().getStatus(), "Loué")) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Le véhicule est déjà loué");
        }
        return service.save(entity);

    }

    /**
     * Cette fonction permet de retrouver une location en passant par son id<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/<span style="color:orange">id</span>
     *
     * @param id L'id de la location
     * @return la location recherchée
     */
    @GetMapping("{id}")
    public Location findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Cette fonction permet de supprimer une location de la base de données en passant par son id<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     *
     * @param id L'id de la location à supprimer
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de début de location<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datedebut?dateDebut=<span style="color:orange">dateDebut</span>
     *
     * @param dateDebut La date de début de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    @GetMapping("dateDebut")
    public List<Location> findAllByDateDebut(@RequestParam LocalDate dateDebut) {
        return service.findAllByDateDebut(dateDebut);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de fin de location<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datefin?dateFin=<span style="color:orange">dateFin</span>
     *
     * @param dateFin La date de fin de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    @GetMapping("dateFin")
    public List<Location> findAllByDateFin(LocalDate dateFin) {
        return service.findAllByDateFin(dateFin);
    }

    /**
     * Cette fonction permet de retrouver une location en fonction de sa date de début et de fin de location
     * <b>Requête Postman en GET</b> : localhost:8080/locations/datedebut&fin?dateDebut=<span style="color:orange">dateDebut</span>&dateFin=<span style="color:orange">dateFin</span>
     *
     * @param dateDebut La date de début de location (Format : "YYYY-MM-DD")
     * @param dateFin   La date de fin de location (Format : "YYYY-MM-DD")
     * @return Une liste de locations
     */
    @GetMapping("dateDebut&dateFin")
    public List<Location> findAllByDateDebutAndDateFin(LocalDate dateDebut, String dateFin) {
        return service.findAllByDateDebutAndDateFin(dateDebut, dateFin);
    }

    /**
     * Cette fonction permet de retrouver une location grâce au nom du locataire<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locations/nom?nom=<span style="color:orange">nom</span>
     * @param nom
     * @return
     */
    @GetMapping("nom")
    public List<Location> findAllByLocataireName(@RequestParam String nom) {
        return service.findAllByLocataireName(nom);
    }

    /**
     * Cette fonction permet de retrouver une location grâce au numéro d'immatriculation du véhicule
     * <b>Requête Postman en GET</b> : localhost:8080/locations/immatriculation?immatriculation=<span style="color:orange">immatriculation</span>
     * @param immatriculation L'immatriculation du véhicule
     * @return Une liste de location
     */
    @GetMapping("immatriculation")
    public List<Location> findAllByImmatriculation(@RequestParam String immatriculation) {
        return service.findAllByImmatriculation(immatriculation);
    }
    @PutMapping("{id}")
    public Location update(Location location, String id) {
        return service.update(location, id);
    }
}
