package org.apache.commons.io.file;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.util.Objects;
import org.apache.commons.io.build.AbstractSupplier;
import org.apache.commons.io.function.IOBiFunction;

/* loaded from: classes9.dex */
public abstract class SimplePathVisitor extends SimpleFileVisitor<Path> implements PathVisitor {
    private final IOBiFunction<Path, IOException, FileVisitResult> visitFileFailedFunction;

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes9.dex */
    public static abstract class AbstractBuilder<T, B extends AbstractSupplier<T, B>> extends AbstractSupplier<T, B> {
        private IOBiFunction<Path, IOException, FileVisitResult> visitFileFailedFunction;

        IOBiFunction<Path, IOException, FileVisitResult> getVisitFileFailedFunction() {
            return this.visitFileFailedFunction;
        }

        public B setVisitFileFailedFunction(IOBiFunction<Path, IOException, FileVisitResult> visitFileFailedFunction) {
            this.visitFileFailedFunction = visitFileFailedFunction;
            return asThis();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimplePathVisitor() {
        this.visitFileFailedFunction = new IOBiFunction() { // from class: org.apache.commons.io.file.SimplePathVisitor$$ExternalSyntheticLambda0
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                return SimplePathVisitor.this.m2113lambda$new$0$orgapachecommonsiofileSimplePathVisitor((Path) obj, (IOException) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$0$org-apache-commons-io-file-SimplePathVisitor, reason: not valid java name */
    public /* synthetic */ FileVisitResult m2113lambda$new$0$orgapachecommonsiofileSimplePathVisitor(Object x$0, IOException x$1) throws IOException {
        return super.visitFileFailed((SimplePathVisitor) x$0, x$1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public SimplePathVisitor(AbstractBuilder<?, ?> builder) {
        this.visitFileFailedFunction = ((AbstractBuilder) builder).visitFileFailedFunction != null ? ((AbstractBuilder) builder).visitFileFailedFunction : new IOBiFunction() { // from class: org.apache.commons.io.file.SimplePathVisitor$$ExternalSyntheticLambda1
            @Override // org.apache.commons.io.function.IOBiFunction
            public final Object apply(Object obj, Object obj2) {
                return SimplePathVisitor.this.m2114lambda$new$1$orgapachecommonsiofileSimplePathVisitor((Path) obj, (IOException) obj2);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$new$1$org-apache-commons-io-file-SimplePathVisitor, reason: not valid java name */
    public /* synthetic */ FileVisitResult m2114lambda$new$1$orgapachecommonsiofileSimplePathVisitor(Object x$0, IOException x$1) throws IOException {
        return super.visitFileFailed((SimplePathVisitor) x$0, x$1);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public SimplePathVisitor(IOBiFunction<Path, IOException, FileVisitResult> visitFileFailedFunction) {
        this.visitFileFailedFunction = (IOBiFunction) Objects.requireNonNull(visitFileFailedFunction, "visitFileFailedFunction");
    }

    @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
    public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
        return this.visitFileFailedFunction.apply(file, exc);
    }
}
