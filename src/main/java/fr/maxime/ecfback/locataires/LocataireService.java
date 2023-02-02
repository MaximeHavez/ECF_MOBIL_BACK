package fr.maxime.ecfback.locataires;

import java.util.List;

public interface LocataireService {
    List<Locataire> findAll();

    Locataire save(Locataire entity);

    Locataire findById(String id);

    Locataire update(Locataire locataire,String id);

    void deleteById(String id);


}
