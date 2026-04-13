package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

/* loaded from: classes9.dex */
public class TaggedWriter extends ProxyWriter {
    private final Serializable tag;

    public TaggedWriter(Writer proxy) {
        super(proxy);
        this.tag = UUID.randomUUID();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.commons.io.output.ProxyWriter
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
