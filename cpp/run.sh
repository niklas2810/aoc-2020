#!/bin/bash

if [ "$#" -eq 0 ]; then
  echo "ERROR: Please specify the day (e.g. 03, 11) you want to run!"
  exit 1
fi


echo "Building..."
./compile.sh $1&>/dev/null
./build/day$1
