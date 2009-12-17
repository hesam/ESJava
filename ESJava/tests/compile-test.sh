#! /bin/sh
SOLVERDIR=../src/polyglot/ext/esj/solver
../bin/jlc -assert -ext esj -cp classes:$SOLVERDIR/kodkod/jar/kodkod.jar $1 $2 
