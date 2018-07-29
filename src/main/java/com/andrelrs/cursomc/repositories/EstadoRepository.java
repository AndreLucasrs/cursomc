package com.andrelrs.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andrelrs.cursomc.domain.Categoria;
import com.andrelrs.cursomc.domain.Cidade;
import com.andrelrs.cursomc.domain.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}
