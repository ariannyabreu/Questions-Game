/*******************************************************************************************************
 * Arianny Abreu Gonzalez
 * Project - Questions Game
 * 1 May 2024
 * Program Description: this class represents a question with associated data, used in decision-making
 * processes. It is serializable to allow objects of this type to be saved and loaded from storage.
 *******************************************************************************************************/

import java.io.Serializable;

public class Question implements Serializable {

    // Subject of the question, to categorize or identify the context.
    private String subject;

    // The text of the question itself
    private String questionText;

    // Category to further classify the question
    private String category;

    // Constructs a new question with specified subject, text, and category.
    public Question(String subject, String questionText, String category) {
        this.subject = subject;
        this.questionText = questionText;
        this.category = category;
    }

    // Returns the subject of the question.
    public String getSubject() {
        return subject;
    }

    // Returns the text of the question.
    public String getQuestionText() {
        return questionText;
    }

    // Returns the category of the question.
    public String getCategory() {
        return category;
    }
}

