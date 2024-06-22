package com.clinicaMed.clinicaMedica.domain.consulta.validaciones;

import com.clinicaMed.clinicaMedica.domain.consulta.DatosAgendarConsulta;
import com.clinicaMed.clinicaMedica.domain.medico.MedicoRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivo implements ValidarConsulta {

    @Autowired
    MedicoRepository medicoRepository;
    @Override
    public void validar(DatosAgendarConsulta  datosAgendarConsulta){
        if(datosAgendarConsulta.idMedico()==null){
            return;
        }

        var medico=medicoRepository.findById(datosAgendarConsulta.idMedico())
                .orElse(null);

        if(medico!=null && medico.getActivo()){
            return;
        }
        throw new ValidacionConsultaException("no se puede agendar citas con Medicos inactivos o no existentes");




    }
}
