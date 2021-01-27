package br.com.zup.casadocodigo.validacao.beanValidations;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.pagamento.PagamentoRequest;
import br.com.zup.casadocodigo.pais.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;

@Component
public class EstadoPertencePaisValidator implements Validator {

    @Autowired
    private EntityManager manager;

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
        Pais pais = manager.find(Pais.class, request.getPaisId());
        Estado estado = manager.find(Estado.class, request.getEstadoId());

        if (!estado.pertencePais(pais)) {
            errors.rejectValue("estadoId", null, "Estado não pertence ao país informado");
        }
    }
}
