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
        System.out.println(findFromDataset(new String[] {"Yarn"} ));
    }

    /**
     * 
     * @param keywords the keywords to search the database for in an array, ex: "yarn" and "paper"
     */
    private List<Integer> findFromDataset(String[] keyword){
        List<Integer> matchingRowIndexes = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILENAME))) {
            String line;
            int rowIndex = 0;

            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",", -1); // -1 keeps empty columns

                if (columns.length >= 3 && columns[3].toLowerCase().contains(keyword[0].toLowerCase())) {
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
