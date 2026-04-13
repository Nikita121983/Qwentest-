package org.apache.commons.lang3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Function;
import java.util.function.Predicate;

/* loaded from: classes9.dex */
public class RuntimeEnvironment {
    private static boolean fileExists(String path) {
        return Files.exists(Paths.get(path, new String[0]), new LinkOption[0]);
    }

    public static Boolean inContainer() {
        return Boolean.valueOf(inContainer(""));
    }

    static boolean inContainer(String dirPrefix) {
        String value = readFile(dirPrefix + "/proc/1/environ", "container");
        if (value != null) {
            return true ^ value.isEmpty();
        }
        return fileExists(new StringBuilder().append(dirPrefix).append("/.dockerenv").toString()) || fileExists(new StringBuilder().append(dirPrefix).append("/run/.containerenv").toString());
    }

    private static String readFile(String envVarFile, String key) {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(envVarFile, new String[0]));
            String content = new String(bytes, Charset.defaultCharset());
            String[] lines = content.split(String.valueOf((char) 0));
            final String prefix = key + "=";
            return (String) Arrays.stream(lines).filter(new Predicate() { // from class: org.apache.commons.lang3.RuntimeEnvironment$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean startsWith;
                    startsWith = ((String) obj).startsWith(prefix);
                    return startsWith;
                }
            }).map(new Function() { // from class: org.apache.commons.lang3.RuntimeEnvironment$$ExternalSyntheticLambda1
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    String[] split;
                    split = ((String) obj).split("=", 2);
                    return split;
                }
            }).map(new Function() { // from class: org.apache.commons.lang3.RuntimeEnvironment$$ExternalSyntheticLambda2
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return RuntimeEnvironment.lambda$readFile$2((String[]) obj);
                }
            }).findFirst().orElse(null);
        } catch (IOException e) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$readFile$2(String[] keyValue) {
        return keyValue[1];
    }

    @Deprecated
    public RuntimeEnvironment() {
    }
}
