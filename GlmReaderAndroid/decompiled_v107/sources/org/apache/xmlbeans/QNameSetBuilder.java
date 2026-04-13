package org.apache.xmlbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.logging.log4j.util.ProcessIdUtil;

/* loaded from: classes.dex */
public class QNameSetBuilder implements QNameSetSpecification, Serializable {
    private static final String[] EMPTY_STRINGARRAY = new String[0];
    private static final long serialVersionUID = 1;
    private Set<QName> _excludedQNames;
    private Set<QName> _includedQNames;
    private Set<String> _includedURIs;
    private boolean _inverted;

    public QNameSetBuilder() {
        this._inverted = false;
        this._includedURIs = new HashSet();
        this._excludedQNames = new HashSet();
        this._includedQNames = new HashSet();
    }

    public QNameSetBuilder(QNameSetSpecification set) {
        Set<String> includedURIs = set.includedURIs();
        if (includedURIs != null) {
            this._inverted = false;
            this._includedURIs = new HashSet(includedURIs);
            this._excludedQNames = new HashSet(set.excludedQNamesInIncludedURIs());
            this._includedQNames = new HashSet(set.includedQNamesInExcludedURIs());
            return;
        }
        this._inverted = true;
        this._includedURIs = new HashSet(set.excludedURIs());
        this._excludedQNames = new HashSet(set.includedQNamesInExcludedURIs());
        this._includedQNames = new HashSet(set.excludedQNamesInIncludedURIs());
    }

    public QNameSetBuilder(Set<String> excludedURIs, Set<String> includedURIs, Set<QName> excludedQNamesInIncludedURIs, Set<QName> includedQNamesInExcludedURIs) {
        if (includedURIs != null && excludedURIs == null) {
            this._inverted = false;
            this._includedURIs = new HashSet(includedURIs);
            this._excludedQNames = new HashSet(excludedQNamesInIncludedURIs);
            this._includedQNames = new HashSet(includedQNamesInExcludedURIs);
            return;
        }
        if (excludedURIs != null && includedURIs == null) {
            this._inverted = true;
            this._includedURIs = new HashSet(excludedURIs);
            this._excludedQNames = new HashSet(includedQNamesInExcludedURIs);
            this._includedQNames = new HashSet(excludedQNamesInIncludedURIs);
            return;
        }
        throw new IllegalArgumentException("Exactly one of excludedURIs and includedURIs must be null");
    }

    public QNameSetBuilder(String str, String targetURI) {
        this();
        String[] uri = splitList(str == null ? "##any" : str);
        for (int i = 0; i < uri.length; i++) {
            String adduri = uri[i];
            if (adduri.startsWith("##")) {
                if (adduri.equals("##other")) {
                    if (targetURI == null) {
                        throw new IllegalArgumentException();
                    }
                    QNameSetBuilder temp = new QNameSetBuilder();
                    temp.addNamespace(targetURI);
                    temp.addNamespace("");
                    temp.invert();
                    addAll(temp);
                } else if (adduri.equals("##any")) {
                    clear();
                    invert();
                } else if (uri[i].equals("##targetNamespace")) {
                    if (targetURI == null) {
                        throw new IllegalArgumentException();
                    }
                    adduri = targetURI;
                } else if (uri[i].equals("##local")) {
                    adduri = "";
                }
            }
            addNamespace(adduri);
        }
    }

    private static String nsFromName(QName QName) {
        String ns = QName.getNamespaceURI();
        return ns == null ? "" : ns;
    }

    private static boolean isSpace(char ch) {
        switch (ch) {
            case '\t':
            case '\n':
            case '\r':
            case ' ':
                return true;
            default:
                return false;
        }
    }

    private static String[] splitList(String s) {
        if (s.isEmpty()) {
            return EMPTY_STRINGARRAY;
        }
        List<String> result = new ArrayList<>();
        int i = 0;
        while (true) {
            if (i < s.length() && isSpace(s.charAt(i))) {
                i++;
            } else {
                if (i >= s.length()) {
                    return (String[]) result.toArray(EMPTY_STRINGARRAY);
                }
                int start = i;
                while (i < s.length() && !isSpace(s.charAt(i))) {
                    i++;
                }
                result.add(s.substring(start, i));
            }
        }
    }

