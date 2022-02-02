package monprojet.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import monprojet.entity.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;

@Log4j2 // Génère le 'logger' pour afficher les messages de trace
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryDAO;
    @Autowired
    private CityRepository cityDAO;

    @Test
    void lesNomsDePaysSontTousDifferents() {
        log.info("On vérifie que les noms de pays sont tous différents ('unique') dans la table 'Country'");
        
        Country paysQuiExisteDeja = new Country("XX", "France");
        try {
            countryDAO.save(paysQuiExisteDeja); // On essaye d'enregistrer un pays dont le nom existe   

            fail("On doit avoir une violation de contrainte d'intégrité (unicité)");
        } catch (DataIntegrityViolationException e) {
            // Si on arrive ici c'est normal, l'exception attendue s'est produite
        }
    }

    @Test
    @Sql("test-data.sql") // On peut charger des donnnées spécifiques pour un test
    void onSaitCompterLesEnregistrements() {
        log.info("On compte les enregistrements de la table 'Country'");
        int combienDePaysDansLeJeuDeTest = 3 + 1; // 3 dans data.sql, 1 dans test-data.sql
        long nombre = countryDAO.count();
        assertEquals(combienDePaysDansLeJeuDeTest, nombre, "On doit trouver 4 pays" );
    }

    @Test
    void idPopulationTest() {
        log.info("On vérifie la population du pays ('id')");
        int population = 0;
        int id = 1;
        for(int i=0; i<cityDAO.count(); i++){
            if(cityDAO.getOne(i).getId()==id){
                population += cityDAO.getOne(i).getPopulation();
            }
        }
        int populationCountry = countryDAO.populationCountry(id);
        assertEquals(populationCountry, population);
    }


    @Test
    void countryListTest() {
        HashMap<String, Integer> countryTest = new HashMap<>();
        countryTest.put("France", 12);
        countryTest.put("United Kingdom", 18);
        countryTest.put("United States of America", 27);

        List<VillePopulation> result = countryDAO.countryList();

        for (VillePopulation villePopulation : result) {
            String nom = villePopulation.getName();
            Integer population = villePopulation.getPopulation();

            if (countryTest.get(nom) != null) {
                assertEquals(population, countryTest.get(nom));
            }

        }
    }
}
