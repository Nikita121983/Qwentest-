package org.apache.commons.codec.language.bm;

import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.apache.commons.codec.Resources;
import org.apache.commons.codec.language.bm.Languages;

/* loaded from: classes9.dex */
public class Languages {
    public static final String ANY = "any";
    private final Set<String> languages;
    private static final Map<NameType, Languages> LANGUAGES = new EnumMap(NameType.class);
    public static final LanguageSet NO_LANGUAGES = new LanguageSet() { // from class: org.apache.commons.codec.language.bm.Languages.1
        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean contains(String language) {
            return false;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public String getAny() {
            throw new NoSuchElementException("Can't fetch any language from the empty language set.");
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isEmpty() {
            return true;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isSingleton() {
            return false;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet merge(LanguageSet other) {
            return other;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet restrictTo(LanguageSet other) {
            return this;
        }

        public String toString() {
            return "NO_LANGUAGES";
        }
    };
    public static final LanguageSet ANY_LANGUAGE = new LanguageSet() { // from class: org.apache.commons.codec.language.bm.Languages.2
        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean contains(String language) {
            return true;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public String getAny() {
            throw new NoSuchElementException("Can't fetch any language from the any language set.");
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isEmpty() {
            return false;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isSingleton() {
            return false;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet merge(LanguageSet other) {
            return other;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet restrictTo(LanguageSet other) {
            return other;
        }

        public String toString() {
            return "ANY_LANGUAGE";
        }
    };

    /* loaded from: classes9.dex */
    public static abstract class LanguageSet {
        public abstract boolean contains(String str);

        public abstract String getAny();

        public abstract boolean isEmpty();

        public abstract boolean isSingleton();

        /* JADX INFO: Access modifiers changed from: package-private */
        public abstract LanguageSet merge(LanguageSet languageSet);

        public abstract LanguageSet restrictTo(LanguageSet languageSet);

        public static LanguageSet from(Set<String> languages) {
            return languages.isEmpty() ? Languages.NO_LANGUAGES : new SomeLanguages(languages);
        }
    }

    /* loaded from: classes9.dex */
    public static final class SomeLanguages extends LanguageSet {
        private final Set<String> languages;

        private SomeLanguages(Set<String> languages) {
            this.languages = Collections.unmodifiableSet(languages);
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean contains(String language) {
            return this.languages.contains(language);
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public String getAny() {
            return this.languages.iterator().next();
        }

        public Set<String> getLanguages() {
            return this.languages;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isEmpty() {
            return this.languages.isEmpty();
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public boolean isSingleton() {
            return this.languages.size() == 1;
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet merge(LanguageSet other) {
            if (other == Languages.NO_LANGUAGES) {
                return this;
            }
            if (other == Languages.ANY_LANGUAGE) {
                return other;
            }
            SomeLanguages someLanguages = (SomeLanguages) other;
            Set<String> set = new HashSet<>(this.languages);
            set.addAll(someLanguages.languages);
            return from(set);
        }

        @Override // org.apache.commons.codec.language.bm.Languages.LanguageSet
        public LanguageSet restrictTo(LanguageSet other) {
            if (other == Languages.NO_LANGUAGES) {
                return other;
            }
            if (other == Languages.ANY_LANGUAGE) {
                return this;
            }
            final SomeLanguages someLanguages = (SomeLanguages) other;
            return from((Set) this.languages.stream().filter(new Predicate() { // from class: org.apache.commons.codec.language.bm.Languages$SomeLanguages$$ExternalSyntheticLambda0
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    boolean contains;
                    contains = Languages.SomeLanguages.this.languages.contains((String) obj);
                    return contains;
                }
            }).collect(Collectors.toSet()));
        }

        public String toString() {
            return "Languages(" + this.languages.toString() + ")";
        }
    }

    static {
        for (NameType s : NameType.values()) {
            LANGUAGES.put(s, getInstance(langResourceName(s)));
        }
    }

    public static Languages getInstance(NameType nameType) {
        return LANGUAGES.get(nameType);
    }

    public static Languages getInstance(String languagesResourceName) {
        Set<String> ls = new HashSet<>();
        Scanner lsScanner = new Scanner(Resources.getInputStream(languagesResourceName), ResourceConstants.ENCODING);
        boolean inExtendedComment = false;
        while (lsScanner.hasNextLine()) {
            try {
                String line = lsScanner.nextLine().trim();
                if (inExtendedComment) {
                    if (line.endsWith("*/")) {
                        inExtendedComment = false;
                    }
                } else if (line.startsWith("/*")) {
                    inExtendedComment = true;
                } else if (!line.isEmpty()) {
                    ls.add(line);
                }
            } catch (Throwable th) {
                try {
                    lsScanner.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        }
        Languages languages = new Languages(Collections.unmodifiableSet(ls));
        lsScanner.close();
        return languages;
    }

    private static String langResourceName(NameType nameType) {
        return String.format("/org/apache/commons/codec/language/bm/%s_languages.txt", nameType.getName());
    }

    private Languages(Set<String> languages) {
        this.languages = languages;
    }

    public Set<String> getLanguages() {
        return this.languages;
    }
}
