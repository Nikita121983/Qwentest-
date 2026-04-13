package org.apache.commons.codec.language.bm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.apache.commons.codec.language.bm.Languages;
import org.apache.commons.codec.language.bm.Rule;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.util.Chars;
import org.apache.logging.log4j.util.ProcessIdUtil;

/* loaded from: classes9.dex */
public class PhoneticEngine {
    private static final int DEFAULT_MAX_PHONEMES = 20;
    private static final Map<NameType, Set<String>> NAME_PREFIXES = new EnumMap(NameType.class);
    private static final Pattern QUOTE = Pattern.compile("'");
    private final boolean concat;
    private final Lang lang;
    private final int maxPhonemes;
    private final NameType nameType;
    private final RuleType ruleType;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes9.dex */
    public static final class PhonemeBuilder {
        private final Set<Rule.Phoneme> phonemes;

        public static PhonemeBuilder empty(Languages.LanguageSet languages) {
            return new PhonemeBuilder(new Rule.Phoneme("", languages));
        }

        private PhonemeBuilder(Rule.Phoneme phoneme) {
            this.phonemes = new LinkedHashSet();
            this.phonemes.add(phoneme);
        }

        private PhonemeBuilder(Set<Rule.Phoneme> phonemes) {
            this.phonemes = phonemes;
        }

