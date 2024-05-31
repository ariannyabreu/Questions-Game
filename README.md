# Questions Game

## Summary

This project is a Java program that implements a binary decision tree-based game. The program tries to guess an object based on a series of yes/no questions. If the program cannot guess correctly, it learns from its mistakes by asking the user to provide a distinguishing question and the correct answer.

## Features

- **Binary Decision Tree**: Core structure of the game where each node contains a question.
- **Serializable**: Allows the game state to be saved and loaded from a file.
- **Learning Capability**: Learns new objects and questions from the user if it fails to guess correctly.
- **User Interaction**: Interacts with the user through the console, asking questions and navigating the tree based on the user's responses.

## How to Use

1. **Compile the Program**
   - Ensure you have Java installed on your system.
   - Open your terminal and navigate to the project directory.
   - Compile the code using `javac`:
     ```sh
     javac *.java
     ```

2. **Run the Program**
   - Execute the compiled program:
     ```sh
     java Main
     ```

3. **Follow the On-Screen Instructions**
   - Answer the yes/no questions posed by the program.
   - If the program guesses incorrectly, provide the correct object and a distinguishing question.

## Classes

### `BDTNode`

Represents a node in the binary decision tree.

- **Attributes**:
  - `Question question`: The question associated with the node.
  - `BDTNode yes, no`: References to the child nodes for "yes" and "no" answers.

- **Methods**:
  - `BDTNode(Question question)`: Constructor to initialize the node with a question.
  - `Question getQuestion()`: Returns the question associated with the node.
  - `boolean isLeaf()`: Checks if the node is a leaf node (i.e., has no children).

### `BinaryDecisionTree`

Manages the binary decision tree.

- **Attributes**:
  - `BDTNode root`: The root node of the tree.
  - `BDTNode current`: The current node in focus.
  - `Stack<BDTNode> nodeStack`: A stack to manage the path taken, allowing backtracking.
  - `CategoryMap categoryMap`: A map to manage categories.

- **Methods**:
  - `BinaryDecisionTree()`: Constructor to initialize the tree with a predefined structure.
  - `void navigateToYes()`: Navigates to the "yes" child of the current node.
  - `void navigateToNo()`: Navigates to the "no" child of the current node.
  - `void setCurrentQuestion(Question question)`: Sets the question for the current node.
  - `Question getCurrentQuestion()`: Returns the question of the current node.
  - `boolean isCurrentLeaf()`: Checks if the current node is a leaf node.
  - `void addNewNode(String subject, String questionText, String category)`: Adds a new node to the tree if the current node is a leaf.
  - `void backtrack()`: Backtracks to the previous node.
  - `void saveToFile(String filename)`: Saves the tree to a file.
  - `static BinaryDecisionTree loadFromFile(String filename)`: Loads the tree from a file.
  - `void resetGameState()`: Resets the game state to the root.

### `CategoryMap`

Manages categories, extending `HashMap<String, List<String>>`.

- **Methods**:
  - `void addSubject(String category, String subject)`: Adds a subject to a category.

### `Question`

Represents a question.

- **Attributes**:
  - `String subject`: The subject of the question.
  - `String questionText`: The text of the question.
  - `String category`: The category of the question.

- **Methods**:
  - `Question(String subject, String questionText, String category)`: Constructor to initialize the question.
  - `String getSubject()`: Returns the subject.
  - `String getQuestionText()`: Returns the question text.
  - `String getCategory()`: Returns the category.

### `TwentyQGame`

Manages the game logic.

- **Methods**:
  - `TwentyQGame()`: Constructor to load the decision tree from a file.
  - `void start()`: Starts the game and interacts with the user through the console.

## Future Improvements

- Enhance the user interface to include a graphical version.
- Expand the tree with more predefined questions and answers.
- Implement more advanced learning algorithms to optimize the decision-making process.

