package org.apache.poi.poifs.macros;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.poifs.filesystem.DirectoryNode;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.DocumentNode;
import org.apache.poi.poifs.filesystem.Entry;
import org.apache.poi.poifs.filesystem.FileMagic;
import org.apache.poi.poifs.filesystem.OfficeXmlFileException;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.poifs.macros.Module;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.RLEDecompressingInputStream;
import org.apache.poi.util.StringUtil;

/* loaded from: classes10.dex */
public class VBAMacroReader implements Closeable {
    private static final int DOC_STRING_RESERVED = 64;
    private static final int HELP_FILE_PATH_RESERVED = 61;
    private static final Logger LOGGER = PoiLogManager.getLogger((Class<?>) VBAMacroReader.class);
    private static final int MAX_STRING_LENGTH = 20000;
    private static final int MODULE_DOCSTRING_RESERVED = 72;
    private static final int PROJECT_CONSTANTS_RESERVED = 60;
    private static final int REFERENCE_NAME_RESERVED = 62;
    private static final int STREAMNAME_RESERVED = 50;
    protected static final String VBA_PROJECT_OOXML = "vbaProject.bin";
    protected static final String VBA_PROJECT_POIFS = "VBA";
    private POIFSFileSystem fs;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum DIR_STATE {
        INFORMATION_RECORD,
        REFERENCES_RECORD,
        MODULES_RECORD
    }

    public VBAMacroReader(InputStream rstream) throws IOException {
        InputStream is = FileMagic.prepareToCheckMagic(rstream);
        FileMagic fm = FileMagic.valueOf(is);
        if (fm == FileMagic.OLE2) {
            this.fs = new POIFSFileSystem(is);
        } else {
            openOOXML(is);
        }
    }

    public VBAMacroReader(File file) throws IOException {
        try {
            this.fs = new POIFSFileSystem(file);
        } catch (OfficeXmlFileException e) {
            openOOXML(Files.newInputStream(file.toPath(), new OpenOption[0]));
        }
    }

