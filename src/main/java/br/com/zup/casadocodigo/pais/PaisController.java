package br.com.zup.casadocodigo.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisRepository paisRepository;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid PaisRequest request) {
        Pais pais = request.toModel();
        paisRepository.save(pais);

        return ResponseEntity.ok().build();
    }
}
