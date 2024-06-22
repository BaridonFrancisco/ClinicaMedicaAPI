package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Component
public class HorarioAtencion implements ValidarConsulta {
    @Override
    public void validar(DatosAgendarConsulta datosAgendarConsulta) {

        var dia = datosAgendarConsulta.fecha().getDayOfWeek().equals(DayOfWeek.SUNDAY);

        // el horario debe estar entre las 7 y las 19
        LocalTime horarioInicio = LocalTime.of(7, 0);
        LocalTime horarioFin = LocalTime.of(19, 59);

        //isBefore
        //isAfter

        var condicion=datosAgendarConsulta.fecha().toLocalTime().isAfter(horarioInicio)
                &&  datosAgendarConsulta.fecha().toLocalTime().isBefore(horarioFin);


        if (dia || !condicion) {
            throw new RuntimeException("Horario no valido");
        }
    }
}
