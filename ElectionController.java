import java.io.*;
import java.net.*;
import java.util.*;
//The server side
public class ElectionController {
    private static List<String> votes = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Server socket setup
            ServerSocket serverSocket = new ServerSocket(9999);
            System.out.println("Server started. Waiting for electorates to cast their votes...");

            while (votes.size() < 5) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                // Receive the vote from the client
                String vote = in.readLine();
                votes.add(vote);

                System.out.println("Vote successfully cast and received: " + vote + ", total count: " + votes.size());

                clientSocket.close();
            }

            // Determine the winner
            String winner = determineWinner(votes);

            // Display election results
            System.out.println("Election Results:");
            System.out.println("Candidate A: " + Collections.frequency(votes, "A"));
            System.out.println("Candidate B: " + Collections.frequency(votes, "B"));
            System.out.println("Winner: Candidate " + winner);

            // Close server socket
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to determine the winner
    private static String determineWinner(List<String> votes) {
        int countA = Collections.frequency(votes, "A");
        int countB = Collections.frequency(votes, "B");
        return countA > countB ? "A":"B";

    }
}