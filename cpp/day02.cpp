#include <iostream>
#include <chrono>
#include <algorithm>

#include "parsing.h"

using namespace std;

class Policy
{
private:
    int _min;
    int _max;
    char _c;
    string _password;

    int occurences()
    {
        int res = 0;
        for (const auto &i : _password)
            if (i == _c)
                ++res;
        return res;
    }

public:
    Policy(const string &raw)
    {
        int index_of_colon = raw.find(':');
        _password = raw.substr(index_of_colon+2);
        _c = raw.at(index_of_colon - 1);

        int index_of_dash = raw.find('-');
        _min = stoi(raw.substr(0, index_of_dash));
        _max = stoi(raw.substr(index_of_dash + 1, index_of_colon - 2));
    }

    bool matchesFirstPolicy()
    {
        int occ = occurences();

        return occ >= _min && occ <= _max;
    }

    bool matchesSecondPolicy()
    {
        return ((int)_password.size() >= _min && _password.at(_min - 1) == _c) ^ ((int)_password.size() >= _max && _password.at(_max - 1) == _c);
    }
};

int main()
{
    auto start = chrono::high_resolution_clock::now();

    vector<string> lines{readFileAsLines("../input/day02.txt")};

    int first = 0;
    int second = 0;

    for (const auto &line : lines)
    {
        Policy p{line};
        if (p.matchesFirstPolicy())
            ++first;

        if (p.matchesSecondPolicy())
            ++second;
    }

    cout << "PART ONE: " << first << endl << "PART TWO: " << second << endl;

    auto end = chrono::high_resolution_clock::now();
    auto duration = chrono::duration<double, milli>(end - start).count();

    cout << "EXECUTION TIME: " << duration << "ms." << endl;
}
