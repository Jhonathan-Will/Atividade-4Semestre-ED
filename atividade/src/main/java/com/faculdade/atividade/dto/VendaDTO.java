package com.faculdade.atividade.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record VendaDTO(
   @NotBlank(message = "Specify if sell was made in person or not") 
   @NotNull(message = "Specify if sell was made in person or not") boolean in_person,
    
   @NotBlank(message = "Confirm the value of this sell") 
   @NotEmpty(message = "Sell dont have value") float value,
    
   @NotEmpty(message = "Sell dont delivery who did") 
   @NotBlank(message = "Dont have confimation that who did this sell") Long id_seller
) {

}
