package pe.edu.i202222698.cl1_jpa_data_chavez_einer.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pe.edu.i202222698.cl1_jpa_data_chavez_einer.entity.Country;


@Repository
public interface CountryRepository extends CrudRepository<Country, String> {

}