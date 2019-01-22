package com.andrelrs.cursomc.repositories;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    @Transactional(readOnly = true)
    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);

    //font: https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods
    //Se usarmos esse metodo abaixo não irá precisar usar o @Query com aquela consulta e nem os @Param, porque esse metodo ja faz essa consulta
    // internamente pelo o Spring data, porque o Spring irá gerar a consulta para a gente
    //Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
