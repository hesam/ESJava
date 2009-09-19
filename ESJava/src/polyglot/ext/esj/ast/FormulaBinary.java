package polyglot.ext.esj.ast;

import polyglot.ast.Precedence;
import polyglot.ast.Binary;

// binary expressions appearing in predicates
public interface FormulaBinary extends Binary {

    public static final Operator ALL       = new Operator("all", Precedence.RELATIONAL);
    public static final Operator SOME       = new Operator("some", Precedence.RELATIONAL);
    public static final Operator NO       = new Operator("no", Precedence.RELATIONAL);
    public static final Operator ONE       = new Operator("one", Precedence.RELATIONAL);
    public static final Operator LONE       = new Operator("lone", Precedence.RELATIONAL);

    // closure ops (FIXME: move...)
    public static final Operator RFLX       = new Operator(".*", Precedence.RELATIONAL);
    public static final Operator NONRFLX       = new Operator(".^", Precedence.RELATIONAL);
    public static final Operator MAP        = new Operator(".<", Precedence.RELATIONAL);

}
