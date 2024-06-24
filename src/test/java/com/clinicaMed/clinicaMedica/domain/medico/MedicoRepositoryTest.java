package com.clinicaMed.clinicaMedica.domain.medico;

import com.clinicaMed.clinicaMedica.domain.consulta.Consulta;
import com.clinicaMed.clinicaMedica.domain.direccion.DatosDireccion;
import com.clinicaMed.clinicaMedica.domain.direccion.Direccion;
import com.clinicaMed.clinicaMedica.domain.paciente.Paciente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;


    /*con TesetEntityManager spring se encarga de crear el entityManager sin necesidad
    * de tener que instanciarlo nosotros*/
    @Autowired
    private TestEntityManager entityManager;



    @Test
    @DisplayName("debe retornar nulo cuando el medico tenga un paciente el horario establecido")
    void seleccionarMedicoConEspecialidad() {
        LocalDateTime proximoLunes=LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        Medico medico=registrarMedico("Juan Gulberto","test1@gmail.com","2214123","100001",Especialidad.CARDIOLOGIA,
                new Direccion("x","101","no","norte","Buenos aires"));

        Paciente paciente=registrarPaciente("Maria stronggerweis","test2@gmail.com","221567","100002",new Direccion("x2","1002","no","sur","Berriso"));

        registrartConsulta(medico,paciente,proximoLunes);

        var medicoLibre=medicoRepository.seleccionarMedicoConEspecialidad(Especialidad.CARDIOLOGIA,proximoLunes);
        System.out.println(medicoLibre);

        Assertions.assertNull(medicoLibre);

    }

    @Test
    @DisplayName("debe retornar un medico que no sea nulo e igual al instanciado para ese horario")
    void seleccionarMedicoConEspecialidad2() {
        LocalDateTime proximoLunes=LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);

        Medico medico=registrarMedico("Juan Gulberto","test1@gmail.com","2214123","100001",Especialidad.CARDIOLOGIA,
                new Direccion("x","101","no","norte","Buenos aires"));

        
        var medicoLibre=medicoRepository.seleccionarMedicoConEspecialidad(Especialidad.CARDIOLOGIA,proximoLunes);
        System.out.println(medicoLibre);

        Assertions.assertNotNull(medicoLibre);
        Assertions.assertEquals(medicoLibre,medico);
    }




    private void registrartConsulta(Medico medico,Paciente paciente,LocalDateTime fechaConsulta) {
        Consulta consulta=new Consulta(null,medico,paciente,fechaConsulta);
        entityManager.persist(consulta);
    }
    private Medico registrarMedico(String nombre, String email, String telefono,String documento,Especialidad especialidad, Direccion direccion){
        Medico med=new Medico(new DatosMedicos(nombre,email,documento,telefono,especialidad,new DatosDireccion(direccion.getCalle(),direccion.getDistrito(),direccion.getCiudad(),direccion.getNumero(),direccion.getComplemento())));
        System.out.println(med);
        entityManager.persist(med);
        return med;
    }
    private Paciente registrarPaciente(String nombre,String email,String telefono,String documento,Direccion direccion){
        Paciente pa=new Paciente(null,nombre,email,telefono,documento,direccion,true);
        entityManager.persist(pa);
        return pa;
    }
}