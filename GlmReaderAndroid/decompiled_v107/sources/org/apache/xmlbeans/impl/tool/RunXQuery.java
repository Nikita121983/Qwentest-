package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.util.ExceptionUtil;

/* loaded from: classes11.dex */
public class RunXQuery {
    public static void printUsage() {
        System.out.println("Run an XQuery against an XML instance");
        System.out.println("Usage:");
        System.out.println("xquery [-verbose] [-pretty] [-q <query> | -qf query.xq] [file.xml]*");
        System.out.println(" -q <query> to specify a query on the command-line");
        System.out.println(" -qf <query> to specify a file containing a query");
        System.out.println(" -pretty pretty-prints the results");
        System.out.println(" -license prints license information");
        System.out.println(" the query is run on each XML file specified");
        System.out.println();
    }

    public static void main(String[] args) throws Exception {
        int i;
        int i2;
        int i3;
        int i4;
        Set<String> flags = new HashSet<>();
        flags.add("h");
        flags.add("help");
        flags.add("usage");
        flags.add("license");
        flags.add("version");
        flags.add("verbose");
        flags.add("pretty");
        int i5 = 0;
        int i6 = 1;
        CommandLine cl = new CommandLine(args, flags, Arrays.asList("q", "qf"));
        if (cl.getOpt("h") == null && cl.getOpt("help") == null) {
            if (cl.getOpt("usage") == null) {
                String[] badopts = cl.getBadOpts();
                if (badopts.length > 0) {
                    for (String badopt : badopts) {
                        System.out.println("Unrecognized option: " + badopt);
                    }
                    printUsage();
                    System.exit(0);
                    return;
                }
                if (cl.getOpt("license") != null) {
                    CommandLine.printLicense();
                    System.exit(0);
                    return;
                }
                if (cl.getOpt("version") != null) {
                    CommandLine.printVersion();
                    System.exit(0);
                    return;
                }
                String[] args2 = cl.args();
                if (args2.length == 0) {
                    printUsage();
                    System.exit(0);
                    return;
                }
                boolean verbose = cl.getOpt("verbose") != null;
                boolean pretty = cl.getOpt("pretty") != null;
                String query = cl.getOpt("q");
                String queryfile = cl.getOpt("qf");
                if (query == null && queryfile == null) {
                    System.err.println("No query specified");
                    System.exit(0);
                    return;
                }
                if (query != null && queryfile != null) {
                    System.err.println("Specify -qf or -q, not both.");
                    System.exit(0);
                    return;
                }
                if (queryfile != null) {
                    try {
                        File queryFile = new File(queryfile);
                        InputStream is = Files.newInputStream(queryFile.toPath(), new OpenOption[0]);
                        StringBuilder sb = new StringBuilder();
                        InputStreamReader r = new InputStreamReader(is, StandardCharsets.ISO_8859_1);
                        while (true) {
                            try {
                                int ch = r.read();
                                if (ch < 0) {
                                    break;
                                } else {
                                    sb.append((char) ch);
                                }
                            } finally {
                            }
                        }
                        r.close();
                        is.close();
                        query = sb.toString();
                    } catch (Throwable e) {
                        if (ExceptionUtil.isFatal(e)) {
                            ExceptionUtil.rethrow(e);
                        }
                        System.err.println("Cannot read query file: " + e.getMessage());
                        System.exit(1);
                        return;
                    }
                }
                if (verbose) {
                    System.out.println("Compile Query:");
                    System.out.println(query);
                    System.out.println();
                }
                try {
                    String query2 = XmlBeans.compileQuery(query);
                    File[] files = cl.getFiles();
                    int length = files.length;
                    int i7 = 0;
                    while (i7 < length) {
                        File file = files[i7];
                        if (!verbose) {
                            i3 = i5;
                        } else {
                            try {
                                InputStream is2 = Files.newInputStream(file.toPath(), new OpenOption[i5]);
                                while (true) {
                                    try {
                                        int ch2 = is2.read();
                                        if (ch2 < 0) {
                                            break;
                                        }
                                        int i8 = i5;
                                        System.out.write(ch2);
                                        i5 = i8;
                                    } finally {
                                    }
                                }
                                if (is2 != null) {
                                    is2.close();
                                }
                                System.out.println();
                                i3 = i5;
                            } catch (Throwable th) {
                                e = th;
                                i2 = i6;
                                System.err.println("Error parsing instance: " + e.getMessage());
                                System.exit(i2);
                                return;
                            }
                        }
                        try {
                            XmlObject x = XmlObject.Factory.parse(file);
                            if (verbose) {
                                System.out.println("Executing Query...");
                                System.err.println();
                            }
                            try {
                                XmlObject[] result = x.execQuery(query2);
                                if (!verbose) {
                                    i4 = i6;
                                } else {
                                    i4 = i6;
                                    System.out.println("Query Result:");
                                }
                                XmlOptions opts = new XmlOptions();
                                opts.setSaveOuter();
                                if (pretty) {
                                    opts.setSavePrettyPrint();
                                }
                                int length2 = result.length;
                                int i9 = length;
                                int i10 = i3;
                                while (i10 < length2) {
                                    int i11 = i10;
                                    XmlObject xmlObject = result[i11];
                                    xmlObject.save(System.out, opts);
                                    System.out.println();
                                    i10 = i11 + 1;
                                    flags = flags;
                                }
                                i7++;
                                length = i9;
                                i5 = i3;
                                i6 = i4;
                            } catch (Throwable e2) {
                                System.err.println("Error executing query: " + e2.getMessage());
                                System.exit(i6);
                                return;
                            }
                        } catch (Throwable th2) {
                            e = th2;
                            i2 = i6;
                            System.err.println("Error parsing instance: " + e.getMessage());
                            System.exit(i2);
                            return;
                        }
                    }
                    return;
                } catch (Exception e3) {
                    System.err.println("Error compiling query: " + e3.getMessage());
                    System.exit(1);
                    return;
                }
            }
            i = 0;
        } else {
            i = 0;
        }
        printUsage();
        System.exit(i);
    }
}
