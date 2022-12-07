import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const line = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day6.txt")[0];
  let last4CharVals = new Array<number>();
  for(let i = 0; i < 4; i++) {
    last4CharVals.push(line.charCodeAt(i));
  }
  if (isArrayUnique(last4CharVals)) {
    return 4;
  }
  for(let i = 4; i < line.length; i++) {
    last4CharVals.shift();
    last4CharVals.push(line.charCodeAt(i));
    if (isArrayUnique(last4CharVals)) {
      return i + 1;
    }
  }
}

const puzzle2 = () => {
  const line = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day6.txt")[0];
  let lastCharVals = new Array<number>();
  for(let i = 0; i < 14; i++) {
    lastCharVals.push(line.charCodeAt(i));
  }
  if (isArrayUnique(lastCharVals)) {
    return 14;
  }
  for(let i = 14; i < line.length; i++) {
    lastCharVals.shift();
    lastCharVals.push(line.charCodeAt(i));
    if (isArrayUnique(lastCharVals)) {
      return i + 1;
    }
  }
}

function isArrayUnique(originalArray: Array<number>) {
  const array = [...originalArray];
  array.sort();
  for(let i = 1; i < array.length; i++) {
    if (array[i] == array[i - 1]) {
      return false;
    }
  }
  return true;
}

export default {puzzle1, puzzle2};