package polyglot.ext.esj.tologic;
// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 SolverOutput.g 2009-08-19 12:44:11

import org.antlr.runtime.*;

import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SolverOutputParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PLUS", "MINUS", "MULT", "DIV", "NUMBER", "DIGIT", "WHITESPACE"
    };
    public static final int MINUS=5;
    public static final int NUMBER=8;
    public static final int EOF=-1;
    public static final int DIV=7;
    public static final int DIGIT=9;
    public static final int PLUS=4;
    public static final int MULT=6;
    public static final int WHITESPACE=10;

    // delegates
    // delegators


        public SolverOutputParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public SolverOutputParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return SolverOutputParser.tokenNames; }
    public String getGrammarFileName() { return "SolverOutput.g"; }


        public static void main(String[] args) throws Exception {
            SolverOutputLexer lex = new SolverOutputLexer(new ANTLRFileStream(args[0]));
           	CommonTokenStream tokens = new CommonTokenStream(lex);

            SolverOutputParser parser = new SolverOutputParser(tokens);

            try {
                parser.expr();
            } catch (RecognitionException e)  {
                e.printStackTrace();
            }
        }



    // $ANTLR start "expr"
    // SolverOutput.g:29:1: expr : term ( ( PLUS | MINUS ) term )* ;
    public final void expr() throws RecognitionException {
        try {
            // SolverOutput.g:29:6: ( term ( ( PLUS | MINUS ) term )* )
            // SolverOutput.g:29:8: term ( ( PLUS | MINUS ) term )*
            {
            pushFollow(FOLLOW_term_in_expr61);
            term();

            state._fsp--;

            // SolverOutput.g:29:13: ( ( PLUS | MINUS ) term )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=PLUS && LA1_0<=MINUS)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // SolverOutput.g:29:15: ( PLUS | MINUS ) term
            	    {
            	    if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_term_in_expr76);
            	    term();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "expr"


    // $ANTLR start "term"
    // SolverOutput.g:31:1: term : factor ( ( MULT | DIV ) factor )* ;
    public final void term() throws RecognitionException {
        try {
            // SolverOutput.g:31:6: ( factor ( ( MULT | DIV ) factor )* )
            // SolverOutput.g:31:8: factor ( ( MULT | DIV ) factor )*
            {
            pushFollow(FOLLOW_factor_in_term88);
            factor();

            state._fsp--;

            // SolverOutput.g:31:15: ( ( MULT | DIV ) factor )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>=MULT && LA2_0<=DIV)) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // SolverOutput.g:31:17: ( MULT | DIV ) factor
            	    {
            	    if ( (input.LA(1)>=MULT && input.LA(1)<=DIV) ) {
            	        input.consume();
            	        state.errorRecovery=false;
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        throw mse;
            	    }

            	    pushFollow(FOLLOW_factor_in_term102);
            	    factor();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "term"


    // $ANTLR start "factor"
    // SolverOutput.g:33:1: factor : NUMBER ;
    public final void factor() throws RecognitionException {
        try {
            // SolverOutput.g:33:8: ( NUMBER )
            // SolverOutput.g:33:10: NUMBER
            {
            match(input,NUMBER,FOLLOW_NUMBER_in_factor114); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "factor"

    // Delegated rules


 

    public static final BitSet FOLLOW_term_in_expr61 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_set_in_expr65 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_term_in_expr76 = new BitSet(new long[]{0x0000000000000032L});
    public static final BitSet FOLLOW_factor_in_term88 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_set_in_term92 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_factor_in_term102 = new BitSet(new long[]{0x00000000000000C2L});
    public static final BitSet FOLLOW_NUMBER_in_factor114 = new BitSet(new long[]{0x0000000000000002L});

}