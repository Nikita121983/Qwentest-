package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JarTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.JavaParserTypeSolver;
import com.github.javaparser.utils.CollectionStrategy;
import com.github.javaparser.utils.ProjectRoot;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;
import org.apache.commons.compress.archivers.zip.ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda0;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.impl.config.ChildSolverCollectionStrategy;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class ChildSolverCollectionStrategy implements CollectionStrategy {
    private static final Logger LOG = XmlBeansLogManager.getLogger(ChildSolverCollectionStrategy.class);
    private final CombinedTypeSolver combinedTypeSolver;
    private final ParserConfiguration config;
    private final PathMatcher javaMatcher = getPathMatcher("glob:**.java");
    private final PathMatcher jarMatcher = getPathMatcher("glob:**.jar");
    private final List<Path> roots = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChildSolverCollectionStrategy(ParserConfiguration config, CombinedTypeSolver combinedTypeSolver) {
        this.config = config;
        this.combinedTypeSolver = combinedTypeSolver;
    }

    public ParserConfiguration getParserConfiguration() {
        return this.config;
    }

    public ProjectRoot collect(Path path) {
        try {
            Files.walkFileTree(path, new FileVisitor());
        } catch (IOException e) {
            LOG.atWarn().withThrowable(e).log("Unable to walk {}", path);
        }
        if (this.roots.isEmpty()) {
            return null;
        }
        return new ProjectRoot(this.roots.get(this.roots.size() - 1), this.config);
    }

    public ProjectRoot collectAll() {
        Path root = null;
        for (Path p : this.roots) {
            if (root == null) {
                root = p;
            } else {
                root = commonRoot(root, p);
                if (root == null) {
                    break;
                }
            }
        }
        if (root == null) {
            throw new IllegalStateException("Unable to construct a common project root - giving up.");
        }
        final ProjectRoot pr = new ProjectRoot(root, this.config);
        List<Path> list = this.roots;
        pr.getClass();
        list.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.config.ChildSolverCollectionStrategy$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                pr.addSourceRoot((Path) obj);
            }
        });
        return pr;
    }

    private static Path commonRoot(Path path1, Path path2) {
        List<Path> l1 = new ArrayList<>();
        Iterator<Path> it = path1.toAbsolutePath().iterator();
        l1.getClass();
        it.forEachRemaining(new ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda0(l1));
        List<Path> l2 = new ArrayList<>();
        Iterator<Path> it2 = path2.toAbsolutePath().iterator();
        l2.getClass();
        it2.forEachRemaining(new ZipSplitReadOnlySeekableByteChannel$$ExternalSyntheticLambda0(l2));
        l1.retainAll(l2);
        if (l1.isEmpty()) {
            return null;
        }
        return l1.get(l1.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CombinedTypeSolver getSolver() {
        return this.combinedTypeSolver;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public class FileVisitor extends SimpleFileVisitor<Path> {
        private FileVisitor() {
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            if (ChildSolverCollectionStrategy.this.javaMatcher.matches(file)) {
                Stream map = ChildSolverCollectionStrategy.this.roots.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.ChildSolverCollectionStrategy$FileVisitor$$ExternalSyntheticLambda0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return ((Path) obj).toAbsolutePath();
                    }
                });
                final Path absolutePath = file.toAbsolutePath();
                absolutePath.getClass();
                if (map.noneMatch(new Predicate() { // from class: org.apache.xmlbeans.impl.config.ChildSolverCollectionStrategy$FileVisitor$$ExternalSyntheticLambda1
                    @Override // java.util.function.Predicate
                    public final boolean test(Object obj) {
                        return absolutePath.startsWith((Path) obj);
                    }
                })) {
                    ChildSolverCollectionStrategy.this.getRoot(file).ifPresent(new Consumer() { // from class: org.apache.xmlbeans.impl.config.ChildSolverCollectionStrategy$FileVisitor$$ExternalSyntheticLambda2
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            ChildSolverCollectionStrategy.FileVisitor.this.m2590x2a8f9c77((Path) obj);
                        }
                    });
                }
            } else if (ChildSolverCollectionStrategy.this.jarMatcher.matches(file)) {
                ChildSolverCollectionStrategy.this.getSolver().add(new JarTypeSolver(file));
            }
            return FileVisitResult.CONTINUE;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$visitFile$0$org-apache-xmlbeans-impl-config-ChildSolverCollectionStrategy$FileVisitor, reason: not valid java name */
        public /* synthetic */ void m2590x2a8f9c77(Path r) {
            ChildSolverCollectionStrategy.this.getSolver().add(new JavaParserTypeSolver(r, ChildSolverCollectionStrategy.this.getParserConfiguration()));
            ChildSolverCollectionStrategy.this.roots.add(r);
        }

        @Override // java.nio.file.SimpleFileVisitor, java.nio.file.FileVisitor
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            return Files.isHidden(dir) ? FileVisitResult.SKIP_SUBTREE : FileVisitResult.CONTINUE;
        }
    }
}
