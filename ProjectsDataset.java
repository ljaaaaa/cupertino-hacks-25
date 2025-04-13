import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProjectsDataset {
    
    private final String FILENAME;
    
    public ProjectsDataset() {
        FILENAME = "custom-crafts-projects-database.csv";
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
     * Return a specific row as an array
     * @param index the index of the file
     * @return thre row as an array
     */
    public String[] getRow(int index){
        String[] row = new String[4];
        
        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {

            String line = "";

            for (int x = 0; x <= index; x++){
                line = br.readLine();
            }
            
            row = line.split(",", -1);

        } catch (IOException e) {
            e.printStackTrace();
        } 

        return row;
    }

    /**
     * 
     * @param keywords the keywords to search the database for in an array, ex: "yarn" and "paper"
     */
    public ArrayList<Integer> findFromDataset(String[] keywords){
        ArrayList<Integer> matchingRowIndexes = new ArrayList<>();

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
