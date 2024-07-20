package org.example.sesacbibimbap.servicefordpfamily.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto;
import org.example.sesacbibimbap.servicefordpfamily.repository.DementiaCenterRepository;
import org.example.sesacbibimbap.servicefordpfamily.repository.DementiaProgramRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j @RequiredArgsConstructor
public class SearchService {
    private final DementiaCenterRepository dementiaCenterRepository;
    private final DementiaProgramRepository dementiaProgramRepository;
    public Page<DementiaCenterDto> searchNearbyCenters(double latitude, double longitude, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Page<DementiaCenterDto> dementiaCenterPage = dementiaCenterRepository.findNearbyCenters(latitude, longitude, pageable);

        for(DementiaCenterDto dementiaCenterDto : dementiaCenterPage) {
            log.info("Dementia Center: {}", dementiaCenterDto);

            dementiaCenterDto.setDementiaPrograms(dementiaProgramRepository.findProgramsByCenterId(dementiaCenterDto.getId()));

        }
        return dementiaCenterPage;
    }

    public Page<DementiaCenterDto> searchCenters(String state, String district, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DementiaCenterDto> dementiaCenterPage;

        if(state == null) {
            dementiaCenterPage = dementiaCenterRepository.findAllCenters(pageable);
        } else if(district == null) {
            dementiaCenterPage = dementiaCenterRepository.findCentersByState(state, pageable);
        } else {
            dementiaCenterPage = dementiaCenterRepository.findCentersByStateAndDistrict(state, district, pageable);
        }

        for(DementiaCenterDto dementiaCenterDto : dementiaCenterPage) {
            log.info("Dementia Center: {}", dementiaCenterDto);
            dementiaCenterDto.setDementiaPrograms(dementiaProgramRepository.findProgramsByCenterId(dementiaCenterDto.getId()));
        }

        return dementiaCenterPage;
    }
}
