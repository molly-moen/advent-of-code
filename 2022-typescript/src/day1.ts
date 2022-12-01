import * as fs from 'fs';

export const day1 = () => {
  const lines: number[] = getLines();
  console.log(lines);
  let maxCals: number = 0;
  let currentSum: number = 0;
  let elfCals: number[] = new Array(0);
  lines.forEach(line => {
    if (Number.isNaN(line)) {
      elfCals.push(currentSum);
      currentSum = 0;
    } else {
      currentSum += line;
    }
  });
  elfCals.sort();
  const calLength = elfCals.length;
  console.log(`last 3 cals: ${elfCals[calLength - 3]}, ${elfCals[calLength - 2]}, ${elfCals[calLength - 1]}`)
  const sum = elfCals[calLength - 3] + elfCals[calLength - 2] + elfCals[calLength - 1];
  console.log(`sum: ${sum}`)
}

const getLines = () => {
  return fs.readFileSync("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/day1.txt", {encoding: "utf-8"}).split("\n").map((x) => parseInt(x));
}