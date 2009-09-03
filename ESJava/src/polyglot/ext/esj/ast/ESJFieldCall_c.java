package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.ext.jl.ast.*;
import polyglot.types.*;
import polyglot.util.*;

import java.util.List;

public class ESJFieldCall_c extends Call_c implements ESJFieldCall {


    public ESJFieldCall_c(Position pos, Receiver target, String name, List arguments) {
        super(pos, target, name, arguments);
    }
}
