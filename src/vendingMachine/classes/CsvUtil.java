/**
 * @project Milestone6
 * @course CST-135
 * @author James Ray
 * @author Christopher Hyde
 * @author Robert Wade
 * @teacher Dr. Lively
 * @date 01/24/18
 *
 * @about A Utility class to read/write csv files
 */
package vendingMachine.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvUtil {
    private String path;
    private File file;
    private Writer fileWriter;

    public CsvUtil(String path) throws IOException {
        this.path = path;
        file = new File(path);
        fileWriter = new FileWriter(file);
    }
    
    public void writeLine(List<String> values){
        try {
            StringBuilder sb = new StringBuilder();
            for (String value : values) {
                sb.append('"').append(value).append('"').append(',');
            }
            sb.append("\n");
            fileWriter.append(sb.toString());
        } catch (IOException ex) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void writeLines(List<List<String>> lines){
        for(List<String> line : lines){
            writeLine(line);
        }
    }
    
    public HashMap<Integer, String[]> readCSV(){
        BufferedReader br = null;
        String line;
        HashMap<Integer, String[]> linesAndValues = new HashMap<>();

        try {

            br = new BufferedReader(new FileReader(file));
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                // use comma as separator
                
                linesAndValues.put(lineNumber, line.split(","));
                lineNumber++;
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, e);
        } catch (IOException e) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, e);
                }
            }
        }
        return linesAndValues;
    }
}
