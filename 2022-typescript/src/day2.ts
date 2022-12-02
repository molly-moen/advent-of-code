import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day2.txt");
  const lines = rawLines.map((line) => parseLine(line));
  let score = 0;
  lines.forEach(line => {
    const opponent = line[0];
    const me = line[1];
    score += getScoreForRound(opponent, me);
  });
  console.log(score);
};

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day2.txt");
  const lines = rawLines.map((line) => parseLine(line));
  let score = 0;
  lines.forEach(line => {
    const opponent = line[0];
    const goal = line[1];
    let me = opponent; // default = draw
    // lose
    if (goal === 1) {
      me = opponent === 1 ? 3 : opponent - 1;
    // win
    } else if (goal === 3) {
      me = opponent === 3 ? 1 : opponent + 1;
    }
    score += getScoreForRound(opponent, me);
  })
  console.log(score);
}

const getScoreForRound = (opponent: number, me: number) => {
  let score = 0;
  // tie
  if (opponent === me) {
    score += 3;
  // win
  } else if ((me > 1 && (me - opponent) === 1) || (me === 1 && opponent === 3)) {
    score += 6;
  } 
  score += me;
  return score;
}

const parseLine = (line: string) => {
  const plays = line.split(" ");
  const result: number[] = new Array(2);
  const opponentMap = {"A": 1, "B": 2, "C": 3};
  const playerMap = {"X": 1, "Y": 2, "Z": 3};
  result[0] = opponentMap[plays[0]];
  result[1] = playerMap[plays[1]];
  return result;
}


export default {puzzle1, puzzle2};