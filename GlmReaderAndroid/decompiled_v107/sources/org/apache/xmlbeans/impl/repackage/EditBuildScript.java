package org.apache.xmlbeans.impl.repackage;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;

/* loaded from: classes11.dex */
public class EditBuildScript {
    public static void main(String[] args) throws Exception {
        if (args.length != 3) {
            throw new IllegalArgumentException("Wrong number of arguments");
        }
        args[0] = args[0].replace('/', File.separatorChar);
        File buildFile = new File(args[0]);
        StringBuffer sb = readFile(buildFile);
        String tokenStr = "<property name=\"" + args[1] + "\" value=\"";
        int i = sb.indexOf(tokenStr);
        if (i < 0) {
            throw new IllegalArgumentException("Can't find token: " + tokenStr);
        }
        int j = tokenStr.length() + i;
        while (sb.charAt(j) != '\"') {
            j++;
        }
        sb.replace(tokenStr.length() + i, j, args[2]);
        writeFile(buildFile, sb);
    }

    static StringBuffer readFile(File f) throws IOException {
        Reader r = Files.newBufferedReader(f.toPath(), StandardCharsets.ISO_8859_1);
        try {
            StringWriter w = new StringWriter();
            try {
                copy(r, w);
                StringBuffer buffer = w.getBuffer();
                w.close();
                if (r != null) {
                    r.close();
                }
                return buffer;
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (r != null) {
                    try {
                        r.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    static void writeFile(File f, StringBuffer chars) throws IOException {
        Writer w = Files.newBufferedWriter(f.toPath(), StandardCharsets.ISO_8859_1, new OpenOption[0]);
        try {
            Reader r = new StringReader(chars.toString());
            try {
                copy(r, w);
                r.close();
                if (w != null) {
                    w.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (w != null) {
                    try {
                        w.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    static void copy(Reader r, Writer w) throws IOException {
        char[] buffer = new char[16384];
        while (true) {
            int n = r.read(buffer, 0, buffer.length);
            if (n >= 0) {
                w.write(buffer, 0, n);
            } else {
                return;
            }
        }
    }
}
