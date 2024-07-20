package org.example.sesacbibimbap.servicefordpfamily.controller;

import lombok.RequiredArgsConstructor;
import org.example.sesacbibimbap.servicefordpfamily.dto.DementiaCenterDto;
import org.example.sesacbibimbap.servicefordpfamily.service.SearchService;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/center-info")
@RequiredArgsConstructor
public class CenterInfoController {
    private final SearchService searchService;
    //searchNearbyCenters
    @GetMapping("/nearby")
    public ResponseEntity<Page<DementiaCenterDto>> searchNearbyCenters(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(searchService.searchNearbyCenters(latitude, longitude, page, size));
    }
}