    public VBAMacroReader(POIFSFileSystem fs) {
        this.fs = fs;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0023, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0024, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0028, code lost:
    
        throw r1;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void openOOXML(java.io.InputStream r5) throws java.io.IOException {
        /*
            r4 = this;
            java.util.zip.ZipInputStream r0 = new java.util.zip.ZipInputStream
            r0.<init>(r5)
        L5:
            java.util.zip.ZipEntry r1 = r0.getNextEntry()     // Catch: java.lang.Throwable -> L34
            r2 = r1
            if (r1 == 0) goto L29
            java.lang.String r1 = r2.getName()     // Catch: java.lang.Throwable -> L34
            java.lang.String r3 = "vbaProject.bin"
            boolean r1 = org.apache.poi.util.StringUtil.endsWithIgnoreCase(r1, r3)     // Catch: java.lang.Throwable -> L34
            if (r1 == 0) goto L5
            org.apache.poi.poifs.filesystem.POIFSFileSystem r1 = new org.apache.poi.poifs.filesystem.POIFSFileSystem     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L34
            r1.<init>(r0)     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L34
            r4.fs = r1     // Catch: java.io.IOException -> L23 java.lang.Throwable -> L34
            r0.close()
            return
        L23:
            r1 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L34
            throw r1     // Catch: java.lang.Throwable -> L34
        L29:
            r0.close()
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "No VBA project found"
            r0.<init>(r1)
            throw r0
        L34:
            r1 = move-exception
            throw r1     // Catch: java.lang.Throwable -> L36
        L36:
            r2 = move-exception
            r0.close()     // Catch: java.lang.Throwable -> L3b
            goto L3f
        L3b:
            r3 = move-exception
            r1.addSuppressed(r3)
        L3f:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.openOOXML(java.io.InputStream):void");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fs.close();
        this.fs = null;
    }

    public Map<String, Module> readMacroModules() throws IOException {
        ModuleMap modules = new ModuleMap();
        Map<String, String> moduleNameMap = new LinkedHashMap<>();
        findMacros(this.fs.getRoot(), modules);
        findModuleNameMap(this.fs.getRoot(), moduleNameMap, modules);
        findProjectProperties(this.fs.getRoot(), moduleNameMap, modules);
        Map<String, Module> moduleSources = new HashMap<>();
        for (Map.Entry<String, ModuleImpl> entry : modules.entrySet()) {
            ModuleImpl module = entry.getValue();
            module.charset = modules.charset;
            moduleSources.put(entry.getKey(), module);
        }
        return moduleSources;
    }

    public Map<String, String> readMacros() throws IOException {
        Map<String, Module> modules = readMacroModules();
        Map<String, String> moduleSources = new HashMap<>();
        for (Map.Entry<String, Module> entry : modules.entrySet()) {
            moduleSources.put(entry.getKey(), entry.getValue().getContent());
        }
        return moduleSources;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public static class ModuleImpl implements Module {
        byte[] buf;
        Charset charset;
        Module.ModuleType moduleType;
        Integer offset;

        protected ModuleImpl() {
        }

        void read(InputStream in) throws IOException {
            this.buf = IOUtils.toByteArray(in);
        }

        @Override // org.apache.poi.poifs.macros.Module
        public String getContent() {
            return new String(this.buf, this.charset);
        }

        @Override // org.apache.poi.poifs.macros.Module
        public Module.ModuleType geModuleType() {
            return this.moduleType;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public static class ModuleMap extends HashMap<String, ModuleImpl> {
        Charset charset = StringUtil.WIN_1252;

        protected ModuleMap() {
        }
    }

    protected void findMacros(DirectoryNode dir, ModuleMap modules) throws IOException {
        if (VBA_PROJECT_POIFS.equalsIgnoreCase(dir.getName())) {
            readMacros(dir, modules);
            return;
        }
        Iterator<Entry> it = dir.iterator();
        while (it.hasNext()) {
            Entry child = it.next();
            if (child instanceof DirectoryNode) {
                findMacros((DirectoryNode) child, modules);
            }
        }
    }

    private static void readModuleMetadataFromDirStream(RLEDecompressingInputStream in, String streamName, ModuleMap modules) throws IOException {
        int moduleOffset = in.readInt();
        ModuleImpl module = modules.get(streamName);
        if (module == null) {
            ModuleImpl module2 = new ModuleImpl();
            module2.offset = Integer.valueOf(moduleOffset);
            modules.put(streamName, module2);
        } else {
            InputStream stream = new RLEDecompressingInputStream(UnsynchronizedByteArrayInputStream.builder().setByteArray(module.buf).setOffset(moduleOffset).setLength(module.buf.length - moduleOffset).get());
            module.read(stream);
            stream.close();
        }
    }

    private static void readModuleFromDocumentStream(DocumentNode documentNode, String name, ModuleMap modules) throws IOException {
        ModuleImpl module = modules.get(name);
        if (module == null) {
            ModuleImpl module2 = new ModuleImpl();
            modules.put(name, module2);
            InputStream dis = new DocumentInputStream(documentNode);
            try {
                module2.read(dis);
                dis.close();
                return;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        dis.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        }
        if (module.buf == null) {
            if (module.offset == null) {
                throw new IOException("Module offset for '" + name + "' was never read.");
            }
            try {
                InputStream compressed = new DocumentInputStream(documentNode);
                try {
                    trySkip(compressed, module.offset.intValue());
                    InputStream decompressed = new RLEDecompressingInputStream(compressed);
                    try {
                        module.read(decompressed);
                        decompressed.close();
                        compressed.close();
                    } finally {
                    }
                } catch (Throwable th4) {
                    try {
                        throw th4;
                    } catch (Throwable th5) {
                        try {
                            compressed.close();
                        } catch (Throwable th6) {
                            th4.addSuppressed(th6);
                        }
                        throw th5;
                    }
                }
            } catch (IllegalArgumentException | IllegalStateException e) {
                InputStream compressed2 = new DocumentInputStream(documentNode);
                try {
                    byte[] decompressedBytes = findCompressedStreamWBruteForce(compressed2);
                    compressed2.close();
                    if (decompressedBytes != null) {
                        module.read(UnsynchronizedByteArrayInputStream.builder().setByteArray(decompressedBytes).get());
                    }
                } catch (Throwable th7) {
                    try {
                        throw th7;
                    } catch (Throwable th8) {
                        try {
                            compressed2.close();
                        } catch (Throwable th9) {
                            th7.addSuppressed(th9);
                        }
                        throw th8;
                    }
                }
            }
        }
    }

    private static void trySkip(InputStream in, long n) throws IOException {
        long skippedBytes = IOUtils.skipFully(in, n);
        if (skippedBytes != n) {
            if (skippedBytes < 0) {
                throw new IOException("Tried skipping " + n + " bytes, but no bytes were skipped. The end of the stream has been reached or the stream is closed.");
            }
            throw new IOException("Tried skipping " + n + " bytes, but only " + skippedBytes + " bytes were skipped. This should never happen with a non-corrupt file.");
        }
    }

    protected void readMacros(DirectoryNode macroDir, ModuleMap modules) throws IOException {
        Iterator<String> it = macroDir.getEntryNames().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            String entryName = it.next();
            if ("dir".equalsIgnoreCase(entryName)) {
                processDirStream(macroDir.getEntryCaseInsensitive(entryName), modules);
                break;
            }
        }
        Iterator<Entry> it2 = macroDir.iterator();
        while (it2.hasNext()) {
            Entry entry = it2.next();
            if (entry instanceof DocumentNode) {
                String name = entry.getName();
                DocumentNode document = (DocumentNode) entry;
                if (!"dir".equalsIgnoreCase(name) && !StringUtil.startsWithIgnoreCase(name, "__SRP") && !StringUtil.startsWithIgnoreCase(name, "_VBA_PROJECT")) {
                    readModuleFromDocumentStream(document, name, modules);
                }
            }
        }
    }

    protected void findProjectProperties(DirectoryNode node, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        Iterator<Entry> it = node.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            if ("project".equalsIgnoreCase(entry.getName())) {
                DocumentNode document = (DocumentNode) entry;
                DocumentInputStream dis = new DocumentInputStream(document);
                try {
                    readProjectProperties(dis, moduleNameMap, modules);
                    dis.close();
                    return;
                } finally {
                }
            } else if (entry instanceof DirectoryNode) {
                findProjectProperties((DirectoryNode) entry, moduleNameMap, modules);
            }
        }
    }

    protected void findModuleNameMap(DirectoryNode node, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        Iterator<Entry> it = node.iterator();
        while (it.hasNext()) {
            Entry entry = it.next();
            if ("projectwm".equalsIgnoreCase(entry.getName())) {
                DocumentNode document = (DocumentNode) entry;
                DocumentInputStream dis = new DocumentInputStream(document);
                try {
                    readNameMapRecords(dis, moduleNameMap, modules.charset);
                    dis.close();
                    return;
                } finally {
                }
            } else if (entry.isDirectoryEntry()) {
                findModuleNameMap((DirectoryNode) entry, moduleNameMap, modules);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public enum RecordType {
        MODULE_OFFSET(49),
        PROJECT_SYS_KIND(1),
        PROJECT_LCID(2),
        PROJECT_LCID_INVOKE(20),
        PROJECT_CODEPAGE(3),
        PROJECT_NAME(4),
        PROJECT_DOC_STRING(5),
        PROJECT_HELP_FILE_PATH(6),
        PROJECT_HELP_CONTEXT(7, 8),
        PROJECT_LIB_FLAGS(8),
        PROJECT_VERSION(9, 10),
        PROJECT_CONSTANTS(12),
        PROJECT_MODULES(15),
        DIR_STREAM_TERMINATOR(16),
        PROJECT_COOKIE(19),
        MODULE_NAME(25),
        MODULE_NAME_UNICODE(71),
        MODULE_STREAM_NAME(26),
        MODULE_DOC_STRING(28),
        MODULE_HELP_CONTEXT(30),
        MODULE_COOKIE(44),
        MODULE_TYPE_PROCEDURAL(33, 4),
        MODULE_TYPE_OTHER(34, 4),
        MODULE_PRIVATE(40, 4),
        REFERENCE_NAME(22),
        REFERENCE_REGISTERED(13),
        REFERENCE_PROJECT(14),
        REFERENCE_CONTROL_A(47),
        REFERENCE_CONTROL_B(51),
        MODULE_TERMINATOR(43),
        EOF(-1),
        UNKNOWN(-2);

        private final int constantLength;
        private final int id;

        RecordType(int id) {
            this.id = id;
            this.constantLength = -1;
        }

        RecordType(int id, int constantLength) {
            this.id = id;
            this.constantLength = constantLength;
        }

        int getConstantLength() {
            return this.constantLength;
        }

        static RecordType lookup(int id) {
            for (RecordType type : values()) {
                if (type.id == id) {
                    return type;
                }
            }
            return UNKNOWN;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class ASCIIUnicodeStringPair {
        private final String ascii;
        private final int pushbackRecordId;
        private final String unicode;

        ASCIIUnicodeStringPair(String ascii, int pushbackRecordId) {
            this.ascii = ascii;
            this.unicode = "";
            this.pushbackRecordId = pushbackRecordId;
        }

        ASCIIUnicodeStringPair(String ascii, String unicode) {
            this.ascii = ascii;
            this.unicode = unicode;
            this.pushbackRecordId = -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String getAscii() {
            return this.ascii;
        }

        private String getUnicode() {
            return this.unicode;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getPushbackRecordId() {
            return this.pushbackRecordId;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:90:0x0195, code lost:
    
        r5.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0199, code lost:
    
        r2.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x019c, code lost:
    
        return;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x003b. Please report as an issue. */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void processDirStream(org.apache.poi.poifs.filesystem.Entry r13, org.apache.poi.poifs.macros.VBAMacroReader.ModuleMap r14) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 514
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.poifs.macros.VBAMacroReader.processDirStream(org.apache.poi.poifs.filesystem.Entry, org.apache.poi.poifs.macros.VBAMacroReader$ModuleMap):void");
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream in, Charset charset, int reservedByte) throws IOException {
        return readStringPair(in, charset, reservedByte, true);
    }

    private ASCIIUnicodeStringPair readStringPair(RLEDecompressingInputStream in, Charset charset, int reservedByte, boolean throwOnUnexpectedReservedByte) throws IOException {
        int nameLength = in.readInt();
        String ascii = readString(in, nameLength, charset);
        int reserved = in.readShort();
        if (reserved != reservedByte) {
            if (throwOnUnexpectedReservedByte) {
                throw new IOException("Expected " + Integer.toHexString(reservedByte) + "after name before Unicode name, but found: " + Integer.toHexString(reserved));
            }
            return new ASCIIUnicodeStringPair(ascii, reserved);
        }
        int unicodeNameRecordLength = in.readInt();
        String unicode = readUnicodeString(in, unicodeNameRecordLength);
        return new ASCIIUnicodeStringPair(ascii, unicode);
    }

    protected void readNameMapRecords(InputStream is, Map<String, String> moduleNames, Charset charset) throws IOException {
        int records = 0;
        while (true) {
            records++;
            if (records < 10000) {
                try {
                    int b = IOUtils.readByte(is);
                    if (b == 0 && (b = IOUtils.readByte(is)) == 0) {
                        return;
                    }
                    String mbcs = readMBCS(b, is, charset);
                    try {
                        String unicode = readUnicode(is);
                        if (StringUtil.isNotBlank(mbcs) && StringUtil.isNotBlank(unicode)) {
                            moduleNames.put(mbcs, unicode);
                        }
                    } catch (EOFException e) {
                        return;
                    }
                } catch (EOFException e2) {
                    return;
                }
            } else {
                LOGGER.atWarn().log("Hit max name records to read (10000). Stopped early.");
                return;
            }
        }
    }

    private static String readUnicode(InputStream is) throws IOException {
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            int b0 = IOUtils.readByte(is);
            int b1 = IOUtils.readByte(is);
            int read = 2;
            while (b0 + b1 != 0 && read < 20000) {
                bos.write(b0);
                bos.write(b1);
                b0 = IOUtils.readByte(is);
                b1 = IOUtils.readByte(is);
                read += 2;
            }
            if (read >= 20000) {
                LOGGER.atWarn().log("stopped reading unicode name after {} bytes", Unbox.box(read));
            }
            String unsynchronizedByteArrayOutputStream = bos.toString(StandardCharsets.UTF_16LE);
            if (bos != null) {
                bos.close();
            }
            return unsynchronizedByteArrayOutputStream;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                if (bos != null) {
                    try {
                        bos.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                }
                throw th2;
            }
        }
    }

