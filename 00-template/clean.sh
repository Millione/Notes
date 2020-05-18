#!/bin/bash

# Go to Current directory
cd "$(dirname "$0")"

# Clean LaTeX Cache
find . -name "*.aux"  | xargs rm -f
find . -name "*.log"  | xargs rm -f
find . -name "*.toc"  | xargs rm -f
find . -name "*.fls"  | xargs rm -f
find . -name "*.fdb_latexmk"  | xargs rm -f
find . -name "*.synctex.gz"  | xargs rm -f
find . -name "*.nav"  | xargs rm -f
find . -name "*.out"  | xargs rm -f
find . -name "*.snm"  | xargs rm -f
find . -name "*.vrb"  | xargs rm -f