package org.sergio.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ConsoleHelper {
    private static BufferedReader reader;

    static {
        InputStream is = System.in;
        InputStreamReader isr = new InputStreamReader(is);
        reader = new BufferedReader(isr);
    }

    public static String readMessage() throws IOException {
        return reader.readLine().trim();
    }

    public static void writeMessage(String message) {
        System.out.println(message);
    }
}
