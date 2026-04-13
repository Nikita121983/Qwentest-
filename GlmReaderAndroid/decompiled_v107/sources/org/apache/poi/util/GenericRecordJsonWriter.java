package org.apache.poi.util;

import androidx.core.os.EnvironmentCompat;
import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.PackedColorModel;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang3.SystemProperties;
import org.apache.commons.math3.geometry.VectorFormat;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public class GenericRecordJsonWriter implements Closeable {
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    protected final AppendableWriter aw;
    protected final PrintWriter fw;
    private static final Pattern ESC_CHARS = Pattern.compile("[\"\\p{Cntrl}\\\\]");
    private static final String NL = System.getProperty(SystemProperties.LINE_SEPARATOR);
    private static final List<Map.Entry<Class<?>, GenericRecordHandler>> handler = new ArrayList();
    protected int indent = 0;
    protected boolean withComments = true;
    protected int childIndex = 0;

    /* JADX INFO: Access modifiers changed from: protected */
    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface GenericRecordHandler {
        boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj);
    }

    static {
        char[] t = new char[255];
        Arrays.fill(t, '\t');
        TABS = new String(t);
        handler(String.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printObject(str, obj);
            }
        });
        handler(Number.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda19
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printNumber(str, obj);
            }
        });
        handler(Boolean.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda20
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printBoolean(str, obj);
            }
        });
        handler(List.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printList(str, obj);
            }
        });
        handler(GenericRecord.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda2
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printGenericRecord(str, obj);
            }
        });
        handler(GenericRecordUtil.AnnotatedFlag.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda3
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printAnnotatedFlag(str, obj);
            }
        });
        handler(byte[].class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda4
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printBytes(str, obj);
            }
        });
        handler(Point2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda5
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printPoint(str, obj);
            }
        });
        handler(Dimension2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda6
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printDimension(str, obj);
            }
        });
        handler(Rectangle2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda7
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printRectangle(str, obj);
            }
        });
        handler(Path2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda14
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printPath(str, obj);
            }
        });
        handler(AffineTransform.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda15
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printAffineTransform(str, obj);
            }
        });
        handler(Color.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda16
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printColor(str, obj);
            }
        });
        handler(BufferedImage.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda17
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printImage(str, obj);
            }
        });
        handler(Array.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda18
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printArray(str, obj);
            }
        });
        handler(Object.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda13
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printObject(str, obj);
            }
        });
    }

    private static void handler(Class<?> c, GenericRecordHandler printer) {
        handler.add(new AbstractMap.SimpleEntry(c, printer));
    }

    public GenericRecordJsonWriter(File fileName) throws IOException {
        OutputStream os = "null".equals(fileName.getName()) ? NullOutputStream.INSTANCE : Files.newOutputStream(fileName.toPath(), new OpenOption[0]);
        this.aw = new AppendableWriter((Writer) new OutputStreamWriter(os, StandardCharsets.UTF_8));
        this.fw = new PrintWriter(this.aw);
    }

    public GenericRecordJsonWriter(Appendable buffer) {
        this.aw = new AppendableWriter(buffer);
        this.fw = new PrintWriter(this.aw);
    }

    public static String marshal(GenericRecord record) {
        return marshal(record, true);
    }

    public static String marshal(GenericRecord record, boolean withComments) {
        StringBuilder sb = new StringBuilder();
        try {
            GenericRecordJsonWriter w = new GenericRecordJsonWriter(sb);
            try {
                w.setWithComments(withComments);
                w.write(record);
                String sb2 = sb.toString();
                w.close();
                return sb2;
            } finally {
            }
        } catch (IOException e) {
            return "{}";
        }
    }

    public void setWithComments(boolean withComments) {
        this.withComments = withComments;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.fw.close();
    }

    protected String tabs() {
        return TABS.substring(0, Math.min(this.indent, TABS.length()));
    }

    public void write(GenericRecord record) {
        String tabs = tabs();
        Enum<?> type = record.getGenericRecordType();
        String recordName = type != null ? type.name() : record.getClass().getSimpleName();
        this.fw.append((CharSequence) tabs);
        this.fw.append((CharSequence) VectorFormat.DEFAULT_PREFIX);
        if (this.withComments) {
            this.fw.append((CharSequence) "   /* ");
            this.fw.append((CharSequence) recordName);
            if (this.childIndex > 0) {
                this.fw.append((CharSequence) " - index: ");
                this.fw.print(this.childIndex);
            }
            this.fw.append((CharSequence) " */");
        }
        this.fw.println();
        boolean hasProperties = writeProperties(record);
        this.fw.println();
        writeChildren(record, hasProperties);
        this.fw.append((CharSequence) tabs);
        this.fw.append((CharSequence) VectorFormat.DEFAULT_SUFFIX);
    }

    protected boolean writeProperties(GenericRecord record) {
        Map<String, Supplier<?>> prop = record.getGenericProperties();
        if (prop == null || prop.isEmpty()) {
            return false;
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        long cnt = prop.entrySet().stream().filter(new Predicate() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda0
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GenericRecordJsonWriter.this.m2559x5a5d6bce((Map.Entry) obj);
            }
        }).count();
        this.childIndex = oldChildIndex;
        return cnt > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeProperties$0$org-apache-poi-util-GenericRecordJsonWriter, reason: not valid java name */
    public /* synthetic */ boolean m2559x5a5d6bce(Map.Entry e) {
        return writeProp((String) e.getKey(), (Supplier) e.getValue());
    }

    protected boolean writeChildren(GenericRecord record, boolean hasProperties) {
        List<? extends GenericRecord> list = record.getGenericChildren();
        if (list == null || list.isEmpty()) {
            return false;
        }
        this.indent++;
        this.aw.setHoldBack(tabs() + (hasProperties ? ", " : "") + "\"children\": [" + NL);
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        long cnt = list.stream().filter(new Predicate() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda11
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return GenericRecordJsonWriter.this.m2558xddd5ca9b((GenericRecord) obj);
            }
        }).count();
        this.childIndex = oldChildIndex;
        this.aw.setHoldBack(null);
        if (cnt > 0) {
            this.fw.println();
            this.fw.println(tabs() + CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        }
        this.indent--;
        return cnt > 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeChildren$1$org-apache-poi-util-GenericRecordJsonWriter, reason: not valid java name */
    public /* synthetic */ boolean m2558xddd5ca9b(GenericRecord l) {
        if (writeValue(null, l)) {
            int i = this.childIndex + 1;
            this.childIndex = i;
            if (i > 0) {
                return true;
            }
        }
        return false;
    }

    public void writeError(String errorMsg) {
        this.fw.append((CharSequence) "{ error: ");
        printObject("error", errorMsg);
        this.fw.append((CharSequence) " }");
    }

    protected boolean writeProp(String str, Supplier<?> supplier) {
        StringBuilder append;
        String str2;
        boolean z = this.childIndex > 0;
        AppendableWriter appendableWriter = this.aw;
        if (z) {
            append = new StringBuilder().append(NL).append(tabs());
            str2 = "\t, ";
        } else {
            append = new StringBuilder().append(tabs());
            str2 = "\t  ";
        }
        appendableWriter.setHoldBack(append.append(str2).toString());
        int i = this.childIndex;
        this.childIndex = 0;
        boolean writeValue = writeValue(str, supplier.get());
        this.childIndex = i + (writeValue ? 1 : 0);
        this.aw.setHoldBack(null);
        return writeValue;
    }

    protected boolean writeValue(String name, final Object o) {
        if (this.childIndex > 0) {
            this.aw.setHoldBack(CollectionUtils.COMMA);
        }
        GenericRecordHandler grh = o == null ? new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda8
            @Override // org.apache.poi.util.GenericRecordJsonWriter.GenericRecordHandler
            public final boolean print(GenericRecordJsonWriter genericRecordJsonWriter, String str, Object obj) {
                return genericRecordJsonWriter.printNull(str, obj);
            }
        } : (GenericRecordHandler) handler.stream().filter(new Predicate() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda9
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                boolean matchInstanceOrArray;
                matchInstanceOrArray = GenericRecordJsonWriter.matchInstanceOrArray((Class) ((Map.Entry) obj).getKey(), o);
                return matchInstanceOrArray;
            }
        }).findFirst().map(new Function() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda10
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return (GenericRecordJsonWriter.GenericRecordHandler) ((Map.Entry) obj).getValue();
            }
        }).orElse(null);
        boolean result = grh != null && grh.print(this, name, o);
        this.aw.setHoldBack(null);
        return result;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean matchInstanceOrArray(Class<?> key, Object instance) {
        return key.isInstance(instance) || (Array.class.equals(key) && instance.getClass().isArray());
    }

    protected void printName(String name) {
        this.fw.print(name != null ? "\"" + name + "\": " : "");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printNull(String name, Object o) {
        printName(name);
        this.fw.write("null");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printNumber(String name, Object o) {
        int size;
        Number n = (Number) o;
        printName(name);
        if (o instanceof Float) {
            this.fw.print(n.floatValue());
            return true;
        }
        if (o instanceof Double) {
            this.fw.print(n.doubleValue());
            return true;
        }
        this.fw.print(n.longValue());
        if (n instanceof Byte) {
            size = 2;
        } else if (n instanceof Short) {
            size = 4;
        } else if (n instanceof Integer) {
            size = 8;
        } else if (n instanceof Long) {
            size = 16;
        } else {
            size = -1;
        }
        long l = n.longValue();
        if (this.withComments && size > 0 && (l < 0 || l > 9)) {
            this.fw.write(" /* 0x");
            this.fw.write(trimHex(l, size));
            this.fw.write(" */");
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printBoolean(String name, Object o) {
        printName(name);
        this.fw.write(((Boolean) o).toString());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printList(String name, Object o) {
        printName(name);
        this.fw.println(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        ((List) o).forEach(new Consumer() { // from class: org.apache.poi.util.GenericRecordJsonWriter$$ExternalSyntheticLambda12
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GenericRecordJsonWriter.this.m2557lambda$printList$3$orgapachepoiutilGenericRecordJsonWriter(obj);
            }
        });
        this.childIndex = oldChildIndex;
        this.fw.write(tabs() + "\t]");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$printList$3$org-apache-poi-util-GenericRecordJsonWriter, reason: not valid java name */
    public /* synthetic */ void m2557lambda$printList$3$orgapachepoiutilGenericRecordJsonWriter(Object e) {
        writeValue(null, e);
        this.childIndex++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printGenericRecord(String name, Object o) {
        printName(name);
        this.indent++;
        write((GenericRecord) o);
        this.indent--;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printAnnotatedFlag(String name, Object o) {
        printName(name);
        GenericRecordUtil.AnnotatedFlag af = (GenericRecordUtil.AnnotatedFlag) o;
        this.fw.print(af.getValue().get().longValue());
        if (this.withComments) {
            this.fw.write(" /* ");
            this.fw.write(af.getDescription());
            this.fw.write(" */ ");
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printBytes(String name, Object o) {
        printName(name);
        this.fw.write(34);
        this.fw.write(Base64.getEncoder().encodeToString((byte[]) o));
        this.fw.write(34);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printPoint(String name, Object o) {
        printName(name);
        Point2D p = (Point2D) o;
        this.fw.write("{ \"x\": " + p.getX() + ", \"y\": " + p.getY() + " }");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printDimension(String name, Object o) {
        printName(name);
        Dimension2D p = (Dimension2D) o;
        this.fw.write("{ \"width\": " + p.getWidth() + ", \"height\": " + p.getHeight() + " }");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printRectangle(String name, Object o) {
        printName(name);
        Rectangle2D p = (Rectangle2D) o;
        this.fw.write("{ \"x\": " + p.getX() + ", \"y\": " + p.getY() + ", \"width\": " + p.getWidth() + ", \"height\": " + p.getHeight() + " }");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printPath(String name, Object o) {
        char c;
        boolean isNext;
        printName(name);
        PathIterator iter = ((Path2D) o).getPathIterator((AffineTransform) null);
        double[] pnts = new double[6];
        this.fw.write(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
        char c2 = 2;
        this.indent += 2;
        String t = tabs();
        this.indent -= 2;
        boolean isNext2 = false;
        while (!iter.isDone()) {
            this.fw.println(isNext2 ? ", " : "");
            this.fw.print(t);
            int segType = iter.currentSegment(pnts);
            this.fw.append((CharSequence) "{ \"type\": ");
            switch (segType) {
                case 0:
                    c = c2;
                    isNext = true;
                    this.fw.write("\"move\", \"x\": " + pnts[0] + ", \"y\": " + pnts[1]);
                    break;
                case 1:
                    c = c2;
                    isNext = true;
                    this.fw.write("\"lineto\", \"x\": " + pnts[0] + ", \"y\": " + pnts[1]);
                    break;
                case 2:
                    c = c2;
                    isNext = true;
                    this.fw.write("\"quad\", \"x1\": " + pnts[0] + ", \"y1\": " + pnts[1] + ", \"x2\": " + pnts[c] + ", \"y2\": " + pnts[3]);
                    break;
                case 3:
                    c = c2;
                    isNext = true;
                    this.fw.write("\"cubic\", \"x1\": " + pnts[0] + ", \"y1\": " + pnts[1] + ", \"x2\": " + pnts[c] + ", \"y2\": " + pnts[3] + ", \"x3\": " + pnts[4] + ", \"y3\": " + pnts[5]);
                    break;
                case 4:
                    this.fw.write("\"close\"");
                    c = c2;
                    isNext = true;
                    break;
                default:
                    c = c2;
                    isNext = true;
                    break;
            }
            this.fw.append((CharSequence) " }");
            iter.next();
            c2 = c;
            isNext2 = isNext;
        }
        this.fw.write(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x004d, code lost:
    
        if (r4.equals(org.apache.commons.lang3.StringUtils.CR) != false) goto L30;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean printObject(java.lang.String r11, java.lang.Object r12) {
        /*
            r10 = this;
            r10.printName(r11)
            java.io.PrintWriter r0 = r10.fw
            r1 = 34
            r0.write(r1)
            java.lang.String r0 = r12.toString()
            java.util.regex.Pattern r2 = org.apache.poi.util.GenericRecordJsonWriter.ESC_CHARS
            java.util.regex.Matcher r2 = r2.matcher(r0)
            r3 = 0
        L15:
            boolean r4 = r2.find()
            r5 = 1
            if (r4 == 0) goto Ld0
            java.io.PrintWriter r4 = r10.fw
            int r6 = r2.start()
            r4.append(r0, r3, r6)
            java.lang.String r4 = r2.group()
            int r6 = r4.hashCode()
            r7 = 4
            r8 = 0
            switch(r6) {
                case 8: goto L6e;
                case 9: goto L64;
                case 10: goto L5a;
                case 12: goto L50;
                case 13: goto L47;
                case 34: goto L3d;
                case 92: goto L33;
                default: goto L32;
            }
        L32:
            goto L78
        L33:
            java.lang.String r5 = "\\"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = 5
            goto L79
        L3d:
            java.lang.String r5 = "\""
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = 6
            goto L79
        L47:
            java.lang.String r6 = "\r"
            boolean r6 = r4.equals(r6)
            if (r6 == 0) goto L32
            goto L79
        L50:
            java.lang.String r5 = "\f"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = r7
            goto L79
        L5a:
            java.lang.String r5 = "\n"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = r8
            goto L79
        L64:
            java.lang.String r5 = "\t"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = 2
            goto L79
        L6e:
            java.lang.String r5 = "\b"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L32
            r5 = 3
            goto L79
        L78:
            r5 = -1
        L79:
            switch(r5) {
                case 0: goto Lc2;
                case 1: goto Lba;
                case 2: goto Lb2;
                case 3: goto Laa;
                case 4: goto La2;
                case 5: goto L9a;
                case 6: goto L92;
                default: goto L7c;
            }
        L7c:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\u"
            r5.write(r6)
            java.io.PrintWriter r5 = r10.fw
            char r6 = r4.charAt(r8)
            long r8 = (long) r6
            java.lang.String r6 = trimHex(r8, r7)
            r5.write(r6)
            goto Lca
        L92:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\\""
            r5.write(r6)
            goto Lca
        L9a:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\\\\\"
            r5.write(r6)
            goto Lca
        La2:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\f"
            r5.write(r6)
            goto Lca
        Laa:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\b"
            r5.write(r6)
            goto Lca
        Lb2:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\t"
            r5.write(r6)
            goto Lca
        Lba:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\r"
            r5.write(r6)
            goto Lca
        Lc2:
            java.io.PrintWriter r5 = r10.fw
            java.lang.String r6 = "\\\\n"
            r5.write(r6)
        Lca:
            int r3 = r2.end()
            goto L15
        Ld0:
            java.io.PrintWriter r4 = r10.fw
            int r6 = r0.length()
            r4.append(r0, r3, r6)
            java.io.PrintWriter r4 = r10.fw
            r4.write(r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordJsonWriter.printObject(java.lang.String, java.lang.Object):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printAffineTransform(String name, Object o) {
        printName(name);
        AffineTransform xForm = (AffineTransform) o;
        this.fw.write("{ \"scaleX\": " + xForm.getScaleX() + ", \"shearX\": " + xForm.getShearX() + ", \"transX\": " + xForm.getTranslateX() + ", \"scaleY\": " + xForm.getScaleY() + ", \"shearY\": " + xForm.getShearY() + ", \"transY\": " + xForm.getTranslateY() + " }");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printColor(String name, Object o) {
        printName(name);
        int rgb = ((Color) o).getRGB();
        this.fw.print(rgb);
        if (this.withComments) {
            this.fw.write(" /* 0x");
            this.fw.write(trimHex(rgb, 8));
            this.fw.write(" */");
            return true;
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printArray(String name, Object o) {
        printName(name);
        this.fw.write(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
        int length = Array.getLength(o);
        int oldChildIndex = this.childIndex;
        int i = 0;
        while (true) {
            this.childIndex = i;
            if (this.childIndex < length) {
                writeValue(null, Array.get(o, this.childIndex));
                i = this.childIndex + 1;
            } else {
                this.childIndex = oldChildIndex;
                this.fw.write(tabs() + "\t]");
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printImage(String name, Object o) {
        BufferedImage img = (BufferedImage) o;
        String[] COLOR_SPACES = {"XYZ", "Lab", "Luv", "YCbCr", "Yxy", "RGB", "GRAY", "HSV", "HLS", "CMYK", "Unknown", "CMY", "Unknown"};
        String[] IMAGE_TYPES = {"CUSTOM", "INT_RGB", "INT_ARGB", "INT_ARGB_PRE", "INT_BGR", "3BYTE_BGR", "4BYTE_ABGR", "4BYTE_ABGR_PRE", "USHORT_565_RGB", "USHORT_555_RGB", "BYTE_GRAY", "USHORT_GRAY", "BYTE_BINARY", "BYTE_INDEXED"};
        printName(name);
        ColorModel cm = img.getColorModel();
        String colorType = cm instanceof IndexColorModel ? "indexed" : cm instanceof ComponentColorModel ? "component" : cm instanceof DirectColorModel ? "direct" : cm instanceof PackedColorModel ? "packed" : EnvironmentCompat.MEDIA_UNKNOWN;
        this.fw.write("{ \"width\": " + img.getWidth() + ", \"height\": " + img.getHeight() + ", \"type\": \"" + IMAGE_TYPES[img.getType()] + "\", \"colormodel\": \"" + colorType + "\", \"pixelBits\": " + cm.getPixelSize() + ", \"numComponents\": " + cm.getNumComponents() + ", \"colorSpace\": \"" + COLOR_SPACES[Math.min(cm.getColorSpace().getType(), 12)] + "\", \"transparency\": " + cm.getTransparency() + ", \"alpha\": " + cm.hasAlpha() + VectorFormat.DEFAULT_SUFFIX);
        return true;
    }

    static String trimHex(long l, int size) {
        String b = Long.toHexString(l);
        int len = b.length();
        return ZEROS.substring(0, Math.max(0, size - len)) + b.substring(Math.max(0, len - size), len);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes10.dex */
    public static class AppendableWriter extends Writer {
        private final Appendable appender;
        private String holdBack;
        private final Writer writer;

        /* JADX INFO: Access modifiers changed from: package-private */
        public AppendableWriter(Appendable buffer) {
            super(buffer);
            this.appender = buffer;
            this.writer = null;
        }

        AppendableWriter(Writer writer) {
            super(writer);
            this.appender = null;
            this.writer = writer;
        }

        void setHoldBack(String holdBack) {
            this.holdBack = holdBack;
        }

        @Override // java.io.Writer
        public void write(char[] cbuf, int off, int len) throws IOException {
            if (this.holdBack != null) {
                if (this.appender != null) {
                    this.appender.append(this.holdBack);
                } else if (this.writer != null) {
                    this.writer.write(this.holdBack);
                }
                this.holdBack = null;
            }
            if (this.appender != null) {
                this.appender.append(String.valueOf(cbuf), off, len);
            } else if (this.writer != null) {
                this.writer.write(cbuf, off, len);
            }
        }

        @Override // java.io.Writer, java.io.Flushable
        public void flush() throws IOException {
            Object o = this.appender != null ? this.appender : this.writer;
            if (o instanceof Flushable) {
                ((Flushable) o).flush();
            }
        }

        @Override // java.io.Writer, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            flush();
            Object o = this.appender != null ? this.appender : this.writer;
            if (o instanceof Closeable) {
                ((Closeable) o).close();
            }
        }
    }
}
