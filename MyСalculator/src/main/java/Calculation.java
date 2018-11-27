import java.util.*;

public class Calculation {


    public void calc(){
//    String[] ss2 = {"1","2","32","4","5"};
//    String[] s3 = {"+","-","*","/"};
//    Map<String, String> states = new HashMap<String, String>();
//    int count = 0;
//    for(int j = 0; j < s3.length; j++){
//        for(int i = 0; i < ss2.length; i++){
//            count++;
//            if(count<=2){
//
//            }
//
//        }
//    }
    String s = "1+2-32*4/5";
    String[] arr = s.split("\\s*(\\+|\\-|\\/|\\*)\\s*");
    char[] ch = s.toCharArray();
    int indM = s.indexOf("*");
    int indS = s.indexOf("/");
    System.out.print(s.charAt(5));
    Double resOne;
    String nS;
    if(indM != -1 && indS != -1){
        String first = Character.toString(ch[--indM]);
        String second = Character.toString(ch[2+indM]);
        if(indM < indS){
            Double firstV = Double.parseDouble(first);
            Double secondV = Double.parseDouble(second);
            resOne = multiply(firstV,secondV);
            nS = s.replace(first+"*"+second,Double.toString(resOne));
            getPrevVal(s,indM);
            //System.out.println(nS);
            //System.out.println(resOne);
        }else {
            Double firstV = Double.parseDouble(first);
            Double secondV = Double.parseDouble(second);
            resOne = split(firstV,secondV);
            nS = s.replace(first+"/"+second,Double.toString(resOne));
            System.out.println(nS);
            System.out.print(resOne);
        }
    }else {
//        if(nS.length()>1){
//
//        }
    }

//    for(int i = 0; i < ch.length; i++){
//        System.out.println(ch[i]);
//    }
//    for(int i = 0; i < arr.length; i++){
//        System.out.println(arr[i]);
//    }

 }

     private void getPrevVal (String str, int indVal){
            String elem;
            int to = indVal;
            int from = indVal;
           String res = str.substring(--from,to);
            System.out.print(res);
         boolean i = true;
           while(i){
               if(res != "+" || res != "-" || res != "*" || res != "/" || from != -1){
                res = str.substring(--from,--indVal);
                System.out.print(res);
               }else{
                i = false;
               }
           }
     }

    public Double sum (double a, double b){
        double res = a + b;
        return res;
    }

    public Double subtract (double a, double b){
        double res = a - b;
        return res;
    }

    public Double multiply (double a, double b){
        double res = a * b;
        return res;
    }

    public Double split (double a, double b){
        double res = a / b;
        return res;
    }
}
