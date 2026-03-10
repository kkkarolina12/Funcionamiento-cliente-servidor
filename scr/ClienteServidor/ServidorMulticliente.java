package ClienteServidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMulticliente {

    public static void main(String[] args) {
        // Puerto en el que el servidor escuchará las conexiones
        final int PUERTO = 5000;

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {
            System.out.println("Servidor multicliente iniciado en el puerto " + PUERTO);

            // El servidor queda en espera continua de nuevos clientes
            while (true) {
                // Acepta la conexión de un nuevo cliente
                Socket socketCliente = servidor.accept();
                System.out.println("Cliente conectado desde: " + socketCliente.getInetAddress());

                // Se crea un hilo independiente para atender a cada cliente
                Thread hilo = new Thread(new ConexionCliente(socketCliente));
                hilo.start();
            }

        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}