import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Day4 {
	public static int puzzle1(ArrayList<String> input) {
		String numbers = input.get(0);
		String[] numbersArr = numbers.split(",");
		int[] bingoCalls = new int[numbersArr.length];
		for(int i = 0; i < numbersArr.length; i++) {
			bingoCalls[i] = Integer.parseInt(numbersArr[i]);
		}
		ArrayList<int[][]> boards = new ArrayList<int[][]>();
		HashMap<Integer, ArrayList<BingoData>> numberMetadata = new HashMap<>();
		setUpBoards(boards, numberMetadata, input);
		for(int i = 0; i < bingoCalls.length; i++) {
			int nextCall = bingoCalls[i];
			if (numberMetadata.containsKey(nextCall)) {
				ArrayList<BingoData> boardData = numberMetadata.get(nextCall);
				for(BingoData bingoData: boardData) {
					int[][] board = boards.get(bingoData.boardIndex);
					board[bingoData.row][bingoData.column] = -1;
					boolean won = checkForWin(board);
					if (won) {
						return calculateScore(board) * nextCall;
					}
				}
			}
		}
		return -1;
	}
	
	public static int puzzle2(ArrayList<String> input) {
		String numbers = input.get(0);
		String[] numbersArr = numbers.split(",");
		int[] bingoCalls = new int[numbersArr.length];
		for(int i = 0; i < numbersArr.length; i++) {
			bingoCalls[i] = Integer.parseInt(numbersArr[i]);
		}
		ArrayList<int[][]> boards = new ArrayList<int[][]>();
		HashMap<Integer, ArrayList<BingoData>> numberMetadata = new HashMap<>();
		Set<Integer> allWinners = new HashSet<Integer>();
		int lastWinnerScore = -1;
		setUpBoards(boards, numberMetadata, input);
		for(int i = 0; i < bingoCalls.length; i++) {
			int nextCall = bingoCalls[i];
			if (numberMetadata.containsKey(nextCall)) {
				ArrayList<BingoData> boardData = numberMetadata.get(nextCall);
				for(BingoData bingoData: boardData) {
					int[][] board = boards.get(bingoData.boardIndex);
					board[bingoData.row][bingoData.column] = -1;
					boolean won = checkForWin(board);
					if (won && !allWinners.contains(bingoData.boardIndex)) {
						lastWinnerScore = calculateScore(board) * nextCall;
						allWinners.add(bingoData.boardIndex);
					}
				}
			}
		}
		return lastWinnerScore;
	}
	
	private static void setUpBoards(ArrayList<int[][]> boards, HashMap<Integer, ArrayList<BingoData>> numberMetadata, ArrayList<String> input) {
		for (int i = 2; i < input.size(); i+=6) {
			int[][] board = new int[5][5];
			for(int j = 0; j < 5; j++) {
				StringTokenizer tokenizer = new StringTokenizer(input.get(i + j));
				for(int k = 0; k < 5; k++) {
					int value = Integer.parseInt(tokenizer.nextToken());
					board[j][k] = value;
					if (!numberMetadata.containsKey(value)) {
						numberMetadata.put(value, new ArrayList<BingoData>());
					}
					numberMetadata.get(value).add(new BingoData(boards.size(), j, k));
				}
			}
			boards.add(board);
		}
	}

	
	private static boolean checkForWin(int[][] board) {
		for(int i = 0; i < 5; i++) {
			if(board[i][0] + board[i][1] + board[i][2] + board[i][3] + board[i][4] == -5) {
				return true;
			}
			if (board[0][i] + board[1][i] + board[2][i] + board[3][i] + board[4][i] == -5) {
				return true;
			}
		}
		return false;
	}
	
	private static int calculateScore(int[][] board) {
		int score = 0;

		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				if (board[i][j] != -1) {
					score += board[i][j];
				}
			}
		}
		return score;
	}
}
