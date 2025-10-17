package com.efigenio.models.interfaces;

import java.util.List;

import com.efigenio.models.Pessoa;

public interface PessoaDAO {
    void criar(Pessoa pessoa);
    List<Pessoa> getPessoas();

    Pessoa pessoaPorIdade(Integer idade);
}
