import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Calculator {
    private static final Logger logger = LoggerFactory.getLogger(Calculator.class);

    public static void main(String[] str){
        WorkWithFiles file = new WorkWithFiles();
        String inputString = file.getInputString();
        if(inputString.length() > 0){
            CheckAndCleanString checkStr = new CheckAndCleanString();
            String cleanSrt = checkStr.checksAndClean(inputString);
            if(cleanSrt.length()>0){
                Calculation calcOperation = new Calculation();
                String result = calcOperation.сalculate(cleanSrt);
                if(result.length()>0){
                    logger.info("The calculation was successful!");
                    file.writeFile(result);
                }
            }
        }

    }
}
