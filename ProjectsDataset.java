import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDataset {
    
    private final String FILENAME;

    public static void main(String[] args){
        new ProjectsDataset();
    }
    
    public ProjectsDataset(){
        FILENAME = "/home/lilja/cupertino-hacks-25/custom-crafts-projects-database.csv";
        String[] input = processInput("Yarn, crochet hook");
        System.out.println(findFromDataset(input));
    }

    /**
     * Process input from user
     * @param input string of inputs, likely items separated by commas
     * @return
     */
    public String[] processInput(String input){
        return input.split(",", -1);
    }

    /**
     * 
     * @param keywords the keywords to search the database for in an array, ex: "yarn" and "paper"
     */
    private List<Integer> findFromDataset(String[] keywords){
        List<Integer> matchingRowIndexes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int rowIndex = 0;

            while ((line = br.readLine()) != null) {
                String[] row = line.split(",", -1); // -1 keeps empty columns

                boolean all_keywords = true;
                boolean some_keywords = false;

                for (String keyword : keywords){
                    if (row.length >= 3 && row[3].toLowerCase().contains(keyword.toLowerCase())) {
                        some_keywords = true;
                    
                    } else {
                        all_keywords = false;
                    }
                }

                if(all_keywords){
                    matchingRowIndexes.add(rowIndex);
                }

                rowIndex++;
            }

            return matchingRowIndexes;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
