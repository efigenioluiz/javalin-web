package com.efigenio.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import javax.print.DocFlavor.STRING;

import com.efigenio.models.Pessoa;
import com.efigenio.repositories.PessoaRepository;

import io.javalin.http.Handler;

public class IndexController {
    private String caminhoArquivo = "arquivo.txt";

    public Handler get = ctx -> {
        PessoaRepository pessoaRepository = new PessoaRepository();
        gravarArquivoDeTexto();

        Pessoa pessoa = new Pessoa("Mitsugui", 16);

        pessoaRepository.salvarPessoa(pessoa);

        lerArquivoDeTexto();

        ctx.render("index.ftl");
    };

    public void lerArquivoDeTexto() throws FileNotFoundException {

        try {
            BufferedReader br = new BufferedReader(new FileReader(caminhoArquivo));

            String linha;

            while ((linha = br.readLine()) != null) {
                System.out.println(linha);
            }

            br.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void gravarArquivoDeTexto() {
        String conteudo = "menssagem do arquivo de texto";
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminhoArquivo));
            bw.write(conteudo);
            bw.newLine();

            bw.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
