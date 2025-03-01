package com.kody.dawa.domain.firstaid.service.impl;

import com.kody.dawa.domain.firstaid.entity.FirstAid;
import com.kody.dawa.domain.firstaid.presentation.dto.request.FirstAidRequest;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidResponse;
import com.kody.dawa.domain.firstaid.presentation.dto.response.FirstAidsResponse;
import com.kody.dawa.domain.firstaid.repository.FirstAidRepository;
import com.kody.dawa.domain.firstaid.service.FirstAidService;
import com.kody.dawa.global.aws.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FirstAidServiceImpl implements FirstAidService {
    private final FirstAidRepository firstAidRepository;
    private final S3Service s3Service;

    @Transactional
    public void createFirstAid(FirstAidRequest request) {
        String emoji = request.getEmoji();
        String imageUrl = getImageUrlFromEmoji(emoji);

        FirstAid firstAid = FirstAid.builder()
                .content(request.getContent())
                .title(request.getTitle())
                .emoji(imageUrl)
                .build();

        for (FirstAidRequest.TagRequest tagRequest : request.getTags()) {
            FirstAid.Tag tag = new FirstAid.Tag();
            tag.setName(tagRequest.getName());
            tag.setFirstAid(firstAid);
            firstAid.getTags().add(tag);
        }
        firstAidRepository.save(firstAid);
    }

    public List<FirstAidsResponse> getFirstAids(List<String> tags) {
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

    private FirstAidsResponse convertToResponse(FirstAid firstAid) {
        List<FirstAidsResponse.TagResponse> tagResponses = firstAid.getTags().stream()
                .map(tag -> new FirstAidsResponse.TagResponse(tag.getName()))
                .collect(Collectors.toList());

        return FirstAidsResponse.builder()
                .title(firstAid.getTitle())
                .emoji(firstAid.getEmoji())
                .tags(tagResponses)
                .build();
    }

    public FirstAidResponse getFirstAid(Long id) {
        Optional<FirstAid> firstAidOptional = firstAidRepository.findById(id);
        FirstAid firstAid = firstAidOptional.orElseThrow(() -> new RuntimeException("응급처치 게시물이 없습니다."));

        List<FirstAid> relatedFirstAids = firstAid.getTags().stream()
                .flatMap(tag -> firstAidRepository.findByTagNameAndNotId(tag.getName(), id).stream())
                .collect(Collectors.toList());

        FirstAidResponse firstAidResponse = convertToFirstAidResponse(firstAid);

        List<FirstAidResponse.RelatedFirstAidResponse> relatedResponses = relatedFirstAids.stream()
                .map(related -> new FirstAidResponse.RelatedFirstAidResponse(
                        related.getTitle(),
                        related.getTags().stream()
                                .map(tag -> new FirstAidResponse.TagResponse(tag.getName()))
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        firstAidResponse = firstAidResponse.toBuilder()
                .relatedFirstAids(relatedResponses)
                .build();

        return firstAidResponse;
    }

    private FirstAidResponse convertToFirstAidResponse(FirstAid firstAid) {
        List<FirstAidResponse.TagResponse> tagResponses = firstAid.getTags().stream()
                .map(tag -> new FirstAidResponse.TagResponse(tag.getName()))
                .collect(Collectors.toList());

        return FirstAidResponse.builder()
                .title(firstAid.getTitle())
                .emoji(firstAid.getEmoji())
                .content(firstAid.getContent())
                .tags(tagResponses)
                .build();
    }
    private String getImageUrlFromEmoji(String emoji) {
        String imageName;

        switch (emoji) {
            case "emoji_1":
                imageName = "cryingface.svg";
                break;
            case "emoji_2":
                imageName = "dizzyface.svg";
                break;
            case "emoji_3":
                imageName = "happyface.svg";
                break;
            case "emoji_4":
                imageName = "inconcenientface.svg";
                break;
            case "emoji_5":
                imageName = "marskface.svg";
                break;
            case "emoji_6":
                imageName = "shockedface.svg";
                break;
            case "emoji_7":
                imageName = "worriedface.svg";
                break;
            default:
                return null;
        }

        return s3Service.getImageUrl(imageName).toString();
    }
}
