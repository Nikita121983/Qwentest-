package org.apache.xmlbeans.impl.common;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import org.apache.logging.log4j.Logger;
import org.apache.xmlbeans.XmlOptions;
import org.apache.xmlbeans.impl.logging.XmlBeansLogManager;

/* loaded from: classes11.dex */
public final class StaxHelper {
    private static final Logger LOG = XmlBeansLogManager.getLogger(StaxHelper.class);

    private StaxHelper() {
    }

    public static XMLInputFactory newXMLInputFactory(XmlOptions options) {
        XMLInputFactory factory = XMLInputFactory.newFactory();
        trySetProperty(factory, "javax.xml.stream.isNamespaceAware", true);
        trySetProperty(factory, "javax.xml.stream.isValidating", false);
        trySetProperty(factory, "javax.xml.stream.supportDTD", options.isLoadDTDGrammar());
        trySetProperty(factory, "javax.xml.stream.isSupportingExternalEntities", options.isLoadExternalDTD());
        return factory;
    }

    public static XMLOutputFactory newXMLOutputFactory(XmlOptions options) {
        XMLOutputFactory factory = XMLOutputFactory.newFactory();
        trySetProperty(factory, "javax.xml.stream.isRepairingNamespaces", true);
        return factory;
    }

    public static XMLEventFactory newXMLEventFactory(XmlOptions options) {
        return XMLEventFactory.newFactory();
    }

    private static void trySetProperty(XMLInputFactory factory, String feature, boolean flag) {
        try {
            factory.setProperty(feature, Boolean.valueOf(flag));
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set StAX property {} because outdated StAX parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("StAX Property unsupported: {}", feature);
        }
    }

    private static void trySetProperty(XMLOutputFactory factory, String feature, boolean flag) {
        try {
            factory.setProperty(feature, Boolean.valueOf(flag));
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set StAX property {} because outdated StAX parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("StAX Property unsupported: {}", feature);
        }
    }
}
