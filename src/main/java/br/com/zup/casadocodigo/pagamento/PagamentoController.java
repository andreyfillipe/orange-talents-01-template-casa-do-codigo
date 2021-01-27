package br.com.zup.casadocodigo.pagamento;

import br.com.zup.casadocodigo.validacao.beanValidations.EstadoPertencePaisValidator;
import br.com.zup.casadocodigo.validacao.beanValidations.ValidarCpfCnpjValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

    @Autowired
    private ValidarCpfCnpjValidator validarCpfCnpjValidator;

    @Autowired
    private EstadoPertencePaisValidator estadoPertencePaisValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(validarCpfCnpjValidator, estadoPertencePaisValidator);
    }

    @PostMapping
    public ResponseEntity<Void> pagamento(@RequestBody @Valid PagamentoRequest request) {
        //salvar pagamento
        return ResponseEntity.ok().build();
    }
}
