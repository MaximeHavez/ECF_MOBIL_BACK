package fr.maxime.ecfback.locations;

import fr.maxime.ecfback.locataires.Locataire;
import fr.maxime.ecfback.vehicules.Vehicule;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    List<Location> findAll();

    Location save(Location entity);

    Location findById(String id);

    Location update(Location Location, String id);

    void deleteById(String id);

    Double calculPrixTotal(String idVoiture, String idLocation, LocalDate dateDebut, LocalDate DateFin);
}
