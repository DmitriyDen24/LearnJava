import java.io.*;
import java.io.File;

public class WorkWithFiles {

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
                throw new Exception("Не достаточно данных для расчета!");
            }
            return inputString;
        }catch (Exception ex){

            return inputString;
        }
    }


    public void writeFile (String resultCalculate){
        String outputText = inputString + " = " + resultCalculate;
        try(FileOutputStream fos = new FileOutputStream(pathToFile + "resultFile.txt"))
        {
            byte[] buffer = outputText.getBytes();
            fos.write(buffer, 0, buffer.length);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
