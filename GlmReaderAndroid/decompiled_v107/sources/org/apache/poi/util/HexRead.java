package org.apache.poi.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

/* loaded from: classes10.dex */
public class HexRead {
    public static byte[] readData(String filename) throws IOException {
        File file = new File(filename);
        InputStream stream = Files.newInputStream(file.toPath(), new OpenOption[0]);
        try {
            byte[] readData = readData(stream, -1);
            if (stream != null) {
                stream.close();
            }
            return readData;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (stream != null) {
                    try {
                        stream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:5:0x000d. Please report as an issue. */
    public static byte[] readData(InputStream stream, String section) throws IOException {
        try {
            StringBuilder sectionText = new StringBuilder();
            boolean inSection = false;
            int c = stream.read();
            while (c != -1) {
                switch (c) {
                    case 10:
                    case 13:
                        inSection = false;
                        StringBuilder sectionText2 = new StringBuilder();
                        sectionText = sectionText2;
                        c = stream.read();
                    case 91:
                        inSection = true;
                        c = stream.read();
                    case 93:
                        inSection = false;
                        if (sectionText.toString().equals(section)) {
                            return readData(stream, 91);
                        }
                        StringBuilder sectionText3 = new StringBuilder();
                        sectionText = sectionText3;
                        c = stream.read();
                    default:
                        if (inSection) {
                            sectionText.append((char) c);
                        }
                        c = stream.read();
                }
            }
            stream.close();
            throw new IOException("Section '" + section + "' not found");
        } finally {
            stream.close();
        }
    }

    public static byte[] readData(String filename, String section) throws IOException {
        return readData(Files.newInputStream(Paths.get(filename, new String[0]), new OpenOption[0]), section);
    }

    public static byte[] readData(InputStream stream, int eofChar) throws IOException {
        int characterCount = 0;
        byte b = 0;
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        while (true) {
            try {
                int count = stream.read();
                int digitValue = -1;
                if (48 <= count && count <= 57) {
                    digitValue = count - 48;
                } else if (65 <= count && count <= 70) {
                    digitValue = count - 55;
                } else if (97 <= count && count <= 102) {
                    digitValue = count - 87;
                } else if (35 == count) {
                    readToEOL(stream);
                } else if (-1 != count) {
                    if (eofChar == count) {
                        break;
                    }
                } else {
                    break;
                }
                if (digitValue != -1) {
                    b = (byte) (((byte) digitValue) + ((byte) (b << 4)));
                    characterCount++;
                    if (characterCount == 2) {
                        bytes.write(b);
                        characterCount = 0;
                        b = 0;
                    }
                }
            } finally {
            }
        }
        byte[] byteArray = bytes.toByteArray();
        bytes.close();
        return byteArray;
    }

    public static byte[] readFromString(String data) {
        try {
            return readData(new ByteArrayInputStream(data.getBytes(StringUtil.UTF8)), -1);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private static void readToEOL(InputStream stream) throws IOException {
        int c = stream.read();
        while (c != -1 && c != 10 && c != 13) {
            c = stream.read();
        }
    }
}
