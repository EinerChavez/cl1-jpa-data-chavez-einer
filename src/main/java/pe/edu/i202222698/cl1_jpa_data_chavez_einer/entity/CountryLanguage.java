package pe.edu.i202222698.cl1_jpa_data_chavez_einer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countrylanguage")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryLanguage {
    @Id
    @Column(name = "CountryCode", columnDefinition = "CHAR(3)")
    private String countryCode;

    @Id
    @Column(name = "Language", length = 30)
    private String language;

    @Column(name = "IsOfficial", columnDefinition = "CHAR(1)")
    private String isOfficial;

    @Column(nullable = false)
    private Float percentage;

    @ManyToOne
    @JoinColumn(name = "CountryCode", referencedColumnName = "Code", insertable = false, updatable = false)
    private Country country;
}