package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.validacao.beanValidations.CpfCnpj;
import br.com.zup.casadocodigo.validacao.beanValidations.ExistsId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotBlank(message = "O email é obrigatório")
    @Email(message = "Formato de email inválido")
    private String email;
    @NotBlank(message = "O nome é obrigatório")
    private String nome;
    @NotBlank(message = "O sobrenome é obrigatório")
    private String sobrenome;
    @NotBlank(message = "O documento é obrigatório")
    @CpfCnpj(message = "CPF/CNPJ inválido")
    private String documento;
    @NotBlank(message = "O endereço é obrigatório")
    private String endereco;
    @NotBlank(message = "O complemento é obrigatório")
    private String complemento;
    @NotBlank(message = "A cidade é obrigatório")
    private String cidade;
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;
    @NotBlank(message = "O cep é obrigatório")
    private String cep;
    @ExistsId(domainClass = Estado.class, fieldName = "id", message = "O estado informado não existe")
    private Long estadoId;
    @NotNull(message = "O País é obrigatório")
    @ExistsId(domainClass = Pais.class, fieldName = "id", message = "O país informado não existe")
    private Long paisId;

    public PagamentoRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String cidade, @NotBlank String telefone, @NotBlank String cep, Long estadoId, @NotNull Long paisId) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.complemento = complemento;
        this.cidade = cidade;
        this.telefone = telefone;
        this.cep = cep;
        this.estadoId = estadoId;
        this.paisId = paisId;
    }

    public String getDocumento() {
        return documento;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Long getPaisId() {
        return paisId;
    }
}
