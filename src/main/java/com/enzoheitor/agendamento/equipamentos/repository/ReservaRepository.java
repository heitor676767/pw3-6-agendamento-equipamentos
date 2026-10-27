package com.enzoHeitor.agendamento.equipamentos.repository;

import com.enzoHeitor.agendamento.equipamentos.entity.ReservaArmario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<ReservaArmario, Integer> {

    List<ReservaArmario> findByAtivoTrue();

    @Query("""
        select count(r) > 0 from ReservaArmario r
        where r.numeroArmario = :numero
          and r.ativo = true
          and r.id <> :idIgnorado
          and r.dataInicio <= :fim
          and r.dataFim >= :inicio
        """)
    boolean existeConflito(@Param("numero") Integer numero,
                           @Param("inicio") LocalDate inicio,
                           @Param("fim") LocalDate fim,
                           @Param("idIgnorado") Integer idIgnorado);
}
