import java.util.ArrayList;
import java.util.Arrays;

public class Day6 {
	public static int puzzle1(ArrayList<String> input) {
		String[] starterFish = input.get(0).split(",");
		ArrayList<Integer> fishTracker = new ArrayList<Integer>();
		for(int i = 0; i < starterFish.length; i++) {
			fishTracker.add(Integer.parseInt(starterFish[i]));
		}
		//System.out.println(Arrays.toString(fishTracker.toArray()));
		for(int i = 0; i < 80; i++) {
			simulateDay(fishTracker);
			//System.out.println(Arrays.toString(fishTracker.toArray()));
		}
		return fishTracker.size();
	}
	
	public static void simulateDay(ArrayList<Integer> fishTracker) {
		int trackerLength = fishTracker.size();
		for(int i = 0; i < trackerLength; i++) {
			int trackerNum = fishTracker.get(i);
			trackerNum--;
			if (trackerNum == -1) {
				trackerNum = 6;
				fishTracker.add(8);
			}
			fishTracker.set(i,  trackerNum);
		}
	}
	
	// 
	public static long puzzle2(ArrayList<String> input) {
		String[] starterFish = input.get(0).split(",");
		long[] fishTracker = new long[9];
		for(int i = 0; i < starterFish.length; i++) {
			fishTracker[Integer.parseInt(starterFish[i])]++;
		}
		for(int i = 0; i < 256; i++) {
			fishTracker = simulateDayPerformance(fishTracker);
		}
		return Arrays.stream(fishTracker).sum();
	}
	
	public static long[] simulateDayPerformance(long[] todaysFish) {
		long[] tomorrowsFish = new long[9];
		// 0 day fish need to create new fish
		tomorrowsFish[8] = todaysFish[0];
		tomorrowsFish[6] = todaysFish[0];
		for(int i = 1; i < 9; i++) {
			tomorrowsFish[i - 1] += todaysFish[i];
		}
		return tomorrowsFish;
	} 
}
