package org.apache.xmlbeans.impl.xb.xsdschema;

import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.impl.schema.SimpleTypeFactory;
import org.apache.xmlbeans.impl.xb.xsdschema.DerivationControl;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface TypeDerivationControl extends DerivationControl {
    public static final int INT_EXTENSION = 2;
    public static final int INT_LIST = 4;
    public static final int INT_RESTRICTION = 3;
    public static final int INT_UNION = 5;
    public static final SimpleTypeFactory<TypeDerivationControl> Factory = new SimpleTypeFactory<>(TypeSystemHolder.typeSystem, "typederivationcontrol3239type");
    public static final SchemaType type = Factory.getType();
    public static final DerivationControl.Enum EXTENSION = DerivationControl.EXTENSION;
    public static final DerivationControl.Enum RESTRICTION = DerivationControl.RESTRICTION;
    public static final DerivationControl.Enum LIST = DerivationControl.LIST;
    public static final DerivationControl.Enum UNION = DerivationControl.UNION;
}
