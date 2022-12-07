import { readLinesString } from "./fileReader";

// [T]     [D]         [L]            
// [R]     [S] [G]     [P]         [H]
// [G]     [H] [W]     [R] [L]     [P]
// [W]     [G] [F] [H] [S] [M]     [L]
// [Q]     [V] [B] [J] [H] [N] [R] [N]
// [M] [R] [R] [P] [M] [T] [H] [Q] [C]
// [F] [F] [Z] [H] [S] [Z] [T] [D] [S]
// [P] [H] [P] [Q] [P] [M] [P] [F] [D]
//  1   2   3   4   5   6   7   8   9 

const stacks = new Array<Array<string>>();
stacks.push(["P", "F", "M", "Q", "W", "G", "R", "T"]);
stacks.push(["H", "F", "R"]);
stacks.push(["P", "Z", "R", "V", "G", "H", "S", "D"]);
stacks.push(["Q", "H", "P", "B", "F", "W", "G"]);
stacks.push(["P", "S", "M", "J", "H"]);
stacks.push(["M", "Z", "T", "H", "S", "R", "P", "L"]);
stacks.push(["P", "T", "H", "N", "M", "L"]);
stacks.push(["F", "D", "Q", "R"]);
stacks.push(["D", "S", "C", "N", "L", "P", "H"]);

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day5.txt");
  rawLines.forEach(line => {
    const values = line.split(" ");
    const numToMove = parseInt(values[1]);
    const source = parseInt(values[3]) - 1;
    const dest = parseInt(values[5]) - 1;
    for(let i = 0; i < numToMove; i++) {
      stacks[dest].push(stacks[source].pop());
    }
  });
  let result = '';
  stacks.forEach(stack => {
    result = result + stack[stack.length - 1];
  });
  console.log(result);
}

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day5.txt");
  rawLines.forEach(line => {
    const values = line.split(" ");
    const numToMove = parseInt(values[1]);
    const source = parseInt(values[3]) - 1;
    const dest = parseInt(values[5]) - 1;
    const sourceLen = stacks[source].length;
    for(let i = numToMove; i > 0; i--) {
      stacks[dest].push(stacks[source][sourceLen - i]);
    }
    stacks[source].splice(sourceLen - numToMove, numToMove);
  });
  let result = '';
  stacks.forEach(stack => {
    result = result + stack[stack.length - 1];
  });
  console.log(result);
}

export default {puzzle1, puzzle2};

