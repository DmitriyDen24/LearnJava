
public class CheckInputString {

    public void checking (String str){

        String t1 = str.replace(" ","");
        String t2 = t1.replace("=","");
       // System.out.print(t1);

        String[] arithmeticOperations = t2.split("\\s*([0-9])\\s*");
        String[] words = t2.split("\\s*(\\+|\\-|\\/|\\*)\\s*");

        for(String word : words){
           // System.out.println(word);
        }
        String r = "+";
        //char[] f = r.toCharArray();
        System.out.print(3+2);
        for(String opp : arithmeticOperations){
            if(opp.length() != 0){
                double a = 4;
                char[] res = opp.toCharArray();
                double r2 =  a+res[0]+2;
               // System.out.println(res[0]);
               // System.out.println(r2);
            }
        }
        //проверка на содержания недопустимых значений в строке
    }
}
