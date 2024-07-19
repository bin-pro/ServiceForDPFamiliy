package org.example.sesacbibimbap.servicefordpfamily.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "dementia_centers")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
public class DementiaCenter {
    //id	name	operating_agency_phone	website_url
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "operating_agency_phone", length = 20)
    private String operatingAgencyPhone;

    @Column(name = "website_url", length = 150)
    private String websiteUrl;

    @OneToOne(mappedBy = "dementiaCenter", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Location locations;

    @Builder
    public DementiaCenter(String name, String operatingAgencyPhone, String websiteUrl) {
        this.name = name != null ? name : "";
        this.operatingAgencyPhone = operatingAgencyPhone != null ? operatingAgencyPhone : "";
        this.websiteUrl = websiteUrl != null ? websiteUrl : "";
    }
}
