import * as fs from 'fs';

export const readLinesString = (filepath: string) => {
  return fs.readFileSync(filepath, {encoding: "utf-8"}).split("\n");
}