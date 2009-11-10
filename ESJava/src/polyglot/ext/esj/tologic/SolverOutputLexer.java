// $ANTLR 3.1.3 Mar 18, 2009 10:09:25 SolverOutput.g 2009-11-09 18:46:35

package polyglot.ext.esj.tologic;

import org.antlr.runtime.CommonTokenStream;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class SolverOutputLexer extends Lexer {
    public static final int COMMA=8;
    public static final int MINUS=5;
    public static final int SPACE=17;
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
    public static final int T__32=32;
    public static final int RBRACKET=11;
    public static final int T__24=24;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int DIV=7;
    public static final int PLUS=4;
    public static final int DIGIT=16;
    public static final int MULT=6;
    public static final int T__18=18;

    // delegates
    // delegators

    public SolverOutputLexer() {;} 
    public SolverOutputLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public SolverOutputLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "SolverOutput.g"; }

    // $ANTLR start "PLUS"
    public final void mPLUS() throws RecognitionException {
        try {
            int _type = PLUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:9:6: ( '+' )
            // SolverOutput.g:9:8: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "PLUS"

    // $ANTLR start "MINUS"
    public final void mMINUS() throws RecognitionException {
        try {
            int _type = MINUS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:10:7: ( '-' )
            // SolverOutput.g:10:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MINUS"

    // $ANTLR start "MULT"
    public final void mMULT() throws RecognitionException {
        try {
            int _type = MULT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:11:6: ( '*' )
            // SolverOutput.g:11:8: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "MULT"

    // $ANTLR start "DIV"
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:12:5: ( '/' )
            // SolverOutput.g:12:7: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIV"

    // $ANTLR start "COMMA"
    public final void mCOMMA() throws RecognitionException {
        try {
            int _type = COMMA;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:13:7: ( ',' )
            // SolverOutput.g:13:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "COMMA"

    // $ANTLR start "EQUALS"
    public final void mEQUALS() throws RecognitionException {
        try {
            int _type = EQUALS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:14:8: ( '=' )
            // SolverOutput.g:14:10: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "EQUALS"

    // $ANTLR start "LBRACKET"
    public final void mLBRACKET() throws RecognitionException {
        try {
            int _type = LBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:15:10: ( '[' )
            // SolverOutput.g:15:12: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACKET"

    // $ANTLR start "RBRACKET"
    public final void mRBRACKET() throws RecognitionException {
        try {
            int _type = RBRACKET;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:16:10: ( ']' )
            // SolverOutput.g:16:12: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACKET"

    // $ANTLR start "LBRACE"
    public final void mLBRACE() throws RecognitionException {
        try {
            int _type = LBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:17:8: ( '{' )
            // SolverOutput.g:17:10: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LBRACE"

    // $ANTLR start "RBRACE"
    public final void mRBRACE() throws RecognitionException {
        try {
            int _type = RBRACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:18:8: ( '}' )
            // SolverOutput.g:18:10: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RBRACE"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:19:7: ( '*** PROBLEM 1 ***' )
            // SolverOutput.g:19:9: '*** PROBLEM 1 ***'
            {
            match("*** PROBLEM 1 ***"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:20:7: ( '*** END ***' )
            // SolverOutput.g:20:9: '*** END ***'
            {
            match("*** END ***"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:21:7: ( '---STATS---' )
            // SolverOutput.g:21:9: '---STATS---'
            {
            match("---STATS---"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:22:7: ( 'UNSATISFIABLE' )
            // SolverOutput.g:22:9: 'UNSATISFIABLE'
            {
            match("UNSATISFIABLE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:23:7: ( 'SATISFIABLE' )
            // SolverOutput.g:23:9: 'SATISFIABLE'
            {
            match("SATISFIABLE"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:24:7: ( '---OUTCOME---' )
            // SolverOutput.g:24:9: '---OUTCOME---'
            {
            match("---OUTCOME---"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:25:7: ( 'TRIVIALLY_' )
            // SolverOutput.g:25:9: 'TRIVIALLY_'
            {
            match("TRIVIALLY_"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:26:7: ( '---INSTANCE---' )
            // SolverOutput.g:26:9: '---INSTANCE---'
            {
            match("---INSTANCE---"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:27:7: ( 'relations:' )
            // SolverOutput.g:27:9: 'relations:'
            {
            match("relations:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:28:7: ( '_' )
            // SolverOutput.g:28:9: '_'
            {
            match('_'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:29:7: ( 'A' )
            // SolverOutput.g:29:9: 'A'
            {
            match('A'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:30:7: ( 'primary variables: ' )
            // SolverOutput.g:30:9: 'primary variables: '
            {
            match("primary variables: "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:31:7: ( 'parsing time: ' )
            // SolverOutput.g:31:9: 'parsing time: '
            {
            match("parsing time: "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:32:7: ( 'translation time: ' )
            // SolverOutput.g:32:9: 'translation time: '
            {
            match("translation time: "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:33:7: ( 'solving time: ' )
            // SolverOutput.g:33:9: 'solving time: '
            {
            match("solving time: "); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "SPACE"
    public final void mSPACE() throws RecognitionException {
        try {
            int _type = SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:142:7: ( ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' ) )
            // SolverOutput.g:142:9: ( '\\t' | ' ' | '\\r' | '\\n' | '\\u000C' )
            {
            if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||(input.LA(1)>='\f' && input.LA(1)<='\r')||input.LA(1)==' ' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "SPACE"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            int _type = DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:144:7: ( '0' .. '9' )
            // SolverOutput.g:144:9: '0' .. '9'
            {
            matchRange('0','9'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "LOWER"
    public final void mLOWER() throws RecognitionException {
        try {
            int _type = LOWER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:146:7: ( 'a' .. 'z' )
            // SolverOutput.g:146:9: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "LOWER"

    // $ANTLR start "UPPER"
    public final void mUPPER() throws RecognitionException {
        try {
            int _type = UPPER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // SolverOutput.g:148:7: ( 'A' .. 'Z' )
            // SolverOutput.g:148:9: 'A' .. 'Z'
            {
            matchRange('A','Z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "UPPER"

    public void mTokens() throws RecognitionException {
        // SolverOutput.g:1:8: ( PLUS | MINUS | MULT | DIV | COMMA | EQUALS | LBRACKET | RBRACKET | LBRACE | RBRACE | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | SPACE | DIGIT | LOWER | UPPER )
        int alt1=29;
        alt1 = dfa1.predict(input);
        switch (alt1) {
            case 1 :
                // SolverOutput.g:1:10: PLUS
                {
                mPLUS(); 

                }
                break;
            case 2 :
                // SolverOutput.g:1:15: MINUS
                {
                mMINUS(); 

                }
                break;
            case 3 :
                // SolverOutput.g:1:21: MULT
                {
                mMULT(); 

                }
                break;
            case 4 :
                // SolverOutput.g:1:26: DIV
                {
                mDIV(); 

                }
                break;
            case 5 :
                // SolverOutput.g:1:30: COMMA
                {
                mCOMMA(); 

                }
                break;
            case 6 :
                // SolverOutput.g:1:36: EQUALS
                {
                mEQUALS(); 

                }
                break;
            case 7 :
                // SolverOutput.g:1:43: LBRACKET
                {
                mLBRACKET(); 

                }
                break;
            case 8 :
                // SolverOutput.g:1:52: RBRACKET
                {
                mRBRACKET(); 

                }
                break;
            case 9 :
                // SolverOutput.g:1:61: LBRACE
                {
                mLBRACE(); 

                }
                break;
            case 10 :
                // SolverOutput.g:1:68: RBRACE
                {
                mRBRACE(); 

                }
                break;
            case 11 :
                // SolverOutput.g:1:75: T__18
                {
                mT__18(); 

                }
                break;
            case 12 :
                // SolverOutput.g:1:81: T__19
                {
                mT__19(); 

                }
                break;
            case 13 :
                // SolverOutput.g:1:87: T__20
                {
                mT__20(); 

                }
                break;
            case 14 :
                // SolverOutput.g:1:93: T__21
                {
                mT__21(); 

                }
                break;
            case 15 :
                // SolverOutput.g:1:99: T__22
                {
                mT__22(); 

                }
                break;
            case 16 :
                // SolverOutput.g:1:105: T__23
                {
                mT__23(); 

                }
                break;
            case 17 :
                // SolverOutput.g:1:111: T__24
                {
                mT__24(); 

                }
                break;
            case 18 :
                // SolverOutput.g:1:117: T__25
                {
                mT__25(); 

                }
                break;
            case 19 :
                // SolverOutput.g:1:123: T__26
                {
                mT__26(); 

                }
                break;
            case 20 :
                // SolverOutput.g:1:129: T__27
                {
                mT__27(); 

                }
                break;
            case 21 :
                // SolverOutput.g:1:135: T__28
                {
                mT__28(); 

                }
                break;
            case 22 :
                // SolverOutput.g:1:141: T__29
                {
                mT__29(); 

                }
                break;
            case 23 :
                // SolverOutput.g:1:147: T__30
                {
                mT__30(); 

                }
                break;
            case 24 :
                // SolverOutput.g:1:153: T__31
                {
                mT__31(); 

                }
                break;
            case 25 :
                // SolverOutput.g:1:159: T__32
                {
                mT__32(); 

                }
                break;
            case 26 :
                // SolverOutput.g:1:165: SPACE
                {
                mSPACE(); 

                }
                break;
            case 27 :
                // SolverOutput.g:1:171: DIGIT
                {
                mDIGIT(); 

                }
                break;
            case 28 :
                // SolverOutput.g:1:177: LOWER
                {
                mLOWER(); 

                }
                break;
            case 29 :
                // SolverOutput.g:1:183: UPPER
                {
                mUPPER(); 

                }
                break;

        }

    }


    protected DFA1 dfa1 = new DFA1(this);
    static final String DFA1_eotS =
        "\2\uffff\1\31\1\33\7\uffff\3\27\1\26\2\uffff\3\26\31\uffff";
    static final String DFA1_eofS =
        "\55\uffff";
    static final String DFA1_minS =
        "\1\11\1\uffff\1\55\1\52\7\uffff\1\116\1\101\1\122\1\145\2\uffff"+
        "\1\141\1\162\1\157\4\uffff\1\55\1\uffff\1\52\12\uffff\1\111\1\40"+
        "\3\uffff\1\105\2\uffff";
    static final String DFA1_maxS =
        "\1\175\1\uffff\1\55\1\52\7\uffff\1\116\1\101\1\122\1\145\2\uffff"+
        "\2\162\1\157\4\uffff\1\55\1\uffff\1\52\12\uffff\1\123\1\40\3\uffff"+
        "\1\120\2\uffff";
    static final String DFA1_acceptS =
        "\1\uffff\1\1\2\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\4\uffff\1\24"+
        "\1\25\3\uffff\1\32\1\33\1\34\1\35\1\uffff\1\2\1\uffff\1\3\1\16\1"+
        "\17\1\21\1\23\1\25\1\26\1\27\1\30\1\31\2\uffff\1\15\1\20\1\22\1"+
        "\uffff\1\13\1\14";
    static final String DFA1_specialS =
        "\55\uffff}>";
    static final String[] DFA1_transitionS = {
            "\2\24\1\uffff\2\24\22\uffff\1\24\11\uffff\1\3\1\1\1\5\1\2\1"+
            "\uffff\1\4\12\25\3\uffff\1\6\3\uffff\1\20\21\27\1\14\1\15\1"+
            "\13\5\27\1\7\1\uffff\1\10\1\uffff\1\17\1\uffff\17\26\1\21\1"+
            "\26\1\16\1\23\1\22\6\26\1\11\1\uffff\1\12",
            "",
            "\1\30",
            "\1\32",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\34",
            "\1\35",
            "\1\36",
            "\1\37",
            "",
            "",
            "\1\42\20\uffff\1\41",
            "\1\43",
            "\1\44",
            "",
            "",
            "",
            "",
            "\1\45",
            "",
            "\1\46",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\51\5\uffff\1\50\3\uffff\1\47",
            "\1\52",
            "",
            "",
            "",
            "\1\54\12\uffff\1\53",
            "",
            ""
    };

    static final short[] DFA1_eot = DFA.unpackEncodedString(DFA1_eotS);
    static final short[] DFA1_eof = DFA.unpackEncodedString(DFA1_eofS);
    static final char[] DFA1_min = DFA.unpackEncodedStringToUnsignedChars(DFA1_minS);
    static final char[] DFA1_max = DFA.unpackEncodedStringToUnsignedChars(DFA1_maxS);
    static final short[] DFA1_accept = DFA.unpackEncodedString(DFA1_acceptS);
    static final short[] DFA1_special = DFA.unpackEncodedString(DFA1_specialS);
    static final short[][] DFA1_transition;

    static {
        int numStates = DFA1_transitionS.length;
        DFA1_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA1_transition[i] = DFA.unpackEncodedString(DFA1_transitionS[i]);
        }
    }

    class DFA1 extends DFA {

        public DFA1(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 1;
            this.eot = DFA1_eot;
            this.eof = DFA1_eof;
            this.min = DFA1_min;
            this.max = DFA1_max;
            this.accept = DFA1_accept;
            this.special = DFA1_special;
            this.transition = DFA1_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( PLUS | MINUS | MULT | DIV | COMMA | EQUALS | LBRACKET | RBRACKET | LBRACE | RBRACE | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | SPACE | DIGIT | LOWER | UPPER );";
        }
    }
 

}