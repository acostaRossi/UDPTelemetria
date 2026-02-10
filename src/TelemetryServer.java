import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TelemetryServer {

    public static void main(String[] args) {

        // UDP
        try {
            DatagramSocket socket = new DatagramSocket(9000);

            while(true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // bloccante
                new Thread(() -> handleClient(packet)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void handleClient(DatagramPacket packet) {
        String s = new String(packet.getData(), 0, packet.getLength());
        String[] parts = s.split(";");
        long time = Long.parseLong(parts[0]);
        long totalMemory = Long.parseLong(parts[1]);
        long usedMemory = Long.parseLong(parts[2]);
        String os = parts[3];
        LocalDateTime lDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(time), ZoneId.systemDefault());
        String sFinal = lDateTime.format(DateTimeFormatter.ISO_DATE_TIME) + " " + totalMemory + " " + usedMemory + " " + os;
        System.out.println(sFinal);
    }
}
