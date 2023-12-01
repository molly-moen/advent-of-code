import { readLinesString } from "./fileReader";

export function parsePoem(): String {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/poem.txt");
  let result = "";
  result = result + rawLines[0];
  for (let i = 1; i < rawLines.length; i++) {
    result = result + '/n' + rawLines[i];
  }
  return result;
}