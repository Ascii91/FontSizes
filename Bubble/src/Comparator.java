import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Comparator
{
    public static void main(String[] args) throws IOException
    {

        BufferedReader fileReader = new BufferedReader(new FileReader("new.txt"));
        BufferedReader fileReader2 = new BufferedReader(new FileReader("bez.txt"));

        for (int i = 0; i < 8100; i++)
        {

            String value1 = fileReader.readLine();
            String value2 = fileReader2.readLine();

            double val1 = Double.parseDouble(value1);
            double val2 = Double.parseDouble(value2);

       
            if(val1<val2){ System.out.println(""+i+"*"+(val2-val1));
            
            //System.out.println("SA"+i+":"+val1);
            //System.out.println("BEZ"+i+":"+val2);
            }
            
        }

    }
}
