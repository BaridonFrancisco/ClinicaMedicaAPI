package com.clinicaMed.clinicaMedica.infra.errores;

public class ValidacionConsultaException extends RuntimeException {
    public ValidacionConsultaException() {
    }

    public ValidacionConsultaException(String message) {
        super(message);
    }

    public ValidacionConsultaException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidacionConsultaException(Throwable cause) {
        super(cause);
    }

    public ValidacionConsultaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
