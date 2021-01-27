package br.com.zup.casadocodigo.pais;

import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(this.nome);
    }
}
