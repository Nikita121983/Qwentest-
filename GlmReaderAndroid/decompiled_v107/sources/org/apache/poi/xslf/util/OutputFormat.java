package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import org.apache.poi.util.Internal;

@Internal
/* loaded from: classes10.dex */
interface OutputFormat extends Closeable {
    Graphics2D addSlide(double d, double d2) throws IOException;

    void writeSlide(MFProxy mFProxy, File file) throws IOException;

    default void writeDocument(MFProxy proxy, File outFile) throws IOException {
    }
}
