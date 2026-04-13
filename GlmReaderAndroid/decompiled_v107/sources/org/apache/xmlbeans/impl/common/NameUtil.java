package org.apache.xmlbeans.impl.common;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import javax.xml.namespace.QName;
import org.apache.poi.extractor.ExtractorFactory;
import org.apache.poi.ss.formula.functions.Complex;
import org.apache.xmlbeans.XmlErrorCodes;

/* loaded from: classes11.dex */
public class NameUtil {
    public static final char AYAH = 1757;
    public static final char COLON = ':';
    private static final int DIGIT = 2;
    public static final char DOT = 183;
    public static final char ELHIZB = 1758;
    public static final char HYPHEN = '-';
    private static final String JAVA_NS_PREFIX = "java:";
    private static final int LOWER = 5;
    private static final int MARK = 3;
    private static final int NOCASE = 6;
    public static final char PERIOD = '.';
    private static final int PUNCT = 1;
    private static final int START = 0;
    public static final char TELEIA = 903;
    private static final int UPPER = 4;
    public static final char USCORE = '_';
    private static final Set<String> javaWords = new HashSet(Arrays.asList("assert", "abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", XmlErrorCodes.DOUBLE, "else", "enum", "extends", "false", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", XmlErrorCodes.INT, "interface", XmlErrorCodes.LONG, "native", "new", "null", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "threadsafe", "throw", "throws", "transient", "true", "try", "void", "volatile", "while"));
    private static final Set<String> extraWords = new HashSet(Arrays.asList(Complex.DEFAULT_SUFFIX, TypedValues.AttributesType.S_TARGET, "org", "com"));
    private static final Set<String> javaNames = new HashSet(Arrays.asList("CharSequence", "Cloneable", "Comparable", "Runnable", "Boolean", "Byte", "Character", "Class", "ClassLoader", "Compiler", "Double", "Float", "InheritableThreadLocal", "Integer", "Long", "Math", "Number", "Object", ExtractorFactory.OOXML_PACKAGE, "Process", "Runtime", "RuntimePermission", "SecurityManager", "Short", "StackTraceElement", "StrictMath", "String", "StringBuffer", "System", "Thread", "ThreadGroup", "ThreadLocal", "Throwable", "Void", "ArithmeticException", "ArrayIndexOutOfBoundsException", "ArrayStoreException", "ClassCastException", "ClassNotFoundException", "CloneNotSupportedException", "Exception", "IllegalAccessException", "IllegalArgumentException", "IllegalMonitorStateException", "IllegalStateException", "IllegalThreadStateException", "IndexOutOfBoundsException", "InstantiationException", "InterruptedException", "NegativeArraySizeException", "NoSuchFieldException", "NoSuchMethodException", "NullPointerException", "NumberFormatException", "RuntimeException", "SecurityException", "StringIndexOutOfBoundsException", "UnsupportedOperationException", "AbstractMethodError", "AssertionError", "ClassCircularityError", "ClassFormatError", "Error", "ExceptionInInitializerError", "IllegalAccessError", "IncompatibleClassChangeError", "InstantiationError", "InternalError", "LinkageError", "NoClassDefFoundError", "NoSuchFieldError", "NoSuchMethodError", "OutOfMemoryError", "StackOverflowError", "ThreadDeath", "UnknownError", "UnsatisfiedLinkError", "UnsupportedClassVersionError", "VerifyError", "VirtualMachineError", "BigInteger", "BigDecimal", "Enum", "Date", "GDate", "GDuration", XmlErrorCodes.QNAME, "List", "XmlObject", "XmlCursor", "XmlBeans", "SchemaType"));

    public static boolean isValidJavaIdentifier(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
        int len = id.length();
        if (len == 0 || javaWords.contains(id) || !Character.isJavaIdentifierStart(id.charAt(0))) {
            return false;
        }
        for (int i = 1; i < len; i++) {
            if (!Character.isJavaIdentifierPart(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getClassNameFromQName(QName qname) {
        return getClassNameFromQName(qname, false);
    }

    public static String getClassNameFromQName(QName qname, boolean useJaxRpcRules) {
        String java_type = upperCamelCase(qname.getLocalPart(), useJaxRpcRules);
        String uri = qname.getNamespaceURI();
        String java_pkg = getPackageFromNamespace(uri, useJaxRpcRules);
        if (java_pkg != null) {
            return java_pkg + "." + java_type;
        }
        return java_type;
    }

    public static String getNamespaceFromPackage(Class<?> clazz) {
        for (Class<?> curr_clazz = clazz; curr_clazz.isArray(); curr_clazz = curr_clazz.getComponentType()) {
        }
        String fullname = clazz.getName();
        int lastdot = fullname.lastIndexOf(46);
        String pkg_name = lastdot < 0 ? "" : fullname.substring(0, lastdot);
        return JAVA_NS_PREFIX + pkg_name;
    }

    private static boolean isUriSchemeChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z') || ((ch >= '0' && ch <= '9') || ch == '-' || ch == '.' || ch == '+');
    }

    private static boolean isUriAlphaChar(char ch) {
        return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    private static int findSchemeColon(String uri) {
        int len = uri.length();
        if (len == 0 || !isUriAlphaChar(uri.charAt(0))) {
            return -1;
        }
        int i = 1;
        while (i < len && isUriSchemeChar(uri.charAt(i))) {
            i++;
        }
        if (i == len || uri.charAt(i) != ':') {
            return -1;
        }
        while (i < len && uri.charAt(i) == ':') {
            i++;
        }
        return i - 1;
    }

    private static String jls77String(String name) {
        StringBuilder buf = new StringBuilder(name);
        for (int i = 0; i < name.length(); i++) {
            if (!Character.isJavaIdentifierPart(buf.charAt(i)) || '$' == buf.charAt(i)) {
                buf.setCharAt(i, USCORE);
            }
        }
        int i2 = buf.length();
        if (i2 == 0 || !Character.isJavaIdentifierStart(buf.charAt(0))) {
            buf.insert(0, USCORE);
        }
        if (isJavaReservedWord(name)) {
            buf.append(USCORE);
        }
        return buf.toString();
    }

    private static List<String> splitDNS(String dns) {
        List<String> result = new ArrayList<>();
        int end = dns.length();
        for (int begin = dns.lastIndexOf(46); begin != -1; begin--) {
            if (dns.charAt(begin) == '.') {
                result.add(jls77String(dns.substring(begin + 1, end)));
                end = begin;
            }
        }
        result.add(jls77String(dns.substring(0, end)));
        if (result.size() >= 3 && result.get(result.size() - 1).toLowerCase(Locale.ROOT).equals("www")) {
            result.remove(result.size() - 1);
        }
        return result;
    }

    private static String processFilename(String filename) {
        int i = filename.lastIndexOf(46);
        if (i > 0 && (i + 1 + 2 == filename.length() || i + 1 + 3 == filename.length() || "html".equals(filename.substring(i + 1).toLowerCase(Locale.ROOT)))) {
            return filename.substring(0, i);
        }
        return filename;
    }

    public static String getPackageFromNamespace(String uri) {
        return getPackageFromNamespace(uri, false);
    }

    public static String getPackageFromNamespace(String uri, boolean useJaxRpcRules) {
        List<String> result;
        if (uri == null || uri.isEmpty()) {
            return "noNamespace";
        }
        int len = uri.length();
        int i = findSchemeColon(uri);
        if (i == len - 1) {
            result = new ArrayList<>();
            result.add(uri.substring(0, i));
        } else if (i >= 0 && uri.substring(0, i).equals("java")) {
            result = Arrays.asList(uri.substring(i + 1).split("\\."));
        } else {
            result = new ArrayList<>();
            int i2 = i + 1;
            loop1: while (i2 < len) {
                while (uri.charAt(i2) == '/') {
                    i2++;
                    if (i2 >= len) {
                        break loop1;
                    }
                }
                int start = i2;
                while (uri.charAt(i2) != '/' && (i2 = i2 + 1) < len) {
                }
                int end = i2;
                result.add(uri.substring(start, end));
            }
            if (result.size() > 1) {
                result.set(result.size() - 1, processFilename(result.get(result.size() - 1)));
            }
            if (!result.isEmpty()) {
                List<String> splitdns = splitDNS(result.get(0));
                result.remove(0);
                result.addAll(0, splitdns);
            }
        }
        StringBuilder buf = new StringBuilder();
        for (String s : result) {
            String part = nonJavaKeyword(lowerCamelCase(s, useJaxRpcRules, true));
            if (!part.isEmpty()) {
                buf.append(part);
                buf.append('.');
            }
        }
        if (buf.length() == 0) {
            return "noNamespace";
        }
        if (useJaxRpcRules) {
            return buf.substring(0, buf.length() - 1).toLowerCase(Locale.ROOT);
        }
        return buf.substring(0, buf.length() - 1);
    }

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println(upperCaseUnderbar(arg));
        }
    }

    public static String upperCaseUnderbar(String xml_name) {
        StringBuilder buf = new StringBuilder();
        List<String> words = splitWords(xml_name, false);
        int sz = words.size() - 1;
        if (sz >= 0 && !Character.isJavaIdentifierStart(words.get(0).charAt(0))) {
            buf.append("X_");
        }
        for (int i = 0; i < sz; i++) {
            buf.append(words.get(i));
            buf.append(USCORE);
        }
        if (sz >= 0) {
            buf.append(words.get(sz));
        }
        return buf.toString().toUpperCase(Locale.ROOT);
    }

    public static String upperCamelCase(String xml_name) {
        return upperCamelCase(xml_name, false);
    }

    public static String upperCamelCase(String xml_name, boolean useJaxRpcRules) {
        StringBuilder buf = new StringBuilder();
        List<String> words = splitWords(xml_name, useJaxRpcRules);
        if (!words.isEmpty()) {
            if (!Character.isJavaIdentifierStart(words.get(0).charAt(0))) {
                buf.append("X");
            }
            for (String word : words) {
                buf.append(word);
            }
        }
        return buf.toString();
    }

    public static String lowerCamelCase(String xml_name) {
        return lowerCamelCase(xml_name, false, true);
    }

    public static String lowerCamelCase(String xml_name, boolean useJaxRpcRules, boolean fixGeneratedName) {
        StringBuilder buf = new StringBuilder();
        List<String> words = splitWords(xml_name, useJaxRpcRules);
        if (!words.isEmpty()) {
            String first = words.get(0).toLowerCase(Locale.ROOT);
            char f = first.charAt(0);
            if (!Character.isJavaIdentifierStart(f) && fixGeneratedName) {
                buf.append("x");
            }
            buf.append(first);
            Iterator<String> itr = words.iterator();
            itr.next();
            while (itr.hasNext()) {
                buf.append(itr.next());
            }
        }
        return buf.toString();
    }

    public static String upperCaseFirstLetter(String s) {
        if (s.isEmpty() || Character.isUpperCase(s.charAt(0))) {
            return s;
        }
        StringBuilder buf = new StringBuilder(s);
        buf.setCharAt(0, Character.toUpperCase(buf.charAt(0)));
        return buf.toString();
    }

    private static void addCapped(List<String> list, String str) {
        if (!str.isEmpty()) {
            list.add(upperCaseFirstLetter(str));
        }
    }

    public static List<String> splitWords(String name, boolean useJaxRpcRules) {
        boolean z;
        List<String> list = new ArrayList<>();
        int len = name.length();
        int start = 0;
        int prefix = 0;
        int i = 0;
        while (i < len) {
            int current = getCharClass(name.charAt(i), useJaxRpcRules);
            if (prefix != 1 && current == 1) {
                addCapped(list, name.substring(start, i));
                do {
                    int charClass = getCharClass(name.charAt(i), useJaxRpcRules);
                    current = charClass;
                    if (charClass == 1) {
                        i++;
                    } else {
                        start = i;
                    }
                } while (i < len);
                return list;
            }
            boolean z2 = false;
            if (prefix == 2) {
                z = true;
            } else {
                z = false;
            }
            if (current == 2) {
                z2 = true;
            }
            if (z != z2 || ((prefix == 5 && current != 5) || isLetter(prefix) != isLetter(current))) {
                addCapped(list, name.substring(start, i));
                start = i;
            } else if (prefix == 4 && current == 5 && i > start + 1) {
                addCapped(list, name.substring(start, i - 1));
                start = i - 1;
            }
            prefix = current;
            i++;
        }
        addCapped(list, name.substring(start));
        return list;
    }

    public static int getCharClass(char c, boolean useJaxRpcRules) {
        if (isPunctuation(c, useJaxRpcRules)) {
            return 1;
        }
        if (Character.isDigit(c)) {
            return 2;
        }
        if (Character.isUpperCase(c)) {
            return 4;
        }
        if (Character.isLowerCase(c)) {
            return 5;
        }
        if (Character.isLetter(c)) {
            return 6;
        }
        return Character.isJavaIdentifierPart(c) ? 3 : 1;
    }

    private static boolean isLetter(int state) {
        return state == 4 || state == 5 || state == 6;
    }

    public static boolean isPunctuation(char c, boolean useJaxRpcRules) {
        return c == '-' || c == '.' || c == ':' || c == 183 || (c == '_' && !useJaxRpcRules) || c == 903 || c == 1757 || c == 1758;
    }

    public static String nonJavaKeyword(String word) {
        if (isJavaReservedWord(word)) {
            return 'x' + word;
        }
        return word;
    }

    public static String nonExtraKeyword(String word) {
        return isExtraReservedWord(word) ? word + "Value" : word;
    }

    public static String nonJavaCommonClassName(String name) {
        if (isJavaCommonClassName(name)) {
            return "X" + name;
        }
        return name;
    }

    private static boolean isJavaReservedWord(String word) {
        return javaWords.contains(word.toLowerCase(Locale.ROOT));
    }

    private static boolean isExtraReservedWord(String word) {
        return extraWords.contains(word.toLowerCase(Locale.ROOT));
    }

    public static boolean isJavaCommonClassName(String word) {
        return javaNames.contains(word);
    }
}
