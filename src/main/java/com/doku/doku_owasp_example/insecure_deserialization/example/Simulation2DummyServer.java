package com.doku.doku_owasp_example.insecure_deserialization.example;

import lombok.SneakyThrows;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * @author Daniel Joi Partogi Hutapea
 */
public class Simulation2DummyServer extends Thread
{
    private final ServerSocket serverSocket;

    public Simulation2DummyServer() throws IOException
    {
        serverSocket = new ServerSocket(8915);
        System.out.println("Dummy server started.");
    }

    @SuppressWarnings({"DuplicatedCode", "InfiniteLoopStatement", "unchecked"})
    @SneakyThrows
    public void run()
    {
        while(true)
        {
            Socket connectionSocket = serverSocket.accept();

            Runnable runnable = () ->
            {
                try
                {
                    ObjectInputStream inFromClient = new ObjectInputStream(connectionSocket.getInputStream());
                    Map<String, String> request = (Map<String, String>) inFromClient.readObject();
                    request.put("new-value", "Malicious code will be executed if this line is executed."); // Adding new value to map will execute the malicious code.

                    System.out.println("Request received: " + request);
                }
                catch(Exception ex)
                {
                    ex.printStackTrace(System.err);
                }
                finally
                {
                    try
                    {
                        connectionSocket.close();
                    }
                    catch(IOException ex)
                    {
                        ex.printStackTrace(System.err);
                    }
                }
            };

            new Thread(runnable).start();
        }
    }

    public static void main(String[] args) throws IOException
    {
        new Simulation2DummyServer().start();
    }
}
