import java.io.IOException;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        // Get the port and host from the command line arguments or use default values
        int port = Integer.parseInt(System.getProperty("port", "8080"));
        String host = System.getProperty("host", "localhost");

        try {
            // Create a datagram socket
            DatagramSocket socket = new DatagramSocket();

            // Create a message to send to the server
            String message = "Hello from UDP client!";
            byte[] sendData = message.getBytes();

            // Create a datagram packet containing the message
            InetAddress address = InetAddress.getByName(host);
            NetworkUtils.Send(socket, address, port, sendData);

            // Create a buffer to receive the response
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Receive the response from the server
            NetworkUtils.Tuple reciveTuple = NetworkUtils.Receive(socket);
            String response = new String(reciveTuple.Payload, 0, reciveTuple.Payload.length);

            // Print the response
            System.out.println("Received response from server: " + response);

            // Close the socket
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}