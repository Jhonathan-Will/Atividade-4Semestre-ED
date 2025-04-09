package com.faculdade.atividade.dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthResponseDTO(  

    @NotEmpty(message = "Token is empty") String token) { }
