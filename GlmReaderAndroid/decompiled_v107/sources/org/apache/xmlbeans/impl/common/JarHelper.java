package org.apache.xmlbeans.impl.common;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;

/* loaded from: classes11.dex */
public class JarHelper {
    private static final int BUFFER_SIZE = 2156;
    private static final char SEP = '/';
    private final byte[] mBuffer = new byte[BUFFER_SIZE];
    private boolean mVerbose = false;
    private String mDestJarName = "";

    public void jarDir(File dirOrFile2Jar, File destJar) throws IOException {
        if (dirOrFile2Jar == null || destJar == null) {
            throw new IllegalArgumentException();
        }
        this.mDestJarName = destJar.getCanonicalPath();
        OutputStream fout = Files.newOutputStream(destJar.toPath(), new OpenOption[0]);
        try {
            JarOutputStream jout = new JarOutputStream(fout);
            try {
                jarDir(dirOrFile2Jar, jout, null);
                jout.close();
                if (fout != null) {
                    fout.close();
                }
            } finally {
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fout != null) {
                    try {
                        fout.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    public void setVerbose(boolean b) {
        this.mVerbose = b;
    }

    private void jarDir(File dirOrFile2jar, JarOutputStream jos, String path) throws IOException {
        if (this.mVerbose) {
            System.out.println("checking " + dirOrFile2jar);
        }
        if (dirOrFile2jar.isDirectory()) {
            String[] dirList = dirOrFile2jar.list();
            String subPath = path == null ? "" : path + dirOrFile2jar.getName() + '/';
            if (path != null) {
                JarEntry je = new JarEntry(subPath);
                je.setTime(dirOrFile2jar.lastModified());
                jos.putNextEntry(je);
                jos.flush();
                jos.closeEntry();
            }
            if (dirList != null) {
                for (String s : dirList) {
                    File f = new File(dirOrFile2jar, s);
                    jarDir(f, jos, subPath);
                }
                return;
            }
            return;
        }
        if (dirOrFile2jar.getCanonicalPath().equals(this.mDestJarName)) {
            if (this.mVerbose) {
                System.out.println("skipping " + dirOrFile2jar.getPath());
                return;
            }
            return;
        }
        if (this.mVerbose) {
            System.out.println("adding " + dirOrFile2jar.getPath());
        }
        InputStream fis = Files.newInputStream(dirOrFile2jar.toPath(), new OpenOption[0]);
        try {
            JarEntry entry = new JarEntry(path + dirOrFile2jar.getName());
            entry.setTime(dirOrFile2jar.lastModified());
            jos.putNextEntry(entry);
            while (true) {
                int mByteCount = fis.read(this.mBuffer);
                if (mByteCount == -1) {
                    break;
                }
                jos.write(this.mBuffer, 0, mByteCount);
                if (this.mVerbose) {
                    System.out.println("wrote " + mByteCount + " bytes");
                }
            }
            jos.flush();
            jos.closeEntry();
            if (fis != null) {
                fis.close();
            }
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }
}
