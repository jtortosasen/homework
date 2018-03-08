package util;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Joan Gerard
 */
public class IO {

    //.........................................................................
    //.........................................................................
    private static final int LF = 10; // line feed
    private static final int CR = 13; // carriage return
    private static final byte[] CR_LF = {CR, LF}; // array con 2 casillas: tiene CR y LF

    //.........................................................................
    //.........................................................................
    public static int copy(InputStream in, OutputStream out)
            throws IOException {
        int byteReaded;
        int numBytes = 0;
        while ((byteReaded = in.read()) != -1) {
            numBytes++;
            out.write(byteReaded);
        } // while
        return numBytes;
    } // ()

    public static void copy(InputStream in, OutputStream out, long size)
            throws IOException {
        for (int i = 0; i < size; i++) {
            out.write(in.read());
        }

    } // ()

    //.........................................................................
    //.........................................................................
    public static String getLine(InputStream in) throws IOException {


        ByteArrayOutputStream storedBytes = new ByteArrayOutputStream();
        int currentByte = 0;
        int prevByte = 0;

        // leo con read, guardo en unByte y comparo con -1
        while ((currentByte = in.read()) != -1) {

            if (currentByte == CR) {
                // si veo un CR, no lo pongo en el acumulador
                // pero lo guardo
                prevByte = CR; // == unByte
            } else if (prevByte == CR && currentByte == LF) {
                // si había visto un CR y ahora veo un LF
                // => he detectado un salto de lína
                // => termino el bucle
                break;

            } else if (prevByte == CR && currentByte != LF) {
                // si había visto un CR pero este no es LF
                // no es un salto de línea y
                // como no puse el CR, pongo ahora los dos
                storedBytes.write(prevByte); // que es CR
                storedBytes.write(currentByte); // que no es LF
            } else {
                // ninguno de los anteriores casos
                // pongo el byte leído
                storedBytes.write(currentByte);
                prevByte = currentByte;
            }

        } // while

        if (currentByte == -1) {
            throw new IOException("entrada cerrada");
        }

        // devuelvo los bytes acumulados, pasados a texto
        return storedBytes.toString();
    } // ()

    //.........................................................................
    //.........................................................................
    public static void sendLine(String line, OutputStream out) throws IOException {
        out.write(line.getBytes());
        out.write(CR_LF);
        // creo que no hace falta: salida.flush();
    } // ()
}
