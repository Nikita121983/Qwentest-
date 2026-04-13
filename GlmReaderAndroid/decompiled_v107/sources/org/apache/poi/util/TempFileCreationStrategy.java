package org.apache.poi.util;

import java.io.File;
import java.io.IOException;

/* loaded from: classes10.dex */
public interface TempFileCreationStrategy {
    File createTempDirectory(String str) throws IOException;

    File createTempFile(String str, String str2) throws IOException;
}
