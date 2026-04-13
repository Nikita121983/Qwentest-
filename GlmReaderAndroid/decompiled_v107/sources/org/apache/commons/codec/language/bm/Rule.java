package org.apache.commons.codec.language.bm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;

/* loaded from: classes9.dex */
public class Rule {

    @Deprecated
    public static final String ALL = "ALL";
    private static final String DOUBLE_QUOTE = "\"";
    private static final String PIPE = "|";
    private final RPattern lContext;
    private final String pattern;
    private final PhonemeExpr phoneme;
    private final RPattern rContext;
    public static final RPattern ALL_STRINGS_RMATCHER = new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda11
        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
        public final boolean isMatch(CharSequence charSequence) {
            return Rule.lambda$static$0(charSequence);
        }
    };
    private static final String HASH_INCLUDE = "#include";
    private static final int HASH_INCLUDE_LENGTH = HASH_INCLUDE.length();
    private static final Pattern AROUND_PLUS = Pattern.compile("[+]");
    private static final Pattern AROUND_PIPE = Pattern.compile("[|]");
    private static final Map<NameType, Map<RuleType, Map<String, Map<String, List<Rule>>>>> RULES = new EnumMap(NameType.class);

    /* loaded from: classes9.dex */
    public interface RPattern {
        boolean isMatch(CharSequence charSequence);
    }

    /* loaded from: classes9.dex */
    public static final class Phoneme implements PhonemeExpr {
        public static final Comparator<Phoneme> COMPARATOR = new Comparator() { // from class: org.apache.commons.codec.language.bm.Rule$Phoneme$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return Rule.Phoneme.lambda$static$0((Rule.Phoneme) obj, (Rule.Phoneme) obj2);
            }
        };
        private final Languages.LanguageSet languages;
        private final StringBuilder phonemeText;

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ int lambda$static$0(Phoneme o1, Phoneme o2) {
            int o1Length = o1.phonemeText.length();
            int o2Length = o2.phonemeText.length();
            for (int i = 0; i < o1Length; i++) {
                if (i >= o2Length) {
                    return 1;
                }
                int c = o1.phonemeText.charAt(i) - o2.phonemeText.charAt(i);
                if (c != 0) {
                    return c;
                }
            }
            if (o1Length < o2Length) {
                return -1;
            }
            return 0;
        }

        public Phoneme(CharSequence phonemeText, Languages.LanguageSet languages) {
            this.phonemeText = new StringBuilder(phonemeText);
            this.languages = languages;
        }

        public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight) {
            this(phonemeLeft.phonemeText, phonemeLeft.languages);
            this.phonemeText.append((CharSequence) phonemeRight.phonemeText);
        }

        public Phoneme(Phoneme phonemeLeft, Phoneme phonemeRight, Languages.LanguageSet languages) {
            this(phonemeLeft.phonemeText, languages);
            this.phonemeText.append((CharSequence) phonemeRight.phonemeText);
        }

        public Phoneme append(CharSequence sequence) {
            this.phonemeText.append(sequence);
            return this;
        }

        public Languages.LanguageSet getLanguages() {
            return this.languages;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public Iterable<Phoneme> getPhonemes() {
            return Collections.singleton(this);
        }

        public CharSequence getPhonemeText() {
            return this.phonemeText;
        }

        @Deprecated
        public Phoneme join(Phoneme right) {
            return new Phoneme(this.phonemeText.toString() + right.phonemeText.toString(), this.languages.restrictTo(right.languages));
        }

        public Phoneme mergeWithLanguage(Languages.LanguageSet lang) {
            return new Phoneme(this.phonemeText.toString(), this.languages.merge(lang));
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public int size() {
            return 1;
        }

        public String toString() {
            return this.phonemeText.toString() + CollectionUtils.DEFAULT_TOSTRING_PREFIX + this.languages + CollectionUtils.DEFAULT_TOSTRING_SUFFIX;
        }
    }

    /* loaded from: classes9.dex */
    public interface PhonemeExpr {
        Iterable<Phoneme> getPhonemes();

        default int size() {
            return (int) Math.min(getPhonemes().spliterator().getExactSizeIfKnown(), 2147483647L);
        }
    }

    /* loaded from: classes9.dex */
    public static final class PhonemeList implements PhonemeExpr {
        private final List<Phoneme> phonemeList;

        public PhonemeList(List<Phoneme> phonemes) {
            this.phonemeList = phonemes;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public List<Phoneme> getPhonemes() {
            return this.phonemeList;
        }

        @Override // org.apache.commons.codec.language.bm.Rule.PhonemeExpr
        public int size() {
            return this.phonemeList.size();
        }
    }

    static {
        for (final NameType nameType : NameType.values()) {
            Map<RuleType, Map<String, Map<String, List<Rule>>>> rtsMap = new EnumMap<>(RuleType.class);
            for (final RuleType ruleType : RuleType.values()) {
                final Map<String, Map<String, List<Rule>>> rsMap = new HashMap<>();
                Languages languages = Languages.getInstance(nameType);
                languages.getLanguages().forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda1
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        Rule.lambda$static$1(NameType.this, ruleType, rsMap, (String) obj);
                    }
                });
                if (!ruleType.equals(RuleType.RULES)) {
                    Scanner scanner = createScanner(nameType, ruleType, "common");
                    try {
                        rsMap.put("common", parseRules(scanner, createResourceName(nameType, ruleType, "common")));
                        if (scanner != null) {
                            scanner.close();
                        }
                    } catch (Throwable th) {
                        if (scanner != null) {
                            try {
                                scanner.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                        }
                        throw th;
                    }
                }
                rtsMap.put(ruleType, Collections.unmodifiableMap(rsMap));
            }
            RULES.put(nameType, Collections.unmodifiableMap(rtsMap));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$static$0(CharSequence input) {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$static$1(NameType nameType, RuleType ruleType, Map rsMap, String l) {
        try {
            Scanner scanner = createScanner(nameType, ruleType, l);
            try {
                rsMap.put(l, parseRules(scanner, createResourceName(nameType, ruleType, l)));
                if (scanner != null) {
                    scanner.close();
                }
            } finally {
            }
        } catch (IllegalStateException e) {
            throw new IllegalStateException("Problem processing " + createResourceName(nameType, ruleType, l), e);
        }
    }

    private static boolean contains(CharSequence chars, final char input) {
        return chars.chars().anyMatch(new IntPredicate() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda10
            @Override // java.util.function.IntPredicate
            public final boolean test(int i) {
                return Rule.lambda$contains$0(input, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$contains$0(char input, int c) {
        return c == input;
    }

    private static String createResourceName(NameType nameType, RuleType rt, String lang) {
        return String.format("/org/apache/commons/codec/language/bm/%s_%s_%s.txt", nameType.getName(), rt.getName(), lang);
    }

    private static Scanner createScanner(NameType nameType, RuleType rt, String lang) {
        String resName = createResourceName(nameType, rt, lang);
        return new Scanner(Resources.getInputStream(resName), ResourceConstants.ENCODING);
    }

    private static Scanner createScanner(String lang) {
        String resName = String.format("/org/apache/commons/codec/language/bm/%s.txt", lang);
        return new Scanner(Resources.getInputStream(resName), ResourceConstants.ENCODING);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean endsWith(CharSequence input, CharSequence suffix) {
        int suffixLength = suffix.length();
        int inputLength = input.length();
        if (suffixLength > inputLength) {
            return false;
        }
        int i = inputLength - 1;
        for (int j = suffixLength - 1; j >= 0; j--) {
            if (input.charAt(i) != suffix.charAt(j)) {
                return false;
            }
            i--;
        }
        return true;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
        Map<String, List<Rule>> ruleMap = getInstanceMap(nameType, rt, langs);
        final List<Rule> allRules = new ArrayList<>();
        ruleMap.values().forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                allRules.addAll((List) obj);
            }
        });
        return allRules;
    }

    public static List<Rule> getInstance(NameType nameType, RuleType rt, String lang) {
        return getInstance(nameType, rt, Languages.LanguageSet.from(new HashSet(Arrays.asList(lang))));
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, Languages.LanguageSet langs) {
        return getInstanceMap(nameType, rt, langs.isSingleton() ? langs.getAny() : Languages.ANY);
    }

    public static Map<String, List<Rule>> getInstanceMap(NameType nameType, RuleType rt, String lang) {
        Map<String, List<Rule>> rules = RULES.get(nameType).get(rt).get(lang);
        if (rules == null) {
            throw new IllegalArgumentException(String.format("No rules found for %s, %s, '%s'.", nameType.getName(), rt.getName(), lang));
        }
        return rules;
    }

    private static Phoneme parsePhoneme(String ph) {
        int open = ph.indexOf(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
        if (open >= 0) {
            if (!ph.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX)) {
                throw new IllegalArgumentException("Phoneme expression contains a '[' but does not end in ']'");
            }
            String before = ph.substring(0, open);
            String in = ph.substring(open + 1, ph.length() - 1);
            Set<String> langs = new HashSet<>(Arrays.asList(AROUND_PLUS.split(in)));
            return new Phoneme(before, Languages.LanguageSet.from(langs));
        }
        return new Phoneme(ph, Languages.ANY_LANGUAGE);
    }

    static PhonemeExpr parsePhonemeExpr(String ph) {
        if (ph.startsWith("(")) {
            if (!ph.endsWith(")")) {
                throw new IllegalArgumentException("Phoneme starting with '(' must end with ')'");
            }
            List<Phoneme> phs = new ArrayList<>();
            String body = ph.substring(1, ph.length() - 1);
            String[] split = AROUND_PIPE.split(body);
            for (String part : split) {
                phs.add(parsePhoneme(part));
            }
            if ((split.length > 1 && split[0].length() != 0 && body.startsWith(PIPE)) || (split[split.length - 1].length() != 0 && body.endsWith(PIPE))) {
                phs.add(new Phoneme("", Languages.ANY_LANGUAGE));
            }
            return new PhonemeList(phs);
        }
        return parsePhoneme(ph);
    }

    private static Map<String, List<Rule>> parseRules(Scanner scanner, final String location) {
        boolean inMultilineComment;
        String str;
        Map<String, List<Rule>> lines = new HashMap<>();
        int currentLine = 0;
        boolean inMultilineComment2 = false;
        while (scanner.hasNextLine()) {
            final int currentLine2 = currentLine + 1;
            String rawLine = scanner.nextLine();
            String line = rawLine;
            if (inMultilineComment2) {
                if (!line.endsWith("*/")) {
                    inMultilineComment = inMultilineComment2;
                    inMultilineComment2 = inMultilineComment;
                    currentLine = currentLine2;
                } else {
                    inMultilineComment2 = false;
                    currentLine = currentLine2;
                }
            } else if (line.startsWith("/*")) {
                inMultilineComment2 = true;
                currentLine = currentLine2;
            } else {
                int cmtI = line.indexOf("//");
                if (cmtI >= 0) {
                    line = line.substring(0, cmtI);
                }
                String line2 = line.trim();
                if (line2.isEmpty()) {
                    currentLine = currentLine2;
                } else {
                    if (line2.startsWith(HASH_INCLUDE)) {
                        String incl = line2.substring(HASH_INCLUDE_LENGTH).trim();
                        if (incl.contains(StringUtils.SPACE)) {
                            throw new IllegalArgumentException("Malformed import statement '" + rawLine + "' in " + location);
                        }
                        Scanner hashIncludeScanner = createScanner(incl);
                        try {
                            lines.putAll(parseRules(hashIncludeScanner, location + "->" + incl));
                            if (hashIncludeScanner != null) {
                                hashIncludeScanner.close();
                            }
                            inMultilineComment = inMultilineComment2;
                        } finally {
                        }
                    } else {
                        String[] parts = ResourceConstants.SPACES.split(line2);
                        if (parts.length != 4) {
                            throw new IllegalArgumentException("Malformed rule statement split into " + parts.length + " parts: " + rawLine + " in " + location);
                        }
                        try {
                            final String pat = stripQuotes(parts[0]);
                            final String lCon = stripQuotes(parts[1]);
                            final String rCon = stripQuotes(parts[2]);
                            PhonemeExpr ph = parsePhonemeExpr(stripQuotes(parts[3]));
                            try {
                                str = "' in ";
                                inMultilineComment = inMultilineComment2;
                                try {
                                    Rule r = new Rule(pat, lCon, rCon, ph) { // from class: org.apache.commons.codec.language.bm.Rule.1
                                        private final String loc;
                                        private final int myLine;

                                        {
                                            this.myLine = currentLine2;
                                            this.loc = location;
                                        }

                                        public String toString() {
                                            StringBuilder sb = new StringBuilder();
                                            sb.append("Rule");
                                            sb.append("{line=").append(this.myLine);
                                            sb.append(", loc='").append(this.loc).append(Chars.QUOTE);
                                            sb.append(", pat='").append(pat).append(Chars.QUOTE);
                                            sb.append(", lcon='").append(lCon).append(Chars.QUOTE);
                                            sb.append(", rcon='").append(rCon).append(Chars.QUOTE);
                                            sb.append('}');
                                            return sb.toString();
                                        }
                                    };
                                    String patternKey = r.pattern.substring(0, 1);
                                    List<Rule> rules = lines.computeIfAbsent(patternKey, new Function() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda2
                                        @Override // java.util.function.Function
                                        public final Object apply(Object obj) {
                                            return Rule.lambda$parseRules$0((String) obj);
                                        }
                                    });
                                    rules.add(r);
                                } catch (IllegalArgumentException e) {
                                    e = e;
                                    throw new IllegalStateException("Problem parsing line '" + currentLine2 + str + location, e);
                                }
                            } catch (IllegalArgumentException e2) {
                                e = e2;
                                str = "' in ";
                            }
                        } catch (IllegalArgumentException e3) {
                            e = e3;
                            str = "' in ";
                        }
                    }
                    inMultilineComment2 = inMultilineComment;
                    currentLine = currentLine2;
                }
            }
        }
        return lines;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$parseRules$0(String k) {
        return new ArrayList();
    }

    private static RPattern pattern(final String str) {
        boolean startsWith = str.startsWith("^");
        boolean endsWith = str.endsWith("$");
        int length = str.length();
        if (endsWith) {
            length--;
        }
        final String substring = str.substring(startsWith ? 1 : 0, length);
        if (!substring.contains(CollectionUtils.DEFAULT_TOSTRING_PREFIX)) {
            if (startsWith && endsWith) {
                if (substring.isEmpty()) {
                    return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda3
                        @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                        public final boolean isMatch(CharSequence charSequence) {
                            return Rule.lambda$pattern$0(charSequence);
                        }
                    };
                }
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda4
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public final boolean isMatch(CharSequence charSequence) {
                        boolean equals;
                        equals = charSequence.equals(substring);
                        return equals;
                    }
                };
            }
            if ((startsWith || endsWith) && substring.isEmpty()) {
                return ALL_STRINGS_RMATCHER;
            }
            if (startsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda5
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public final boolean isMatch(CharSequence charSequence) {
                        boolean startsWith2;
                        startsWith2 = Rule.startsWith(charSequence, substring);
                        return startsWith2;
                    }
                };
            }
            if (endsWith) {
                return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda6
                    @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                    public final boolean isMatch(CharSequence charSequence) {
                        boolean endsWith2;
                        endsWith2 = Rule.endsWith(charSequence, substring);
                        return endsWith2;
                    }
                };
            }
        } else {
            boolean startsWith2 = substring.startsWith(CollectionUtils.DEFAULT_TOSTRING_PREFIX);
            boolean endsWith2 = substring.endsWith(CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
            if (startsWith2 && endsWith2) {
                String substring2 = substring.substring(1, substring.length() - 1);
                if (!substring2.contains(CollectionUtils.DEFAULT_TOSTRING_PREFIX)) {
                    boolean startsWith3 = substring2.startsWith("^");
                    if (startsWith3) {
                        substring2 = substring2.substring(1);
                    }
                    final String str2 = substring2;
                    final boolean z = !startsWith3;
                    if (startsWith && endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda7
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public final boolean isMatch(CharSequence charSequence) {
                                return Rule.lambda$pattern$4(str2, z, charSequence);
                            }
                        };
                    }
                    if (startsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda8
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public final boolean isMatch(CharSequence charSequence) {
                                return Rule.lambda$pattern$5(str2, z, charSequence);
                            }
                        };
                    }
                    if (endsWith) {
                        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule$$ExternalSyntheticLambda9
                            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
                            public final boolean isMatch(CharSequence charSequence) {
                                return Rule.lambda$pattern$6(str2, z, charSequence);
                            }
                        };
                    }
                }
            }
        }
        return new RPattern() { // from class: org.apache.commons.codec.language.bm.Rule.2
            final Pattern pattern;

            {
                this.pattern = Pattern.compile(str);
            }

            @Override // org.apache.commons.codec.language.bm.Rule.RPattern
            public boolean isMatch(CharSequence input) {
                Matcher matcher = this.pattern.matcher(input);
                return matcher.find();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$pattern$0(CharSequence input) {
        return input.length() == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$pattern$4(String bContent, boolean shouldMatch, CharSequence input) {
        return input.length() == 1 && contains(bContent, input.charAt(0)) == shouldMatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$pattern$5(String bContent, boolean shouldMatch, CharSequence input) {
        return input.length() > 0 && contains(bContent, input.charAt(0)) == shouldMatch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$pattern$6(String bContent, boolean shouldMatch, CharSequence input) {
        return input.length() > 0 && contains(bContent, input.charAt(input.length() - 1)) == shouldMatch;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean startsWith(CharSequence input, CharSequence prefix) {
        if (prefix.length() > input.length()) {
            return false;
        }
        for (int i = 0; i < prefix.length(); i++) {
            if (input.charAt(i) != prefix.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    private static String stripQuotes(String str) {
        if (str.startsWith(DOUBLE_QUOTE)) {
            str = str.substring(1);
        }
        if (str.endsWith(DOUBLE_QUOTE)) {
            return str.substring(0, str.length() - 1);
        }
        return str;
    }

    public Rule(String pattern, String lContext, String rContext, PhonemeExpr phoneme) {
        this.pattern = pattern;
        this.lContext = pattern(lContext + "$");
        this.rContext = pattern("^" + rContext);
        this.phoneme = phoneme;
    }

    public RPattern getLContext() {
        return this.lContext;
    }

    public String getPattern() {
        return this.pattern;
    }

    public PhonemeExpr getPhoneme() {
        return this.phoneme;
    }

    public RPattern getRContext() {
        return this.rContext;
    }

    public boolean patternAndContextMatches(CharSequence input, int i) {
        if (i < 0) {
            throw new IndexOutOfBoundsException("Can not match pattern at negative indexes");
        }
        int patternLength = this.pattern.length();
        int ipl = i + patternLength;
        if (ipl <= input.length() && input.subSequence(i, ipl).equals(this.pattern) && this.rContext.isMatch(input.subSequence(ipl, input.length()))) {
            return this.lContext.isMatch(input.subSequence(0, i));
        }
        return false;
    }
}
