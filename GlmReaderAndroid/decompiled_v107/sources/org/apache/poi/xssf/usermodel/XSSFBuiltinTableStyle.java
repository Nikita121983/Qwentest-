package org.apache.poi.xssf.usermodel;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.EnumMap;
import java.util.Map;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.io.input.UnsynchronizedByteArrayInputStream;
import org.apache.commons.io.output.StringBuilderWriter;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.poi.ooxml.util.DocumentHelper;
import org.apache.poi.ss.usermodel.DifferentialStyleProvider;
import org.apache.poi.ss.usermodel.TableStyle;
import org.apache.poi.ss.usermodel.TableStyleType;
import org.apache.poi.util.XMLHelper;
import org.apache.poi.xssf.model.StylesTable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes10.dex */
public enum XSSFBuiltinTableStyle {
    TableStyleDark1,
    TableStyleDark2,
    TableStyleDark3,
    TableStyleDark4,
    TableStyleDark5,
    TableStyleDark6,
    TableStyleDark7,
    TableStyleDark8,
    TableStyleDark9,
    TableStyleDark10,
    TableStyleDark11,
    TableStyleLight1,
    TableStyleLight2,
    TableStyleLight3,
    TableStyleLight4,
    TableStyleLight5,
    TableStyleLight6,
    TableStyleLight7,
    TableStyleLight8,
    TableStyleLight9,
    TableStyleLight10,
    TableStyleLight11,
    TableStyleLight12,
    TableStyleLight13,
    TableStyleLight14,
    TableStyleLight15,
    TableStyleLight16,
    TableStyleLight17,
    TableStyleLight18,
    TableStyleLight19,
    TableStyleLight20,
    TableStyleLight21,
    TableStyleMedium1,
    TableStyleMedium2,
    TableStyleMedium3,
    TableStyleMedium4,
    TableStyleMedium5,
    TableStyleMedium6,
    TableStyleMedium7,
    TableStyleMedium8,
    TableStyleMedium9,
    TableStyleMedium10,
    TableStyleMedium11,
    TableStyleMedium12,
    TableStyleMedium13,
    TableStyleMedium14,
    TableStyleMedium15,
    TableStyleMedium16,
    TableStyleMedium17,
    TableStyleMedium18,
    TableStyleMedium19,
    TableStyleMedium20,
    TableStyleMedium21,
    TableStyleMedium22,
    TableStyleMedium23,
    TableStyleMedium24,
    TableStyleMedium25,
    TableStyleMedium26,
    TableStyleMedium27,
    TableStyleMedium28,
    PivotStyleMedium1,
    PivotStyleMedium2,
    PivotStyleMedium3,
    PivotStyleMedium4,
    PivotStyleMedium5,
    PivotStyleMedium6,
    PivotStyleMedium7,
    PivotStyleMedium8,
    PivotStyleMedium9,
    PivotStyleMedium10,
    PivotStyleMedium11,
    PivotStyleMedium12,
    PivotStyleMedium13,
    PivotStyleMedium14,
    PivotStyleMedium15,
    PivotStyleMedium16,
    PivotStyleMedium17,
    PivotStyleMedium18,
    PivotStyleMedium19,
    PivotStyleMedium20,
    PivotStyleMedium21,
    PivotStyleMedium22,
    PivotStyleMedium23,
    PivotStyleMedium24,
    PivotStyleMedium25,
    PivotStyleMedium26,
    PivotStyleMedium27,
    PivotStyleMedium28,
    PivotStyleLight1,
    PivotStyleLight2,
    PivotStyleLight3,
    PivotStyleLight4,
    PivotStyleLight5,
    PivotStyleLight6,
    PivotStyleLight7,
    PivotStyleLight8,
    PivotStyleLight9,
    PivotStyleLight10,
    PivotStyleLight11,
    PivotStyleLight12,
    PivotStyleLight13,
    PivotStyleLight14,
    PivotStyleLight15,
    PivotStyleLight16,
    PivotStyleLight17,
    PivotStyleLight18,
    PivotStyleLight19,
    PivotStyleLight20,
    PivotStyleLight21,
    PivotStyleLight22,
    PivotStyleLight23,
    PivotStyleLight24,
    PivotStyleLight25,
    PivotStyleLight26,
    PivotStyleLight27,
    PivotStyleLight28,
    PivotStyleDark1,
    PivotStyleDark2,
    PivotStyleDark3,
    PivotStyleDark4,
    PivotStyleDark5,
    PivotStyleDark6,
    PivotStyleDark7,
    PivotStyleDark8,
    PivotStyleDark9,
    PivotStyleDark10,
    PivotStyleDark11,
    PivotStyleDark12,
    PivotStyleDark13,
    PivotStyleDark14,
    PivotStyleDark15,
    PivotStyleDark16,
    PivotStyleDark17,
    PivotStyleDark18,
    PivotStyleDark19,
    PivotStyleDark20,
    PivotStyleDark21,
    PivotStyleDark22,
    PivotStyleDark23,
    PivotStyleDark24,
    PivotStyleDark25,
    PivotStyleDark26,
    PivotStyleDark27,
    PivotStyleDark28;

