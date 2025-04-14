package com.faculdade.atividade.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record VendaDTO(
   @NotNull(message = "Specify if sell was made in person or not") Boolean in_person,
    
   @NotNull(message = "Confirm the value of this sell")
   @Positive(message = "Sell value must be greater than 0") Float value

) {

}
