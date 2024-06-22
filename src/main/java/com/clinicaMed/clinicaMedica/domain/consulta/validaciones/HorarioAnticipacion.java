package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
@Component
public class HorarioAnticipacion implements ValidarConsulta {
    @Override
    public void validar(DatosAgendarConsulta datosAgendarConsulta){

        LocalDateTime ahora=LocalDateTime.now();
        LocalDateTime horarioConsulta=datosAgendarConsulta.fecha();

        //diferencia de 30 minutos
        var diff= Duration.between(ahora,horarioConsulta).toMinutes()<30;

        if(diff){
            throw new ValidacionConsultaException("Las consultas deben programarse con 30 minutos de anticipacion");
        }



    }


}
