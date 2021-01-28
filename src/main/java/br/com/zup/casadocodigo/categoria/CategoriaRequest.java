package br.com.zup.casadocodigo.categoria;

import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank(message = "O nome é obrigadotório")
    @UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Já existe categoria com este nome cadastrado")
    private String nome;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public Categoria toModel() {
        return new Categoria(this.nome);
    }
}
