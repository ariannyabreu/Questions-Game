/*******************************************************************************************************
 * Arianny Abreu Gonzalez
 * Project - Questions Game
 * 1 May 2024
 * Program Description: This class is made to manage a binary decision tree structure for a game for
 * decision-making where each node represents a decision point with a question and two possible outcomes
 * (yes or no). It also supports navigating through the tree, modifying it, and performing file operations
 * for saving and loading the tree state.
 *******************************************************************************************************/

import java.io.*;
import java.util.Stack;

// Class BinaryDecisionTree manages a binary decision tree and is serializable to allow saving and loading the tree state.
public class BinaryDecisionTree implements Serializable {

    // Root node of the decision tree.
    private BDTNode root;

    // Current node, representing the current focus in the tree.
    private BDTNode current;

    // Stack to manage the path taken, allowing backtracking.
    private Stack<BDTNode> nodeStack = new Stack<>();

    // Map to manage categories.
    private CategoryMap categoryMap = new CategoryMap();


    // Constructor initializes the decision tree with a set structure of nodes and questions.
    public BinaryDecisionTree() {
        this.root = new BDTNode(new Question("alive", "Is it (or was it ever) alive?", "Unknown"));
        this.current = root;

        // Alive or not.
        this.root.yes = new BDTNode(new Question("human", "Is it human?", "Alive"));
        this.root.no = new BDTNode(new Question("material", "Is it an object or material?", "Not Alive"));
        this.root.no.no = new BDTNode(new Question("concept or idea", "Is it a concept or idea?", "Not Alive"));
        this.root.yes.no = new BDTNode(new Question("animal", "Is it an animal?", "Non-human Alive"));
        this.root.yes.yes = new BDTNode(new Question("adult", "Is it an adult human?", "Human"));
        this.root.yes.yes.yes = new BDTNode(new Question("famous adult", "Is it a famous person?", "Human"));
        this.root.yes.yes.yes.yes = new BDTNode(new Question("celebrity", "Is it a celebrity?", "Human"));
        this.root.yes.yes.yes.yes.yes = new BDTNode(new Question("singer", "Is it a singer?", "Human"));
        this.root.yes.yes.yes.no = new BDTNode(new Question("adult in your family", "Is it a relative?", "Human"));
        this.root.yes.yes.yes.no.no = new BDTNode(new Question("neighbor", "Is it your neighbor?", "Human"));
        this.root.yes.yes.yes.no.yes = new BDTNode(new Question("female", "Is it a female relative?", "Human"));
        this.root.yes.yes.yes.no.yes.no = new BDTNode(new Question("male", "Is it a male relative?", "Human"));
        this.root.yes.yes.yes.no.yes.yes = new BDTNode(new Question("direct relative", "Is it a direct relative?", "Human"));
        this.root.yes.yes.yes.no.yes.no.yes = new BDTNode(new Question("direct relative", "Is it a direct relative?", "Human"));
        this.root.yes.yes.yes.no.yes.no.yes.yes = new BDTNode(new Question("dad", "Is it your father?", "Human"));
        this.root.yes.yes.yes.no.yes.no.yes.yes.no = new BDTNode(new Question("brother", "Is it your brother?", "Human"));
        this.root.yes.yes.yes.no.yes.yes.yes = new BDTNode(new Question("mom", "Is it your mother?", "Human"));
        this.root.yes.yes.yes.no.yes.yes.yes.no = new BDTNode(new Question("sister", "Is it a sibling?", "Human"));
        this.root.yes.yes.yes.yes.no = new BDTNode(new Question("scientific", "Is it a scientific?", "Human"));
        this.root.yes.yes.yes.yes.yes.no = new BDTNode(new Question("actor/actress", "Is it an actor/actress?", "Human"));
        this.root.yes.yes.no = new BDTNode(new Question("child", "Is it a child?", "Human"));
        this.root.yes.yes.no.yes = new BDTNode(new Question("child in your family", "Is it your child?", "Human"));
        this.root.yes.yes.no.yes.no = new BDTNode(new Question("famous child", "Is it a famous child?", "Human"));
        this.root.yes.yes.no.yes.no.no = new BDTNode(new Question("child in your family", "Is it your child?", "Human"));
        this.root.yes.yes.no.yes.no.yes = new BDTNode(new Question("celebrity son/daughter", "Is it a celebrity son/daughter?", "Human"));
        this.root.yes.yes.no.yes.no.yes.no = new BDTNode(new Question("child actor/actress", "Is it a celebrity child?", "Human"));

        // Non-living objects.
        this.root.no.yes = new BDTNode(new Question("used indoors", "Is it used indoors?", "Object"));
        this.root.no.yes.no = new BDTNode(new Question("used outdoors", "Is it used outdoors?", "Object"));
        this.root.no.yes.no.yes = new BDTNode(new Question("umbrella", "Is it an umbrella?", "Object"));
        this.root.no.yes.no.no = new BDTNode(new Question("concept", "Is it a concept or idea?", "Object"));

        this.root.no.no = new BDTNode(new Question("concept", "Is it a concept or phenomenon?", "Concept"));
        this.root.no.yes.yes = new BDTNode(new Question("made of wood", "Is it made out of wood?", "Object wood"));
        this.root.no.yes.yes.yes = new BDTNode(new Question("chair", "Is it a chair?", "Chair"));
        this.root.no.yes.yes.no = new BDTNode(new Question("made of plastic", "Is it made out of plastic?", "Object"));
        this.root.no.yes.yes.no.yes = new BDTNode(new Question("ball", "Is it a ball?", "Ball"));
        this.root.no.yes.yes.no.no = new BDTNode(new Question("made of glass", "Is it made out of glass?", "Object"));
        this.root.no.yes.yes.no.no.yes = new BDTNode(new Question("glass/cup", "Is it a glass/cup?", "Object"));
        this.root.no.yes.yes.no.no.no = new BDTNode(new Question("made of metal", "Is it made out of metal?", "Object"));
        this.root.no.yes.yes.no.no.no.yes = new BDTNode(new Question("screw/nail", "Is it a screw/nail?", "Object"));
        this.root.no.yes.yes.no.no.no.no = new BDTNode(new Question("made of fabric", "Is it made out of fabric?", "Object"));
        this.root.no.yes.yes.no.no.no.no.yes = new BDTNode(new Question("pillow", "Is it a pillow?", "Object"));
        this.root.no.yes.yes.no.no.no.no.no = new BDTNode(new Question("liquid", "Is it liquid?", "Liquid"));
        this.root.no.yes.yes.no.no.no.no.no.yes = new BDTNode(new Question("perfume", "Is it a perfume?", "Liquid"));
        this.root.no.yes.yes.no.no.no.no.no.no = new BDTNode(new Question("light", "Is it light?", "Light"));

        // Animal specifics.
        this.root.yes.no.yes = new BDTNode(new Question("mammal", "Is it a mammal?", "Animal"));
        this.root.yes.no.no = new BDTNode(new Question("plant", "Is it a plant?", "Non-human Alive"));
        this.root.yes.no.no.no = new BDTNode(new Question("bacteria", "Is it a bacteria?", "Non-human Alive"));
        this.root.yes.no.no.yes = new BDTNode(new Question("Tree", "Is it a tree?", "Non-human Alive"));

        // Mammal specifics.
        this.root.yes.no.yes.yes = new BDTNode(new Question("wild", "Is it known for living in the wild?", "Mammal"));
        this.root.yes.no.yes.no = new BDTNode(new Question("reptile", "Is it a reptile?", "Wild reptileM"));

    }

