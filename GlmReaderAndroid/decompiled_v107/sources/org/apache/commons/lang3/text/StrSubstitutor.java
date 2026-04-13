package org.apache.commons.lang3.text;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.geometry.VectorFormat;

@Deprecated
/* loaded from: classes9.dex */
public class StrSubstitutor {
    public static final char DEFAULT_ESCAPE = '$';
    public static final StrMatcher DEFAULT_PREFIX = StrMatcher.stringMatcher("${");
    public static final StrMatcher DEFAULT_SUFFIX = StrMatcher.stringMatcher(VectorFormat.DEFAULT_SUFFIX);
    public static final StrMatcher DEFAULT_VALUE_DELIMITER = StrMatcher.stringMatcher(":-");
    private boolean enableSubstitutionInVariables;
    private char escapeChar;
    private StrMatcher prefixMatcher;
    private boolean preserveEscapes;
    private StrMatcher suffixMatcher;
    private StrMatcher valueDelimiterMatcher;
    private StrLookup<?> variableResolver;

    public static <V> String replace(Object source, Map<String, V> valueMap) {
        return new StrSubstitutor(valueMap).replace(source);
    }

    public static <V> String replace(Object source, Map<String, V> valueMap, String prefix, String suffix) {
        return new StrSubstitutor(valueMap, prefix, suffix).replace(source);
    }

    public static String replace(Object source, Properties valueProperties) {
        if (valueProperties == null) {
            return source.toString();
        }
        Map<String, String> valueMap = new HashMap<>();
        Enumeration<?> propNames = valueProperties.propertyNames();
        while (propNames.hasMoreElements()) {
            String propName = String.valueOf(propNames.nextElement());
            String propValue = valueProperties.getProperty(propName);
            valueMap.put(propName, propValue);
        }
        return replace(source, valueMap);
    }

    public static String replaceSystemProperties(Object source) {
        return new StrSubstitutor(StrLookup.systemPropertiesLookup()).replace(source);
    }

