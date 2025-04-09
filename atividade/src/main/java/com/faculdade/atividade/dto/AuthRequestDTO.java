package com.faculdade.atividade.dto;

import jakarta.validation.constraints.NotEmpty;

public record AuthRequestDTO(

    @NotEmpty( message = "Token is empity")    String email,
    @NotEmpty( message = "Passowrd is empty")  String password) { }
