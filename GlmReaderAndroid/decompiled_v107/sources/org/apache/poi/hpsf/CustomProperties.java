package org.apache.poi.hpsf;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Unbox;
import org.apache.poi.logging.PoiLogManager;
import org.apache.poi.util.CodePageUtil;

/* loaded from: classes10.dex */
public class CustomProperties implements Map<String, Object> {
    private static final Logger LOG = PoiLogManager.getLogger((Class<?>) CustomProperties.class);
    private final HashMap<Long, CustomProperty> props = new HashMap<>();
    private final TreeBidiMap<Long, String> dictionary = new TreeBidiMap<>();
    private boolean isPure = true;
    private int codepage = -1;

    public CustomProperty put(String name, CustomProperty cp) {
        if (name == null) {
            this.isPure = false;
            return null;
        }
        if (!name.equals(cp.getName())) {
            throw new IllegalArgumentException("Parameter \"name\" (" + name + ") and custom property's name (" + cp.getName() + ") do not match.");
        }
        checkCodePage(name);
        this.props.remove(this.dictionary.getKey((Object) name));
        this.dictionary.put((TreeBidiMap<Long, String>) Long.valueOf(cp.getID()), (Long) name);
        return this.props.put(Long.valueOf(cp.getID()), cp);
    }

    @Override // java.util.Map
    public Object put(String key, Object value) {
        int variantType;
        if (value instanceof String) {
            variantType = 30;
        } else if (value instanceof Short) {
            variantType = 2;
        } else if (value instanceof Integer) {
            variantType = 3;
        } else if (value instanceof Long) {
            variantType = 20;
        } else if (value instanceof Float) {
            variantType = 4;
        } else if (value instanceof Double) {
            variantType = 5;
        } else if (value instanceof Boolean) {
            variantType = 11;
        } else if ((value instanceof BigInteger) && ((BigInteger) value).bitLength() <= 64 && ((BigInteger) value).compareTo(BigInteger.ZERO) >= 0) {
            variantType = 21;
        } else {
            if (!(value instanceof java.util.Date)) {
                throw new IllegalStateException("unsupported datatype - currently String,Short,Integer,Long,Float,Double,Boolean,BigInteger(unsigned long),Date can be processed.");
            }
            variantType = 64;
        }
        Property p = new Property(-1L, variantType, value);
        return put(new CustomProperty(p, key));
    }

    @Override // java.util.Map
    public Object get(Object key) {
        Long id = this.dictionary.getKey(key);
        CustomProperty cp = this.props.get(id);
        if (cp != null) {
            return cp.getValue();
        }
        return null;
    }

    @Override // java.util.Map
    public Object remove(Object key) {
        Long id = this.dictionary.removeValue(key);
        return this.props.remove(id);
    }

    @Override // java.util.Map
    public int size() {
        return this.props.size();
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.props.isEmpty();
    }

    @Override // java.util.Map
    public void clear() {
        this.props.clear();
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.props.hashCode();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return (obj instanceof CustomProperties) && this.props.equals(((CustomProperties) obj).props);
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        for (Map.Entry<? extends String, ?> me : map.entrySet()) {
            put(me.getKey(), me.getValue());
        }
    }

    public List<CustomProperty> properties() {
        List<CustomProperty> list = new ArrayList<>(this.props.size());
        list.addAll(this.props.values());
        return Collections.unmodifiableList(list);
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        List<Object> list = new ArrayList<>(this.props.size());
        for (CustomProperty property : this.props.values()) {
            list.add(property.getValue());
        }
        return Collections.unmodifiableCollection(list);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        Map<String, Object> set = new LinkedHashMap<>(this.props.size());
        for (CustomProperty property : this.props.values()) {
            set.put(property.getName(), property.getValue());
        }
        return Collections.unmodifiableSet(set.entrySet());
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.dictionary.values());
    }

    public Set<String> nameSet() {
        return Collections.unmodifiableSet(this.dictionary.values());
    }

    public Set<Long> idSet() {
        return Collections.unmodifiableSet(this.dictionary.keySet());
    }

    public void setCodepage(int codepage) {
        this.codepage = codepage;
    }

    public int getCodepage() {
        return this.codepage;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Map<Long, String> getDictionary() {
        return this.dictionary;
    }

    @Override // java.util.Map
    public boolean containsKey(Object key) {
        return ((key instanceof Long) && this.dictionary.containsKey(key)) || this.dictionary.containsValue(key);
    }

    @Override // java.util.Map
    public boolean containsValue(Object value) {
        if (value instanceof CustomProperty) {
            return this.props.containsValue(value);
        }
        for (CustomProperty cp : this.props.values()) {
            if (cp.getValue() == value) {
                return true;
            }
        }
        return false;
    }

    public boolean isPure() {
        return this.isPure;
    }

    public void setPure(boolean isPure) {
        this.isPure = isPure;
    }

    private Object put(CustomProperty customProperty) throws ClassCastException {
        String name = customProperty.getName();
        Long oldId = name == null ? null : this.dictionary.getKey((Object) name);
        if (oldId != null) {
            customProperty.setID(oldId.longValue());
        } else {
            long lastKey = this.dictionary.isEmpty() ? 0L : this.dictionary.lastKey().longValue();
            long nextKey = Math.max(lastKey, 31L) + 1;
            customProperty.setID(nextKey);
        }
        return put(name, customProperty);
    }

    private void checkCodePage(String value) {
        int cp = getCodepage();
        if (cp == -1) {
            cp = 1252;
        }
        if (cp == 1200) {
            return;
        }
        String cps = "";
        try {
            cps = CodePageUtil.codepageToEncoding(cp, false);
        } catch (UnsupportedEncodingException e) {
            LOG.atError().log("Codepage '{}' can't be found.", Unbox.box(cp));
        }
        if (!cps.isEmpty() && Charset.forName(cps).newEncoder().canEncode(value)) {
            return;
        }
        LOG.atDebug().log("Charset '{}' can't encode '{}' - switching to unicode.", cps, value);
        setCodepage(1200);
    }
}
