package com.efigenio.repositories;

import com.efigenio.DAOs.PessoaDAOImpl;
import com.efigenio.models.Pessoa;

public class PessoaRepository {
    private PessoaDAOImpl pessoaDAO;
    // private AgendaDAOImpl agendaDAO;

    public PessoaRepository() {
        this.pessoaDAO = new PessoaDAOImpl();
        // this.agendaDAO = new AgendaDAOImpl();
    }

    public void salvarPessoa(Pessoa pessoa) {

        pessoa = this.pessoaDAO.criar(pessoa);
        
        // agendaDAO.criarAgendaParaPessoa(pessoa.getId());

    }

}
