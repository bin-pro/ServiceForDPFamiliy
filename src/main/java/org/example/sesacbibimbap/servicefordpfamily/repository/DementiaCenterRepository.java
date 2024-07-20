package org.example.sesacbibimbap.servicefordpfamily.repository;

import org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto;
import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaCenter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DementiaCenterRepository extends JpaRepository<DementiaCenter, Long> {
    @Query("SELECT new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto(" +
            "dc.id, dc.name, dc.operatingAgencyPhone, dc.websiteUrl, " +
            "CAST(FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', l.longitude, l.latitude), FUNCTION('POINT', :longitude, :latitude)) AS double), " +
            "new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto$LocationInfo(" +
            "l.state, l.district, l.addressDetails, l.latitude, l.longitude)) " +
            "FROM DementiaCenter dc JOIN dc.locations l " +
            "ORDER BY FUNCTION('ST_Distance_Sphere', FUNCTION('POINT', l.longitude, l.latitude), FUNCTION('POINT', :longitude, :latitude))")
    Page<DementiaCenterDto> findNearbyCenters(@Param("latitude") double latitude,
                                              @Param("longitude") double longitude,
                                              Pageable pageable);

    @Query("SELECT new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto(" +
            "dc.id, dc.name, dc.operatingAgencyPhone, dc.websiteUrl, " +
            "new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto$LocationInfo(" +
            "l.state, l.district, l.addressDetails, l.latitude, l.longitude)) " +
            "FROM DementiaCenter dc JOIN dc.locations l " +
            "WHERE l.state = :state")
    Page<DementiaCenterDto> findCentersByState(String state, Pageable pageable);

    @Query("SELECT new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto(" +
            "dc.id, dc.name, dc.operatingAgencyPhone, dc.websiteUrl, " +
            "new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto$LocationInfo(" +
            "l.state, l.district, l.addressDetails, l.latitude, l.longitude)) " +
            "FROM DementiaCenter dc JOIN dc.locations l " +
            "WHERE l.state = :state AND l.district = :district")
    Page<DementiaCenterDto> findCentersByStateAndDistrict(String state, String district, Pageable pageable);

    @Query("SELECT new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto(" +
            "dc.id, dc.name, dc.operatingAgencyPhone, dc.websiteUrl, " +
            "new org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto$LocationInfo(" +
            "l.state, l.district, l.addressDetails, l.latitude, l.longitude)) " +
            "FROM DementiaCenter dc JOIN dc.locations l")
    Page<DementiaCenterDto> findAllCenters(Pageable pageable);
}
