#!/bin/bash

for file in *.cpp; do
    filename=$(basename -- "$file")
    filename="${filename%.*}"
    echo "$filename"
    clang++ -Wall -Wextra -Werror "$file" -o "$filename.out"
    compile=$?
    if [ $compile != 0 ]; then
    echo "Failed to compile $file!"
    exit 1
    fi
done

echo "Success!"