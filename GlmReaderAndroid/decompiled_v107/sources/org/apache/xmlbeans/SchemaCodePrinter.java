package org.apache.xmlbeans;

import java.io.IOException;
import java.io.Writer;
import org.apache.xmlbeans.impl.repackage.Repackager;

/* loaded from: classes.dex */
public interface SchemaCodePrinter {
    void printHolder(Writer writer, SchemaTypeSystem schemaTypeSystem, XmlOptions xmlOptions, Repackager repackager) throws IOException;

    @Deprecated
    default void printTypeImpl(Writer writer, SchemaType sType) throws IOException {
        printTypeImpl(writer, sType, null);
    }

    default void printTypeImpl(Writer writer, SchemaType sType, XmlOptions opt) throws IOException {
        printTypeImpl(writer, sType);
    }

    @Deprecated
    default void printType(Writer writer, SchemaType sType) throws IOException {
        printType(writer, sType, null);
    }

    default void printType(Writer writer, SchemaType sType, XmlOptions opt) throws IOException {
        printType(writer, sType);
    }
}
