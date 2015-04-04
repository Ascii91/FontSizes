import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileGenerator
{

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException
    {
        System.out.println("Write to file started");

        PrintWriter writer = new PrintWriter("file.txt");
        String generatedString = "";
        String tempString = "a";
        for (int i = 0; i < 10000; i++)
        {
            tempString = "";
            for (int j = 0; j < 10; j++)
            {
                
                
                tempString += "a";
                generatedString +=" "+ tempString;
                if (generatedString.length() >= 10000)
                {
                    break;
                }
            }
            if (generatedString.length() >= 10000)
            {
                break;
            }
        }

        writer.write(generatedString);
        writer.flush();
        writer.close();

        System.out.println("Write to file done");

    }

}
