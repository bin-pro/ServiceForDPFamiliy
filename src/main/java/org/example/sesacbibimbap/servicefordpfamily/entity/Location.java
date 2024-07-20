package org.example.sesacbibimbap.servicefordpfamily.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "locations", indexes = {
        @Index(name = "idx_latitude_longitude", columnList = "latitude,longitude"),
        @Index(name = "idx_state", columnList = "state"),
        @Index(name = "idx_district", columnList = "district")
})
public class Location {
    //state	district	address_details	latitude	longitude

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "state", length = 20)
    private String state;

    @Column(name = "district", length = 20)
    private String district;

    @Column(name = "address_details", length = 100)
    private String addressDetails;

    @Column(name = "latitude", length = 50)
    private Double latitude;

    @Column(name = "longitude", length = 50)
    private Double longitude;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "dementia_center_id", nullable = false, columnDefinition = "BIGINT", foreignKey = @ForeignKey(name = "fk_location_dementia_center"))
    private DementiaCenter dementiaCenter;

    @Builder
    public Location(String state, String district, String addressDetails, Double latitude, Double longitude) {
        this.state = state != null ? state : "";
        this.district = district != null ? district : "";
        this.addressDetails = addressDetails != null ? addressDetails : "";
        this.latitude = latitude != null ? latitude : 0.0;
        this.longitude = longitude != null ? longitude : 0.0;
    }

    public void setDementiaCenter(DementiaCenter center) {
        this.dementiaCenter = center;
    }
}
