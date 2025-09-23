package com.doku.doku_owasp_example.insecure_deserialization.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * @author Daniel Joi Partogi Hutapea
 */
@Setter @Getter @NoArgsConstructor
public class MyData implements Serializable
{
    private static final long serialVersionUID = 1L;

    private int id;
    private String name;

    private void readObject(ObjectInputStream in)
    {
        Thread.dumpStack();
        System.out.println("readObject executed...");

        try
        {
            in.defaultReadObject(); // Deserialize data.
            System.out.println("Unsafe code...");
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public String toString()
    {
        return "MyData{" + "id=" + id + ", name='" + name + '\'' + '}';
    }
}
