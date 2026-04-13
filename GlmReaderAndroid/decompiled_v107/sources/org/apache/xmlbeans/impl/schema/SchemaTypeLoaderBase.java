package org.apache.xmlbeans.impl.schema;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.stream.XMLStreamReader;
import org.apache.xmlbeans.SchemaAttributeGroup;
import org.apache.xmlbeans.SchemaField;
import org.apache.xmlbeans.SchemaGlobalAttribute;
import org.apache.xmlbeans.SchemaGlobalElement;
import org.apache.xmlbeans.SchemaModelGroup;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SchemaTypeLoader;
import org.apache.xmlbeans.XmlBeans;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlFactoryHook;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.XmlSaxHandler;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.store.Locale;
import org.apache.xmlbeans.impl.xpath.XPathFactory;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Node;

/* loaded from: classes11.dex */
public abstract class SchemaTypeLoaderBase implements SchemaTypeLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String USER_AGENT = "XMLBeans/" + XmlBeans.getVersion() + " (" + XmlBeans.getTitle() + ")";

    private static String doCompilePath(String pathExpr, XmlOptions options) {
        return XPathFactory.compilePath(pathExpr, options);
    }

    private static String doCompileQuery(String queryExpr, XmlOptions options) {
        return XPathFactory.compileQuery(queryExpr, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findType(QName name) {
        SchemaType.Ref ref = findTypeRef(name);
        if (ref == null) {
            return null;
        }
        SchemaType result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findDocumentType(QName name) {
        SchemaType.Ref ref = findDocumentTypeRef(name);
        if (ref == null) {
            return null;
        }
        SchemaType result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType findAttributeType(QName name) {
        SchemaType.Ref ref = findAttributeTypeRef(name);
        if (ref == null) {
            return null;
        }
        SchemaType result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaModelGroup findModelGroup(QName name) {
        SchemaModelGroup.Ref ref = findModelGroupRef(name);
        if (ref == null) {
            return null;
        }
        SchemaModelGroup result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaAttributeGroup findAttributeGroup(QName name) {
        SchemaAttributeGroup.Ref ref = findAttributeGroupRef(name);
        if (ref == null) {
            return null;
        }
        SchemaAttributeGroup result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalElement findElement(QName name) {
        SchemaGlobalElement.Ref ref = findElementRef(name);
        if (ref == null) {
            return null;
        }
        SchemaGlobalElement result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaGlobalAttribute findAttribute(QName name) {
        SchemaGlobalAttribute.Ref ref = findAttributeRef(name);
        if (ref == null) {
            return null;
        }
        SchemaGlobalAttribute result = ref.get();
        if (result == null) {
            throw new AssertionError();
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject newInstance(SchemaType type, XmlOptions options) {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.newInstance(this, type, options);
        }
        return Locale.newInstance(this, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(String xmlText, SchemaType type, XmlOptions options) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse(this, xmlText, type, options);
        }
        return Locale.parseToXmlObject(this, xmlText, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(XMLStreamReader xsr, SchemaType type, XmlOptions options) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse(this, xsr, type, options);
        }
        return Locale.parseToXmlObject(this, xsr, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(File file, SchemaType type, XmlOptions options) throws XmlException, IOException {
        String fileName = file.toURI().normalize().toString();
        if (options == null) {
            options = new XmlOptions();
            options.setDocumentSourceName(fileName);
        } else if (options.getDocumentSourceName() == null) {
            options = new XmlOptions(options);
            options.setDocumentSourceName(fileName);
        }
        InputStream fis = Files.newInputStream(file.toPath(), new OpenOption[0]);
        try {
            XmlObject parse = parse(fis, type, options);
            if (fis != null) {
                fis.close();
            }
            return parse;
        } finally {
        }
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(URL url, SchemaType type, XmlOptions options) throws XmlException, IOException {
        URLConnection conn;
        if (options == null) {
            options = new XmlOptions();
            options.setDocumentSourceName(url.toString());
        } else if (options.getDocumentSourceName() == null) {
            options = new XmlOptions(options);
            options.setDocumentSourceName(url.toString());
        }
        boolean redirected = false;
        int count = 0;
        do {
            conn = url.openConnection();
            conn.addRequestProperty("User-Agent", USER_AGENT);
            conn.addRequestProperty("Accept", "application/xml, text/xml, */*");
            if (conn instanceof HttpURLConnection) {
                HttpURLConnection httpcon = (HttpURLConnection) conn;
                int code = httpcon.getResponseCode();
                boolean redirected2 = code == 301 || code == 302;
                if (redirected2 && count > 5) {
                    redirected2 = false;
                }
                if (!redirected2) {
                    redirected = redirected2;
                } else {
                    String newLocation = httpcon.getHeaderField("Location");
                    if (newLocation == null) {
                        redirected = false;
                    } else {
                        URL url2 = new URL(newLocation);
                        count++;
                        redirected = redirected2;
                        url = url2;
                    }
                }
            }
        } while (redirected);
        InputStream stream = conn.getInputStream();
        try {
            XmlObject parse = parse(stream, type, options);
            if (stream != null) {
                stream.close();
            }
            return parse;
        } finally {
        }
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(InputStream jiois, SchemaType type, XmlOptions options) throws XmlException, IOException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        DigestInputStream digestStream = null;
        if (options != null && options.isLoadMessageDigest()) {
            try {
                MessageDigest sha = MessageDigest.getInstance("SHA");
                digestStream = new DigestInputStream(jiois, sha);
                jiois = digestStream;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        if (hook != null) {
            return hook.parse(this, jiois, type, options);
        }
        XmlObject result = Locale.parseToXmlObject(this, jiois, type, options);
        if (digestStream != null) {
            result.documentProperties().setMessageDigest(digestStream.getMessageDigest().digest());
        }
        return result;
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(Reader jior, SchemaType type, XmlOptions options) throws XmlException, IOException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse(this, jior, type, options);
        }
        return Locale.parseToXmlObject(this, jior, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlObject parse(Node node, SchemaType type, XmlOptions options) throws XmlException {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.parse(this, node, type, options);
        }
        return Locale.parseToXmlObject(this, node, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public XmlSaxHandler newXmlSaxHandler(SchemaType type, XmlOptions options) {
        XmlFactoryHook hook = XmlFactoryHook.ThreadContext.getHook();
        if (hook != null) {
            return hook.newXmlSaxHandler(this, type, options);
        }
        return Locale.newSaxHandler(this, type, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public DOMImplementation newDomImplementation(XmlOptions options) {
        return Locale.newDomImplementation(this, options);
    }

    public String compilePath(String pathExpr) {
        return compilePath(pathExpr, null);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public String compilePath(String pathExpr, XmlOptions options) {
        return doCompilePath(pathExpr, options);
    }

    public String compileQuery(String queryExpr) {
        return compileQuery(queryExpr, null);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public String compileQuery(String queryExpr, XmlOptions options) {
        return doCompileQuery(queryExpr, options);
    }

    @Override // org.apache.xmlbeans.SchemaTypeLoader
    public SchemaType typeForSignature(String signature) {
        String uri;
        int end;
        int i;
        SchemaType curType;
        SchemaType schemaType;
        int end2 = signature.indexOf(64);
        if (end2 < 0) {
            uri = "";
            end = signature.length();
        } else {
            uri = signature.substring(end2 + 1);
            end = end2;
        }
        List<String> parts = new ArrayList<>();
        int index = 0;
        while (true) {
            i = 1;
            if (index >= end) {
                break;
            }
            int nextc = signature.indexOf(58, index);
            int nextd = signature.indexOf(124, index);
            int next = nextc < 0 ? nextd : nextd < 0 ? nextc : Math.min(nextc, nextd);
            if (next < 0 || next > end) {
                next = end;
            }
            parts.add(signature.substring(index, next));
            index = next + 1;
        }
        int i2 = parts.size() - 1;
        SchemaType curType2 = null;
        while (i2 >= 0) {
            String part = parts.get(i2);
            if (part.length() < i) {
                throw new IllegalArgumentException();
            }
            int offset = (part.length() < 2 || part.charAt(i) != '=') ? i : 2;
            int i3 = 0;
            SchemaType schemaType2 = null;
            switch (part.charAt(0)) {
                case 'A':
                case 'Q':
                    if (curType2 == null) {
                        SchemaGlobalAttribute attr = findAttribute(QNameHelper.forLNS(part.substring(offset), uri));
                        if (attr != null) {
                            curType = attr.getType();
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        if (curType2.isSimpleType()) {
                            return null;
                        }
                        SchemaType[] subTypes = curType2.getAnonymousTypes();
                        String localName = part.substring(offset);
                        int length = subTypes.length;
                        while (i3 < length) {
                            SchemaType subType = subTypes[i3];
                            SchemaField field = subType.getContainerField();
                            if (field == null || !field.isAttribute() || !field.getName().getLocalPart().equals(localName)) {
                                i3++;
                            } else {
                                curType = subType;
                                break;
                            }
                        }
                        return null;
                    }
                case 'B':
                    if (curType2 == null) {
                        throw new IllegalArgumentException();
                    }
                    if (curType2.getSimpleVariety() == i) {
                        SchemaType[] subTypes2 = curType2.getAnonymousTypes();
                        if (subTypes2.length == i) {
                            curType = subTypes2[0];
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                case 'C':
                case 'R':
                    if (curType2 == null) {
                        curType = findAttributeType(QNameHelper.forLNS(part.substring(offset), uri));
                        if (curType != null) {
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                case 'D':
                    if (curType2 == null) {
                        curType = findDocumentType(QNameHelper.forLNS(part.substring(offset), uri));
                        if (curType != null) {
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                case 'E':
                case 'U':
                    if (curType2 == null) {
                        SchemaGlobalElement elt = findElement(QNameHelper.forLNS(part.substring(offset), uri));
                        if (elt != null) {
                            curType = elt.getType();
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        if (curType2.getContentType() < 3) {
                            return null;
                        }
                        SchemaType[] subTypes3 = curType2.getAnonymousTypes();
                        String localName2 = part.substring(offset);
                        int length2 = subTypes3.length;
                        while (i3 < length2) {
                            SchemaType subType2 = subTypes3[i3];
                            SchemaField field2 = subType2.getContainerField();
                            if (field2 == null || field2.isAttribute()) {
                                schemaType = schemaType2;
                            } else {
                                schemaType = schemaType2;
                                if (field2.getName().getLocalPart().equals(localName2)) {
                                    curType = subType2;
                                    break;
                                }
                            }
                            i3++;
                            schemaType2 = schemaType;
                        }
                        return schemaType2;
                    }
                    break;
                case 'F':
                case 'G':
                case 'H':
                case 'J':
                case 'K':
                case 'L':
                case 'N':
                case 'O':
                case 'P':
                case 'S':
                default:
                    throw new IllegalArgumentException();
                case 'I':
                    if (curType2 == null) {
                        throw new IllegalArgumentException();
                    }
                    if (curType2.getSimpleVariety() == 3) {
                        SchemaType[] subTypes4 = curType2.getAnonymousTypes();
                        if (subTypes4.length == i) {
                            curType = subTypes4[0];
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        return null;
                    }
                case 'M':
                    if (curType2 == null) {
                        throw new IllegalArgumentException();
                    }
                    try {
                        int index2 = Integer.parseInt(part.substring(offset));
                        if (curType2.getSimpleVariety() == 2) {
                            SchemaType[] subTypes5 = curType2.getAnonymousTypes();
                            if (subTypes5.length > index2) {
                                curType = subTypes5[index2];
                                break;
                            } else {
                                return null;
                            }
                        } else {
                            return null;
                        }
                    } catch (Exception e) {
                        throw new IllegalArgumentException();
                    }
                case 'T':
                    if (curType2 == null) {
                        curType = findType(QNameHelper.forLNS(part.substring(offset), uri));
                        if (curType != null) {
                            break;
                        } else {
                            return null;
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
            }
            curType2 = curType;
            i2--;
            i = 1;
        }
        return curType2;
    }
}
