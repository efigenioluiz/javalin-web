package com.efigenio.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

import io.javalin.http.Handler;
import io.javalin.http.UploadedFile;

public class ContatoController {

    public Handler get = ctx -> {
        ctx.render("cadastro.ftl");
    };

    public Handler post = ctx -> {
        String nome = ctx.formParam("nome");
        String telefone = ctx.formParam("telefone");
        String email = ctx.formParam("email");

        UploadedFile inputFile = ctx.uploadedFile("file");

        Path outPath = null;

        // I will save locally the file
        if (inputFile != null) {
            String fileName = inputFile.filename();
            Path uploadDir = Path.of("uploads");
            System.out.println(uploadDir);

            Files.createDirectories(uploadDir);

            outPath = uploadDir.resolve(Paths.get(fileName).getFileName());

            try (BufferedInputStream bis = new BufferedInputStream(inputFile.content())) {
                Files.copy(bis, outPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        System.out.println(nome);
        System.out.println(telefone);
        System.out.println(email);
        String mensagem = "Contato: " + nome + "Cadastrado";

        String idade = "19";
        Integer idadeInt = Integer.parseInt(idade);

        // LocalDate localDate = LocalDate.now();
        // String dateStr = null;
        // SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        // Date date = (Date) sdf.parse(dateStr);

        ctx.render("visualizarContato.ftl", Map.of("imagem", outPath));
    };
}
