/***************************************************************************************************************
 * Arianny Abreu Gonzalez
 * Project - Questions Game
 * 1 May 2024
 * Program Description:  It is designed to easily manage and retrieve subjects categorized under specific labels.
 * This class is serializable, allowing it to be saved to or loaded from a file stream. The class is not explicitly
 * used within the program, it could potentially be utilized in the BinaryDecisionTree for managing categories of
 * questions or subjects more efficiently that how it is currently.
 ***************************************************************************************************************/

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryMap extends HashMap<String, List<String>> implements Serializable {
    public void addSubject(String category, String subject) {
        this.computeIfAbsent(category, k -> new ArrayList<>()).add(subject);
    }
}
