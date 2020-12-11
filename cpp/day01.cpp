#include <iostream>
#include <algorithm>
#include <numeric>
#include <chrono>

#include "parsing.h"

int main()
{
  int p1{}, p2{};
  auto start = chrono::high_resolution_clock::now();

  auto lines{readFileAsLines("../input/day01.txt")};
  vector<int> numbers{};

  transform(lines.begin(), lines.end(), back_inserter(numbers), [](const std::string &line) -> int { return stoi(line); });

  sort(numbers.begin(), numbers.end());

  for (int first : numbers)
  {
    for (int second : numbers)
    {
      if (second > first)
        break;

      if (first + second == 2020)
        p1 = (first * second);

      for (int third : numbers)
      {
        if (third > second)
          break;

        if (first + second + third == 2020)
          p2 = (first * second * third);
      }
    }
  }

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end - start).count();

  cout << "PART ONE: " << p1 << endl;
  cout << "PART TWO: " << p2 << endl;
  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
