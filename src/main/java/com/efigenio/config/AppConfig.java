package com.efigenio.config;

import com.efigenio.Main;

import freemarker.template.Configuration;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinFreemarker;

public class AppConfig {

    public Javalin iniciaApp() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_34);
        cfg.setClassForTemplateLoading(Main.class, "/templates");
        cfg.setDefaultEncoding("UTF-8");

        Javalin app = Javalin.create(config -> {
            config.staticFiles.add("/public", Location.CLASSPATH);

            config.staticFiles.add(staticFiles -> {
                staticFiles.hostedPath = "/uploads";                    // URL pública
                staticFiles.directory = "src/main/resources/uploads";   // caminho no servidor
                staticFiles.location = Location.EXTERNAL;
            });
            config.fileRenderer(new JavalinFreemarker(cfg));
        }).start(7000);

        return app;
    }
}
