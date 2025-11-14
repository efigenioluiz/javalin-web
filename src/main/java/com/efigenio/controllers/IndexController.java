package com.efigenio.controllers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;

import com.efigenio.config.AppConfig;
import com.efigenio.models.Pessoa;
import com.efigenio.repositories.PessoaRepository;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import freemarker.template.Template;
import io.javalin.http.Handler;

public class IndexController {
    private String caminhoArquivo = "arquivo.txt";

    public Handler selectBox = ctx -> {

        // PessoaRepository pessoaRepository = new PessoaRepository();

        Pessoa pessoa = new Pessoa("Efigenio", 25);
        Pessoa pessoa2 = new Pessoa("Maria", 30);
        Pessoa pessoa3 = new Pessoa("João", 28);

        Map<String, Pessoa> pessoas = new HashMap<>();
        pessoas.put(pessoa.getNome(), pessoa);
        pessoas.put(pessoa2.getNome(), pessoa2);
        pessoas.put(pessoa3.getNome(), pessoa3);

        System.out.println(pessoas);

        ctx.render("selectbox.ftl", Map.of("pessoas", pessoas));
    };

    public Handler getRelatorio = ctx -> {

        Template template = AppConfig.freemarkerConfiguration.getTemplate("relatorio.ftl");
        StringWriter writer = new StringWriter();

        template.process(Map.of("message", "olá"), writer);
        String html = writer.toString();

        ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(html, null);
        builder.toStream(pdfStream);
        builder.run();

        ctx.contentType("application/pdf");
        ctx.header("Content-Disposition", "attachment; filename=relatorio.pdf");
        ctx.result(new ByteArrayInputStream(pdfStream.toByteArray()));
    };

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
