package org.apache.xmlbeans.impl.config;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.resolution.TypeSolver;
import com.github.javaparser.symbolsolver.JavaSymbolSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ClassLoaderTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.CombinedTypeSolver;
import com.github.javaparser.symbolsolver.resolution.typesolvers.ReflectionTypeSolver;
import com.github.javaparser.utils.ProjectRoot;
import com.github.javaparser.utils.SourceRoot;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes11.dex */
public class Parser {
    private final File[] classpath;
    private final CombinedTypeSolver combinedTypeSolver = new CombinedTypeSolver(new TypeSolver[0]);
    private final File[] javaFiles;
    private final ParserConfiguration pc;
    private final ProjectRoot projectRoot;

    public Parser(File[] javaFiles, File[] classpath) {
        this.javaFiles = javaFiles != null ? (File[]) javaFiles.clone() : new File[0];
        this.classpath = classpath != null ? (File[]) classpath.clone() : new File[0];
        this.pc = new ParserConfiguration();
        this.pc.setLanguageLevel(ParserConfiguration.LanguageLevel.BLEEDING_EDGE);
        URL[] urls = (URL[]) Stream.of((Object[]) this.classpath).map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda14
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                URL fileToURL;
                fileToURL = Parser.fileToURL((File) obj);
                return fileToURL;
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda1
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((URL) obj);
                return nonNull;
            }
        }).toArray(new IntFunction() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return Parser.lambda$new$0(i);
            }
        });
        this.combinedTypeSolver.add(new ClassLoaderTypeSolver(new URLClassLoader(urls, getClass().getClassLoader())));
        this.combinedTypeSolver.add(new ReflectionTypeSolver());
        JavaSymbolSolver symbolSolver = new JavaSymbolSolver(this.combinedTypeSolver);
        this.pc.setSymbolResolver(symbolSolver);
        if (this.javaFiles.length > 0) {
            final ChildSolverCollectionStrategy solver = new ChildSolverCollectionStrategy(this.pc, this.combinedTypeSolver);
            Stream distinct = Stream.of((Object[]) this.javaFiles).map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda3
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return Parser.lambda$new$1((File) obj);
                }
            }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda4
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    Path path;
                    path = ((File) obj).toPath();
                    return path;
                }
            }).distinct();
            solver.getClass();
            distinct.forEach(new Consumer() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda5
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ChildSolverCollectionStrategy.this.collect((Path) obj);
                }
            });
            this.projectRoot = solver.collectAll();
            return;
        }
        this.projectRoot = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ URL[] lambda$new$0(int x$0) {
        return new URL[x$0];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ File lambda$new$1(File f) {
        return f.isDirectory() ? f : f.getParentFile();
    }

    public ClassOrInterfaceDeclaration loadSource(final String className) {
        final String fileName = className.replace('.', '/') + ".java";
        if (this.projectRoot == null) {
            return null;
        }
        Stream flatMap = this.projectRoot.getSourceRoots().stream().map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Parser.this.m2600lambda$loadSource$2$orgapachexmlbeansimplconfigParser(fileName, (SourceRoot) obj);
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean nonNull;
                nonNull = Objects.nonNull((ParseResult) obj);
                return nonNull;
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda7
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isSuccessful;
                isSuccessful = ((ParseResult) obj).isSuccessful();
                return isSuccessful;
            }
        }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Optional result;
                result = ((ParseResult) obj).getResult();
                return result;
            }
        }).map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda9
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object obj2;
                obj2 = ((Optional) obj).get();
                return (CompilationUnit) obj2;
            }
        }).flatMap(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Stream stream;
                stream = ((CompilationUnit) obj).getTypes().stream();
                return stream;
            }
        });
        final Class<ClassOrInterfaceDeclaration> cls = ClassOrInterfaceDeclaration.class;
        ClassOrInterfaceDeclaration.class.getClass();
        Stream filter = flatMap.filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean isInstance;
                isInstance = cls.isInstance((TypeDeclaration) obj);
                return isInstance;
            }
        }).filter(new Predicate() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda12
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean equals;
                equals = className.equals(((TypeDeclaration) obj).getFullyQualifiedName().orElse(null));
                return equals;
            }
        });
        final Class<ClassOrInterfaceDeclaration> cls2 = ClassOrInterfaceDeclaration.class;
        ClassOrInterfaceDeclaration.class.getClass();
        return (ClassOrInterfaceDeclaration) filter.map(new Function() { // from class: org.apache.xmlbeans.impl.config.Parser$$ExternalSyntheticLambda13
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Object cast;
                cast = cls2.cast((TypeDeclaration) obj);
                return (ClassOrInterfaceDeclaration) cast;
            }
        }).findFirst().orElse(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static URL fileToURL(File file) {
        try {
            return file.toURI().toURL();
        } catch (MalformedURLException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: parseOrNull, reason: merged with bridge method [inline-methods] */
    public ParseResult<CompilationUnit> m2600lambda$loadSource$2$orgapachexmlbeansimplconfigParser(SourceRoot sr, String fileName) {
        try {
            return sr.tryToParse("", fileName, this.pc);
        } catch (IOException e) {
            return null;
        }
    }
}
