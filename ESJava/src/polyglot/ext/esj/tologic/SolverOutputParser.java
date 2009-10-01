// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 SolverOutput.g 2009-10-01 15:28:25

package polyglot.ext.esj.tologic;

import java.util.HashMap;
import org.antlr.runtime.CommonTokenStream;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SolverOutputParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PLUS", "MINUS", "MULT", "DIV", "COMMA", "EQUALS", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", "VCHAR", "LOWER", "UPPER", "DIGIT", "SPACE", "'*** PROBLEM 1 ***'", "'*** END ***'", "'---STATS---'", "'UNSATISFIABLE'", "'SATISFIABLE'", "'---OUTCOME---'", "'TRIVIALLY_'", "'---INSTANCE---'", "'relations:'", "'_'", "'A'"
    };
    public static final int COMMA=8;
    public static final int SPACE=18;
    public static final int MINUS=5;
    public static final int VCHAR=14;
    public static final int T__28=28;
    public static final int T__23=23;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int LOWER=15;
    public static final int T__19=19;
    public static final int LBRACKET=10;
    public static final int T__22=22;
    public static final int LBRACE=12;
    public static final int T__29=29;
    public static final int RBRACE=13;
    public static final int EQUALS=9;
    public static final int UPPER=16;
    public static final int EOF=-1;
    public static final int T__27=27;
    public static final int RBRACKET=11;
    public static final int T__24=24;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int DIV=7;
    public static final int DIGIT=17;
    public static final int PLUS=4;
    public static final int MULT=6;

    // delegates
    // delegators
    static ArrayList models;
    public ArrayList models() { return models; }

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
                parser.solutions();
            } catch (RecognitionException e)  {
                e.printStackTrace();
            }
        }



    // $ANTLR start "solutions"
    // SolverOutput.g:50:1: solutions returns [ArrayList models] : (s= solution )+ ;
    public final void solutions() throws RecognitionException {

        ArrayList s = null;


        try {
            // SolverOutput.g:50:38: ( (s= solution )+ )
            // SolverOutput.g:51:5: (s= solution )+
            {
             models = new ArrayList(); 
            // SolverOutput.g:52:7: (s= solution )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=SPACE && LA1_0<=19)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // SolverOutput.g:52:9: s= solution
            	    {
            	    pushFollow(FOLLOW_solution_in_solutions176);
            	    s=solution();

            	    state._fsp--;

            	     models.add(s); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
    }
    // $ANTLR end "solutions"


    // $ANTLR start "solution"
    // SolverOutput.g:54:1: solution returns [ArrayList model] : header (o= unsatOutcome | o= satOutcome sol= instance ) stats ;
    public final ArrayList solution() throws RecognitionException {
        ArrayList model = null;

        boolean o = false;

        HashMap sol = null;


        try {
            // SolverOutput.g:54:36: ( header (o= unsatOutcome | o= satOutcome sol= instance ) stats )
            // SolverOutput.g:55:5: header (o= unsatOutcome | o= satOutcome sol= instance ) stats
            {
            pushFollow(FOLLOW_header_in_solution199);
            header();

            state._fsp--;

            // SolverOutput.g:55:12: (o= unsatOutcome | o= satOutcome sol= instance )
            int alt2=2;
            alt2 = dfa2.predict(input);
            switch (alt2) {
                case 1 :
                    // SolverOutput.g:55:14: o= unsatOutcome
                    {
                    pushFollow(FOLLOW_unsatOutcome_in_solution207);
                    o=unsatOutcome();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SolverOutput.g:55:33: o= satOutcome sol= instance
                    {
                    pushFollow(FOLLOW_satOutcome_in_solution215);
                    o=satOutcome();

                    state._fsp--;

                    pushFollow(FOLLOW_instance_in_solution221);
                    sol=instance();

                    state._fsp--;


                    }
                    break;

            }

            pushFollow(FOLLOW_stats_in_solution225);
            stats();

            state._fsp--;

             model = new ArrayList(); model.add(o); model.add(sol); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return model;
    }
    // $ANTLR end "solution"


    // $ANTLR start "header"
    // SolverOutput.g:58:1: header : spaces '*** PROBLEM 1 ***' ;
    public final void header() throws RecognitionException {
        try {
            // SolverOutput.g:58:8: ( spaces '*** PROBLEM 1 ***' )
            // SolverOutput.g:58:10: spaces '*** PROBLEM 1 ***'
            {
            pushFollow(FOLLOW_spaces_in_header244);
            spaces();

            state._fsp--;

            match(input,19,FOLLOW_19_in_header246); 

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
    // $ANTLR end "header"


    // $ANTLR start "end"
    // SolverOutput.g:60:1: end : '*** END ***' spaces ;
    public final void end() throws RecognitionException {
        try {
            // SolverOutput.g:60:5: ( '*** END ***' spaces )
            // SolverOutput.g:60:7: '*** END ***' spaces
            {
            match(input,20,FOLLOW_20_in_end255); 
            pushFollow(FOLLOW_spaces_in_end257);
            spaces();

            state._fsp--;


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
    // $ANTLR end "end"


    // $ANTLR start "stats"
    // SolverOutput.g:62:1: stats : spaces '---STATS---' ( VCHAR )* ;
    public final void stats() throws RecognitionException {
        try {
            // SolverOutput.g:62:7: ( spaces '---STATS---' ( VCHAR )* )
            // SolverOutput.g:62:9: spaces '---STATS---' ( VCHAR )*
            {
            pushFollow(FOLLOW_spaces_in_stats266);
            spaces();

            state._fsp--;

            match(input,21,FOLLOW_21_in_stats268); 
            // SolverOutput.g:62:30: ( VCHAR )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==VCHAR) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // SolverOutput.g:62:30: VCHAR
            	    {
            	    match(input,VCHAR,FOLLOW_VCHAR_in_stats270); 

            	    }
            	    break;

            	default :
            	    break loop3;
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
    // $ANTLR end "stats"


    // $ANTLR start "unsatOutcome"
    // SolverOutput.g:64:1: unsatOutcome returns [boolean o] : outcome 'UNSATISFIABLE' ;
    public final boolean unsatOutcome() throws RecognitionException {
        boolean o = false;

        try {
            // SolverOutput.g:64:34: ( outcome 'UNSATISFIABLE' )
            // SolverOutput.g:65:5: outcome 'UNSATISFIABLE'
            {
            pushFollow(FOLLOW_outcome_in_unsatOutcome289);
            outcome();

            state._fsp--;

            match(input,22,FOLLOW_22_in_unsatOutcome291); 
             o = false; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return o;
    }
    // $ANTLR end "unsatOutcome"


    // $ANTLR start "satOutcome"
    // SolverOutput.g:68:1: satOutcome returns [boolean o] : outcome 'SATISFIABLE' ;
    public final boolean satOutcome() throws RecognitionException {
        boolean o = false;

        try {
            // SolverOutput.g:68:32: ( outcome 'SATISFIABLE' )
            // SolverOutput.g:69:5: outcome 'SATISFIABLE'
            {
            pushFollow(FOLLOW_outcome_in_satOutcome320);
            outcome();

            state._fsp--;

            match(input,23,FOLLOW_23_in_satOutcome322); 
             o = true; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return o;
    }
    // $ANTLR end "satOutcome"


    // $ANTLR start "outcome"
    // SolverOutput.g:72:1: outcome : spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )? ;
    public final void outcome() throws RecognitionException {
        try {
            // SolverOutput.g:72:9: ( spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )? )
            // SolverOutput.g:72:11: spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )?
            {
            pushFollow(FOLLOW_spaces_in_outcome341);
            spaces();

            state._fsp--;

            match(input,24,FOLLOW_24_in_outcome343); 
            pushFollow(FOLLOW_spaces_in_outcome345);
            spaces();

            state._fsp--;

            // SolverOutput.g:72:41: ( 'TRIVIALLY_' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==25) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // SolverOutput.g:72:41: 'TRIVIALLY_'
                    {
                    match(input,25,FOLLOW_25_in_outcome347); 

                    }
                    break;

            }


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
    // $ANTLR end "outcome"


    // $ANTLR start "instance"
    // SolverOutput.g:74:1: instance returns [HashMap rels] : spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE ;
    public final HashMap instance() throws RecognitionException {
        HashMap rels = null;

        HashMap rs = null;


        try {
            // SolverOutput.g:74:33: ( spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE )
            // SolverOutput.g:75:5: spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE
            {
            pushFollow(FOLLOW_spaces_in_instance366);
            spaces();

            state._fsp--;

            match(input,26,FOLLOW_26_in_instance368); 
            pushFollow(FOLLOW_spaces_in_instance370);
            spaces();

            state._fsp--;

            match(input,27,FOLLOW_27_in_instance372); 
            match(input,LBRACE,FOLLOW_LBRACE_in_instance374); 
            pushFollow(FOLLOW_relations_in_instance380);
            rs=relations();

            state._fsp--;

            match(input,RBRACE,FOLLOW_RBRACE_in_instance382); 
             rels = rs; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return rels;
    }
    // $ANTLR end "instance"


    // $ANTLR start "relations"
    // SolverOutput.g:78:1: relations returns [HashMap rs] : r= relation ( COMMA r= relation )* ;
    public final HashMap relations() throws RecognitionException {
        HashMap rs = null;

        SolverOutputParser.relation_return r = null;


        try {
            // SolverOutput.g:78:32: (r= relation ( COMMA r= relation )* )
            // SolverOutput.g:79:5: r= relation ( COMMA r= relation )*
            {
            pushFollow(FOLLOW_relation_in_relations417);
            r=relation();

            state._fsp--;

             rs = new HashMap(); rs.put((r!=null?r.rn:null),(r!=null?r.rv:null)); 
            // SolverOutput.g:80:9: ( COMMA r= relation )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==COMMA) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // SolverOutput.g:80:10: COMMA r= relation
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_relations431); 
            	    pushFollow(FOLLOW_relation_in_relations437);
            	    r=relation();

            	    state._fsp--;

            	     rs.put((r!=null?r.rn:null),(r!=null?r.rv:null)); 

            	    }
            	    break;

            	default :
            	    break loop5;
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
        return rs;
    }
    // $ANTLR end "relations"

    public static class relation_return extends ParserRuleReturnScope {
        public String rn;
        public ArrayList rv;
    };

    // $ANTLR start "relation"
    // SolverOutput.g:82:1: relation returns [String rn, ArrayList rv] : n= name EQUALS v= value ;
    public final SolverOutputParser.relation_return relation() throws RecognitionException {
        SolverOutputParser.relation_return retval = new SolverOutputParser.relation_return();
        retval.start = input.LT(1);

        String n = null;

        ArrayList v = null;


        try {
            // SolverOutput.g:82:44: (n= name EQUALS v= value )
            // SolverOutput.g:83:6: n= name EQUALS v= value
            {
            pushFollow(FOLLOW_name_in_relation465);
            n=name();

            state._fsp--;

            match(input,EQUALS,FOLLOW_EQUALS_in_relation467); 
            pushFollow(FOLLOW_value_in_relation473);
            v=value();

            state._fsp--;

             retval.rn = n; retval.rv = v; 

            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end "relation"


    // $ANTLR start "name"
    // SolverOutput.g:86:1: name returns [String n] : spaces (a= LOWER | a= LOWER d1= number '_' ) d= number ;
    public final String name() throws RecognitionException {
        String n = null;

        Token a=null;
        String d1 = null;

        String d = null;


        try {
            // SolverOutput.g:86:25: ( spaces (a= LOWER | a= LOWER d1= number '_' ) d= number )
            // SolverOutput.g:87:6: spaces (a= LOWER | a= LOWER d1= number '_' ) d= number
            {
            pushFollow(FOLLOW_spaces_in_name503);
            spaces();

            state._fsp--;

            // SolverOutput.g:87:13: (a= LOWER | a= LOWER d1= number '_' )
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // SolverOutput.g:87:14: a= LOWER
                    {
                    a=(Token)match(input,LOWER,FOLLOW_LOWER_in_name510); 
                     n = a.getText(); 

                    }
                    break;
                case 2 :
                    // SolverOutput.g:87:49: a= LOWER d1= number '_'
                    {
                    a=(Token)match(input,LOWER,FOLLOW_LOWER_in_name520); 
                    pushFollow(FOLLOW_number_in_name526);
                    d1=number();

                    state._fsp--;

                    match(input,28,FOLLOW_28_in_name528); 
                     n = a.getText() + d1 + "_"; 

                    }
                    break;

            }

            pushFollow(FOLLOW_number_in_name548);
            d=number();

            state._fsp--;

             n = n + d; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return n;
    }
    // $ANTLR end "name"


    // $ANTLR start "value"
    // SolverOutput.g:90:1: value returns [ArrayList v] : l= list ;
    public final ArrayList value() throws RecognitionException {
        ArrayList v = null;

        ArrayList l = null;


        try {
            // SolverOutput.g:90:29: (l= list )
            // SolverOutput.g:91:6: l= list
            {
            pushFollow(FOLLOW_list_in_value573);
            l=list();

            state._fsp--;

             v = l; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return v;
    }
    // $ANTLR end "value"


    // $ANTLR start "list"
    // SolverOutput.g:94:1: list returns [ArrayList l] : ( LBRACKET RBRACKET | LBRACKET is= listItems RBRACKET );
    public final ArrayList list() throws RecognitionException {
        ArrayList l = null;

        ArrayList is = null;


        try {
            // SolverOutput.g:94:28: ( LBRACKET RBRACKET | LBRACKET is= listItems RBRACKET )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==LBRACKET) ) {
                int LA7_1 = input.LA(2);

                if ( (LA7_1==RBRACKET) ) {
                    alt7=1;
                }
                else if ( (LA7_1==LBRACKET) ) {
                    alt7=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 7, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // SolverOutput.g:95:6: LBRACKET RBRACKET
                    {
                    match(input,LBRACKET,FOLLOW_LBRACKET_in_list603); 
                    match(input,RBRACKET,FOLLOW_RBRACKET_in_list605); 
                     l = new ArrayList(); 

                    }
                    break;
                case 2 :
                    // SolverOutput.g:97:6: LBRACKET is= listItems RBRACKET
                    {
                    match(input,LBRACKET,FOLLOW_LBRACKET_in_list623); 
                    pushFollow(FOLLOW_listItems_in_list629);
                    is=listItems();

                    state._fsp--;

                    match(input,RBRACKET,FOLLOW_RBRACKET_in_list631); 
                     l = is; 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return l;
    }
    // $ANTLR end "list"


    // $ANTLR start "listItems"
    // SolverOutput.g:100:1: listItems returns [ArrayList is] : a= listItem ( COMMA b= listItem )* ;
    public final ArrayList listItems() throws RecognitionException {
        ArrayList is = null;

        ArrayList a = null;

        ArrayList b = null;


        try {
            // SolverOutput.g:100:33: (a= listItem ( COMMA b= listItem )* )
            // SolverOutput.g:101:6: a= listItem ( COMMA b= listItem )*
            {
            pushFollow(FOLLOW_listItem_in_listItems663);
            a=listItem();

            state._fsp--;

             is = new ArrayList(); is.add(a); 
            // SolverOutput.g:102:9: ( COMMA b= listItem )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==COMMA) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // SolverOutput.g:102:10: COMMA b= listItem
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_listItems677); 
            	    pushFollow(FOLLOW_listItem_in_listItems683);
            	    b=listItem();

            	    state._fsp--;

            	     is.add(b); 

            	    }
            	    break;

            	default :
            	    break loop8;
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
        return is;
    }
    // $ANTLR end "listItems"


    // $ANTLR start "listItem"
    // SolverOutput.g:104:1: listItem returns [ArrayList i] : (a= binaryTuple | a= ternaryTuple ) ;
    public final ArrayList listItem() throws RecognitionException {
        ArrayList i = null;

        ArrayList a = null;


        try {
            // SolverOutput.g:104:31: ( (a= binaryTuple | a= ternaryTuple ) )
            // SolverOutput.g:105:6: (a= binaryTuple | a= ternaryTuple )
            {
            // SolverOutput.g:105:6: (a= binaryTuple | a= ternaryTuple )
            int alt9=2;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // SolverOutput.g:105:8: a= binaryTuple
                    {
                    pushFollow(FOLLOW_binaryTuple_in_listItem716);
                    a=binaryTuple();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SolverOutput.g:105:26: a= ternaryTuple
                    {
                    pushFollow(FOLLOW_ternaryTuple_in_listItem724);
                    a=ternaryTuple();

                    state._fsp--;


                    }
                    break;

            }

             i = a ; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return i;
    }
    // $ANTLR end "listItem"


    // $ANTLR start "binaryTuple"
    // SolverOutput.g:108:1: binaryTuple returns [ArrayList t] : LBRACKET a= atom COMMA b= atom RBRACKET ;
    public final ArrayList binaryTuple() throws RecognitionException {
        ArrayList t = null;

        int a = 0;

        int b = 0;


        try {
            // SolverOutput.g:108:34: ( LBRACKET a= atom COMMA b= atom RBRACKET )
            // SolverOutput.g:109:6: LBRACKET a= atom COMMA b= atom RBRACKET
            {
            match(input,LBRACKET,FOLLOW_LBRACKET_in_binaryTuple754); 
            pushFollow(FOLLOW_atom_in_binaryTuple760);
            a=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_binaryTuple762); 
            pushFollow(FOLLOW_atom_in_binaryTuple768);
            b=atom();

            state._fsp--;

            match(input,RBRACKET,FOLLOW_RBRACKET_in_binaryTuple770); 
             t = new ArrayList();
                      t.add(a); t.add(b); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return t;
    }
    // $ANTLR end "binaryTuple"


    // $ANTLR start "ternaryTuple"
    // SolverOutput.g:113:1: ternaryTuple returns [ArrayList t] : LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET ;
    public final ArrayList ternaryTuple() throws RecognitionException {
        ArrayList t = null;

        int a = 0;

        int b = 0;

        int c = 0;


        try {
            // SolverOutput.g:113:35: ( LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET )
            // SolverOutput.g:114:6: LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET
            {
            match(input,LBRACKET,FOLLOW_LBRACKET_in_ternaryTuple800); 
            pushFollow(FOLLOW_atom_in_ternaryTuple806);
            a=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_ternaryTuple808); 
            pushFollow(FOLLOW_atom_in_ternaryTuple814);
            b=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_ternaryTuple816); 
            pushFollow(FOLLOW_atom_in_ternaryTuple822);
            c=atom();

            state._fsp--;

            match(input,RBRACKET,FOLLOW_RBRACKET_in_ternaryTuple824); 
             t = new ArrayList();
                      t.add(a); t.add(b); t.add(c); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return t;
    }
    // $ANTLR end "ternaryTuple"


    // $ANTLR start "atom"
    // SolverOutput.g:118:1: atom returns [int n] : spaces 'A' d= number ;
    public final int atom() throws RecognitionException {
        int n = 0;

        String d = null;


        try {
            // SolverOutput.g:118:21: ( spaces 'A' d= number )
            // SolverOutput.g:119:6: spaces 'A' d= number
            {
            pushFollow(FOLLOW_spaces_in_atom851);
            spaces();

            state._fsp--;

            match(input,29,FOLLOW_29_in_atom853); 
            pushFollow(FOLLOW_number_in_atom859);
            d=number();

            state._fsp--;

             n = Integer.parseInt(d); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return n;
    }
    // $ANTLR end "atom"


    // $ANTLR start "letter"
    // SolverOutput.g:122:1: letter : ( LOWER | UPPER );
    public final void letter() throws RecognitionException {
        try {
            // SolverOutput.g:122:8: ( LOWER | UPPER )
            // SolverOutput.g:
            {
            if ( (input.LA(1)>=LOWER && input.LA(1)<=UPPER) ) {
                input.consume();
                state.errorRecovery=false;
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                throw mse;
            }


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
    // $ANTLR end "letter"


    // $ANTLR start "number"
    // SolverOutput.g:124:1: number returns [String n] : (d= DIGIT )+ ;
    public final String number() throws RecognitionException {
        String n = null;

        Token d=null;

        try {
            // SolverOutput.g:124:27: ( (d= DIGIT )+ )
            // SolverOutput.g:125:9: (d= DIGIT )+
            {
             n = ""; 
            // SolverOutput.g:125:22: (d= DIGIT )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==DIGIT) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // SolverOutput.g:125:24: d= DIGIT
            	    {
            	    d=(Token)match(input,DIGIT,FOLLOW_DIGIT_in_number911); 
            	     n = n + d.getText(); 

            	    }
            	    break;

            	default :
            	    if ( cnt10 >= 1 ) break loop10;
                        EarlyExitException eee =
                            new EarlyExitException(10, input);
                        throw eee;
                }
                cnt10++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return n;
    }
    // $ANTLR end "number"


    // $ANTLR start "spaces"
    // SolverOutput.g:127:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // SolverOutput.g:127:8: ( ( SPACE )* )
            // SolverOutput.g:127:10: ( SPACE )*
            {
            // SolverOutput.g:127:10: ( SPACE )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==SPACE) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // SolverOutput.g:127:10: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces925); 

            	    }
            	    break;

            	default :
            	    break loop11;
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
    // $ANTLR end "spaces"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA9 dfa9 = new DFA9(this);
    static final String DFA2_eotS =
        "\7\uffff";
    static final String DFA2_eofS =
        "\7\uffff";
    static final String DFA2_minS =
        "\4\22\1\26\2\uffff";
    static final String DFA2_maxS =
        "\2\30\2\31\1\27\2\uffff";
    static final String DFA2_acceptS =
        "\5\uffff\1\1\1\2";
    static final String DFA2_specialS =
        "\7\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\5\uffff\1\2",
            "\1\1\5\uffff\1\2",
            "\1\3\3\uffff\1\5\1\6\1\uffff\1\4",
            "\1\3\3\uffff\1\5\1\6\1\uffff\1\4",
            "\1\5\1\6",
            "",
            ""
    };

    static final short[] DFA2_eot = DFA.unpackEncodedString(DFA2_eotS);
    static final short[] DFA2_eof = DFA.unpackEncodedString(DFA2_eofS);
    static final char[] DFA2_min = DFA.unpackEncodedStringToUnsignedChars(DFA2_minS);
    static final char[] DFA2_max = DFA.unpackEncodedStringToUnsignedChars(DFA2_maxS);
    static final short[] DFA2_accept = DFA.unpackEncodedString(DFA2_acceptS);
    static final short[] DFA2_special = DFA.unpackEncodedString(DFA2_specialS);
    static final short[][] DFA2_transition;

    static {
        int numStates = DFA2_transitionS.length;
        DFA2_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA2_transition[i] = DFA.unpackEncodedString(DFA2_transitionS[i]);
        }
    }

    class DFA2 extends DFA {

        public DFA2(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 2;
            this.eot = DFA2_eot;
            this.eof = DFA2_eof;
            this.min = DFA2_min;
            this.max = DFA2_max;
            this.accept = DFA2_accept;
            this.special = DFA2_special;
            this.transition = DFA2_transition;
        }
        public String getDescription() {
            return "55:12: (o= unsatOutcome | o= satOutcome sol= instance )";
        }
    }
    static final String DFA6_eotS =
        "\5\uffff";
    static final String DFA6_eofS =
        "\5\uffff";
    static final String DFA6_minS =
        "\1\17\1\21\1\11\2\uffff";
    static final String DFA6_maxS =
        "\1\17\1\21\1\34\2\uffff";
    static final String DFA6_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA6_specialS =
        "\5\uffff}>";
    static final String[] DFA6_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\7\uffff\1\2\12\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "87:13: (a= LOWER | a= LOWER d1= number '_' )";
        }
    }
    static final String DFA9_eotS =
        "\13\uffff";
    static final String DFA9_eofS =
        "\13\uffff";
    static final String DFA9_minS =
        "\1\12\2\22\1\21\1\10\2\22\1\21\1\10\2\uffff";
    static final String DFA9_maxS =
        "\1\12\2\35\2\21\2\35\2\21\2\uffff";
    static final String DFA9_acceptS =
        "\11\uffff\1\1\1\2";
    static final String DFA9_specialS =
        "\13\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1",
            "\1\2\12\uffff\1\3",
            "\1\2\12\uffff\1\3",
            "\1\4",
            "\1\5\10\uffff\1\4",
            "\1\6\12\uffff\1\7",
            "\1\6\12\uffff\1\7",
            "\1\10",
            "\1\12\2\uffff\1\11\5\uffff\1\10",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "105:6: (a= binaryTuple | a= ternaryTuple )";
        }
    }
 

    public static final BitSet FOLLOW_solution_in_solutions176 = new BitSet(new long[]{0x00000000000C0002L});
    public static final BitSet FOLLOW_header_in_solution199 = new BitSet(new long[]{0x0000000001040000L});
    public static final BitSet FOLLOW_unsatOutcome_in_solution207 = new BitSet(new long[]{0x0000000000240000L});
    public static final BitSet FOLLOW_satOutcome_in_solution215 = new BitSet(new long[]{0x0000000004040000L});
    public static final BitSet FOLLOW_instance_in_solution221 = new BitSet(new long[]{0x0000000000240000L});
    public static final BitSet FOLLOW_stats_in_solution225 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_header244 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_header246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_end255 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_spaces_in_end257 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_stats266 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_stats268 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_VCHAR_in_stats270 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_outcome_in_unsatOutcome289 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_unsatOutcome291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_outcome_in_satOutcome320 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_satOutcome322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_outcome341 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_outcome343 = new BitSet(new long[]{0x0000000002040000L});
    public static final BitSet FOLLOW_spaces_in_outcome345 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_25_in_outcome347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_instance366 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_instance368 = new BitSet(new long[]{0x0000000008040000L});
    public static final BitSet FOLLOW_spaces_in_instance370 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_instance372 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LBRACE_in_instance374 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_relations_in_instance380 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RBRACE_in_instance382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relation_in_relations417 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_relations431 = new BitSet(new long[]{0x0000000000048000L});
    public static final BitSet FOLLOW_relation_in_relations437 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_name_in_relation465 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_EQUALS_in_relation467 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_value_in_relation473 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_name503 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_LOWER_in_name510 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_LOWER_in_name520 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_number_in_name526 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_name528 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_number_in_name548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_value573 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_list603 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_list605 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_list623 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_listItems_in_list629 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_list631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listItem_in_listItems663 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_listItems677 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_listItem_in_listItems683 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_binaryTuple_in_listItem716 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryTuple_in_listItem724 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_binaryTuple754 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_atom_in_binaryTuple760 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_binaryTuple762 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_atom_in_binaryTuple768 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_binaryTuple770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_ternaryTuple800 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple806 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_ternaryTuple808 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple814 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_ternaryTuple816 = new BitSet(new long[]{0x0000000020040000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple822 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_ternaryTuple824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_atom851 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_atom853 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_number_in_atom859 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_letter0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIGIT_in_number911 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_SPACE_in_spaces925 = new BitSet(new long[]{0x0000000000040002L});

}