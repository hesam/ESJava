package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.types.*;
import polyglot.util.*;

import java.util.List;

public class ESJFieldClosureCall_c extends Call_c implements ESJFieldClosureCall {


    public ESJFieldClosureCall_c(Position pos, Receiver target, String name, List arguments) {
        super(pos, target, name, arguments);
    }
}
