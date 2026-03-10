package ClienteServidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConexionCliente implements Runnable {

    // Socket asociado a un cliente concreto
    private Socket socket;

    public ConexionCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            // Flujo de entrada para recibir datos del cliente
            BufferedReader entrada = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            // Flujo de salida para enviar datos al cliente
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)
        ) {
            // El servidor recibe el mensaje enviado por el cliente
            String mensaje = entrada.readLine();
            System.out.println("Mensaje recibido de " + socket.getInetAddress() + ": " + mensaje);

            // El servidor responde al cliente
            salida.println("Hola cliente, he recibido tu mensaje: " + mensaje);

        } catch (IOException e) {
            System.out.println("Error al atender al cliente: " + e.getMessage());
        } finally {
            try {
                // Se cierra la conexión con el cliente al finalizar la comunicación
                socket.close();
                System.out.println("Conexión cerrada con: " + socket.getInetAddress());
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }
}