package org.apache.poi.hpsf;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.io.output.UnsynchronizedByteArrayOutputStream;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hpsf.wellknown.PropertyIDMap;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.CodePageUtil;
import org.apache.poi.util.IOUtils;
import org.apache.poi.util.LittleEndian;
import org.apache.poi.util.LittleEndianByteArrayInputStream;
import org.apache.poi.util.LittleEndianOutputStream;

/* loaded from: classes10.dex */
public class Section {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) Section.class);
    private final long _offset;
    private Map<Long, String> dictionary;
    private ClassID formatID;
    private final Map<Long, Property> properties;
    private final UnsynchronizedByteArrayOutputStream sectionBytes;
    private transient boolean wasNull;

    public Section() {
        this.sectionBytes = UnsynchronizedByteArrayOutputStream.builder().get();
        this.properties = new LinkedHashMap();
        this._offset = -1L;
    }

    public Section(Section s) {
        this.sectionBytes = UnsynchronizedByteArrayOutputStream.builder().get();
        this.properties = new LinkedHashMap();
        this._offset = -1L;
        setFormatID(s.getFormatID());
        for (Property p : s.properties.values()) {
            this.properties.put(Long.valueOf(p.getID()), new Property(p));
        }
        setDictionary(s.getDictionary());
    }

    public Section(byte[] src, int offset) throws UnsupportedEncodingException {
        int i;
        int codepage;
        int offFix;
        this.sectionBytes = UnsynchronizedByteArrayOutputStream.builder().get();
        this.properties = new LinkedHashMap();
        this.formatID = new ClassID(src, offset);
        int offFix2 = (int) LittleEndian.getUInt(src, offset + 16);
        if (src[offFix2] != 0) {
            i = offFix2;
        } else {
            int i2 = 0;
            while (i2 < 3 && src[offFix2] == 0) {
                i2++;
                offFix2++;
            }
            int i3 = 0;
            while (i3 < 3 && (src[offFix2 + 3] != 0 || src[offFix2 + 7] != 0 || src[offFix2 + 11] != 0)) {
                i3++;
                offFix2--;
            }
            i = offFix2;
        }
        this._offset = i;
        LittleEndianByteArrayInputStream leis = new LittleEndianByteArrayInputStream(src, i);
        int size = (int) Math.min(leis.readUInt(), src.length - this._offset);
        int propertyCount = (int) leis.readUInt();
        TreeBidiMap<Long, Long> offset2Id = new TreeBidiMap<>();
        for (int i4 = 0; i4 < propertyCount; i4++) {
            offset2Id.put((TreeBidiMap<Long, Long>) Long.valueOf(leis.readUInt()), Long.valueOf(leis.readUInt()));
        }
        long j = 1;
        Long cpOffset = offset2Id.getKey((Object) 1L);
        if (cpOffset == null) {
            codepage = -1;
        } else {
            leis.setReadIndex(Math.toIntExact(this._offset + cpOffset.longValue()));
            long type = leis.readUInt();
            if (type != 2) {
                throw new HPSFRuntimeException("Value type of property ID 1 is not VT_I2 but " + type + ".");
            }
            int codepage2 = leis.readUShort();
            setCodepage(codepage2);
            codepage = codepage2;
        }
        for (Map.Entry<Long, Long> me : offset2Id.entrySet()) {
            long off = me.getKey().longValue();
            long id = me.getValue().longValue();
            if (id != j) {
                long j2 = j;
                int pLen = propLen(offset2Id, Long.valueOf(off), size);
                leis.setReadIndex(Math.toIntExact(this._offset + off));
                if (id == 0) {
                    leis.mark(BZip2Constants.BASEBLOCKSIZE);
                    if (readDictionary(leis, pLen, codepage)) {
                        offFix = i;
                    } else {
                        leis.reset();
                        try {
                            offFix = i;
                            try {
                                setProperty(new Property(Math.max(31L, offset2Id.inverseBidiMap().lastKey().longValue()) + j2, leis, pLen, codepage));
                            } catch (RuntimeException e) {
                                LOG.atInfo().log("Dictionary fallback failed - ignoring property");
                                j = j2;
                                i = offFix;
                            }
                        } catch (RuntimeException e2) {
                            offFix = i;
                        }
                    }
                } else {
                    offFix = i;
                    setProperty(new Property(id, leis, pLen, codepage));
                }
                j = j2;
                i = offFix;
            }
        }
        this.sectionBytes.write(src, Math.toIntExact(this._offset), size);
        padSectionBytes();
    }

    private static int propLen(TreeBidiMap<Long, Long> offset2Id, Long entryOffset, long maxSize) {
        Long nextKey = offset2Id.nextKey((TreeBidiMap<Long, Long>) entryOffset);
        long begin = entryOffset.longValue();
        long end = nextKey != null ? nextKey.longValue() : maxSize;
        return Math.toIntExact(end - begin);
    }

    public ClassID getFormatID() {
        return this.formatID;
    }

    public void setFormatID(ClassID formatID) {
        this.formatID = formatID;
    }

    public void setFormatID(byte[] formatID) {
        ClassID fid = getFormatID();
        if (fid == null) {
            fid = new ClassID();
            setFormatID(fid);
        }
        fid.setBytes(formatID);
    }

    public long getOffset() {
        return this._offset;
    }

    public int getPropertyCount() {
        return this.properties.size();
    }

    public Property[] getProperties() {
        return (Property[]) this.properties.values().toArray(new Property[0]);
    }

    public void setProperties(Property[] properties) {
        this.properties.clear();
        for (Property p : properties) {
            setProperty(p);
        }
    }

    public Object getProperty(long id) {
        this.wasNull = !this.properties.containsKey(Long.valueOf(id));
        if (this.wasNull) {
            return null;
        }
        return this.properties.get(Long.valueOf(id)).getValue();
    }

    public void setProperty(int id, String value) {
        setProperty(id, 30L, value);
    }

    public void setProperty(int id, int value) {
        setProperty(id, 3L, Integer.valueOf(value));
    }

    public void setProperty(int id, long value) {
        setProperty(id, 20L, Long.valueOf(value));
    }

    public void setProperty(int id, boolean value) {
        setProperty(id, 11L, Boolean.valueOf(value));
    }

    public void setProperty(int id, long variantType, Object value) {
        setProperty(new Property(id, variantType, value));
    }

    public void setProperty(Property p) {
        Property old = this.properties.get(Long.valueOf(p.getID()));
        if (old == null || !old.equals(p)) {
            this.properties.put(Long.valueOf(p.getID()), p);
            this.sectionBytes.reset();
        }
    }

    public void setProperty(int id, Object value) {
        if (value instanceof String) {
            setProperty(id, (String) value);
            return;
        }
        if (value instanceof Long) {
            setProperty(id, ((Long) value).longValue());
            return;
        }
        if (value instanceof Integer) {
            setProperty(id, ((Integer) value).intValue());
            return;
        }
        if (value instanceof Short) {
            setProperty(id, ((Short) value).intValue());
        } else if (value instanceof Boolean) {
            setProperty(id, ((Boolean) value).booleanValue());
        } else {
            if (value instanceof java.util.Date) {
                setProperty(id, 64L, value);
                return;
            }
            throw new HPSFRuntimeException("HPSF does not support properties of type " + value.getClass().getName() + ".");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getPropertyIntValue(long id) {
        Object o = getProperty(id);
        if (o == null) {
            return 0;
        }
        if (!(o instanceof Long) && !(o instanceof Integer)) {
            throw new HPSFRuntimeException("This property is not an integer type, but " + o.getClass().getName() + ".");
        }
        Number i = (Number) o;
        return i.intValue();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getPropertyBooleanValue(int id) {
        Boolean b = (Boolean) getProperty(id);
        return b != null && b.booleanValue();
    }

    protected void setPropertyBooleanValue(int id, boolean value) {
        setProperty(id, 11L, Boolean.valueOf(value));
    }

    public int getSize() {
        int size = this.sectionBytes.size();
        if (size > 0) {
            return size;
        }
        try {
            return calcSize();
        } catch (HPSFRuntimeException ex) {
            throw ex;
        } catch (Exception ex2) {
            throw new HPSFRuntimeException(ex2);
        }
    }

    private int calcSize() throws WritingNotSupportedException, IOException {
        this.sectionBytes.reset();
        write(this.sectionBytes);
        padSectionBytes();
        return this.sectionBytes.size();
    }

    private void padSectionBytes() {
        byte[] padArray = {0, 0, 0};
        int pad = 3 & (4 - (this.sectionBytes.size() & 3));
        this.sectionBytes.write(padArray, 0, pad);
    }

    public boolean wasNull() {
        return this.wasNull;
    }

    public String getPIDString(long pid) {
        Map<Long, String> dic = getDictionary();
        if (dic == null || !dic.containsKey(Long.valueOf(pid))) {
            ClassID fmt = getFormatID();
            if (SummaryInformation.FORMAT_ID.equals(fmt)) {
                dic = PropertyIDMap.getSummaryInformationProperties();
            } else if (DocumentSummaryInformation.FORMAT_ID[0].equals(fmt)) {
                dic = PropertyIDMap.getDocumentSummaryInformationProperties();
            }
        }
        return (dic == null || !dic.containsKey(Long.valueOf(pid))) ? PropertyIDMap.UNDEFINED : dic.get(Long.valueOf(pid));
    }

    public void clear() {
        for (Property p : getProperties()) {
            removeProperty(p.getID());
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof Section)) {
            return false;
        }
        Section s = (Section) o;
        if (!s.getFormatID().equals(getFormatID())) {
            return false;
        }
        Set<Long> propIds = new HashSet<>(this.properties.keySet());
        propIds.addAll(s.properties.keySet());
        propIds.remove(0L);
        propIds.remove(1L);
        for (Long id : propIds) {
            Property p1 = this.properties.get(id);
            Property p2 = s.properties.get(id);
            if (p1 == null || !p1.equals(p2)) {
                return false;
            }
        }
        Map<Long, String> d1 = getDictionary();
        Map<Long, String> d2 = s.getDictionary();
        return (d1 == null && d2 == null) || (d1 != null && d1.equals(d2));
    }

    public void removeProperty(long id) {
        if (this.properties.remove(Long.valueOf(id)) != null) {
            this.sectionBytes.reset();
        }
    }

    public int write(OutputStream out) throws WritingNotSupportedException, IOException {
        if (this.sectionBytes.size() > 0) {
            this.sectionBytes.writeTo(out);
            return this.sectionBytes.size();
        }
        int codepage = getCodepage();
        if (codepage == -1) {
            LOG.atWarn().log("The codepage property is not set although a dictionary is present. Defaulting to ISO-8859-1.");
            codepage = 1252;
        }
        int[][] offsets = (int[][]) java.lang.reflect.Array.newInstance((Class<?>) Integer.TYPE, this.properties.size(), 2);
        UnsynchronizedByteArrayOutputStream bos = UnsynchronizedByteArrayOutputStream.builder().get();
        try {
            LittleEndianOutputStream leos = new LittleEndianOutputStream(bos);
            try {
                leos.writeInt(-1);
                leos.writeInt(this.properties.size());
                int propCnt = 0;
                Iterator<Property> it = this.properties.values().iterator();
                while (it.hasNext()) {
                    leos.writeUInt(it.next().getID());
                    offsets[propCnt][0] = bos.size();
                    leos.writeInt(-1);
                    propCnt++;
                }
                int propCnt2 = 0;
                for (Property p : this.properties.values()) {
                    int propCnt3 = propCnt2 + 1;
                    offsets[propCnt2][1] = bos.size();
                    if (p.getID() != 0) {
                        p.write(bos, codepage);
                    } else {
                        writeDictionary(bos, codepage);
                    }
                    propCnt2 = propCnt3;
                }
                byte[] result = bos.toByteArray();
                LittleEndian.putInt(result, 0, bos.size());
                for (int[] off : offsets) {
                    LittleEndian.putUInt(result, off[0], off[1]);
                }
                out.write(result);
                int size = bos.size();
                leos.close();
                if (bos != null) {
                    bos.close();
                }
                return size;
            } finally {
            }
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

    private boolean readDictionary(LittleEndianByteArrayInputStream leis, int length, int codepage) {
        long id;
        Map<Long, String> dic = new HashMap<>();
        long nrEntries = leis.readUInt();
        boolean isCorrupted = false;
        long id2 = -1;
        int i = 0;
        while (true) {
            if (i >= nrEntries) {
                break;
            }
            String errMsg = "The property set's dictionary contains bogus data. All dictionary entries starting with the one with ID " + id2 + " will be ignored.";
            long id3 = leis.readUInt();
            long sLength = leis.readUInt();
            int cp = codepage == -1 ? 1252 : codepage;
            long j = sLength - 1;
            long nrEntries2 = nrEntries;
            long nrEntries3 = cp == 1200 ? 2 : 1;
            int nrBytes = Math.toIntExact(j * nrEntries3);
            if (nrBytes > 16777215) {
                LOG.atWarn().log(errMsg);
                isCorrupted = true;
                break;
            }
            try {
                byte[] buf = IOUtils.safelyAllocate(nrBytes, CodePageString.getMaxRecordLength());
                leis.readFully(buf, 0, nrBytes);
                String str = CodePageUtil.getStringFromCodePage(buf, 0, nrBytes, cp);
                int pad = 1;
                if (cp == 1200) {
                    pad = ((4 - ((nrBytes + 2) & 3)) & 3) + 2;
                }
                boolean isCorrupted2 = isCorrupted;
                id = id3;
                try {
                    IOUtils.skipFully(leis, pad);
                    dic.put(Long.valueOf(id), str);
                    i++;
                    id2 = id;
                    isCorrupted = isCorrupted2;
                    nrEntries = nrEntries2;
                } catch (IOException e) {
                    ex = e;
                    LOG.atWarn().withThrowable(ex).log(errMsg);
                    isCorrupted = true;
                    setDictionary(dic);
                    return !isCorrupted;
                } catch (RuntimeException e2) {
                    ex = e2;
                    LOG.atWarn().withThrowable(ex).log(errMsg);
                    isCorrupted = true;
                    setDictionary(dic);
                    return !isCorrupted;
                }
            } catch (IOException | RuntimeException e3) {
                ex = e3;
                id = id3;
            }
        }
        setDictionary(dic);
        return !isCorrupted;
    }

    private void writeDictionary(OutputStream out, int codepage) throws IOException {
        byte[] padding = new byte[4];
        Map<Long, String> dic = getDictionary();
        LittleEndian.putUInt(dic.size(), out);
        int length = 4;
        for (Map.Entry<Long, String> ls : dic.entrySet()) {
            LittleEndian.putUInt(ls.getKey().longValue(), out);
            int length2 = length + 4;
            String value = ls.getValue() + "\u0000";
            byte[] bytes = CodePageUtil.getBytesInCodePage(value, codepage);
            int len = codepage == 1200 ? value.length() : bytes.length;
            LittleEndian.putUInt(len, out);
            out.write(bytes);
            int length3 = length2 + 4 + bytes.length;
            int pad = codepage == 1200 ? (4 - (length3 & 3)) & 3 : 0;
            out.write(padding, 0, pad);
            length = length3 + pad;
        }
        out.write(padding, 0, (4 - (length & 3)) & 3);
    }

    public void setDictionary(Map<Long, String> dictionary) throws IllegalPropertySetDataException {
        if (dictionary != null) {
            if (this.dictionary == null) {
                this.dictionary = new TreeMap();
            }
            this.dictionary.putAll(dictionary);
            int cp = getCodepage();
            if (cp == -1) {
                setCodepage(1252);
            }
            setProperty(0, -1L, dictionary);
            return;
        }
        removeProperty(0L);
        this.dictionary = null;
    }

    public int hashCode() {
        return Arrays.deepHashCode(new Object[]{getFormatID(), getProperties()});
    }

    public String toString() {
        return toString(null);
    }

    public String toString(PropertyIDMap idMap) {
        StringBuilder b = new StringBuilder();
        Property[] pa = getProperties();
        b.append("\n\n\n");
        b.append(getClass().getName());
        b.append('[');
        b.append("formatID: ");
        b.append(getFormatID());
        b.append(", offset: ");
        b.append(getOffset());
        b.append(", propertyCount: ");
        b.append(getPropertyCount());
        b.append(", size: ");
        b.append(getSize());
        b.append(", properties: [\n");
        int codepage = getCodepage();
        if (codepage == -1) {
            codepage = 1252;
        }
        for (Property p : pa) {
            b.append(p.toString(codepage, idMap));
            b.append(",\n");
        }
        b.append(']');
        b.append(']');
        return b.toString();
    }

    public Map<Long, String> getDictionary() {
        if (this.dictionary == null) {
            this.dictionary = (Map) getProperty(0L);
        }
        return this.dictionary;
    }

    public int getCodepage() {
        Integer codepage = (Integer) getProperty(1L);
        if (codepage == null) {
            return -1;
        }
        return codepage.intValue();
    }

    public void setCodepage(int codepage) {
        setProperty(1, 2L, Integer.valueOf(codepage));
    }
}
