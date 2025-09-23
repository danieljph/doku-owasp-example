package com.doku.doku_owasp_example.xxe.controller;

import com.doku.doku_owasp_example.xxe.model.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/xxe")
@RestController
public class XxeSimulatorController
{
    private final XmlMapper xmlMapper; // Use Jackson version 2.7.3 to simulate vulnerability (com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.3)
    private final ObjectMapper objectMapper;

    /**
     * Due to different Jackson core version, we cannot use XmlMapper to convert an object to XML, that why we use JSON instead.
     */
    @PostMapping(value = "/simulate-on-jackson-xml-mapper", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String simulateOnJacksonXmlMapper(@RequestBody String requestBody) throws Exception
    {
        Note note = xmlMapper.readValue(requestBody, Note.class);
        note.setArchived(true);
        return objectMapper.writeValueAsString(note);
    }

    @PostMapping(value = "/simulate-on-javax-xml", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String simulateOnJavaxXml(@RequestBody String requestBody) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        ByteArrayInputStream input = new ByteArrayInputStream(requestBody.getBytes(StandardCharsets.UTF_8));
        Document doc = builder.parse(input);
        doc.getDocumentElement().normalize();

        String to = doc.getDocumentElement()
            .getElementsByTagName("to")
            .item(0)
            .getTextContent();

        String footer = doc.getDocumentElement()
            .getElementsByTagName("footer")
            .item(0)
            .getTextContent();

        Note note = new Note();
        note.setTo(to);
        note.setFooter(footer);
        note.setArchived(true);

        return objectMapper.writeValueAsString(note);
    }

    @SneakyThrows
    @PostMapping(value = "/simulate-apache-poi-vulnerability", produces = MediaType.TEXT_PLAIN_VALUE)
    public String simulateApachePoiVulnerability(@RequestParam("file") MultipartFile file)
    {
        Workbook wb = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = wb.getSheetAt(0);

        for(Row row : sheet)
        {
            for(Cell cell : row)
            {
                System.out.print(cell+"\t");
            }
            System.out.println();
        }

        return "Uploaded.";
    }

    @GetMapping(value = "/upload-data-from-evil-external-dtd")
    public String uploadDataFromEvilExternalDtd(@RequestParam("content") String content)
    {
        log.info("Uploaded Data from Evil External DTD: {}", content);
        return "<!ENTITY dummy \"DUMMY\">";
    }
}
