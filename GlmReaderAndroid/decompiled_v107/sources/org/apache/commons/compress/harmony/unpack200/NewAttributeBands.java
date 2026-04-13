package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.unpack200.bytecode.Attribute;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.harmony.unpack200.bytecode.NewAttribute;
import org.apache.commons.compress.utils.ParsingUtils;
import org.apache.logging.log4j.util.ProcessIdUtil;

/* loaded from: classes9.dex */
public class NewAttributeBands extends BandSet {
    private final AttributeLayout attributeLayout;
    protected List<AttributeLayoutElement> attributeLayoutElements;
    private int backwardsCallCount;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public interface AttributeLayoutElement {
        void addToAttribute(int i, NewAttribute newAttribute);

        void readBands(InputStream inputStream, int i) throws IOException, Pack200Exception;
    }

    /* loaded from: classes9.dex */
    public class Call extends LayoutElement {
        private Callable callable;
        private final int callableIndex;

        public Call(int callableIndex) {
            super();
            this.callableIndex = callableIndex;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int n, NewAttribute attribute) {
            this.callable.addNextToAttribute(attribute);
        }

        public Callable getCallable() {
            return this.callable;
        }

        public int getCallableIndex() {
            return this.callableIndex;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) {
            if (this.callableIndex > 0) {
                this.callable.addCount(count);
            }
        }

        public void setCallable(Callable callable) {
            this.callable = callable;
            if (this.callableIndex < 1) {
                callable.setBackwardsCallable();
            }
        }
    }

    /* loaded from: classes9.dex */
    public static class Callable implements AttributeLayoutElement {
        private final List<LayoutElement> body;
        private int count;
        private int index;
        private boolean isBackwardsCallable;
        private boolean isFirstCallable;

        public Callable(List<LayoutElement> body) {
            this.body = body;
        }

        public void addCount(int count) {
            this.count += count;
        }

        public void addNextToAttribute(NewAttribute attribute) {
            for (LayoutElement element : this.body) {
                element.addToAttribute(this.index, attribute);
            }
            this.index++;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int n, NewAttribute attribute) {
            if (this.isFirstCallable) {
                for (LayoutElement element : this.body) {
                    element.addToAttribute(this.index, attribute);
                }
                this.index++;
            }
        }

        public List<LayoutElement> getBody() {
            return this.body;
        }

        public boolean isBackwardsCallable() {
            return this.isBackwardsCallable;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            int count2;
            if (this.isFirstCallable) {
                count2 = count + this.count;
            } else {
                count2 = this.count;
            }
            for (LayoutElement element : this.body) {
                element.readBands(in, count2);
            }
        }

        public void setBackwardsCallable() {
            this.isBackwardsCallable = true;
        }

        public void setFirstCallable(boolean isFirstCallable) {
            this.isFirstCallable = isFirstCallable;
        }
    }

    /* loaded from: classes9.dex */
    public class Integral extends LayoutElement {
        private int[] band;
        private final String tag;