        public void append(final CharSequence str) {
            this.phonemes.forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$PhonemeBuilder$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((Rule.Phoneme) obj).append(str);
                }
            });
        }

        public void apply(Rule.PhonemeExpr phonemeExpr, int maxPhonemes) {
            Set<Rule.Phoneme> newPhonemes = new LinkedHashSet<>(Math.min(this.phonemes.size() * phonemeExpr.size(), maxPhonemes));
            loop0: for (Rule.Phoneme left : this.phonemes) {
                for (Rule.Phoneme right : phonemeExpr.getPhonemes()) {
                    Languages.LanguageSet languages = left.getLanguages().restrictTo(right.getLanguages());
                    if (!languages.isEmpty()) {
                        Rule.Phoneme join = new Rule.Phoneme(left, right, languages);
                        if (newPhonemes.size() < maxPhonemes) {
                            newPhonemes.add(join);
                            if (newPhonemes.size() >= maxPhonemes) {
                                break loop0;
                            }
                        } else {
                            continue;
                        }
                    }
                }
            }
            this.phonemes.clear();
            this.phonemes.addAll(newPhonemes);
        }

        public Set<Rule.Phoneme> getPhonemes() {
            return this.phonemes;
        }

        public String makeString() {
            return (String) this.phonemes.stream().map(new Function() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$PhonemeBuilder$$ExternalSyntheticLambda0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((Rule.Phoneme) obj).getPhonemeText();
                }
            }).collect(Collectors.joining("|"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class RulesApplication {
        private final Map<String, List<Rule>> finalRules;
        private boolean found;
        private int i;
        private final CharSequence input;
        private final int maxPhonemes;
        private final PhonemeBuilder phonemeBuilder;

        RulesApplication(Map<String, List<Rule>> finalRules, CharSequence input, PhonemeBuilder phonemeBuilder, int i, int maxPhonemes) {
            Objects.requireNonNull(finalRules, "finalRules");
            this.finalRules = finalRules;
            this.phonemeBuilder = phonemeBuilder;
            this.input = input;
            this.i = i;
            this.maxPhonemes = maxPhonemes;
        }

        public int getI() {
            return this.i;
        }

        public PhonemeBuilder getPhonemeBuilder() {
            return this.phonemeBuilder;
        }

        public RulesApplication invoke() {
            this.found = false;
            int patternLength = 1;
            List<Rule> rules = this.finalRules.get(this.input.subSequence(this.i, this.i + 1));
            if (rules != null) {
                Iterator<Rule> it = rules.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Rule rule = it.next();
                    String pattern = rule.getPattern();
                    patternLength = pattern.length();
                    if (rule.patternAndContextMatches(this.input, this.i)) {
                        this.phonemeBuilder.apply(rule.getPhoneme(), this.maxPhonemes);
                        this.found = true;
                        break;
                    }
                }
            }
            if (!this.found) {
                patternLength = 1;
            }
            this.i += patternLength;
            return this;
        }

        public boolean isFound() {
            return this.found;
        }
    }

    static {
        NAME_PREFIXES.put(NameType.ASHKENAZI, Collections.unmodifiableSet(new HashSet(Arrays.asList("bar", "ben", "da", "de", "van", "von"))));
        NAME_PREFIXES.put(NameType.SEPHARDIC, Collections.unmodifiableSet(new HashSet(Arrays.asList("al", "el", "da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
        NAME_PREFIXES.put(NameType.GENERIC, Collections.unmodifiableSet(new HashSet(Arrays.asList("da", "dal", "de", "del", "dela", "de la", "della", "des", "di", "do", "dos", "du", "van", "von"))));
    }

    private static String join(List<String> strings, String sep) {
        return (String) strings.stream().collect(Collectors.joining(sep));
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean concatenate) {
        this(nameType, ruleType, concatenate, 20);
    }

    public PhoneticEngine(NameType nameType, RuleType ruleType, boolean concatenate, int maxPhonemes) {
        if (ruleType == RuleType.RULES) {
            throw new IllegalArgumentException("ruleType must not be " + RuleType.RULES);
        }
        this.nameType = nameType;
        this.ruleType = ruleType;
        this.concat = concatenate;
        this.lang = Lang.instance(nameType);
        this.maxPhonemes = maxPhonemes;
    }

    private PhonemeBuilder applyFinalRules(PhonemeBuilder phonemeBuilder, final Map<String, List<Rule>> finalRules) {
        Objects.requireNonNull(finalRules, "finalRules");
        if (finalRules.isEmpty()) {
            return phonemeBuilder;
        }
        final Map<Rule.Phoneme, Rule.Phoneme> phonemes = new TreeMap<>(Rule.Phoneme.COMPARATOR);
        phonemeBuilder.getPhonemes().forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$$ExternalSyntheticLambda3
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PhoneticEngine.this.m2029x6a0b9e3b(finalRules, phonemes, (Rule.Phoneme) obj);
            }
        });
        return new PhonemeBuilder(phonemes.keySet());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$applyFinalRules$0$org-apache-commons-codec-language-bm-PhoneticEngine, reason: not valid java name */
    public /* synthetic */ void m2029x6a0b9e3b(Map finalRules, final Map phonemes, Rule.Phoneme phoneme) {
        PhonemeBuilder subBuilder = PhonemeBuilder.empty(phoneme.getLanguages());
        CharSequence phonemeText = phoneme.getPhonemeText();
        PhonemeBuilder subBuilder2 = subBuilder;
        int i = 0;
        while (i < phonemeText.length()) {
            Map finalRules2 = finalRules;
            RulesApplication rulesApplication = new RulesApplication(finalRules2, phonemeText, subBuilder2, i, this.maxPhonemes).invoke();
            boolean found = rulesApplication.isFound();
            subBuilder2 = rulesApplication.getPhonemeBuilder();
            if (!found) {
                subBuilder2.append(phonemeText.subSequence(i, i + 1));
            }
            i = rulesApplication.getI();
            finalRules = finalRules2;
        }
        subBuilder2.getPhonemes().forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$$ExternalSyntheticLambda2
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                PhoneticEngine.lambda$applyFinalRules$1(phonemes, (Rule.Phoneme) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$applyFinalRules$1(Map phonemes, Rule.Phoneme newPhoneme) {
        if (phonemes.containsKey(newPhoneme)) {
            Rule.Phoneme oldPhoneme = (Rule.Phoneme) phonemes.remove(newPhoneme);
            Rule.Phoneme mergedPhoneme = oldPhoneme.mergeWithLanguage(newPhoneme.getLanguages());
            phonemes.put(mergedPhoneme, mergedPhoneme);
            return;
        }
        phonemes.put(newPhoneme, newPhoneme);
    }

    public String encode(String input) {
        Languages.LanguageSet languageSet = this.lang.guessLanguages(input);
        return encode(input, languageSet);
    }

    public String encode(String input, Languages.LanguageSet languageSet) {
        String input2;
        Map<String, List<Rule>> rules = Rule.getInstanceMap(this.nameType, RuleType.RULES, languageSet);
        Map<String, List<Rule>> finalRules1 = Rule.getInstanceMap(this.nameType, this.ruleType, "common");
        Map<String, List<Rule>> finalRules2 = Rule.getInstanceMap(this.nameType, this.ruleType, languageSet);
        String input3 = input.toLowerCase(Locale.ENGLISH).replace('-', Chars.SPACE).trim();
        if (this.nameType == NameType.GENERIC) {
            if (input3.startsWith("d'")) {
                String remainder = input3.substring(2);
                String combined = "d" + remainder;
                return "(" + encode(remainder) + ")-(" + encode(combined) + ")";
            }
            for (String l : NAME_PREFIXES.get(this.nameType)) {
                if (input3.startsWith(l + StringUtils.SPACE)) {
                    String remainder2 = input3.substring(l.length() + 1);
                    String combined2 = l + remainder2;
                    return "(" + encode(remainder2) + ")-(" + encode(combined2) + ")";
                }
            }
        }
        List<String> words = Arrays.asList(ResourceConstants.SPACES.split(input3));
        final List<String> words2 = new ArrayList<>();
        switch (this.nameType) {
            case SEPHARDIC:
                words.forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$$ExternalSyntheticLambda0
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        PhoneticEngine.lambda$encode$0(words2, (String) obj);
                    }
                });
                words2.removeAll(NAME_PREFIXES.get(this.nameType));
                break;
            case ASHKENAZI:
                words2.addAll(words);
                words2.removeAll(NAME_PREFIXES.get(this.nameType));
                break;
            case GENERIC:
                words2.addAll(words);
                break;
            default:
                throw new IllegalStateException("Unreachable case: " + this.nameType);
        }
        if (this.concat) {
            String input4 = join(words2, StringUtils.SPACE);
            input2 = input4;
        } else if (words2.size() == 1) {
            String input5 = words.iterator().next();
            input2 = input5;
        } else if (words2.isEmpty()) {
            input2 = input3;
        } else {
            final StringBuilder result = new StringBuilder();
            words2.forEach(new Consumer() { // from class: org.apache.commons.codec.language.bm.PhoneticEngine$$ExternalSyntheticLambda1
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    PhoneticEngine.this.m2030x991e5e3d(result, (String) obj);
                }
            });
            return result.substring(1);
        }
        PhonemeBuilder phonemeBuilder = PhonemeBuilder.empty(languageSet);
        PhonemeBuilder phonemeBuilder2 = phonemeBuilder;
        int i = 0;
        while (i < input2.length()) {
            RulesApplication rulesApplication = new RulesApplication(rules, input2, phonemeBuilder2, i, this.maxPhonemes).invoke();
            i = rulesApplication.getI();
            phonemeBuilder2 = rulesApplication.getPhonemeBuilder();
        }
        return applyFinalRules(applyFinalRules(phonemeBuilder2, finalRules1), finalRules2).makeString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$encode$0(List words2, String aWord) {
        String[] parts = QUOTE.split(aWord, -1);
        words2.add(parts[parts.length - 1]);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$encode$1$org-apache-commons-codec-language-bm-PhoneticEngine, reason: not valid java name */
    public /* synthetic */ void m2030x991e5e3d(StringBuilder result, String word) {
        result.append(ProcessIdUtil.DEFAULT_PROCESSID).append(encode(word));
    }

    public Lang getLang() {
        return this.lang;
    }

    public int getMaxPhonemes() {
        return this.maxPhonemes;
    }

    public NameType getNameType() {
        return this.nameType;
    }

    public RuleType getRuleType() {
        return this.ruleType;
    }

    public boolean isConcat() {
        return this.concat;
    }
}
