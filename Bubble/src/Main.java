import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main
{

	public static void main(String[] args) throws java.lang.Exception
	{

		float fontWidth = 0;
		float fontHeight = 0;
		float width = 0;
		float height = 0;
		String sentence = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in));

//		BufferedReader readerFile = new BufferedReader(new FileReader(
//				"file.txt"));

		// PrintWriter writer = new PrintWriter("output.txt");
		// sentence = readerFile.readLine();
		// for (int x = 1; x < 1000; x++)
		// {
		// System.out.println("x" + x);
		// for (int y = 1; y < 1000; y++)
		// {

		String line = "";
		line = reader.readLine();

		width = Float.parseFloat(line.split(" ")[0]);

		height = (Float.parseFloat(line.split(" ")[1]));//
		line = reader.readLine();

		sentence = line;

		List<String> words = Arrays.asList(sentence.split(" "));

		if (width == 0 || height == 0)
		{
			System.out.println(0);

		}
		else
		{

		int maxWord = 0;
		for (String word : words)
		{
			if (word.length() > maxWord)
			{
				maxWord = word.length();
			}
		}

		fontWidth = width / maxWord;
		fontHeight = 3.0f * fontWidth / 2.0f; // desired font height

		while (true)
		{
			// System.out.println("Words.size"+ words.size());
			words = mergeWords(words, maxWord);
			// System.out.println("Words.size after"+ words.size());
			// System.out.println("**"+fontHeight);
			if (fontHeight * words.size() <= height)
			{
				// System.out.println("BREAKPOINT1: " +words.size());
				break;// :D super
			} else
			{

				// mora se smanjiti font za koliko?
				// za malo samo ili za sledeci merge

				float faliNam = (fontHeight * words.size() - height)
						/ words.size();

				int myRowLength = 1 + Main.minTwoSize(words);
				maxWord = myRowLength;

				float smanjenje = fontHeight
						- (3.0f * (width / myRowLength) / 2.0f);

				// ako nam fali manje od jednog merga onda cemo da
				// smanjimo za

				if (faliNam <= smanjenje)
				{
					fontHeight -= faliNam;
					fontWidth = 2.0f * fontHeight / 3.0f;
					break;
				}

				fontWidth = width / myRowLength;
				fontHeight = 3.0f * fontWidth / 2.0f;

			}

		}
		int tempMax = 0;
		for (String str : words)
		{
			if (str.length() > tempMax)
			{
				tempMax = str.length();
			}
		}

		// if (tempMax * fontWidth > width
		// || fontHeight * words.size() - height > 0.0001)
		// {
		// System.out.println("****************************");
		// writer.println("Width:" + width + "  Height:" + height
		// + " Size:" + fontHeight + " Words size:"
		// + words.size() + " Razlika:"
		// + (fontHeight * words.size() - height));
		// }

		// writer.println(fontHeight);
		// writer.flush();
		// }
		// }

		System.out.println(fontHeight);
		}
	}

	// nalazi sumu duzina dve najmanje susedne reci
	public static int minTwoSize(List<String> words)
	{
		int minSum = 10001;

		for (int i = 0; i < words.size() - 1; i++)
		{
			if (words.get(i).length() + words.get(i + 1).length() < minSum)
			{
				minSum = words.get(i).length() + words.get(i + 1).length();
			}
		}

		return minSum;
	}

	// samo merguje
	public static List<String> mergeWords(List<String> words, int initMax)
	{
		if (words.size() == 1)
		{
			return words;
		}
		while (true)
		{
			if (words.size() == 1)
			{
				return words;
			}
			List<String> initList = new ArrayList<String>();
			boolean merged = false;
			for (int i = 0; i < words.size() - 1; i++)
			{
				String word1 = words.get(i);
				String word2 = words.get(i + 1);
				String mergedWords = word1 + " " + word2;
				if (mergedWords.length() > initMax)
				{

					initList.add(word1);
					if (i == words.size() - 2)
					{
						initList.add(word2);
					}

				} else
				{
					merged = true;
					initList.add(mergedWords);
					i++;
					if (i == words.size() - 2)
					{
						initList.add(words.get(words.size() - 1));
					}

				}

			}

			words = initList;
			if (!merged)
			{
				break;
			}
		}

		return words;
	}

}
