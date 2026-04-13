package org.apache.commons.collections4;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.collections4.bag.HashBag;
import org.apache.commons.collections4.collection.PredicatedCollection;
import org.apache.commons.collections4.collection.SynchronizedCollection;
import org.apache.commons.collections4.collection.TransformedCollection;
import org.apache.commons.collections4.collection.UnmodifiableBoundedCollection;
import org.apache.commons.collections4.collection.UnmodifiableCollection;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.PermutationIterator;

/* loaded from: classes9.dex */
public class CollectionUtils {
    public static final String COLON = ":";
    public static final String COMMA = ",";
    public static final String DEFAULT_TOSTRING_PREFIX = "[";
    public static final String DEFAULT_TOSTRING_SUFFIX = "]";
    public static final Collection EMPTY_COLLECTION = Collections.emptyList();
    public static final int INDEX_NOT_FOUND = -1;

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static class CardinalityHelper<O> {
        final Bag<O> cardinalityA;
        final Bag<O> cardinalityB;

        static boolean equals(Collection<?> a, Collection<?> b) {
            return new HashBag((Collection) a).equals(new HashBag((Collection) b));
        }

        CardinalityHelper(Iterable<? extends O> a, Iterable<? extends O> b) {
            this.cardinalityA = new HashBag(a);
            this.cardinalityB = new HashBag(b);
        }

        public int freqA(Object key) {
            return getFreq(key, this.cardinalityA);
        }

        public int freqB(Object key) {
            return getFreq(key, this.cardinalityB);
        }

        private int getFreq(Object key, Bag<?> freqMap) {
            return freqMap.getCount(key);
        }

        public final int max(Object obj) {
            return Math.max(freqA(obj), freqB(obj));
        }

        public final int min(Object obj) {
            return Math.min(freqA(obj), freqB(obj));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes9.dex */
    public static final class EquatorWrapper<O> {
        private final Equator<? super O> equator;
        private final O object;

        EquatorWrapper(Equator<? super O> equator, O object) {
            this.equator = equator;
            this.object = object;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof EquatorWrapper)) {
                return false;
            }
            return this.equator.equate(this.object, (O) ((EquatorWrapper) obj).getObject());
        }

        public O getObject() {
            return this.object;
        }

        public int hashCode() {
            return this.equator.hash(this.object);
        }
    }

    /* loaded from: classes9.dex */
    private static final class SetOperationCardinalityHelper<O> extends CardinalityHelper<O> implements Iterable<O> {
        private final Set<O> elements;
        private final List<O> newList;

        SetOperationCardinalityHelper(Iterable<? extends O> a, Iterable<? extends O> b) {
            super(a, b);
            this.elements = new HashSet();
            CollectionUtils.addAll(this.elements, a);
            CollectionUtils.addAll(this.elements, b);
            this.newList = new ArrayList(this.elements.size());
        }

        @Override // java.lang.Iterable
        public Iterator<O> iterator() {
            return this.elements.iterator();
        }

        public Collection<O> list() {
            return this.newList;
        }

        public void setCardinality(O obj, int count) {
            for (int i = 0; i < count; i++) {
                this.newList.add(obj);
            }
        }
    }

