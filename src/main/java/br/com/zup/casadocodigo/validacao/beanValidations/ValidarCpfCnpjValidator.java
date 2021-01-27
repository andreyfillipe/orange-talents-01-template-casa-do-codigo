package br.com.zup.casadocodigo.validacao.beanValidations;

import br.com.zup.casadocodigo.pagamento.PagamentoRequest;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ValidarCpfCnpjValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return PagamentoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if (errors.hasErrors()) {
           return;
        }
        PagamentoRequest request = (PagamentoRequest) o;
        if (!request.validarDocumento()) {
            errors.rejectValue("documento", null, "Documento CPF/CNPJ inválido");
        }
    }
}