    private static String readMBCS(int firstByte, InputStream is, Charset charset) throws IOException {
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        int len = 0;
        int b = firstByte;
        while (b > 0 && len < 20000) {
            len++;
            try {
                bos.write(b);
                b = IOUtils.readByte(is);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (bos != null) {
                        try {
                            bos.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        }
        String unsynchronizedByteArrayOutputStream = bos.toString(charset);
        if (bos != null) {
            bos.close();
        }
        return unsynchronizedByteArrayOutputStream;
    }

    private static String readString(InputStream stream, int length, Charset charset) throws IOException {
        byte[] buffer = IOUtils.safelyAllocate(length, 20000);
        int bytesRead = IOUtils.readFully(stream, buffer);
        if (bytesRead != length) {
            throw new IOException("Tried to read: " + length + ", but could only read: " + bytesRead);
        }
        return new String(buffer, 0, length, charset);
    }

    protected void readProjectProperties(DocumentInputStream dis, Map<String, String> moduleNameMap, ModuleMap modules) throws IOException {
        int i;
        InputStreamReader reader;
        StringBuilder builder;
        int i2;
        InputStreamReader reader2 = new InputStreamReader(dis, modules.charset);
        StringBuilder builder2 = new StringBuilder();
        char[] buffer = new char[512];
        while (true) {
            int read = reader2.read(buffer);
            i = 0;
            if (read < 0) {
                break;
            } else {
                builder2.append(buffer, 0, read);
            }
        }
        String properties = builder2.toString();
        String[] split = properties.split("\r\n|\n\r");
        int length = split.length;
        int i3 = 0;
        while (i3 < length) {
            String line = split[i3];
            if (line.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX)) {
                reader = reader2;
                builder = builder2;
                i2 = i;
            } else {
                String[] tokens = line.split("=");
                int i4 = i;
                if (tokens.length <= 1 || tokens[1].length() <= 1) {
                    reader = reader2;
                } else if (!tokens[1].startsWith("\"") || !tokens[1].endsWith("\"")) {
                    reader = reader2;
                } else {
                    reader = reader2;
                    tokens[1] = tokens[1].substring(1, tokens[1].length() - 1);
                }
                if (!"Document".equals(tokens[i4]) || tokens.length <= 1) {
                    builder = builder2;
                    if (!"Module".equals(tokens[0]) || tokens.length <= 1) {
                        i2 = 0;
                        if ("Class".equals(tokens[0]) && tokens.length > 1) {
                            ModuleImpl module = getModule(tokens[1], moduleNameMap, modules);
                            if (module != null) {
                                module.moduleType = Module.ModuleType.Class;
                            } else {
                                LOGGER.atWarn().log("couldn't find module with name: {}", tokens[1]);
                            }
                        }
                    } else {
                        ModuleImpl module2 = getModule(tokens[1], moduleNameMap, modules);
                        if (module2 != null) {
                            module2.moduleType = Module.ModuleType.Module;
                        } else {
                            LOGGER.atWarn().log("couldn't find module with name: {}", tokens[1]);
                        }
                        i2 = 0;
                    }
                } else {
                    builder = builder2;
                    String mn = tokens[1].substring(i4, tokens[1].indexOf("/&H"));
                    ModuleImpl module3 = getModule(mn, moduleNameMap, modules);
                    if (module3 != null) {
                        module3.moduleType = Module.ModuleType.Document;
                    } else {
                        LOGGER.atWarn().log("couldn't find module with name: {}", mn);
                    }
                    i2 = 0;
                }
            }
            i3++;
            i = i2;
            reader2 = reader;
            builder2 = builder;
        }
    }

    private ModuleImpl getModule(String moduleName, Map<String, String> moduleNameMap, ModuleMap moduleMap) {
        if (moduleNameMap.containsKey(moduleName)) {
            return moduleMap.get(moduleNameMap.get(moduleName));
        }
        return moduleMap.get(moduleName);
    }

    private String readUnicodeString(RLEDecompressingInputStream in, int unicodeNameRecordLength) throws IOException {
        byte[] buffer = IOUtils.safelyAllocate(unicodeNameRecordLength, 20000);
        int bytesRead = IOUtils.readFully(in, buffer);
        if (bytesRead != unicodeNameRecordLength) {
            throw new EOFException();
        }
        return new String(buffer, StringUtil.UTF16LE);
    }

    private static byte[] findCompressedStreamWBruteForce(InputStream is) throws IOException {
        int w;
        byte[] compressed = IOUtils.toByteArray(is);
        byte[] decompressed = null;
        for (int i = 0; i < compressed.length; i++) {
            if (compressed[i] == 1 && i < compressed.length - 1 && (w = LittleEndian.getUShort(compressed, i + 1)) > 0 && (w & 28672) == 12288 && (decompressed = tryToDecompress(UnsynchronizedByteArrayInputStream.builder().setByteArray(compressed).setOffset(i).setLength(compressed.length - i).get())) != null && decompressed.length > 9) {
                int firstX = Math.min(20, decompressed.length);
                String start = new String(decompressed, 0, firstX, StringUtil.WIN_1252);
                if (start.contains("Attribute")) {
                    return decompressed;
                }
            }
        }
        return decompressed;
    }

    private static byte[] tryToDecompress(InputStream is) {
        try {
            RLEDecompressingInputStream ris = new RLEDecompressingInputStream(is);
            try {
                byte[] byteArray = IOUtils.toByteArray(ris);
                ris.close();
                return byteArray;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        ris.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException | IllegalArgumentException | IllegalStateException e) {
            return null;
        }
    }
}