    private static final Map<XSSFBuiltinTableStyle, TableStyle> styleMap = new EnumMap(XSSFBuiltinTableStyle.class);

    public TableStyle getStyle() {
        init();
        return styleMap.get(this);
    }

    public static boolean isBuiltinStyle(TableStyle style) {
        if (style == null) {
            return false;
        }
        try {
            valueOf(style.getName());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static synchronized void init() {
        synchronized (XSSFBuiltinTableStyle.class) {
            if (!styleMap.isEmpty()) {
                return;
            }
            try {
                InputStream is = XSSFBuiltinTableStyle.class.getResourceAsStream("presetTableStyles.xml");
                try {
                    Document doc = DocumentHelper.readDocument(is);
                    NodeList styleNodes = doc.getDocumentElement().getChildNodes();
                    for (int i = 0; i < styleNodes.getLength(); i++) {
                        Node node = styleNodes.item(i);
                        if (node.getNodeType() == 1) {
                            Element tag = (Element) node;
                            String styleName = tag.getTagName();
                            XSSFBuiltinTableStyle builtIn = valueOf(styleName);
                            Node dxfsNode = tag.getElementsByTagName("dxfs").item(0);
                            Node tableStyleNode = tag.getElementsByTagName("tableStyles").item(0);
                            StylesTable styles = new StylesTable();
                            UnsynchronizedByteArrayInputStream bis = UnsynchronizedByteArrayInputStream.builder().setByteArray(styleXML(dxfsNode, tableStyleNode).getBytes(StandardCharsets.UTF_8)).get();
                            try {
                                styles.readFrom(bis);
                                if (bis != null) {
                                    bis.close();
                                }
                                styleMap.put(builtIn, new XSSFBuiltinTypeStyleStyle(builtIn, styles.getExplicitTableStyle(styleName)));
                            } finally {
                            }
                        }
                    }
                    if (is != null) {
                        is.close();
                    }
                } finally {
                }
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private static String styleXML(Node dxfsNode, Node tableStyleNode) throws TransformerException {
        dxfsNode.insertBefore(dxfsNode.getOwnerDocument().createElement("dxf"), dxfsNode.getFirstChild());
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" xmlns:mc=\"http://schemas.openxmlformats.org/markup-compatibility/2006\" xmlns:x14ac=\"http://schemas.microsoft.com/office/spreadsheetml/2009/9/ac\" xmlns:x16r2=\"http://schemas.microsoft.com/office/spreadsheetml/2015/02/main\" mc:Ignorable=\"x14ac x16r2\">\n" + writeToString(dxfsNode) + writeToString(tableStyleNode) + "</styleSheet>";
    }

    private static String writeToString(Node node) throws TransformerException {
        StringBuilderWriter sw = new StringBuilderWriter(1024);
        try {
            Transformer transformer = XMLHelper.newTransformer();
            transformer.setOutputProperty("omit-xml-declaration", BooleanUtils.YES);
            transformer.transform(new DOMSource(node), new StreamResult(sw));
            String stringBuilderWriter = sw.toString();
            sw.close();
            return stringBuilderWriter;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                try {
                    sw.close();
                } catch (Throwable th3) {
                    th.addSuppressed(th3);
                }
                throw th2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* loaded from: classes10.dex */
    public static class XSSFBuiltinTypeStyleStyle implements TableStyle {
        private final XSSFBuiltinTableStyle builtIn;
        private final TableStyle style;

        protected XSSFBuiltinTypeStyleStyle(XSSFBuiltinTableStyle builtIn, TableStyle style) {
            this.builtIn = builtIn;
            this.style = style;
        }

        @Override // org.apache.poi.ss.usermodel.TableStyle
        public String getName() {
            return this.style.getName();
        }

        @Override // org.apache.poi.ss.usermodel.TableStyle
        public int getIndex() {
            return this.builtIn.ordinal();
        }

        @Override // org.apache.poi.ss.usermodel.TableStyle
        public boolean isBuiltin() {
            return true;
        }

        @Override // org.apache.poi.ss.usermodel.TableStyle
        public DifferentialStyleProvider getStyle(TableStyleType type) {
            return this.style.getStyle(type);
        }
    }
}
