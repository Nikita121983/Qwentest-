package org.apache.commons.codec.language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import org.apache.commons.codec.CharEncoding;
import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.StringEncoder;
import org.apache.commons.codec.language.DaitchMokotoffSoundex;

/* loaded from: classes9.dex */
public class DaitchMokotoffSoundex implements StringEncoder {
    private static final String COMMENT = "//";
    private static final String DOUBLE_QUOTE = "\"";
    private static final int MAX_LENGTH = 6;
    private static final String MULTILINE_COMMENT_END = "*/";
    private static final String MULTILINE_COMMENT_START = "/*";
    private static final char NUL = 0;
    private static final String RESOURCE_FILE = "/org/apache/commons/codec/language/dmrules.txt";
    private final boolean folding;
    private static final Map<Character, List<Rule>> RULES = new HashMap();
    private static final Map<Character, Character> FOLDINGS = new HashMap();
    private static final Pattern EQUAL = Pattern.compile("=");
    private static final Pattern SPACES = Pattern.compile("\\s+");

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Branch {
        private final StringBuilder builder;
        private String cachedString;
        private String lastReplacement;

        private Branch() {
            this.builder = new StringBuilder();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Branch createBranch() {
            Branch branch = new Branch();
            branch.builder.append(toString());
            branch.lastReplacement = this.lastReplacement;
            return branch;
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Branch)) {
                return false;
            }
            return toString().equals(((Branch) other).toString());
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void finish() {
            while (this.builder.length() < 6) {
                this.builder.append('0');
                this.cachedString = null;
            }
        }

        public int hashCode() {
            return toString().hashCode();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processNextReplacement(String replacement, boolean forceAppend) {
            boolean append = this.lastReplacement == null || !this.lastReplacement.endsWith(replacement) || forceAppend;
            if (append && this.builder.length() < 6) {
                this.builder.append(replacement);
                if (this.builder.length() > 6) {
                    this.builder.delete(6, this.builder.length());
                }
                this.cachedString = null;
            }
            this.lastReplacement = replacement;
        }

        public String toString() {
            if (this.cachedString == null) {
                this.cachedString = this.builder.toString();
            }
            return this.cachedString;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class Rule {
        private static final Pattern PIPE = Pattern.compile("\\|");
        private final String pattern;
        private final String[] replacementAtStart;
        private final String[] replacementBeforeVowel;
        private final String[] replacementDefault;

        private Rule(String pattern, String replacementAtStart, String replacementBeforeVowel, String replacementDefault) {
            this.pattern = pattern;
            this.replacementAtStart = PIPE.split(replacementAtStart);
            this.replacementBeforeVowel = PIPE.split(replacementBeforeVowel);
            this.replacementDefault = PIPE.split(replacementDefault);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int getPatternLength() {
            return this.pattern.length();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public String[] getReplacements(String context, boolean atStart) {
            if (atStart) {
                return this.replacementAtStart;
            }
            int nextIndex = getPatternLength();
            boolean nextCharIsVowel = nextIndex < context.length() && isVowel(context.charAt(nextIndex));
            if (nextCharIsVowel) {
                return this.replacementBeforeVowel;
            }
            return this.replacementDefault;
        }

        private boolean isVowel(char ch) {
            return ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u';
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean matches(String context) {
            return context.startsWith(this.pattern);
        }

        public String toString() {
            return String.format("%s=(%s,%s,%s)", this.pattern, Arrays.asList(this.replacementAtStart), Arrays.asList(this.replacementBeforeVowel), Arrays.asList(this.replacementDefault));
        }
    }

    static {
        Scanner scanner = new Scanner(Resources.getInputStream(RESOURCE_FILE), CharEncoding.UTF_8);
        try {
            parseRules(scanner, RESOURCE_FILE, RULES, FOLDINGS);
            scanner.close();
            RULES.forEach(new BiConsumer() { // from class: org.apache.commons.codec.language.DaitchMokotoffSoundex$$ExternalSyntheticLambda2
                @Override // java.util.function.BiConsumer
                public final void accept(Object obj, Object obj2) {
                    ((List) obj2).sort(new Comparator() { // from class: org.apache.commons.codec.language.DaitchMokotoffSoundex$$ExternalSyntheticLambda1
                        @Override // java.util.Comparator
                        public final int compare(Object obj3, Object obj4) {
                            return DaitchMokotoffSoundex.lambda$static$1((DaitchMokotoffSoundex.Rule) obj3, (DaitchMokotoffSoundex.Rule) obj4);
                        }
                    });
                }
            });
        } catch (Throwable th) {
            try {
                scanner.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ int lambda$static$1(Rule rule1, Rule rule2) {
        return rule2.getPatternLength() - rule1.getPatternLength();
    }

    private static void parseRules(Scanner scanner, String location, Map<Character, List<Rule>> ruleMapping, Map<Character, Character> asciiFoldings) {
        int currentLine = 0;
        boolean inMultilineComment = false;
        while (scanner.hasNextLine()) {
            int currentLine2 = currentLine + 1;
            String rawLine = scanner.nextLine();
            String line = rawLine;
            if (inMultilineComment) {
                if (!line.endsWith(MULTILINE_COMMENT_END)) {
                    currentLine = currentLine2;
                } else {
                    inMultilineComment = false;
                    currentLine = currentLine2;
                }
            } else {
                if (line.startsWith(MULTILINE_COMMENT_START)) {
                    inMultilineComment = true;
                } else {
                    int cmtI = line.indexOf(COMMENT);
                    if (cmtI >= 0) {
                        line = line.substring(0, cmtI);
                    }
                    String line2 = line.trim();
                    if (line2.isEmpty()) {
                        currentLine = currentLine2;
                    } else if (line2.contains("=")) {
                        String[] parts = EQUAL.split(line2);
                        if (parts.length != 2) {
                            throw new IllegalArgumentException("Malformed folding statement split into " + parts.length + " parts: " + rawLine + " in " + location);
                        }
                        String leftCharacter = parts[0];
                        String rightCharacter = parts[1];
                        if (leftCharacter.length() == 1 && rightCharacter.length() == 1) {
                            asciiFoldings.put(Character.valueOf(leftCharacter.charAt(0)), Character.valueOf(rightCharacter.charAt(0)));
                        } else {
                            throw new IllegalArgumentException("Malformed folding statement - patterns are not single characters: " + rawLine + " in " + location);
                        }
                    } else {
                        String[] parts2 = SPACES.split(line2);
                        if (parts2.length != 4) {
                            throw new IllegalArgumentException("Malformed rule statement split into " + parts2.length + " parts: " + rawLine + " in " + location);
                        }
                        try {
                            String pattern = stripQuotes(parts2[0]);
                            String replacement1 = stripQuotes(parts2[1]);
                            String replacement2 = stripQuotes(parts2[2]);
                            String replacement3 = stripQuotes(parts2[3]);
                            Rule r = new Rule(pattern, replacement1, replacement2, replacement3);
                            char patternKey = r.pattern.charAt(0);
                            try {
                                List<Rule> rules = ruleMapping.computeIfAbsent(Character.valueOf(patternKey), new Function() { // from class: org.apache.commons.codec.language.DaitchMokotoffSoundex$$ExternalSyntheticLambda0
                                    @Override // java.util.function.Function
                                    public final Object apply(Object obj) {
                                        return DaitchMokotoffSoundex.lambda$parseRules$0((Character) obj);
                                    }
                                });
                                rules.add(r);
                            } catch (IllegalArgumentException e) {
                                e = e;
                                throw new IllegalStateException("Problem parsing line '" + currentLine2 + "' in " + location, e);
                            }
                        } catch (IllegalArgumentException e2) {
                            e = e2;
                        }
                    }
                }
                currentLine = currentLine2;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ List lambda$parseRules$0(Character k) {
        return new ArrayList();
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

    public DaitchMokotoffSoundex() {
        this(true);
    }

    public DaitchMokotoffSoundex(boolean folding) {
        this.folding = folding;
    }

    private String cleanup(String input) {
        StringBuilder sb = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (!Character.isWhitespace(ch) && Character.isLetter(ch)) {
                char ch2 = Character.toLowerCase(ch);
                Character character = FOLDINGS.get(Character.valueOf(ch2));
                if (this.folding && character != null) {
                    ch2 = character.charValue();
                }
                sb.append(ch2);
            }
        }
        return sb.toString();
    }

    @Override // org.apache.commons.codec.Encoder
    public Object encode(Object obj) throws EncoderException {
        if (!(obj instanceof String)) {
            throw new EncoderException("Parameter supplied to DaitchMokotoffSoundex encode is not of type java.lang.String");
        }
        return encode((String) obj);
    }

    @Override // org.apache.commons.codec.StringEncoder
    public String encode(String source) {
        if (source == null) {
            return null;
        }
        return soundex(source, false)[0];
    }

    public String soundex(String source) {
        return String.join("|", soundex(source, true));
    }

    /* JADX WARN: Code restructure failed: missing block: B:41:0x009d, code lost:
    
        if (r4 != 'n') goto L44;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00b6 A[LOOP:3: B:34:0x0086->B:45:0x00b6, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String[] soundex(java.lang.String r21, boolean r22) {
        /*
            Method dump skipped, instructions count: 287
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.codec.language.DaitchMokotoffSoundex.soundex(java.lang.String, boolean):java.lang.String[]");
    }
}