    public static <C> boolean addAll(Collection<C> collection, C... elements) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(elements, "elements");
        boolean changed = false;
        for (C element : elements) {
            changed |= collection.add(element);
        }
        return changed;
    }

    public static <C> boolean addAll(Collection<C> collection, Enumeration<? extends C> enumeration) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(enumeration, "enumeration");
        boolean changed = false;
        while (enumeration.hasMoreElements()) {
            changed |= collection.add(enumeration.nextElement());
        }
        return changed;
    }

    public static <C> boolean addAll(Collection<C> collection, Iterable<? extends C> iterable) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(iterable, "iterable");
        if (iterable instanceof Collection) {
            return collection.addAll((Collection) iterable);
        }
        return addAll(collection, iterable.iterator());
    }

    public static <C> boolean addAll(Collection<C> collection, Iterator<? extends C> iterator) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(iterator, "iterator");
        boolean changed = false;
        while (iterator.hasNext()) {
            changed |= collection.add(iterator.next());
        }
        return changed;
    }

    public static <T> boolean addIgnoreNull(Collection<T> collection, T object) {
        Objects.requireNonNull(collection, "collection");
        return object != null && collection.add(object);
    }

    @Deprecated
    public static <O> int cardinality(O obj, Iterable<? super O> collection) {
        return IterableUtils.frequency((Iterable) Objects.requireNonNull(collection, "collection"), obj);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void checkIndexBounds(int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }
    }

    public static <O extends Comparable<? super O>> List<O> collate(Iterable<? extends O> a, Iterable<? extends O> b) {
        return collate(a, b, ComparatorUtils.naturalComparator(), true);
    }

    public static <O extends Comparable<? super O>> List<O> collate(Iterable<? extends O> a, Iterable<? extends O> b, boolean includeDuplicates) {
        return collate(a, b, ComparatorUtils.naturalComparator(), includeDuplicates);
    }

    public static <O> List<O> collate(Iterable<? extends O> a, Iterable<? extends O> b, Comparator<? super O> c) {
        return collate(a, b, c, true);
    }

    public static <O> List<O> collate(Iterable<? extends O> iterableA, Iterable<? extends O> iterableB, Comparator<? super O> comparator, boolean includeDuplicates) {
        Objects.requireNonNull(iterableA, "iterableA");
        Objects.requireNonNull(iterableB, "iterableB");
        Objects.requireNonNull(comparator, "comparator");
        int totalSize = ((iterableA instanceof Collection) && (iterableB instanceof Collection)) ? Math.max(1, ((Collection) iterableA).size() + ((Collection) iterableB).size()) : 10;
        Iterator<O> iterator = new CollatingIterator<>(comparator, iterableA.iterator(), iterableB.iterator());
        if (includeDuplicates) {
            return IteratorUtils.toList(iterator, totalSize);
        }
        ArrayList<O> mergedList = new ArrayList<>(totalSize);
        O lastItem = null;
        while (iterator.hasNext()) {
            O item = iterator.next();
            if (lastItem == null || !lastItem.equals(item)) {
                mergedList.add(item);
            }
            lastItem = item;
        }
        mergedList.trimToSize();
        return mergedList;
    }

    public static <I, O, R extends Collection<? super O>> R collect(Iterable<? extends I> iterable, Transformer<? super I, ? extends O> transformer, R r) {
        if (iterable != null) {
            return (R) collect(iterable.iterator(), transformer, r);
        }
        return r;
    }

    public static <I, O> Collection<O> collect(Iterable<I> inputCollection, Transformer<? super I, ? extends O> transformer) {
        int size = 0;
        if (inputCollection != null) {
            size = inputCollection instanceof Collection ? ((Collection) inputCollection).size() : 0;
        }
        Collection<O> answer = size == 0 ? new ArrayList<>() : new ArrayList<>(size);
        return collect(inputCollection, transformer, answer);
    }

    public static <I, O, R extends Collection<? super O>> R collect(Iterator<? extends I> it, Transformer<? super I, ? extends O> transformer, R r) {
        if (it != null && transformer != null) {
            while (it.hasNext()) {
                r.add(transformer.apply(it.next()));
            }
        }
        return r;
    }

    public static <I, O> Collection<O> collect(Iterator<I> inputIterator, Transformer<? super I, ? extends O> transformer) {
        return collect(inputIterator, transformer, new ArrayList());
    }

    public static boolean containsAll(Collection<?> coll1, Collection<?> coll2) {
        Objects.requireNonNull(coll1, "coll1");
        Objects.requireNonNull(coll2, "coll2");
        if (coll2.isEmpty()) {
            return true;
        }
        Set<Object> elementsAlreadySeen = new HashSet<>();
        for (Object nextElement : coll2) {
            if (!elementsAlreadySeen.contains(nextElement)) {
                boolean foundCurrentElement = false;
                Iterator<?> it = coll1.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    Object p = it.next();
                    elementsAlreadySeen.add(p);
                    if (Objects.equals(nextElement, p)) {
                        foundCurrentElement = true;
                        break;
                    }
                }
                if (!foundCurrentElement) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean containsAny(Collection<?> coll1, Collection<?> coll2) {
        Objects.requireNonNull(coll1, "coll1");
        Objects.requireNonNull(coll2, "coll2");
        if (coll1.size() < coll2.size()) {
            for (Object aColl1 : coll1) {
                if (coll2.contains(aColl1)) {
                    return true;
                }
            }
            return false;
        }
        for (Object aColl2 : coll2) {
            if (coll1.contains(aColl2)) {
                return true;
            }
        }
        return false;
    }

    public static <T> boolean containsAny(Collection<?> coll1, T... coll2) {
        Objects.requireNonNull(coll1, "coll1");
        Objects.requireNonNull(coll2, "coll2");
        if (coll1.size() < coll2.length) {
            for (Object aColl1 : coll1) {
                if (ArrayUtils.contains(coll2, aColl1)) {
                    return true;
                }
            }
        } else {
            for (T t : coll2) {
                if (coll1.contains(t)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Deprecated
    public static <C> int countMatches(Iterable<C> input, Predicate<? super C> predicate) {
        if (predicate == null) {
            return 0;
        }
        return (int) IterableUtils.countMatches(input, predicate);
    }

    public static <O> Collection<O> disjunction(Iterable<? extends O> a, Iterable<? extends O> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        SetOperationCardinalityHelper<O> helper = new SetOperationCardinalityHelper<>(a, b);
        Iterator<O> it = helper.iterator();
        while (it.hasNext()) {
            O obj = it.next();
            helper.setCardinality(obj, helper.max(obj) - helper.min(obj));
        }
        return helper.list();
    }

    public static <T> Collection<T> emptyCollection() {
        return EMPTY_COLLECTION;
    }

    public static <T> Collection<T> emptyIfNull(Collection<T> collection) {
        return collection == null ? emptyCollection() : collection;
    }

    @Deprecated
    public static <C> boolean exists(Iterable<C> input, Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAny(input, predicate);
    }

    public static <E> E extractSingleton(Collection<E> collection) {
        Objects.requireNonNull(collection, "collection");
        if (collection.size() != 1) {
            throw new IllegalArgumentException("Can extract singleton only when collection size == 1");
        }
        return collection.iterator().next();
    }

    public static <T> boolean filter(Iterable<T> collection, Predicate<? super T> predicate) {
        boolean result = false;
        if (collection != null && predicate != null) {
            Iterator<T> it = collection.iterator();
            while (it.hasNext()) {
                if (!predicate.test(it.next())) {
                    it.remove();
                    result = true;
                }
            }
        }
        return result;
    }

    public static <T> boolean filterInverse(Iterable<T> collection, Predicate<? super T> predicate) {
        return filter(collection, predicate == null ? null : PredicateUtils.notPredicate(predicate));
    }

    @Deprecated
    public static <T> T find(Iterable<T> iterable, Predicate<? super T> predicate) {
        if (predicate != null) {
            return (T) IterableUtils.find(iterable, predicate);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(Iterable<T> iterable, C c) {
        if (c != null) {
            return (T) IterableUtils.forEachButLast(iterable, c);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> T forAllButLastDo(Iterator<T> it, C c) {
        if (c != null) {
            return (T) IteratorUtils.forEachButLast(it, c);
        }
        return null;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(Iterable<T> collection, C closure) {
        if (closure != null) {
            IterableUtils.forEach(collection, closure);
        }
        return closure;
    }

    @Deprecated
    public static <T, C extends Closure<? super T>> C forAllDo(Iterator<T> iterator, C closure) {
        if (closure != null) {
            IteratorUtils.forEach(iterator, closure);
        }
        return closure;
    }

    @Deprecated
    public static <T> T get(Iterable<T> iterable, int i) {
        Objects.requireNonNull(iterable, "iterable");
        return (T) IterableUtils.get(iterable, i);
    }

    @Deprecated
    public static <T> T get(Iterator<T> it, int i) {
        Objects.requireNonNull(it, "iterator");
        return (T) IteratorUtils.get(it, i);
    }

    public static <K, V> Map.Entry<K, V> get(Map<K, V> map, int index) {
        Objects.requireNonNull(map, "map");
        checkIndexBounds(index);
        return (Map.Entry) get((Iterable) map.entrySet(), index);
    }

    public static Object get(Object object, int index) {
        if (index < 0) {
            throw new IndexOutOfBoundsException("Index cannot be negative: " + index);
        }
        if (object instanceof Map) {
            Map<?, ?> map = (Map) object;
            Iterator<?> iterator = map.entrySet().iterator();
            return IteratorUtils.get(iterator, index);
        }
        if (object instanceof Object[]) {
            return ((Object[]) object)[index];
        }
        if (object instanceof Iterator) {
            Iterator<?> it = (Iterator) object;
            return IteratorUtils.get(it, index);
        }
        if (object instanceof Iterable) {
            Iterable<?> iterable = (Iterable) object;
            return IterableUtils.get(iterable, index);
        }
        if (object instanceof Enumeration) {
            Enumeration<?> it2 = (Enumeration) object;
            return EnumerationUtils.get(it2, index);
        }
        if (object == null) {
            throw new IllegalArgumentException("Unsupported object type: null");
        }
        try {
            return Array.get(object, index);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
        }
    }

    public static <O> Map<O, Integer> getCardinalityMap(Iterable<? extends O> coll) {
        Objects.requireNonNull(coll, "coll");
        Map<O, Integer> count = new HashMap<>();
        for (O obj : coll) {
            Integer c = count.get(obj);
            if (c != null) {
                count.put(obj, Integer.valueOf(c.intValue() + 1));
            } else {
                count.put(obj, 1);
            }
        }
        return count;
    }

    public static <E> int hashCode(Collection<? extends E> collection, Equator<? super E> equator) {
        Objects.requireNonNull(equator, "equator");
        if (collection == null) {
            return 0;
        }
        int i = 1;
        Iterator<? extends E> it = collection.iterator();
        while (it.hasNext()) {
            i = (i * 31) + equator.hash(it.next());
        }
        return i;
    }

    public static <O> Collection<O> intersection(Iterable<? extends O> a, Iterable<? extends O> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        SetOperationCardinalityHelper<O> helper = new SetOperationCardinalityHelper<>(a, b);
        Iterator<O> it = helper.iterator();
        while (it.hasNext()) {
            O obj = it.next();
            helper.setCardinality(obj, helper.min(obj));
        }
        return helper.list();
    }

    public static boolean isEmpty(Collection<?> coll) {
        return coll == null || coll.isEmpty();
    }

    public static boolean isEqualCollection(Collection<?> a, Collection<?> b) {
        return CardinalityHelper.equals(a, b);
    }

    public static <E> boolean isEqualCollection(Collection<? extends E> a, Collection<? extends E> b, final Equator<? super E> equator) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        Objects.requireNonNull(equator, "equator");
        if (a.size() != b.size()) {
            return false;
        }
        Transformer<E, ?> transformer = new Transformer() { // from class: org.apache.commons.collections4.CollectionUtils$$ExternalSyntheticLambda2
            @Override // org.apache.commons.collections4.Transformer
            public final Object transform(Object obj) {
                return CollectionUtils.lambda$isEqualCollection$0(Equator.this, obj);
            }
        };
        return isEqualCollection(collect(a, transformer), collect(b, transformer));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$isEqualCollection$0(Equator equator, Object input) {
        return new EquatorWrapper(equator, input);
    }

    public static boolean isFull(Collection<? extends Object> collection) {
        Objects.requireNonNull(collection, "collection");
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection) collection).isFull();
        }
        try {
            BoundedCollection<?> bcoll = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection);
            return bcoll.isFull();
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !isEmpty(coll);
    }

    public static boolean isProperSubCollection(Collection<?> a, Collection<?> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        return a.size() < b.size() && isSubCollection(a, b);
    }

    public static boolean isSubCollection(Collection<?> a, Collection<?> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        CardinalityHelper<Object> helper = new CardinalityHelper<>(a, b);
        for (Object obj : a) {
            if (helper.freqA(obj) > helper.freqB(obj)) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static <C> boolean matchesAll(Iterable<C> input, Predicate<? super C> predicate) {
        return predicate != null && IterableUtils.matchesAll(input, predicate);
    }

    public static int maxSize(Collection<? extends Object> collection) {
        Objects.requireNonNull(collection, "collection");
        if (collection instanceof BoundedCollection) {
            return ((BoundedCollection) collection).maxSize();
        }
        try {
            BoundedCollection<?> bcoll = UnmodifiableBoundedCollection.unmodifiableBoundedCollection(collection);
            return bcoll.maxSize();
        } catch (IllegalArgumentException e) {
            return -1;
        }
    }

    public static <E> Collection<List<E>> permutations(Collection<E> collection) {
        Objects.requireNonNull(collection, "collection");
        PermutationIterator<E> it = new PermutationIterator<>(collection);
        Collection<List<E>> result = new ArrayList<>();
        while (it.hasNext()) {
            result.add(it.next());
        }
        return result;
    }

    public static <C> Collection<C> predicatedCollection(Collection<C> collection, Predicate<? super C> predicate) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(predicate, "predicate");
        return PredicatedCollection.predicatedCollection(collection, predicate);
    }

    public static <E> Collection<E> removeAll(Collection<E> collection, Collection<?> remove) {
        return ListUtils.removeAll(collection, remove);
    }

    public static <E> Collection<E> removeAll(Iterable<E> collection, Iterable<? extends E> remove, final Equator<? super E> equator) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(remove, "remove");
        Objects.requireNonNull(equator, "equator");
        Transformer<E, EquatorWrapper<E>> transformer = new Transformer() { // from class: org.apache.commons.collections4.CollectionUtils$$ExternalSyntheticLambda1
            @Override // org.apache.commons.collections4.Transformer
            public final Object transform(Object obj) {
                return CollectionUtils.lambda$removeAll$1(Equator.this, obj);
            }
        };
        Set<EquatorWrapper<E>> removeSet = (Set) collect(remove, transformer, new HashSet());
        List<E> list = new ArrayList<>();
        for (E element : collection) {
            if (!removeSet.contains(new EquatorWrapper(equator, element))) {
                list.add(element);
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EquatorWrapper lambda$removeAll$1(Equator equator, Object input) {
        return new EquatorWrapper(equator, input);
    }

    public static <E> Collection<E> removeCount(Collection<E> input, int startIndex, int count) {
        Objects.requireNonNull(input, "input");
        if (startIndex < 0) {
            throw new IndexOutOfBoundsException("The start index can't be less than 0.");
        }
        if (count < 0) {
            throw new IndexOutOfBoundsException("The count can't be less than 0.");
        }
        if (input.size() < startIndex + count) {
            throw new IndexOutOfBoundsException("The sum of start index and count can't be greater than the size of collection.");
        }
        Collection<E> result = new ArrayList<>(count);
        Iterator<E> iterator = input.iterator();
        while (count > 0) {
            if (startIndex > 0) {
                startIndex--;
                iterator.next();
            } else {
                count--;
                result.add(iterator.next());
                iterator.remove();
            }
        }
        return result;
    }

    public static <E> Collection<E> removeRange(Collection<E> input, int startIndex, int endIndex) {
        Objects.requireNonNull(input, "input");
        if (endIndex < startIndex) {
            throw new IllegalArgumentException("The end index can't be less than the start index.");
        }
        if (input.size() < endIndex) {
            throw new IndexOutOfBoundsException("The end index can't be greater than the size of collection.");
        }
        return removeCount(input, startIndex, endIndex - startIndex);
    }

    public static <C> Collection<C> retainAll(Collection<C> collection, Collection<?> retain) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(retain, "retain");
        return ListUtils.retainAll(collection, retain);
    }

    public static <E> Collection<E> retainAll(Iterable<E> collection, Iterable<? extends E> retain, final Equator<? super E> equator) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(retain, "retain");
        Objects.requireNonNull(equator, "equator");
        Transformer<E, EquatorWrapper<E>> transformer = new Transformer() { // from class: org.apache.commons.collections4.CollectionUtils$$ExternalSyntheticLambda0
            @Override // org.apache.commons.collections4.Transformer
            public final Object transform(Object obj) {
                return CollectionUtils.lambda$retainAll$2(Equator.this, obj);
            }
        };
        Set<EquatorWrapper<E>> retainSet = (Set) collect(retain, transformer, new HashSet());
        List<E> list = new ArrayList<>();
        for (E element : collection) {
            if (retainSet.contains(new EquatorWrapper(equator, element))) {
                list.add(element);
            }
        }
        return list;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ EquatorWrapper lambda$retainAll$2(Equator equator, Object input) {
        return new EquatorWrapper(equator, input);
    }

    public static void reverseArray(Object[] array) {
        Objects.requireNonNull(array, "array");
        int j = array.length - 1;
        for (int i = 0; j > i; i++) {
            Object tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
        }
    }

    public static <O> Collection<O> select(Iterable<? extends O> inputCollection, Predicate<? super O> predicate) {
        int size = 0;
        if (inputCollection != null) {
            size = inputCollection instanceof Collection ? ((Collection) inputCollection).size() : 0;
        }
        Collection<O> answer = size == 0 ? new ArrayList<>() : new ArrayList<>(size);
        return select(inputCollection, predicate, answer);
    }

    public static <O, R extends Collection<? super O>> R select(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r) {
        if (iterable != null && predicate != null) {
            for (O o : iterable) {
                if (predicate.test(o)) {
                    r.add(o);
                }
            }
        }
        return r;
    }

    public static <O, R extends Collection<? super O>> R select(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r, R r2) {
        if (iterable != null && predicate != null) {
            for (O o : iterable) {
                if (predicate.test(o)) {
                    r.add(o);
                } else {
                    r2.add(o);
                }
            }
        }
        return r;
    }

    public static <O> Collection<O> selectRejected(Iterable<? extends O> inputCollection, Predicate<? super O> predicate) {
        int size = 0;
        if (inputCollection != null) {
            size = inputCollection instanceof Collection ? ((Collection) inputCollection).size() : 0;
        }
        Collection<O> answer = size == 0 ? new ArrayList<>() : new ArrayList<>(size);
        return selectRejected(inputCollection, predicate, answer);
    }

    public static <O, R extends Collection<? super O>> R selectRejected(Iterable<? extends O> iterable, Predicate<? super O> predicate, R r) {
        if (iterable != null && predicate != null) {
            for (O o : iterable) {
                if (!predicate.test(o)) {
                    r.add(o);
                }
            }
        }
        return r;
    }

    public static int size(Object object) {
        if (object == null) {
            return 0;
        }
        int total = 0;
        if (object instanceof Map) {
            int total2 = ((Map) object).size();
            return total2;
        }
        if (object instanceof Collection) {
            int total3 = ((Collection) object).size();
            return total3;
        }
        if (object instanceof Iterable) {
            int total4 = IterableUtils.size((Iterable) object);
            return total4;
        }
        if (object instanceof Object[]) {
            int total5 = ((Object[]) object).length;
            return total5;
        }
        if (object instanceof Iterator) {
            int total6 = IteratorUtils.size((Iterator) object);
            return total6;
        }
        if (object instanceof Enumeration) {
            Enumeration<?> it = (Enumeration) object;
            while (it.hasMoreElements()) {
                total++;
                it.nextElement();
            }
            return total;
        }
        try {
            int total7 = Array.getLength(object);
            return total7;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
        }
    }

    public static boolean sizeIsEmpty(Object object) {
        if (object == null) {
            return true;
        }
        if (object instanceof Collection) {
            return ((Collection) object).isEmpty();
        }
        if (object instanceof Iterable) {
            return IterableUtils.isEmpty((Iterable) object);
        }
        if (object instanceof Map) {
            return ((Map) object).isEmpty();
        }
        if (object instanceof Object[]) {
            return ((Object[]) object).length == 0;
        }
        if (object instanceof Iterator) {
            return true ^ ((Iterator) object).hasNext();
        }
        if (object instanceof Enumeration) {
            return true ^ ((Enumeration) object).hasMoreElements();
        }
        try {
            return Array.getLength(object) == 0;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported object type: " + object.getClass().getName());
        }
    }

    public static <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b) {
        Predicate<O> p = TruePredicate.truePredicate();
        return subtract(a, b, p);
    }

    public static <O> Collection<O> subtract(Iterable<? extends O> a, Iterable<? extends O> b, Predicate<O> p) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        Objects.requireNonNull(p, "p");
        ArrayList<O> list = new ArrayList<>();
        HashBag<O> bag = new HashBag<>();
        for (O element : b) {
            if (p.test(element)) {
                bag.add(element);
            }
        }
        for (O element2 : a) {
            if (!bag.remove(element2, 1)) {
                list.add(element2);
            }
        }
        return list;
    }

    @Deprecated
    public static <C> Collection<C> synchronizedCollection(Collection<C> collection) {
        Objects.requireNonNull(collection, "collection");
        return SynchronizedCollection.synchronizedCollection(collection);
    }

    public static <C> void transform(Collection<C> collection, Transformer<? super C, ? extends C> transformer) {
        if (collection != null && transformer != null) {
            if (collection instanceof List) {
                ListIterator listIterator = ((List) collection).listIterator();
                while (listIterator.hasNext()) {
                    listIterator.set(transformer.apply((C) listIterator.next()));
                }
            } else {
                Collection<? extends C> collect = collect(collection, transformer);
                collection.clear();
                collection.addAll(collect);
            }
        }
    }

    public static <E> Collection<E> transformingCollection(Collection<E> collection, Transformer<? super E, ? extends E> transformer) {
        Objects.requireNonNull(collection, "collection");
        Objects.requireNonNull(transformer, "transformer");
        return TransformedCollection.transformingCollection(collection, transformer);
    }

    public static <O> Collection<O> union(Iterable<? extends O> a, Iterable<? extends O> b) {
        Objects.requireNonNull(a, "a");
        Objects.requireNonNull(b, "b");
        SetOperationCardinalityHelper<O> helper = new SetOperationCardinalityHelper<>(a, b);
        Iterator<O> it = helper.iterator();
        while (it.hasNext()) {
            O obj = it.next();
            helper.setCardinality(obj, helper.max(obj));
        }
        return helper.list();
    }

    @Deprecated
    public static <C> Collection<C> unmodifiableCollection(Collection<? extends C> collection) {
        Objects.requireNonNull(collection, "collection");
        return UnmodifiableCollection.unmodifiableCollection(collection);
    }

    private CollectionUtils() {
    }
}
