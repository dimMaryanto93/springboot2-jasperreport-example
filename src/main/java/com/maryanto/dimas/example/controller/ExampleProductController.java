package com.maryanto.dimas.example.controller;

import com.maryanto.dimas.example.dto.ExampleDto;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/api/report/jasper")
public class ExampleProductController {

    private static List<ExampleDto.Product> products = new ArrayList<>();

    static {
        products.addAll(Arrays.asList(
                new ExampleDto.Product(
                        UUID.randomUUID().toString(),
                        "Apple Macbook Pro 13\" 2017 (Touchbar)",
                        new BigDecimal(25_000_000),
                        2L,
                        ""
                ),
                new ExampleDto.Product(
                        UUID.randomUUID().toString(),
                        "Ipad Pro 11\" 2018 (Celular + Wifi) 64gb",
                        new BigDecimal(15_000_000),
                        1L,
                        ""
                ),
                new ExampleDto.Product(
                        UUID.randomUUID().toString(),
                        "Iphone 7 Plus (32 gb)",
                        new BigDecimal(8_000_000),
                        1L,
                        ""
                ))
        );
    }

    @GetMapping("/product-list")
    public void productList(HttpServletResponse response) throws JRException, IOException {
        InputStream jasperStream = this.getClass().getResourceAsStream("/report/jasper/report-example.jasper");
        Map<String, Object> params = new HashMap<>();

        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JRBeanCollectionDataSource(products));
        response.setContentType("application/x-pdf");
        response.setHeader("Content-disposition", "inline; filename=products-list.pdf");

        final OutputStream outStream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);
    }
}
