package fr.maxime.ecfback.locataires;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/locataires")
public class LocataireController {

    private final LocataireServiceImpl service;

    public LocataireController(LocataireServiceImpl service) {
        this.service = service;
    }

    /**
     * Cette fonction permet de récupérer la liste de tous les locataires présents dans la base de données<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locataires
     *
     * @return une liste de locataires
     */
    @GetMapping("")
    public List<Locataire> findAll() {
        return service.findAll();
    }

    /**
     * Cette fonction permet de sauvegarder un nouveau locataire en base de données<br>
     * <b>Requête Postman en POST</b> : localhost:8080/locataires
     *
     * @param entity Un locataire
     * @return Le nouveau locataire enregistré dans la base de données
     */
    @PostMapping("")
    public Locataire save(@RequestBody Locataire entity) {
        return service.save(entity);
    }

    /**
     * Cette fonction permet de retrouver un locataire en passant par son id<br>
     * <b>Requête Postman en GET</b> : localhost:8080/locataires/<span style="color:orange">id</span>
     *
     * @param id L'id du locataire
     * @return le locataire recherché
     */
    @GetMapping("{id}")
    public Locataire findById(@PathVariable String id) {
        return service.findById(id);
    }

    /**
     * Cette fonction permet de supprimer un locataire de la base de données en passant par son id<br>
     * <b>Requête Postman en DELETE</b> : localhost:8080/locataires/<span style="color:orange">id</span>
     *
     * @param id L'id du locataire à supprimer
     */
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable String id) {
        service.deleteById(id);
    }

    /**
     * Cette fonction permet de retrouver un locataire grâce à son nom de famille
     * <b>Requête Postman en GET</b> : localhost:8080/locataires/nom?nom=<span style="color:orange">nom</span>
     *
     * @param nom Le nom de famille du locataire
     * @return Une liste de locataire
     */
    @GetMapping("nom")
    public List<Locataire> findAllByNom(@RequestParam String nom) {
        return service.findAllByNom(nom);
    }

    /**
     * Cette fonction permet de retrouver un locataire grâce à son prénom
     * <b>Requête Postman en GET</b> : localhost:8080/locataires/prenom?prenom=<span style="color:orange">prenom</span>
     *
     * @param prenom Le prénom du locataire
     * @return Une liste de locataires
     */
    @GetMapping("prenom")
    public List<Locataire> findAllByPrenom(@RequestParam String prenom) {
        return service.findAllByPrenom(prenom);
    }

    /**
     * Cette fonction permet de retrouver un locataire grâce à son nom de famille et son prénom
     * <b>Requête Postman en GET</b> : localhost:8080/locataires/nom&prenom?nom=<span style="color:orange">nom</span>&prenom=<span style="color:orange">prenom</span>
     *
     * @param nom    Le nom de famille du locataire
     * @param prenom Le prénom du locataire
     * @return Une liste de locataire
     */
    @GetMapping("nom&prenom")
    public List<Locataire> findAllByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return service.findAllByNomAndPrenom(nom, prenom);
    }

    /**
     * Cette fonction permet de retrouver un locataire grâce à son email
     * <b>Requête Postman en GET</b> : localhost:8080/locataires/email?email=<span style="color:orange">email</span>
     *
     * @param email L'email du locataire
     * @return Le locataire recherché
     */
    @GetMapping("email")
    public List<Locataire> findByEmail(@RequestParam String email) {
        return service.findByEmail(email);
    }

    /**
     * Cette fonction permet de mettre à jour le locataire grâce à son id
     * <b>Requête Postman en PUT</b> : localhost:8080/locataires/<span style="color:orange">id</span>
     * @param id L'id du locataire
     * @return Le locataire mis à jour
     */
    @PutMapping("{id}")
    public Locataire update(@RequestBody Locataire locataire, @PathVariable String id) {
        return service.update(locataire, id);
    }
}
