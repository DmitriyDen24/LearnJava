public class Сalculator {
    public static void main(String[] str){
        WorkWithFile file = new WorkWithFile();
        String inputString = file.getInputString();
        if(inputString.length() > 0){
            Calculation check = new Calculation();
            check.calc("5+9-16*2/4");
        }

    }
}
