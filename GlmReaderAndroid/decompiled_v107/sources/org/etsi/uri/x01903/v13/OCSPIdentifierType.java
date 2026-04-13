package org.etsi.uri.x01903.v13;

import java.util.Calendar;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnyURI;
import org.apache.xmlbeans.XmlDateTime;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface OCSPIdentifierType extends XmlObject {
    public static final DocumentFactory<OCSPIdentifierType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ocspidentifiertype3968type");
    public static final SchemaType type = Factory.getType();

    ResponderIDType addNewResponderID();

    Calendar getProducedAt();

    ResponderIDType getResponderID();

    String getURI();

    boolean isSetURI();

    void setProducedAt(Calendar calendar);

    void setResponderID(ResponderIDType responderIDType);

    void setURI(String str);

    void unsetURI();

    XmlDateTime xgetProducedAt();

    XmlAnyURI xgetURI();

    void xsetProducedAt(XmlDateTime xmlDateTime);

    void xsetURI(XmlAnyURI xmlAnyURI);
}
