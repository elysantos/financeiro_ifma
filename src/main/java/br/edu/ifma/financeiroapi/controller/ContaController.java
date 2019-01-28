package br.edu.ifma.financeiroapi.controller;

import br.edu.ifma.financeiroapi.domain.Conta;
import br.edu.ifma.financeiroapi.domain.Favorecido;
import br.edu.ifma.financeiroapi.service.ContaService;
import br.edu.ifma.financeiroapi.service.FavorecidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/contas")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @RequestMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
    public List<Conta> listar(){
        return this.contaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> buscar(@PathVariable("id") Long id){
        try{
            Optional<Conta> conta = this.contaService.buscarPorID(id);
            return ResponseEntity.ok(conta.get());
        }catch (ResponseStatusException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@Valid @RequestBody Conta conta){
        try{
            Conta contaCriada = this.contaService.criar(conta);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(
      @PathVariable("id") Long id,
      @Valid @RequestBody Conta conta
    ){
        try{
            Conta contaRetorno = this.contaService.atualizar(conta, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable("id") Long id){
        if(this.contaService.remover(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
