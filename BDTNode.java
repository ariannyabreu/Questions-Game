/*******************************************************************************************************
 * Arianny Abreu Gonzalez
 * Project - Questions Game
 * 1 May 2024
 * Program Description: This class represents a node in a binary decision tree,
 * where each node contains a question and can point to two other nodes: yes and no
 * based on the answers to the question. It also includes methods to check if a node is a leaf,
 * indicating it has no children.
 *******************************************************************************************************/

import java.io.Serializable;

// Class BDTNode represents a node in a Binary Decision Tree and is serializable to allow objects to be serialized for storage or transmission.
public class BDTNode implements Serializable {

    // Question associated with this node.
    Question question;

    // References to the child nodes: yes for a positive answer to the question and no for a negative answer.
    BDTNode yes, no;

    // Constructor that initializes a BDTNode with a question. Child nodes are initially set to null.
    public BDTNode(Question question) {
        this.question = question;
        this.yes = null;
        this.no = null;
    }

    // Getter method to return the question associated with this node.
    public Question getQuestion() {
        return this.question;
    }

    // Method to determine if the node is a leaf node.
    public boolean isLeaf() {
        return (yes == null && no == null);
    }
}
