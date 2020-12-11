#!/bin/bash

for file in *.cpp; do
    filename=$(basename -- "$file")
    filename="${filename%.*}"
    echo "$filename"
    clang++ -Wall -Wextra -Wpedantic -Werror -flto -fuse-ld=lld -O3 "$file" -o "$filename.out"
    compile=$?
    if [ $compile != 0 ]; then
        echo "Failed to compile $file!"
        exit 1
    fi
done

echo "Success!"
