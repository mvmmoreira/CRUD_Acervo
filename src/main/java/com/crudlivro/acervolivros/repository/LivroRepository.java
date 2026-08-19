package com.crudlivro.acervolivros.repository;

import com.crudlivro.acervolivros.model.Livro;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class LivroRepository {
    private Map<Long, Livro> livros = new HashMap<>();
    private Long proximoId = 1L;

    public Livro salvar(Livro livro){
        livro.setId(proximoId);
        livros.put(proximoId, livro);
        proximoId++;
        return livro;
    }

    public Livro buscarPorId(Long id){
        return livros.get(id);
    }

    public List<Livro> listarTodos(){
        return new ArrayList<>(livros.values());
    }

    public void deletar(Long id){
        livros.remove(id);

    }

    public Livro atualizar(Long id, Livro livroAtualizado){
        livroAtualizado.setId(id);
        livros.put(id, livroAtualizado);
        return livroAtualizado;
    }

    public boolean existeIsbn(String isbn){
        for(Livro livro : livros.values()){
            if (livro.getIsbn().equals(isbn)){
                return true;
            }
        }
        return false;
    }
}
