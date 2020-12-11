#include <iostream>
#include <chrono>

using namespace std;

int main()
{
    int p1{}, p2{};
  auto start = chrono::high_resolution_clock::now();

  auto end = chrono::high_resolution_clock::now();
  auto duration = chrono::duration<double, milli>(end-start).count();

  cout << "PART ONE: " << p1 << endl;
  cout << "PART TWO: " << p2 << endl;
  cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
