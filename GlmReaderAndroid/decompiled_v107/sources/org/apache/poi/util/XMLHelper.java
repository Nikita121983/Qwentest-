package org.apache.poi.util;

import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.validation.SchemaFactory;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.poi.POIException;
import org.apache.poi.logging.PoiLogManager;
import org.apache.xmlbeans.impl.common.NameUtil;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

@Internal
/* loaded from: classes10.dex */
public final class XMLHelper {
    static final String FEATURE_DISALLOW_DOCTYPE_DECL = "http://apache.org/xml/features/disallow-doctype-decl";
    static final String FEATURE_EXTERNAL_ENTITIES = "http://xml.org/sax/features/external-general-entities";
    static final String FEATURE_LOAD_DTD_GRAMMAR = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar";
    static final String FEATURE_LOAD_EXTERNAL_DTD = "http://apache.org/xml/features/nonvalidating/load-external-dtd";
    static final String FEATURE_PARAMETER_ENTITIES = "http://xml.org/sax/features/external-parameter-entities";
    static final String METHOD_ENTITY_EXPANSION_XERCES = "setEntityExpansionLimit";
    static final String PROPERTY_ENTITY_EXPANSION_LIMIT = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit";
    static final String PROPERTY_SECURITY_MANAGER = "http://apache.org/xml/properties/security-manager";
    private static Object _xercesSecurityManager;
    private static long lastLog;
    static final String[] SECURITY_MANAGERS = {"org.apache.xerces.util.SecurityManager"};
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XMLHelper.class);
    private static final DocumentBuilderFactory documentBuilderFactory = getDocumentBuilderFactory();
    private static final SAXParserFactory saxFactory = getSaxParserFactory();
    private static volatile boolean _xercesSecurityManagerSet = false;

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface SecurityFeature {
        void accept(String str, boolean z) throws ParserConfigurationException, SAXException, TransformerException;
    }

    @FunctionalInterface
    /* loaded from: classes10.dex */
    public interface SecurityProperty {
        void accept(String str, Object obj) throws SAXException;
    }

    private XMLHelper() {
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x0097, code lost:
    
        if (trySet(new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6(), "http://apache.org/xml/properties/security-manager", r2) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static javax.xml.parsers.DocumentBuilderFactory getDocumentBuilderFactory() {
        /*
            javax.xml.parsers.DocumentBuilderFactory r0 = javax.xml.parsers.DocumentBuilderFactory.newInstance()
            r1 = 1
            r0.setNamespaceAware(r1)
            r2 = 0
            r0.setExpandEntityReferences(r2)
            r0.setValidating(r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://javax.xml.XMLConstants/feature/secure-processing"
            trySet(r3, r4, r1)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>()
            java.lang.String r4 = "http://javax.xml.XMLConstants/property/accessExternalSchema"
            java.lang.String r5 = ""
            quietSet(r3, r4, r5)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>()
            java.lang.String r4 = "http://javax.xml.XMLConstants/property/accessExternalDTD"
            quietSet(r3, r4, r5)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://xml.org/sax/features/external-general-entities"
            trySet(r3, r4, r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://xml.org/sax/features/external-parameter-entities"
            trySet(r3, r4, r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://apache.org/xml/features/nonvalidating/load-external-dtd"
            trySet(r3, r4, r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar"
            trySet(r3, r4, r2)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda5
            r3.<init>()
            java.lang.String r4 = "http://apache.org/xml/features/disallow-doctype-decl"
            trySet(r3, r4, r1)
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda7
            r3.<init>()
            java.lang.String r4 = "XIncludeAware"
            trySet(r3, r4, r2)
            java.lang.Object r2 = getXercesSecurityManager()
            if (r2 == 0) goto L99
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>()
            java.lang.String r4 = "http://apache.org/xml/properties/security-manager"
            boolean r3 = trySet(r3, r4, r2)
            if (r3 != 0) goto Laa
        L99:
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda6
            r3.<init>()
            java.lang.String r4 = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            trySet(r3, r4, r1)
        Laa:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.XMLHelper.getDocumentBuilderFactory():javax.xml.parsers.DocumentBuilderFactory");
    }

    public static DocumentBuilder newDocumentBuilder() {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.setEntityResolver(new XMLHelper$$ExternalSyntheticLambda12());
            documentBuilder.setErrorHandler(new DocHelperErrorHandler(true));
            return documentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x006e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static javax.xml.parsers.SAXParserFactory getSaxParserFactory() {
        /*
            java.lang.String r0 = "-"
            java.lang.String r1 = "Failed to create SAXParserFactory"
            javax.xml.parsers.SAXParserFactory r2 = javax.xml.parsers.SAXParserFactory.newInstance()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r3 = 0
            r2.setValidating(r3)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r4 = 1
            r2.setNamespaceAware(r4)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r2.getClass()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11 r5 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r5.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            java.lang.String r6 = "http://javax.xml.XMLConstants/feature/secure-processing"
            trySet(r5, r6, r4)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r2.getClass()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11 r5 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r5.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            java.lang.String r6 = "http://apache.org/xml/features/nonvalidating/load-dtd-grammar"
            trySet(r5, r6, r3)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r2.getClass()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11 r5 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r5.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            java.lang.String r6 = "http://apache.org/xml/features/nonvalidating/load-external-dtd"
            trySet(r5, r6, r3)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r2.getClass()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11 r5 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r5.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            java.lang.String r6 = "http://xml.org/sax/features/external-general-entities"
            trySet(r5, r6, r3)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r2.getClass()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11 r3 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda11     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            r3.<init>()     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            java.lang.String r5 = "http://apache.org/xml/features/disallow-doctype-decl"
            trySet(r3, r5, r4)     // Catch: java.lang.Exception -> L52 java.lang.Error -> L65 java.lang.RuntimeException -> L67
            return r2
        L52:
            r2 = move-exception
            boolean r3 = org.apache.poi.util.ExceptionUtil.isFatal(r2)
            if (r3 == 0) goto L5c
            org.apache.poi.util.ExceptionUtil.rethrow(r2)
        L5c:
            logThrowable(r2, r1, r0)
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            r0.<init>(r1, r2)
            throw r0
        L65:
            r2 = move-exception
            goto L68
        L67:
            r2 = move-exception
        L68:
            boolean r3 = org.apache.poi.util.ExceptionUtil.isFatal(r2)
            if (r3 == 0) goto L71
            org.apache.poi.util.ExceptionUtil.rethrow(r2)
        L71:
            logThrowable(r2, r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.XMLHelper.getSaxParserFactory():javax.xml.parsers.SAXParserFactory");
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x004a, code lost:
    
        if (trySet(new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda3(), "http://apache.org/xml/properties/security-manager", r1) == false) goto L14;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static org.xml.sax.XMLReader newXMLReader() throws org.xml.sax.SAXException, javax.xml.parsers.ParserConfigurationException {
        /*
            javax.xml.parsers.SAXParserFactory r0 = org.apache.poi.util.XMLHelper.saxFactory
            javax.xml.parsers.SAXParser r0 = r0.newSAXParser()
            org.xml.sax.XMLReader r0 = r0.getXMLReader()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda12 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda12
            r1.<init>()
            r0.setEntityResolver(r1)
            org.apache.poi.util.XMLHelper$DocHelperErrorHandler r1 = new org.apache.poi.util.XMLHelper$DocHelperErrorHandler
            r2 = 0
            r1.<init>(r2)
            r0.setErrorHandler(r1)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2
            r1.<init>()
            java.lang.String r3 = "http://javax.xml.XMLConstants/feature/secure-processing"
            r4 = 1
            trySet(r1, r3, r4)
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2 r1 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda2
            r1.<init>()
            java.lang.String r3 = "http://xml.org/sax/features/external-general-entities"
            trySet(r1, r3, r2)
            java.lang.Object r1 = getXercesSecurityManager()
            if (r1 == 0) goto L4c
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda3 r2 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda3
            r2.<init>()
            java.lang.String r3 = "http://apache.org/xml/properties/security-manager"
            boolean r2 = trySet(r2, r3, r1)
            if (r2 != 0) goto L5d
        L4c:
            r0.getClass()
            org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda3 r2 = new org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda3
            r2.<init>()
            java.lang.String r3 = "http://www.oracle.com/xml/jaxp/properties/entityExpansionLimit"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            trySet(r2, r3, r4)
        L5d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.poi.util.XMLHelper.newXMLReader():org.xml.sax.XMLReader");
    }

    public static XMLInputFactory newXMLInputFactory() {
        final XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setProperty(str, Boolean.valueOf(z));
            }
        }, "javax.xml.stream.isNamespaceAware", true);
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setProperty(str, Boolean.valueOf(z));
            }
        }, "javax.xml.stream.isValidating", false);
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setProperty(str, Boolean.valueOf(z));
            }
        }, "javax.xml.stream.supportDTD", false);
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda1
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setProperty(str, Boolean.valueOf(z));
            }
        }, "javax.xml.stream.isSupportingExternalEntities", false);
        return factory;
    }

    public static XMLOutputFactory newXMLOutputFactory() {
        final XMLOutputFactory factory = XMLOutputFactory.newInstance();
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda10
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setProperty(str, Boolean.valueOf(z));
            }
        }, "javax.xml.stream.isRepairingNamespaces", true);
        return factory;
    }

    public static XMLEventFactory newXMLEventFactory() {
        return XMLEventFactory.newInstance();
    }

    public static TransformerFactory getTransformerFactory() {
        final TransformerFactory factory = TransformerFactory.newInstance();
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda8
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setFeature(str, z);
            }
        }, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda9
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setAttribute(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda9
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setAttribute(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda9
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setAttribute(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
        return factory;
    }

    public static Transformer newTransformer() throws TransformerConfigurationException {
        Transformer serializer = getTransformerFactory().newTransformer();
        serializer.setOutputProperty("encoding", "UTF-8");
        serializer.setOutputProperty("indent", BooleanUtils.NO);
        serializer.setOutputProperty("method", "xml");
        return serializer;
    }

    public static SchemaFactory getSchemaFactory() {
        final SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        factory.getClass();
        trySet(new SecurityFeature() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda0
            @Override // org.apache.poi.util.XMLHelper.SecurityFeature
            public final void accept(String str, boolean z) {
                factory.setFeature(str, z);
            }
        }, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda4
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setProperty(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalDTD", "");
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda4
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setProperty(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalStylesheet", "");
        factory.getClass();
        quietSet(new SecurityProperty() { // from class: org.apache.poi.util.XMLHelper$$ExternalSyntheticLambda4
            @Override // org.apache.poi.util.XMLHelper.SecurityProperty
            public final void accept(String str, Object obj) {
                factory.setProperty(str, obj);
            }
        }, "http://javax.xml.XMLConstants/property/accessExternalSchema", "");
        return factory;
    }

    public static int getDepthOfChildNodes(Node node, int maxSupportedDepth) throws POIException {
        return getDepthOfChildNodes(node, maxSupportedDepth, 0);
    }

    private static int getDepthOfChildNodes(Node node, int maxSupportedDepth, int nodeDepth) throws POIException {
        int currentDepth = nodeDepth + 1;
        int maxDepth = currentDepth;
        for (Node child = node.getFirstChild(); child != null; child = child.getNextSibling()) {
            int childDepth = getDepthOfChildNodes(child, maxSupportedDepth, currentDepth);
            if (childDepth > maxDepth && (maxDepth = childDepth) > maxSupportedDepth) {
                throw new POIException(String.format(Locale.ROOT, "Node depth exceeds maximum supported depth of %s", Integer.valueOf(maxSupportedDepth)));
            }
        }
        return maxDepth;
    }

    private static Object getXercesSecurityManager() {
        if (_xercesSecurityManagerSet) {
            return _xercesSecurityManager;
        }
        synchronized (XMLHelper.class) {
            if (!_xercesSecurityManagerSet) {
                _xercesSecurityManager = tryGetXercesSecurityManager();
                _xercesSecurityManagerSet = true;
            }
        }
        return _xercesSecurityManager;
    }

    private static Object tryGetXercesSecurityManager() {
        for (String securityManagerClassName : SECURITY_MANAGERS) {
            try {
                Object mgr = Class.forName(securityManagerClassName).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                Method setLimit = mgr.getClass().getMethod(METHOD_ENTITY_EXPANSION_XERCES, Integer.TYPE);
                setLimit.invoke(mgr, 1);
                return mgr;
            } catch (ClassNotFoundException e) {
            } catch (Throwable e2) {
                if (ExceptionUtil.isFatal(e2)) {
                    ExceptionUtil.rethrow(e2);
                }
                logThrowable(e2, "SAX Feature unsupported", securityManagerClassName);
            }
        }
        return null;
    }

    private static boolean trySet(SecurityFeature feature, String name, boolean value) {
        try {
            feature.accept(name, value);
            return true;
        } catch (Error e) {
            if (ExceptionUtil.isFatal(e)) {
                ExceptionUtil.rethrow(e);
            }
            logThrowable(e, "Cannot set SAX feature because outdated XML parser in classpath", name);
            return false;
        } catch (Exception e2) {
            if (ExceptionUtil.isFatal(e2)) {
                ExceptionUtil.rethrow(e2);
            }
            logThrowable(e2, "SAX Feature unsupported", name);
            return false;
        }
    }

    private static boolean trySet(SecurityProperty property, String name, Object value) {
        try {
            property.accept(name, value);
            return true;
        } catch (Error e) {
            if (ExceptionUtil.isFatal(e)) {
                ExceptionUtil.rethrow(e);
            }
            logThrowable(e, "Cannot set SAX feature because outdated XML parser in classpath", name);
            return false;
        } catch (Exception e2) {
            if (ExceptionUtil.isFatal(e2)) {
                ExceptionUtil.rethrow(e2);
            }
            logThrowable(e2, "SAX Feature unsupported", name);
            return false;
        }
    }

    private static boolean quietSet(SecurityProperty property, String name, Object value) {
        try {
            property.accept(name, value);
            return true;
        } catch (Throwable e) {
            if (ExceptionUtil.isFatal(e)) {
                ExceptionUtil.rethrow(e);
                return false;
            }
            return false;
        }
    }

    private static void logThrowable(Throwable t, String message, String name) {
        if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
            LOG.atWarn().withThrowable(t).log("{} [log suppressed for 5 minutes] {}", message, name);
            lastLog = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes10.dex */
    public static class DocHelperErrorHandler implements ErrorHandler {
        private final boolean logException;

        public DocHelperErrorHandler(boolean logException) {
            this.logException = logException;
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException exception) {
            printError(Level.WARN, exception);
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException exception) {
            printError(Level.ERROR, exception);
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException exception) throws SAXException {
            printError(Level.FATAL, exception);
            throw exception;
        }

        private void printError(Level type, SAXParseException ex) {
            int index;
            String systemId = ex.getSystemId();
            if (systemId != null && (index = systemId.lastIndexOf(47)) != -1) {
                systemId = systemId.substring(index + 1);
            }
            String message = (systemId == null ? "" : systemId) + NameUtil.COLON + ex.getLineNumber() + NameUtil.COLON + ex.getColumnNumber() + NameUtil.COLON + ex.getMessage();
            LogBuilder builder = XMLHelper.LOG.atLevel(type);
            if (this.logException) {
                builder = builder.withThrowable(ex);
            }
            builder.log(message);
        }
    }

    public static InputSource ignoreEntity(String publicId, String systemId) {
        return new InputSource(new StringReader(""));
    }
}
