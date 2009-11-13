// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 SolverOutput.g 2009-11-13 13:10:05

package polyglot.ext.esj.tologic;

import java.util.HashMap;
import org.antlr.runtime.CommonTokenStream;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SolverOutputParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PLUS", "MINUS", "MULT", "DIV", "COMMA", "EQUALS", "LBRACKET", "RBRACKET", "LBRACE", "RBRACE", "LOWER", "UPPER", "DIGIT", "SPACE", "'*** PROBLEM 1 ***'", "'*** END ***'", "'---STATS---'", "'UNSATISFIABLE'", "'SATISFIABLE'", "'---OUTCOME---'", "'TRIVIALLY_'", "'---INSTANCE---'", "'relations:'", "'_'", "'A'", "'primary variables: '", "'parsing time: '", "'translation time: '", "'solving time: '"
    };
    public static final int COMMA=8;
    public static final int SPACE=17;
    public static final int MINUS=5;
    public static final int T__28=28;
    public static final int T__23=23;
    public static final int T__20=20;
    public static final int T__21=21;
    public static final int LOWER=14;
    public static final int T__19=19;
    public static final int LBRACKET=10;
    public static final int T__22=22;
    public static final int LBRACE=12;
    public static final int T__29=29;
    public static final int RBRACE=13;
    public static final int EQUALS=9;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int UPPER=15;
    public static final int EOF=-1;
    public static final int T__27=27;
    public static final int RBRACKET=11;
    public static final int T__32=32;
    public static final int T__24=24;
    public static final int T__26=26;
    public static final int DIV=7;
    public static final int T__25=25;
    public static final int DIGIT=16;
    public static final int PLUS=4;
    public static final int MULT=6;
    public static final int T__18=18;

    // delegates
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

                if ( (LA1_0==LOWER||(LA1_0>=SPACE && LA1_0<=26)||LA1_0==28) ) {
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
    // SolverOutput.g:54:1: solution returns [ArrayList model] : header (o= unsatOutcome | o= satOutcome sol= instance ) st= stats end ;
    public final ArrayList solution() throws RecognitionException {
        ArrayList model = null;

        boolean o = false;

        HashMap sol = null;

        String st = null;


        try {
            // SolverOutput.g:54:36: ( header (o= unsatOutcome | o= satOutcome sol= instance ) st= stats end )
            // SolverOutput.g:55:5: header (o= unsatOutcome | o= satOutcome sol= instance ) st= stats end
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

            pushFollow(FOLLOW_stats_in_solution229);
            st=stats();

            state._fsp--;

            pushFollow(FOLLOW_end_in_solution231);
            end();

            state._fsp--;

             model = new ArrayList(); 
                      model.add(o); model.add(sol); model.add(st); 

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
    // SolverOutput.g:59:1: header : spaces '*** PROBLEM 1 ***' ;
    public final void header() throws RecognitionException {
        try {
            // SolverOutput.g:59:8: ( spaces '*** PROBLEM 1 ***' )
            // SolverOutput.g:59:10: spaces '*** PROBLEM 1 ***'
            {
            pushFollow(FOLLOW_spaces_in_header250);
            spaces();

            state._fsp--;

            match(input,18,FOLLOW_18_in_header252); 

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
    // SolverOutput.g:61:1: end : spaces '*** END ***' ;
    public final void end() throws RecognitionException {
        try {
            // SolverOutput.g:61:5: ( spaces '*** END ***' )
            // SolverOutput.g:61:7: spaces '*** END ***'
            {
            pushFollow(FOLLOW_spaces_in_end261);
            spaces();

            state._fsp--;

            match(input,19,FOLLOW_19_in_end263); 

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
    // SolverOutput.g:63:1: stats returns [String st] : spaces '---STATS---' t= statText ;
    public final String stats() throws RecognitionException {
        String st = null;

        String t = null;


        try {
            // SolverOutput.g:63:27: ( spaces '---STATS---' t= statText )
            // SolverOutput.g:64:5: spaces '---STATS---' t= statText
            {
            pushFollow(FOLLOW_spaces_in_stats281);
            spaces();

            state._fsp--;

            match(input,20,FOLLOW_20_in_stats283); 
            pushFollow(FOLLOW_statText_in_stats289);
            t=statText();

            state._fsp--;

             st = t; 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return st;
    }
    // $ANTLR end "stats"


    // $ANTLR start "unsatOutcome"
    // SolverOutput.g:66:1: unsatOutcome returns [boolean o] : outcome 'UNSATISFIABLE' ;
    public final boolean unsatOutcome() throws RecognitionException {
        boolean o = false;

        try {
            // SolverOutput.g:66:34: ( outcome 'UNSATISFIABLE' )
            // SolverOutput.g:67:5: outcome 'UNSATISFIABLE'
            {
            pushFollow(FOLLOW_outcome_in_unsatOutcome309);
            outcome();

            state._fsp--;

            match(input,21,FOLLOW_21_in_unsatOutcome311); 
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
    // SolverOutput.g:70:1: satOutcome returns [boolean o] : outcome 'SATISFIABLE' ;
    public final boolean satOutcome() throws RecognitionException {
        boolean o = false;

        try {
            // SolverOutput.g:70:32: ( outcome 'SATISFIABLE' )
            // SolverOutput.g:71:5: outcome 'SATISFIABLE'
            {
            pushFollow(FOLLOW_outcome_in_satOutcome340);
            outcome();

            state._fsp--;

            match(input,22,FOLLOW_22_in_satOutcome342); 
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
    // SolverOutput.g:74:1: outcome : spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )? ;
    public final void outcome() throws RecognitionException {
        try {
            // SolverOutput.g:74:9: ( spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )? )
            // SolverOutput.g:74:11: spaces '---OUTCOME---' spaces ( 'TRIVIALLY_' )?
            {
            pushFollow(FOLLOW_spaces_in_outcome361);
            spaces();

            state._fsp--;

            match(input,23,FOLLOW_23_in_outcome363); 
            pushFollow(FOLLOW_spaces_in_outcome365);
            spaces();

            state._fsp--;

            // SolverOutput.g:74:41: ( 'TRIVIALLY_' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==24) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // SolverOutput.g:74:41: 'TRIVIALLY_'
                    {
                    match(input,24,FOLLOW_24_in_outcome367); 

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
    // SolverOutput.g:76:1: instance returns [HashMap rels] : spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE ;
    public final HashMap instance() throws RecognitionException {
        HashMap rels = null;

        HashMap rs = null;


        try {
            // SolverOutput.g:76:33: ( spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE )
            // SolverOutput.g:77:5: spaces '---INSTANCE---' spaces 'relations:' LBRACE rs= relations RBRACE
            {
            pushFollow(FOLLOW_spaces_in_instance386);
            spaces();

            state._fsp--;

            match(input,25,FOLLOW_25_in_instance388); 
            pushFollow(FOLLOW_spaces_in_instance390);
            spaces();

            state._fsp--;

            match(input,26,FOLLOW_26_in_instance392); 
            match(input,LBRACE,FOLLOW_LBRACE_in_instance394); 
            pushFollow(FOLLOW_relations_in_instance400);
            rs=relations();

            state._fsp--;

            match(input,RBRACE,FOLLOW_RBRACE_in_instance402); 
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
    // SolverOutput.g:80:1: relations returns [HashMap rs] : r= relation ( COMMA r= relation )* ;
    public final HashMap relations() throws RecognitionException {
        HashMap rs = null;

        SolverOutputParser.relation_return r = null;


        try {
            // SolverOutput.g:80:32: (r= relation ( COMMA r= relation )* )
            // SolverOutput.g:81:5: r= relation ( COMMA r= relation )*
            {
            pushFollow(FOLLOW_relation_in_relations437);
            r=relation();

            state._fsp--;

             rs = new HashMap(); rs.put((r!=null?r.rn:null),(r!=null?r.rv:null)); 
            // SolverOutput.g:82:9: ( COMMA r= relation )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==COMMA) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // SolverOutput.g:82:10: COMMA r= relation
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_relations451); 
            	    pushFollow(FOLLOW_relation_in_relations457);
            	    r=relation();

            	    state._fsp--;

            	     rs.put((r!=null?r.rn:null),(r!=null?r.rv:null)); 

            	    }
            	    break;

            	default :
            	    break loop4;
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
    // SolverOutput.g:84:1: relation returns [String rn, ArrayList rv] : n= name EQUALS v= value ;
    public final SolverOutputParser.relation_return relation() throws RecognitionException {
        SolverOutputParser.relation_return retval = new SolverOutputParser.relation_return();
        retval.start = input.LT(1);

        String n = null;

        ArrayList v = null;


        try {
            // SolverOutput.g:84:44: (n= name EQUALS v= value )
            // SolverOutput.g:85:6: n= name EQUALS v= value
            {
            pushFollow(FOLLOW_name_in_relation485);
            n=name();

            state._fsp--;

            match(input,EQUALS,FOLLOW_EQUALS_in_relation487); 
            pushFollow(FOLLOW_value_in_relation493);
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
    // SolverOutput.g:88:1: name returns [String n] : spaces (a= LOWER | a= LOWER d1= number '_' ) d= number ;
    public final String name() throws RecognitionException {
        String n = null;

        Token a=null;
        String d1 = null;

        String d = null;


        try {
            // SolverOutput.g:88:25: ( spaces (a= LOWER | a= LOWER d1= number '_' ) d= number )
            // SolverOutput.g:89:6: spaces (a= LOWER | a= LOWER d1= number '_' ) d= number
            {
            pushFollow(FOLLOW_spaces_in_name523);
            spaces();

            state._fsp--;

            // SolverOutput.g:89:13: (a= LOWER | a= LOWER d1= number '_' )
            int alt5=2;
            alt5 = dfa5.predict(input);
            switch (alt5) {
                case 1 :
                    // SolverOutput.g:89:14: a= LOWER
                    {
                    a=(Token)match(input,LOWER,FOLLOW_LOWER_in_name530); 
                     n = a.getText(); 

                    }
                    break;
                case 2 :
                    // SolverOutput.g:89:49: a= LOWER d1= number '_'
                    {
                    a=(Token)match(input,LOWER,FOLLOW_LOWER_in_name540); 
                    pushFollow(FOLLOW_number_in_name546);
                    d1=number();

                    state._fsp--;

                    match(input,27,FOLLOW_27_in_name548); 
                     n = a.getText() + d1 + "_"; 

                    }
                    break;

            }

            pushFollow(FOLLOW_number_in_name568);
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
    // SolverOutput.g:92:1: value returns [ArrayList v] : l= list ;
    public final ArrayList value() throws RecognitionException {
        ArrayList v = null;

        ArrayList l = null;


        try {
            // SolverOutput.g:92:29: (l= list )
            // SolverOutput.g:93:6: l= list
            {
            pushFollow(FOLLOW_list_in_value593);
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
    // SolverOutput.g:96:1: list returns [ArrayList l] : ( LBRACKET RBRACKET | LBRACKET is= listItems RBRACKET );
    public final ArrayList list() throws RecognitionException {
        ArrayList l = null;

        ArrayList is = null;


        try {
            // SolverOutput.g:96:28: ( LBRACKET RBRACKET | LBRACKET is= listItems RBRACKET )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==LBRACKET) ) {
                int LA6_1 = input.LA(2);

                if ( (LA6_1==RBRACKET) ) {
                    alt6=1;
                }
                else if ( (LA6_1==LBRACKET) ) {
                    alt6=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 6, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // SolverOutput.g:97:6: LBRACKET RBRACKET
                    {
                    match(input,LBRACKET,FOLLOW_LBRACKET_in_list623); 
                    match(input,RBRACKET,FOLLOW_RBRACKET_in_list625); 
                     l = new ArrayList(); 

                    }
                    break;
                case 2 :
                    // SolverOutput.g:99:6: LBRACKET is= listItems RBRACKET
                    {
                    match(input,LBRACKET,FOLLOW_LBRACKET_in_list643); 
                    pushFollow(FOLLOW_listItems_in_list649);
                    is=listItems();

                    state._fsp--;

                    match(input,RBRACKET,FOLLOW_RBRACKET_in_list651); 
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
    // SolverOutput.g:102:1: listItems returns [ArrayList is] : a= listItem ( COMMA b= listItem )* ;
    public final ArrayList listItems() throws RecognitionException {
        ArrayList is = null;

        ArrayList a = null;

        ArrayList b = null;


        try {
            // SolverOutput.g:102:33: (a= listItem ( COMMA b= listItem )* )
            // SolverOutput.g:103:6: a= listItem ( COMMA b= listItem )*
            {
            pushFollow(FOLLOW_listItem_in_listItems683);
            a=listItem();

            state._fsp--;

             is = new ArrayList(); is.add(a); 
            // SolverOutput.g:104:9: ( COMMA b= listItem )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==COMMA) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // SolverOutput.g:104:10: COMMA b= listItem
            	    {
            	    match(input,COMMA,FOLLOW_COMMA_in_listItems697); 
            	    pushFollow(FOLLOW_listItem_in_listItems703);
            	    b=listItem();

            	    state._fsp--;

            	     is.add(b); 

            	    }
            	    break;

            	default :
            	    break loop7;
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
    // SolverOutput.g:106:1: listItem returns [ArrayList i] : (a= binaryTuple | a= ternaryTuple | a= unaryTuple ) ;
    public final ArrayList listItem() throws RecognitionException {
        ArrayList i = null;

        ArrayList a = null;


        try {
            // SolverOutput.g:106:31: ( (a= binaryTuple | a= ternaryTuple | a= unaryTuple ) )
            // SolverOutput.g:107:6: (a= binaryTuple | a= ternaryTuple | a= unaryTuple )
            {
            // SolverOutput.g:107:6: (a= binaryTuple | a= ternaryTuple | a= unaryTuple )
            int alt8=3;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // SolverOutput.g:107:8: a= binaryTuple
                    {
                    pushFollow(FOLLOW_binaryTuple_in_listItem736);
                    a=binaryTuple();

                    state._fsp--;


                    }
                    break;
                case 2 :
                    // SolverOutput.g:107:26: a= ternaryTuple
                    {
                    pushFollow(FOLLOW_ternaryTuple_in_listItem744);
                    a=ternaryTuple();

                    state._fsp--;


                    }
                    break;
                case 3 :
                    // SolverOutput.g:107:45: a= unaryTuple
                    {
                    pushFollow(FOLLOW_unaryTuple_in_listItem752);
                    a=unaryTuple();

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
    // SolverOutput.g:110:1: binaryTuple returns [ArrayList t] : LBRACKET a= atom COMMA b= atom RBRACKET ;
    public final ArrayList binaryTuple() throws RecognitionException {
        ArrayList t = null;

        int a = 0;

        int b = 0;


        try {
            // SolverOutput.g:110:34: ( LBRACKET a= atom COMMA b= atom RBRACKET )
            // SolverOutput.g:111:6: LBRACKET a= atom COMMA b= atom RBRACKET
            {
            match(input,LBRACKET,FOLLOW_LBRACKET_in_binaryTuple782); 
            pushFollow(FOLLOW_atom_in_binaryTuple788);
            a=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_binaryTuple790); 
            pushFollow(FOLLOW_atom_in_binaryTuple796);
            b=atom();

            state._fsp--;

            match(input,RBRACKET,FOLLOW_RBRACKET_in_binaryTuple798); 
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
    // SolverOutput.g:115:1: ternaryTuple returns [ArrayList t] : LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET ;
    public final ArrayList ternaryTuple() throws RecognitionException {
        ArrayList t = null;

        int a = 0;

        int b = 0;

        int c = 0;


        try {
            // SolverOutput.g:115:35: ( LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET )
            // SolverOutput.g:116:6: LBRACKET a= atom COMMA b= atom COMMA c= atom RBRACKET
            {
            match(input,LBRACKET,FOLLOW_LBRACKET_in_ternaryTuple828); 
            pushFollow(FOLLOW_atom_in_ternaryTuple834);
            a=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_ternaryTuple836); 
            pushFollow(FOLLOW_atom_in_ternaryTuple842);
            b=atom();

            state._fsp--;

            match(input,COMMA,FOLLOW_COMMA_in_ternaryTuple844); 
            pushFollow(FOLLOW_atom_in_ternaryTuple850);
            c=atom();

            state._fsp--;

            match(input,RBRACKET,FOLLOW_RBRACKET_in_ternaryTuple852); 
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


    // $ANTLR start "unaryTuple"
    // SolverOutput.g:120:1: unaryTuple returns [ArrayList t] : LBRACKET a= atom RBRACKET ;
    public final ArrayList unaryTuple() throws RecognitionException {
        ArrayList t = null;

        int a = 0;


        try {
            // SolverOutput.g:120:33: ( LBRACKET a= atom RBRACKET )
            // SolverOutput.g:121:6: LBRACKET a= atom RBRACKET
            {
            match(input,LBRACKET,FOLLOW_LBRACKET_in_unaryTuple880); 
            pushFollow(FOLLOW_atom_in_unaryTuple886);
            a=atom();

            state._fsp--;

            match(input,RBRACKET,FOLLOW_RBRACKET_in_unaryTuple888); 
             t = new ArrayList();
                      t.add(a); 

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
    // $ANTLR end "unaryTuple"


    // $ANTLR start "atom"
    // SolverOutput.g:125:1: atom returns [int n] : spaces 'A' d= number ;
    public final int atom() throws RecognitionException {
        int n = 0;

        String d = null;


        try {
            // SolverOutput.g:125:21: ( spaces 'A' d= number )
            // SolverOutput.g:126:6: spaces 'A' d= number
            {
            pushFollow(FOLLOW_spaces_in_atom916);
            spaces();

            state._fsp--;

            match(input,28,FOLLOW_28_in_atom918); 
            pushFollow(FOLLOW_number_in_atom924);
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
    // SolverOutput.g:129:1: letter : ( LOWER | UPPER );
    public final void letter() throws RecognitionException {
        try {
            // SolverOutput.g:129:8: ( LOWER | UPPER )
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
    // SolverOutput.g:131:1: number returns [String n] : (d= DIGIT )+ ;
    public final String number() throws RecognitionException {
        String n = null;

        Token d=null;

        try {
            // SolverOutput.g:131:27: ( (d= DIGIT )+ )
            // SolverOutput.g:132:9: (d= DIGIT )+
            {
             n = ""; 
            // SolverOutput.g:132:22: (d= DIGIT )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==DIGIT) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // SolverOutput.g:132:24: d= DIGIT
            	    {
            	    d=(Token)match(input,DIGIT,FOLLOW_DIGIT_in_number976); 
            	     n = n + d.getText(); 

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
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


    // $ANTLR start "statText"
    // SolverOutput.g:134:1: statText returns [String st] : ( ns )+ 'primary variables: ' number 'parsing time: ' number ( ns )+ 'translation time: ' n= number ( ns )+ 'solving time: ' n= number ( ns )+ ;
    public final String statText() throws RecognitionException {
        String st = null;

        String n = null;


        try {
            // SolverOutput.g:134:30: ( ( ns )+ 'primary variables: ' number 'parsing time: ' number ( ns )+ 'translation time: ' n= number ( ns )+ 'solving time: ' n= number ( ns )+ )
            // SolverOutput.g:135:9: ( ns )+ 'primary variables: ' number 'parsing time: ' number ( ns )+ 'translation time: ' n= number ( ns )+ 'solving time: ' n= number ( ns )+
            {
            // SolverOutput.g:135:9: ( ns )+
            int cnt10=0;
            loop10:
            do {
                int alt10=2;
                alt10 = dfa10.predict(input);
                switch (alt10) {
            	case 1 :
            	    // SolverOutput.g:135:9: ns
            	    {
            	    pushFollow(FOLLOW_ns_in_statText1002);
            	    ns();

            	    state._fsp--;


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

            match(input,29,FOLLOW_29_in_statText1005); 
            pushFollow(FOLLOW_number_in_statText1007);
            number();

            state._fsp--;

            match(input,30,FOLLOW_30_in_statText1009); 
            pushFollow(FOLLOW_number_in_statText1011);
            number();

            state._fsp--;

            // SolverOutput.g:136:9: ( ns )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                alt11 = dfa11.predict(input);
                switch (alt11) {
            	case 1 :
            	    // SolverOutput.g:136:9: ns
            	    {
            	    pushFollow(FOLLOW_ns_in_statText1021);
            	    ns();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            match(input,31,FOLLOW_31_in_statText1024); 
            pushFollow(FOLLOW_number_in_statText1030);
            n=number();

            state._fsp--;

             st = "translation time: " + n + " ms "; 
            // SolverOutput.g:137:9: ( ns )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                alt12 = dfa12.predict(input);
                switch (alt12) {
            	case 1 :
            	    // SolverOutput.g:137:9: ns
            	    {
            	    pushFollow(FOLLOW_ns_in_statText1042);
            	    ns();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            match(input,32,FOLLOW_32_in_statText1045); 
            pushFollow(FOLLOW_number_in_statText1051);
            n=number();

            state._fsp--;

             st = st + "\tsolving time: " + n + " ms"; 
            // SolverOutput.g:137:92: ( ns )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                alt13 = dfa13.predict(input);
                switch (alt13) {
            	case 1 :
            	    // SolverOutput.g:137:92: ns
            	    {
            	    pushFollow(FOLLOW_ns_in_statText1055);
            	    ns();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return st;
    }
    // $ANTLR end "statText"


    // $ANTLR start "spaces"
    // SolverOutput.g:139:1: spaces : ( SPACE )* ;
    public final void spaces() throws RecognitionException {
        try {
            // SolverOutput.g:139:8: ( ( SPACE )* )
            // SolverOutput.g:139:10: ( SPACE )*
            {
            // SolverOutput.g:139:10: ( SPACE )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==SPACE) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // SolverOutput.g:139:10: SPACE
            	    {
            	    match(input,SPACE,FOLLOW_SPACE_in_spaces1065); 

            	    }
            	    break;

            	default :
            	    break loop14;
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


    // $ANTLR start "ns"
    // SolverOutput.g:141:1: ns : ~ ( '*' ) ;
    public final void ns() throws RecognitionException {
        try {
            // SolverOutput.g:141:4: (~ ( '*' ) )
            // SolverOutput.g:141:6: ~ ( '*' )
            {
            if ( (input.LA(1)>=PLUS && input.LA(1)<=MINUS)||(input.LA(1)>=DIV && input.LA(1)<=32) ) {
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
    // $ANTLR end "ns"

    // Delegated rules


    protected DFA2 dfa2 = new DFA2(this);
    protected DFA5 dfa5 = new DFA5(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA10 dfa10 = new DFA10(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA12 dfa12 = new DFA12(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA2_eotS =
        "\7\uffff";
    static final String DFA2_eofS =
        "\7\uffff";
    static final String DFA2_minS =
        "\4\21\1\25\2\uffff";
    static final String DFA2_maxS =
        "\2\27\2\30\1\26\2\uffff";
    static final String DFA2_acceptS =
        "\5\uffff\1\2\1\1";
    static final String DFA2_specialS =
        "\7\uffff}>";
    static final String[] DFA2_transitionS = {
            "\1\1\5\uffff\1\2",
            "\1\1\5\uffff\1\2",
            "\1\3\3\uffff\1\6\1\5\1\uffff\1\4",
            "\1\3\3\uffff\1\6\1\5\1\uffff\1\4",
            "\1\6\1\5",
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
    static final String DFA5_eotS =
        "\5\uffff";
    static final String DFA5_eofS =
        "\5\uffff";
    static final String DFA5_minS =
        "\1\16\1\20\1\11\2\uffff";
    static final String DFA5_maxS =
        "\1\16\1\20\1\33\2\uffff";
    static final String DFA5_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA5_specialS =
        "\5\uffff}>";
    static final String[] DFA5_transitionS = {
            "\1\1",
            "\1\2",
            "\1\3\6\uffff\1\2\12\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA5_eot = DFA.unpackEncodedString(DFA5_eotS);
    static final short[] DFA5_eof = DFA.unpackEncodedString(DFA5_eofS);
    static final char[] DFA5_min = DFA.unpackEncodedStringToUnsignedChars(DFA5_minS);
    static final char[] DFA5_max = DFA.unpackEncodedStringToUnsignedChars(DFA5_maxS);
    static final short[] DFA5_accept = DFA.unpackEncodedString(DFA5_acceptS);
    static final short[] DFA5_special = DFA.unpackEncodedString(DFA5_specialS);
    static final short[][] DFA5_transition;

    static {
        int numStates = DFA5_transitionS.length;
        DFA5_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA5_transition[i] = DFA.unpackEncodedString(DFA5_transitionS[i]);
        }
    }

    class DFA5 extends DFA {

        public DFA5(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 5;
            this.eot = DFA5_eot;
            this.eof = DFA5_eof;
            this.min = DFA5_min;
            this.max = DFA5_max;
            this.accept = DFA5_accept;
            this.special = DFA5_special;
            this.transition = DFA5_transition;
        }
        public String getDescription() {
            return "89:13: (a= LOWER | a= LOWER d1= number '_' )";
        }
    }
    static final String DFA8_eotS =
        "\14\uffff";
    static final String DFA8_eofS =
        "\14\uffff";
    static final String DFA8_minS =
        "\1\12\2\21\1\20\1\10\1\21\1\uffff\1\21\1\20\1\10\2\uffff";
    static final String DFA8_maxS =
        "\1\12\2\34\2\20\1\34\1\uffff\1\34\2\20\2\uffff";
    static final String DFA8_acceptS =
        "\6\uffff\1\3\3\uffff\1\2\1\1";
    static final String DFA8_specialS =
        "\14\uffff}>";
    static final String[] DFA8_transitionS = {
            "\1\1",
            "\1\2\12\uffff\1\3",
            "\1\2\12\uffff\1\3",
            "\1\4",
            "\1\5\2\uffff\1\6\4\uffff\1\4",
            "\1\7\12\uffff\1\10",
            "",
            "\1\7\12\uffff\1\10",
            "\1\11",
            "\1\12\2\uffff\1\13\4\uffff\1\11",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "107:6: (a= binaryTuple | a= ternaryTuple | a= unaryTuple )";
        }
    }
    static final String DFA10_eotS =
        "\u0084\uffff";
    static final String DFA10_eofS =
        "\36\uffff\1\41\145\uffff";
    static final String DFA10_minS =
        "\2\4\1\uffff\12\4\1\uffff\10\4\1\uffff\12\4\1\uffff\2\4\1\uffff"+
        "\11\4\1\uffff\125\4";
    static final String DFA10_maxS =
        "\2\40\1\uffff\12\40\1\uffff\10\40\1\uffff\12\40\1\uffff\2\40\1\uffff"+
        "\11\40\1\uffff\125\40";
    static final String DFA10_acceptS =
        "\2\uffff\1\1\12\uffff\1\1\10\uffff\1\1\12\uffff\1\2\2\uffff\1\1"+
        "\11\uffff\1\1\125\uffff";
    static final String DFA10_specialS =
        "\u0084\uffff}>";
    static final String[] DFA10_transitionS = {
            "\2\2\1\uffff\26\2\1\1\3\2",
            "\2\2\1\uffff\11\2\1\3\20\2",
            "",
            "\2\2\1\uffff\11\2\1\3\15\2\1\4\2\2",
            "\2\2\1\uffff\11\2\1\5\20\2",
            "\2\10\1\uffff\11\10\1\7\14\10\1\6\3\10",
            "\2\10\1\uffff\11\10\1\11\14\10\1\6\1\10\1\12\1\10",
            "\2\10\1\uffff\11\10\1\7\14\10\1\6\1\10\1\12\1\10",
            "\2\10\1\uffff\26\10\1\6\1\10\1\12\1\10",
            "\2\10\1\uffff\11\10\1\11\14\10\1\6\1\13\1\12\1\10",
            "\2\10\1\uffff\11\10\1\14\14\10\1\6\1\10\1\12\1\10",
            "\2\10\1\uffff\11\10\1\15\14\10\1\6\1\10\1\12\1\10",
            "\2\21\1\uffff\11\21\1\17\14\21\1\16\1\21\1\20\1\21",
            "",
            "\2\21\1\uffff\11\21\1\22\14\21\1\16\1\21\1\20\1\23",
            "\2\21\1\uffff\11\21\1\17\14\21\1\16\1\21\1\20\1\23",
            "\2\21\1\uffff\11\21\1\17\14\21\1\16\1\21\1\20\1\23",
            "\2\21\1\uffff\26\21\1\16\1\21\1\20\1\23",
            "\2\21\1\uffff\11\21\1\22\14\21\1\16\1\24\1\20\1\23",
            "\2\21\1\uffff\11\21\1\25\14\21\1\16\1\21\1\20\1\23",
            "\2\21\1\uffff\11\21\1\26\14\21\1\16\1\21\1\20\1\23",
            "\2\33\1\uffff\11\33\1\31\14\33\1\27\1\33\1\32\1\30",
            "",
            "\2\33\1\uffff\11\33\1\34\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\11\33\1\31\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\11\33\1\31\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\11\33\1\37\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\12\33\1\35\1\33\1\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\11\33\1\34\1\35\1\33\1\36\11\33\1\27\1\40\1\32"+
            "\1\30",
            "\2\33\1\uffff\12\33\1\35\1\33\1\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\42\1\43\1\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\11\33\1\37\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\11\33\1\44\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "",
            "\2\33\1\uffff\12\33\1\42\1\43\1\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\45\1\33\1\36\3\33\1\46\5\33\1\27\1\33"+
            "\1\32\1\30",
            "",
            "\2\33\1\uffff\12\33\1\45\1\33\1\36\3\33\1\46\5\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\47\1\33\1\36\1\33\1\52\1\51\1\33\1\50"+
            "\4\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\47\1\33\1\36\1\33\1\52\1\51\1\33\1\50"+
            "\4\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\35\1\33\1\36\1\33\1\52\1\51\6\33\1\27"+
            "\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\53\1\33\1\36\5\33\1\54\3\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\55\1\33\1\36\1\56\10\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\12\33\1\53\1\33\1\36\5\33\1\54\3\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\57\1\33\1\36\6\33\1\60\2\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\55\1\33\1\36\1\56\10\33\1\27\1\33\1\32"+
            "\1\30",
            "",
            "\2\33\1\uffff\12\33\1\57\1\33\1\36\6\33\1\60\2\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\5\33\1\61\4\33\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\7\33\1\63\2\33\1\62\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\7\33\1\63\2\33\1\62\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\11\33\1\64\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\2\33\1\66\6\33\1\64\1\35\1\33\1\36\7\33\1\65"+
            "\1\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\11\33\1\67\1\35\1\33\1\36\11\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\3\33\1\70\6\33\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\2\33\1\66\6\33\1\67\1\35\1\33\1\36\11\33\1\27"+
            "\1\33\1\32\1\30",
            "\2\33\1\uffff\3\33\1\72\1\71\5\33\1\35\1\33\1\36\11\33\1\27"+
            "\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\73\4\33\1\74\3\33\1\35\1\33\1\36\11\33"+
            "\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\75\1\33\1\36\10\33\1\76\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\7\33\1\100\2\33\1\77\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\55\1\33\1\36\1\56\10\33\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\12\33\1\75\1\33\1\36\10\33\1\76\1\27\1\33\1\32"+
            "\1\30",
            "\2\33\1\uffff\11\33\1\101\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\7\33\1\100\2\33\1\77\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\11\33\1\102\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\103\2\33\1\104\4\33\1\101\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\2\33\1\106\6\33\1\102\1\35\1\33\1\36\7\33\1\105"+
            "\1\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\107\1\33\1\36\10\33\1\110\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\11\33\1\113\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\3\33\1\114\6\33\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\12\33\1\107\1\33\1\36\10\33\1\110\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\115\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\3\33\1\116\6\33\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\1\33\1\73\4\33\1\74\3\33\1\35\1\33\1\36\11\33"+
            "\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\2\33\1\106\6\33\1\113\1\35\1\33\1\36\11\33\1"+
            "\27\1\33\1\32\1\30",
            "\2\33\1\uffff\3\33\1\120\1\117\5\33\1\35\1\33\1\36\11\33\1"+
            "\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\121\2\33\1\122\4\33\1\115\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\123\1\33\1\36\10\33\1\124\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\73\4\33\1\74\3\33\1\35\1\33\1\36\11\33"+
            "\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\125\1\33\1\36\10\33\1\126\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\12\33\1\127\1\33\1\36\10\33\1\130\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\123\1\33\1\36\10\33\1\124\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\131\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\12\33\1\125\1\33\1\36\10\33\1\126\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\132\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\12\33\1\127\1\33\1\36\10\33\1\130\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\133\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\135\2\33\1\134\4\33\1\131\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\137\2\33\1\136\4\33\1\132\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\4\33\1\140\4\33\1\133\1\35\1\33\1\36\11\33\1"+
            "\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\141\1\33\1\36\10\33\1\142\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\145\1\33\1\36\10\33\1\146\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\141\1\33\1\36\10\33\1\142\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\147\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\3\33\1\150\6\33\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\1\33\1\73\4\33\1\74\3\33\1\35\1\33\1\36\11\33"+
            "\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\145\1\33\1\36\10\33\1\146\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\151\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\152\2\33\1\153\4\33\1\147\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\154\1\33\1\36\10\33\1\155\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\156\2\33\1\157\4\33\1\151\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\160\1\33\1\36\10\33\1\161\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\154\1\33\1\36\10\33\1\155\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\162\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\12\33\1\163\1\33\1\36\10\33\1\164\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\160\1\33\1\36\10\33\1\161\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\165\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\166\2\33\1\167\4\33\1\162\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\163\1\33\1\36\10\33\1\164\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\170\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\4\33\1\171\4\33\1\165\1\35\1\33\1\36\11\33\1"+
            "\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\172\1\33\1\36\10\33\1\173\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\4\33\1\174\4\33\1\170\1\35\1\33\1\36\11\33\1"+
            "\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\111\2\33\1\112\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\172\1\33\1\36\10\33\1\173\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\11\33\1\175\1\35\1\33\1\36\11\33\1\27\1\33\1"+
            "\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\177\2\33\1\176\4\33\1\175\1\35\1\33\1"+
            "\36\11\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\u0080\1\33\1\36\10\33\1\u0081\1\27\1"+
            "\33\1\32\1\30",
            "\2\33\1\uffff\12\33\1\u0080\1\33\1\36\10\33\1\u0081\1\27\1"+
            "\33\1\32\1\30",
            "\2\33\1\uffff\11\33\1\u0082\1\35\1\33\1\36\11\33\1\27\1\33"+
            "\1\32\1\30",
            "\2\33\1\uffff\4\33\1\u0083\4\33\1\u0082\1\35\1\33\1\36\11\33"+
            "\1\27\1\33\1\32\1\30",
            "\2\33\1\uffff\1\33\1\143\2\33\1\144\5\33\1\35\1\33\1\36\11"+
            "\33\1\27\1\33\1\32\1\30"
    };

    static final short[] DFA10_eot = DFA.unpackEncodedString(DFA10_eotS);
    static final short[] DFA10_eof = DFA.unpackEncodedString(DFA10_eofS);
    static final char[] DFA10_min = DFA.unpackEncodedStringToUnsignedChars(DFA10_minS);
    static final char[] DFA10_max = DFA.unpackEncodedStringToUnsignedChars(DFA10_maxS);
    static final short[] DFA10_accept = DFA.unpackEncodedString(DFA10_acceptS);
    static final short[] DFA10_special = DFA.unpackEncodedString(DFA10_specialS);
    static final short[][] DFA10_transition;

    static {
        int numStates = DFA10_transitionS.length;
        DFA10_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA10_transition[i] = DFA.unpackEncodedString(DFA10_transitionS[i]);
        }
    }

    class DFA10 extends DFA {

        public DFA10(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 10;
            this.eot = DFA10_eot;
            this.eof = DFA10_eof;
            this.min = DFA10_min;
            this.max = DFA10_max;
            this.accept = DFA10_accept;
            this.special = DFA10_special;
            this.transition = DFA10_transition;
        }
        public String getDescription() {
            return "()+ loopback of 135:9: ( ns )+";
        }
    }
    static final String DFA11_eotS =
        "\u00df\uffff";
    static final String DFA11_eofS =
        "\17\uffff\1\21\22\uffff\1\21\u00bc\uffff";
    static final String DFA11_minS =
        "\2\4\1\uffff\5\4\1\uffff\7\4\2\uffff\30\4\1\uffff\15\4\1\uffff\u00a6"+
        "\4";
    static final String DFA11_maxS =
        "\2\40\1\uffff\5\40\1\uffff\7\40\2\uffff\30\40\1\uffff\15\40\1\uffff"+
        "\u00a6\40";
    static final String DFA11_acceptS =
        "\2\uffff\1\1\5\uffff\1\1\7\uffff\1\1\1\2\30\uffff\1\1\15\uffff\1"+
        "\1\u00a6\uffff";
    static final String DFA11_specialS =
        "\u00df\uffff}>";
    static final String[] DFA11_transitionS = {
            "\2\2\1\uffff\30\2\1\1\1\2",
            "\2\2\1\uffff\11\2\1\3\20\2",
            "",
            "\2\6\1\uffff\11\6\1\4\16\6\1\5\1\6",
            "\2\6\1\uffff\11\6\1\4\16\6\1\5\1\7",
            "\2\6\1\uffff\11\6\1\10\16\6\1\5\1\7",
            "\2\6\1\uffff\30\6\1\5\1\7",
            "\2\6\1\uffff\11\6\1\11\16\6\1\5\1\7",
            "",
            "\2\15\1\uffff\11\15\1\13\16\15\1\14\1\12",
            "\2\15\1\uffff\11\15\1\13\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\11\15\1\13\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\11\15\1\20\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\22\1\23\1\17\13\15\1\14\1\12",
            "",
            "",
            "\2\15\1\uffff\12\15\1\22\1\23\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\24\1\15\1\17\3\15\1\25\7\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\24\1\15\1\17\3\15\1\25\7\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\26\1\15\1\17\1\15\1\31\1\30\1\15\1\27"+
            "\6\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\26\1\15\1\17\1\15\1\31\1\30\1\15\1\27"+
            "\6\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\16\1\15\1\17\1\15\1\31\1\30\10\15\1\14"+
            "\1\12",
            "\2\15\1\uffff\12\15\1\32\1\15\1\17\5\15\1\33\5\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\34\1\15\1\17\1\35\12\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\32\1\15\1\17\5\15\1\33\5\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\36\1\15\1\17\6\15\1\37\4\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\34\1\15\1\17\1\35\12\15\1\14\1\12",
            "\2\44\1\uffff\12\44\1\41\1\44\1\42\13\44\1\43\1\40",
            "\2\15\1\uffff\12\15\1\36\1\15\1\17\6\15\1\37\4\15\1\14\1\12",
            "\2\15\1\uffff\5\15\1\45\4\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\47\1\41\1\44\1\42\11\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\41\1\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\50\1\51\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\52\1\41\1\44\1\42\11\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\41\1\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\7\15\1\54\2\15\1\53\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\55\1\41\1\44\1\42\11\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\11\44\1\47\1\41\1\44\1\42\11\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\50\1\51\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\56\1\44\1\42\3\44\1\57\5\44\1\46\1\44"+
            "\1\43\1\40",
            "",
            "\2\15\1\uffff\7\15\1\54\2\15\1\53\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\11\15\1\60\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\55\1\41\1\44\1\42\11\44\1\46\1\61\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\56\1\44\1\42\3\44\1\57\5\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\62\1\44\1\42\1\44\1\65\1\64\1\44\1\63"+
            "\4\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\2\15\1\67\6\15\1\60\1\16\1\15\1\17\7\15\1\66"+
            "\3\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\70\1\41\1\44\1\42\11\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\62\1\44\1\42\1\44\1\65\1\64\1\44\1\63"+
            "\4\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\41\1\44\1\42\1\44\1\65\1\64\6\44\1\46"+
            "\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\71\1\44\1\42\5\44\1\72\3\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\73\1\44\1\42\1\74\10\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\15\1\uffff\11\15\1\75\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\3\15\1\76\6\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "",
            "\2\44\1\uffff\12\44\1\71\1\44\1\42\5\44\1\72\3\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\77\1\44\1\42\6\44\1\100\2\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\73\1\44\1\42\1\74\10\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\41\1\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\2\15\1\67\6\15\1\75\1\16\1\15\1\17\13\15\1\14"+
            "\1\12",
            "\2\15\1\uffff\3\15\1\102\1\101\5\15\1\16\1\15\1\17\13\15\1"+
            "\14\1\12",
            "\2\44\1\uffff\12\44\1\77\1\44\1\42\6\44\1\100\2\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\5\44\1\103\4\44\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\15\1\uffff\1\15\1\104\4\15\1\105\3\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\106\1\15\1\17\10\15\1\107\2\15\1\14\1"+
            "\12",
            "\2\44\1\uffff\7\44\1\111\2\44\1\110\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\15\1\uffff\7\15\1\113\2\15\1\112\1\15\1\17\13\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\12\15\1\34\1\15\1\17\1\35\12\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\106\1\15\1\17\10\15\1\107\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\114\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\7\44\1\111\2\44\1\110\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\115\1\41\1\44\1\42\11\44\1\46\1\44\1"+
            "\43\1\40",
            "\2\15\1\uffff\7\15\1\113\2\15\1\112\1\15\1\17\13\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\116\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\1\15\1\117\2\15\1\120\4\15\1\114\1\16\1\15\1"+
            "\17\13\15\1\14\1\12",
            "\2\44\1\uffff\2\44\1\122\6\44\1\115\1\41\1\44\1\42\7\44\1\121"+
            "\1\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\2\15\1\124\6\15\1\116\1\16\1\15\1\17\7\15\1\123"+
            "\3\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\125\1\15\1\17\10\15\1\126\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\131\1\41\1\44\1\42\11\44\1\46\1\44\1"+
            "\43\1\40",
            "\2\44\1\uffff\3\44\1\132\6\44\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\15\1\uffff\11\15\1\133\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\3\15\1\134\6\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\125\1\15\1\17\10\15\1\126\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\135\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\3\15\1\136\6\15\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\1\15\1\104\4\15\1\105\3\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\44\1\uffff\2\44\1\122\6\44\1\131\1\41\1\44\1\42\11\44\1"+
            "\46\1\44\1\43\1\40",
            "\2\44\1\uffff\3\44\1\140\1\137\5\44\1\41\1\44\1\42\11\44\1"+
            "\46\1\44\1\43\1\40",
            "\2\15\1\uffff\2\15\1\124\6\15\1\133\1\16\1\15\1\17\13\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\3\15\1\142\1\141\5\15\1\16\1\15\1\17\13\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\143\2\15\1\144\4\15\1\135\1\16\1\15\1"+
            "\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\145\1\15\1\17\10\15\1\146\2\15\1\14\1"+
            "\12",
            "\2\44\1\uffff\1\44\1\147\4\44\1\150\3\44\1\41\1\44\1\42\11"+
            "\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\151\1\44\1\42\10\44\1\152\1\46\1\44\1"+
            "\43\1\40",
            "\2\15\1\uffff\1\15\1\104\4\15\1\105\3\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\153\1\15\1\17\10\15\1\154\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\12\15\1\155\1\15\1\17\10\15\1\156\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\145\1\15\1\17\10\15\1\146\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\157\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\7\44\1\161\2\44\1\160\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\73\1\44\1\42\1\74\10\44\1\46\1\44\1\43"+
            "\1\40",
            "\2\44\1\uffff\12\44\1\151\1\44\1\42\10\44\1\152\1\46\1\44\1"+
            "\43\1\40",
            "\2\44\1\uffff\11\44\1\162\1\41\1\44\1\42\11\44\1\46\1\44\1"+
            "\43\1\40",
            "\2\15\1\uffff\12\15\1\153\1\15\1\17\10\15\1\154\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\163\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\155\1\15\1\17\10\15\1\156\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\164\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\1\15\1\166\2\15\1\165\4\15\1\157\1\16\1\15\1"+
            "\17\13\15\1\14\1\12",
            "\2\44\1\uffff\7\44\1\161\2\44\1\160\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\167\1\41\1\44\1\42\11\44\1\46\1\44\1"+
            "\43\1\40",
            "\2\44\1\uffff\1\44\1\170\2\44\1\171\4\44\1\162\1\41\1\44\1"+
            "\42\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\173\2\15\1\172\4\15\1\163\1\16\1\15\1"+
            "\17\13\15\1\14\1\12",
            "\2\15\1\uffff\4\15\1\174\4\15\1\164\1\16\1\15\1\17\13\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\175\1\15\1\17\10\15\1\176\2\15\1\14\1"+
            "\12",
            "\2\44\1\uffff\2\44\1\u0080\6\44\1\167\1\41\1\44\1\42\7\44\1"+
            "\177\1\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u0081\1\44\1\42\10\44\1\u0082\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u0087\1\15\1\17\10\15\1\u0088\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\175\1\15\1\17\10\15\1\176\2\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\11\15\1\u0089\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\11\44\1\u008a\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\3\44\1\u008b\6\44\1\41\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u0081\1\44\1\42\10\44\1\u0082\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u008c\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\3\44\1\u008d\6\44\1\41\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\147\4\44\1\150\3\44\1\41\1\44\1\42\11"+
            "\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\3\15\1\u008e\6\15\1\16\1\15\1\17\13\15\1\14\1"+
            "\12",
            "\2\15\1\uffff\1\15\1\104\4\15\1\105\3\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u0087\1\15\1\17\10\15\1\u0088\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u008f\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\1\15\1\u0090\2\15\1\u0091\4\15\1\u0089\1\16\1"+
            "\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\2\44\1\u0080\6\44\1\u008a\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\3\44\1\u0093\1\u0092\5\44\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0094\2\44\1\u0095\4\44\1\u008c\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u0096\1\44\1\42\10\44\1\u0097\1\46\1"+
            "\44\1\43\1\40",
            "\2\15\1\uffff\12\15\1\u0098\1\15\1\17\10\15\1\u0099\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\u009a\2\15\1\u009b\4\15\1\u008f\1\16\1"+
            "\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u009c\1\15\1\17\10\15\1\u009d\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\44\1\uffff\1\44\1\147\4\44\1\150\3\44\1\41\1\44\1\42\11"+
            "\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u009e\1\44\1\42\10\44\1\u009f\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00a0\1\44\1\42\10\44\1\u00a1\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u0096\1\44\1\42\10\44\1\u0097\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00a2\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\15\1\uffff\12\15\1\u0098\1\15\1\17\10\15\1\u0099\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u00a3\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u00a4\1\15\1\17\10\15\1\u00a5\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u009c\1\15\1\17\10\15\1\u009d\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u00a6\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\12\44\1\u009e\1\44\1\42\10\44\1\u009f\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00a7\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00a0\1\44\1\42\10\44\1\u00a1\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00a8\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00aa\2\44\1\u00a9\4\44\1\u00a2\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\u00ab\2\15\1\u00ac\4\15\1\u00a3\1\16\1"+
            "\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u00a4\1\15\1\17\10\15\1\u00a5\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u00ad\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\4\15\1\u00ae\4\15\1\u00a6\1\16\1\15\1\17\13\15"+
            "\1\14\1\12",
            "\2\44\1\uffff\1\44\1\u00b0\2\44\1\u00af\4\44\1\u00a7\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\4\44\1\u00b1\4\44\1\u00a8\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00b2\1\44\1\42\10\44\1\u00b3\1\46\1"+
            "\44\1\43\1\40",
            "\2\15\1\uffff\12\15\1\u00b4\1\15\1\17\10\15\1\u00b5\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\15\1\uffff\4\15\1\u00b6\4\15\1\u00ad\1\16\1\15\1\17\13\15"+
            "\1\14\1\12",
            "\2\15\1\uffff\1\15\1\127\2\15\1\130\5\15\1\16\1\15\1\17\13"+
            "\15\1\14\1\12",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00b9\1\44\1\42\10\44\1\u00ba\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00b2\1\44\1\42\10\44\1\u00b3\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00bb\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\15\1\uffff\12\15\1\u00b4\1\15\1\17\10\15\1\u00b5\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u00bc\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\44\1\uffff\3\44\1\u00bd\6\44\1\41\1\44\1\42\11\44\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\147\4\44\1\150\3\44\1\41\1\44\1\42\11"+
            "\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00b9\1\44\1\42\10\44\1\u00ba\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00be\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00bf\2\44\1\u00c0\4\44\1\u00bb\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\u00c2\2\15\1\u00c1\4\15\1\u00bc\1\16\1"+
            "\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\12\44\1\u00c3\1\44\1\42\10\44\1\u00c4\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00c5\2\44\1\u00c6\4\44\1\u00be\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00c7\1\44\1\42\10\44\1\u00c8\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\15\1\uffff\12\15\1\u00c9\1\15\1\17\10\15\1\u00ca\2\15\1"+
            "\14\1\12",
            "\2\44\1\uffff\12\44\1\u00c3\1\44\1\42\10\44\1\u00c4\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00cb\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00cc\1\44\1\42\10\44\1\u00cd\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00c7\1\44\1\42\10\44\1\u00c8\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00ce\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\15\1\uffff\12\15\1\u00c9\1\15\1\17\10\15\1\u00ca\2\15\1"+
            "\14\1\12",
            "\2\15\1\uffff\11\15\1\u00cf\1\16\1\15\1\17\13\15\1\14\1\12",
            "\2\44\1\uffff\1\44\1\u00d0\2\44\1\u00d1\4\44\1\u00cb\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00cc\1\44\1\42\10\44\1\u00cd\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00d2\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\4\44\1\u00d3\4\44\1\u00ce\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\4\15\1\u00d4\4\15\1\u00cf\1\16\1\15\1\17\13\15"+
            "\1\14\1\12",
            "\2\44\1\uffff\12\44\1\u00d5\1\44\1\42\10\44\1\u00d6\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\4\44\1\u00d7\4\44\1\u00d2\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u0083\2\44\1\u0084\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\15\1\uffff\1\15\1\u0085\2\15\1\u0086\5\15\1\16\1\15\1\17"+
            "\13\15\1\14\1\12",
            "\2\44\1\uffff\12\44\1\u00d5\1\44\1\42\10\44\1\u00d6\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00d8\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00da\2\44\1\u00d9\4\44\1\u00d8\1\41\1"+
            "\44\1\42\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00db\1\44\1\42\10\44\1\u00dc\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\12\44\1\u00db\1\44\1\42\10\44\1\u00dc\1\46\1"+
            "\44\1\43\1\40",
            "\2\44\1\uffff\11\44\1\u00dd\1\41\1\44\1\42\11\44\1\46\1\44"+
            "\1\43\1\40",
            "\2\44\1\uffff\4\44\1\u00de\4\44\1\u00dd\1\41\1\44\1\42\11\44"+
            "\1\46\1\44\1\43\1\40",
            "\2\44\1\uffff\1\44\1\u00b7\2\44\1\u00b8\5\44\1\41\1\44\1\42"+
            "\11\44\1\46\1\44\1\43\1\40"
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "()+ loopback of 136:9: ( ns )+";
        }
    }
    static final String DFA12_eotS =
        "\u0142\uffff";
    static final String DFA12_eofS =
        "\10\uffff\1\12\21\uffff\1\12\34\uffff\1\12\u010a\uffff";
    static final String DFA12_minS =
        "\2\4\1\uffff\6\4\2\uffff\26\4\1\uffff\42\4\1\uffff\5\4\1\uffff\u00f7"+
        "\4";
    static final String DFA12_maxS =
        "\2\40\1\uffff\6\40\2\uffff\26\40\1\uffff\42\40\1\uffff\5\40\1\uffff"+
        "\u00f7\40";
    static final String DFA12_acceptS =
        "\2\uffff\1\1\6\uffff\1\1\1\2\26\uffff\1\1\42\uffff\1\1\5\uffff\1"+
        "\1\u00f7\uffff";
    static final String DFA12_specialS =
        "\u0142\uffff}>";
    static final String[] DFA12_transitionS = {
            "\2\2\1\uffff\31\2\1\1",
            "\2\2\1\uffff\11\2\1\3\20\2",
            "",
            "\2\6\1\uffff\11\6\1\4\17\6\1\5",
            "\2\6\1\uffff\11\6\1\4\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\11\6\1\11\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\13\1\14\1\10\14\6\1\5",
            "",
            "",
            "\2\6\1\uffff\12\6\1\13\1\14\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\15\1\6\1\10\3\6\1\16\10\6\1\5",
            "\2\6\1\uffff\12\6\1\15\1\6\1\10\3\6\1\16\10\6\1\5",
            "\2\6\1\uffff\12\6\1\17\1\6\1\10\1\6\1\22\1\21\1\6\1\20\7\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\17\1\6\1\10\1\6\1\22\1\21\1\6\1\20\7\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\7\1\6\1\10\1\6\1\22\1\21\11\6\1\5",
            "\2\6\1\uffff\12\6\1\23\1\6\1\10\5\6\1\24\6\6\1\5",
            "\2\6\1\uffff\12\6\1\25\1\6\1\10\1\26\13\6\1\5",
            "\2\6\1\uffff\12\6\1\23\1\6\1\10\5\6\1\24\6\6\1\5",
            "\2\6\1\uffff\12\6\1\27\1\6\1\10\6\6\1\30\5\6\1\5",
            "\2\6\1\uffff\12\6\1\25\1\6\1\10\1\26\13\6\1\5",
            "\2\34\1\uffff\12\34\1\31\1\34\1\32\14\34\1\33",
            "\2\6\1\uffff\12\6\1\27\1\6\1\10\6\6\1\30\5\6\1\5",
            "\2\6\1\uffff\5\6\1\35\4\6\1\7\1\6\1\10\14\6\1\5",
            "\2\34\1\uffff\12\34\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\37\1\40\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\11\34\1\41\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\7\6\1\43\2\6\1\42\1\6\1\10\14\6\1\5",
            "\2\34\1\uffff\11\34\1\44\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\37\1\40\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\45\1\34\1\32\3\34\1\46\5\34\1\36\2\34"+
            "\1\33",
            "",
            "\2\6\1\uffff\7\6\1\43\2\6\1\42\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\11\6\1\47\1\7\1\6\1\10\14\6\1\5",
            "\2\34\1\uffff\11\34\1\44\1\31\1\34\1\32\11\34\1\36\1\50\1\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\45\1\34\1\32\3\34\1\46\5\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\51\1\34\1\32\1\34\1\54\1\53\1\34\1\52"+
            "\4\34\1\36\2\34\1\33",
            "\2\6\1\uffff\2\6\1\56\6\6\1\47\1\7\1\6\1\10\7\6\1\55\4\6\1"+
            "\5",
            "\2\34\1\uffff\11\34\1\57\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\51\1\34\1\32\1\34\1\54\1\53\1\34\1\52"+
            "\4\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\31\1\34\1\32\1\34\1\54\1\53\6\34\1\36"+
            "\2\34\1\33",
            "\2\34\1\uffff\12\34\1\60\1\34\1\32\5\34\1\61\3\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\62\1\34\1\32\1\63\10\34\1\36\2\34\1\33",
            "\2\6\1\uffff\11\6\1\64\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\3\6\1\65\6\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\11\73\1\72\1\66\1\73\1\67\11\73\1\70\2\73\1\71",
            "\2\34\1\uffff\12\34\1\60\1\34\1\32\5\34\1\61\3\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\74\1\34\1\32\6\34\1\75\2\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\62\1\34\1\32\1\63\10\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\31\1\34\1\32\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\2\6\1\56\6\6\1\64\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\3\6\1\77\1\76\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\12\73\1\66\1\73\1\67\11\73\1\70\1\73\1\100\1"+
            "\71",
            "\2\73\1\uffff\12\73\1\101\1\102\1\67\11\73\1\70\1\73\1\100"+
            "\1\71",
            "\2\73\1\uffff\11\73\1\103\1\66\1\73\1\67\11\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\73\1\uffff\11\73\1\104\1\66\1\73\1\67\11\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\73\1\uffff\11\73\1\72\1\66\1\73\1\67\11\73\1\70\1\73\1\100"+
            "\1\71",
            "\2\73\1\uffff\12\73\1\66\1\73\1\67\11\73\1\70\1\73\1\100\1"+
            "\71",
            "\2\34\1\uffff\12\34\1\74\1\34\1\32\6\34\1\75\2\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\5\34\1\105\4\34\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\1\6\1\106\4\6\1\107\3\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\110\1\6\1\10\10\6\1\111\3\6\1\5",
            "\2\73\1\uffff\11\73\1\112\1\66\1\73\1\67\11\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\73\1\uffff\12\73\1\101\1\102\1\67\11\73\1\70\1\73\1\100"+
            "\1\71",
            "\2\73\1\uffff\12\73\1\113\1\73\1\67\3\73\1\114\5\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\103\1\66\1\73\1\67\11\73\1\70\1\115\1"+
            "\100\1\71",
            "",
            "\2\34\1\uffff\7\34\1\117\2\34\1\116\1\34\1\32\11\34\1\36\2"+
            "\34\1\33",
            "\2\6\1\uffff\7\6\1\121\2\6\1\120\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\25\1\6\1\10\1\26\13\6\1\5",
            "\2\6\1\uffff\12\6\1\110\1\6\1\10\10\6\1\111\3\6\1\5",
            "\2\6\1\uffff\11\6\1\122\1\7\1\6\1\10\14\6\1\5",
            "",
            "\2\73\1\uffff\12\73\1\113\1\73\1\67\3\73\1\114\5\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\123\1\73\1\67\1\73\1\126\1\125\1\73\1"+
            "\124\4\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\72\1\66\1\73\1\67\11\73\1\70\1\73\1\100"+
            "\1\71",
            "\2\34\1\uffff\7\34\1\117\2\34\1\116\1\34\1\32\11\34\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\127\1\31\1\34\1\32\11\34\1\36\2\34\1"+
            "\33",
            "\2\6\1\uffff\7\6\1\121\2\6\1\120\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\11\6\1\130\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\131\2\6\1\132\4\6\1\122\1\7\1\6\1\10\14"+
            "\6\1\5",
            "\2\73\1\uffff\12\73\1\123\1\73\1\67\1\73\1\126\1\125\1\73\1"+
            "\124\4\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\66\1\73\1\67\1\73\1\126\1\125\6\73\1"+
            "\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\133\1\73\1\67\5\73\1\134\3\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\135\1\73\1\67\1\136\10\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\34\1\uffff\2\34\1\140\6\34\1\127\1\31\1\34\1\32\7\34\1\137"+
            "\1\34\1\36\2\34\1\33",
            "\2\6\1\uffff\2\6\1\142\6\6\1\130\1\7\1\6\1\10\7\6\1\141\4\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\143\1\6\1\10\10\6\1\144\3\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\12\73\1\133\1\73\1\67\5\73\1\134\3\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\147\1\73\1\67\6\73\1\150\2\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\135\1\73\1\67\1\136\10\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\73\1\uffff\12\73\1\66\1\73\1\67\11\73\1\70\1\73\1\100\1"+
            "\71",
            "\2\34\1\uffff\11\34\1\151\1\31\1\34\1\32\11\34\1\36\2\34\1"+
            "\33",
            "\2\34\1\uffff\3\34\1\152\6\34\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\11\6\1\153\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\3\6\1\154\6\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\143\1\6\1\10\10\6\1\144\3\6\1\5",
            "\2\6\1\uffff\11\6\1\155\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\3\6\1\156\6\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\106\4\6\1\107\3\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\12\73\1\147\1\73\1\67\6\73\1\150\2\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\5\73\1\157\4\73\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\2\34\1\140\6\34\1\151\1\31\1\34\1\32\11\34\1"+
            "\36\2\34\1\33",
            "\2\34\1\uffff\3\34\1\161\1\160\5\34\1\31\1\34\1\32\11\34\1"+
            "\36\2\34\1\33",
            "\2\6\1\uffff\2\6\1\142\6\6\1\153\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\3\6\1\163\1\162\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\164\2\6\1\165\4\6\1\155\1\7\1\6\1\10\14"+
            "\6\1\5",
            "\2\6\1\uffff\12\6\1\166\1\6\1\10\10\6\1\167\3\6\1\5",
            "\2\73\1\uffff\7\73\1\171\2\73\1\170\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\172\4\34\1\173\3\34\1\31\1\34\1\32\11"+
            "\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\174\1\34\1\32\10\34\1\175\1\36\2\34\1"+
            "\33",
            "\2\6\1\uffff\1\6\1\106\4\6\1\107\3\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\176\1\6\1\10\10\6\1\177\3\6\1\5",
            "\2\6\1\uffff\12\6\1\u0080\1\6\1\10\10\6\1\u0081\3\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\166\1\6\1\10\10\6\1\167\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u0082\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\7\73\1\171\2\73\1\170\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0083\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\7\34\1\u0085\2\34\1\u0084\1\34\1\32\11\34\1\36"+
            "\2\34\1\33",
            "\2\34\1\uffff\12\34\1\62\1\34\1\32\1\63\10\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\174\1\34\1\32\10\34\1\175\1\36\2\34\1"+
            "\33",
            "\2\34\1\uffff\11\34\1\u0086\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\12\6\1\176\1\6\1\10\10\6\1\177\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u0087\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u0080\1\6\1\10\10\6\1\u0081\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u0088\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\u008a\2\6\1\u0089\4\6\1\u0082\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\73\1\uffff\2\73\1\u008c\6\73\1\u0083\1\66\1\73\1\67\7\73"+
            "\1\u008b\1\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\7\34\1\u0085\2\34\1\u0084\1\34\1\32\11\34\1\36"+
            "\2\34\1\33",
            "\2\34\1\uffff\11\34\1\u008d\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\1\34\1\u008e\2\34\1\u008f\4\34\1\u0086\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u0091\2\6\1\u0090\4\6\1\u0087\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\6\1\uffff\4\6\1\u0092\4\6\1\u0088\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u0093\1\6\1\10\10\6\1\u0094\3\6\1\5",
            "\2\73\1\uffff\11\73\1\u0095\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\3\73\1\u0096\6\73\1\66\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\34\1\uffff\2\34\1\u0098\6\34\1\u008d\1\31\1\34\1\32\7\34"+
            "\1\u0097\1\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u0099\1\34\1\32\10\34\1\u009a\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\u009f\1\6\1\10\10\6\1\u00a0\3\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u0093\1\6\1\10\10\6\1\u0094\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00a1\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\2\73\1\u008c\6\73\1\u0095\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\3\73\1\u00a3\1\u00a2\5\73\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\11\34\1\u00a4\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\3\34\1\u00a5\6\34\1\31\1\34\1\32\11\34\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\12\34\1\u0099\1\34\1\32\10\34\1\u009a\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00a6\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\3\34\1\u00a7\6\34\1\31\1\34\1\32\11\34\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\172\4\34\1\173\3\34\1\31\1\34\1\32\11"+
            "\34\1\36\2\34\1\33",
            "\2\6\1\uffff\3\6\1\u00a8\6\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\106\4\6\1\107\3\6\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u009f\1\6\1\10\10\6\1\u00a0\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00a9\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\u00aa\2\6\1\u00ab\4\6\1\u00a1\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\73\1\uffff\1\73\1\u00ac\4\73\1\u00ad\3\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00ae\1\73\1\67\10\73\1\u00af\1\70\1"+
            "\73\1\100\1\71",
            "\2\34\1\uffff\2\34\1\u0098\6\34\1\u00a4\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\34\1\uffff\3\34\1\u00b1\1\u00b0\5\34\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\34\1\uffff\1\34\1\u00b2\2\34\1\u00b3\4\34\1\u00a6\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00b4\1\34\1\32\10\34\1\u00b5\1\36\2"+
            "\34\1\33",
            "\2\6\1\uffff\12\6\1\u00b6\1\6\1\10\10\6\1\u00b7\3\6\1\5",
            "\2\6\1\uffff\1\6\1\u00b8\2\6\1\u00b9\4\6\1\u00a9\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u00ba\1\6\1\10\10\6\1\u00bb\3\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\7\73\1\u00bd\2\73\1\u00bc\1\73\1\67\11\73\1\70"+
            "\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\135\1\73\1\67\1\136\10\73\1\70\1\73\1"+
            "\100\1\71",
            "\2\73\1\uffff\12\73\1\u00ae\1\73\1\67\10\73\1\u00af\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u00be\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\1\34\1\172\4\34\1\173\3\34\1\31\1\34\1\32\11"+
            "\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00bf\1\34\1\32\10\34\1\u00c0\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\12\34\1\u00c1\1\34\1\32\10\34\1\u00c2\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00b4\1\34\1\32\10\34\1\u00b5\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00c3\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\12\6\1\u00b6\1\6\1\10\10\6\1\u00b7\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00c4\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u00c5\1\6\1\10\10\6\1\u00c6\3\6\1\5",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\u00ba\1\6\1\10\10\6\1\u00bb\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00c7\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\7\73\1\u00bd\2\73\1\u00bc\1\73\1\67\11\73\1\70"+
            "\1\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u00c8\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00c9\2\73\1\u00ca\4\73\1\u00be\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u00bf\1\34\1\32\10\34\1\u00c0\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00cb\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\u00c1\1\34\1\32\10\34\1\u00c2\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00cc\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\1\34\1\u00ce\2\34\1\u00cd\4\34\1\u00c3\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u00cf\2\6\1\u00d0\4\6\1\u00c4\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\6\1\uffff\12\6\1\u00c5\1\6\1\10\10\6\1\u00c6\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00d1\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\4\6\1\u00d2\4\6\1\u00c7\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\2\73\1\u00d4\6\73\1\u00c8\1\66\1\73\1\67\7\73"+
            "\1\u00d3\1\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00d5\1\73\1\67\10\73\1\u00d6\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u00da\2\34\1\u00d9\4\34\1\u00cb\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\4\34\1\u00db\4\34\1\u00cc\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00dc\1\34\1\32\10\34\1\u00dd\1\36\2"+
            "\34\1\33",
            "\2\6\1\uffff\12\6\1\u00de\1\6\1\10\10\6\1\u00df\3\6\1\5",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\6\1\uffff\4\6\1\u00e0\4\6\1\u00d1\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\145\2\6\1\146\5\6\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\11\73\1\u00e1\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\3\73\1\u00e2\6\73\1\66\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00d5\1\73\1\67\10\73\1\u00d6\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u00e3\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\3\73\1\u00e4\6\73\1\66\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00ac\4\73\1\u00ad\3\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00e7\1\34\1\32\10\34\1\u00e8\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00dc\1\34\1\32\10\34\1\u00dd\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00e9\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\12\6\1\u00de\1\6\1\10\10\6\1\u00df\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u00ea\1\7\1\6\1\10\14\6\1\5",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\73\1\uffff\2\73\1\u00d4\6\73\1\u00e1\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\3\73\1\u00ec\1\u00eb\5\73\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00ed\2\73\1\u00ee\4\73\1\u00e3\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00ef\1\73\1\67\10\73\1\u00f0\1\70\1"+
            "\73\1\100\1\71",
            "\2\34\1\uffff\3\34\1\u00f1\6\34\1\31\1\34\1\32\11\34\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\172\4\34\1\173\3\34\1\31\1\34\1\32\11"+
            "\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u00e7\1\34\1\32\10\34\1\u00e8\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u00f2\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\1\34\1\u00f3\2\34\1\u00f4\4\34\1\u00e9\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u00f6\2\6\1\u00f5\4\6\1\u00ea\1\7\1\6\1"+
            "\10\14\6\1\5",
            "\2\73\1\uffff\1\73\1\u00ac\4\73\1\u00ad\3\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00f7\1\73\1\67\10\73\1\u00f8\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00f9\1\73\1\67\10\73\1\u00fa\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00ef\1\73\1\67\10\73\1\u00f0\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u00fb\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u00fc\1\34\1\32\10\34\1\u00fd\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u00fe\2\34\1\u00ff\4\34\1\u00f2\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u0100\1\34\1\32\10\34\1\u0101\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\6\1\uffff\12\6\1\u0102\1\6\1\10\10\6\1\u0103\3\6\1\5",
            "\2\73\1\uffff\12\73\1\u00f7\1\73\1\67\10\73\1\u00f8\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0104\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u00f9\1\73\1\67\10\73\1\u00fa\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0105\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0107\2\73\1\u0106\4\73\1\u00fb\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u00fc\1\34\1\32\10\34\1\u00fd\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u0108\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\12\34\1\u0109\1\34\1\32\10\34\1\u010a\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u0100\1\34\1\32\10\34\1\u0101\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u010b\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\6\1\uffff\12\6\1\u0102\1\6\1\10\10\6\1\u0103\3\6\1\5",
            "\2\6\1\uffff\11\6\1\u010c\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\1\73\1\u010e\2\73\1\u010d\4\73\1\u0104\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\4\73\1\u010f\4\73\1\u0105\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0110\1\73\1\67\10\73\1\u0111\1\70\1"+
            "\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u0112\2\34\1\u0113\4\34\1\u0108\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u0109\1\34\1\32\10\34\1\u010a\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u0114\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\4\34\1\u0115\4\34\1\u010b\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\6\1\uffff\4\6\1\u0116\4\6\1\u010c\1\7\1\6\1\10\14\6\1\5",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0119\1\73\1\67\10\73\1\u011a\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0110\1\73\1\67\10\73\1\u0111\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u011b\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u011c\1\34\1\32\10\34\1\u011d\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\4\34\1\u011e\4\34\1\u0114\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\34\1\uffff\1\34\1\u009b\2\34\1\u009c\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\6\1\uffff\1\6\1\u009d\2\6\1\u009e\5\6\1\7\1\6\1\10\14\6"+
            "\1\5",
            "\2\73\1\uffff\3\73\1\u011f\6\73\1\66\1\73\1\67\11\73\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00ac\4\73\1\u00ad\3\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0119\1\73\1\67\10\73\1\u011a\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0120\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0121\2\73\1\u0122\4\73\1\u011b\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u011c\1\34\1\32\10\34\1\u011d\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u0123\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\73\1\uffff\12\73\1\u0124\1\73\1\67\10\73\1\u0125\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0126\2\73\1\u0127\4\73\1\u0120\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0128\1\73\1\67\10\73\1\u0129\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u012b\2\34\1\u012a\4\34\1\u0123\1\31\1"+
            "\34\1\32\11\34\1\36\2\34\1\33",
            "\2\73\1\uffff\12\73\1\u0124\1\73\1\67\10\73\1\u0125\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u012c\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u012d\1\73\1\67\10\73\1\u012e\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u0128\1\73\1\67\10\73\1\u0129\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u012f\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\34\1\uffff\12\34\1\u0130\1\34\1\32\10\34\1\u0131\1\36\2"+
            "\34\1\33",
            "\2\73\1\uffff\1\73\1\u0132\2\73\1\u0133\4\73\1\u012c\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u012d\1\73\1\67\10\73\1\u012e\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0134\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\4\73\1\u0135\4\73\1\u012f\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\12\34\1\u0130\1\34\1\32\10\34\1\u0131\1\36\2"+
            "\34\1\33",
            "\2\34\1\uffff\11\34\1\u0136\1\31\1\34\1\32\11\34\1\36\2\34"+
            "\1\33",
            "\2\73\1\uffff\12\73\1\u0137\1\73\1\67\10\73\1\u0138\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\4\73\1\u0139\4\73\1\u0134\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u00d7\2\73\1\u00d8\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\4\34\1\u013a\4\34\1\u0136\1\31\1\34\1\32\11\34"+
            "\1\36\2\34\1\33",
            "\2\73\1\uffff\12\73\1\u0137\1\73\1\67\10\73\1\u0138\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u013b\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\34\1\uffff\1\34\1\u00e5\2\34\1\u00e6\5\34\1\31\1\34\1\32"+
            "\11\34\1\36\2\34\1\33",
            "\2\73\1\uffff\1\73\1\u013d\2\73\1\u013c\4\73\1\u013b\1\66\1"+
            "\73\1\67\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u013e\1\73\1\67\10\73\1\u013f\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\12\73\1\u013e\1\73\1\67\10\73\1\u013f\1\70\1"+
            "\73\1\100\1\71",
            "\2\73\1\uffff\11\73\1\u0140\1\66\1\73\1\67\11\73\1\70\1\73"+
            "\1\100\1\71",
            "\2\73\1\uffff\4\73\1\u0141\4\73\1\u0140\1\66\1\73\1\67\11\73"+
            "\1\70\1\73\1\100\1\71",
            "\2\73\1\uffff\1\73\1\u0117\2\73\1\u0118\5\73\1\66\1\73\1\67"+
            "\11\73\1\70\1\73\1\100\1\71"
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "()+ loopback of 137:9: ( ns )+";
        }
    }
    static final String DFA13_eotS =
        "\u01a3\uffff";
    static final String DFA13_eofS =
        "\2\uffff\1\3\20\uffff\1\1\34\uffff\1\50\33\uffff\1\50\u0156\uffff";
    static final String DFA13_minS =
        "\1\4\1\uffff\1\4\1\uffff\44\4\1\uffff\62\4\1\uffff\12\4\1\uffff"+
        "\32\4\1\uffff\u0121\4";
    static final String DFA13_maxS =
        "\1\40\1\uffff\1\40\1\uffff\44\40\1\uffff\62\40\1\uffff\12\40\1\uffff"+
        "\32\40\1\uffff\u0121\40";
    static final String DFA13_acceptS =
        "\1\uffff\1\1\1\uffff\1\2\44\uffff\1\1\62\uffff\1\1\12\uffff\1\1"+
        "\32\uffff\1\1\u0121\uffff";
    static final String DFA13_specialS =
        "\u01a3\uffff}>";
    static final String[] DFA13_transitionS = {
            "\2\1\1\uffff\14\1\1\2\15\1",
            "",
            "\2\1\1\uffff\12\1\1\4\1\5\16\1",
            "",
            "\2\1\1\uffff\12\1\1\4\1\5\16\1",
            "\2\1\1\uffff\12\1\1\6\5\1\1\7\11\1",
            "\2\1\1\uffff\12\1\1\6\5\1\1\7\11\1",
            "\2\1\1\uffff\12\1\1\10\3\1\1\13\1\12\1\1\1\11\10\1",
            "\2\1\1\uffff\12\1\1\10\3\1\1\13\1\12\1\1\1\11\10\1",
            "\2\1\1\uffff\16\1\1\13\1\12\12\1",
            "\2\1\1\uffff\12\1\1\14\7\1\1\15\7\1",
            "\2\1\1\uffff\12\1\1\16\2\1\1\17\14\1",
            "\2\1\1\uffff\12\1\1\14\7\1\1\15\7\1",
            "\2\1\1\uffff\12\1\1\20\10\1\1\21\6\1",
            "\2\1\1\uffff\12\1\1\16\2\1\1\17\14\1",
            "\2\24\1\uffff\12\24\1\22\1\24\1\23\15\24",
            "\2\1\1\uffff\12\1\1\20\10\1\1\21\6\1",
            "\2\1\1\uffff\5\1\1\25\24\1",
            "\2\24\1\uffff\12\24\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\27\1\30\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\1\1\uffff\7\1\1\32\2\1\1\31\17\1",
            "\2\24\1\uffff\11\24\1\33\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\27\1\30\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\34\1\24\1\23\3\24\1\35\5\24\1\26\3\24",
            "\2\1\1\uffff\7\1\1\32\2\1\1\31\17\1",
            "\2\1\1\uffff\11\1\1\36\20\1",
            "\2\24\1\uffff\11\24\1\33\1\22\1\24\1\23\11\24\1\26\1\37\2\24",
            "\2\24\1\uffff\12\24\1\34\1\24\1\23\3\24\1\35\5\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\40\1\24\1\23\1\24\1\42\1\43\1\24\1\41"+
            "\4\24\1\26\3\24",
            "\2\1\1\uffff\2\1\1\45\6\1\1\36\12\1\1\44\5\1",
            "\2\24\1\uffff\11\24\1\46\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\40\1\24\1\23\1\24\1\42\1\43\1\24\1\41"+
            "\4\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\22\1\24\1\23\1\24\1\42\1\43\6\24\1\26"+
            "\3\24",
            "\2\24\1\uffff\12\24\1\47\1\24\1\23\1\50\10\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\51\1\24\1\23\5\24\1\52\3\24\1\26\3\24",
            "\2\50\1\uffff\11\50\1\53\20\50",
            "\2\50\1\uffff\3\50\1\54\26\50",
            "\2\61\1\uffff\11\61\1\56\1\57\1\61\1\60\11\61\1\55\3\61",
            "\2\24\1\uffff\12\24\1\47\1\24\1\23\1\50\10\24\1\26\3\24",
            "",
            "\2\24\1\uffff\12\24\1\51\1\24\1\23\5\24\1\52\3\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\62\1\24\1\23\6\24\1\63\2\24\1\26\3\24",
            "\2\50\1\uffff\2\50\1\45\6\50\1\53\20\50",
            "\2\50\1\uffff\3\50\1\65\1\64\25\50",
            "\2\61\1\uffff\11\61\1\67\1\57\1\61\1\60\11\61\1\55\1\61\1\66"+
            "\1\61",
            "\2\61\1\uffff\11\61\1\56\1\57\1\61\1\60\11\61\1\55\1\61\1\66"+
            "\1\61",
            "\2\61\1\uffff\12\61\1\57\1\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\70\1\71\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\57\1\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\62\1\24\1\23\6\24\1\63\2\24\1\26\3\24",
            "\2\24\1\uffff\5\24\1\72\4\24\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\50\1\uffff\1\50\1\73\4\50\1\74\23\50",
            "\2\50\1\uffff\12\50\1\75\12\50\1\76\4\50",
            "\2\61\1\uffff\11\61\1\77\1\57\1\61\1\60\11\61\1\55\1\61\1\66"+
            "\1\61",
            "\2\61\1\uffff\11\61\1\67\1\57\1\61\1\60\11\61\1\55\1\100\1"+
            "\66\1\61",
            "\2\61\1\uffff\12\61\1\70\1\71\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\101\1\61\1\60\3\61\1\102\5\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\7\24\1\104\2\24\1\103\1\24\1\23\11\24\1\26\3"+
            "\24",
            "\2\50\1\uffff\7\50\1\106\2\50\1\105\17\50",
            "\2\50\1\uffff\12\50\1\16\2\50\1\17\14\50",
            "\2\50\1\uffff\12\50\1\75\12\50\1\76\4\50",
            "\2\50\1\uffff\11\50\1\107\20\50",
            "\2\115\1\uffff\11\115\1\110\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\115",
            "\2\61\1\uffff\11\61\1\56\1\57\1\61\1\60\11\61\1\55\1\61\1\66"+
            "\1\61",
            "\2\61\1\uffff\12\61\1\101\1\61\1\60\3\61\1\102\5\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\116\1\61\1\60\1\61\1\120\1\121\1\61\1"+
            "\117\4\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\7\24\1\104\2\24\1\103\1\24\1\23\11\24\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\122\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\50\1\uffff\7\50\1\106\2\50\1\105\17\50",
            "\2\50\1\uffff\11\50\1\123\20\50",
            "\2\50\1\uffff\1\50\1\124\2\50\1\125\4\50\1\107\20\50",
            "\2\115\1\uffff\11\115\1\110\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\110\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\127\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\113\1\115\1\114\11\115\1\112\1\115"+
            "\1\111\1\126",
            "\2\115\1\uffff\12\115\1\130\1\131\1\114\11\115\1\112\1\115"+
            "\1\111\1\126",
            "\2\115\1\uffff\12\115\1\113\1\115\1\114\11\115\1\112\1\115"+
            "\1\111\1\126",
            "\2\61\1\uffff\12\61\1\116\1\61\1\60\1\61\1\120\1\121\1\61\1"+
            "\117\4\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\57\1\61\1\60\1\61\1\120\1\121\6\61\1"+
            "\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\132\1\61\1\60\1\133\10\61\1\55\1\61\1"+
            "\66\1\61",
            "\2\61\1\uffff\12\61\1\134\1\61\1\60\5\61\1\135\3\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\2\24\1\136\6\24\1\122\1\22\1\24\1\23\7\24\1\137"+
            "\1\24\1\26\3\24",
            "\2\133\1\uffff\2\133\1\141\6\133\1\123\12\133\1\140\5\133",
            "\2\133\1\uffff\12\133\1\142\12\133\1\143\4\133",
            "\2\133\1\uffff\1\133\1\144\2\133\1\145\25\133",
            "\2\115\1\uffff\11\115\1\146\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\127\1\113\1\115\1\114\11\115\1\112"+
            "\1\147\1\111\1\126",
            "\2\115\1\uffff\12\115\1\130\1\131\1\114\11\115\1\112\1\115"+
            "\1\111\1\126",
            "\2\115\1\uffff\12\115\1\150\1\115\1\114\3\115\1\151\5\115\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\132\1\61\1\60\1\133\10\61\1\55\1\61\1"+
            "\66\1\61",
            "",
            "\2\61\1\uffff\12\61\1\134\1\61\1\60\5\61\1\135\3\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\152\1\61\1\60\6\61\1\153\2\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\3\24\1\154\6\24\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\11\24\1\155\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\146\1\uffff\11\146\1\156\20\146",
            "\2\146\1\uffff\3\146\1\157\26\146",
            "\2\146\1\uffff\12\146\1\142\12\146\1\143\4\146",
            "\2\146\1\uffff\11\146\1\160\20\146",
            "\2\146\1\uffff\3\146\1\161\26\146",
            "\2\146\1\uffff\1\146\1\73\4\146\1\74\23\146",
            "",
            "\2\115\1\uffff\11\115\1\162\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\150\1\115\1\114\3\115\1\151\5\115\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\163\1\115\1\114\1\115\1\165\1\166\1"+
            "\115\1\164\4\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\152\1\61\1\60\6\61\1\153\2\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\5\61\1\167\4\61\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\3\24\1\171\1\170\5\24\1\22\1\24\1\23\11\24\1"+
            "\26\3\24",
            "\2\24\1\uffff\2\24\1\136\6\24\1\155\1\22\1\24\1\23\11\24\1"+
            "\26\3\24",
            "\2\146\1\uffff\2\146\1\141\6\146\1\156\20\146",
            "\2\146\1\uffff\3\146\1\173\1\172\25\146",
            "\2\146\1\uffff\1\146\1\174\2\146\1\175\4\146\1\160\20\146",
            "\2\146\1\uffff\12\146\1\176\12\146\1\177\4\146",
            "\2\115\1\uffff\11\115\1\162\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\163\1\115\1\114\1\115\1\165\1\166\1"+
            "\115\1\164\4\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\113\1\115\1\114\1\115\1\165\1\166\6"+
            "\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0080\1\115\1\114\1\u0081\10\115\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0082\1\115\1\114\5\115\1\u0083\3\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\7\61\1\u0085\2\61\1\u0084\1\61\1\60\11\61\1\55"+
            "\1\61\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0086\4\24\1\u0087\3\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0088\1\24\1\23\10\24\1\u0089\1\26\3"+
            "\24",
            "\2\u0081\1\uffff\1\u0081\1\73\4\u0081\1\74\23\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u008a\12\u0081\1\u008b\4\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u008c\12\u0081\1\u008d\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\144\2\u0081\1\145\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\176\12\u0081\1\177\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u008e\20\u0081",
            "\2\115\1\uffff\12\115\1\u0080\1\115\1\114\1\u0081\10\115\1"+
            "\112\1\115\1\111\1\126",
            "",
            "\2\115\1\uffff\12\115\1\u0082\1\115\1\114\5\115\1\u0083\3\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u008f\1\115\1\114\6\115\1\u0090\2\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\7\61\1\u0085\2\61\1\u0084\1\61\1\60\11\61\1\55"+
            "\1\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0091\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\7\24\1\u0093\2\24\1\u0092\1\24\1\23\11\24\1\26"+
            "\3\24",
            "\2\24\1\uffff\12\24\1\47\1\24\1\23\1\50\10\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0088\1\24\1\23\10\24\1\u0089\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0094\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\12\u0081\1\u008a\12\u0081\1\u008b\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u0095\20\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u008c\12\u0081\1\u008d\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u0096\20\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u0098\2\u0081\1\u0097\4\u0081\1"+
            "\u008e\20\u0081",
            "\2\115\1\uffff\12\115\1\u008f\1\115\1\114\6\115\1\u0090\2\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\5\115\1\u0099\4\115\1\113\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\2\61\1\u009a\6\61\1\u0091\1\57\1\61\1\60\7\61"+
            "\1\u009b\1\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\7\24\1\u0093\2\24\1\u0092\1\24\1\23\11\24\1\26"+
            "\3\24",
            "\2\24\1\uffff\11\24\1\u009c\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u009e\2\24\1\u009d\4\24\1\u0094\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\1\u0081\1\u00a0\2\u0081\1\u009f\4\u0081\1"+
            "\u0095\20\u0081",
            "\2\u0081\1\uffff\4\u0081\1\u00a1\4\u0081\1\u0096\20\u0081",
            "\2\u0081\1\uffff\1\u0081\1\144\2\u0081\1\145\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00a2\12\u0081\1\u00a3\4\u0081",
            "\2\115\1\uffff\7\115\1\u00a5\2\115\1\u00a4\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\3\61\1\u00a6\6\61\1\57\1\61\1\60\11\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u00a7\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\2\24\1\u00a9\6\24\1\u009c\1\22\1\24\1\23\7\24"+
            "\1\u00a8\1\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00ac\1\24\1\23\10\24\1\u00ad\1\26\3"+
            "\24",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00b0\12\u0081\1\u00b1\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\144\2\u0081\1\145\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00a2\12\u0081\1\u00a3\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u00b2\20\u0081",
            "\2\115\1\uffff\7\115\1\u00a5\2\115\1\u00a4\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u00b3\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\3\61\1\u00b5\1\u00b4\5\61\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\2\61\1\u009a\6\61\1\u00a7\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\11\24\1\u00b6\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\3\24\1\u00b7\6\24\1\22\1\24\1\23\11\24\1\26\3"+
            "\24",
            "\2\24\1\uffff\3\24\1\u00b8\6\24\1\22\1\24\1\23\11\24\1\26\3"+
            "\24",
            "\2\24\1\uffff\1\24\1\u0086\4\24\1\u0087\3\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00ac\1\24\1\23\10\24\1\u00ad\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u00b9\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\3\u0081\1\u00ba\26\u0081",
            "\2\u0081\1\uffff\1\u0081\1\73\4\u0081\1\74\23\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00b0\12\u0081\1\u00b1\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u00bb\20\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u00bc\2\u0081\1\u00bd\4\u0081\1"+
            "\u00b2\20\u0081",
            "\2\115\1\uffff\2\115\1\u00be\6\115\1\u00b3\1\113\1\115\1\114"+
            "\7\115\1\u00bf\1\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u00c0\4\61\1\u00c1\3\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u00c2\1\61\1\60\10\61\1\u00c3\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\2\24\1\u00a9\6\24\1\u00b6\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\24\1\uffff\3\24\1\u00c5\1\u00c4\5\24\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00c6\1\24\1\23\10\24\1\u00c7\1\26\3"+
            "\24",
            "\2\24\1\uffff\1\24\1\u00c9\2\24\1\u00c8\4\24\1\u00b9\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\12\u0081\1\u00ca\12\u0081\1\u00cb\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u00cc\2\u0081\1\u00cd\4\u0081\1"+
            "\u00bb\20\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00ce\12\u0081\1\u00cf\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\144\2\u0081\1\145\25\u0081",
            "\2\115\1\uffff\3\115\1\u00d0\6\115\1\113\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u00d1\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\7\61\1\u00d3\2\61\1\u00d2\1\61\1\60\11\61\1\55"+
            "\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\132\1\61\1\60\1\133\10\61\1\55\1\61\1"+
            "\66\1\61",
            "\2\61\1\uffff\12\61\1\u00c2\1\61\1\60\10\61\1\u00c3\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u00d4\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0086\4\24\1\u0087\3\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00d5\1\24\1\23\10\24\1\u00d6\1\26\3"+
            "\24",
            "\2\24\1\uffff\12\24\1\u00c6\1\24\1\23\10\24\1\u00c7\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u00d7\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00d8\1\24\1\23\10\24\1\u00d9\1\26\3"+
            "\24",
            "\2\u0081\1\uffff\12\u0081\1\u00ca\12\u0081\1\u00cb\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u00da\20\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00db\12\u0081\1\u00dc\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00ce\12\u0081\1\u00cf\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u00dd\20\u0081",
            "\2\115\1\uffff\3\115\1\u00df\1\u00de\5\115\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\2\115\1\u00be\6\115\1\u00d1\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\7\61\1\u00d3\2\61\1\u00d2\1\61\1\60\11\61\1\55"+
            "\1\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u00e0\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00e2\2\61\1\u00e1\4\61\1\u00d4\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u00d5\1\24\1\23\10\24\1\u00d6\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u00e3\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00e5\2\24\1\u00e4\4\24\1\u00d7\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00d8\1\24\1\23\10\24\1\u00d9\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u00e6\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\1\u0081\1\u00e7\2\u0081\1\u00e8\4\u0081\1"+
            "\u00da\20\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u00db\12\u0081\1\u00dc\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u00e9\20\u0081",
            "\2\u0081\1\uffff\4\u0081\1\u00ea\4\u0081\1\u00dd\20\u0081",
            "\2\115\1\uffff\1\115\1\u00eb\4\115\1\u00ec\3\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u00ed\1\115\1\114\10\115\1\u00ee\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\2\61\1\u00f0\6\61\1\u00e0\1\57\1\61\1\60\7\61"+
            "\1\u00ef\1\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u00f3\1\61\1\60\10\61\1\u00f4\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u00f6\2\24\1\u00f5\4\24\1\u00e3\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u00f7\1\24\1\23\10\24\1\u00f8\1\26\3"+
            "\24",
            "\2\24\1\uffff\4\24\1\u00f9\4\24\1\u00e6\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\u0081\1\uffff\12\u0081\1\u00fa\12\u0081\1\u00fb\4\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\u0081\1\uffff\4\u0081\1\u00fc\4\u0081\1\u00e9\20\u0081",
            "\2\u0081\1\uffff\1\u0081\1\144\2\u0081\1\145\25\u0081",
            "\2\115\1\uffff\7\115\1\u00fe\2\115\1\u00fd\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0080\1\115\1\114\1\u0081\10\115\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u00ed\1\115\1\114\10\115\1\u00ee\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u00ff\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\11\61\1\u0100\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\3\61\1\u0101\6\61\1\57\1\61\1\60\11\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\3\61\1\u0102\6\61\1\57\1\61\1\60\11\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00c0\4\61\1\u00c1\3\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u00f3\1\61\1\60\10\61\1\u00f4\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0103\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0106\1\24\1\23\10\24\1\u0107\1\26\3"+
            "\24",
            "\2\24\1\uffff\12\24\1\u00f7\1\24\1\23\10\24\1\u00f8\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0108\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\u0081\1\uffff\12\u0081\1\u00fa\12\u0081\1\u00fb\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u0109\20\u0081",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\115\1\uffff\7\115\1\u00fe\2\115\1\u00fd\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u010a\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u010c\2\115\1\u010b\4\115\1\u00ff\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\2\61\1\u00f0\6\61\1\u0100\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\3\61\1\u010e\1\u010d\5\61\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u010f\1\61\1\60\10\61\1\u0110\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u0112\2\61\1\u0111\4\61\1\u0103\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\3\24\1\u0113\6\24\1\22\1\24\1\23\11\24\1\26\3"+
            "\24",
            "\2\24\1\uffff\1\24\1\u0086\4\24\1\u0087\3\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0106\1\24\1\23\10\24\1\u0107\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0114\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u0116\2\24\1\u0115\4\24\1\u0108\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\1\u0081\1\u0118\2\u0081\1\u0117\4\u0081\1"+
            "\u0109\20\u0081",
            "\2\115\1\uffff\2\115\1\u011a\6\115\1\u010a\1\113\1\115\1\114"+
            "\7\115\1\u0119\1\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u011d\1\115\1\114\10\115\1\u011e\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u00c0\4\61\1\u00c1\3\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u011f\1\61\1\60\10\61\1\u0120\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u010f\1\61\1\60\10\61\1\u0110\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0121\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0122\1\61\1\60\10\61\1\u0123\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u0124\1\24\1\23\10\24\1\u0125\1\26\3"+
            "\24",
            "\2\24\1\uffff\1\24\1\u0127\2\24\1\u0126\4\24\1\u0114\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0128\1\24\1\23\10\24\1\u0129\1\26\3"+
            "\24",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\u0081\1\uffff\12\u0081\1\u012a\12\u0081\1\u012b\4\u0081",
            "\2\115\1\uffff\11\115\1\u012c\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\3\115\1\u012d\6\115\1\113\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\3\115\1\u012e\6\115\1\113\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u00eb\4\115\1\u00ec\3\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u011d\1\115\1\114\10\115\1\u011e\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u012f\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u011f\1\61\1\60\10\61\1\u0120\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0130\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u0132\2\61\1\u0131\4\61\1\u0121\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0122\1\61\1\60\10\61\1\u0123\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0133\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u0124\1\24\1\23\10\24\1\u0125\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0134\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0135\1\24\1\23\10\24\1\u0136\1\26\3"+
            "\24",
            "\2\24\1\uffff\12\24\1\u0128\1\24\1\23\10\24\1\u0129\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0137\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\u0081\1\uffff\12\u0081\1\u012a\12\u0081\1\u012b\4\u0081",
            "\2\u0081\1\uffff\11\u0081\1\u0138\20\u0081",
            "\2\115\1\uffff\2\115\1\u011a\6\115\1\u012c\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\3\115\1\u013a\1\u0139\5\115\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u013b\1\115\1\114\10\115\1\u013c\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u013e\2\115\1\u013d\4\115\1\u012f\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u0140\2\61\1\u013f\4\61\1\u0130\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0141\1\61\1\60\10\61\1\u0142\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\4\61\1\u0143\4\61\1\u0133\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0144\2\24\1\u0145\4\24\1\u0134\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0135\1\24\1\23\10\24\1\u0136\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u0146\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\4\24\1\u0147\4\24\1\u0137\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\u0081\1\uffff\4\u0081\1\u0148\4\u0081\1\u0138\20\u0081",
            "\2\115\1\uffff\1\115\1\u00eb\4\115\1\u00ec\3\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0149\1\115\1\114\10\115\1\u014a\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u013b\1\115\1\114\10\115\1\u013c\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u014b\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u014c\1\115\1\114\10\115\1\u014d\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0150\1\61\1\60\10\61\1\u0151\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0141\1\61\1\60\10\61\1\u0142\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0152\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u0153\1\24\1\23\10\24\1\u0154\1\26\3"+
            "\24",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\4\24\1\u0155\4\24\1\u0146\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u00aa\2\24\1\u00ab\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\u0081\1\uffff\1\u0081\1\u00ae\2\u0081\1\u00af\25\u0081",
            "\2\115\1\uffff\12\115\1\u0149\1\115\1\114\10\115\1\u014a\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u0156\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u0158\2\115\1\u0157\4\115\1\u014b\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u014c\1\115\1\114\10\115\1\u014d\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u0159\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\3\61\1\u015a\6\61\1\57\1\61\1\60\11\61\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00c0\4\61\1\u00c1\3\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0150\1\61\1\60\10\61\1\u0151\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u015b\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u015d\2\61\1\u015c\4\61\1\u0152\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u0153\1\24\1\23\10\24\1\u0154\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u015e\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\115\1\uffff\1\115\1\u0160\2\115\1\u015f\4\115\1\u0156\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0161\1\115\1\114\10\115\1\u0162\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\4\115\1\u0163\4\115\1\u0159\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u0164\1\61\1\60\10\61\1\u0165\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u0167\2\61\1\u0166\4\61\1\u015b\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0168\1\61\1\60\10\61\1\u0169\1\55\1"+
            "\61\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u016b\2\24\1\u016a\4\24\1\u015e\1\22\1"+
            "\24\1\23\11\24\1\26\3\24",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u016e\1\115\1\114\10\115\1\u016f\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0161\1\115\1\114\10\115\1\u0162\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u0170\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u0164\1\61\1\60\10\61\1\u0165\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0171\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0172\1\61\1\60\10\61\1\u0173\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0168\1\61\1\60\10\61\1\u0169\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u0174\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\24\1\uffff\12\24\1\u0175\1\24\1\23\10\24\1\u0176\1\26\3"+
            "\24",
            "\2\115\1\uffff\3\115\1\u0177\6\115\1\113\1\115\1\114\11\115"+
            "\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u00eb\4\115\1\u00ec\3\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u016e\1\115\1\114\10\115\1\u016f\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u0178\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u017a\2\115\1\u0179\4\115\1\u0170\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u017b\2\61\1\u017c\4\61\1\u0171\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0172\1\61\1\60\10\61\1\u0173\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u017d\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\4\61\1\u017e\4\61\1\u0174\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\12\24\1\u0175\1\24\1\23\10\24\1\u0176\1\26\3"+
            "\24",
            "\2\24\1\uffff\11\24\1\u017f\1\22\1\24\1\23\11\24\1\26\3\24",
            "\2\115\1\uffff\12\115\1\u0180\1\115\1\114\10\115\1\u0181\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u0183\2\115\1\u0182\4\115\1\u0178\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0184\1\115\1\114\10\115\1\u0185\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u0186\1\61\1\60\10\61\1\u0187\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\4\61\1\u0188\4\61\1\u017d\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u00f1\2\61\1\u00f2\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\4\24\1\u0189\4\24\1\u017f\1\22\1\24\1\23\11\24"+
            "\1\26\3\24",
            "\2\115\1\uffff\12\115\1\u0180\1\115\1\114\10\115\1\u0181\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u018a\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u018b\1\115\1\114\10\115\1\u018c\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u0184\1\115\1\114\10\115\1\u0185\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u018d\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u0186\1\61\1\60\10\61\1\u0187\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u018e\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\24\1\uffff\1\24\1\u0104\2\24\1\u0105\5\24\1\22\1\24\1\23"+
            "\11\24\1\26\3\24",
            "\2\115\1\uffff\1\115\1\u018f\2\115\1\u0190\4\115\1\u018a\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u018b\1\115\1\114\10\115\1\u018c\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u0191\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\4\115\1\u0192\4\115\1\u018d\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u0194\2\61\1\u0193\4\61\1\u018e\1\57\1"+
            "\61\1\60\11\61\1\55\1\61\1\66\1\61",
            "\2\115\1\uffff\12\115\1\u0195\1\115\1\114\10\115\1\u0196\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\4\115\1\u0197\4\115\1\u0191\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u011b\2\115\1\u011c\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\61\1\uffff\12\61\1\u0198\1\61\1\60\10\61\1\u0199\1\55\1"+
            "\61\1\66\1\61",
            "\2\115\1\uffff\12\115\1\u0195\1\115\1\114\10\115\1\u0196\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u019a\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\12\61\1\u0198\1\61\1\60\10\61\1\u0199\1\55\1"+
            "\61\1\66\1\61",
            "\2\61\1\uffff\11\61\1\u019b\1\57\1\61\1\60\11\61\1\55\1\61"+
            "\1\66\1\61",
            "\2\115\1\uffff\1\115\1\u019d\2\115\1\u019c\4\115\1\u019a\1"+
            "\113\1\115\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\61\1\uffff\4\61\1\u019e\4\61\1\u019b\1\57\1\61\1\60\11\61"+
            "\1\55\1\61\1\66\1\61",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\12\115\1\u019f\1\115\1\114\10\115\1\u01a0\1"+
            "\112\1\115\1\111\1\126",
            "\2\61\1\uffff\1\61\1\u014e\2\61\1\u014f\5\61\1\57\1\61\1\60"+
            "\11\61\1\55\1\61\1\66\1\61",
            "\2\115\1\uffff\12\115\1\u019f\1\115\1\114\10\115\1\u01a0\1"+
            "\112\1\115\1\111\1\126",
            "\2\115\1\uffff\11\115\1\u01a1\1\113\1\115\1\114\11\115\1\112"+
            "\1\115\1\111\1\126",
            "\2\115\1\uffff\4\115\1\u01a2\4\115\1\u01a1\1\113\1\115\1\114"+
            "\11\115\1\112\1\115\1\111\1\126",
            "\2\115\1\uffff\1\115\1\u016c\2\115\1\u016d\5\115\1\113\1\115"+
            "\1\114\11\115\1\112\1\115\1\111\1\126"
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "()+ loopback of 137:92: ( ns )+";
        }
    }
 

    public static final BitSet FOLLOW_solution_in_solutions176 = new BitSet(new long[]{0x0000000000060002L});
    public static final BitSet FOLLOW_header_in_solution199 = new BitSet(new long[]{0x0000000000820000L});
    public static final BitSet FOLLOW_unsatOutcome_in_solution207 = new BitSet(new long[]{0x0000000000120000L});
    public static final BitSet FOLLOW_satOutcome_in_solution215 = new BitSet(new long[]{0x0000000002020000L});
    public static final BitSet FOLLOW_instance_in_solution221 = new BitSet(new long[]{0x0000000000120000L});
    public static final BitSet FOLLOW_stats_in_solution229 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_end_in_solution231 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_header250 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_18_in_header252 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_end261 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_end263 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_stats281 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_stats283 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_statText_in_stats289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_outcome_in_unsatOutcome309 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_unsatOutcome311 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_outcome_in_satOutcome340 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_satOutcome342 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_outcome361 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_outcome363 = new BitSet(new long[]{0x0000000001020000L});
    public static final BitSet FOLLOW_spaces_in_outcome365 = new BitSet(new long[]{0x0000000001000002L});
    public static final BitSet FOLLOW_24_in_outcome367 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_instance386 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_instance388 = new BitSet(new long[]{0x0000000004020000L});
    public static final BitSet FOLLOW_spaces_in_instance390 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_instance392 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_LBRACE_in_instance394 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_relations_in_instance400 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_RBRACE_in_instance402 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_relation_in_relations437 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_relations451 = new BitSet(new long[]{0x0000000000024000L});
    public static final BitSet FOLLOW_relation_in_relations457 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_name_in_relation485 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_EQUALS_in_relation487 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_value_in_relation493 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_name523 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_LOWER_in_name530 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_LOWER_in_name540 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_name546 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_name548 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_name568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_list_in_value593 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_list623 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_list625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_list643 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_listItems_in_list649 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_list651 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_listItem_in_listItems683 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_COMMA_in_listItems697 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_listItem_in_listItems703 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_binaryTuple_in_listItem736 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ternaryTuple_in_listItem744 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_unaryTuple_in_listItem752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_binaryTuple782 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_binaryTuple788 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_binaryTuple790 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_binaryTuple796 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_binaryTuple798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_ternaryTuple828 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple834 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_ternaryTuple836 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple842 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_COMMA_in_ternaryTuple844 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_ternaryTuple850 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_ternaryTuple852 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_LBRACKET_in_unaryTuple880 = new BitSet(new long[]{0x0000000010020000L});
    public static final BitSet FOLLOW_atom_in_unaryTuple886 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RBRACKET_in_unaryTuple888 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_spaces_in_atom916 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_atom918 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_atom924 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_letter0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIGIT_in_number976 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ns_in_statText1002 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_29_in_statText1005 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_statText1007 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_statText1009 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_statText1011 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_ns_in_statText1021 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_31_in_statText1024 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_statText1030 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_ns_in_statText1042 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_32_in_statText1045 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_number_in_statText1051 = new BitSet(new long[]{0x00000001FFFFFFB0L});
    public static final BitSet FOLLOW_ns_in_statText1055 = new BitSet(new long[]{0x00000001FFFFFFB2L});
    public static final BitSet FOLLOW_SPACE_in_spaces1065 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_set_in_ns1075 = new BitSet(new long[]{0x0000000000000002L});

}
