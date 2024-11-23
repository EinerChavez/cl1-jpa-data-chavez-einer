package pe.edu.i202222698.cl1_jpa_data_chavez_einer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.i202222698.cl1_jpa_data_chavez_einer.repository.CountryRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@SpringBootApplication
public class Cl1JpaDataChavezEinerApplication implements CommandLineRunner {

    @Autowired
    private CountryRepository countryRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(Cl1JpaDataChavezEinerApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        System.out.println("Estos son la Lista de Países Que hay:");
        countryRepository.findAll().forEach(country -> {
            System.out.println("País: " + country.getName());
        });
// CONSULTA   ifPresentOrElse()
        System.out.println("\n=== Idiomas disponibles para Argentina o Perú ===");
        executeLanguageQuery();

        System.out.println("\n=== Eliminando Colombia y Argentina ===");
        deleteCountriesAndRelatedData("COL", "ARG");

        System.out.println("\n=== Reejecutando consulta de idiomas ===");
        executeLanguageQuery();
    }

    private void executeLanguageQuery() {
        countryRepository.findById("ARG").ifPresentOrElse(
                country -> country.getLanguages().forEach(lang ->
                        System.out.println("Lenguaje de Argentina: " + lang.getLanguage())),
                () -> countryRepository.findById("PER").ifPresent(peru ->
                        peru.getLanguages().forEach(lang ->
                                System.out.println("Lenguaje de Perú: " + lang.getLanguage()))
                )
        );
    }

//CONSULTA deleteAllById()
    @Transactional
    public void deleteCountriesAndRelatedData(String... countryCodes) {
        String codes = String.join("','", countryCodes);
        String formattedCodes = "'" + codes + "'";
        try {
            entityManager.createNativeQuery("DELETE FROM city WHERE CountryCode IN (" + formattedCodes + ")").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM countrylanguage WHERE CountryCode IN (" + formattedCodes + ")").executeUpdate();
            entityManager.createNativeQuery("DELETE FROM country WHERE Code IN (" + formattedCodes + ")").executeUpdate();
            System.out.println("Países eliminados exitosamente");
        } catch (Exception e) {
            System.err.println("Error al eliminar países: " + e.getMessage());
        }
    }

}