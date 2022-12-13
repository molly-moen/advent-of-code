import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day12.txt");
  const matrix = createMatrix(rawLines);
  const start = findStart(matrix);
  const possiblePaths = new Array<path>();
  var shortestPath = matrix.length * matrix[0].length;
  const coordinatesChecked = Array<Array<number>>();
  for(let i = 0; i < matrix.length; i++) {
    coordinatesChecked.push(new Array<number>(matrix[i].length));
  }
  possiblePaths.push({
    steps: 0,
    matrix: copyMatrix(matrix),
    location: start,
    isAtEnd: false
  });
  while(possiblePaths.length > 0) {
    const nextPath = possiblePaths.pop();
    if(nextPath.isAtEnd) {
      if (shortestPath > nextPath.steps) {
        shortestPath = nextPath.steps;
      }
    } else if (nextPath.steps < shortestPath) {
      // only keep checking if this could be the shortest path
      // and we haven't seen this coordinate with fewer steps previously
      const steps = coordinatesChecked[nextPath.location.x][nextPath.location.y]
      if (steps === undefined || steps > nextPath.steps) {
        coordinatesChecked[nextPath.location.x][nextPath.location.y] = nextPath.steps;
        addPaths(possiblePaths, nextPath);
      }
    }
  }
  return shortestPath;
};

const puzzle2 = () => {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day12.txt");
  const matrix = createMatrix(rawLines);
  const possiblePaths = new Array<path>();
  var shortestPath = matrix.length * matrix[0].length;
  const coordinatesChecked = Array<Array<number>>();
  for(let i = 0; i < matrix.length; i++) {
    coordinatesChecked.push(new Array<number>(matrix[i].length));
  }
  findAndAddStarts(matrix, possiblePaths);
  while(possiblePaths.length > 0) {
    const nextPath = possiblePaths.pop();
    if(nextPath.isAtEnd) {
      if (shortestPath > nextPath.steps) {
        shortestPath = nextPath.steps;
      }
    } else if (nextPath.steps < shortestPath) {
      // only keep checking if this could be the shortest path
      // and we haven't seen this coordinate with fewer steps previously
      const steps = coordinatesChecked[nextPath.location.x][nextPath.location.y]
      if (steps === undefined || steps > nextPath.steps) {
        coordinatesChecked[nextPath.location.x][nextPath.location.y] = nextPath.steps;
        addPaths(possiblePaths, nextPath);
      }
    }
  }
  return shortestPath;
};

function findAndAddStarts(matrix: string[][], possiblePaths: path[]) {
  for(let i = 0; i < matrix.length; i++) {
    for(let j = 0; j < matrix[i].length; j++) {
      if (matrix[i][j] === 'a' || matrix[i][j] === 'S') {
        possiblePaths.push({
          steps: 0,
          matrix: copyMatrix(matrix),
          location: {x: i, y: j},
          isAtEnd: false
        });
      }
    }
  }
}

function addPaths(possiblePaths: Array<path>, path: path) {
  const currentX = path.location.x;
  const currentY = path.location.y;
  addPathIfViable({x: currentX - 1, y: currentY}, possiblePaths, path);
  addPathIfViable({x: currentX + 1, y: currentY}, possiblePaths, path);
  addPathIfViable({x: currentX, y: currentY - 1}, possiblePaths, path);
  addPathIfViable({x: currentX, y: currentY + 1}, possiblePaths, path);
}

function addPathIfViable( nextCoordinate, possiblePaths: Array<path>, currentPath: path) {
  const currentCoordinate = currentPath.location;
  const matrix = currentPath.matrix;
  var isAtEnd = false;
  // valid coordinate
  if(nextCoordinate.x >= 0 && nextCoordinate.x < matrix.length && nextCoordinate.y >= 0 && nextCoordinate.y < matrix[nextCoordinate.x].length) {
    let currentLocation = matrix[currentCoordinate.x][currentCoordinate.y];
    let nextLocation = matrix[nextCoordinate.x][nextCoordinate.y];
    if (currentLocation === 'S') {
      currentLocation = 'a';
    }
    if (nextLocation === 'E') {
      nextLocation = 'z';
      isAtEnd = true;
    }
    // don't go to an already visited location
    if (nextLocation !== 'X' && currentLocation !== 'E') {
      // next location can only be 1 above current
      if (nextLocation.charCodeAt(0) - currentLocation.charCodeAt(0) <= 1) {
        const newMatrix = copyMatrix(matrix);
        // mark previous location visited
        newMatrix[currentCoordinate.x][currentCoordinate.y] = 'X';
        possiblePaths.push({
          location: nextCoordinate,
          matrix: newMatrix,
          steps: currentPath.steps + 1,
          isAtEnd: isAtEnd
        })
      }
    } else {
      //console.log('invalid coordinate');
    }
  } else {
    //console.log('invalid coordinate');
  }
}

function findStart(matrix: string[][]): coordinate {
  for(let i = 0; i < matrix.length; i++) {
    for(let j = 0; j < matrix[i].length; j++) {
      if (matrix[i][j] === 'S') {
        return {x: i, y: j};
      }
    }
  }
  return {x: -1, y: -1};
}

function createMatrix(rawLines: string[]) {
  const matrix = new Array<Array<string>>();
  rawLines.forEach(line => {
    const newLine = new Array<string>();
    for(let i = 0; i < line.length; i++) {
      newLine.push(line.charAt(i));
    }
    matrix.push(newLine);
  });
  return matrix;
}

function copyMatrix(matrix: string[][]): string[][] {
  const copiedMatrix = new Array<Array<string>>();
  for(let i = 0; i < matrix.length; i++) {
    copiedMatrix[i] = new Array<string>();
    for(let j = 0; j < matrix[i].length; j++) {
      copiedMatrix[i][j] = matrix[i][j];
    }
  }
  return copiedMatrix;
}

interface coordinate {
  x: number,
  y: number
}

interface path {
  steps: number,
  matrix: string[][],
  location: coordinate,
  isAtEnd: boolean
}

export default {puzzle1, puzzle2};