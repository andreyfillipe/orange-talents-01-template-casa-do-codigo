package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.autor.AutorRepository;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.categoria.CategoriaRepository;
import br.com.zup.casadocodigo.validacao.beanValidations.ExistsId;
import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;
    @NotBlank
    @Size(max = 500)
    private String resumo;
    @NotBlank
    private String sumario;
    @NotNull
    @Min(20)
    private BigDecimal preco;
    @NotNull
    @Min(100)
    private Integer numeroPaginas;
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;
    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long categoriaId;
    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long autorId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LivroRequest(@NotBlank String titulo,
                        @NotBlank @Size(max = 500) String resumo,
                        @NotBlank String sumario,
                        @NotNull @Min(20) BigDecimal preco,
                        @NotNull @Min(100) Integer numeroPaginas,
                        @NotBlank String isbn,
                        @Future @NotNull LocalDate dataPublicacao,
                        @NotNull Long categoriaId,
                        @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public Livro toModel(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        Categoria categoria = categoriaRepository.findById(this.categoriaId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Categoria não encontrada"));
        Autor autor = autorRepository.findById(this.autorId).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST ,"Autor não encontrado"));

        return new Livro(this.titulo,
                         this.resumo,
                         this.sumario,
                         this.preco,
                         this.numeroPaginas,
                         this.isbn,
                         this.dataPublicacao,
                         categoria,
                         autor);
    }
}
