package com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.address.AddressMapping;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "suppliers")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SupplierMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    @Enumerated(EnumType.STRING)
    private DocumentType documentType;
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressMapping address;

    @ManyToMany(mappedBy = "suppliers")
    private List<CompanyMapping> companies;

    // Constructors, getters, and setters are automatically generated by Lombok
}
