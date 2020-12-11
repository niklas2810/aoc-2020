#!/bin/bash

mkdir tmp build
cd tmp
cmake -DCMAKE_BUILD_TYPE=Release ..
if [ "$#" -gt 0 ]; then
  make day$1
else
  cmake --build .
fi
mv day* ../build/
cd ..
rm -rf ./tmp
