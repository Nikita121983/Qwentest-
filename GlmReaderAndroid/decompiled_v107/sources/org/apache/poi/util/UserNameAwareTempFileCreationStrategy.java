package org.apache.poi.util;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/* loaded from: classes10.dex */
public class UserNameAwareTempFileCreationStrategy extends DefaultTempFileCreationStrategy {
    private static final String JAVA_PROP_USER_NAME = "user.name";

    @Override // org.apache.poi.util.DefaultTempFileCreationStrategy
    protected Path getPOIFilesDirectoryPath() throws IOException {
        String tmpDir = getJavaIoTmpDir();
        String poifilesDir = DefaultTempFileCreationStrategy.POIFILES;
        String username = System.getProperty("user.name");
        if (username != null && !username.isEmpty()) {
            poifilesDir = DefaultTempFileCreationStrategy.POIFILES + "_" + username;
        }
        return Paths.get(tmpDir, poifilesDir);
    }
}
