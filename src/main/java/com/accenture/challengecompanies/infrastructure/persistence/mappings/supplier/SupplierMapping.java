package com.accenture.challengecompanies.infrastructure.persistence.mappings.supplier;

import com.accenture.challengecompanies.domain.enums.DocumentType;
import com.accenture.challengecompanies.domain.models.Supplier;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.address.AddressMapping;
import com.accenture.challengecompanies.infrastructure.persistence.mappings.company.CompanyMapping;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "suppliers", uniqueConstraints = @UniqueConstraint(columnNames = {"document", "documentType"}))
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

    private String email;

    private String rg;
    private Date birthdate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private AddressMapping address;

    @ManyToMany(mappedBy = "suppliers")
    private List<CompanyMapping> companies;


    public SupplierMapping(Supplier supplier) {
        this.document = supplier.getDocument();
        this.documentType = supplier.getDocumentType();
        this.name = supplier.getName();
        this.email = supplier.getEmail();
        this.rg = supplier.getRg();
        this.birthdate = supplier.getBirthdate();
        this.address = new AddressMapping(supplier.getAddress());

    }

    public Supplier toModel() {
        return new Supplier(
                this.getId(),
                this.getDocument(),
                this.getDocumentType(),
                this.getName(),
                this.getEmail(),
                this.getRg(),
                this.getBirthdate(),
                this.getAddress().toModel()
        );
    }

    // Constructors, getters, and setters are automatically generated by Lombok
}
