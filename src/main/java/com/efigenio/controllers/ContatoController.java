package com.efigenio.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

import io.javalin.http.Handler;

public class ContatoController {

    public Handler get = ctx -> {
        ctx.render("cadastro.ftl");
    };

    public Handler post = ctx -> {
        String nome = ctx.formParam("nome");
        String telefone = ctx.formParam("telefone");
        String email = ctx.formParam("email");

        System.out.println(nome);
        System.out.println(telefone);
        System.out.println(email);
        String mensagem = "Contato: "+ nome+ "Cadastrado";

        String idade= "19"; 
        Integer idadeInt = Integer.parseInt(idade);

        // LocalDate localDate = LocalDate.now();
        String  dateStr = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = (Date) sdf.parse(dateStr);

        ctx.render("visualizarContato.ftl",Map.of("mensagem",mensagem));
    };
}
