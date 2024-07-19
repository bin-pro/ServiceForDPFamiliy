package org.example.sesacbibimbap.servicefordpfamily.service;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaCenter;
import org.example.sesacbibimbap.servicefordpfamily.entity.DementiaProgram;
import org.example.sesacbibimbap.servicefordpfamily.entity.Location;
import org.example.sesacbibimbap.servicefordpfamily.repository.DementiaCenterRepository;
import org.example.sesacbibimbap.servicefordpfamily.repository.DementiaProgramRepository;
import org.example.sesacbibimbap.servicefordpfamily.repository.LocationRepository;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import org.springframework.core.io.Resource;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

@Service @Slf4j @RequiredArgsConstructor
public class DataImportService {
    private final DementiaCenterRepository dementiaCenterRepository;
    private final LocationRepository locationRepository;
    private final DementiaProgramRepository dementiaProgramRepository;
    @Transactional
    public void importData() throws IOException, CsvException {
        log.info("Starting data import...");
        importDementiaCenters();
        importLocations();
        importDementiaPrograms();
        log.info("Data import completed");
    }

    @Transactional
    public void importDementiaCenters() throws IOException, CsvException {
        Resource resource = new ClassPathResource("static/dementia_centers.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), Charset.forName("EUC-KR")))) {

            List<String[]> records = reader.readAll();
            if (records.isEmpty()) {
                throw new RuntimeException("No records found");
            }
            records.remove(0); // Remove header

            for (String[] record : records) {
                DementiaCenter center = DementiaCenter.builder()
                        .name(record[1])
                        .operatingAgencyPhone(record[2])
                        .websiteUrl(record[3])
                        .build();
                dementiaCenterRepository.save(center);
            }
        }catch (Exception e){
            log.error("Error while importing dementia centers", e);
        }
    }

    @Transactional
    public void importLocations() throws IOException, CsvException {
        Resource resource = new ClassPathResource("static/locations.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), Charset.forName("EUC-KR"))) ) {
            List<String[]> records = reader.readAll();
            if(records.isEmpty()) {
                throw new RuntimeException("No records found");
            }

            records.remove(0); // Remove header

            for (String[] record : records) {
                Long dementiaCenterId = Long.parseLong(record[0]);
                DementiaCenter center = dementiaCenterRepository.findById(dementiaCenterId)
                        .orElseThrow(() -> new RuntimeException("DementiaCenter not found"));

                Location location = Location.builder()
                        .state(record[1])
                        .district(record[2])
                        .addressDetails(record[3])
                        .latitude(Double.parseDouble(record[4]))
                        .longitude(Double.parseDouble(record[5]))
                        .build();
                location.setDementiaCenter(center);

                locationRepository.save(location);
            }
        }
    }

    @Transactional
    public void importDementiaPrograms() throws IOException, CsvException {
        Resource resource = new ClassPathResource("static/dementia_programs.csv");
        try (CSVReader reader = new CSVReader(new InputStreamReader(resource.getInputStream(), Charset.forName("EUC-KR"))) ) {
            List<String[]> records = reader.readAll();
            if(records.isEmpty()) {
                throw new RuntimeException("No records found");
            }
            records.remove(0); // Remove header

            for (String[] record : records) {
                Long dementiaCenterId = Long.parseLong(record[0]);
                DementiaCenter center = dementiaCenterRepository.findById(dementiaCenterId)
                        .orElseThrow(() -> new RuntimeException("DementiaCenter not found"));

                String[] programs = record[1].split("\\+");

                for (String program : programs) {
                    DementiaProgram dementiaProgram = DementiaProgram.builder()
                            .name(program.trim())
                            .build();
                    dementiaProgram.setDementiaCenter(center);
                    dementiaProgramRepository.save(dementiaProgram);
                }
            }
        }
    }
}
