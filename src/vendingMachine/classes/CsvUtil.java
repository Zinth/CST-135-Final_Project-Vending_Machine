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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvUtil {
    private File file;
    private Writer fileWriter;
    private final static char QUOTES = '"';
    private final static char FIELD_SEPORATOR = ',';

    public CsvUtil(String path) {
        try {
            file = new File(path);
            fileWriter = new FileWriter(file);
        } catch (IOException ex) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void writeLine(List<String> values){
        try {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (String value : values) {
                if(!first){
                    sb.append(FIELD_SEPORATOR);
                }
                sb.append(QUOTES).append(value).append(QUOTES);
                first = false;
            }
            sb.append("\n");
            fileWriter.append(sb.toString());
            fileWriter.flush();
        } catch (IOException ex) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void writeLines(List<List<String>> lines){
        for(List<String> line : lines){
            writeLine(line);
        }
    }
    
    public void finalizeWrite(){
        try {
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(CsvUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Read a csv into a hash map.
     * Usage: 
     *    Iterator it = csvUtil.readCSV().entrySet().iterator();
     *        while (it.hasNext()) {
     *            Map.Entry pair = (Map.Entry)it.next();
     *            System.out.println(pair.getKey()); // Line Number
     *            for(String value : Arrays.asList((String[]) pair.getValue())){
     *                System.out.println(value); //each item in the field
     *            }
     *            it.remove(); // avoids a ConcurrentModificationException
     *   } 
     * @return 
     */
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
