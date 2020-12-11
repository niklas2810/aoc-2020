#include <iostream>
#include <chrono>
#include <algorithm>

#include "parsing.h"

using namespace std;

int performShiftOps(const string &in, char up)
{
  int iterator{(int)in.size() - 1};
  int result{};

  for (const auto &c : in)
  {
    if (c == up)
      result += 1 << iterator;

    --iterator;
  }

  return result;
}

int parseTicket(const string &data)
{
  string rowString{data.substr(0, 7)};
  string colString{data.substr(7, 3)};

  int row{performShiftOps(rowString, 'B')};
  int col{performShiftOps(colString, 'R')};

  return row*8+col;
}

int main()
{
  auto start = chrono::high_resolution_clock::now();

  vector<string> input{readFileAsLines("../input/day05.txt")};

  if (input.size() == 0)
  {
    cout << "ERROR: Input file not found!\n";
    exit(EXIT_FAILURE);
  }

  vector<int> ids(input.size());
  transform(input.begin(), input.end(), ids.begin(), [](const string &data) -> int { return parseTicket(data); });
  sort(ids.begin(), ids.end());

  int min{ids[0]};
  int max{ids[ids.size() - 1]};
  std::cout << "PART ONE: " << max << std::endl;

  for(int i = 1; i < (int)ids.size(); ++i) {
    if(ids[i] != min+i) {
      cout << "PART TWO: " << min+i << endl;
      break;
    }
  }

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end - start).count();

  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
