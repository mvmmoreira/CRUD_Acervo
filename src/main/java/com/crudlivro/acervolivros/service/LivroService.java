package com.crudlivro.acervolivros.service;

import com.crudlivro.acervolivros.exception.LivroJaExisteException;
import com.crudlivro.acervolivros.exception.LivroNaoEncontradoException;
import com.crudlivro.acervolivros.model.Livro;
import com.crudlivro.acervolivros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;


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

    public Livro buscar(Long id){
        Livro livro = repository.buscarPorId(id);
        if(livro == null){
            throw new LivroNaoEncontradoException("Livro não cadastrado");
        }
        return livro;
    }

    public List<Livro> listar(){
        return repository.listarTodos();
    }

    public void remover(Long id){
        Livro livro = repository.buscarPorId(id);
        if(livro == null){
            throw new LivroNaoEncontradoException("Livro não cadastrado");
        }
        repository.deletar(id);
    }


}
