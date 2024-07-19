package org.example.sesacbibimbap.servicefordpfamily.repository;

import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DementiaProgramRepository extends JpaRepository<DementiaProgram, Long> {


}
