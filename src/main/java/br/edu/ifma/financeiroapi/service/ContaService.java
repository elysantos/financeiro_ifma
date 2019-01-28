package br.edu.ifma.financeiroapi.service;

import br.edu.ifma.financeiroapi.domain.Conta;
import br.edu.ifma.financeiroapi.domain.Favorecido;
import br.edu.ifma.financeiroapi.repository.Contas;
import br.edu.ifma.financeiroapi.repository.Favorecidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private Contas contas;

    public List<Conta> listar(){
        return contas.findAll();
    }

    public Optional<Conta> buscarPorID(Long id) throws ResponseStatusException {
        Optional<Conta> itemOptional = this.contas.findById(id);
        if(itemOptional.isPresent()){
            return itemOptional;
        }
        throw  new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public Conta criar(Conta conta) throws ResponseStatusException {
        return this.contas.save(conta);
    }

    public Conta atualizar(Conta conta, Long id) throws ResponseStatusException{

        Optional<Conta> itemOptional = this.contas.findById(id);

        if(itemOptional.isPresent()){

            Conta antigo = itemOptional.get();
            conta.setId(antigo.getId());

            this.contas.save(conta);

            return conta;

        }else{
            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED,
              "Recurso não encontrado.");
        }
    }


    public boolean remover(Long id){
        Optional<Conta> conta = this.contas.findById(id);
        if(conta.isPresent()){
            this.contas.delete(conta.get());
            return true;
        }
        return false;
    }
}
