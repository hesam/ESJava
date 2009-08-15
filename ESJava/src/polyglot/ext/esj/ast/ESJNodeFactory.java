package polyglot.ext.esj.ast;

import polyglot.ast.*;
import polyglot.types.*;
import polyglot.ext.jl.ast.*;
import polyglot.types.*;
import polyglot.ext.esj.types.*;
import polyglot.util.*;
import java.util.*;
import polyglot.ext.jl5.ast.*;
import polyglot.ext.jl5.parse.JL5Name;
import polyglot.ext.jl5.types.FlagAnnotations;

/**
 * NodeFactory for esj extension.
 */
public interface ESJNodeFactory extends JL5NodeFactory {
    // TODO: Declare any factory methods for new AST nodes.
    ESJPredMethodDecl ESJPredMethodDecl(Position pos, FlagAnnotations flags,
					TypeNode returnType, String name,
					List formals, List throwTypes, Block body, 
					List paramTypes, String quantMtdId, 
					boolean quantKind, String quantVarN, List quantVarD, 
					LocalInstance quantVarI, Expr quantListExpr, 
					ESJQuantifyClauseExpr quantClauseExpr);

    ESJLogPredMethodDecl ESJLogPredMethodDecl(Position pos, FlagAnnotations flags,
					      TypeNode returnType, String name,
					      List formals, List throwTypes, Block body, 
					      List paramTypes, boolean quantKind, 
					      String quantVarN, List quantVarD, 
					      LocalInstance quantVarI,  Expr quantListExpr, 
					      ESJQuantifyClauseExpr quantClauseExpr); 
    
    ESJEnsuredMethodDecl ESJEnsuredMethodDecl(Position pos, FlagAnnotations flags,
					      TypeNode returnType, String name,
					      List formals, List throwTypes, Block body, 
					      List paramTypes, Expr ensuresExpr, 
					      JL5Formal catchFormal);

    ESJQuantifyExpr ESJQuantifyExpr(Position pos, boolean quantKind, String quantVarN, List quantVarD, LocalInstance quantVarI, Expr quantListExpr, Expr quantClauseExpr);

    ESJLogQuantifyExpr ESJLogQuantifyExpr(Position pos, boolean quantKind, String quantVarN, List quantVarD, LocalInstance quantVarI, Expr quantListExpr, Expr quantClauseExpr);

    ESJQuantifyTypeExpr ESJQuantifyTypeExpr(Position pos, CanonicalTypeNode theType);

    FormulaBinary FormulaBinary(Position pos, Expr left, Binary.Operator op, Expr right);
    CmpBinary CmpBinary(Position pos, Expr left, Binary.Operator op, Expr right);

}
