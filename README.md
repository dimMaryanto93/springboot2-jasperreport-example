# Springboot 2 Jasper Report Integration

How to generate reporting with JasperReport Engine, system required:

- Make sure JasperReport Studio / IDE version same as dependency example:
    ```xml
    <dependency>
        <groupId>net.sf.jasperreports</groupId>
        <artifactId>jasperreports</artifactId>
        <version>6.12.2</version>
    </dependency>
    ```
- Penggunaan font dalam JasperReport sebisa mungkin gunakan fong yang general seperti `sans-sarif, arial, dan lain-lain`
- Jangan menggunakan query dalam JasperReport, supaya tidak di compile ulang karena memperlambat proses. Passing data menggunakan `JRBeanCollection` class