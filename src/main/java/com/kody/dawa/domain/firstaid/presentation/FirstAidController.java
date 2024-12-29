package com.kody.dawa.domain.firstaid.presentation;

import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;
import com.kody.dawa.domain.firstaid.service.FirstAidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/firstaid")
@RestController
@RequiredArgsConstructor
public class FirstAidController {
    private final FirstAidService firstAidService;
    @PostMapping
    public void createFirstAid(@RequestBody FirstAidRequest request) {
        firstAidService.createFirstAid(request);
    }

    @GetMapping
    public List<FirstAidResponse> getFirstAids(@RequestParam(required = false) List<String> tags) {
        return firstAidService.getFirstAids(tags);
    }
}
