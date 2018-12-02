public class Calculator {
    public static void main(String[] str){
        WorkWithFiles file = new WorkWithFiles();
        String inputString = file.getInputString();
        if(inputString.length() > 0){
            CheckAndCleanString checkStr = new CheckAndCleanString();
            String cleanSrt = checkStr.checksAndClean(inputString);
            if(cleanSrt.length()>0){
                Calculation calcOperation = new Calculation();
                String result = calcOperation.сalculate(cleanSrt);
                file.writeFile(result);
            }else{
                System.out.print("Пример содержит ошибки!Подробности см. в лог файле!");
            }
        }

    }
}
