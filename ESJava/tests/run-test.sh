#! /bin/sh
SOLVERDIR=../src/polyglot/ext/esj/solver/kodkodi-1.1.4
java -ea -cp .:classes:../classes:$SOLVERDIR/jar/kodkod.jar:$SOLVERDIR/jar/antlr-runtime-3.1.1.jar -Djava.library.path=$SOLVERDIR/x86-mac $1 
