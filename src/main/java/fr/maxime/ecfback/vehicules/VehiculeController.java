package fr.maxime.ecfback.vehicules;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/vehicules")
public class VehiculeController {

    private final VehiculeServiceImpl service;

    public VehiculeController(VehiculeServiceImpl service) {
        this.service = service;
    }

    /**
     * Cette fonction permet de récupérer la liste de tous les véhicules présents dans la base de données<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules
     *
     * @return une liste de vehicules
     */
    @GetMapping("")
    public List<Vehicule> findAll() {
        return service.findAll();
    }

    /**
     * Cette fonction permet de sauvegarder un nouveau véhicule en base de données<br>
     * <b>Requête Postman en POST</b> : localhost:8080/vehicules
     *
     * @param entity Un véhicule
     * @return Le nouveau vehicule enregistré dans la base de données
     */
    @PostMapping("")
    public Vehicule save(@RequestBody Vehicule entity) {
        return service.save(entity);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en passant par son id<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     *
     * @param id L'id du véhicule
     * @return le véhicule recherché
     */
    @GetMapping("{id}")
    public Vehicule findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Cette fonction permet de supprimer un véhicule de la base de données en passant par son id<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     *
     * @param id L'id du véhicule à supprimer
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à sa marque<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/marque?marque=<span style="color:orange">marque</span>
     *
     * @param marque La marque du véhicule
     * @return Une liste de véhicules
     */
    @GetMapping("marque")
    public List<Vehicule> findAllByMarque(@RequestParam String marque) {
        return service.findAllByMarque(marque);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à son modèle<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/modele?modele=<span style="color:orange">modele</span>
     *
     * @param modele Le modèle du véhicule
     * @return Une liste de véhicules
     */
    @GetMapping("modele")
    public List<Vehicule> findAllByModele(@RequestParam String modele) {
        return service.findAllByModele(modele);
    }

    /**
     * Cette fonction permet de retrouver un véhicule grâce à sa marque et son modèle<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/marque&modele?marque=<span style="color:orange">marque</span>&modele=<span style="color:orange">modele</span>
     *
     * @param marque La marque du véhicule
     * @param modele Le modèle du véhicule
     * @return Une liste de véhicules
     */
    @GetMapping("marque&modele")
    public List<Vehicule> findAllByMarqueAndModele(@RequestParam String marque, @RequestParam String modele) {
        return service.findAllByMarqueAndModele(marque, modele);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son type<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/type?type=<span style="color:orange">type</span>
     *
     * @param type Le type de véhicule (Voiture, Camion, etc)
     * @return Une liste de véhicules
     */
    @GetMapping("type")
    public List<Vehicule> findAllByType(@RequestParam String type) {
        return service.findAllByType(type);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son immatriculation<br>
     * <b>Requête Postman en GET</b> : localhost:8080/vehicules/immatriculation?immatriculation=<span style="color:orange">immatriculation</span>
     *
     * @param immatriculation L'immatriculation du véhicule
     * @return Le véhicule recherché
     */
    @GetMapping("immatriculation")
    public Vehicule findByImmatriculation(@RequestParam String immatriculation) {
        return service.findByImmatriculation(immatriculation);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son état<br>
         * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/etat?etat=<span style="color:orange">etat</span>
     *
     * @param etat L'état du véhicule (A, B, C, D)
     * @return Une liste de véhicules
     */
    @GetMapping("etat")
    public List<Vehicule> findAllByEtat(@RequestParam String etat) {
        return service.findAllByEtat(etat);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son prix de location à la journée<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/prix?prix=<span style="color:orange">prix</span>
     *
     * @param prix Le prix de location à la journée du véhicule
     * @return Ue liste de véhicule
     */
    @GetMapping("prix")
    public List<Vehicule> findAllByPrix(@RequestParam String prix) {
        return service.findAllByPrix(prix);
    }

    /**
     * Cette fonction permet de retrouver un véhicule en fonction de son status de location<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/vehicules/status?status=<span style="color:orange">status</span>
     *
     * @param status Le status de location (Loué, Disponible)
     * @return Une liste de véhicule
     */
    @GetMapping("status")
    public List<Vehicule> findAllByStatus(String status) {
        return service.findAllByStatus(status);
    }

    /**
     * Cette fonction permet de mettre à jour le véhicule grâce à son id
     * <b>Requête Postman en PUT</b> : localhost:8080/vehicules/<span style="color:orange">id</span>
     * @param id L'id du véhicule
     * @return Le véhicule mis à jour
     */
    @PutMapping("{id}")
    public Vehicule update(@RequestBody Vehicule vehicule, @PathVariable String id) {
        return service.update(vehicule, id);
    }
}
