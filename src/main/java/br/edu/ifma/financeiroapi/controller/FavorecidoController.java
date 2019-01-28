package br.edu.ifma.financeiroapi.controller;

import br.edu.ifma.financeiroapi.domain.Favorecido;
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
@RequestMapping("/favorecidos")
public class FavorecidoController {


    @Autowired
    private FavorecidoService favorecidoService;

    @RequestMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      method = RequestMethod.GET)
    public List<Favorecido> listar(){
        return this.favorecidoService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favorecido> buscar(@PathVariable("id") Long id){
        try{
            Optional<Favorecido> favorecidoOptional = this.favorecidoService.buscarPorID(id);
            return ResponseEntity.ok(favorecidoOptional.get());
        }catch (ResponseStatusException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> adicionar(@Valid @RequestBody Favorecido favorecido){
        try{
            Favorecido favorecidoCriado = this.favorecidoService.criar(favorecido);
            return  ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> atualizar(
      @PathVariable("id") Long id,
      @Valid @RequestBody Favorecido favorecido
    ){
        try{
            Favorecido favorecidoRetorno = this.favorecidoService.atualizar(favorecido, id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }catch (ResponseStatusException e){
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> apagar(@PathVariable("id") Long id){
        if(this.favorecidoService.remover(id)){
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
