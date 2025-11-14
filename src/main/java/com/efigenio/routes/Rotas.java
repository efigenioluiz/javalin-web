package com.efigenio.routes;

import com.efigenio.controllers.ContatoController;
import com.efigenio.controllers.IndexController;

import io.javalin.Javalin;

public class Rotas {
    private ContatoController contatoController;
    private IndexController indexController;

    public Rotas() {
        this.contatoController = new ContatoController();
        this.indexController = new IndexController();
    }

    public void registraRotas(Javalin app) {

        app.get("/", indexController.get);
        app.get("/cadastro", contatoController.get);
        app.post("/contatos", contatoController.post);
        app.get("/relatorio", indexController.getRelatorio);
        app.get("/grafico", contatoController.grafico);

        app.get("/select", indexController.selectBox);
    }

}
