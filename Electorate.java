import java.io.*;
import java.net.*;
//The client side
public class Electorate {
    public static void main(String[] args) {
        try {
            // Prompt the user for their vote
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter your vote (A or B): ");
            String vote = reader.readLine().toUpperCase();

            // Validate the vote
            if (!vote.equals("A") && !vote.equals("B")) {
                System.out.println("Invalid vote. Please try again.");
                return;
            }

            // Connect to the server
            Socket socket = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Send the vote to the server
            out.println(vote);

            // Close the socket
            socket.close();

            System.out.println("Vote cast: " + vote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}