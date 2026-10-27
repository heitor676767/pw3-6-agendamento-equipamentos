package com.enzoHeitor.agendamento.equipamentos.controller;

import com.enzoHeitor.agendamento.equipamentos.entity.ReservaArmario;
import com.enzoHeitor.agendamento.equipamentos.service.ReservaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
@CrossOrigin("*")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ReservaArmario> listar() {
        return service.listar();
    }

    @GetMapping("/ativas")
    public List<ReservaArmario> listarAtivas() {
        return service.listarAtivas();
    }

    @GetMapping("/{id}")
    public ReservaArmario buscar(@PathVariable Integer id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/reservar")
    public ResponseEntity<ReservaArmario> criar(@RequestBody ReservaArmario reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(reserva));
    }

    @PutMapping("/{id}")
    public ReservaArmario atualizar(@PathVariable Integer id, @RequestBody ReservaArmario reserva) {
        return service.atualizar(id, reserva);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer id) {
        service.excluir(id);
    }

    @PatchMapping("/{id}/finalizar")
    public ReservaArmario finalizar(@PathVariable Integer id) {
        return service.finalizar(id);
    }
}
