package ch.heigvd.api.calc;

import java.io.*;
import java.net.Socket;
import java.nio.Buffer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Calculator client implementation
 */
public class Client {

    private static final Logger LOG = Logger.getLogger(Client.class.getName());

    /**
     * Main function to run client
     *
     * @param args no args required
     */
    public static void main(String[] args) {
        // Log output on a single line
        System.setProperty("java.util.logging.SimpleFormatter.format", "%4$s: %5$s%6$s%n");

        BufferedReader stdin = null;

        /* TODO: Implement the client here, according to your specification
         *   The client has to do the following:
         *   - connect to the server
         *   - initialize the dialog with the server according to your specification
         *   - In a loop:
         *     - read the command from the user on stdin (already created)
         *     - send the command to the server
         *     - read the response line from the server (using BufferedReader.readLine)
         */

        stdin = new BufferedReader(new InputStreamReader(System.in));

        Socket clientSocket = null;
        BufferedWriter out = null;
        BufferedReader in = null;

        try {
            clientSocket = new Socket("localhost", 23000);
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String calcul;
            LOG.log(Level.INFO, "Entrez un calcul");
            calcul = stdin.readLine();
            out.write(calcul);
            out.flush();

            LOG.log(Level.INFO, "Votre calcul a été envoyé");
            String response = in.readLine();
            LOG.log(Level.INFO, "Réponse1 : " + response);

            LOG.log(Level.INFO, "FIN");
            clientSocket.close();

        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.toString(), e);
        }

    }
}
