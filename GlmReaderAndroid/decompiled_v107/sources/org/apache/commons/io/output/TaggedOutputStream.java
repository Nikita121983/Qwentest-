package org.apache.commons.io.output;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

/* loaded from: classes9.dex */
public class TaggedOutputStream extends ProxyOutputStream {
    private final Serializable tag;

    public TaggedOutputStream(OutputStream proxy) {
        super(proxy);
        this.tag = UUID.randomUUID();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.io.output.ProxyOutputStream
    public void handleIOException(IOException e) throws IOException {
        throw new TaggedIOException(e, this.tag);
    }

    public boolean isCauseOf(Exception exception) {
        return TaggedIOException.isTaggedWith(exception, this.tag);
    }

    public void throwIfCauseOf(Exception exception) throws IOException {
        TaggedIOException.throwCauseIfTaggedWith(exception, this.tag);
    }
}
