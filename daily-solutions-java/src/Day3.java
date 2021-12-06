import java.util.ArrayList;

public class Day3 {

	public static int puzzle1(ArrayList<String> input) {
		int numLines = input.size();
		int[] numOnes = new int[input.get(0).length()];
		for(String line : input) {
			for(int i = 0; i < line.length(); i++) {
				if(line.charAt(i) == '1') {
					numOnes[i]++;
				}
			}
		}
		String gammaRate = "";
		String epsilonRate = "";
		for(int i = 0; i < numOnes.length; i++) {
			if (numOnes[i] > numLines / 2) {
				gammaRate += "1";
				epsilonRate += "0";
			} else {
				gammaRate += "0";
				epsilonRate += "1";
			}
		}
		System.out.println("gamma: " + gammaRate + " epsilon " + epsilonRate);
		int gamma = Integer.parseInt(gammaRate, 2);
		int epsilon = Integer.parseInt(epsilonRate, 2);
		return gamma * epsilon;
	}
	
	public static int puzzle2(ArrayList<String> input) {
		return getOxygenRating(input) * getCO2Rating(input);
	}
	
	public static int getOxygenRating(ArrayList<String> input) {
		ArrayList<String> filteredValues = input;
		int currentIndex = 0;
		int numDigits = input.get(0).length();
		while(filteredValues.size() > 1 && currentIndex < numDigits) {
			int onesCount = getOnesCountForIndex(filteredValues, currentIndex);
			int zeroesCount = filteredValues.size() - onesCount;
			if (onesCount >= zeroesCount) {
				filteredValues = filterForCharAtIndex(filteredValues, '1', currentIndex);
			} else {
				filteredValues = filterForCharAtIndex(filteredValues, '0', currentIndex);
			}
			currentIndex++;
		}
		if (filteredValues.size() == 1) {
			return Integer.parseInt(filteredValues.get(0), 2);
		}
		return -1;
		
	}
	
	public static int getCO2Rating(ArrayList<String> input) {
		ArrayList<String> filteredValues = input;
		int currentIndex = 0;
		int numDigits = input.get(0).length();
		while(filteredValues.size() > 1 && currentIndex < numDigits) {
			int onesCount = getOnesCountForIndex(filteredValues, currentIndex);
			int zeroesCount = filteredValues.size() - onesCount;
			if (zeroesCount <= onesCount) {
				filteredValues = filterForCharAtIndex(filteredValues, '0', currentIndex);
			} else {
				filteredValues = filterForCharAtIndex(filteredValues, '1', currentIndex);
			}
			currentIndex++;
		}
		if (filteredValues.size() == 1) {
			return Integer.parseInt(filteredValues.get(0), 2);
		}
		return -1;
	}
	
	public static int getOnesCountForIndex(ArrayList<String> input, int index) {
		int numOnes = 0;
		for(String line : input) {
			if(line.charAt(index) == '1') {
				numOnes++;
			}
		}
		return numOnes;
	}
	
	public static ArrayList<String> filterForCharAtIndex(ArrayList<String> input, char c, int index) {
		ArrayList<String> output = new ArrayList<String>();
		for(String line : input) {
			if (line.charAt(index) == c) {
				output.add(line);
			}
		}
		return output;
	}
}
