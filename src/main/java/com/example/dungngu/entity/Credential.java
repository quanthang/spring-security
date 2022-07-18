package com.example.dungngu.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Credential {
    private  String AccessToken;
    private String RefreshToken;
    private long expiredAt;
    private String scope;
}
