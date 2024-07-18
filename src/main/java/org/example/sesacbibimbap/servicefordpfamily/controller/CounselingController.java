package org.example.sesacbibimbap.servicefordpfamily.controller;

import org.example.sesacbibimbap.servicefordpfamily.dto.CounselingDto;
import org.example.sesacbibimbap.servicefordpfamily.service.CounselingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RequestMapping("/counsel")
@RestController
@RequiredArgsConstructor
public class CounselingController {
    private final CounselingService counselingService;

    @GetMapping
    public ResponseEntity<CounselingDto.GetResponse> getCounseling(CounselingDto.GetRequest request){
        log.info("getCounseling at "+ LocalDateTime.now().toString());

        return ResponseEntity.ok(counselingService.getCounselingResponse(request));
    }


}
