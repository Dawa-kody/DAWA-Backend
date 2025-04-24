package com.kody.dawa.domain.user.presentation.dto.request;

import com.kody.dawa.domain.user.enums.Gender;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateRequest {
    @NotBlank
    private String schoolNumber;
    @NotBlank
    private String name;
    @NotBlank
    private Gender gender;
    @NotBlank
    private String email;
}
