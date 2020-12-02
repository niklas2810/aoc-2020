
#include <fstream>
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <numeric>
#include <chrono>

using namespace std;

vector<string> readFileAsLines(const string& filename)
{
  ifstream infile{ filename };
  string line;

  vector<string> result;

  while (getline(infile, line))
    result.push_back(line);

  return result;
}

int main()
{
  auto start = chrono::high_resolution_clock::now();

  auto lines{ readFileAsLines("input/day01.txt") };
  vector<int> numbers{};

  transform(lines.begin(), lines.end(), back_inserter(numbers), [](const std::string& line)->int {return stoi(line); });

  sort(numbers.begin(), numbers.end());

  for (int first : numbers) {
    for (int second : numbers) {
      if (second > first) break;

      if (first + second == 2020)
        cout << "PART ONE: " << first << " + " << second << " = 2020, Multiplied: " << (first * second) << endl;

      for (int third : numbers) {
        if (third > second) break;

        if (first + second + third == 2020)
          cout << "PART TWO: " << first << " + " << second << " + "
          << third << " = 2020, Multiplied: " << (first * second * third) << endl;
      }
    }
  }

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end-start).count();

  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
