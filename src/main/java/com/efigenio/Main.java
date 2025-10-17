package com.efigenio;

import com.efigenio.DAOs.PessoaDAOImpl;
import com.efigenio.config.AppConfig;
import com.efigenio.database.FabricaDeConexao;
import com.efigenio.models.Pessoa;
import com.efigenio.routes.Rotas;

import io.github.cdimascio.dotenv.Dotenv;

public class Main {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        Rotas rotas = new Rotas();
        AppConfig appConfig = new AppConfig();

        rotas.registraRotas(appConfig.iniciaApp());

        Pessoa pessoa = new Pessoa("Julio Cezar", 16);

        PessoaDAOImpl pessoaDAO = new PessoaDAOImpl();

        pessoaDAO.criar(pessoa);
    }
}