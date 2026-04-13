package org.apache.xmlbeans;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/* loaded from: classes.dex */
public interface Filer {
    OutputStream createBinaryFile(String str) throws IOException;

    Writer createSourceFile(String str, String str2) throws IOException;

    default Writer createSourceFile(String typename) throws IOException {
        return createSourceFile(typename, null);
    }
}
