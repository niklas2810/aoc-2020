#!/bin/bash

for file in *.cpp; do
    clang++ -Wall -Wextra -Werror "$file" -o "$file.out"
    compile=$?
    if [ $compile != 0 ]; then
    echo "Failed to compile $file!"
    exit 1
    fi
done

echo "Success!"