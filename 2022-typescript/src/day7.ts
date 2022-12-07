import { readLinesString } from "./fileReader";

const puzzle1 = () => {
  const rootDir: directory = getDirectoryStructure();
  let sizeResult = 0;
  const directoriesToCheck = new Array<directory>();
  directoriesToCheck.push(rootDir);
  while(directoriesToCheck.length > 0) {
    const directory = directoriesToCheck.pop();
    const dirSize = getDirectorySize(directory);
    if (dirSize <= 100000) {
      sizeResult += dirSize;
    }
    directory.items.forEach(item => {
      if ("parent" in item) {
        directoriesToCheck.push(item);
      }
    })
  }
  return sizeResult;
}

const puzzle2 = () => {
  const rootDir: directory = getDirectoryStructure();
  const totalSize = getDirectorySize(rootDir);
  const minDeleteSize = 30000000 - (70000000 - totalSize);
  let directorySizeToDelete = totalSize;
  const directoriesToCheck = new Array<directory>();
  directoriesToCheck.push(rootDir);
  while(directoriesToCheck.length > 0) {
    const directory = directoriesToCheck.pop();
    const dirSize = getDirectorySize(directory);
    if (dirSize >= minDeleteSize && dirSize < directorySizeToDelete) {
      directorySizeToDelete = dirSize;
    }
    directory.items.forEach(item => {
      if ("parent" in item) {
        directoriesToCheck.push(item);
      }
    })
  }
  return directorySizeToDelete;
}

function getDirectoryStructure() {
  const rawLines = readLinesString("/Users/mollymoen/Documents/GitHub/advent-of-code/2022-typescript/src/inputs/day7.txt");
  const rootDir: directory = {
    name: '/',
    items: new Array<item>(),
    parent: undefined
  }
  createDirectories(rawLines, rootDir);
  return rootDir;
}

function createDirectories(rawLines: Array<string>, rootDir: directory) {
  let currentIndex = 1;
  let currentDirectory = rootDir;
  while(currentIndex < rawLines.length) {
    //console.log(`currentIndex is ${currentIndex}`);
    const line = rawLines[currentIndex];
    if (currentIndex === 587) {
      console.log(line);
    }
    if (line.startsWith("$")) {
      const sections = line.split(" ");
      if (sections[1] === "cd") {
        const newDir = sections[2];
        if (newDir === '..') {
          currentDirectory = currentDirectory.parent;
        } else {
          let newDirectory: directory;
          for(let i = 0; i < currentDirectory.items.length; i++) {
            const nextItem = currentDirectory.items[i];
            if ("parent" in nextItem && nextItem.name === newDir) {
              newDirectory = nextItem;
            }
          }
          if (!newDirectory) {
            console.log("no directory to change to!");
          } else {
            currentDirectory = newDirectory;
          }
        }
        currentIndex++;
      } else { // ls
        currentIndex++;
        // we should only do this if we haven't already listed the directory
        if (currentDirectory.items.length === 0) {
          let nextLine = rawLines[currentIndex];
          while(nextLine && !nextLine.startsWith("$")) {
            const data = nextLine.split(" ");
            if (data[0] === "dir") {
              let newDirectory = {
                name: data[1],
                items: new Array<item>(),
                parent: currentDirectory
              }
              currentDirectory.items.push(newDirectory);
            } else {
              const newFile = {
                name: data[1],
                size: parseInt(data[0])
              }
              currentDirectory.items.push(newFile);
            }
            currentIndex++;
            nextLine = rawLines[currentIndex];
          }
        }
      }
    } else {
      console.log(`skipping index ${currentIndex}`);
      currentIndex++;
    }
  }
}

function getDirectorySize(directory: directory) {
  let size = 0;
  directory.items.forEach(item => {
    if ("size" in item) {
      // it's a file
      size += item.size;
    } else {
      size += getDirectorySize(item);
    }
  });
  return size;
}

interface directory {
  name: String;
  items: Array<item>;
  parent: directory | undefined;
}

type item = directory | fileData;

interface fileData {
  name: String;
  size: number;
}

export default {puzzle1, puzzle2};