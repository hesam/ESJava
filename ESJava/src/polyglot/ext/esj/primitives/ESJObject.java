package polyglot.ext.esj.primitives;

import polyglot.ext.esj.tologic.*;
import java.util.*;


public interface ESJObject {

    public ESJList allInstances2();
    //public LogSet allInstances2_log(); 
    public LogVar var_log();
    public boolean isQuantifyVar();
    public ESJObject old();
    public boolean isOld();
    public void relationize();
    public Object clone();
    public void result(Object r);
    //public ESJSet fieldsClosure(boolean isReflexive, java.lang.String ... fieldNs);
}
