package org.apache.poi.openxml4j.opc.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* loaded from: classes10.dex */
public final class FileHelper {
    public static File getDirectory(File f) {
        char ch1;
        if (f != null) {
            String path = f.getPath();
            int num2 = path.length();
            do {
                num2--;
                if (num2 >= 0) {
                    ch1 = path.charAt(num2);
                } else {
                    return null;
                }
            } while (ch1 != File.separatorChar);
            return new File(path.substring(0, num2));
        }
        return null;
    }

    public static void copyFile(File in, File out) throws IOException {
        FileInputStream fis = new FileInputStream(in);
        try {
            FileOutputStream fos = new FileOutputStream(out);
            try {
                FileChannel sourceChannel = fis.getChannel();
                try {
                    FileChannel destinationChannel = fos.getChannel();
                    try {
                        sourceChannel.transferTo(0L, sourceChannel.size(), destinationChannel);
                        if (destinationChannel != null) {
                            destinationChannel.close();
                        }
                        if (sourceChannel != null) {
                            sourceChannel.close();
                        }
                        fos.close();
                        fis.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    public static String getFilename(File file) {
        char ch1;
        if (file != null) {
            String path = file.getPath();
            int len = path.length();
            int num2 = len;
            do {
                num2--;
                if (num2 >= 0) {
                    ch1 = path.charAt(num2);
                } else {
                    return "";
                }
            } while (ch1 != File.separatorChar);
            return path.substring(num2 + 1, len);
        }
        return "";
    }
}
