#include "parsing.h"

vector<string> readFileAsLines(const string& filename)
{
  ifstream infile{ filename };
  string line;

  vector<string> result;

  while (getline(infile, line))
    result.push_back(line);

  return result;
}
