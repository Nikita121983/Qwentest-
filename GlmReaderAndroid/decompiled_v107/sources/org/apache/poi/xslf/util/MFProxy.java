package org.apache.poi.xslf.util;

import java.awt.Graphics2D;
import java.awt.geom.Dimension2D;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Set;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.sl.draw.EmbeddedExtractor;
import org.apache.poi.util.Internal;

/* JADX INFO: Access modifiers changed from: package-private */
@Internal
/* loaded from: classes10.dex */
public abstract class MFProxy implements Closeable {
    boolean ignoreParse;
    boolean quiet;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void draw(Graphics2D graphics2D);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Iterable<EmbeddedExtractor.EmbeddedPart> getEmbeddings(int i);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract GenericRecord getRoot();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract Dimension2D getSize();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String getTitle();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void parse(File file) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void parse(InputStream inputStream) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void setDefaultCharset(Charset charset);

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setIgnoreParse(boolean ignoreParse) {
        this.ignoreParse = ignoreParse;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setQuiet(boolean quiet) {
        this.quiet = quiet;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setSlideNo(int slideNo) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getSlideCount() {
        return 1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Set<Integer> slideIndexes(String range) {
        return Collections.singleton(1);
    }
}
