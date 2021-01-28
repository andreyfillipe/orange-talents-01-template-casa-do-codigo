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

    @NotBlank(message = "O título é obrigatório")
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo", message = "Já existe livro com este título cadastrado")
    private String titulo;
    @NotBlank(message = "O resumo é obrigatório")
    @Size(max = 500, message = "O resumo tem que conter no máximo 500 caracteres")
    private String resumo;
    @NotBlank(message = "O sumário é obrigatório")
    private String sumario;
    @NotNull(message = "O preço é obrigatório")
    @Min(value = 20, message = "O preço tem que conter no mínimo 20 caracteres")
    private BigDecimal preco;
    @NotNull(message = "O número de páginas é obrigatório")
    @Min(value = 100, message = "O número de páginas tem que conter no mínimo 100 caracteres")
    private Integer numeroPaginas;
    @NotBlank(message = "O isbn é obrigatório")
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn", message = "Já existe livro com este isbn cadastrado")
    private String isbn;
    @NotNull(message = "A data de publicação é obrigatório")
    @Future(message = "A data de publicação tem que ser maior que a data atual")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    @NotNull(message = "A categoria é obrigatório")
    @ExistsId(domainClass = Categoria.class, fieldName = "id", message = "A categoria informada não existe")
    private Long categoriaId;
    @NotNull(message = "O Autor é obrigatório")
    @ExistsId(domainClass = Autor.class, fieldName = "id", message = "O autor informado não existe")
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
