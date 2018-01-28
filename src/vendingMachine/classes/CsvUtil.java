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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CsvUtil {
    private File file;
    private Writer fileWriter;
    private final static char QUOTES = '"';
    private final static char FIELD_SEPORATOR = ',';

    /**
     * Constructor
     * @param path 
     */
    public CsvUtil(String path) {
            file = new File(path);
    }

    /**
     * Write multiple lines to a CSV file.
     * @param values 
     */
    public void writeLine(List<String> values){
        try {
            fileWriter = new FileWriter(System.getProperty("user.dir")+"/src/res/output/"+file);
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
    /**
     * Write a single line to a CSV file.
     * @param lines 
     */
    public void writeLines(List<List<String>> lines){
        for(List<String> line : lines){
            writeLine(line);
        }
    }
    
    /**
     * Finish writing to the file to close it.
     */
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
     *  CsvUtil csvUtil = newCsvUtil("pathToFile.csv");
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
    public List<String[]> readCSV(){
        BufferedReader br = null;
        String line;
        List<String[]> values = new ArrayList<>();

        try {

            br = new BufferedReader(new FileReader(System.getProperty("user.dir")+"/src/res/input/"+file));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                values.add(line.split(","));
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
        return values;
    }
}
