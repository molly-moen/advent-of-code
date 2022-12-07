import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day4.txt");
  let result = 0;
  rawLines.forEach(line => {
    const pairs = line.split(",");
    const range1 = getRange(pairs[0]);
    const range2 = getRange(pairs[1]);
    let lowRange = range1;
    let highRange = range2;
    if (range1[0] > range2[0]) {
      lowRange = range2;
      highRange = range1;
    }
    if (lowRange[0] < highRange[0] && lowRange[1] >= highRange[1]) {
      result++;
    } else if (lowRange[0] === highRange[0]) { // always contains?
      result++;
    }
  });
  console.log(result);
};

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day4.txt");
  let result = 0;
  rawLines.forEach(line => {
    const pairs = line.split(",");
    const range1 = getRange(pairs[0]);
    const range2 = getRange(pairs[1]);
    let lowRange = range1;
    let highRange = range2;
    if (range1[0] > range2[0]) {
      lowRange = range2;
      highRange = range1;
    }
    if (lowRange[0] === highRange[0]) { // always overlaps
      result++;
    } else if (lowRange[1] >= highRange[0]) {
      result++;
    }
  });
  console.log(result);
};

function getRange(pair) {
  return pair.split("-").map((x) => parseInt(x));
}

export default {puzzle1, puzzle2};