package org.apache.xmlbeans.impl.values;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.apache.commons.lang3.StringUtils;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.SimpleValue;
import org.apache.xmlbeans.XmlAnySimpleType;
import org.apache.xmlbeans.XmlErrorCodes;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlSimpleList;
import org.apache.xmlbeans.impl.common.PrefixResolver;
import org.apache.xmlbeans.impl.common.QNameHelper;
import org.apache.xmlbeans.impl.common.ValidationContext;
import org.apache.xmlbeans.impl.common.XMLChar;

/* loaded from: classes11.dex */
public class XmlListImpl extends XmlObjectBase implements XmlAnySimpleType {
    private static final String[] EMPTY_STRINGARRAY = new String[0];
    private XmlSimpleList<?> _jvalue;
    private final SchemaType _schemaType;
    private XmlSimpleList<? extends XmlAnySimpleType> _value;

    public XmlListImpl(SchemaType type, boolean complex) {
        this._schemaType = type;
        initComplexType(complex, false);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.XmlObject
    public SchemaType schemaType() {
        return this._schemaType;
    }

    private static String compute_list_text(List<? extends XmlAnySimpleType> xList) {
        return xList.isEmpty() ? "" : (String) xList.stream().map(new Function() { // from class: org.apache.xmlbeans.impl.values.XmlListImpl$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                String object2String;
                object2String = XmlListImpl.object2String((XmlAnySimpleType) obj);
                return object2String;
            }
        }).collect(Collectors.joining(StringUtils.SPACE));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String object2String(Object o) {
        String s = o instanceof SimpleValue ? ((SimpleValue) o).getStringValue() : o.toString();
        return s == null ? "" : s;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected String compute_text(NamespaceManager nsm) {
        return compute_list_text(this._value);
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean is_defaultable_ws(String v) {
        try {
            XmlSimpleList<? extends XmlAnySimpleType> savedValue = this._value;
            set_text(v);
            this._value = savedValue;
            return false;
        } catch (XmlValueOutOfRangeException e) {
            return true;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_text(String s) {
        if (_validateOnSet() && !this._schemaType.matchPatternFacet(s)) {
            throw new XmlValueOutOfRangeException(XmlErrorCodes.DATATYPE_VALID$PATTERN_VALID, new Object[]{XmlErrorCodes.LIST, s, QNameHelper.readable(this._schemaType)});
        }
        SchemaType itemType = this._schemaType.getListItemType();
        XmlSimpleList<? extends XmlAnySimpleType> newval = lex(s, itemType, _voorVc, has_store() ? get_store() : null);
        if (_validateOnSet()) {
            validateValue(newval, this._schemaType, _voorVc);
        }
        this._value = newval;
        this._jvalue = null;
    }

    public static String[] split_list(String s) {
        if (s.isEmpty()) {
            return EMPTY_STRINGARRAY;
        }
        List<String> result = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i < s.length() && XMLChar.isSpace(s.charAt(i))) {
                i++;
            } else {
                if (i >= s.length()) {
                    return (String[]) result.toArray(EMPTY_STRINGARRAY);
                }
                int start = i;
                while (i < s.length() && !XMLChar.isSpace(s.charAt(i))) {
                    i++;
                }
                result.add(s.substring(start, i));
            }
        }
    }

    public static XmlSimpleList<? extends XmlAnySimpleType> lex(String s, final SchemaType itemType, final ValidationContext ctx, PrefixResolver resolver) {
        String[] parts = split_list(s);
        Function<String, XmlAnySimpleType> fun = new Function() { // from class: org.apache.xmlbeans.impl.values.XmlListImpl$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XmlListImpl.lambda$lex$0(SchemaType.this, ctx, (String) obj);
            }
        };
        boolean pushed = false;
        if (resolver != null) {
            NamespaceContext.push(new NamespaceContext(resolver));
            pushed = true;
        }
        try {
            List<? extends XmlAnySimpleType> list = (List) Stream.of((Object[]) parts).map(fun).collect(Collectors.toList());
            return new XmlSimpleList<>(list);
        } finally {
            if (pushed) {
                NamespaceContext.pop();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlAnySimpleType lambda$lex$0(SchemaType itemType, ValidationContext ctx, String str) {
        try {
            return itemType.newValue(str);
        } catch (XmlValueOutOfRangeException e) {
            Object[] obj = {"item '" + str + "' is not a valid value of " + QNameHelper.readable(itemType)};
            ctx.invalid(XmlErrorCodes.LIST, obj);
            return null;
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected void set_nil() {
        this._value = null;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public XmlSimpleList<? extends XmlAnySimpleType> xgetListValue() {
        check_dated();
        return this._value;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase, org.apache.xmlbeans.SimpleValue
    public List<?> getListValue() {
        check_dated();
        if (this._value == null) {
            return null;
        }
        if (this._jvalue != null) {
            return this._jvalue;
        }
        List<Object> javaResult = new ArrayList<>();
        Iterator<? extends XmlAnySimpleType> it = this._value.iterator();
        while (it.hasNext()) {
            Object o = it.next();
            javaResult.add(java_value((XmlObject) o));
        }
        this._jvalue = new XmlSimpleList<>(javaResult);
        return this._jvalue;
    }

    private static boolean permits_inner_space(XmlObject obj) {
        switch (((SimpleValue) obj).instanceType().getPrimitiveType().getBuiltinTypeCode()) {
            case 1:
            case 2:
            case 6:
            case 12:
                return true;
            default:
                return false;
        }
    }

    private static boolean contains_white_space(String s) {
        return s.indexOf(32) >= 0 || s.indexOf(9) >= 0 || s.indexOf(10) >= 0 || s.indexOf(13) >= 0;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void set_list(List<?> list) {
        final SchemaType itemType = this._schemaType.getListItemType();
        boolean pushed = false;
        if (has_store()) {
            NamespaceContext.push(new NamespaceContext(get_store()));
            pushed = true;
        }
        Function<Object, XmlAnySimpleType> fun = new Function() { // from class: org.apache.xmlbeans.impl.values.XmlListImpl$$ExternalSyntheticLambda1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return XmlListImpl.lambda$set_list$1(SchemaType.this, obj);
            }
        };
        try {
            XmlSimpleList<? extends XmlAnySimpleType> xList = new XmlSimpleList<>((List) list.stream().map(fun).collect(Collectors.toList()));
            if (_validateOnSet()) {
                validateValue(xList, this._schemaType, _voorVc);
            }
            this._value = xList;
            this._jvalue = null;
        } finally {
            if (pushed) {
                NamespaceContext.pop();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ XmlAnySimpleType lambda$set_list$1(SchemaType itemType, Object entry) {
        if ((entry instanceof XmlObject) && permits_inner_space((XmlObject) entry)) {
            String stringrep = entry.toString();
            if (contains_white_space(stringrep)) {
                throw new XmlValueOutOfRangeException();
            }
        }
        return itemType.newValue(entry);
    }

    public static void validateValue(XmlSimpleList<? extends XmlAnySimpleType> items, SchemaType sType, ValidationContext context) {
        int i;
        int i2;
        int i3;
        XmlObject[] enumvals = sType.getEnumerationValues();
        if (enumvals != null) {
            int length = enumvals.length;
            int i4 = 0;
            while (true) {
                if (i4 < length) {
                    XmlObject enumval = enumvals[i4];
                    if (equal_xmlLists(items, ((XmlObjectBase) enumval).xgetListValue())) {
                        break;
                    } else {
                        i4++;
                    }
                } else {
                    context.invalid(XmlErrorCodes.DATATYPE_ENUM_VALID, new Object[]{XmlErrorCodes.LIST, items, QNameHelper.readable(sType)});
                    break;
                }
            }
        }
        XmlObject o = sType.getFacet(0);
        if (o != null && (i3 = ((SimpleValue) o).getIntValue()) != items.size()) {
            context.invalid(XmlErrorCodes.DATATYPE_LENGTH_VALID$LIST_LENGTH, new Object[]{items, Integer.valueOf(items.size()), Integer.valueOf(i3), QNameHelper.readable(sType)});
        }
        XmlObject o2 = sType.getFacet(1);
        if (o2 != null && (i2 = ((SimpleValue) o2).getIntValue()) > items.size()) {
            context.invalid(XmlErrorCodes.DATATYPE_MIN_LENGTH_VALID$LIST_LENGTH, new Object[]{items, Integer.valueOf(items.size()), Integer.valueOf(i2), QNameHelper.readable(sType)});
        }
        XmlObject o3 = sType.getFacet(2);
        if (o3 != null && (i = ((SimpleValue) o3).getIntValue()) < items.size()) {
            context.invalid(XmlErrorCodes.DATATYPE_MAX_LENGTH_VALID$LIST_LENGTH, new Object[]{items, Integer.valueOf(items.size()), Integer.valueOf(i), QNameHelper.readable(sType)});
        }
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected boolean equal_to(XmlObject obj) {
        return equal_xmlLists(this._value, ((XmlObjectBase) obj).xgetListValue());
    }

    private static boolean equal_xmlLists(List<?> a, List<?> b) {
        if (a.size() != b.size()) {
            return false;
        }
        for (int i = 0; i < a.size(); i++) {
            if (!a.get(i).equals(b.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    protected int value_hash_code() {
        if (this._value == null) {
            return 0;
        }
        int hash = this._value.size();
        int incr = this._value.size() / 9;
        if (incr < 1) {
            incr = 1;
        }
        for (int i = 0; i < this._value.size(); i += incr) {
            hash = (hash * 19) + this._value.get(i).hashCode();
        }
        return hash;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.apache.xmlbeans.impl.values.XmlObjectBase
    public void validate_simpleval(String lexical, ValidationContext ctx) {
        validateValue(xgetListValue(), schemaType(), ctx);
    }
}