        public Integral(String tag) {
            super();
            this.tag = tag;
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int n, NewAttribute attribute) {
            char c;
            int value = this.band[n];
            String str = this.tag;
            switch (str.hashCode()) {
                case 66:
                    if (str.equals("B")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 72:
                    if (str.equals("H")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 73:
                    if (str.equals("I")) {
                        c = 6;
                        break;
                    }
                    c = 65535;
                    break;
                case 86:
                    if (str.equals("V")) {
                        c = '\t';
                        break;
                    }
                    c = 65535;
                    break;
                case 2236:
                    if (str.equals("FB")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 2242:
                    if (str.equals("FH")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 2243:
                    if (str.equals("FI")) {
                        c = 7;
                        break;
                    }
                    c = 65535;
                    break;
                case 2256:
                    if (str.equals("FV")) {
                        c = '\n';
                        break;
                    }
                    c = 65535;
                    break;
                case 2639:
                    if (str.equals("SB")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 2645:
                    if (str.equals("SH")) {
                        c = 5;
                        break;
                    }
                    c = 65535;
                    break;
                case 2646:
                    if (str.equals("SI")) {
                        c = '\b';
                        break;
                    }
                    c = 65535;
                    break;
                case 2659:
                    if (str.equals("SV")) {
                        c = 11;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            switch (c) {
                case 0:
                case 1:
                    attribute.addInteger(1, value);
                    return;
                case 2:
                    attribute.addInteger(1, (byte) value);
                    return;
                case 3:
                case 4:
                    attribute.addInteger(2, value);
                    return;
                case 5:
                    attribute.addInteger(2, (short) value);
                    return;
                case 6:
                case 7:
                case '\b':
                    attribute.addInteger(4, value);
                    return;
                case '\t':
                case '\n':
                case 11:
                    return;
                default:
                    if (this.tag.startsWith("PO")) {
                        char uintType = this.tag.substring(2).toCharArray()[0];
                        attribute.addBCOffset(getLength(uintType), value);
                        return;
                    }
                    if (this.tag.startsWith("P")) {
                        char uintType2 = this.tag.substring(1).toCharArray()[0];
                        attribute.addBCIndex(getLength(uintType2), value);
                        return;
                    } else {
                        if (!this.tag.startsWith("OS")) {
                            if (this.tag.startsWith("O")) {
                                char uintType3 = this.tag.substring(1).toCharArray()[0];
                                attribute.addBCLength(getLength(uintType3), value);
                                return;
                            }
                            return;
                        }
                        char uintType4 = this.tag.substring(2).toCharArray()[0];
                        int length = getLength(uintType4);
                        switch (length) {
                            case 1:
                                value = (byte) value;
                                break;
                            case 2:
                                value = (short) value;
                                break;
                        }
                        attribute.addBCLength(length, value);
                        return;
                    }
            }
        }

        public String getTag() {
            return this.tag;
        }

        int getValue(int index) {
            return this.band[index];
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            this.band = NewAttributeBands.this.decodeBandInt(NewAttributeBands.this.attributeLayout.getName() + "_" + this.tag, in, NewAttributeBands.this.getCodec(this.tag), count);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static abstract class LayoutElement implements AttributeLayoutElement {
        private LayoutElement() {
        }

        protected int getLength(char uintType) {
            switch (uintType) {
                case 'B':
                    return 1;
                case 'H':
                    return 2;
                case 'I':
                    return 4;
                case 'V':
                    return 0;
                default:
                    return 0;
            }
        }
    }

    /* loaded from: classes9.dex */
    public class Reference extends LayoutElement {
        private Object band;
        private final int length;
        private final String tag;

        public Reference(String tag) {
            super();
            this.tag = tag;
            this.length = getLength(tag.charAt(tag.length() - 1));
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int n, NewAttribute attribute) {
            if (this.tag.startsWith("KI")) {
                attribute.addToBody(this.length, ((CPInteger[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("KJ")) {
                attribute.addToBody(this.length, ((CPLong[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("KF")) {
                attribute.addToBody(this.length, ((CPFloat[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("KD")) {
                attribute.addToBody(this.length, ((CPDouble[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("KS")) {
                attribute.addToBody(this.length, ((CPString[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("RC")) {
                attribute.addToBody(this.length, ((CPClass[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("RS")) {
                attribute.addToBody(this.length, ((CPUTF8[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("RD")) {
                attribute.addToBody(this.length, ((CPNameAndType[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("RF")) {
                attribute.addToBody(this.length, ((CPFieldRef[]) this.band)[n]);
                return;
            }
            if (this.tag.startsWith("RM")) {
                attribute.addToBody(this.length, ((CPMethodRef[]) this.band)[n]);
            } else if (this.tag.startsWith("RI")) {
                attribute.addToBody(this.length, ((CPInterfaceMethodRef[]) this.band)[n]);
            } else if (this.tag.startsWith("RU")) {
                attribute.addToBody(this.length, ((CPUTF8[]) this.band)[n]);
            }
        }

        public String getTag() {
            return this.tag;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            if (this.tag.startsWith("KI")) {
                this.band = NewAttributeBands.this.parseCPIntReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("KJ")) {
                this.band = NewAttributeBands.this.parseCPLongReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("KF")) {
                this.band = NewAttributeBands.this.parseCPFloatReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("KD")) {
                this.band = NewAttributeBands.this.parseCPDoubleReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("KS")) {
                this.band = NewAttributeBands.this.parseCPStringReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("RC")) {
                this.band = NewAttributeBands.this.parseCPClassReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("RS")) {
                this.band = NewAttributeBands.this.parseCPSignatureReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("RD")) {
                this.band = NewAttributeBands.this.parseCPDescriptorReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("RF")) {
                this.band = NewAttributeBands.this.parseCPFieldRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
                return;
            }
            if (this.tag.startsWith("RM")) {
                this.band = NewAttributeBands.this.parseCPMethodRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
            } else if (this.tag.startsWith("RI")) {
                this.band = NewAttributeBands.this.parseCPInterfaceMethodRefReferences(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
            } else if (this.tag.startsWith("RU")) {
                this.band = NewAttributeBands.this.parseCPUTF8References(NewAttributeBands.this.attributeLayout.getName(), in, Codec.UNSIGNED5, count);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class Replication extends LayoutElement {
        private final Integral countElement;
        private final List<LayoutElement> layoutElements;

        public Replication(String tag, String contents) throws IOException {
            super();
            this.layoutElements = new ArrayList();
            this.countElement = new Integral(tag);
            StringReader stream = new StringReader(contents);
            while (true) {
                LayoutElement e = NewAttributeBands.this.readNextLayoutElement(stream);
                if (e != null) {
                    this.layoutElements.add(e);
                } else {
                    return;
                }
            }
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int index, NewAttribute attribute) {
            this.countElement.addToAttribute(index, attribute);
            int offset = 0;
            for (int i = 0; i < index; i++) {
                offset += this.countElement.getValue(i);
            }
            long numElements = this.countElement.getValue(index);
            for (int i2 = offset; i2 < offset + numElements; i2++) {
                for (LayoutElement layoutElement : this.layoutElements) {
                    layoutElement.addToAttribute(i2, attribute);
                }
            }
        }

        public Integral getCountElement() {
            return this.countElement;
        }

        public List<LayoutElement> getLayoutElements() {
            return this.layoutElements;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            this.countElement.readBands(in, count);
            int arrayCount = 0;
            for (int i = 0; i < count; i++) {
                arrayCount += this.countElement.getValue(i);
            }
            for (LayoutElement layoutElement : this.layoutElements) {
                layoutElement.readBands(in, arrayCount);
            }
        }
    }

    /* loaded from: classes9.dex */
    public class Union extends LayoutElement {
        private int[] caseCounts;
        private final List<LayoutElement> defaultCaseBody;
        private int defaultCount;
        private final List<UnionCase> unionCases;
        private final Integral unionTag;

        public Union(String tag, List<UnionCase> unionCases, List<LayoutElement> body) {
            super();
            this.unionTag = new Integral(tag);
            this.unionCases = unionCases;
            this.defaultCaseBody = body;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int n, NewAttribute attribute) {
            this.unionTag.addToAttribute(n, attribute);
            int offset = 0;
            int[] tagBand = this.unionTag.band;
            int tag = this.unionTag.getValue(n);
            boolean defaultCase = true;
            for (UnionCase unionCase : this.unionCases) {
                if (unionCase.hasTag(tag)) {
                    defaultCase = false;
                    for (int j = 0; j < n; j++) {
                        if (unionCase.hasTag(tagBand[j])) {
                            offset++;
                        }
                    }
                    unionCase.addToAttribute(offset, attribute);
                }
            }
            if (defaultCase) {
                int defaultOffset = 0;
                for (int j2 = 0; j2 < n; j2++) {
                    boolean found = false;
                    Iterator<UnionCase> it = this.unionCases.iterator();
                    while (it.hasNext()) {
                        if (it.next().hasTag(tagBand[j2])) {
                            found = true;
                        }
                    }
                    if (!found) {
                        defaultOffset++;
                    }
                }
                if (this.defaultCaseBody != null) {
                    for (LayoutElement element : this.defaultCaseBody) {
                        element.addToAttribute(defaultOffset, attribute);
                    }
                }
            }
        }

        public List<LayoutElement> getDefaultCaseBody() {
            return this.defaultCaseBody;
        }

        public List<UnionCase> getUnionCases() {
            return this.unionCases;
        }

        public Integral getUnionTag() {
            return this.unionTag;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            int i;
            this.unionTag.readBands(in, count);
            int[] values = this.unionTag.band;
            this.caseCounts = new int[this.unionCases.size()];
            int i2 = 0;
            while (true) {
                i = 0;
                if (i2 >= this.caseCounts.length) {
                    break;
                }
                UnionCase unionCase = this.unionCases.get(i2);
                int length = values.length;
                while (i < length) {
                    int value = values[i];
                    if (unionCase.hasTag(value)) {
                        int[] iArr = this.caseCounts;
                        iArr[i2] = iArr[i2] + 1;
                    }
                    i++;
                }
                unionCase.readBands(in, this.caseCounts[i2]);
                i2++;
            }
            int i3 = values.length;
            while (i < i3) {
                int value2 = values[i];
                boolean found = false;
                Iterator<UnionCase> it = this.unionCases.iterator();
                while (it.hasNext()) {
                    if (it.next().hasTag(value2)) {
                        found = true;
                    }
                }
                if (!found) {
                    this.defaultCount++;
                }
                i++;
            }
            if (this.defaultCaseBody != null) {
                for (LayoutElement element : this.defaultCaseBody) {
                    element.readBands(in, this.defaultCount);
                }
            }
        }
    }

    /* loaded from: classes9.dex */
    public class UnionCase extends LayoutElement {
        private List<LayoutElement> body;
        private final List<Integer> tags;

        public UnionCase(List<Integer> tags) {
            super();
            this.tags = tags;
        }

        public UnionCase(List<Integer> tags, List<LayoutElement> body) {
            super();
            this.tags = tags;
            this.body = body;
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void addToAttribute(int index, NewAttribute attribute) {
            if (this.body != null) {
                for (LayoutElement element : this.body) {
                    element.addToAttribute(index, attribute);
                }
            }
        }

        public List<LayoutElement> getBody() {
            return this.body == null ? Collections.EMPTY_LIST : this.body;
        }

        public boolean hasTag(int i) {
            return this.tags.contains(Integer.valueOf(i));
        }

        public boolean hasTag(long l) {
            return this.tags.contains(Integer.valueOf((int) l));
        }

        @Override // org.apache.commons.compress.harmony.unpack200.NewAttributeBands.AttributeLayoutElement
        public void readBands(InputStream in, int count) throws IOException, Pack200Exception {
            if (this.body != null) {
                for (LayoutElement element : this.body) {
                    element.readBands(in, count);
                }
            }
        }
    }

    public NewAttributeBands(Segment segment, AttributeLayout attributeLayout) throws IOException {
        super(segment);
        this.attributeLayout = attributeLayout;
        parseLayout();
        attributeLayout.setBackwardsCallCount(this.backwardsCallCount);
    }

    public int getBackwardsCallCount() {
        return this.backwardsCallCount;
    }

    public BHSDCodec getCodec(String layoutElement) {
        if (layoutElement.indexOf(79) >= 0) {
            return Codec.BRANCH5;
        }
        if (layoutElement.indexOf(80) >= 0) {
            return Codec.BCI5;
        }
        if (layoutElement.indexOf(83) >= 0 && !layoutElement.contains("KS") && !layoutElement.contains("RS")) {
            return Codec.SIGNED5;
        }
        if (layoutElement.indexOf(66) >= 0) {
            return Codec.BYTE1;
        }
        return Codec.UNSIGNED5;
    }

    private Attribute getOneAttribute(int index, List<AttributeLayoutElement> elements) {
        NewAttribute attribute = new NewAttribute(this.segment.getCpBands().cpUTF8Value(this.attributeLayout.getName()), this.attributeLayout.getIndex());
        for (AttributeLayoutElement element : elements) {
            element.addToAttribute(index, attribute);
        }
        return attribute;
    }

    private StringReader getStreamUpToMatchingBracket(StringReader stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int foundBracket = -1;
        while (foundBracket != 0) {
            int read = stream.read();
            if (read == -1) {
                break;
            }
            char c = (char) read;
            if (c == ']') {
                foundBracket++;
            }
            if (c == '[') {
                foundBracket--;
            }
            if (foundBracket != 0) {
                sb.append(c);
            }
        }
        return new StringReader(sb.toString());
    }

    public List<Attribute> parseAttributes(InputStream in, int occurrenceCount) throws IOException, Pack200Exception {
        for (AttributeLayoutElement element : this.attributeLayoutElements) {
            element.readBands(in, occurrenceCount);
        }
        List<Attribute> attributes = new ArrayList<>(occurrenceCount);
        for (int i = 0; i < occurrenceCount; i++) {
            attributes.add(getOneAttribute(i, this.attributeLayoutElements));
        }
        return attributes;
    }

    private void parseLayout() throws IOException {
        if (this.attributeLayoutElements == null) {
            this.attributeLayoutElements = new ArrayList();
            StringReader stream = new StringReader(this.attributeLayout.getLayout());
            while (true) {
                AttributeLayoutElement e = readNextAttributeElement(stream);
                if (e != null) {
                    this.attributeLayoutElements.add(e);
                } else {
                    resolveCalls();
                    return;
                }
            }
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void read(InputStream in) throws IOException, Pack200Exception {
    }

    private List<LayoutElement> readBody(StringReader stream) throws IOException {
        List<LayoutElement> layoutElements = new ArrayList<>();
        while (true) {
            LayoutElement e = readNextLayoutElement(stream);
            if (e != null) {
                layoutElements.add(e);
            } else {
                return layoutElements;
            }
        }
    }

    private AttributeLayoutElement readNextAttributeElement(StringReader stream) throws IOException {
        stream.mark(1);
        int next = stream.read();
        if (next == -1) {
            return null;
        }
        if (next == 91) {
            return new Callable(readBody(getStreamUpToMatchingBracket(stream)));
        }
        stream.reset();
        return readNextLayoutElement(stream);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public LayoutElement readNextLayoutElement(StringReader stream) throws IOException {
        int nextChar = stream.read();
        if (nextChar == -1) {
            return null;
        }
        switch (nextChar) {
            case 40:
                int number = readNumber(stream).intValue();
                stream.read();
                return new Call(number);
            case 66:
            case 72:
            case 73:
            case 86:
                return new Integral(new String(new char[]{(char) nextChar}));
            case 70:
            case 83:
                return new Integral(new String(new char[]{(char) nextChar, (char) stream.read()}));
            case 75:
            case 82:
                StringBuilder string = new StringBuilder("").append((char) nextChar).append((char) stream.read());
                char nxt = (char) stream.read();
                string.append(nxt);
                if (nxt == 'N') {
                    string.append((char) stream.read());
                }
                return new Reference(string.toString());
            case 78:
                char uintType = (char) stream.read();
                stream.read();
                String str = readUpToMatchingBracket(stream);
                return new Replication("" + uintType, str);
            case 79:
                stream.mark(1);
                if (stream.read() != 83) {
                    stream.reset();
                    return new Integral("O" + ((char) stream.read()));
                }
                return new Integral("OS" + ((char) stream.read()));
            case 80:
                stream.mark(1);
                if (stream.read() != 79) {
                    stream.reset();
                    return new Integral("P" + ((char) stream.read()));
                }
                return new Integral("PO" + ((char) stream.read()));
            case 84:
                String intType = "" + ((char) stream.read());
                if (intType.equals("S")) {
                    intType = intType + ((char) stream.read());
                }
                List<UnionCase> unionCases = new ArrayList<>();
                while (true) {
                    UnionCase c = readNextUnionCase(stream);
                    if (c != null) {
                        unionCases.add(c);
                    } else {
                        stream.read();
                        stream.read();
                        stream.read();
                        List<LayoutElement> body = null;
                        stream.mark(1);
                        char next = (char) stream.read();
                        if (next != ']') {
                            stream.reset();
                            body = readBody(getStreamUpToMatchingBracket(stream));
                        }
                        return new Union(intType, unionCases, body);
                    }
                }
            default:
                return null;
        }
    }

    private UnionCase readNextUnionCase(StringReader stream) throws IOException {
        Integer nextTag;
        stream.mark(2);
        stream.read();
        int next = stream.read();
        char ch = (char) next;
        if (ch == ')' || next == -1) {
            stream.reset();
            return null;
        }
        stream.reset();
        stream.read();
        List<Integer> tags = new ArrayList<>();
        do {
            nextTag = readNumber(stream);
            if (nextTag != null) {
                tags.add(nextTag);
                stream.read();
            }
        } while (nextTag != null);
        stream.read();
        stream.mark(1);
        char ch2 = (char) stream.read();
        if (ch2 == ']') {
            return new UnionCase(tags);
        }
        stream.reset();
        return new UnionCase(tags, readBody(getStreamUpToMatchingBracket(stream)));
    }

    private Integer readNumber(StringReader stream) throws IOException {
        stream.mark(1);
        char first = (char) stream.read();
        boolean negative = first == '-';
        if (!negative) {
            stream.reset();
        }
        stream.mark(100);
        int length = 0;
        while (true) {
            int i = stream.read();
            if (i == -1 || !Character.isDigit((char) i)) {
                break;
            }
            length++;
        }
        stream.reset();
        if (length == 0) {
            return null;
        }
        char[] digits = new char[length];
        int read = stream.read(digits);
        if (read == digits.length) {
            return Integer.valueOf(ParsingUtils.parseIntValue((negative ? ProcessIdUtil.DEFAULT_PROCESSID : "") + new String(digits)));
        }
        throw new IOException("Error reading from the input stream");
    }

    private String readUpToMatchingBracket(StringReader stream) throws IOException {
        StringBuilder sb = new StringBuilder();
        int foundBracket = -1;
        while (foundBracket != 0) {
            int read = stream.read();
            if (read == -1) {
                break;
            }
            char c = (char) read;
            if (c == ']') {
                foundBracket++;
            }
            if (c == '[') {
                foundBracket--;
            }
            if (foundBracket != 0) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    private void resolveCalls() {
        int backwardsCalls = 0;
        for (int i = 0; i < this.attributeLayoutElements.size(); i++) {
            AttributeLayoutElement element = this.attributeLayoutElements.get(i);
            if (element instanceof Callable) {
                Callable callable = (Callable) element;
                if (i == 0) {
                    callable.setFirstCallable(true);
                }
                for (LayoutElement layoutElement : callable.body) {
                    backwardsCalls += resolveCallsForElement(i, callable, layoutElement);
                }
            }
        }
        this.backwardsCallCount = backwardsCalls;
    }

    private int resolveCallsForElement(int i, Callable currentCallable, LayoutElement layoutElement) {
        int backwardsCalls = 0;
        if (layoutElement instanceof Call) {
            Call call = (Call) layoutElement;
            int index = call.callableIndex;
            if (index == 0) {
                int backwardsCalls2 = 0 + 1;
                call.setCallable(currentCallable);
                return backwardsCalls2;
            }
            if (index > 0) {
                for (int k = i + 1; k < this.attributeLayoutElements.size(); k++) {
                    AttributeLayoutElement el = this.attributeLayoutElements.get(k);
                    if ((el instanceof Callable) && index - 1 == 0) {
                        call.setCallable((Callable) el);
                        return 0;
                    }
                }
                return 0;
            }
            int backwardsCalls3 = 0 + 1;
            for (int k2 = i - 1; k2 >= 0; k2--) {
                AttributeLayoutElement el2 = this.attributeLayoutElements.get(k2);
                if ((el2 instanceof Callable) && (index = index + 1) == 0) {
                    call.setCallable((Callable) el2);
                    return backwardsCalls3;
                }
            }
            return backwardsCalls3;
        }
        if (layoutElement instanceof Replication) {
            List<LayoutElement> children = ((Replication) layoutElement).layoutElements;
            for (LayoutElement child : children) {
                backwardsCalls += resolveCallsForElement(i, currentCallable, child);
            }
            return backwardsCalls;
        }
        return 0;
    }

    public void setBackwardsCalls(int[] backwardsCalls) throws IOException {
        int index = 0;
        parseLayout();
        for (AttributeLayoutElement element : this.attributeLayoutElements) {
            if ((element instanceof Callable) && ((Callable) element).isBackwardsCallable()) {
                ((Callable) element).addCount(backwardsCalls[index]);
                index++;
            }
        }
    }

    @Override // org.apache.commons.compress.harmony.unpack200.BandSet
    public void unpack() throws IOException, Pack200Exception {
    }
}
