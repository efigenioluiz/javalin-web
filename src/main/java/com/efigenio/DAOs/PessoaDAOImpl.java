package com.efigenio.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.efigenio.database.FabricaDeConexao;
import com.efigenio.models.Pessoa;
import com.efigenio.models.interfaces.PessoaDAO;

public class PessoaDAOImpl implements PessoaDAO {

    private static Connection conexao;

    public PessoaDAOImpl() {
        conexao = FabricaDeConexao.getInstancia().getConexao();
    }

    @Override
    public void criar(Pessoa pessoa) {
        try {

            String query = "INSERT INTO Pessoa(nome,idade) VALUES(?,?)";

            PreparedStatement pstm = conexao.prepareStatement(query);

            pstm.setString(1, pessoa.getNome());
            pstm.setInt(2, pessoa.getIdade());

            pstm.execute();

            pstm.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Pessoa> getPessoas() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPessoas'");
    }

    @Override
    public Pessoa pessoaPorIdade(Integer idade) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'pessoaPorIdade'");
    }

}
