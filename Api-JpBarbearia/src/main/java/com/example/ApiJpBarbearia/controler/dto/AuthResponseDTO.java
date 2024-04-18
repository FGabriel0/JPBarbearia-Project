package com.example.ApiJpBarbearia.controler.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
	
	 @JsonProperty("email")
	 private String email;

	 @JsonProperty("nome")
	 private String nome;

    @JsonProperty("access_token")
	private String accessToken;
    
    @JsonProperty("expiresIn")
    private Integer expiresIn; 
        
    @JsonProperty("refresh_token")
    private String refreshToken;

   
}
