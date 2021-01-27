package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.AutorRepository;
import br.com.zup.casadocodigo.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private AutorRepository autorRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<Void> salvar(@RequestBody @Valid LivroRequest request) {
        Livro livro = request.toModel(categoriaRepository, autorRepository);
        livroRepository.save(livro);

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public Page<LivroProjecao> listar(@PageableDefault(sort = "titulo", direction = Sort.Direction.ASC, page = 0, size = 100) Pageable paginacao) {
        return livroRepository.findLivros(paginacao);
    }

    @GetMapping("{id}")
    public ResponseEntity<LivroDetalhe> listarPorId(@PathVariable Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        if (!livro.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new LivroDetalhe(livro.get()));
    }
}
