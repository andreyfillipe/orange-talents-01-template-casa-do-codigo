package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.validacao.beanValidations.ExistsId;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PagamentoRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String documento;
    @NotBlank
    private String endereco;
    @NotBlank
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String telefone;
    @NotBlank
    private String cep;
    @ExistsId(domainClass = Estado.class, fieldName = "id")
    private Long estadoId;
    @NotNull
    @ExistsId(domainClass = Pais.class, fieldName = "id")
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

    public boolean validarDocumento() {
        Assert.hasLength(documento, "Não realizar validação para documento null");

        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public Long getPaisId() {
        return paisId;
    }
}