    public StrSubstitutor() {
        this((StrLookup<?>) null, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, '$');
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, escape);
    }

    public <V> StrSubstitutor(Map<String, V> valueMap, String prefix, String suffix, char escape, String valueDelimiter) {
        this((StrLookup<?>) StrLookup.mapLookup(valueMap), prefix, suffix, escape, valueDelimiter);
    }

    public StrSubstitutor(StrLookup<?> variableResolver) {
        this(variableResolver, DEFAULT_PREFIX, DEFAULT_SUFFIX, '$');
    }

    public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape) {
        setVariableResolver(variableResolver);
        setVariablePrefix(prefix);
        setVariableSuffix(suffix);
        setEscapeChar(escape);
        setValueDelimiterMatcher(DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, String prefix, String suffix, char escape, String valueDelimiter) {
        setVariableResolver(variableResolver);
        setVariablePrefix(prefix);
        setVariableSuffix(suffix);
        setEscapeChar(escape);
        setValueDelimiter(valueDelimiter);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape) {
        this(variableResolver, prefixMatcher, suffixMatcher, escape, DEFAULT_VALUE_DELIMITER);
    }

    public StrSubstitutor(StrLookup<?> variableResolver, StrMatcher prefixMatcher, StrMatcher suffixMatcher, char escape, StrMatcher valueDelimiterMatcher) {
        setVariableResolver(variableResolver);
        setVariablePrefixMatcher(prefixMatcher);
        setVariableSuffixMatcher(suffixMatcher);
        setEscapeChar(escape);
        setValueDelimiterMatcher(valueDelimiterMatcher);
    }

    private void checkCyclicSubstitution(String varName, List<String> priorVariables) {
        if (!priorVariables.contains(varName)) {
            return;
        }
        StrBuilder buf = new StrBuilder(256);
        buf.append("Infinite loop in property interpolation of ");
        buf.append(priorVariables.remove(0));
        buf.append(": ");
        buf.appendWithSeparators(priorVariables, "->");
        throw new IllegalStateException(buf.toString());
    }

    public char getEscapeChar() {
        return this.escapeChar;
    }

    public StrMatcher getValueDelimiterMatcher() {
        return this.valueDelimiterMatcher;
    }

    public StrMatcher getVariablePrefixMatcher() {
        return this.prefixMatcher;
    }

    public StrLookup<?> getVariableResolver() {
        return this.variableResolver;
    }

    public StrMatcher getVariableSuffixMatcher() {
        return this.suffixMatcher;
    }

    public boolean isEnableSubstitutionInVariables() {
        return this.enableSubstitutionInVariables;
    }

    public boolean isPreserveEscapes() {
        return this.preserveEscapes;
    }

    public String replace(char[] source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length).append(source);
        substitute(buf, 0, source.length);
        return buf.toString();
    }

    public String replace(char[] source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(CharSequence source) {
        if (source == null) {
            return null;
        }
        return replace(source, 0, source.length());
    }

    public String replace(CharSequence source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(Object source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder().append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public String replace(StrBuilder source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length()).append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public String replace(StrBuilder source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public String replace(String source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source);
        if (!substitute(buf, 0, source.length())) {
            return source;
        }
        return buf.toString();
    }

    public String replace(String source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return source.substring(offset, offset + length);
        }
        return buf.toString();
    }

    public String replace(StringBuffer source) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(source.length()).append(source);
        substitute(buf, 0, buf.length());
        return buf.toString();
    }

    public String replace(StringBuffer source, int offset, int length) {
        if (source == null) {
            return null;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        substitute(buf, 0, length);
        return buf.toString();
    }

    public boolean replaceIn(StrBuilder source) {
        if (source == null) {
            return false;
        }
        return substitute(source, 0, source.length());
    }

    public boolean replaceIn(StrBuilder source, int offset, int length) {
        if (source == null) {
            return false;
        }
        return substitute(source, offset, length);
    }

    public boolean replaceIn(StringBuffer source) {
        if (source == null) {
            return false;
        }
        return replaceIn(source, 0, source.length());
    }

    public boolean replaceIn(StringBuffer source, int offset, int length) {
        if (source == null) {
            return false;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return false;
        }
        source.replace(offset, offset + length, buf.toString());
        return true;
    }

    public boolean replaceIn(StringBuilder source) {
        if (source == null) {
            return false;
        }
        return replaceIn(source, 0, source.length());
    }

    public boolean replaceIn(StringBuilder source, int offset, int length) {
        if (source == null) {
            return false;
        }
        StrBuilder buf = new StrBuilder(length).append(source, offset, length);
        if (!substitute(buf, 0, length)) {
            return false;
        }
        source.replace(offset, offset + length, buf.toString());
        return true;
    }

    protected String resolveVariable(String variableName, StrBuilder buf, int startPos, int endPos) {
        StrLookup<?> resolver = getVariableResolver();
        if (resolver == null) {
            return null;
        }
        return resolver.lookup(variableName);
    }

    public void setEnableSubstitutionInVariables(boolean enableSubstitutionInVariables) {
        this.enableSubstitutionInVariables = enableSubstitutionInVariables;
    }

    public void setEscapeChar(char escapeCharacter) {
        this.escapeChar = escapeCharacter;
    }

    public void setPreserveEscapes(boolean preserveEscapes) {
        this.preserveEscapes = preserveEscapes;
    }

    public StrSubstitutor setValueDelimiter(char valueDelimiter) {
        return setValueDelimiterMatcher(StrMatcher.charMatcher(valueDelimiter));
    }

    public StrSubstitutor setValueDelimiter(String valueDelimiter) {
        if (StringUtils.isEmpty(valueDelimiter)) {
            setValueDelimiterMatcher(null);
            return this;
        }
        return setValueDelimiterMatcher(StrMatcher.stringMatcher(valueDelimiter));
    }

    public StrSubstitutor setValueDelimiterMatcher(StrMatcher valueDelimiterMatcher) {
        this.valueDelimiterMatcher = valueDelimiterMatcher;
        return this;
    }

    public StrSubstitutor setVariablePrefix(char prefix) {
        return setVariablePrefixMatcher(StrMatcher.charMatcher(prefix));
    }

    public StrSubstitutor setVariablePrefix(String prefix) {
        return setVariablePrefixMatcher(StrMatcher.stringMatcher((String) Objects.requireNonNull(prefix)));
    }

    public StrSubstitutor setVariablePrefixMatcher(StrMatcher prefixMatcher) {
        this.prefixMatcher = (StrMatcher) Objects.requireNonNull(prefixMatcher, "prefixMatcher");
        return this;
    }

    public void setVariableResolver(StrLookup<?> variableResolver) {
        this.variableResolver = variableResolver;
    }

    public StrSubstitutor setVariableSuffix(char suffix) {
        return setVariableSuffixMatcher(StrMatcher.charMatcher(suffix));
    }

    public StrSubstitutor setVariableSuffix(String suffix) {
        return setVariableSuffixMatcher(StrMatcher.stringMatcher((String) Objects.requireNonNull(suffix)));
    }

    public StrSubstitutor setVariableSuffixMatcher(StrMatcher suffixMatcher) {
        this.suffixMatcher = (StrMatcher) Objects.requireNonNull(suffixMatcher);
        return this;
    }

    protected boolean substitute(StrBuilder buf, int offset, int length) {
        return substitute(buf, offset, length, null) > 0;
    }

    private int substitute(StrBuilder buf, int offset, int length, List<String> priorVariables) {
        StrMatcher suffMatcher;
        char escape;
        boolean substitutionInVariablesEnabled;
        boolean top;
        int pos;
        int bufEnd;
        String varName;
        int change;
        int endMatchLen;
        StrMatcher pfxMatcher = getVariablePrefixMatcher();
        StrMatcher suffMatcher2 = getVariableSuffixMatcher();
        char escape2 = getEscapeChar();
        StrMatcher valueDelimMatcher = getValueDelimiterMatcher();
        boolean substitutionInVariablesEnabled2 = isEnableSubstitutionInVariables();
        boolean top2 = priorVariables == null;
        char[] chars = buf.buffer;
        int bufEnd2 = offset + length;
        int bufEnd3 = bufEnd2;
        int pos2 = offset;
        char[] chars2 = chars;
        int lengthChange = 0;
        int lengthChange2 = 0;
        List<String> priorVariables2 = priorVariables;
        while (pos2 < bufEnd3) {
            int startMatchLen = pfxMatcher.isMatch(chars2, pos2, offset, bufEnd3);
            if (startMatchLen == 0) {
                pos2++;
                suffMatcher = suffMatcher2;
                escape = escape2;
                substitutionInVariablesEnabled = substitutionInVariablesEnabled2;
                top = top2;
            } else {
                if (pos2 > offset) {
                    substitutionInVariablesEnabled = substitutionInVariablesEnabled2;
                    if (chars2[pos2 - 1] == escape2) {
                        if (this.preserveEscapes) {
                            pos2++;
                            substitutionInVariablesEnabled2 = substitutionInVariablesEnabled;
                        } else {
                            buf.deleteCharAt(pos2 - 1);
                            char[] chars3 = buf.buffer;
                            lengthChange--;
                            lengthChange2 = 1;
                            bufEnd3--;
                            suffMatcher = suffMatcher2;
                            escape = escape2;
                            chars2 = chars3;
                            top = top2;
                        }
                    }
                } else {
                    substitutionInVariablesEnabled = substitutionInVariablesEnabled2;
                }
                int startPos = pos2;
                pos2 += startMatchLen;
                int nestedVarCount = 0;
                while (true) {
                    if (pos2 >= bufEnd3) {
                        suffMatcher = suffMatcher2;
                        escape = escape2;
                        top = top2;
                        break;
                    }
                    if (substitutionInVariablesEnabled && (endMatchLen = pfxMatcher.isMatch(chars2, pos2, offset, bufEnd3)) != 0) {
                        nestedVarCount++;
                        pos2 += endMatchLen;
                    } else {
                        int endMatchLen2 = suffMatcher2.isMatch(chars2, pos2, offset, bufEnd3);
                        if (endMatchLen2 == 0) {
                            pos2++;
                        } else if (nestedVarCount == 0) {
                            suffMatcher = suffMatcher2;
                            escape = escape2;
                            int pos3 = pos2;
                            String varNameExpr = new String(chars2, startPos + startMatchLen, (pos2 - startPos) - startMatchLen);
                            if (substitutionInVariablesEnabled) {
                                StrBuilder bufName = new StrBuilder(varNameExpr);
                                substitute(bufName, 0, bufName.length());
                                varNameExpr = bufName.toString();
                            }
                            int pos4 = pos3 + endMatchLen2;
                            String varName2 = varNameExpr;
                            String varDefaultValue = null;
                            if (valueDelimMatcher == null) {
                                pos = pos4;
                                bufEnd = bufEnd3;
                                top = top2;
                            } else {
                                pos = pos4;
                                char[] varNameExprChars = varNameExpr.toCharArray();
                                bufEnd = bufEnd3;
                                int bufEnd4 = 0;
                                while (true) {
                                    top = top2;
                                    if (bufEnd4 >= varNameExprChars.length || (!substitutionInVariablesEnabled && pfxMatcher.isMatch(varNameExprChars, bufEnd4, bufEnd4, varNameExprChars.length) != 0)) {
                                        break;
                                    }
                                    int valueDelimiterMatchLen = valueDelimMatcher.isMatch(varNameExprChars, bufEnd4);
                                    if (valueDelimiterMatchLen == 0) {
                                        bufEnd4++;
                                        top2 = top;
                                    } else {
                                        String varName3 = varNameExpr.substring(0, bufEnd4);
                                        varDefaultValue = varNameExpr.substring(bufEnd4 + valueDelimiterMatchLen);
                                        varName = varName3;
                                        break;
                                    }
                                }
                            }
                            varName = varName2;
                            if (priorVariables2 == null) {
                                priorVariables2 = new ArrayList();
                                priorVariables2.add(new String(chars2, offset, length));
                            }
                            checkCyclicSubstitution(varName, priorVariables2);
                            priorVariables2.add(varName);
                            String varValue = resolveVariable(varName, buf, startPos, pos4);
                            if (varValue == null) {
                                varValue = varDefaultValue;
                            }
                            if (varValue == null) {
                                change = pos;
                            } else {
                                int varLen = varValue.length();
                                buf.replace(startPos, pos4, varValue);
                                lengthChange2 = 1;
                                int change2 = substitute(buf, startPos, varLen, priorVariables2);
                                int change3 = (change2 + varLen) - (pos4 - startPos);
                                change = pos + change3;
                                bufEnd += change3;
                                lengthChange += change3;
                                chars2 = buf.buffer;
                            }
                            priorVariables2.remove(priorVariables2.size() - 1);
                            pos2 = change;
                            bufEnd3 = bufEnd;
                        } else {
                            nestedVarCount--;
                            pos2 += endMatchLen2;
                        }
                    }
                }
            }
            substitutionInVariablesEnabled2 = substitutionInVariablesEnabled;
            suffMatcher2 = suffMatcher;
            escape2 = escape;
            top2 = top;
        }
        if (top2) {
            return lengthChange2;
        }
        return lengthChange;
    }
}
