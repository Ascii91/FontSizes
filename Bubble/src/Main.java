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

		String line = "";
		line = reader.readLine();

		width = Float.parseFloat(line.split(" ")[0]);

		height = (Float.parseFloat(line.split(" ")[1]));//
		line = reader.readLine();
		line = line.trim();
		sentence = line;

		List<String> words = Arrays.asList(sentence.split(" "));

		if (width == 0 || height == 0)
		{
			System.out.println(0);

		} else
		{

			// pronadjemo najduzu rec od svih reci
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
				if (fontHeight * words.size() <= height
						&& maxWord * fontWidth <= width)
				{
					break;
				}
				// System.out.println("Before calculation");
				words = makeCalculation(words, maxWord, width, height);
				// System.out.println("After calculation");
				for (String str : words)
				{
					if (str.length() > maxWord)
					{
						maxWord = str.length();
					}
				}
			

				fontWidth = width / maxWord;
				fontHeight = fontWidth * (3.0f / 2.0f);

				if (fontHeight * words.size() <= height
						&& maxWord * fontWidth <= width)
				{
					break;
				} else
				{
					//

					fontHeight = height / words.size();
					fontWidth = (2.0f / 3.0f) * fontHeight;

					break;

				}

			}
			System.out.println(fontHeight);

		}
	}

	private static List<String> makeCalculation(List<String> words,
			int maxWord, float width, float height)
	{
		int currentMaxRow = maxWord;
		while (true)
		{

			
			List<String> tempList = sabij(words, currentMaxRow);
			// proveri je li bolje sabijanje ili umanjenje;

			words = tempList;

			
			int tempMaxRow = -1;
			for (String s : words)
			{
				if (s.length() > tempMaxRow)
				{
					tempMaxRow = s.length();
				}
			}

			float tempWidth = width / tempMaxRow;
			float tempHeight = tempWidth * (3.0f / 2.0f);

			// izracunamo visinu i sirinu
			// ako su oboje upasali break;

			if (tempHeight * words.size() <= height)
			{
				break;
			}

			// ako nisu povecavamo current maxRow za minimum pa nazad na
			// sabijanje

			int minimum = 100000;
			for (int i = 0; i < words.size() - 1; i++)
			{
				String word1 = words.get(i);
				String word2 = words.get(i + 1);

				if (word1.length() + word2.split(" ")[0].length() + 1 < minimum)
				{
					minimum = word1.length() + word2.split(" ")[0].length() + 1;
				}

			}
	

			currentMaxRow = minimum;
			if ((width / minimum) * (3.0f / 2.0f) < height / words.size())
			{
				break;
			}
			//

		}

		return words;
	}

	private static List<String> sabij(List<String> words, int currentMaxRow)
	{
		if (words.size() == 1)
		{
			return words;
		}
		while (true)
		{
			List<String> tempList = new ArrayList<String>();
			if (words.size() == 1)
			{
				return words;
			}

			boolean transaction = false;
			for (int i = 0; i < words.size() - 1; i++)
			{
				String word1 = words.get(i);
				String word2 = words.get(i + 1);
				String mergedWord = word1 + " " + word2.split(" ")[0];
				// System.out.println("CurrentMax"+ currentMaxRow);
				if (word1.length() + word2.split(" ")[0].length() + 1 <= currentMaxRow)
				{
					transaction = true;
					// word1 += " " + word2.split(" ")[0];
					int separator = word2.indexOf(" ");
					if (separator == -1)
					{ // otisla je cela rec
						// System.out.println("GRANA1 " +mergedWord);
						tempList.add(mergedWord);
					} else
					{
					
						word2 = word2.substring(separator + 1);
						tempList.add(mergedWord);
						tempList.add(word2);

					}
					i++;
					if (i == words.size() - 2)
					{
						tempList.add(words.get(words.size() - 1));
						break;
					}

				} else
				{
					tempList.add(word1);
					if (i == words.size() - 2)
					{
						tempList.add(words.get(words.size() - 1));
						break;
					}

				}

			}

			words = tempList;
			if (!transaction)
			{
				break;
			}
		}

		return words;
	}

}
