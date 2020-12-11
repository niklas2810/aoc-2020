#!/bin/bash

mkdir tmp build
cd tmp
cmake ..
cmake --build .
mv day* ../build/
cd ..
rm -rf ./tmp
