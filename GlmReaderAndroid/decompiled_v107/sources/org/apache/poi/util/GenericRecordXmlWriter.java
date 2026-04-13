package org.apache.poi.util;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.geom.Dimension2D;
import java.awt.geom.Path2D;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
import java.util.stream.Collectors;
import java.util.stream.Stream;
import kotlin.text.Typography;
import org.apache.commons.io.output.NullOutputStream;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.poi.common.usermodel.GenericRecord;
import org.apache.poi.util.GenericRecordJsonWriter;
import org.apache.poi.util.GenericRecordUtil;

/* loaded from: classes10.dex */
public class GenericRecordXmlWriter implements Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String TABS;
    private static final String ZEROS = "0000000000000000";
    private final PrintWriter fw;
    private static final Pattern ESC_CHARS = Pattern.compile("[<>&'\"\\p{Cntrl}]");
    private static final List<Map.Entry<Class<?>, GenericRecordHandler>> handler = new ArrayList();
    private int indent = 0;
    private boolean withComments = true;
    private int childIndex = 0;
    private boolean attributePhase = true;

    /* JADX INFO: Access modifiers changed from: protected */
    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface GenericRecordHandler {
        boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj);
    }

    static {
        char[] t = new char[255];
        Arrays.fill(t, '\t');
        TABS = new String(t);
        handler(String.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda17
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printObject(str, obj);
            }
        });
        handler(Number.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda2
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printNumber(str, obj);
            }
        });
        handler(Boolean.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda3
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printBoolean(str, obj);
            }
        });
        handler(List.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda4
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printList(str, obj);
            }
        });
        handler(GenericRecordUtil.AnnotatedFlag.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda5
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printAnnotatedFlag(str, obj);
            }
        });
        handler(byte[].class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda6
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printBytes(str, obj);
            }
        });
        handler(Point2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda7
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printPoint(str, obj);
            }
        });
        handler(Dimension2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda8
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printDimension(str, obj);
            }
        });
        handler(Rectangle2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda9
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printRectangle(str, obj);
            }
        });
        handler(Path2D.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda10
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printPath(str, obj);
            }
        });
        handler(AffineTransform.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda18
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printAffineTransform(str, obj);
            }
        });
        handler(Color.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda19
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printColor(str, obj);
            }
        });
        handler(BufferedImage.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda20
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printBufferedImage(str, obj);
            }
        });
        handler(Array.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printArray(str, obj);
            }
        });
        handler(Object.class, new GenericRecordHandler() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda17
            @Override // org.apache.poi.util.GenericRecordXmlWriter.GenericRecordHandler
            public final boolean print(GenericRecordXmlWriter genericRecordXmlWriter, String str, Object obj) {
                return genericRecordXmlWriter.printObject(str, obj);
            }
        });
    }

    private static void handler(Class<?> c, GenericRecordHandler printer) {
        handler.add(new AbstractMap.SimpleEntry(c, printer));
    }

    public GenericRecordXmlWriter(File fileName) throws IOException {
        OutputStream os = "null".equals(fileName.getName()) ? NullOutputStream.INSTANCE : Files.newOutputStream(fileName.toPath(), new OpenOption[0]);
        this.fw = new PrintWriter(new OutputStreamWriter(os, StandardCharsets.UTF_8));
    }

    public GenericRecordXmlWriter(Appendable buffer) {
        this.fw = new PrintWriter(new GenericRecordJsonWriter.AppendableWriter(buffer));
    }

    public static String marshal(GenericRecord record) {
        return marshal(record, true);
    }

    public static String marshal(GenericRecord record, boolean withComments) {
        StringBuilder sb = new StringBuilder();
        try {
            GenericRecordXmlWriter w = new GenericRecordXmlWriter(sb);
            try {
                w.setWithComments(withComments);
                w.write(record);
                String sb2 = sb.toString();
                w.close();
                return sb2;
            } finally {
            }
        } catch (IOException e) {
            return "<record/>";
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
        write("record", record);
    }

    protected void write(String name, GenericRecord record) {
        String tabs = tabs();
        Enum<?> type = record.getGenericRecordType();
        String recordName = type != null ? type.name() : record.getClass().getSimpleName();
        this.fw.append((CharSequence) tabs);
        this.fw.append((CharSequence) "<").append((CharSequence) name).append((CharSequence) " type=\"");
        this.fw.append((CharSequence) recordName);
        this.fw.append((CharSequence) "\"");
        if (this.childIndex > 0) {
            this.fw.append((CharSequence) " index=\"");
            this.fw.print(this.childIndex);
            this.fw.append((CharSequence) "\"");
        }
        this.attributePhase = true;
        boolean hasComplex = writeProperties(record);
        this.attributePhase = false;
        if (hasComplex | writeChildren(record, hasComplex)) {
            this.fw.append((CharSequence) tabs);
            this.fw.println("</" + name + ">");
        } else {
            this.fw.println("/>");
        }
    }

    protected boolean writeProperties(GenericRecord record) {
        Map<String, Supplier<?>> prop = record.getGenericProperties();
        if (prop == null || prop.isEmpty()) {
            return false;
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        List<Map.Entry<String, Supplier<?>>> complex = (List) prop.entrySet().stream().flatMap(new Function() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda11
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return GenericRecordXmlWriter.this.writeProp((Map.Entry) obj);
            }
        }).collect(Collectors.toList());
        this.attributePhase = false;
        if (!complex.isEmpty()) {
            this.fw.println(">");
            this.indent++;
            complex.forEach(new Consumer() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda13
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericRecordXmlWriter.this.writeProp((Map.Entry) obj);
                }
            });
            this.indent--;
        }
        this.childIndex = oldChildIndex;
        return !complex.isEmpty();
    }

    protected boolean writeChildren(GenericRecord record, boolean hasComplexProperties) {
        List<? extends GenericRecord> list = record.getGenericChildren();
        if (list == null || list.isEmpty()) {
            return false;
        }
        if (!hasComplexProperties) {
            this.fw.print(">");
        }
        this.indent++;
        this.fw.println();
        this.fw.println(tabs() + "<children>");
        this.indent++;
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        list.forEach(new Consumer() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda12
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GenericRecordXmlWriter.this.m2562x1bb4caeb((GenericRecord) obj);
            }
        });
        this.childIndex = oldChildIndex;
        this.fw.println();
        this.indent--;
        this.fw.println(tabs() + "</children>");
        this.indent--;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeChildren$0$org-apache-poi-util-GenericRecordXmlWriter, reason: not valid java name */
    public /* synthetic */ void m2562x1bb4caeb(GenericRecord l) {
        writeValue("record", l);
        this.childIndex++;
    }

    public void writeError(String errorMsg) {
        printObject("error", errorMsg);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Stream<Map.Entry<String, Supplier<?>>> writeProp(Map.Entry<String, Supplier<?>> me) {
        final Object obj = me.getValue().get();
        if (obj == null) {
            return Stream.empty();
        }
        boolean isComplex = isComplex(obj);
        if (this.attributePhase == isComplex) {
            return isComplex ? Stream.of(new AbstractMap.SimpleEntry(me.getKey(), new Supplier() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return GenericRecordXmlWriter.lambda$writeProp$1(obj);
                }
            })) : Stream.empty();
        }
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        writeValue(me.getKey(), obj);
        this.childIndex = oldChildIndex;
        return Stream.empty();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$writeProp$1(Object obj) {
        return obj;
    }

    protected static boolean isComplex(Object obj) {
        return ((obj instanceof Number) || (obj instanceof Boolean) || (obj instanceof Character) || (obj instanceof String) || (obj instanceof Color) || (obj instanceof Enum)) ? false : true;
    }

    protected void writeValue(final String name, final Object value) {
        if (name == null) {
            throw new AssertionError();
        }
        if (value instanceof GenericRecord) {
            printGenericRecord(name, value);
        } else if (value != null) {
            if (name.endsWith(">")) {
                this.fw.print("\t");
            }
            handler.stream().filter(new Predicate() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda15
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean matchInstanceOrArray;
                    matchInstanceOrArray = GenericRecordXmlWriter.matchInstanceOrArray((Class) ((Map.Entry) obj).getKey(), value);
                    return matchInstanceOrArray;
                }
            }).findFirst().ifPresent(new Consumer() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda16
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    GenericRecordXmlWriter.this.m2563lambda$writeValue$3$orgapachepoiutilGenericRecordXmlWriter(name, value, (Map.Entry) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$writeValue$3$org-apache-poi-util-GenericRecordXmlWriter, reason: not valid java name */
    public /* synthetic */ void m2563lambda$writeValue$3$orgapachepoiutilGenericRecordXmlWriter(String name, Object value, Map.Entry h) {
        ((GenericRecordHandler) h.getValue()).print(this, name, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static boolean matchInstanceOrArray(Class<?> key, Object instance) {
        return key.isInstance(instance) || (Array.class.equals(key) && instance.getClass().isArray());
    }

    protected void openName(String name) {
        String name2 = name.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.print(StringUtils.SPACE + name2.replace(Typography.greater, Chars.SPACE).trim() + "=\"");
            return;
        }
        this.fw.print(tabs() + "<" + name2);
        if (name2.endsWith(">")) {
            this.fw.println();
        }
    }

    protected void closeName(String name) {
        String name2 = name.replace(">>", ">");
        if (this.attributePhase) {
            this.fw.append((CharSequence) "\"");
        } else if (name2.endsWith(">")) {
            this.fw.println(tabs() + "\t</" + name2);
        } else {
            this.fw.println("/>");
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printNumber(String name, Object o) {
        if (!this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        Number n = (Number) o;
        this.fw.print(n.toString());
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printBoolean(String name, Object o) {
        if (!this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        this.fw.write(((Boolean) o).toString());
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printList(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name + ">");
        int oldChildIndex = this.childIndex;
        this.childIndex = 0;
        ((List) o).forEach(new Consumer() { // from class: org.apache.poi.util.GenericRecordXmlWriter$$ExternalSyntheticLambda14
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                GenericRecordXmlWriter.this.m2561lambda$printList$4$orgapachepoiutilGenericRecordXmlWriter(obj);
            }
        });
        this.childIndex = oldChildIndex;
        closeName(name + ">");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$printList$4$org-apache-poi-util-GenericRecordXmlWriter, reason: not valid java name */
    public /* synthetic */ void m2561lambda$printList$4$orgapachepoiutilGenericRecordXmlWriter(Object e) {
        writeValue("item>", e);
        this.childIndex++;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printArray(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name + ">");
        int length = Array.getLength(o);
        int oldChildIndex = this.childIndex;
        int i = 0;
        while (true) {
            this.childIndex = i;
            if (this.childIndex < length) {
                writeValue("item>", Array.get(o, this.childIndex));
                i = this.childIndex + 1;
            } else {
                this.childIndex = oldChildIndex;
                closeName(name + ">");
                return true;
            }
        }
    }

    protected void printGenericRecord(String name, Object value) {
        write(name, (GenericRecord) value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printAnnotatedFlag(String name, Object o) {
        int len;
        if (this.attributePhase) {
            throw new AssertionError();
        }
        GenericRecordUtil.AnnotatedFlag af = (GenericRecordUtil.AnnotatedFlag) o;
        Number n = af.getValue().get();
        if (n instanceof Byte) {
            len = 2;
        } else if (n instanceof Short) {
            len = 4;
        } else if (n instanceof Integer) {
            len = 8;
        } else {
            len = 16;
        }
        openName(name);
        this.fw.print(" flag=\"0x");
        this.fw.print(trimHex(n.longValue(), len));
        this.fw.print('\"');
        if (this.withComments) {
            this.fw.print(" description=\"");
            this.fw.print(af.getDescription());
            this.fw.print("\"");
        }
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printBytes(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name + ">");
        this.fw.write(Base64.getEncoder().encodeToString((byte[]) o));
        closeName(name + ">");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printPoint(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        Point2D p = (Point2D) o;
        this.fw.println(" x=\"" + p.getX() + "\" y=\"" + p.getY() + "\"/>");
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printDimension(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        Dimension2D p = (Dimension2D) o;
        this.fw.println(" width=\"" + p.getWidth() + "\" height=\"" + p.getHeight() + "\"/>");
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printRectangle(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        Rectangle2D p = (Rectangle2D) o;
        this.fw.println(" x=\"" + p.getX() + "\" y=\"" + p.getY() + "\" width=\"" + p.getWidth() + "\" height=\"" + p.getHeight() + "\"/>");
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printPath(String name, Object o) {
        char c;
        if (!this.attributePhase) {
            openName(name + ">");
            PathIterator iter = ((Path2D) o).getPathIterator((AffineTransform) null);
            double[] pnts = new double[6];
            char c2 = 2;
            this.indent += 2;
            String t = tabs();
            this.indent -= 2;
            while (!iter.isDone()) {
                this.fw.print(t);
                int segType = iter.currentSegment(pnts);
                this.fw.print("<pathelement ");
                switch (segType) {
                    case 0:
                        c = c2;
                        this.fw.print("type=\"move\" x=\"" + pnts[0] + "\" y=\"" + pnts[1] + "\"");
                        break;
                    case 1:
                        c = c2;
                        this.fw.print("type=\"lineto\" x=\"" + pnts[0] + "\" y=\"" + pnts[1] + "\"");
                        break;
                    case 2:
                        c = c2;
                        this.fw.print("type=\"quad\" x1=\"" + pnts[0] + "\" y1=\"" + pnts[1] + "\" x2=\"" + pnts[c] + "\" y2=\"" + pnts[3] + "\"");
                        break;
                    case 3:
                        c = c2;
                        this.fw.print("type=\"cubic\" x1=\"" + pnts[0] + "\" y1=\"" + pnts[1] + "\" x2=\"" + pnts[c] + "\" y2=\"" + pnts[3] + "\" x3=\"" + pnts[4] + "\" y3=\"" + pnts[5] + "\"");
                        break;
                    case 4:
                        this.fw.print("type=\"close\"");
                        c = c2;
                        break;
                    default:
                        c = c2;
                        break;
                }
                this.fw.println("/>");
                iter.next();
                c2 = c;
            }
            closeName(name + ">");
            return true;
        }
        throw new AssertionError();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0042, code lost:
    
        if (r4.equals(">") != false) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean printObject(java.lang.String r9, java.lang.Object r10) {
        /*
            r8 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.StringBuilder r0 = r0.append(r9)
            java.lang.String r1 = ">"
            java.lang.StringBuilder r0 = r0.append(r1)
            java.lang.String r0 = r0.toString()
            r8.openName(r0)
            java.lang.String r0 = r10.toString()
            java.util.regex.Pattern r2 = org.apache.poi.util.GenericRecordXmlWriter.ESC_CHARS
            java.util.regex.Matcher r2 = r2.matcher(r0)
            r3 = 0
        L21:
            boolean r4 = r2.find()
            r5 = 1
            if (r4 == 0) goto Lbc
            java.io.PrintWriter r4 = r8.fw
            int r6 = r2.start()
            r4.write(r0, r3, r6)
            java.lang.String r4 = r2.group()
            int r6 = r4.hashCode()
            r7 = 0
            switch(r6) {
                case 34: goto L63;
                case 38: goto L59;
                case 39: goto L4f;
                case 60: goto L45;
                case 62: goto L3e;
                default: goto L3d;
            }
        L3d:
            goto L6d
        L3e:
            boolean r6 = r4.equals(r1)
            if (r6 == 0) goto L3d
            goto L6e
        L45:
            java.lang.String r5 = "<"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L3d
            r5 = r7
            goto L6e
        L4f:
            java.lang.String r5 = "'"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L3d
            r5 = 3
            goto L6e
        L59:
            java.lang.String r5 = "&"
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L3d
            r5 = 2
            goto L6e
        L63:
            java.lang.String r5 = "\""
            boolean r5 = r4.equals(r5)
            if (r5 == 0) goto L3d
            r5 = 4
            goto L6e
        L6d:
            r5 = -1
        L6e:
            switch(r5) {
                case 0: goto Lae;
                case 1: goto La6;
                case 2: goto L9e;
                case 3: goto L96;
                case 4: goto L8e;
                default: goto L71;
            }
        L71:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&#x"
            r5.write(r6)
            java.io.PrintWriter r5 = r8.fw
            int r6 = r4.codePointAt(r7)
            long r6 = (long) r6
            java.lang.String r6 = java.lang.Long.toHexString(r6)
            r5.write(r6)
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = ";"
            r5.write(r6)
            goto Lb6
        L8e:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&quot;"
            r5.write(r6)
            goto Lb6
        L96:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&apos;"
            r5.write(r6)
            goto Lb6
        L9e:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&amp;"
            r5.write(r6)
            goto Lb6
        La6:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&gt;"
            r5.write(r6)
            goto Lb6
        Lae:
            java.io.PrintWriter r5 = r8.fw
            java.lang.String r6 = "&lt;"
            r5.write(r6)
        Lb6:
            int r3 = r2.end()
            goto L21
        Lbc:
            java.io.PrintWriter r4 = r8.fw
            int r6 = r0.length()
            r4.append(r0, r3, r6)
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.StringBuilder r4 = r4.append(r9)
            java.lang.StringBuilder r1 = r4.append(r1)
            java.lang.String r1 = r1.toString()
            r8.closeName(r1)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.GenericRecordXmlWriter.printObject(java.lang.String, java.lang.Object):boolean");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printAffineTransform(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        AffineTransform xForm = (AffineTransform) o;
        this.fw.write("<" + name + " scaleX=\"" + xForm.getScaleX() + "\" shearX=\"" + xForm.getShearX() + "\" transX=\"" + xForm.getTranslateX() + "\" scaleY=\"" + xForm.getScaleY() + "\" shearY=\"" + xForm.getShearY() + "\" transY=\"" + xForm.getTranslateY() + "\"/>");
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printColor(String name, Object o) {
        if (!this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        int rgb = ((Color) o).getRGB();
        this.fw.print("0x" + trimHex(rgb, 8));
        closeName(name);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public boolean printBufferedImage(String name, Object o) {
        if (this.attributePhase) {
            throw new AssertionError();
        }
        openName(name);
        BufferedImage bi = (BufferedImage) o;
        this.fw.println(" width=\"" + bi.getWidth() + "\" height=\"" + bi.getHeight() + "\" bands=\"" + bi.getColorModel().getNumComponents() + "\"");
        closeName(name);
        return true;
    }

    protected String trimHex(long l, int size) {
        String b = Long.toHexString(l);
        int len = b.length();
        return ZEROS.substring(0, Math.max(0, size - len)) + b.substring(Math.max(0, len - size), len);
    }
}
