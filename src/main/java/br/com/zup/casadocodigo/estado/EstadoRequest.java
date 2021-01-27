package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisRepository;
import br.com.zup.casadocodigo.validacao.beanValidations.ExistsId;
import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = Estado.class, fieldName = "nome")
    private String nome;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
    private Long paisId;

    public EstadoRequest(@NotBlank String nome, @NotNull Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public Estado toModel(PaisRepository paisRepository) {
        Pais pais = paisRepository.findById(this.paisId).get();
        return new Estado(this.nome, pais);
    }
}
