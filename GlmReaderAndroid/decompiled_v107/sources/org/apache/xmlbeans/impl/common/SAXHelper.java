package org.apache.xmlbeans.impl.common;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;
import org.apache.xmlbeans.impl.util.ExceptionUtil;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

/* loaded from: classes11.dex */
public final class SAXHelper {
    private static long lastLog;
    private static final Logger LOG = XmlBeansLogManager.getLogger(SAXHelper.class);
    public static final EntityResolver IGNORING_ENTITY_RESOLVER = new EntityResolver() { // from class: org.apache.xmlbeans.impl.common.SAXHelper$$ExternalSyntheticLambda0
        @Override // org.xml.sax.EntityResolver
        public final InputSource resolveEntity(String str, String str2) {
            return SAXHelper.lambda$static$0(str, str2);
        }
    };

    private SAXHelper() {
    }

    public static XMLReader newXMLReader(XmlOptions options) throws SAXException, ParserConfigurationException {
        XMLReader xmlReader = saxFactory(options).newSAXParser().getXMLReader();
        xmlReader.setEntityResolver(IGNORING_ENTITY_RESOLVER);
        trySetSAXFeature(xmlReader, "http://javax.xml.XMLConstants/feature/secure-processing");
        trySetXercesSecurityManager(xmlReader, options);
        return xmlReader;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ InputSource lambda$static$0(String publicId, String systemId) throws SAXException, IOException {
        return new InputSource(new StringReader(""));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static SAXParserFactory saxFactory() {
        return saxFactory(new XmlOptions());
    }

    static SAXParserFactory saxFactory(XmlOptions options) {
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        saxFactory.setValidating(false);
        saxFactory.setNamespaceAware(true);
        trySetSAXFeature(saxFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
        trySetSAXFeature(saxFactory, XMLBeansConstants.FEATURE_LOAD_DTD_GRAMMAR, options.isLoadDTDGrammar());
        trySetSAXFeature(saxFactory, XMLBeansConstants.FEATURE_LOAD_EXTERNAL_DTD, options.isLoadExternalDTD());
        trySetSAXFeature(saxFactory, XMLBeansConstants.FEATURE_DISALLOW_DOCTYPE_DECL, options.disallowDocTypeDeclaration());
        return saxFactory;
    }

    private static void trySetSAXFeature(SAXParserFactory spf, String feature, boolean flag) {
        try {
            spf.setFeature(feature, flag);
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set SAX feature {} because outdated XML parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", feature);
        }
    }

    private static void trySetSAXFeature(XMLReader xmlReader, String feature) {
        try {
            xmlReader.setFeature(feature, true);
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set SAX feature {} because outdated XML parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("SAX Feature unsupported: {}", feature);
        }
    }

    private static void trySetXercesSecurityManager(XMLReader xmlReader, XmlOptions options) {
        Class<?> clazz;
        String[] strArr = {"org.apache.xerces.util.SecurityManager"};
        for (int i = 0; i < 1; i++) {
            String securityManagerClassName = strArr[i];
            try {
                clazz = Class.forName(securityManagerClassName);
            } catch (Throwable e) {
                if (ExceptionUtil.isFatal(e)) {
                    ExceptionUtil.rethrow(e);
                }
            }
            try {
                Object mgr = clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                Method setLimit = clazz.getMethod("setEntityExpansionLimit", Integer.TYPE);
                setLimit.invoke(mgr, Integer.valueOf(options.getEntityExpansionLimit()));
                xmlReader.setProperty(XMLBeansConstants.SECURITY_MANAGER, mgr);
                return;
            } catch (Throwable e2) {
                if (ExceptionUtil.isFatal(e2)) {
                    ExceptionUtil.rethrow(e2);
                }
                if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
                    LOG.atWarn().withThrowable(e2).log("SAX Security Manager could not be setup [log suppressed for 5 minutes]");
                    lastLog = System.currentTimeMillis();
                }
            }
        }
        try {
            xmlReader.setProperty(XMLBeansConstants.ENTITY_EXPANSION_LIMIT, Integer.valueOf(options.getEntityExpansionLimit()));
        } catch (SAXException e3) {
            if (System.currentTimeMillis() > lastLog + TimeUnit.MINUTES.toMillis(5L)) {
                LOG.atWarn().withThrowable(e3).log("SAX Security Manager could not be setup [log suppressed for 5 minutes]");
                lastLog = System.currentTimeMillis();
            }
        }
    }
}
