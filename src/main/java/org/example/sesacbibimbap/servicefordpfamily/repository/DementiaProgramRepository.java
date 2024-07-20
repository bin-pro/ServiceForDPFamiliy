package org.example.sesacbibimbap.servicefordpfamily.repository;

import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DementiaProgramRepository extends JpaRepository<DementiaProgram, Long> {

    @Query("SELECT dp.name FROM DementiaProgram dp WHERE dp.dementiaCenter.id = :id")
    List<String> findProgramsByCenterId(Long id);
}
