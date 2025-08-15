package com.alura_cursos.ForoHub.infra.exception;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String mensaje) {
        super(mensaje);
    }
}
