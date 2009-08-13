package polyglot.ext.esj.ast;

import polyglot.ast.*;
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
public class ESJNodeFactory_c extends JL5NodeFactory_c
    implements ESJNodeFactory {

    // TODO:  Implement factory methods for new AST nodes.
    // TODO:  Override factory methods for overriden AST nodes.
    // TODO:  Override factory methods for AST nodes with new extension nodes.
    public ESJPredMethodDecl ESJPredMethodDecl(Position pos, FlagAnnotations flags, 
					       TypeNode returnType, String name, List formals, 
					       List throwTypes, Block body, List paramTypes, 
					       String quantMtdId, boolean quantKind, 
					       String quantVarN, List quantVarD, 
					       LocalInstance quantVarI, Expr quantListExpr, 
					       ESJQuantifyClauseExpr quantClauseExpr) {	
    	return new ESJPredMethodDecl_c(pos, flags, returnType, name, formals, throwTypes, body, paramTypes, quantMtdId, quantKind, quantVarN, quantVarD, quantVarI, quantListExpr, quantClauseExpr);
    }


    public ESJLogPredMethodDecl ESJLogPredMethodDecl(Position pos, FlagAnnotations flags, 
						     TypeNode returnType, String name, 
						     List formals, List throwTypes, Block body, 
						     List paramTypes) {	
    	return new ESJLogPredMethodDecl_c(pos, flags, returnType, name, formals, throwTypes, body, paramTypes);
    }

    public ESJEnsuredMethodDecl ESJEnsuredMethodDecl(Position pos, FlagAnnotations flags,
						     TypeNode returnType, String name,
						     List formals, List throwTypes, Block body, 
						     List paramTypes, Expr ensuresExpr, 
						     JL5Formal catchFormal) {
	return new ESJEnsuredMethodDecl_c(pos, flags, returnType, name, formals, throwTypes, body, paramTypes, ensuresExpr, catchFormal);

    }

    public ESJQuantifyExpr ESJQuantifyExpr(Position pos, boolean quantKind, String quantVarN, 
					   List quantVarD, LocalInstance quantVarI, 
					   Expr quantListExpr, Expr quantClauseExpr) {
	return new ESJQuantifyExpr_c(pos, quantKind, quantVarN, quantVarD, quantVarI, quantListExpr, quantClauseExpr);
    }

    public ESJQuantifyTypeExpr ESJQuantifyTypeExpr(Position pos, CanonicalTypeNode theType) {
	return new ESJQuantifyTypeExpr_c(pos, theType);
    }

    public ESJBinary ESJBinary(Position pos, Expr left, Binary.Operator op, Expr right) {
	return new ESJBinary_c(pos, left, op,  right);
    }

}
