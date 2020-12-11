#include <iostream>
#include <chrono>

#include "parsing.h"

using namespace std;

bool isTree(const string &line, int index)
{
  return line.at(index % line.length()) == '#';
}

long long findTrees(const vector<string> &lines, int xPerMove, int yPerMove)
{
  int trees = 0;
  int x = 0;
  int size = lines.size();
  for (int y = 0; y < size; y += yPerMove)
  {
    if (isTree(lines.at(y), x))
      trees++;
    x += xPerMove;
  }
  return trees;
}

int main()
{
  int p1{}, p2{};
  auto start = chrono::high_resolution_clock::now();

  auto lines = readFileAsLines("../input/day03.txt");

  auto t0 = findTrees(lines, 1, 1);
  auto t1 = findTrees(lines, 3, 1);
  auto t2 = findTrees(lines, 5, 1);
  auto t3 = findTrees(lines, 7, 1);
  auto t4 = findTrees(lines, 1, 2);

  p1 = t1;
  p2 =  (t0 * t1 * t2 * t3 * t4);

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end - start).count();

  cout << "PART ONE: " << p1 << endl;
  cout << "PART TWO: " << p2 << endl;
  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
