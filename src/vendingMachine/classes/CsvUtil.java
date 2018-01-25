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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CsvUtil {
    private String path;
    private File file;
    private Writer fileWriter;

    public CsvUtil(String path) throws IOException {
        this.path = path;
        file = new File(path);
        fileWriter = new FileWriter(file);
    }
    
    public void writeLine(List<String> values) throws IOException{
        StringBuilder sb = new StringBuilder();
        for (String value : values) {
            sb.append('"').append(value).append('"').append(',');
        }
        sb.append("\n");
        fileWriter.append(sb.toString());
    }
    public void writeLines(List<List<String>> lines) throws IOException{
        for(List<String> line : lines){
            writeLine(line);
        }
    }
}
