import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class TelemetryServer {


    public static void main(String[] args) {

        // UDP
        try {
            DatagramSocket socket = new DatagramSocket(9000);

            while(true) {
                byte[] buffer = new byte[1024];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet); // bloccante
                String s = new String(packet.getData(), 0, packet.getLength());
                System.out.println(s);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