    // Navigate to the yes child of the current node, pushing the current node onto the stack.
    public void navigateToYes() {
        if (current != null) {
            nodeStack.push(current);
            current = current.yes;
        }
    }

    // Navigate to the no child of the current node, pushing the current node onto the stack.
    public void navigateToNo() {
        if (current != null) {
            nodeStack.push(current);
            current = current.no;
        }
    }

    // Sets the question for the current node.
    public void setCurrentQuestion(Question question) {
        if (current != null) {
            current.question = question;
        }
    }

    // Returns the question of the current node.
    public Question getCurrentQuestion() {
        return current.question;
    }

    // Checks if the current node has no children.
    public boolean isCurrentLeaf() {
        return current.isLeaf();
    }

    // Adds a new node to the tree at the current node if it is a leaf.
    public void addNewNode(String subject, String questionText, String category) {
        if (isCurrentLeaf()) {
            current.yes = new BDTNode(new Question(current.question.getSubject(), current.question.getQuestionText(), current.question.getCategory()));
            current.no = new BDTNode(new Question(subject, questionText, category));
            setCurrentQuestion(new Question("unknown", questionText, "Unknown"));
        }
    }

    public void backtrack() {
        if (!nodeStack.isEmpty()) {
            current = nodeStack.pop();
        }
    }

    // Save the current state of the binary decision tree to a file.
    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load a binary decision tree from a file or create a new one if the file doesn't exist or can't be read.
    public static BinaryDecisionTree loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (BinaryDecisionTree) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tree from file, creating a new tree.");
            // Return a new tree.
            return new BinaryDecisionTree();
        }

    }

    // Reset the current node to the root and print the starting or current question.
    public void resetGameState() {
        current = root;
        if (current != null && current.getQuestion() != null) {
            System.out.println("Game reset method. Starting at root: " + current.getQuestion().getQuestionText());
        } else {
            System.out.println("Game state reset. No root node or question defined.");
        }
    }

}
