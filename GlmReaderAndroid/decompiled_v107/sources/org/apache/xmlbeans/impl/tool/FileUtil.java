package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Collection;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.io.FileUtils$$ExternalSyntheticLambda19;

/* loaded from: classes11.dex */
final class FileUtil {
    private FileUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Collection<File> find(File base, final Pattern pattern, boolean searchSubdirectories) {
        try {
            Stream<Path> pathStream = Files.find(base.toPath(), searchSubdirectories ? Integer.MAX_VALUE : 1, new BiPredicate() { // from class: org.apache.xmlbeans.impl.tool.FileUtil$$ExternalSyntheticLambda0
                @Override // java.util.function.BiPredicate
                public final boolean test(Object obj, Object obj2) {
                    return FileUtil.lambda$find$0(pattern, (Path) obj, (BasicFileAttributes) obj2);
                }
            }, new FileVisitOption[0]);
            try {
                Collection<File> collection = (Collection) pathStream.map(new FileUtils$$ExternalSyntheticLambda19()).collect(Collectors.toList());
                if (pathStream != null) {
                    pathStream.close();
                }
                return collection;
            } finally {
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$find$0(Pattern pattern, Path path, BasicFileAttributes atts) {
        String name = path.getFileName().toString();
        return !name.endsWith(".xsdconfig") && pattern.matcher(name).matches();
    }
}
