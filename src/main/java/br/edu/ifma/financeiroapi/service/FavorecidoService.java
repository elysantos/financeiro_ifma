package br.edu.ifma.financeiroapi.service;

import br.edu.ifma.financeiroapi.domain.Favorecido;
import br.edu.ifma.financeiroapi.repository.Favorecidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class FavorecidoService {

    @Autowired
    private Favorecidos favorecidos;

    public List<Favorecido> listar(){
        return favorecidos.findAll();
    }

    public Optional<Favorecido> buscarPorID(Long id) throws  ResponseStatusException{
        Optional<Favorecido> favorecidoOptional = this.favorecidos.findById(id);
        if(favorecidoOptional.isPresent()){
            return favorecidoOptional;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Favorecido criar(Favorecido favorecido) throws ResponseStatusException {
        Optional<Favorecido> favorecidoEncontrado;
        favorecidoEncontrado = this.favorecidos.findByDocumento(favorecido.getDocumento());

        if(favorecidoEncontrado.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
              "Recurso já existente.");
        }
        return this.favorecidos.save(favorecido);
    }

    public Favorecido atualizar(Favorecido favorecido, Long id) throws ResponseStatusException{

        Optional<Favorecido> favorecidoOptional = this.favorecidos.findById(id);

        if(favorecidoOptional.isPresent()){

            Favorecido antigo = favorecidoOptional.get();
            favorecido.setId(antigo.getId());

            this.favorecidos.save(favorecido);

            return favorecido;

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,
              "Recurso não encontrado.");
        }
    }


    public boolean remover(Long id){
        Optional<Favorecido> favorecido = this.favorecidos.findById(id);
        if(favorecido.isPresent()){
            this.favorecidos.delete(favorecido.get());
            return true;
        }
        return false;
    }
}
