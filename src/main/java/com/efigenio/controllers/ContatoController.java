package com.efigenio.controllers;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.StringWriter;
import java.nio.file.StandardCopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Map;

import org.apache.batik.dom.GenericDOMImplementation;
import org.apache.batik.svggen.SVGGraphics2D;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.w3c.dom.Document;

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

    public Handler grafico = ctx -> {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.addValue(150, "Vendas", "Jan");
        dataset.addValue(200, "Vendas", "Fev");
        dataset.addValue(2000, "Vendas", "Mar");

        // Criar gráfico com JFreeChart
        JFreeChart chart = ChartFactory.createBarChart(
                "Vendas Mensais",
                "Mês",
                "Vendas",
                dataset);

        // Gera Gráfico de Pizza
        // JFreeChart newChart = ChartFactory.createPieChart(
        // "Distribuição de Vendas",
        // null,
        // true,
        // true,
        // false);

        // Gerar o gráfico como SVG
        String svgContent = geradorSVG(chart);

        // Enviar o SVG para o template
        ctx.render("grafico.ftl", Map.of("graficoSvg", svgContent));
    };

    private static String geradorSVG(JFreeChart chart) {
        try {
            // Cria documento SVG é um documento por TAG
            String svgNS = "http://www.w3.org/2000/svg";
            Document document = GenericDOMImplementation.getDOMImplementation()
                    .createDocument(svgNS, "svg", null);

            SVGGraphics2D svgGenerator = new SVGGraphics2D(document);

            int width = 600;
            int height = 400;

            // Obrigatório: define tamanho do canvas no SVG
            svgGenerator.setSVGCanvasSize(new java.awt.Dimension(width, height));

            chart.draw(svgGenerator, new java.awt.geom.Rectangle2D.Double(0, 0, width, height));

            StringWriter writer = new StringWriter();
            svgGenerator.stream(writer, true);

            // Adiciona width e height ao <svg>
            String svg = writer.toString();
            svg = svg.replace("<svg", "<svg width=\"" + width + "\" height=\"" + height + "\"");

            return svg;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

}
