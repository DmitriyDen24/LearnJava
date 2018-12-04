import java.io.*;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkWithFiles {
    private static final Logger logger = LoggerFactory.getLogger(WorkWithFiles.class);
    private static final String pathToFile = new File("").getAbsoluteFile().toString()+"\\src\\main\\resources\\";
    private static String inputString = "";

    public String getInputString (){
        try (FileInputStream fin = new FileInputStream(pathToFile + "inputFile.txt")){
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            while ((inputString = br.readLine()) != null){
                if(inputString.length() >= 3)
                    break;
            }
            if(inputString.length()<=0){
                throw new Exception("Not enough data to calculate!");
            }
            logger.info("String from file success getting!");
            return inputString;
        }catch (Exception ex){
            logger.error("Read file failure!", ex);
            return inputString;
        }
    }


    public void writeFile (String resultCalculate){
        String outputText = inputString + " = " + resultCalculate;
        try(FileOutputStream fos = new FileOutputStream(pathToFile + "resultFile.txt"))
        {
            byte[] buffer = outputText.getBytes();
            fos.write(buffer, 0, buffer.length);
            logger.info("Creation and writing to the file was successful!");
        }
        catch(IOException ex){
            logger.error("An error occurred while creating and writing to the file!", ex);
        }
    }
}
