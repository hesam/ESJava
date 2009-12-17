#! /bin/sh
SOLVERDIR=../src/polyglot/ext/esj/solver
java -ea -cp .:classes:../classes:$SOLVERDIR/kodkod/jar/kodkod.jar -Djava.library.path=$SOLVERDIR/x86-mac $1 
