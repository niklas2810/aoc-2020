#include <iostream>
#include <chrono>

using namespace std;

int main()
{
  auto start = chrono::high_resolution_clock::now();

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end-start).count();

  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
