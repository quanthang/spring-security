package com.example.dungngu.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CredentialTto {
    private String accessToken;
    private String refreshToken;
    private long expiredAt;
    private String scope;
}
