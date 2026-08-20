package com.crudlivro.acervolivros.service;

import com.crudlivro.acervolivros.exception.LivroJaExisteException;
import com.crudlivro.acervolivros.model.Livro;
import com.crudlivro.acervolivros.repository.LivroRepository;
import org.springframework.stereotype.Service;


@Service
public class LivroService {
    private final LivroRepository repository;

    public LivroService(LivroRepository repository){
        this.repository = repository;
    }

    public Livro cadastar(Livro livro){
        if(repository.existeIsbn(livro.getIsbn())){
            throw new LivroJaExisteException("Impossivél cadastrar um livro existente");
        }

        return repository.salvar(livro);
    }
}
