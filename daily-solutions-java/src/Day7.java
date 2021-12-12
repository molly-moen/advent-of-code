import java.util.ArrayList;
import java.util.Collections;

public class Day7 {
	public static int puzzle1(ArrayList<String> input) {
		ArrayList<Integer> positions = getPositions(input);
		Collections.sort(positions);
		int minPosition = positions.get(0);
		int maxPosition = positions.get(positions.size() - 1);
		int minFuelCost = calculateFuel(positions, minPosition);
		for(int i = minPosition + 1; i <= maxPosition; i++) {
			int nextFuelCost = calculateFuel(positions, i);
			if (nextFuelCost < minFuelCost) {
				minFuelCost = nextFuelCost;
			}
		}
		return minFuelCost;
	}
	
	public static int puzzle2(ArrayList<String> input) {
		ArrayList<Integer> positions = getPositions(input);
		Collections.sort(positions);
		int minPosition = positions.get(0);
		int maxPosition = positions.get(positions.size() - 1);
		int minFuelCost = calculateFuelPuzzle2(positions, minPosition);
		for(int i = minPosition + 1; i <= maxPosition; i++) {
			int nextFuelCost = calculateFuelPuzzle2(positions, i);
			if (nextFuelCost < minFuelCost) {
				minFuelCost = nextFuelCost;
			}
		}
		return minFuelCost;
	}
	
	private static ArrayList<Integer> getPositions(ArrayList<String> input) {
		String[] stringTokens = input.get(0).split(",");
		ArrayList<Integer> tokens = new ArrayList<Integer>();
		for(int i = 0; i < stringTokens.length; i++) {
			tokens.add(Integer.parseInt(stringTokens[i]));
		}
		return tokens;
	}
	
	private static int calculateFuel(ArrayList<Integer> positions, int alignmentPoint) {
		int fuelCost = 0;
		for(int i = 0; i < positions.size(); i++) {
			fuelCost += Math.abs(positions.get(i) - alignmentPoint);
		}
		return fuelCost;
	}
	
	private static int calculateFuelPuzzle2(ArrayList<Integer> positions, int alignmentPoint) {

		int fuelCost = 0;
		for(int i = 0; i < positions.size(); i++) {
			int change = Math.abs(positions.get(i) - alignmentPoint);
			int thisFuelCost = (change + 1) * (change / 2);
			if (change % 2 == 1) {
				thisFuelCost += (change / 2) + 1;
			}
			//System.out.println("position is " + positions.get(i) + " alignment point is " + alignmentPoint + " this fuel cost is " + thisFuelCost);
			fuelCost += thisFuelCost;
		}
		return fuelCost;
	}
}
