/*****************************************************************************************************************
 * Arianny Abreu Gonzalez
 * Project - Questions Game
 * 1 May 2024
 * Program Description: This class encapsulates the logic for playing the game using a binary decision
 * tree to guess objects based on yes/no questions.
 *****************************************************************************************************************/
import java.util.Scanner;
//import java.io.FileNotFoundException;
public class TwentyQGame {

    // The decision tree used to manage the game logic.
    private BinaryDecisionTree tree;

    // Constructor that loads the decision tree from a serialized file.
    public TwentyQGame() {
        this.tree = BinaryDecisionTree.loadFromFile("tree.ser");
    }

    public void start() {

        // Resets the game state to the root of the tree.
        tree.resetGameState();
        Scanner scanner = new Scanner(System.in);
        String response;

        // Continue asking questions until a leaf node is reached.
        while (!tree.isCurrentLeaf()) {
            // Display the current question.
            System.out.println(tree.getCurrentQuestion().getQuestionText());
            response = scanner.nextLine();
            // Navigate to the yes branch of the tree.
            if ("yes".equalsIgnoreCase(response)) {
                tree.navigateToYes();
            } else {
                // Navigate to the no branch of the tree.
                tree.navigateToNo();
            }
        }

        // Once a leaf node is reached, make a guess.
        System.out.println("Is your object a " + tree.getCurrentQuestion().getSubject() + "?");
        response = scanner.nextLine();
        if ("yes".equalsIgnoreCase(response)) {
            System.out.println("Hooray! I guessed it!");
        } else {
            // If the guess is wrong, ask the player for the correct answer to learn from it.
            System.out.println("Oh no! I got it wrong. Help me learn.");
            System.out.println("What was your object?");
            String subject = scanner.nextLine();
            System.out.println("Please give me a yes/no question that distinguishes your object from a " + tree.getCurrentQuestion().getSubject() + " (make sure the answer to this question is no).");
            String questionText = scanner.nextLine();
            System.out.println("What category does it belong to?");
            String category = scanner.nextLine();
            // Add the new information to the tree.
            tree.addNewNode(subject, questionText, category);
            // Save the updated tree back to the file.
            tree.saveToFile("tree.ser");
        }
    }
}
