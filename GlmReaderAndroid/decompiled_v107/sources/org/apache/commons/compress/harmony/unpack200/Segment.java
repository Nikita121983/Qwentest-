package org.apache.commons.compress.harmony.unpack200;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Consumer;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.zip.CRC32;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPField;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethod;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassConstantPool;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFile;
import org.apache.commons.compress.harmony.unpack200.bytecode.ClassFileEntry;
import org.apache.commons.compress.harmony.unpack200.bytecode.InnerClassesAttribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.SourceFileAttribute;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class Segment {
    public static final int LOG_LEVEL_QUIET = 0;
    public static final int LOG_LEVEL_STANDARD = 1;
    public static final int LOG_LEVEL_VERBOSE = 2;
    private AttrDefinitionBands attrDefinitionBands;
    private BcBands bcBands;
    private ClassBands classBands;
    private byte[][] classFilesContents;
    private CpBands cpBands;
    private boolean deflateHint;
    private boolean doPreRead;
    private FileBands fileBands;
    private boolean[] fileDeflate;
    private boolean[] fileIsClass;
    private SegmentHeader header;
    private IcBands icBands;
    private InputStream internalBuffer;
    private int logLevel;
    private PrintWriter logPrintWriter;
    private boolean overrideDeflateHint;

    private ClassFile buildClassFile(int classNum) {
        List<Attribute> classAttributes;
        CPUTF8 innerName;
        CPClass outerClass;
        ClassFile classFile = new ClassFile();
        int[] major = this.classBands.getClassVersionMajor();
        int[] minor = this.classBands.getClassVersionMinor();
        if (major != null) {
            classFile.major = major[classNum];
            classFile.minor = minor[classNum];
        } else {
            classFile.major = this.header.getDefaultClassMajorVersion();
            classFile.minor = this.header.getDefaultClassMinorVersion();
        }
        ClassConstantPool cp = classFile.pool;
        int fullNameIndexInCpClass = this.classBands.getClassThisInts()[classNum];
        String fullName = this.cpBands.getCpClass()[fullNameIndexInCpClass];
        int i = fullName.lastIndexOf(PackagingURIHelper.FORWARD_SLASH_STRING) + 1;
        List<Attribute> classAttributes2 = this.classBands.getClassAttributes()[classNum];
        SourceFileAttribute sourceFileAttribute = null;
        for (Attribute classAttribute : classAttributes2) {
            if (classAttribute.isSourceFileAttribute()) {
                sourceFileAttribute = (SourceFileAttribute) classAttribute;
            }
        }
        if (sourceFileAttribute == null) {
            AttributeLayout SOURCE_FILE = this.attrDefinitionBands.getAttributeDefinitionMap().getAttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0);
            classAttributes = classAttributes2;
            if (SOURCE_FILE.matches(this.classBands.getRawClassFlags()[classNum])) {
                int firstDollar = -1;
                for (int index = 0; index < fullName.length(); index++) {
                    if (fullName.charAt(index) <= '$') {
                        firstDollar = index;
                    }
                }
                String fileName = (firstDollar <= -1 || i > firstDollar) ? fullName.substring(i) + ".java" : fullName.substring(i, firstDollar) + ".java";
                sourceFileAttribute = new SourceFileAttribute(this.cpBands.cpUTF8Value(fileName, false));
                classFile.attributes = new Attribute[]{(Attribute) cp.add(sourceFileAttribute)};
            } else {
                classFile.attributes = new Attribute[0];
            }
        } else {
            classAttributes = classAttributes2;
            classFile.attributes = new Attribute[]{(Attribute) cp.add(sourceFileAttribute)};
        }
        List<Attribute> classAttributesWithoutSourceFileAttribute = new ArrayList<>(classAttributes.size());
        int index2 = 0;
        while (index2 < classAttributes.size()) {
            List<Attribute> classAttributes3 = classAttributes;
            Attribute attrib = classAttributes3.get(index2);
            if (!attrib.isSourceFileAttribute()) {
                classAttributesWithoutSourceFileAttribute.add(attrib);
            }
            index2++;
            classAttributes = classAttributes3;
        }
        List<Attribute> classAttributes4 = classAttributes;
        Attribute[] originalAttributes = classFile.attributes;
        classFile.attributes = new Attribute[originalAttributes.length + classAttributesWithoutSourceFileAttribute.size()];
        System.arraycopy(originalAttributes, 0, classFile.attributes, 0, originalAttributes.length);
        for (int index3 = 0; index3 < classAttributesWithoutSourceFileAttribute.size(); index3++) {
            Attribute attrib2 = classAttributesWithoutSourceFileAttribute.get(index3);
            cp.add(attrib2);
            classFile.attributes[originalAttributes.length + index3] = attrib2;
        }
        ClassFileEntry cfThis = cp.add(this.cpBands.cpClassValue(fullNameIndexInCpClass));
        ClassFileEntry cfSuper = cp.add(this.cpBands.cpClassValue(this.classBands.getClassSuperInts()[classNum]));
        ClassFileEntry[] cfInterfaces = new ClassFileEntry[this.classBands.getClassInterfacesInts()[classNum].length];
        int i2 = 0;
        while (i2 < cfInterfaces.length) {
            cfInterfaces[i2] = cp.add(this.cpBands.cpClassValue(this.classBands.getClassInterfacesInts()[classNum][i2]));
            i2++;
            major = major;
        }
        ClassFileEntry[] cfFields = new ClassFileEntry[this.classBands.getClassFieldCount()[classNum]];
        int i3 = 0;
        while (i3 < cfFields.length) {
            int descriptorIndex = this.classBands.getFieldDescrInts()[classNum][i3];
            int[] minor2 = minor;
            int nameIndex = this.cpBands.getCpDescriptorNameInts()[descriptorIndex];
            int fullNameIndexInCpClass2 = fullNameIndexInCpClass;
            int typeIndex = this.cpBands.getCpDescriptorTypeInts()[descriptorIndex];
            int i4 = i3;
            CPUTF8 name = this.cpBands.cpUTF8Value(nameIndex);
            CPUTF8 descriptor = this.cpBands.cpSignatureValue(typeIndex);
            cfFields[i4] = cp.add(new CPField(name, descriptor, this.classBands.getFieldFlags()[classNum][i4], this.classBands.getFieldAttributes()[classNum][i4]));
            i3 = i4 + 1;
            minor = minor2;
            fullNameIndexInCpClass = fullNameIndexInCpClass2;
        }
        ClassFileEntry[] cfMethods = new ClassFileEntry[this.classBands.getClassMethodCount()[classNum]];
        int typeIndex2 = 0;
        while (typeIndex2 < cfMethods.length) {
            int descriptorIndex2 = this.classBands.getMethodDescrInts()[classNum][typeIndex2];
            int nameIndex2 = this.cpBands.getCpDescriptorNameInts()[descriptorIndex2];
            int i5 = typeIndex2;
            int typeIndex3 = this.cpBands.getCpDescriptorTypeInts()[descriptorIndex2];
            CPUTF8 name2 = this.cpBands.cpUTF8Value(nameIndex2);
            CPUTF8 descriptor2 = this.cpBands.cpSignatureValue(typeIndex3);
            cfMethods[i5] = cp.add(new CPMethod(name2, descriptor2, this.classBands.getMethodFlags()[classNum][i5], this.classBands.getMethodAttributes()[classNum][i5]));
            typeIndex2 = i5 + 1;
        }
        cp.addNestedEntries();
        IcTuple[] icLocal = getClassBands().getIcLocal()[classNum];
        boolean icLocalSent = icLocal != null;
        boolean addInnerClassesAttr = false;
        InnerClassesAttribute innerClassesAttribute = new InnerClassesAttribute(AttributeLayout.ATTRIBUTE_INNER_CLASSES);
        IcTuple[] icRelevant = getIcBands().getRelevantIcTuples(fullName, cp);
        List<IcTuple> ic_stored = computeIcStored(icLocal, icRelevant);
        for (IcTuple icStored : ic_stored) {
            String fullName2 = fullName;
            Attribute[] originalAttributes2 = originalAttributes;
            int innerClassIndex = icStored.thisClassIndex();
            SourceFileAttribute sourceFileAttribute2 = sourceFileAttribute;
            int outerClassIndex = icStored.outerClassIndex();
            List<Attribute> classAttributes5 = classAttributes4;
            int simpleClassNameIndex = icStored.simpleClassNameIndex();
            boolean icLocalSent2 = icLocalSent;
            String innerClassString = icStored.thisClassString();
            ClassFileEntry[] cfMethods2 = cfMethods;
            String outerClassString = icStored.outerClassString();
            ClassFileEntry[] cfFields2 = cfFields;
            String simpleClassName = icStored.simpleClassName();
            ClassFileEntry[] cfInterfaces2 = cfInterfaces;
            CPClass innerClass = innerClassIndex != -1 ? this.cpBands.cpClassValue(innerClassIndex) : this.cpBands.cpClassValue(innerClassString);
            if (!icStored.isAnonymous()) {
                innerName = simpleClassNameIndex != -1 ? this.cpBands.cpUTF8Value(simpleClassNameIndex) : this.cpBands.cpUTF8Value(simpleClassName);
            } else {
                innerName = null;
            }
            if (icStored.isMember()) {
                outerClass = outerClassIndex != -1 ? this.cpBands.cpClassValue(outerClassIndex) : this.cpBands.cpClassValue(outerClassString);
            } else {
                outerClass = null;
            }
            int flags = icStored.F;
            innerClassesAttribute.addInnerClassesEntry(innerClass, outerClass, innerName, flags);
            addInnerClassesAttr = true;
            originalAttributes = originalAttributes2;
            fullName = fullName2;
            sourceFileAttribute = sourceFileAttribute2;
            classAttributes4 = classAttributes5;
            icLocalSent = icLocalSent2;
            cfMethods = cfMethods2;
            cfFields = cfFields2;
            cfInterfaces = cfInterfaces2;
        }
        ClassFileEntry[] cfFields3 = cfFields;
        ClassFileEntry[] cfMethods3 = cfMethods;
        ClassFileEntry[] cfInterfaces3 = cfInterfaces;
        boolean icLocalSent3 = icLocalSent;
        if (icLocalSent3 && icLocal.length == 0) {
            addInnerClassesAttr = false;
        }
        if (!icLocalSent3 && icRelevant.length == 0) {
            addInnerClassesAttr = false;
        }
        if (addInnerClassesAttr) {
            Attribute[] originalAttrs = classFile.attributes;
            Attribute[] newAttrs = new Attribute[originalAttrs.length + 1];
            System.arraycopy(originalAttrs, 0, newAttrs, 0, originalAttrs.length);
            newAttrs[newAttrs.length - 1] = innerClassesAttribute;
            classFile.attributes = newAttrs;
            cp.addWithNestedEntries(innerClassesAttribute);
        }
        cp.resolve(this);
        classFile.accessFlags = (int) this.classBands.getClassFlags()[classNum];
        classFile.thisClass = cp.indexOf(cfThis);
        classFile.superClass = cp.indexOf(cfSuper);
        classFile.interfaces = new int[cfInterfaces3.length];
        for (int i6 = 0; i6 < cfInterfaces3.length; i6++) {
            classFile.interfaces[i6] = cp.indexOf(cfInterfaces3[i6]);
        }
        classFile.fields = cfFields3;
        classFile.methods = cfMethods3;
        return classFile;
    }

    private List<IcTuple> computeIcStored(IcTuple[] icLocal, IcTuple[] icRelevant) {
        final List<IcTuple> result = new ArrayList<>(icRelevant.length);
        List<IcTuple> duplicates = new ArrayList<>(icRelevant.length);
        Set<IcTuple> isInResult = new HashSet<>(icRelevant.length);
        if (icLocal != null) {
            for (IcTuple element : icLocal) {
                if (isInResult.add(element)) {
                    result.add(element);
                }
            }
        }
        for (IcTuple element2 : icRelevant) {
            if (isInResult.add(element2)) {
                result.add(element2);
            } else {
                duplicates.add(element2);
            }
        }
        Objects.requireNonNull(result);
        duplicates.forEach(new Consumer() { // from class: org.apache.commons.compress.harmony.unpack200.Segment$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                result.remove((IcTuple) obj);
            }
        });
        return result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AttrDefinitionBands getAttrDefinitionBands() {
        return this.attrDefinitionBands;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public ClassBands getClassBands() {
        return this.classBands;
    }

    public SegmentConstantPool getConstantPool() {
        return this.cpBands.getConstantPool();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CpBands getCpBands() {
        return this.cpBands;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public IcBands getIcBands() {
        return this.icBands;
    }

    public SegmentHeader getSegmentHeader() {
        return this.header;
    }

    public void log(int messageLevel, String message) {
        if (this.logLevel >= messageLevel && this.logPrintWriter != null) {
            this.logPrintWriter.println(message);
        }
    }

    public void overrideDeflateHint(boolean deflateHint) {
        this.overrideDeflateHint = true;
        this.deflateHint = deflateHint;
    }

    private void parseSegment() throws IOException, Pack200Exception {
        this.header.unpack();
        this.cpBands.unpack();
        this.attrDefinitionBands.unpack();
        this.icBands.unpack();
        this.classBands.unpack();
        this.bcBands.unpack();
        this.fileBands.unpack();
        int classNum = 0;
        int numberOfFiles = this.header.getNumberOfFiles();
        String[] fileName = this.fileBands.getFileName();
        int[] fileOptions = this.fileBands.getFileOptions();
        SegmentOptions options = this.header.getOptions();
        this.classFilesContents = new byte[numberOfFiles];
        this.fileDeflate = new boolean[numberOfFiles];
        this.fileIsClass = new boolean[numberOfFiles];
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        for (int i = 0; i < numberOfFiles; i++) {
            String name = fileName[i];
            boolean nameIsEmpty = name == null || name.isEmpty();
            boolean isClass = (fileOptions[i] & 2) == 2 || nameIsEmpty;
            if (isClass && nameIsEmpty) {
                fileName[i] = this.cpBands.getCpClass()[this.classBands.getClassThisInts()[classNum]] + ".class";
            }
            if (this.overrideDeflateHint) {
                this.fileDeflate[i] = this.deflateHint;
            } else {
                this.fileDeflate[i] = (fileOptions[i] & 1) == 1 || options.shouldDeflate();
            }
            this.fileIsClass[i] = isClass;
            if (isClass) {
                ClassFile classFile = buildClassFile(classNum);
                classFile.write(dos);
                dos.flush();
                this.classFilesContents[classNum] = bos.toByteArray();
                bos.reset();
                classNum++;
            }
        }
    }

    private void readSegment(InputStream in) throws IOException, Pack200Exception {
        log(2, "-------");
        this.cpBands = new CpBands(this);
        this.cpBands.read(in);
        this.attrDefinitionBands = new AttrDefinitionBands(this);
        this.attrDefinitionBands.read(in);
        this.icBands = new IcBands(this);
        this.icBands.read(in);
        this.classBands = new ClassBands(this);
        this.classBands.read(in);
        this.bcBands = new BcBands(this);
        this.bcBands.read(in);
        this.fileBands = new FileBands(this);
        this.fileBands.read(in);
        this.fileBands.processFileBits();
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public void setLogStream(OutputStream logStream) {
        this.logPrintWriter = logStream != null ? new PrintWriter((Writer) new OutputStreamWriter(logStream, Charset.defaultCharset()), false) : null;
    }

    public void setPreRead(boolean value) {
        this.doPreRead = value;
    }

    public void unpack(InputStream inputStream, JarOutputStream out) throws IOException, Pack200Exception {
        unpackRead(inputStream);
        unpackProcess();
        unpackWrite(out);
    }

    void unpackProcess() throws IOException, Pack200Exception {
        if (this.internalBuffer != null) {
            readSegment(this.internalBuffer);
        }
        parseSegment();
    }

    void unpackRead(InputStream inputStream) throws IOException, Pack200Exception {
        InputStream in = Pack200UnpackerAdapter.newBoundedInputStream(inputStream);
        this.header = new SegmentHeader(this);
        this.header.read(in);
        int size = ((int) this.header.getArchiveSize()) - this.header.getArchiveSizeOffset();
        if (this.doPreRead && this.header.getArchiveSize() != 0) {
            byte[] data = new byte[size];
            in.read(data);
            this.internalBuffer = new BufferedInputStream(new ByteArrayInputStream(data));
            return;
        }
        readSegment(in);
    }

    void unpackWrite(JarOutputStream out) throws IOException {
        writeJar(out);
        IOUtils.close(this.logPrintWriter);
    }

    public void writeJar(JarOutputStream out) throws IOException {
        String[] fileName;
        int[] fileModtime;
        long[] fileSize;
        String[] fileName2 = this.fileBands.getFileName();
        int[] fileModtime2 = this.fileBands.getFileModtime();
        long[] fileSize2 = this.fileBands.getFileSize();
        byte[][] fileBits = this.fileBands.getFileBits();
        int classNum = 0;
        int numberOfFiles = this.header.getNumberOfFiles();
        long archiveModtime = this.header.getArchiveModtime();
        int i = 0;
        while (i < numberOfFiles) {
            String name = fileName2[i];
            long modtime = (fileModtime2[i] + archiveModtime) * 1000;
            boolean deflate = this.fileDeflate[i];
            JarEntry entry = new JarEntry(name);
            if (deflate) {
                fileName = fileName2;
                entry.setMethod(8);
                fileModtime = fileModtime2;
                fileSize = fileSize2;
            } else {
                fileName = fileName2;
                entry.setMethod(0);
                CRC32 crc = new CRC32();
                fileModtime = fileModtime2;
                if (this.fileIsClass[i]) {
                    crc.update(this.classFilesContents[classNum]);
                    fileSize = fileSize2;
                    entry.setSize(this.classFilesContents[classNum].length);
                } else {
                    fileSize = fileSize2;
                    crc.update(fileBits[i]);
                    entry.setSize(fileSize[i]);
                }
                entry.setCrc(crc.getValue());
            }
            entry.setTime(modtime - TimeZone.getDefault().getRawOffset());
            out.putNextEntry(entry);
            if (this.fileIsClass[i]) {
                entry.setSize(this.classFilesContents[classNum].length);
                out.write(this.classFilesContents[classNum]);
                classNum++;
            } else {
                entry.setSize(fileSize[i]);
                out.write(fileBits[i]);
            }
            i++;
            fileName2 = fileName;
            fileModtime2 = fileModtime;
            fileSize2 = fileSize;
        }
    }
}
