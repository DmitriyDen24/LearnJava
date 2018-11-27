import java.io.*;

public class WorkWithFile {
    String inputString;
    WorkWithFile (){
        readFile();
    }

    private void readFile (){
        try (FileInputStream fin = new FileInputStream("C://notes.txt")){
            BufferedReader br = new BufferedReader(new InputStreamReader(fin));
            String strLine;
            while ((strLine = br.readLine()) != null){
                if(strLine.length() >= 3){
                    inputString = strLine;
                    break;
                }
            }
            if(inputString.length()<=0){
                throw new Exception("Не достаточно данных для расчета!");
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    public String getInputString (){
        return inputString;
    }

    public void writeFile (){

    }
}
