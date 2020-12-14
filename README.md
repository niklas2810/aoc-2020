<div style="width:100%;padding:0px;margin:0px" align="center">
    <h1>Advent of Code 2020</h1>
    <br>
    <a href="https://github.com/niklas2810/aoc-2020/tree/main/java">
    <img alt="java build" src="https://img.shields.io/github/workflow/status/niklas2810/aoc-2020/Build%20using%20Maven?logo=github&style=for-the-badge&label=Java%20Build"/></a>
    <a href="https://github.com/niklas2810/aoc-2020/tree/main/cpp">
        <img alt="cpp build" src="https://img.shields.io/github/workflow/status/niklas2810/aoc-2020/Build%20C++%20Code?label=C%2B%2B%20Build&logo=github&style=for-the-badge"/></a>
        <a href="https://app.codacy.com/gh/niklas2810/aoc-2020/dashboard"><img alt="codacy analysis" src="https://img.shields.io/codacy/grade/7c0e18e900264a2ea46674ffebd0328c?logo=codacy&style=for-the-badge"/></a>
    <br>
    <br>   
</div>


Hey there! In this repository, you will be able to find my solutions
for this years' [Advent of Code](https://adventofcode.com).

All of my solutions will be **written in Java and/or C++**. You can find an overview of which puzzle I've already solved [here](#solved-puzzles)! In case there are any questions regarding the project, feel free to [open an issue](https://github.com/niklas2810/aoc-2020/issues/new)!


## Setup Instructions

First off, clone the project: `git clone https://github.com/niklas2810/aoc-2020.git`

**Java:**

1. Make sure you have [Maven](https://maven.apache.org/download.cgi) and [Java](https://adoptopenjdk.net) (>= v14) installed and added to your PATH. You can test this by typing `mvn -version` and `java -version` into your terminal.
2. Open the java directory in the IDE of your choice (I'm using IntelliJ) and run it! 
3. Alternatively, build it from your terminal via `mvn package`. You will find an executable named `aoc-2020-1.0-shaded.jar` in the `target` directory.
4. You can execute one of the build scripts as well.

**CPP:**

1. Install [Visual Studio Code](https://code.visualstudio.com/) and **clang/g++** (if you are using Windows, you can run the project using WSL, too!). To install the build tools, run: `sudo apt-get install build-essential clang -y`
2. (_Recommended_) You can execute the compile script in the cpp directory: `./compile.sh`. You will be able to find an executable for each day named `day<num>.out`. **This requires clang to be installed.**
3. Alternatively, build the executable from a specific day: `clang++ day<num>.cpp`/`g++ day<num>.cpp`.
4. Execute the file!


## Solved Puzzles

- _Puzzle solved in this language:_ ✔️
- _Puzzle **not** solved in this language (yet):_ ❌
- _Unknown status:_ ❔

Puzzle Day / Name | Java | C++
--- | --- | ---
Day 01 : Report Repair | ✔️ | ✔️
Day 02 : Password Philosophy | ✔️ | ✔️
Day 03 : Toboggan Trajectory | ✔️ | ✔️
Day 04 : Passport Processing | ✔️ | ❌
Day 05 : Binary Boarding | ✔️ | ✔️
Day 06 : Custom Customs | ✔️ | ❔
Day 07 : Handy Haversacks | ✔️ | ❔
Day 08 : Handheld Halting | ✔️ | ❔
Day 09 : Encoding Error | ✔️ | ❔
Day 10 : Adapter Array | ✔️ | ❔
Day 11 : Seating System | ✔️ | ❔
Day 12 : Rain Risk | ✔️ | ❔
Day 13 : Shuttle Search | ✔️ | ❔
Day 14 : ??? | ❔ | ❔
Day 15 : ??? | ❔ | ❔
Day 16 : ??? | ❔ | ❔
Day 17 : ??? | ❔ | ❔
Day 18 : ??? | ❔ | ❔
Day 19 : ??? | ❔ | ❔
Day 20 : ??? | ❔ | ❔
Day 21 : ??? | ❔ | ❔
Day 22 : ??? | ❔ | ❔
Day 22 : ??? | ❔ | ❔
Day 23 : ??? | ❔ | ❔
Day 24 : ??? | ❔ | ❔
Day 25 : ??? | ❔ | ❔


_I didn't solve Day 04 in C++ because the verification process is not complex, but rather tedious._ 