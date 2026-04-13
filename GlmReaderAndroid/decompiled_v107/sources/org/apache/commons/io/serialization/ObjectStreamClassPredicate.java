package org.apache.commons.io.serialization;

import java.io.ObjectStreamClass;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/* loaded from: classes9.dex */
public class ObjectStreamClassPredicate implements Predicate<ObjectStreamClass> {
    private final List<ClassNameMatcher> acceptMatchers = new ArrayList();
    private final List<ClassNameMatcher> rejectMatchers = new ArrayList();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FullClassNameMatcher lambda$accept$0(Class c) {
        return new FullClassNameMatcher(c.getName());
    }

    public ObjectStreamClassPredicate accept(Class<?>... classes) {
        Stream map = Stream.of((Object[]) classes).map(new Function() { // from class: org.apache.commons.io.serialization.ObjectStreamClassPredicate$$ExternalSyntheticLambda0
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectStreamClassPredicate.lambda$accept$0((Class) obj);
            }
        });
        List<ClassNameMatcher> list = this.acceptMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ObjectStreamClassPredicate$$ExternalSyntheticLambda1(list));
        return this;
    }

    public ObjectStreamClassPredicate accept(ClassNameMatcher matcher) {
        this.acceptMatchers.add(matcher);
        return this;
    }

    public ObjectStreamClassPredicate accept(Pattern pattern) {
        this.acceptMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ObjectStreamClassPredicate accept(String... patterns) {
        Stream map = Stream.of((Object[]) patterns).map(new ObjectStreamClassPredicate$$ExternalSyntheticLambda3());
        List<ClassNameMatcher> list = this.acceptMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ObjectStreamClassPredicate$$ExternalSyntheticLambda4(list));
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ FullClassNameMatcher lambda$reject$1(Class c) {
        return new FullClassNameMatcher(c.getName());
    }

    public ObjectStreamClassPredicate reject(Class<?>... classes) {
        Stream map = Stream.of((Object[]) classes).map(new Function() { // from class: org.apache.commons.io.serialization.ObjectStreamClassPredicate$$ExternalSyntheticLambda2
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ObjectStreamClassPredicate.lambda$reject$1((Class) obj);
            }
        });
        List<ClassNameMatcher> list = this.rejectMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ObjectStreamClassPredicate$$ExternalSyntheticLambda1(list));
        return this;
    }

    public ObjectStreamClassPredicate reject(ClassNameMatcher m) {
        this.rejectMatchers.add(m);
        return this;
    }

    public ObjectStreamClassPredicate reject(Pattern pattern) {
        this.rejectMatchers.add(new RegexpClassNameMatcher(pattern));
        return this;
    }

    public ObjectStreamClassPredicate reject(String... patterns) {
        Stream map = Stream.of((Object[]) patterns).map(new ObjectStreamClassPredicate$$ExternalSyntheticLambda3());
        List<ClassNameMatcher> list = this.rejectMatchers;
        Objects.requireNonNull(list);
        map.forEach(new ObjectStreamClassPredicate$$ExternalSyntheticLambda4(list));
        return this;
    }

    @Override // java.util.function.Predicate
    public boolean test(ObjectStreamClass objectStreamClass) {
        return test(objectStreamClass.getName());
    }

    public boolean test(String name) {
        for (ClassNameMatcher m : this.rejectMatchers) {
            if (m.matches(name)) {
                return false;
            }
        }
        for (ClassNameMatcher m2 : this.acceptMatchers) {
            if (m2.matches(name)) {
                return true;
            }
        }
        return false;
    }
}
