#include <fstream>
#include <string>
#include <vector>

using namespace std;

vector<string> readFileAsLines(const string &filename)
{
  ifstream infile{filename};
  if (!infile)
  {
    cout << "ERROR: File \"" << filename << "\" not found!\n";
    exit(EXIT_FAILURE);
  }
  string line;

  vector<string> result;

  while (getline(infile, line))
    result.push_back(line);

  return result;
}