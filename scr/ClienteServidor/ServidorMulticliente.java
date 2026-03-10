//En esta versión multicliente, el servidor permanece en ejecución 
//continua mediante un bucle while(true), aceptando múltiples conexiones de clientes. 
//Cada vez que un cliente se conecta, se crea un hilo independiente usando la clase ConexionCliente, 
//lo que permite atender a varios clientes al mismo tiempo. De esta forma, 
//se mejora el ejemplo original monocliente y se adapta a un funcionamiento multicliente real.

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