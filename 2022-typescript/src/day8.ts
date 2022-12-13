import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day8.txt");
  const matrix = createMatrix(rawLines);
  let treeCount = 0;
  // top row and bottom row
  treeCount += matrix[0].length + matrix[matrix.length - 1].length;
  // middle columns (subtract 4 for corners)
  treeCount += (matrix.length * 2) - 4;
  for(let i = 1; i < matrix.length - 1; i++) {
    for(let j = 1; j < matrix[i].length - 1; j++) {
      if (canSeeTree(matrix, i, j)) {
        treeCount++;
      }
    }
  }
  return treeCount;
}

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day8.txt");
  const matrix = createMatrix(rawLines);
  let maxScore = 0;
  for(let i = 1; i < matrix.length - 1; i++) {
    for(let j = 1; j < matrix[i].length - 1; j++) {
      let score = getScoreForTree(matrix, i, j);
      if (score > maxScore) {
        maxScore = score;
      }
    }
  }
  return maxScore;
}

function createMatrix(rawLines: string[]) {
  const matrix = new Array<Array<number>>();
  rawLines.forEach(line => {
    const newLine = new Array<number>();
    for(let i = 0; i < line.length; i++) {
      newLine.push(parseInt(line.charAt(i)));
    }
    matrix.push(newLine);
  });
  return matrix;
}

function getScoreForTree(matrix: number[][], treeX: number, treeY: number) {
  const treeHeight = matrix[treeX][treeY];
  let score = 1;
  score *= treeCountForRow(matrix[treeX], treeY - 1, -1, -1, treeHeight);
  score *= treeCountForRow(matrix[treeX], treeY + 1, matrix[treeX].length, 1, treeHeight);
  score *= treeCountForColumn(matrix, treeY, treeX - 1, -1, -1, treeHeight);
  score *= treeCountForColumn(matrix, treeY, treeX + 1, matrix.length, 1, treeHeight);
  return score;
}

function treeCountForRow(row: number[], start: number, end: number, change: number, treeHeight: number) {
  let foundBlocker = false;
  let yIndex = start;
  let treeCount = 0;
  while(!foundBlocker && yIndex != end) {
    if (row[yIndex] >= treeHeight) {
      foundBlocker = true;
    }
    treeCount++;
    yIndex += change;
  }
  return treeCount;
}

function treeCountForColumn(matrix: number[][], treeY: number, start: number, end: number, change: number, treeHeight: number) {
  let foundBlocker = false;
  let xIndex = start;
  let treeCount = 0;
  while(!foundBlocker && xIndex != end) {
    if (matrix[xIndex][treeY] >= treeHeight) {
      foundBlocker = true;
    }
    treeCount++;
    xIndex += change
  }
  return treeCount;
}

function canSeeTree(matrix: number[][], treeX: number, treeY: number) {
  const treeHeight = matrix[treeX][treeY];
  let row = matrix[treeX];
  let visibleLeft = isVisibleInRow(row, 0, treeY, treeHeight);
  if (visibleLeft) {
    return true;
  }
  let visibleRight = isVisibleInRow(row, treeY + 1, row.length, treeHeight);
  if (visibleRight) {
    return true;
  }
  let visibleAbove = isVisibleInColumn(matrix, 0, treeX, treeY, treeHeight);
  if (visibleAbove) {
    return true;
  }
  let visibleBelow = isVisibleInColumn(matrix, treeX + 1, matrix.length, treeY, treeHeight);
  return visibleBelow;
}

function isVisibleInRow(row: number[], start: number, end: number, treeHeight: number) {
  let isVisible = true;
  for(let i = start; i < end; i++) {
    if (row[i] >= treeHeight) {
      isVisible = false;
    }
  }
  return isVisible;
}

function isVisibleInColumn(matrix: number[][], start: number, end: number, treeY: number, treeHeight: number) {
  let isVisible = true;
  for(let i = start; i < end; i++) {
    if (matrix[i][treeY] >= treeHeight) {
      isVisible = false;
    }
  }
  return isVisible;
}

export default {puzzle1, puzzle2};