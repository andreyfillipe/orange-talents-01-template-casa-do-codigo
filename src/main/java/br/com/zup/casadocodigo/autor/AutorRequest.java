package br.com.zup.casadocodigo.autor;

import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    @UniqueValue(domainClass = Autor.class, fieldName = "email", message = "Já existe autor com este email cadastrado")
    private String email;
    @NotBlank(message = "A descrição é obrigatório")
    @Size(max = 400, message = "A descrição tem que conter no máximo 400 caracteres")
    private String descricao;

    public AutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public Autor toModel() {
        return new Autor(this.nome, this.email, this.descricao);
    }
}
