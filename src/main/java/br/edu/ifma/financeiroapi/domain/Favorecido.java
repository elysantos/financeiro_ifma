package br.edu.ifma.financeiroapi.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
public class Favorecido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @Size(min=11, max=14)
    @Column(unique = true, nullable = false, length = 14)
    private String documento;

    @NotNull
    @Column(nullable = false)
    private String razao;

    @NotNull
    @Column(nullable = false)
    private String fantasia;

    @Size(min=8, max = 16)
    private String telefone;

    private boolean tipo;

}
