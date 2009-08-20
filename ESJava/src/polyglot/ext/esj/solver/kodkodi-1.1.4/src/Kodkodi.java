
/*
    Title:      Kodkodi.java
    Author:     Jasmin Blanchette, TU Muenchen
    License:    See COPYRIGHT for details.
*/

/*
     -server functionality by: Hesam Samimi at http://www.cs.ucla.edu/~hesam/ 

     * The socket programming sections are extracted from Sun's examples.
     
 * Copyright (c) 1995 - 2008 Sun Microsystems, Inc.  All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Sun Microsystems nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/ 


package polyglot.ext.esj.solver.Kodkodi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;
import polyglot.ext.esj.solver.Kodkodi.KodkodiLexer;
import polyglot.ext.esj.solver.Kodkodi.KodkodiParser;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.Lexer;
import org.antlr.runtime.RecognitionException;

// Server
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.net.*;

public final class Kodkodi
{

    private static final int DEFAULT_PORT = 9128;

    private static boolean verbose;
    private static boolean exitOnSuccess;
    private static boolean cleanUpInst;
    private static int maxMsecs;
    private static int maxThreads;

    // server
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private static boolean server;
    private static int port;
    private static PrintWriter out;
    private static BufferedReader in;

    private static final class ExitTask extends TimerTask {
        public void run () {
            System.err.println("Ran out of time");
            System.exit(2);
        }
    }

    private static void printVersion() {
        System.out.println("Kodkodi version 1.1.4 (17 Mar 2009)");
    }

    private static void printUsageAndExit(int code) {
        printVersion();
        (code == 0 ? System.out : System.err).println(
            "Usage: kodkodi [options]\n" +
            "options:\n" +
            "  -help               Show usage and exit\n" +
            "  -version            Show version number and exit\n" +
            "  -verbose            Produce more output\n" +
            "  -exit-on-success    Exit on the first successful 'solve' " +
            "directive\n" +
            "  -clean-up-inst      Remove trivial parts of instance from " +
            "output\n" +
            "  -max-msecs <num>    Maximum running time in milliseconds\n" +
            "  -max-threads <num>  Maximum number of simultaneous threads " +
            "(default: " + Runtime.getRuntime().availableProcessors()  + ")\n" +
            "  -server             Run as TCP server\n" +
            "  -port <number>      Listen to specified port (default: " + DEFAULT_PORT + ")\n");
        System.exit(code);
    }

    private static String callSolver(InputStream in) {

	String solutionBuf = new String();
	try {

            if (maxMsecs > 0) {
                Timer timer = new Timer();
                timer.schedule(new ExitTask(), maxMsecs);
            }

            ANTLRInputStream stream = new ANTLRInputStream(in);
            KodkodiLexer lexer = new KodkodiLexer(stream);
            KodkodiParser parser = KodkodiParser.create(verbose, exitOnSuccess,
                                                        cleanUpInst, maxThreads, lexer);
            int numErrors = 0;

            try {
                solutionBuf = parser.problems();
            } catch (RecognitionException except) {
                if (parser != null)
                    parser.reportError(except);
                System.exit(1);
            }
            if (parser.getTokenStream().LA(1) != KodkodiParser.EOF) {
                System.err.println("Error: trailing tokens");
                System.exit(1);
            }

            numErrors += parser.getNumberOfSyntaxErrors();
            numErrors += lexer.getNumberOfSyntaxErrors();
            //System.exit(numErrors > 0 ? 1 : 0);
	} catch (Exception except) {
            String message = except.getMessage();
            if (message.length() == 0)
                message = except.toString();
            System.err.println("Error: " + message);
//            except.printStackTrace();
            System.exit(1);
        }
	return (solutionBuf);
    }

    private static void setupServer() throws IOException {

        serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + port + ".");
            System.exit(1);
        }

	System.out.println("Kodkod server listening for clients on port " + port + "...");

        clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.err.println("Accept failed.");
            System.exit(1);
        }

        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(
				new InputStreamReader(
				clientSocket.getInputStream()));
    }

    public static void solverLoop() throws IOException {
        String inputLine = "\n", output;
	char charIn;
	out.println("Say 'Bye;', or ask me a semi-colon terminated kodkodi problem!");
	while (true) {
	    while((charIn = (char)in.read()) != ';') {
		inputLine += charIn;
	    }
	    inputLine = inputLine.substring(1,inputLine.length());
	    inputLine += charIn;
	    if (inputLine.equals("Bye;")) {
		out.println("Bye;");
		break;
	    } else {
		System.out.println("Client: " + inputLine);
		output = callSolver(new ByteArrayInputStream(inputLine.getBytes("UTF-8")));
		out.println(output+";;");
		inputLine = "";
	    }
	}
	        
        out.close();
        in.close();
        clientSocket.close();
        serverSocket.close();
    }


    public static String ESJCallSolver(String problem)  {

	//System.out.println("\n\nClient:\n" + problem + "\n\n");
        verbose = false;
        exitOnSuccess = false;
        cleanUpInst = false;
        maxMsecs = 0;
        maxThreads = Runtime.getRuntime().availableProcessors();
        server = false;
        port = DEFAULT_PORT;



	try {
	    return callSolver(new ByteArrayInputStream(problem.getBytes("UTF-8")));
	} catch (IOException e) {
	    return null; }
    }

    public static void main(String[] args) {

        verbose = false;
        exitOnSuccess = false;
        cleanUpInst = false;
        maxMsecs = 0;
        maxThreads = Runtime.getRuntime().availableProcessors();

        server = false;
        port = DEFAULT_PORT;

        try {
            int i = 0;
            while (i < args.length) {
                if (args[i].equals("-help")) {
                    printUsageAndExit(0);
                } else if (args[i].equals("-version")) {
                    printVersion();
                    System.exit(0);
                } else if (args[i].equals("-verbose")) {
                    verbose = true;
                } else if (args[i].equals("-exit-on-success")) {
                    exitOnSuccess = true;
                } else if (args[i].equals("-clean-up-inst")) {
                    cleanUpInst = true;
                } else if (args[i].equals("-max-msecs")) {
                    try {
                        ++i;
                        maxMsecs = Integer.parseInt(args[i]);
                    } catch (IndexOutOfBoundsException except) {
                        printUsageAndExit(1);
                    } catch (NumberFormatException except) {
                        printUsageAndExit(1);
                    }
                } else if (args[i].equals("-max-threads")) {
                    try {
                        ++i;
                        maxThreads = Integer.parseInt(args[i]);
                    } catch (IndexOutOfBoundsException except) {
                        printUsageAndExit(1);
                    } catch (NumberFormatException except) {
                        printUsageAndExit(1);
                    }

                } else if (args[i].equals("-server")) {
                    server = true;
                } else if (args[i].equals("-port")) {
                    try {
                        ++i;
                        port = Integer.parseInt(args[i]);
                    } catch (IndexOutOfBoundsException except) {
                        printUsageAndExit(1);
                    } catch (NumberFormatException except) {
                        printUsageAndExit(1);
                    }
                } else {
                    printUsageAndExit(1);
                }
                ++i;
            }
           
	    
	    InputStream in;

            if (server) {
		setupServer();
		solverLoop();
            } else {
		in = System.in;
		callSolver(in);
	    }	   
	    
        } catch (Exception except) {
            String message = except.getMessage();
            if (message.length() == 0)
                message = except.toString();
            System.err.println("Error: " + message);
//            except.printStackTrace();
            System.exit(1);
        }

    }

    

}
