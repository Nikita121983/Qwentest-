package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;
import org.apache.xmlbeans.impl.util.ExceptionUtil;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/* loaded from: classes11.dex */
public final class DocumentHelper {
    private static final Logger LOG = XmlBeansLogManager.getLogger(DocumentHelper.class);
    private static final DocumentBuilder documentBuilderSingleton = newDocumentBuilder(new XmlOptions());
    private static long lastLog;

    private DocumentHelper() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes11.dex */
    public static class DocHelperErrorHandler implements ErrorHandler {
        private DocHelperErrorHandler() {
        }

        @Override // org.xml.sax.ErrorHandler
        public void warning(SAXParseException exception) throws SAXException {
            DocumentHelper.LOG.atWarn().withThrowable(exception).log(asString(exception));
        }

        @Override // org.xml.sax.ErrorHandler
        public void error(SAXParseException exception) throws SAXException {
            DocumentHelper.LOG.atError().withThrowable(exception).log(asString(exception));
        }

        @Override // org.xml.sax.ErrorHandler
        public void fatalError(SAXParseException exception) throws SAXException {
            DocumentHelper.LOG.atFatal().withThrowable(exception).log(asString(exception));
            throw exception;
        }

        private String asString(SAXParseException ex) {
            StringBuilder sb = new StringBuilder();
            String systemId = ex.getSystemId();
            if (systemId != null) {
                int index = systemId.lastIndexOf(47);
                if (index != -1) {
                    systemId = systemId.substring(index + 1);
                }
                sb.append(systemId);
            }
            sb.append(NameUtil.COLON);
            sb.append(ex.getLineNumber());
            sb.append(NameUtil.COLON);
            sb.append(ex.getColumnNumber());
            sb.append(": ");
            sb.append(ex.getMessage());
            return sb.toString();
        }
    }

    public static DocumentBuilder newDocumentBuilder(XmlOptions xmlOptions) {
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory(xmlOptions).newDocumentBuilder();
            documentBuilder.setEntityResolver(SAXHelper.IGNORING_ENTITY_RESOLVER);
            documentBuilder.setErrorHandler(new DocHelperErrorHandler());
            return documentBuilder;
        } catch (ParserConfigurationException e) {
            throw new IllegalStateException("cannot create a DocumentBuilder", e);
        }
    }

    private static DocumentBuilderFactory documentBuilderFactory(XmlOptions options) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);
        documentBuilderFactory.setValidating(false);
        trySetFeature(documentBuilderFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        trySetFeature(documentBuilderFactory, XMLBeansConstants.FEATURE_LOAD_DTD_GRAMMAR, options.isLoadDTDGrammar());
        trySetFeature(documentBuilderFactory, XMLBeansConstants.FEATURE_LOAD_EXTERNAL_DTD, options.isLoadExternalDTD());
        trySetFeature(documentBuilderFactory, XMLBeansConstants.FEATURE_DISALLOW_DOCTYPE_DECL, options.disallowDocTypeDeclaration());
        trySetXercesSecurityManager(documentBuilderFactory, options);
        return documentBuilderFactory;
    }

    private static void trySetFeature(DocumentBuilderFactory dbf, String feature, boolean enabled) {
        try {
            dbf.setFeature(feature, enabled);
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set SAX feature {} because of outdated XML parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", feature);
        }
    }

    private static void trySetXercesSecurityManager(DocumentBuilderFactory dbf, XmlOptions options) {
        String[] strArr = {"org.apache.xerces.util.SecurityManager"};
        for (int i = 0; i < 1; i++) {
            String securityManagerClassName = strArr[i];
            try {
                Object mgr = Class.forName(securityManagerClassName).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                Method setLimit = mgr.getClass().getMethod("setEntityExpansionLimit", Integer.TYPE);
                setLimit.invoke(mgr, Integer.valueOf(options.getEntityExpansionLimit()));
                dbf.setAttribute(XMLBeansConstants.SECURITY_MANAGER, mgr);
                return;
            } catch (ClassNotFoundException e) {
            } catch (Throwable e2) {
                if (ExceptionUtil.isFatal(e2)) {
                    ExceptionUtil.rethrow(e2);
                }
                if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
                    LOG.atWarn().withThrowable(e2).log("DocumentBuilderFactory Security Manager could not be setup [log suppressed for 5 minutes]");
                    lastLog = System.currentTimeMillis();
                }
            }
        }
        try {
            dbf.setAttribute(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(options.getEntityExpansionLimit()));
        } catch (Throwable e3) {
            if (ExceptionUtil.isFatal(e3)) {
                ExceptionUtil.rethrow(e3);
            }
            if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
                LOG.atWarn().withThrowable(e3).log("DocumentBuilderFactory Entity Expansion Limit could not be setup [log suppressed for 5 minutes]");
                lastLog = System.currentTimeMillis();
            }
        }
    }

    public static Document readDocument(XmlOptions xmlOptions, InputStream inp) throws IOException, SAXException {
        return newDocumentBuilder(xmlOptions).parse(inp);
    }

    public static Document readDocument(XmlOptions xmlOptions, InputSource inp) throws IOException, SAXException {
        return newDocumentBuilder(xmlOptions).parse(inp);
    }

    public static Document createDocument() {
        return documentBuilderSingleton.newDocument();
    }
}
