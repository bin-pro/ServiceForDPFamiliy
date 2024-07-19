package org.example.sesacbibimbap.servicefordpfamily.repository;

import org.example.sesacbibimbap.servicefordpfamily.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
}
