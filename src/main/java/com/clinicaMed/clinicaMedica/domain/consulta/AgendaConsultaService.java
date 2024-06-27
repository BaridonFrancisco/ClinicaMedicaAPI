package com.clinicaMed.clinicaMedica.domain.consulta;

import com.clinicaMed.clinicaMedica.domain.consulta.validaciones.ValidarConsulta;
import com.clinicaMed.clinicaMedica.domain.medico.Medico;
import com.clinicaMed.clinicaMedica.domain.medico.MedicoRepository;
import com.clinicaMed.clinicaMedica.domain.paciente.PacienteRepository;
import com.clinicaMed.clinicaMedica.infra.errores.ValidacionConsultaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private PacienteRepository pacienteRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private List<ValidarConsulta> listaValidaciones;

    public DatosDetalleConsulta agendar(DatosAgendarConsulta datosValidar){

        listaValidaciones.forEach(v-> v.validar(datosValidar));
        var paciente=pacienteRepository.findById(datosValidar.idPaciente())
                .orElseThrow(RuntimeException::new);
        
        var medico=seleccionarMedico(datosValidar);
        if(medico==null)throw new ValidacionConsultaException("No se ha podido seleciona un medico");
        var consultaCreada=new Consulta(null,medico,paciente,datosValidar.fecha());
        consultaRepository.save(consultaCreada);

        return new DatosDetalleConsulta(consultaCreada);

    }


    private Medico seleccionarMedico(DatosAgendarConsulta datosAgendarConsulta) {
        if(datosAgendarConsulta.idMedico()!=null){
            return medicoRepository.getReferenceById(datosAgendarConsulta.idMedico());
        }
        return medicoRepository.seleccionarMedicoConEspecialidad(datosAgendarConsulta.especialidad(),datosAgendarConsulta.fecha());

    }

    public void removerConsulta(DatosAgendarConsulta datosAgendarConsulta){
        var existeConsulta=consultaRepository.existsById(datosAgendarConsulta.id());
        if(!existeConsulta){
            throw new RuntimeException("La consulta no existe");
        }
        listaValidaciones.forEach(v->v.validar(datosAgendarConsulta));
        consultaRepository.deleteById(datosAgendarConsulta.id());
    }

}
