package br.com.zup.casadocodigo.livro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query(value = "select l from Livro l")
    Page<LivroProjecao> findLivros(Pageable paginacao);
}
