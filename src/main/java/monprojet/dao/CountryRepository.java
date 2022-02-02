package monprojet.dao;

import java.util.HashMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import monprojet.entity.Country;

// This will be AUTO IMPLEMENTED by Spring 

public interface CountryRepository extends JpaRepository<Country, Integer> {

    @Query(value = "SELECT SUM(population) as population "
            + "FROM City "
            + "WHERE City.country_id = :id ", nativeQuery = true)
    public Integer populationCountry(Integer id);

    //Une méthode sans paramètre, qui renvoie une liste (nom du pays, population)
    @Query(value = "SELECT country.name, SUM(city.population) as population "
            + "FROM Country "
            + "INNER JOIN City ON Country.id = City.country_id "
            +"GROUP BY country.name", nativeQuery = true)
    public List<VillePopulation> countryList();

}
