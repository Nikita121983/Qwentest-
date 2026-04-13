package org.etsi.uri.x01903.v13;

import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes11.dex */
public interface XAdESTimeStampType extends GenericTimeStampType {
    public static final DocumentFactory<XAdESTimeStampType> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "xadestimestamptypeaedbtype");
    public static final SchemaType type = Factory.getType();
}
