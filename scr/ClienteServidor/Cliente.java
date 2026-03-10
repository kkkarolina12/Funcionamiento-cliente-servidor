package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Cliente {

    public static void main(String[] args) {
        // Dirección del servidor
        final String HOST = "127.0.0.1";

        // Puerto del servidor
        final int PUERTO = 5000;

        try (
            // El cliente se conecta al servidor
            Socket socket = new Socket(HOST, PUERTO);

            // Flujo para escribir desde teclado
            BufferedReader teclado = new BufferedReader(
                    new InputStreamReader(System.in));

            // Flujo para enviar datos al servidor
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            // Flujo para recibir datos desde el servidor
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()))
        ) {
            // Se solicita al usuario un mensaje para enviar al servidor
            System.out.print("Escribe un mensaje para el servidor: ");
            String mensaje = teclado.readLine();

            // El cliente envía el mensaje al servidor
            salida.println(mensaje);

            // El cliente recibe la respuesta del servidor
            String respuesta = entrada.readLine();
            System.out.println("Respuesta del servidor: " + respuesta);

        } catch (IOException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}