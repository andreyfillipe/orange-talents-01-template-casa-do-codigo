package br.com.zup.casadocodigo.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid AutorRequest request) {
        Autor autor = request.toModel();
        autorRepository.save(autor);

        return ResponseEntity.ok().build();
    }
}
