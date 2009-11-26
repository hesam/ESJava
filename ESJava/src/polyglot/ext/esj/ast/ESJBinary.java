package polyglot.ext.esj.ast;

import polyglot.ast.Precedence;
import polyglot.ast.Binary;

// binary expressions appearing in predicates
public interface ESJBinary extends Binary {

    public String kodkodiOp();
    public String kodkodOp();
}
