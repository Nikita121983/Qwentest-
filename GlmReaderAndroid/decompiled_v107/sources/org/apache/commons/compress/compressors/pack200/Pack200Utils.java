package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.logging.log4j.message.StructuredDataId;

/* loaded from: classes9.dex */
public class Pack200Utils {
    public static void normalize(File jar) throws IOException {
        normalize(jar, jar, null);
    }

    public static void normalize(File from, File to) throws IOException {
        normalize(from, to, null);
    }

    public static void normalize(File from, File to, Map<String, String> props) throws IOException {
        if (props == null) {
            props = new HashMap();
        }
        props.put(Pack200.Packer.SEGMENT_LIMIT, StructuredDataId.RESERVED);
        Path tempFile = Files.createTempFile("commons-compress", "pack200normalize", new FileAttribute[0]);
        try {
            OutputStream fos = Files.newOutputStream(tempFile, new OpenOption[0]);
            try {
                JarFile jarFile = new JarFile(from);
                try {
                    Pack200.Packer packer = Pack200.newPacker();
                    packer.properties().putAll(props);
                    packer.pack(jarFile, fos);
                    jarFile.close();
                    if (fos != null) {
                        fos.close();
                    }
                    Pack200.Unpacker unpacker = Pack200.newUnpacker();
                    JarOutputStream jos = new JarOutputStream(Files.newOutputStream(to.toPath(), new OpenOption[0]));
                    try {
                        unpacker.unpack(tempFile.toFile(), jos);
                        jos.close();
                    } finally {
                    }
                } finally {
                }
            } finally {
            }
        } finally {
            Files.delete(tempFile);
        }
    }

    public static void normalize(File jar, Map<String, String> props) throws IOException {
        normalize(jar, jar, props);
    }

    private Pack200Utils() {
    }
}
