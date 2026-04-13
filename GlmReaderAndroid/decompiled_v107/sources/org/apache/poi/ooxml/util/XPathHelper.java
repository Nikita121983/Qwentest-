package org.apache.poi.ooxml.util;

import com.microsoft.schemas.compatibility.AlternateContentDocument;
import java.util.Locale;
import javax.xml.namespace.QName;
import javax.xml.xpath.XPathFactory;
import org.apache.logging.log4j.Logger;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.Internal;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.XmlAnyTypeImpl;

/* loaded from: classes10.dex */
public final class XPathHelper {
    private static final String MAC_DML_NS = "http://schemas.microsoft.com/office/mac/drawingml/2008/main";
    private static final String MC_NS = "http://schemas.openxmlformats.org/markup-compatibility/2006";
    private static final String OSGI_ERROR = "Schemas (*.xsb) for <CLASS> can't be loaded - usually this happens when OSGI loading is used and the thread context classloader has no reference to the xmlbeans classes - please either verify if the <XSB>.xsb is on the classpath or alternatively try to use the poi-ooxml-full-x.x.jar";
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) XPathHelper.class);
    private static final QName ALTERNATE_CONTENT_TAG = new QName("http://schemas.openxmlformats.org/markup-compatibility/2006", "AlternateContent");
    static final XPathFactory xpathFactory = XPathFactory.newInstance();

    static {
        trySetFeature(xpathFactory, "http://javax.xml.XMLConstants/feature/secure-processing", true);
    }

    private XPathHelper() {
    }

    public static XPathFactory getFactory() {
        return xpathFactory;
    }

    private static void trySetFeature(XPathFactory xpf, String feature, boolean enabled) {
        try {
            xpf.setFeature(feature, enabled);
        } catch (AbstractMethodError ame) {
            LOG.atWarn().withThrowable(ame).log("Cannot set XPathFactory feature ({}) because outdated XML parser in classpath", feature);
        } catch (Exception e) {
            LOG.atWarn().withThrowable(e).log("XPathFactory Feature ({}) unsupported", feature);
        }
    }

    @Internal
    public static <T extends XmlObject> T selectProperty(XmlObject xmlObject, Class<T> cls, XSLFShape.ReparseFactory<T> reparseFactory, QName[]... qNameArr) throws XmlException {
        XmlObject xmlObject2;
        Throwable th;
        T t;
        XmlCursor newCursor = xmlObject.newCursor();
        try {
            XmlCursor selectProperty = selectProperty(newCursor, qNameArr, 0, reparseFactory != null, false);
            if (selectProperty == null) {
                if (selectProperty != null) {
                    selectProperty.close();
                }
                if (newCursor == null) {
                    return null;
                }
                newCursor.close();
                return null;
            }
            try {
                xmlObject2 = selectProperty.getObject();
                try {
                    if (xmlObject2 instanceof XmlAnyTypeImpl) {
                        String replace = OSGI_ERROR.replace("<CLASS>", cls.getSimpleName()).replace("<XSB>", cls.getSimpleName().toLowerCase(Locale.ROOT) + "*");
                        if (reparseFactory == null) {
                            throw new XmlException(replace);
                        }
                        t = reparseFactory.parse(selectProperty.newXMLStreamReader());
                    } else {
                        t = (T) xmlObject2;
                    }
                    if (selectProperty != null) {
                        selectProperty.close();
                    }
                    if (newCursor != null) {
                        newCursor.close();
                    }
                    return t;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        throw th;
                    } catch (Throwable th3) {
                        if (selectProperty != null) {
                            try {
                                try {
                                    selectProperty.close();
                                } catch (Throwable th4) {
                                    th.addSuppressed(th4);
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                try {
                                    throw th;
                                } catch (Throwable th6) {
                                    if (newCursor != null) {
                                        try {
                                            newCursor.close();
                                        } catch (Throwable th7) {
                                            th.addSuppressed(th7);
                                        }
                                    }
                                    throw th6;
                                }
                            }
                        }
                        throw th3;
                    }
                }
            } catch (Throwable th8) {
                xmlObject2 = xmlObject;
                th = th8;
            }
        } catch (Throwable th9) {
            th = th9;
        }
    }

    private static XmlCursor selectProperty(XmlCursor cur, QName[][] path, int offset, boolean reparseAlternate, boolean isAlternate) throws XmlException {
        AlternateContentDocument.AlternateContent alterCont;
        for (QName qn : path[offset]) {
            boolean found = cur.toChild(qn);
            while (found) {
                if (offset == path.length - 1) {
                    return cur;
                }
                cur.push();
                XmlCursor innerCur = selectProperty(cur, path, offset + 1, reparseAlternate, false);
                if (innerCur != null) {
                    return innerCur;
                }
                cur.pop();
                found = cur.toNextSibling(qn);
            }
        }
        if (isAlternate || !cur.toChild(ALTERNATE_CONTENT_TAG)) {
            return null;
        }
        XmlObject xo = cur.getObject();
        if (xo instanceof AlternateContentDocument.AlternateContent) {
            alterCont = (AlternateContentDocument.AlternateContent) xo;
        } else {
            if (!reparseAlternate) {
                throw new XmlException(OSGI_ERROR.replace("<CLASS>", "AlternateContent").replace("<XSB>", "alternatecontentelement"));
            }
            try {
                AlternateContentDocument acd = AlternateContentDocument.Factory.parse(cur.newXMLStreamReader());
                alterCont = acd.getAlternateContent();
            } catch (XmlException e) {
                throw new XmlException("unable to parse AlternateContent element", e);
            }
        }
        int choices = alterCont.sizeOfChoiceArray();
        for (int i = 0; i < choices; i++) {
            AlternateContentDocument.AlternateContent.Choice choice = alterCont.getChoiceArray(i);
            XmlCursor cCur = choice.newCursor();
            try {
                String requiresNS = cCur.namespaceForPrefix(choice.getRequires());
                if (!MAC_DML_NS.equalsIgnoreCase(requiresNS)) {
                    XmlCursor innerCur2 = selectProperty(cCur, path, offset, reparseAlternate, true);
                    if (innerCur2 != null && innerCur2 != cCur) {
                        if (cCur != null) {
                            cCur.close();
                        }
                        return innerCur2;
                    }
                    if (cCur != null) {
                        cCur.close();
                    }
                } else if (cCur != null) {
                    cCur.close();
                }
            } finally {
            }
        }
        if (!alterCont.isSetFallback()) {
            return null;
        }
        XmlCursor fCur = alterCont.getFallback().newCursor();
        XmlCursor innerCur3 = null;
        try {
            return selectProperty(fCur, path, offset, reparseAlternate, true);
        } finally {
            if (innerCur3 != fCur) {
                fCur.close();
            }
        }
    }
}
