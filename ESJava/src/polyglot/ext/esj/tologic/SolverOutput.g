grammar SolverOutput;

tokens {
	PLUS 	= '+' ;
	MINUS	= '-' ;
	MULT	= '*' ;
	DIV	= '/' ;

    COMMA = ',' ;
    EQUALS = '=' ;
    LBRACKET = '[' ;
    RBRACKET = ']' ;
    LBRACE = '{' ;
    RBRACE = '}' ;

}

@parser::header {
package polyglot.ext.esj.tologic;

import java.util.HashMap;
import org.antlr.runtime.CommonTokenStream;
}

@lexer::header {
package polyglot.ext.esj.tologic;

import org.antlr.runtime.CommonTokenStream;
}

@members {
    public static void main(String[] args) throws Exception {
        SolverOutputLexer lex = new SolverOutputLexer(new ANTLRFileStream(args[0]));
       	CommonTokenStream tokens = new CommonTokenStream(lex);

        SolverOutputParser parser = new SolverOutputParser(tokens);

        try {
            parser.solutions();
        } catch (RecognitionException e)  {
            e.printStackTrace();
        }
    }
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

solutions returns [ArrayList models] :
    { $models = new ArrayList(); } 
      ( s = solution { $models.add($s.model); } )+ ;

solution returns [ArrayList model] : 
    header ( o = unsatOutcome | o = satOutcome sol = instance ) st = stats end
        { $model = new ArrayList(); 
          $model.add($o.o); $model.add($sol.rels); $model.add($st.st); } ;

header : spaces '*** PROBLEM 1 ***' ;

end : spaces '*** END ***' ;

stats returns [String st] : 
    spaces '---STATS---' t = statText { $st = $t.st; } ;

unsatOutcome returns [boolean o] : 
    outcome 'UNSATISFIABLE' 
        { $o = false; } ;

satOutcome returns [boolean o] : 
    outcome 'SATISFIABLE'
        { $o = true; } ;

outcome : spaces '---OUTCOME---' spaces 'TRIVIALLY_'? ;

instance returns [HashMap rels] : 
    spaces '---INSTANCE---' spaces 'relations:' LBRACE rs = relations RBRACE 
        { $rels = $rs.rs; } ;

relations returns [HashMap rs] :   
    r = relation { $rs = new HashMap(); $rs.put($r.rn,$r.rv); } 
        (COMMA r = relation { $rs.put($r.rn,$r.rv); } )* ;

relation returns [String rn, ArrayList rv] : 
     n = name EQUALS v = value 
        { $rn = $n.n; $rv = $v.v; } ;

name returns [String n] : 
     spaces (a = LOWER { $n = $a.getText(); } | a = LOWER d1 = number '_' { $n = $a.getText() + $d1.n + "_"; } )  
        d = number { $n = $n + $d.n; } ;

value returns [ArrayList v] : 
     l = list 
        { $v = $l.l; } ;

list returns [ArrayList l] : 
     LBRACKET RBRACKET 
        { $l = new ArrayList(); }
   | LBRACKET is = listItems RBRACKET
        { $l = $is.is; } ;

listItems returns [ArrayList is]: 
     a = listItem { $is = new ArrayList(); $is.add(a); } 
        (COMMA b = listItem { $is.add(b); } )* ;

listItem returns [ArrayList i]:     
     ( a = binaryTuple | a = ternaryTuple | a = unaryTuple )
        { $i = $a.t ; } ;

binaryTuple returns [ArrayList t]: 
     LBRACKET a = atom COMMA b = atom RBRACKET 
        { $t = new ArrayList();
          $t.add(a); $t.add(b); } ;

ternaryTuple returns [ArrayList t]:  
     LBRACKET a = atom COMMA b = atom COMMA c = atom RBRACKET
        { $t = new ArrayList();
          $t.add(a); $t.add(b); $t.add(c); } ;

unaryTuple returns [ArrayList t]: 
     LBRACKET a = atom RBRACKET 
        { $t = new ArrayList();
          $t.add(a); } ;

atom returns [int n]:
     spaces 'A' d = number
        { $n = Integer.parseInt($d.n); } ;

letter : LOWER | UPPER ;

number returns [String n] :
        { $n = ""; } ( d = DIGIT { $n = $n + $d.getText(); } )+ ;

statText returns [String st] :
        ns+ 'primary variables: ' number 'parsing time: ' number
        ns+ 'translation time: ' n = number { $st = "translation time: " + $n.n + " ms "; }
        ns+ 'solving time: ' n = number { $st = $st + "\tsolving time: " + $n.n + " ms"; } ns+ ;

spaces : SPACE* ;

ns : ~('*') ;

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

SPACE : ( '\t' | ' ' | '\r' | '\n'| '\u000C' ) 	{ $channel = HIDDEN; } ;

DIGIT	: '0'..'9' ;

LOWER : 'a'..'z' ;

UPPER : 'A'..'Z' ;


