package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.autor.Autor;
import br.com.zup.casadocodigo.categoria.Categoria;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String titulo;
    @NotBlank
    @Size(max = 500)
    @Column(nullable = false, length = 500)
    private String resumo;
    @NotBlank
    @Column(nullable = false)
    private String sumario;
    @NotNull
    @Min(20)
    @Column(nullable = false)
    private BigDecimal preco;
    @NotNull
    @Min(100)
    @Column(nullable = false)
    private Integer numeroPaginas;
    @NotBlank
    @Column(nullable = false, unique = true)
    private String isbn;
    @Future
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    @Column(nullable = false)
    private LocalDate dataPublicacao;

    @NotNull
    @ManyToOne
    private Categoria categoria;
    @NotNull
    @ManyToOne
    private Autor autor;

    @Deprecated
    public Livro() {
    }

    public Livro(@NotBlank String titulo,
                 @NotBlank @Size(max = 500) String resumo,
                 @NotBlank String sumario,
                 @NotNull @Min(20) BigDecimal preco,
                 @NotNull @Min(100) Integer numeroPaginas,
                 @NotBlank String isbn,
                 @Future @NotNull LocalDate dataPublicacao,
                 @NotNull @Valid Categoria categoria,
                 @NotNull @Valid Autor autor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroPaginas = numeroPaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
        this.categoria = categoria;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroPaginas() {
        return numeroPaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public Autor getAutor() {
        return autor;
    }
}
