package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.function.IOConsumer;

/* loaded from: classes9.dex */
public class FilterCollectionWriter extends Writer {
    protected final Collection<Writer> EMPTY_WRITERS = Collections.emptyList();
    protected final Collection<Writer> writers;

    /* JADX INFO: Access modifiers changed from: protected */
    public FilterCollectionWriter(Collection<Writer> writers) {
        this.writers = writers == null ? this.EMPTY_WRITERS : writers;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public FilterCollectionWriter(Writer... writers) {
        this.writers = writers == null ? this.EMPTY_WRITERS : Arrays.asList(writers);
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(final char c) throws IOException {
        return forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda9
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).append(c);
            }
        });
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(final CharSequence csq) throws IOException {
        return forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda3
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).append(csq);
            }
        });
    }

    @Override // java.io.Writer, java.lang.Appendable
    public Writer append(final CharSequence csq, final int start, final int end) throws IOException {
        return forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).append(csq, start, end);
            }
        });
    }

    @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).close();
            }
        });
    }

    @Override // java.io.Writer, java.io.Flushable
    public void flush() throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda6
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).flush();
            }
        });
    }

    private FilterCollectionWriter forAllWriters(IOConsumer<Writer> action) throws IOExceptionList {
        IOConsumer.forAll(action, writers());
        return this;
    }

    @Override // java.io.Writer
    public void write(final char[] cbuf) throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda4
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).write(cbuf);
            }
        });
    }

    @Override // java.io.Writer
    public void write(final char[] cbuf, final int off, final int len) throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda5
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).write(cbuf, off, len);
            }
        });
    }

    @Override // java.io.Writer
    public void write(final int c) throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda8
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).write(c);
            }
        });
    }

    @Override // java.io.Writer
    public void write(final String str) throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda2
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).write(str);
            }
        });
    }

    @Override // java.io.Writer
    public void write(final String str, final int off, final int len) throws IOException {
        forAllWriters(new IOConsumer() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda7
            @Override // org.apache.commons.io.function.IOConsumer
            public final void accept(Object obj) {
                ((Writer) obj).write(str, off, len);
            }
        });
    }

    private Stream<Writer> writers() {
        return this.writers.stream().filter(new Predicate() { // from class: org.apache.commons.io.output.FilterCollectionWriter$$ExternalSyntheticLambda10
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((Writer) obj);
                return nonNull;
            }
        });
    }
}
