#!/usr/bin/env bash

ARCHNAME="linux-amd64"
if [ "$(uname)" == "Darwin" ]; then
    ARCHNAME="osx-universal"
fi
echo $ARCHNAME

if [ ! -e binaries ]; then
    wget https://github.com/duckdb/duckdb/releases/download/v0.10.1/libduckdb-$ARCHNAME.zip
    unzip libduckdb-$ARCHNAME.zip -d binaries
    rm libduckdb-$ARCHNAME.zip
fi

export DUCKDB_HOME="$(pwd)/binaries"