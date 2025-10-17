package com.efigenio.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class FabricaDeConexao {
    Dotenv dotenv = Dotenv.load();

    private static FabricaDeConexao instancia;
    private String nomeBanco = dotenv.get("NOME_BANCO");
    private String usuario = dotenv.get("USUARIO");
    private String senha = dotenv.get("SENHA");
    private String destino = dotenv.get("DESTINO");
    private Integer porta = Integer.valueOf(dotenv.get("PORTA"));

    // Garante que ninguém mais consiga chamar o new
    private FabricaDeConexao() {
    }

    public static FabricaDeConexao getInstancia() {
        if (instancia == null) {
            instancia = new FabricaDeConexao();
        }
        return instancia;
    }

    public Connection getConexao() {
        try {
            String url = "jdbc:mysql://" + destino + ":" + porta + "/" + nomeBanco + "?user=" + usuario + "&password="
                    + senha;
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao tentar conectar com o banco de dados", e);
        }
    }

}
