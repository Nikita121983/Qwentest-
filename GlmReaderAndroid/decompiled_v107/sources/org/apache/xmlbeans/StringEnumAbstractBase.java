package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes.dex */
public class StringEnumAbstractBase implements Serializable {
    private static final long serialVersionUID = 1;
    private int _int;
    private String _string;

    /* JADX INFO: Access modifiers changed from: protected */
    public StringEnumAbstractBase(String s, int i) {
        this._string = s;
        this._int = i;
    }

    public final String toString() {
        return this._string;
    }

    public final int intValue() {
        return this._int;
    }

    public final int hashCode() {
        return this._string.hashCode();
    }

    /* loaded from: classes.dex */
    public static final class Table {
        private List<StringEnumAbstractBase> _list;
        private Map<String, StringEnumAbstractBase> _map;

        public Table(StringEnumAbstractBase[] array) {
            this._map = new HashMap(array.length);
            this._list = new ArrayList(array.length + 1);
            for (int i = 0; i < array.length; i++) {
                this._map.put(array[i].toString(), array[i]);
                int j = array[i].intValue();
                while (this._list.size() <= j) {
                    this._list.add(null);
                }
                this._list.set(j, array[i]);
            }
        }

        public StringEnumAbstractBase forString(String s) {
            return this._map.get(s);
        }

        public StringEnumAbstractBase forInt(int i) {
            if (i < 0 || i > this._list.size()) {
                return null;
            }
            return this._list.get(i);
        }

        public int lastInt() {
            return this._list.size() - 1;
        }
    }
}
