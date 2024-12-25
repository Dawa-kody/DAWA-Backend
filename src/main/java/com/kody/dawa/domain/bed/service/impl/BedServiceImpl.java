package com.kody.dawa.domain.bed.service.impl;

import com.kody.dawa.domain.bed.entity.Bed;
import com.kody.dawa.domain.bed.presentation.dto.request.BedRequest;
import com.kody.dawa.domain.bed.repository.BedRepository;
import com.kody.dawa.domain.bed.service.BedService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BedServiceImpl implements BedService {
    private final BedRepository bedRepository;

    public void bedSetting(BedRequest request) {
        Optional<Bed> bedStatus = bedRepository.findAll().stream().reduce((first, second) -> second);
        Bed bed;
        if (bedStatus.isPresent()) {
            bed = bedStatus.get();
            bed.setBed1(request.isBed1());
            bed.setBed2(request.isBed2());
        } else {
            bed = Bed.builder()
                    .bed1(request.isBed1())
                    .bed2(request.isBed2())
                    .build();
        }
        bedRepository.save(bed);
    }

    public Bed getCurrentBedStatus() {
        return bedRepository.findAll().stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }
}
