import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day3.txt");
  let result = 0;
  rawLines.forEach(line => {
    const length = line.length;
    const first = Array.from(line.substring(0, (line.length / 2)));
    const second = Array.from(line.substring(line.length / 2));
    const duplicates = first.filter((value) => second.includes(value));
    result += convertCharToPriority(duplicates[0]);
  });
  console.log(result);
};

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day3.txt");
  let result = 0;
  for(let i = 0; i < rawLines.length; i+=3) {
    const charCounts = new Map<string, number>();
    for(let j = i; j < i + 3; j++) {
      const line = rawLines[j];
      const lineArr = Array.from(line).sort();
      let lastChar = lineArr[0];
      addCharToMap(lastChar, charCounts);
      for(let k = 1; k < lineArr.length; k++) {
        if (lineArr[k] != lastChar) {
          addCharToMap(lineArr[k], charCounts);
          lastChar = lineArr[k];
        }
      }
    }
    charCounts.forEach((count, character) => {
      if (count === 3) {
        result += convertCharToPriority(character);
      }
    })
  }
  console.log(result);
};

function addCharToMap(character: string, charCounts: Map<string, number>) {
  const value = charCounts.get(character);
  if (value) {
    charCounts.set(character, value + 1);
  } else {
    charCounts.set(character, 1);
  }
} 

function convertCharToPriority(character: string) {
  const asciiValue = character.charCodeAt(0);
  // lowercase
  if (asciiValue >= 97) {
    return asciiValue - 96;
  // uppercase
  } else {
    return asciiValue - 38;
  }
}


export default {puzzle1, puzzle2};