    private static void removeAllMatchingNs(String uri, Set<QName> qnameset) {
        Iterator<QName> i = qnameset.iterator();
        while (i.hasNext()) {
            if (uri.equals(nsFromName(i.next()))) {
                i.remove();
            }
        }
    }

    private static void removeAllMatchingFirstOnly(Set<String> setFirst, Set<String> setSecond, Set<QName> qnameset) {
        Iterator<QName> i = qnameset.iterator();
        while (i.hasNext()) {
            String ns = nsFromName(i.next());
            if (setFirst.contains(ns) && !setSecond.contains(ns)) {
                i.remove();
            }
        }
    }

    private static void removeAllMatchingBoth(Set<String> setFirst, Set<String> setSecond, Set<QName> qnameset) {
        Iterator<QName> i = qnameset.iterator();
        while (i.hasNext()) {
            String ns = nsFromName(i.next());
            if (setFirst.contains(ns) && setSecond.contains(ns)) {
                i.remove();
            }
        }
    }

    private static void removeAllMatchingNeither(Set<String> setFirst, Set<String> setSecond, Set<QName> qnameset) {
        Iterator<QName> i = qnameset.iterator();
        while (i.hasNext()) {
            String ns = nsFromName(i.next());
            if (!setFirst.contains(ns) && !setSecond.contains(ns)) {
                i.remove();
            }
        }
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public boolean contains(QName name) {
        boolean in;
        if (this._includedURIs.contains(nsFromName(name))) {
            in = !this._excludedQNames.contains(name);
        } else {
            in = this._includedQNames.contains(name);
        }
        return this._inverted ^ in;
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public boolean isAll() {
        return this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public boolean isEmpty() {
        return !this._inverted && this._includedURIs.isEmpty() && this._includedQNames.isEmpty();
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public QNameSet intersect(QNameSetSpecification set) {
        QNameSetBuilder result = new QNameSetBuilder(this);
        result.restrict(set);
        return result.toQNameSet();
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public QNameSet union(QNameSetSpecification set) {
        QNameSetBuilder result = new QNameSetBuilder(this);
        result.addAll(set);
        return result.toQNameSet();
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public QNameSet inverse() {
        return QNameSet.forSets(includedURIs(), excludedURIs(), includedQNamesInExcludedURIs(), excludedQNamesInIncludedURIs());
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public boolean containsAll(QNameSetSpecification set) {
        if (!this._inverted && set.excludedURIs() != null) {
            return false;
        }
        return inverse().isDisjoint(set);
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public boolean isDisjoint(QNameSetSpecification set) {
        if (this._inverted && set.excludedURIs() != null) {
            return false;
        }
        if (this._inverted) {
            return isDisjointImpl(set, this);
        }
        return isDisjointImpl(this, set);
    }

    private boolean isDisjointImpl(QNameSetSpecification set1, QNameSetSpecification set2) {
        Set<String> includeURIs = set1.includedURIs();
        Set<String> otherIncludeURIs = set2.includedURIs();
        if (otherIncludeURIs != null) {
            Iterator<String> i = includeURIs.iterator();
            while (i.hasNext()) {
                if (otherIncludeURIs.contains(i.next())) {
                    return false;
                }
            }
        } else {
            Set<String> otherExcludeURIs = set2.excludedURIs();
            Iterator<String> i2 = includeURIs.iterator();
            while (i2.hasNext()) {
                if (!otherExcludeURIs.contains(i2.next())) {
                    return false;
                }
            }
        }
        Iterator<QName> i3 = set1.includedQNamesInExcludedURIs().iterator();
        while (i3.hasNext()) {
            if (set2.contains(i3.next())) {
                return false;
            }
        }
        if (!includeURIs.isEmpty()) {
            Iterator<QName> i4 = set2.includedQNamesInExcludedURIs().iterator();
            while (i4.hasNext()) {
                if (set1.contains(i4.next())) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void clear() {
        this._inverted = false;
        this._includedURIs.clear();
        this._excludedQNames.clear();
        this._includedQNames.clear();
    }

    public void invert() {
        this._inverted = !this._inverted;
    }

    public void add(QName qname) {
        if (!this._inverted) {
            addImpl(qname);
        } else {
            removeImpl(qname);
        }
    }

    public void addNamespace(String uri) {
        if (!this._inverted) {
            addNamespaceImpl(uri);
        } else {
            removeNamespaceImpl(uri);
        }
    }

    public void addAll(QNameSetSpecification set) {
        if (this._inverted) {
            removeAllImpl(set.includedURIs(), set.excludedURIs(), set.includedQNamesInExcludedURIs(), set.excludedQNamesInIncludedURIs());
        } else {
            addAllImpl(set.includedURIs(), set.excludedURIs(), set.includedQNamesInExcludedURIs(), set.excludedQNamesInIncludedURIs());
        }
    }

    public void remove(QName qname) {
        if (this._inverted) {
            addImpl(qname);
        } else {
            removeImpl(qname);
        }
    }

    public void removeNamespace(String uri) {
        if (this._inverted) {
            addNamespaceImpl(uri);
        } else {
            removeNamespaceImpl(uri);
        }
    }

    public void removeAll(QNameSetSpecification set) {
        if (this._inverted) {
            addAllImpl(set.includedURIs(), set.excludedURIs(), set.includedQNamesInExcludedURIs(), set.excludedQNamesInIncludedURIs());
        } else {
            removeAllImpl(set.includedURIs(), set.excludedURIs(), set.includedQNamesInExcludedURIs(), set.excludedQNamesInIncludedURIs());
        }
    }

    public void restrict(QNameSetSpecification set) {
        if (this._inverted) {
            addAllImpl(set.excludedURIs(), set.includedURIs(), set.excludedQNamesInIncludedURIs(), set.includedQNamesInExcludedURIs());
        } else {
            removeAllImpl(set.excludedURIs(), set.includedURIs(), set.excludedQNamesInIncludedURIs(), set.includedQNamesInExcludedURIs());
        }
    }

    private void addImpl(QName qname) {
        if (this._includedURIs.contains(nsFromName(qname))) {
            this._excludedQNames.remove(qname);
        } else {
            this._includedQNames.add(qname);
        }
    }

    private void addNamespaceImpl(String uri) {
        if (this._includedURIs.contains(uri)) {
            removeAllMatchingNs(uri, this._excludedQNames);
        } else {
            removeAllMatchingNs(uri, this._includedQNames);
            this._includedURIs.add(uri);
        }
    }

    private void addAllImpl(Set<String> includedURIs, Set<String> excludedURIs, Set<QName> includedQNames, Set<QName> excludedQNames) {
        boolean exclude = excludedURIs != null;
        Set<String> specialURIs = exclude ? excludedURIs : includedURIs;
        Iterator<QName> i = this._excludedQNames.iterator();
        while (i.hasNext()) {
            QName name = i.next();
            if ((specialURIs.contains(nsFromName(name)) ^ exclude) && !excludedQNames.contains(name)) {
                i.remove();
            }
        }
        for (QName name2 : excludedQNames) {
            if (!this._includedURIs.contains(nsFromName(name2)) && !this._includedQNames.contains(name2)) {
                this._excludedQNames.add(name2);
            }
        }
        for (QName name3 : includedQNames) {
            if (!this._includedURIs.contains(nsFromName(name3))) {
                this._includedQNames.add(name3);
            } else {
                this._excludedQNames.remove(name3);
            }
        }
        if (!exclude) {
            removeAllMatchingFirstOnly(includedURIs, this._includedURIs, this._includedQNames);
            this._includedURIs.addAll(includedURIs);
            return;
        }
        removeAllMatchingNeither(excludedURIs, this._includedURIs, this._includedQNames);
        Iterator<String> i2 = this._includedURIs.iterator();
        while (i2.hasNext()) {
            if (!excludedURIs.contains(i2.next())) {
                i2.remove();
            }
        }
        for (String uri : excludedURIs) {
            if (!this._includedURIs.contains(uri)) {
                this._includedURIs.add(uri);
            } else {
                this._includedURIs.remove(uri);
            }
        }
        Set<QName> temp = this._excludedQNames;
        this._excludedQNames = this._includedQNames;
        this._includedQNames = temp;
        this._inverted = true ^ this._inverted;
    }

    private void removeImpl(QName qname) {
        if (this._includedURIs.contains(nsFromName(qname))) {
            this._excludedQNames.add(qname);
        } else {
            this._includedQNames.remove(qname);
        }
    }

    private void removeNamespaceImpl(String uri) {
        if (this._includedURIs.contains(uri)) {
            removeAllMatchingNs(uri, this._excludedQNames);
            this._includedURIs.remove(uri);
        } else {
            removeAllMatchingNs(uri, this._includedQNames);
        }
    }

    private void removeAllImpl(Set<String> includedURIs, Set<String> excludedURIs, Set<QName> includedQNames, Set<QName> excludedQNames) {
        boolean exclude = excludedURIs != null;
        Set<String> specialURIs = exclude ? excludedURIs : includedURIs;
        Iterator<QName> i = this._includedQNames.iterator();
        while (i.hasNext()) {
            QName name = i.next();
            String uri = nsFromName(name);
            if (specialURIs.contains(uri) ^ exclude) {
                if (!excludedQNames.contains(name)) {
                    i.remove();
                }
            } else if (includedQNames.contains(name)) {
                i.remove();
            }
        }
        for (QName name2 : includedQNames) {
            String uri2 = nsFromName(name2);
            if (this._includedURIs.contains(uri2)) {
                this._excludedQNames.add(name2);
            }
        }
        for (QName name3 : excludedQNames) {
            String uri3 = nsFromName(name3);
            if (this._includedURIs.contains(uri3) && !this._excludedQNames.contains(name3)) {
                this._includedQNames.add(name3);
            }
        }
        if (exclude) {
            removeAllMatchingFirstOnly(this._includedURIs, excludedURIs, this._excludedQNames);
        } else {
            removeAllMatchingBoth(this._includedURIs, includedURIs, this._excludedQNames);
        }
        Iterator<String> i2 = this._includedURIs.iterator();
        while (i2.hasNext()) {
            if (specialURIs.contains(i2.next()) ^ exclude) {
                i2.remove();
            }
        }
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public Set<String> excludedURIs() {
        if (this._inverted) {
            return Collections.unmodifiableSet(this._includedURIs);
        }
        return null;
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public Set<String> includedURIs() {
        if (this._inverted) {
            return null;
        }
        return this._includedURIs;
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public Set<QName> excludedQNamesInIncludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._includedQNames : this._excludedQNames);
    }

    @Override // org.apache.xmlbeans.QNameSetSpecification
    public Set<QName> includedQNamesInExcludedURIs() {
        return Collections.unmodifiableSet(this._inverted ? this._excludedQNames : this._includedQNames);
    }

    private String prettyQName(QName name) {
        if (name.getNamespaceURI() == null) {
            return name.getLocalPart();
        }
        return name.getLocalPart() + "@" + name.getNamespaceURI();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("QNameSetBuilder");
        sb.append(this._inverted ? "-(" : "+(");
        Iterator<String> i = this._includedURIs.iterator();
        while (i.hasNext()) {
            sb.append("+*@");
            sb.append(i.next());
            sb.append(", ");
        }
        Iterator<QName> i2 = this._excludedQNames.iterator();
        while (i2.hasNext()) {
            sb.append(ProcessIdUtil.DEFAULT_PROCESSID);
            sb.append(prettyQName(i2.next()));
            sb.append(", ");
        }
        Iterator<QName> i3 = this._includedQNames.iterator();
        while (i3.hasNext()) {
            sb.append("+");
            sb.append(prettyQName(i3.next()));
            sb.append(", ");
        }
        int index = sb.lastIndexOf(", ");
        if (index > 0) {
            sb.setLength(index);
        }
        sb.append(')');
        return sb.toString();
    }

    public QNameSet toQNameSet() {
        return QNameSet.forSpecification(this);
    }
}
