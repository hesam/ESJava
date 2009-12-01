package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;
import java.util.*;

import kodkod.ast.IntExpression;

public interface ESJObject {

    public ESJList allInstances2();
    //public LogSet allInstances2_log(); 
    public LogVar var_log();
    public Log2Var var_log2();
    public boolean isQuantifyVar();
    public boolean isQuantifyVar2();
    public ESJObject old();
    public boolean isOld();
    public void relationize();
    public Object clone();
    public void result(Object r);
    //public ESJSet fieldsClosure(boolean isReflexive, java.lang.String ... fieldNs);
}
