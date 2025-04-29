package com.kody.dawa.domain.user.presentation.dto.response;
import com.kody.dawa.domain.user.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponse {
    private String name;
    private Gender gender;
    private String schoolNumber;
}
