package org.apache.xmlbeans.soap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.xml.namespace.QName;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.XmlWhitespace;
import org.apache.xmlbeans.impl.values.XmlValueOutOfRangeException;

/* loaded from: classes11.dex */
public final class SOAPArrayType {
    private static final int[] EMPTY_INT_ARRAY = new int[0];
    private int[] _dimensions;
    private int[] _ranks;
    private QName _type;

    public boolean isSameRankAs(SOAPArrayType otherType) {
        return Arrays.equals(this._ranks, otherType._ranks) && this._dimensions.length == otherType._dimensions.length;
    }

    private static int[] internalParseCommaIntString(String csl) {
        List<String> dimStrings = new ArrayList<>();
        int i = 0;
        while (true) {
            int j = csl.indexOf(44, i);
            if (j < 0) {
                dimStrings.add(csl.substring(i));
                return dimStrings.stream().mapToInt(new ToIntFunction() { // from class: org.apache.xmlbeans.soap.SOAPArrayType$$ExternalSyntheticLambda0
                    @Override // java.util.function.ToIntFunction
                    public final int applyAsInt(Object obj) {
                        int collapseDimString;
                        collapseDimString = SOAPArrayType.collapseDimString((String) obj);
                        return collapseDimString;
                    }
                }).toArray();
            }
            dimStrings.add(csl.substring(i, j));
            i = j + 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int collapseDimString(String dimString2) {
        String dimString = XmlWhitespace.collapse(dimString2, 3);
        try {
            if (!"*".equals(dimString) && !dimString.isEmpty()) {
                return Integer.parseInt(dimString);
            }
            return -1;
        } catch (Exception e) {
            throw new XmlValueOutOfRangeException("Malformed integer in SOAP array index");
        }
    }

    public SOAPArrayType(String s, PrefixResolver m) {
        int firstbrace = s.indexOf(91);
        if (firstbrace < 0) {
            throw new XmlValueOutOfRangeException();
        }
        String firstpart = XmlWhitespace.collapse(s.substring(0, firstbrace), 3);
        int firstcolon = firstpart.indexOf(58);
        String prefix = firstcolon >= 0 ? firstpart.substring(0, firstcolon) : "";
        String uri = m.getNamespaceForPrefix(prefix);
        if (uri == null) {
            throw new XmlValueOutOfRangeException();
        }
        this._type = QNameHelper.forLNS(firstpart.substring(firstcolon + 1), uri);
        initDimensions(s, firstbrace);
    }

    public SOAPArrayType(QName name, String dimensions) {
        int firstbrace = dimensions.indexOf(91);
        if (firstbrace < 0) {
            this._type = name;
            this._ranks = EMPTY_INT_ARRAY;
            String[] dimStrings = XmlWhitespace.collapse(dimensions, 3).split(StringUtils.SPACE);
            for (int i = 0; i < dimStrings.length; i++) {
                String dimString = dimStrings[i];
                if (dimString.equals("*")) {
                    this._dimensions[i] = -1;
                } else {
                    try {
                        this._dimensions[i] = Integer.parseInt(dimStrings[i]);
                    } catch (Exception e) {
                        throw new XmlValueOutOfRangeException();
                    }
                }
            }
            return;
        }
        this._type = name;
        initDimensions(dimensions, firstbrace);
    }

    public SOAPArrayType(SOAPArrayType nested, int[] dimensions) {
        this._type = nested._type;
        this._ranks = new int[nested._ranks.length + 1];
        System.arraycopy(nested._ranks, 0, this._ranks, 0, nested._ranks.length);
        this._ranks[this._ranks.length - 1] = nested._dimensions.length;
        this._dimensions = new int[dimensions.length];
        System.arraycopy(dimensions, 0, this._dimensions, 0, dimensions.length);
    }

    private void initDimensions(String s, int firstbrace) {
        List<String> braces = new ArrayList<>();
        int lastbrace = -1;
        int i = firstbrace;
        while (i >= 0) {
            lastbrace = s.indexOf(93, i);
            if (lastbrace < 0) {
                throw new XmlValueOutOfRangeException();
            }
            braces.add(s.substring(i + 1, lastbrace));
            i = s.indexOf(91, lastbrace);
        }
        int i2 = lastbrace + 1;
        String trailer = s.substring(i2);
        if (!XmlWhitespace.isAllSpace(trailer)) {
            throw new XmlValueOutOfRangeException();
        }
        this._ranks = new int[braces.size() - 1];
        for (int i3 = 0; i3 < this._ranks.length; i3++) {
            String commas = braces.get(i3);
            int commacount = 0;
            for (int j = 0; j < commas.length(); j++) {
                char ch = commas.charAt(j);
                if (ch == ',') {
                    commacount++;
                } else if (!XmlWhitespace.isSpace(ch)) {
                    throw new XmlValueOutOfRangeException();
                }
            }
            this._ranks[i3] = commacount + 1;
        }
        int i4 = braces.size();
        this._dimensions = internalParseCommaIntString(braces.get(i4 - 1));
    }

    public QName getQName() {
        return this._type;
    }

    public int[] getRanks() {
        int[] result = new int[this._ranks.length];
        System.arraycopy(this._ranks, 0, result, 0, result.length);
        return result;
    }

    public int[] getDimensions() {
        int[] result = new int[this._dimensions.length];
        System.arraycopy(this._dimensions, 0, result, 0, result.length);
        return result;
    }

    public boolean containsNestedArrays() {
        return this._ranks.length > 0;
    }

    public String soap11DimensionString() {
        return soap11DimensionString(this._dimensions);
    }

    public String soap11DimensionString(int[] actualDimensions) {
        StringBuilder sb = new StringBuilder();
        for (int rank : this._ranks) {
            sb.append('[');
            for (int j = 1; j < rank; j++) {
                sb.append(',');
            }
            sb.append(']');
        }
        sb.append((String) IntStream.of(actualDimensions).mapToObj(new IntFunction() { // from class: org.apache.xmlbeans.soap.SOAPArrayType$$ExternalSyntheticLambda1
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SOAPArrayType.lambda$soap11DimensionString$0(i);
            }
        }).collect(Collectors.joining(CollectionUtils.COMMA, CollectionUtils.DEFAULT_TOSTRING_PREFIX, CollectionUtils.DEFAULT_TOSTRING_SUFFIX)));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$soap11DimensionString$0(int i) {
        return i >= 0 ? Integer.toString(i) : "";
    }

    private SOAPArrayType() {
    }

    public static SOAPArrayType newSoap12Array(QName itemType, String arraySize) {
        String[] dimStrings = XmlWhitespace.collapse(arraySize, 3).split(StringUtils.SPACE);
        int[] dimensions = new int[dimStrings.length];
        for (int i = 0; i < dimStrings.length; i++) {
            String dimString = dimStrings[i];
            if (i == 0 && dimString.equals("*")) {
                dimensions[i] = -1;
            } else {
                try {
                    dimensions[i] = Integer.parseInt(dimStrings[i]);
                } catch (Exception e) {
                    throw new XmlValueOutOfRangeException();
                }
            }
        }
        SOAPArrayType sot = new SOAPArrayType();
        sot._ranks = EMPTY_INT_ARRAY;
        sot._type = itemType;
        sot._dimensions = dimensions;
        return sot;
    }

    public String soap12DimensionString(int[] actualDimensions) {
        return (String) IntStream.of(actualDimensions).mapToObj(new IntFunction() { // from class: org.apache.xmlbeans.soap.SOAPArrayType$$ExternalSyntheticLambda2
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return SOAPArrayType.lambda$soap12DimensionString$1(i);
            }
        }).collect(Collectors.joining(StringUtils.SPACE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$soap12DimensionString$1(int i) {
        return i >= 0 ? Integer.toString(i) : "";
    }

    public SOAPArrayType nestedArrayType() {
        if (!containsNestedArrays()) {
            throw new IllegalStateException();
        }
        SOAPArrayType result = new SOAPArrayType();
        result._type = this._type;
        result._ranks = new int[this._ranks.length - 1];
        System.arraycopy(this._ranks, 0, result._ranks, 0, result._ranks.length);
        result._dimensions = new int[this._ranks[this._ranks.length - 1]];
        Arrays.fill(result._dimensions, -1);
        return result;
    }

    public int hashCode() {
        return this._type.hashCode() + this._dimensions.length + this._ranks.length + (this._dimensions.length != 0 ? this._dimensions[0] : 0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SOAPArrayType)) {
            return false;
        }
        SOAPArrayType sat = (SOAPArrayType) obj;
        return this._type.equals(sat._type) && Arrays.equals(this._ranks, sat._ranks) && Arrays.equals(this._dimensions, sat._dimensions);
    }
}
