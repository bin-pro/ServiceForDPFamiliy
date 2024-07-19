package org.example.sesacbibimbap.servicefordpfamily.repository;

import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DementiaCenterRepository extends JpaRepository<DementiaCenter, Long> {

}
