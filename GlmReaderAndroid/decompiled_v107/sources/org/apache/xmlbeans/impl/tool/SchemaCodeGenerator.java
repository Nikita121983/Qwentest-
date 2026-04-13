package org.apache.xmlbeans.impl.tool;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileAttribute;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;
import org.apache.xmlbeans.Filer;
import org.apache.xmlbeans.SchemaTypeSystem;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.apache.xmlbeans.impl.repackage.Repackager;
import org.apache.xmlbeans.impl.util.FilerImpl;

/* loaded from: classes11.dex */
public class SchemaCodeGenerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Set<File> deleteFileQueue = new HashSet();
    private static int triesRemaining = 0;

    static /* synthetic */ boolean access$000() {
        return tryNowThatItsLater();
    }

    public static void saveTypeSystem(SchemaTypeSystem system, File classesDir, File sourceFile, Repackager repackager, XmlOptions options) throws IOException {
        Filer filer = new FilerImpl(classesDir, null, repackager, false, false);
        system.save(filer);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void deleteObsoleteFiles(File rootDir, File srcDir, Set seenFiles) {
        if (!rootDir.isDirectory() || !srcDir.isDirectory()) {
            throw new IllegalArgumentException();
        }
        String absolutePath = srcDir.getAbsolutePath();
        if (absolutePath.length() <= 5) {
            return;
        }
        if (absolutePath.startsWith("/home/") && (absolutePath.indexOf(PackagingURIHelper.FORWARD_SLASH_STRING, 6) >= absolutePath.length() - 1 || absolutePath.indexOf(PackagingURIHelper.FORWARD_SLASH_STRING, 6) < 0)) {
            return;
        }
        File[] files = srcDir.listFiles();
        for (int i = 0; i < files.length; i++) {
            if (files[i].isDirectory()) {
                deleteObsoleteFiles(rootDir, files[i], seenFiles);
            } else if (!seenFiles.contains(files[i])) {
                deleteXmlBeansFile(files[i]);
                deleteDirRecursively(rootDir, files[i].getParentFile());
            }
        }
    }

    private static void deleteXmlBeansFile(File file) {
        if (file.getName().endsWith(".java")) {
            file.delete();
        }
    }

    private static void deleteDirRecursively(File root, File dir) {
        String[] list = dir.list();
        while (list != null && list.length == 0 && !dir.equals(root)) {
            dir.delete();
            dir = dir.getParentFile();
            list = dir.list();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static File createTempDir() throws IOException {
        String num;
        try {
            File tmpDirFile = IOUtil.getTempDir().toFile();
            tmpDirFile.mkdirs();
        } catch (Exception e) {
            e.printStackTrace();
        }
        File tmpFile = Files.createTempFile(IOUtil.getTempDir(), "xbean", ".tmp", new FileAttribute[0]).toFile();
        String path = tmpFile.getAbsolutePath();
        if (!path.endsWith(".tmp")) {
            throw new IOException("Error: createTempFile did not create a file ending with .tmp");
        }
        String path2 = path.substring(0, path.length() - 4);
        File tmpSrcDir = null;
        int count = 0;
        while (true) {
            if (count >= 100) {
                break;
            }
            StringBuilder append = new StringBuilder().append(path2).append(".d");
            if (count == 0) {
                num = "";
            } else {
                num = Integer.toString(count);
                count++;
            }
            String name = append.append(num).toString();
            tmpSrcDir = new File(name);
            if (tmpSrcDir.exists()) {
                count++;
            } else {
                boolean created = tmpSrcDir.mkdirs();
                if (!created) {
                    throw new AssertionError("Could not create " + tmpSrcDir.getAbsolutePath());
                }
            }
        }
        tmpFile.deleteOnExit();
        return tmpSrcDir;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void tryHardToDelete(File dir) {
        tryToDelete(dir);
        if (dir.exists()) {
            tryToDeleteLater(dir);
        }
    }

    private static void tryToDelete(File dir) {
        String[] list;
        if (dir.exists()) {
            if (dir.isDirectory() && (list = dir.list()) != null) {
                for (String str : list) {
                    tryToDelete(new File(dir, str));
                }
            }
            if (!dir.delete()) {
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x0058  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0059  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean tryNowThatItsLater() {
        /*
            java.util.Set<java.io.File> r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.deleteFileQueue
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L5f
            java.util.Set<java.io.File> r2 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.deleteFileQueue     // Catch: java.lang.Throwable -> L5f
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L5f
            java.util.Set<java.io.File> r2 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.deleteFileQueue     // Catch: java.lang.Throwable -> L5f
            r2.clear()     // Catch: java.lang.Throwable -> L5f
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = r0
            java.util.Iterator r0 = r1.iterator()
        L1a:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L33
            java.lang.Object r3 = r0.next()
            java.io.File r3 = (java.io.File) r3
            tryToDelete(r3)
            boolean r4 = r3.exists()
            if (r4 == 0) goto L32
            r2.add(r3)
        L32:
            goto L1a
        L33:
            java.util.Set<java.io.File> r3 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.deleteFileQueue
            monitor-enter(r3)
            int r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining     // Catch: java.lang.Throwable -> L5c
            r4 = 1
            if (r0 <= 0) goto L40
            int r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining     // Catch: java.lang.Throwable -> L5c
            int r0 = r0 - r4
            org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining = r0     // Catch: java.lang.Throwable -> L5c
        L40:
            int r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining     // Catch: java.lang.Throwable -> L5c
            r5 = 0
            if (r0 <= 0) goto L52
            boolean r0 = r2.isEmpty()     // Catch: java.lang.Throwable -> L5c
            if (r0 == 0) goto L4c
            goto L52
        L4c:
            java.util.Set<java.io.File> r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.deleteFileQueue     // Catch: java.lang.Throwable -> L5c
            r0.addAll(r2)     // Catch: java.lang.Throwable -> L5c
            goto L54
        L52:
            org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining = r5     // Catch: java.lang.Throwable -> L5c
        L54:
            int r0 = org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.triesRemaining     // Catch: java.lang.Throwable -> L5c
            if (r0 > 0) goto L59
            goto L5a
        L59:
            r4 = r5
        L5a:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L5c
            return r4
        L5c:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L5c
            throw r0
        L5f:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L5f
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.tryNowThatItsLater():boolean");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void giveUp() {
        synchronized (deleteFileQueue) {
            deleteFileQueue.clear();
            triesRemaining = 0;
        }
    }

    private static void tryToDeleteLater(File dir) {
        synchronized (deleteFileQueue) {
            deleteFileQueue.add(dir);
            if (triesRemaining == 0) {
                new Thread() { // from class: org.apache.xmlbeans.impl.tool.SchemaCodeGenerator.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        while (!SchemaCodeGenerator.access$000()) {
                            try {
                                Thread.sleep(3000L);
                            } catch (InterruptedException e) {
                                SchemaCodeGenerator.giveUp();
                                return;
                            }
                        }
                    }
                };
            }
            if (triesRemaining < 10) {
                triesRemaining = 10;
            }
        }
    }
}
