package org.apache.xmlbeans.impl.xb.xsdschema;

import java.util.List;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.schema.DocumentFactory;
import org.apache.xmlbeans.impl.schema.ElementFactory;
import org.apache.xmlbeans.metadata.system.sXMLSCHEMA.TypeSystemHolder;

/* loaded from: classes11.dex */
public interface UnionDocument extends XmlObject {
    public static final DocumentFactory<UnionDocument> Factory = new DocumentFactory<>(TypeSystemHolder.typeSystem, "union5866doctype");
    public static final SchemaType type = Factory.getType();

    /* loaded from: classes11.dex */
    public interface Union extends Annotated {
        public static final ElementFactory<Union> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "union498belemtype");
        public static final SchemaType type = Factory.getType();

        /* loaded from: classes11.dex */
        public interface MemberTypes extends XmlAnySimpleType {
            public static final ElementFactory<MemberTypes> Factory = new ElementFactory<>(TypeSystemHolder.typeSystem, "membertypes2404attrtype");
            public static final SchemaType type = Factory.getType();

            List getListValue();

            void setListValue(List<?> list);

            List xgetListValue();
        }

        LocalSimpleType addNewSimpleType();

        List getMemberTypes();

        LocalSimpleType getSimpleTypeArray(int i);

        LocalSimpleType[] getSimpleTypeArray();

        List<LocalSimpleType> getSimpleTypeList();

        LocalSimpleType insertNewSimpleType(int i);

        boolean isSetMemberTypes();

        void removeSimpleType(int i);

        void setMemberTypes(List list);

        void setSimpleTypeArray(int i, LocalSimpleType localSimpleType);

        void setSimpleTypeArray(LocalSimpleType[] localSimpleTypeArr);

        int sizeOfSimpleTypeArray();

        void unsetMemberTypes();

        MemberTypes xgetMemberTypes();

        void xsetMemberTypes(MemberTypes memberTypes);
    }

    Union addNewUnion();

    Union getUnion();

    void setUnion(Union union);
}
