package org.apache.commons.compress.harmony.unpack200;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.poi.openxml4j.opc.PackagingURIHelper;

/* loaded from: classes9.dex */
public class AttributeLayoutMap {
    private final Map<Integer, AttributeLayout> classLayouts = new HashMap();
    private final Map<Integer, AttributeLayout> fieldLayouts = new HashMap();
    private final Map<Integer, AttributeLayout> methodLayouts = new HashMap();
    private final Map<Integer, AttributeLayout> codeLayouts = new HashMap();
    private final Map<Integer, AttributeLayout>[] layouts = {this.classLayouts, this.fieldLayouts, this.methodLayouts, this.codeLayouts};
    private final Map<AttributeLayout, NewAttributeBands> layoutsToBands = new HashMap();

    private static AttributeLayout[] getDefaultAttributeLayouts() throws Pack200Exception {
        return new AttributeLayout[]{new AttributeLayout(AttributeLayout.ACC_PUBLIC, 0, "", 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 1, "", 0), new AttributeLayout(AttributeLayout.ACC_PUBLIC, 2, "", 0), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 0, "", 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 1, "", 1), new AttributeLayout(AttributeLayout.ACC_PRIVATE, 2, "", 1), new AttributeLayout(AttributeLayout.ATTRIBUTE_LINE_NUMBER_TABLE, 3, "NH[PHH]", 1), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 0, "", 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 1, "", 2), new AttributeLayout(AttributeLayout.ACC_PROTECTED, 2, "", 2), new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TABLE, 3, "NH[PHOHRUHRSHH]", 2), new AttributeLayout(AttributeLayout.ACC_STATIC, 0, "", 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 1, "", 3), new AttributeLayout(AttributeLayout.ACC_STATIC, 2, "", 3), new AttributeLayout(AttributeLayout.ATTRIBUTE_LOCAL_VARIABLE_TYPE_TABLE, 3, "NH[PHOHRUHRSHH]", 3), new AttributeLayout(AttributeLayout.ACC_FINAL, 0, "", 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 1, "", 4), new AttributeLayout(AttributeLayout.ACC_FINAL, 2, "", 4), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 0, "", 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 1, "", 5), new AttributeLayout(AttributeLayout.ACC_SYNCHRONIZED, 2, "", 5), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 0, "", 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 1, "", 6), new AttributeLayout(AttributeLayout.ACC_VOLATILE, 2, "", 6), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 0, "", 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 1, "", 7), new AttributeLayout(AttributeLayout.ACC_TRANSIENT, 2, "", 7), new AttributeLayout(AttributeLayout.ACC_NATIVE, 0, "", 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 1, "", 8), new AttributeLayout(AttributeLayout.ACC_NATIVE, 2, "", 8), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 0, "", 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 1, "", 9), new AttributeLayout(AttributeLayout.ACC_INTERFACE, 2, "", 9), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 0, "", 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 1, "", 10), new AttributeLayout(AttributeLayout.ACC_ABSTRACT, 2, "", 10), new AttributeLayout(AttributeLayout.ACC_STRICT, 0, "", 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 1, "", 11), new AttributeLayout(AttributeLayout.ACC_STRICT, 2, "", 11), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 0, "", 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 1, "", 12), new AttributeLayout(AttributeLayout.ACC_SYNTHETIC, 2, "", 12), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 0, "", 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 1, "", 13), new AttributeLayout(AttributeLayout.ACC_ANNOTATION, 2, "", 13), new AttributeLayout(AttributeLayout.ACC_ENUM, 0, "", 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 1, "", 14), new AttributeLayout(AttributeLayout.ACC_ENUM, 2, "", 14), new AttributeLayout(AttributeLayout.ATTRIBUTE_SOURCE_FILE, 0, "RUNH", 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_CONSTANT_VALUE, 1, "KQH", 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_CODE, 2, "", 17), new AttributeLayout(AttributeLayout.ATTRIBUTE_ENCLOSING_METHOD, 0, "RCHRDNH", 18), new AttributeLayout(AttributeLayout.ATTRIBUTE_EXCEPTIONS, 2, "NH[RCH]", 18), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 0, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 1, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_SIGNATURE, 2, "RSH", 19), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 0, "", 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 1, "", 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_DEPRECATED, 2, "", 20), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 0, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 1, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_ANNOTATIONS, 2, "*", 21), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 0, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 1, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_ANNOTATIONS, 2, "*", 22), new AttributeLayout(AttributeLayout.ATTRIBUTE_INNER_CLASSES, 0, "", 23), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_VISIBLE_PARAMETER_ANNOTATIONS, 2, "*", 23), new AttributeLayout(AttributeLayout.ATTRIBUTE_CLASS_FILE_VERSION, 0, "", 24), new AttributeLayout(AttributeLayout.ATTRIBUTE_RUNTIME_INVISIBLE_PARAMETER_ANNOTATIONS, 2, "*", 24), new AttributeLayout(AttributeLayout.ATTRIBUTE_ANNOTATION_DEFAULT, 2, "*", 25)};
    }

    public AttributeLayoutMap() throws Pack200Exception {
        for (AttributeLayout defaultAttributeLayout : getDefaultAttributeLayouts()) {
            add(defaultAttributeLayout);
        }
    }

    public void add(AttributeLayout layout) {
        getLayout(layout.getContext()).put(Integer.valueOf(layout.getIndex()), layout);
    }

    public void add(AttributeLayout layout, NewAttributeBands newBands) {
        add(layout);
        this.layoutsToBands.put(layout, newBands);
    }

    public void checkMap() throws Pack200Exception {
        for (Map<Integer, AttributeLayout> map : this.layouts) {
            Collection<AttributeLayout> c = map.values();
            if (!(c instanceof List)) {
                c = new ArrayList(c);
            }
            List<AttributeLayout> layouts = (List) c;
            for (int j = 0; j < layouts.size(); j++) {
                AttributeLayout layout1 = layouts.get(j);
                for (int j2 = j + 1; j2 < layouts.size(); j2++) {
                    AttributeLayout layout2 = layouts.get(j2);
                    if (layout1.getName().equals(layout2.getName()) && layout1.getLayout().equals(layout2.getLayout())) {
                        throw new Pack200Exception("Same layout/name combination: " + layout1.getLayout() + PackagingURIHelper.FORWARD_SLASH_STRING + layout1.getName() + " exists twice for context: " + AttributeLayout.contextNames[layout1.getContext()]);
                    }
                }
            }
        }
    }

    public NewAttributeBands getAttributeBands(AttributeLayout layout) {
        return this.layoutsToBands.get(layout);
    }

    public AttributeLayout getAttributeLayout(int index, int context) {
        Map<Integer, AttributeLayout> map = getLayout(context);
        return map.get(Integer.valueOf(index));
    }

    public AttributeLayout getAttributeLayout(String name, int context) {
        Map<Integer, AttributeLayout> map = getLayout(context);
        for (AttributeLayout layout : map.values()) {
            if (layout.getName().equals(name)) {
                return layout;
            }
        }
        return null;
    }

    private Map<Integer, AttributeLayout> getLayout(int context) {
        return this.layouts[context];
    }
}
