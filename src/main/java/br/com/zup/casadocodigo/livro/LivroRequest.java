package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.autor.AutorRepository;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.categoria.CategoriaRepository;
import br.com.zup.casadocodigo.validacao.beanValidations.ExistsId;
import br.com.zup.casadocodigo.validacao.beanValidations.UniqueValue;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

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

    public LivroRequest(@NotBlank String titulo,
                        @NotBlank @Size(max = 500) String resumo,
                        @NotBlank String sumario,
                        @NotNull @Min(20) BigDecimal preco,
                        @NotNull @Min(100) Integer numeroPaginas,
                        @NotBlank String isbn,
                        @NotNull Long categoriaId,
                        @NotNull Long autorId) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.categoriaId = categoriaId;
        this.autorId = autorId;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Livro toModel(CategoriaRepository categoriaRepository, AutorRepository autorRepository) {
        Optional<Categoria> categoria = categoriaRepository.findById(this.categoriaId);
        Optional<Autor> autor = autorRepository.findById(this.autorId);

        Assert.state(categoria.isPresent(), "Categoria não encontrada");
        Assert.state(autor.isPresent(), "Autor não encontrado");

        return new Livro(this.titulo,
                         this.resumo,
                         this.sumario,
                         this.preco,
                         this.numeroPaginas,
                         this.isbn,
                         this.dataPublicacao,
                         categoria.get(),
                         autor.get());
    }
}
