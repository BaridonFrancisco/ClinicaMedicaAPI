package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.medico.MedicoRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

public class MedicoActivo {

    @Autowired
    MedicoRepository medicoRepository;

    public void validar(DatosAgendarConsulta  datosAgendarConsulta){
        if(datosAgendarConsulta.idMedico()==null){
            return;
        }
        var comprobarEstado=medicoRepository.buscarEstadoMedico(datosAgendarConsulta.idMedico());
        if(comprobarEstado){
            throw new ValidacionConsultaException("El medico no se encuentra disponible esta inactivo");
        }

    }
}
