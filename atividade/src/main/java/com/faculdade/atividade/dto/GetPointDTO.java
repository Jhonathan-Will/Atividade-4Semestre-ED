package com.faculdade.atividade.dto;

import jakarta.validation.constraints.NotEmpty;

public record GetPointDTO(

    @NotEmpty( message = "Value is empty") String value

) {}
