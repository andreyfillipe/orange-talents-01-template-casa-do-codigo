package br.com.zup.casadocodigo.validacao.beanValidations;

import br.com.zup.casadocodigo.estado.Estado;
import br.com.zup.casadocodigo.estado.EstadoRepository;
import br.com.zup.casadocodigo.pagamento.PagamentoRequest;
import br.com.zup.casadocodigo.pais.Pais;
import br.com.zup.casadocodigo.pais.PaisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

@Component
public class EstadoPertencePaisValidator implements Validator {

    @Autowired
    private PaisRepository paisRepository;

    @Autowired
    private EstadoRepository estadoRepository;

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
        Pais pais = paisRepository.findById(request.getPaisId()).orElseThrow(() -> new IllegalArgumentException("País não encontrado"));
        Estado estado = estadoRepository.findById(request.getEstadoId()).orElseThrow(() -> new IllegalArgumentException("Estado não econtrado"));

        if (!estado.pertencePais(pais)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Estado não pertence ao país informado");
        }
    }
}
