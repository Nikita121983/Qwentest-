package com.microsoft.schemas.office.office;

import com.microsoft.schemas.vml.STExt;
import org.apache.poi.schemas.ooxml.system.ooxml.TypeSystemHolder;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;

/* loaded from: classes.dex */
public interface CTShapeLayout extends XmlObject {
    public static final DocumentFactory<CTShapeLayout> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "ctshapelayoutbda4type");
    public static final SchemaType type = Factory.getType();

    CTIdMap addNewIdmap();

    CTRegroupTable addNewRegrouptable();

    CTRules addNewRules();

    STExt.Enum getExt();

    CTIdMap getIdmap();

    CTRegroupTable getRegrouptable();

    CTRules getRules();

    boolean isSetExt();

    boolean isSetIdmap();

    boolean isSetRegrouptable();

    boolean isSetRules();

    void setExt(STExt.Enum r1);

    void setIdmap(CTIdMap cTIdMap);

    void setRegrouptable(CTRegroupTable cTRegroupTable);

    void setRules(CTRules cTRules);

    void unsetExt();

    void unsetIdmap();

    void unsetRegrouptable();

    void unsetRules();

    STExt xgetExt();

    void xsetExt(STExt sTExt);
}
