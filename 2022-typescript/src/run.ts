import {parsePoem} from "./stringParser";

class Run {
  public static start() {
    console.log(parsePoem());
  }
}

Run.start();