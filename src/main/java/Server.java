import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {

    public static void main(String[] args) {

        // Get the port from the command line arguments or use default value
        int port = Integer.parseInt(System.getProperty("port", "8080"));

        try {
            // Create a datagram socket bound to the specified port
            DatagramSocket socket = new DatagramSocket(port);
            System.out.println("Server started on port " + port);

            // Create a buffer to receive incoming packets
            byte[] receiveData = new byte[1024];

            // Loop to receive and respond to client messages
            while (true) {

                // Receive a packet from a client
                NetworkUtils.Tuple tuple = NetworkUtils.Receive(socket);
                String message = new String(tuple.Payload, 0, tuple.Payload.length);
                System.out.println("Received message from client: " + message);

                // Create a response message and packet
                String response = "Hello from UDP server!";
                byte[] sendData = response.getBytes();
                InetAddress address = tuple.Address;
                int portNumber = tuple.Port;

                // Send the response packet to the client
                NetworkUtils.Send(socket, address, portNumber, sendData);
                System.out.println("Sent response to client");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}