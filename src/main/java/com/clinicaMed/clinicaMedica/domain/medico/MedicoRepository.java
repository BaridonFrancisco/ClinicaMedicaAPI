package com.clinicaMed.clinicaMedica.domain.medico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {

    Page<Medico> findByActivoTrue(Pageable paginacion);

    @Query("""
       select m from Medico m
       where m.activo=true
       and
       m.especialidad=:especialidad
       and
       m.id not in(
           select c.medico.id from Consulta c
           where
           c.data=:fecha
       )
       order by rand()
       limit 1
       """)
    Medico seleccionarMedicoConEspecialidad(@Param("especialidad") Especialidad especialidad,@Param("fecha") LocalDateTime fecha);

    @Query("SELECT m.activo FROM Medico m WHERE m.id=:idMedico")
    Boolean buscarEstadoMedico(Long idMedico);
}
