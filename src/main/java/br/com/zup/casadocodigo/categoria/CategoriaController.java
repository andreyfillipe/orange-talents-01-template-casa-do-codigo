package br.com.zup.casadocodigo.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid CategoriaRequest request) {
        Categoria categoria = request.toModel();
        categoriaRepository.save(categoria);

        return ResponseEntity.ok().build();
    }
}
