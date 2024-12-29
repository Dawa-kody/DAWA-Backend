package com.kody.dawa.domain.firstaid.service.impl;

import com.kody.dawa.domain.firstaid.entity.FirstAid;
import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;
import com.kody.dawa.domain.firstaid.repository.FirstAidRepository;
import com.kody.dawa.domain.firstaid.service.FirstAidService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FirstAidServiceImpl implements FirstAidService {
    private final FirstAidRepository firstAidRepository;

    @Transactional
    public void createFirstAid(FirstAidRequest request) {
        FirstAid firstAid = FirstAid.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .emoji(request.getEmoji())
                .build();

        for (FirstAidRequest.TagRequest tagRequest : request.getTags()) {
            FirstAid.Tag tag = new FirstAid.Tag();
            tag.setName(tagRequest.getName());
            tag.setFirstAid(firstAid);
            firstAid.getTags().add(tag);
        }
        firstAidRepository.save(firstAid);
    }

    public List<FirstAidResponse> getFirstAids(List<String> tags) {
        List<FirstAid> firstAids;
        if (tags == null || tags.isEmpty()) {
            firstAids = firstAidRepository.findAll();
        } else {
            firstAids = firstAidRepository.findByTags(tags);
        }

        return firstAids.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private FirstAidResponse convertToResponse(FirstAid firstAid) {
        List<FirstAidResponse.TagResponse> tagResponses = firstAid.getTags().stream()
                .map(tag -> new FirstAidResponse.TagResponse(tag.getName()))
                .collect(Collectors.toList());

        return FirstAidResponse.builder()
                .title(firstAid.getTitle())
                .emoji(firstAid.getEmoji())
                .tags(tagResponses)
                .build();
    }
}
