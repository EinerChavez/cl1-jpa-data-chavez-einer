package pe.edu.i202222698.cl1_jpa_data_chavez_einer.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class CountryLanguageId implements Serializable {
    private String countryCode;
    private String language;
}