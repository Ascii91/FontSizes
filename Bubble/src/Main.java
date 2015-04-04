import java.io.BufferedReader;
import java.io.InputStreamReader;
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

		try
		{
			width = Float.parseFloat(reader.readLine());//

			height = Float.parseFloat(reader.readLine());//

			sentence = reader.readLine();
		} catch (Exception e)
		{
		}
		List<String> words = Arrays.asList(sentence.split(" "));

		if (width == 0 || height == 0)
		{
			System.out.println(0);
			return;
		}

		int maxWord = 0;
		for (String word : words)
		{
			if (word.length() > maxWord)
			{
				maxWord = word.length();
			}
		}

		fontWidth = width / maxWord;
		fontHeight = 3 * fontWidth / 2; // desired font height

		while (true)
		{

			words = mergeWords(words, maxWord);

			if (fontHeight * words.size() <= height)
			{
				break;// :D super
			} else
			{

				// mora se smanjiti font za koliko?
				// za malo samo ili za sledeci merge

				float faliNam = (fontHeight * words.size() - height)
						/ words.size();

				int myRowLength = 1 + Main.minTwoSize(words);
				maxWord = myRowLength;

				float smanjenje = fontHeight - (3 * (width / myRowLength) / 2);

				// ako nam fali manje od jednog merga onda cemo da smanjimo za

				if (faliNam <= smanjenje)
				{
					fontHeight -= faliNam;
					fontWidth = 2 * fontHeight / 3;
					break;
				}

				fontWidth = width / myRowLength;
				fontHeight = 3 * fontWidth / 2;

			}

		}

		System.out.println(fontHeight);
		return;
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
