#! /bin/sh
SOLVERDIR=../src/polyglot/ext/esj/solver/kodkodi-1.1.4
../bin/jlc -assert -ext esj -cp classes:$SOLVERDIR/jar/kodkod.jar $1 $2 
