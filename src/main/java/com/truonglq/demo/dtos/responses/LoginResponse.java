package com.truonglq.demo.dtos.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginResponse {
    String token;
    @JsonProperty("expires_in")
    long expiresIn;

    @JsonProperty("token_type")
    String tokenType = "Bearer";
}
