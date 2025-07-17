package com.kody.dawa.domain.firstaid.presentation;

import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidsResponse;
import com.kody.dawa.domain.firstaid.service.FirstAidService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/firstaid")
@RestController
@RequiredArgsConstructor
public class FirstAidController {
    private final FirstAidService firstAidService;
    @PostMapping //admin
    public void createFirstAid(@RequestBody FirstAidRequest request) {
        firstAidService.createFirstAid(request);
    }

    @GetMapping
    public List<FirstAidsResponse> getFirstAids(@RequestParam(required = false) List<String> tags) {
        return firstAidService.getFirstAids(tags);
    }

    @GetMapping("/{id}")
    public FirstAidResponse getFirstAid(@PathVariable Long id) {
        return firstAidService.getFirstAid(id);
    }


}
