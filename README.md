# README for UDP Client-Server Java Project

## Project Overview
This project demonstrates a simple UDP client-server communication in Java. The client sends a message to the server, which then responds back to the client. Both the client and server utilize DatagramSockets for network communication. This README provides details on how to build, run, and understand the project's structure.

## Prerequisites
- Java Development Kit (JDK) 8 or higher
- An IDE like IntelliJ IDEA, Eclipse, or a simple text editor

## How to Build and Run
### Server
1. Open a terminal or command prompt.
2. Navigate to the directory containing the `Server.java` file.
3. Compile the server using the Java compiler:
   ```shell
   javac Server.java NetworkUtils.java
   ```
4. Run the compiled server class:
   ```shell
   java Server
   ```
   Optionally, specify a port using the `-Dport=XXXX` JVM argument:
   ```shell
   java -Dport=8080 Server
   ```

### Client
1. Open a new terminal or command prompt window (separate from the server's).
2. Navigate to the directory containing the `Client.java` file.
3. Compile the client using the Java compiler:
   ```shell
   javac Client.java NetworkUtils.java
   ```
4. Run the compiled client class:
   ```shell
   java Client
   ```
   Optionally, specify the host and port using the `-Dhost=XXXX` and `-Dport=XXXX` JVM arguments:
   ```shell
   java -Dhost=localhost -Dport=8080 Client
   ```

## Project Structure
### Client
- **Client.java**: The main client class that sends a UDP message to the server and waits for a response.
- **NetworkUtils.java**: A utility class containing methods for sending and receiving UDP packets.

### Server
- **Server.java**: The main server class that listens for UDP packets from clients, processes them, and sends a response back.

### Utility Classes
- **NetworkUtils.java**: Shared between the client and server, this class provides static methods to facilitate sending and receiving messages over UDP.

## Key Concepts Demonstrated
- Use of `DatagramSocket` and `DatagramPacket` for UDP communication.
- Sending and receiving data over the network using UDP protocol.
- Basic client-server architecture.

## Contributing
This project is open for contributions. Please ensure your contributions follow the project's coding style and submit a pull request for any enhancements or bug fixes.