package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;
import java.util.*;

import kodkod.ast.IntExpression;

public interface ESJObject {

    public ESJList allInstances2();
    public LogVar var_log();
    public boolean isQuantifyVar();
    public ESJObject old();
    public boolean isOld();
    public void relationize();
    public Object clone();
    public boolean isCloned();
    public void result(Object r);
}
