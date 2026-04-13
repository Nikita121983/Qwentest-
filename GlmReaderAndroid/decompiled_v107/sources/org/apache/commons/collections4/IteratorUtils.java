package org.apache.commons.collections4;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.Spliterators;
import java.util.function.IntFunction;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.apache.commons.collections4.functors.EqualPredicate;
import org.apache.commons.collections4.iterators.ArrayIterator;
import org.apache.commons.collections4.iterators.ArrayListIterator;
import org.apache.commons.collections4.iterators.BoundedIterator;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.apache.commons.collections4.iterators.EmptyIterator;
import org.apache.commons.collections4.iterators.EmptyListIterator;
import org.apache.commons.collections4.iterators.EmptyMapIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedIterator;
import org.apache.commons.collections4.iterators.EmptyOrderedMapIterator;
import org.apache.commons.collections4.iterators.EnumerationIterator;
import org.apache.commons.collections4.iterators.FilterIterator;
import org.apache.commons.collections4.iterators.FilterListIterator;
import org.apache.commons.collections4.iterators.IteratorChain;
import org.apache.commons.collections4.iterators.IteratorEnumeration;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.collections4.iterators.LazyIteratorChain;
import org.apache.commons.collections4.iterators.ListIteratorWrapper;
import org.apache.commons.collections4.iterators.LoopingIterator;
import org.apache.commons.collections4.iterators.LoopingListIterator;
import org.apache.commons.collections4.iterators.NodeListIterator;
import org.apache.commons.collections4.iterators.ObjectArrayIterator;
import org.apache.commons.collections4.iterators.ObjectArrayListIterator;
import org.apache.commons.collections4.iterators.ObjectGraphIterator;
import org.apache.commons.collections4.iterators.PeekingIterator;
import org.apache.commons.collections4.iterators.PushbackIterator;
import org.apache.commons.collections4.iterators.SingletonIterator;
import org.apache.commons.collections4.iterators.SingletonListIterator;
import org.apache.commons.collections4.iterators.SkippingIterator;
import org.apache.commons.collections4.iterators.TransformIterator;
import org.apache.commons.collections4.iterators.UnmodifiableIterator;
import org.apache.commons.collections4.iterators.UnmodifiableListIterator;
import org.apache.commons.collections4.iterators.UnmodifiableMapIterator;
import org.apache.commons.collections4.iterators.ZippingIterator;
import org.apache.xmlbeans.XmlErrorCodes;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/* loaded from: classes9.dex */
public class IteratorUtils {
    private static final String DEFAULT_TOSTRING_DELIMITER = ", ";
    public static final ResettableIterator EMPTY_ITERATOR = EmptyIterator.RESETTABLE_INSTANCE;
    public static final ResettableListIterator EMPTY_LIST_ITERATOR = EmptyListIterator.RESETTABLE_INSTANCE;
    public static final OrderedIterator EMPTY_ORDERED_ITERATOR = EmptyOrderedIterator.INSTANCE;
    public static final MapIterator EMPTY_MAP_ITERATOR = EmptyMapIterator.INSTANCE;
    public static final OrderedMapIterator EMPTY_ORDERED_MAP_ITERATOR = EmptyOrderedMapIterator.INSTANCE;

    private static <E, C extends Collection<E>> C addAll(Iterator<? extends E> iterator, C list) {
        Objects.requireNonNull(iterator, "iterator");
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }

    public static <E> ResettableIterator<E> arrayIterator(E... array) {
        return new ObjectArrayIterator(array);
    }

    public static <E> ResettableIterator<E> arrayIterator(E[] array, int start) {
        return new ObjectArrayIterator(array, start);
    }

    public static <E> ResettableIterator<E> arrayIterator(E[] array, int start, int end) {
        return new ObjectArrayIterator(array, start, end);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object array) {
        return new ArrayIterator(array);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object array, int start) {
        return new ArrayIterator(array, start);
    }

    public static <E> ResettableIterator<E> arrayIterator(Object array, int start, int end) {
        return new ArrayIterator(array, start, end);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E... array) {
        return new ObjectArrayListIterator(array);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E[] array, int start) {
        return new ObjectArrayListIterator(array, start);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(E[] array, int start, int end) {
        return new ObjectArrayListIterator(array, start, end);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object array) {
        return new ArrayListIterator(array);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object array, int start) {
        return new ArrayListIterator(array, start);
    }

    public static <E> ResettableListIterator<E> arrayListIterator(Object array, int start, int end) {
        return new ArrayListIterator(array, start, end);
    }

    public static <E> Enumeration<E> asEnumeration(Iterator<? extends E> iterator) {
        return new IteratorEnumeration((Iterator) Objects.requireNonNull(iterator, "iterator"));
    }

    public static <E> Iterable<E> asIterable(Iterator<? extends E> iterator) {
        Objects.requireNonNull(iterator, "iterator");
        return new IteratorIterable(iterator, false);
    }

    public static <E> Iterator<E> asIterator(Enumeration<? extends E> enumeration) {
        return new EnumerationIterator((Enumeration) Objects.requireNonNull(enumeration, "enumeration"));
    }

    public static <E> Iterator<E> asIterator(Enumeration<? extends E> enumeration, Collection<? super E> removeCollection) {
        return new EnumerationIterator((Enumeration) Objects.requireNonNull(enumeration, "enumeration"), (Collection) Objects.requireNonNull(removeCollection, "removeCollection"));
    }

    public static <E> Iterable<E> asMultipleUseIterable(Iterator<? extends E> iterator) {
        Objects.requireNonNull(iterator, "iterator");
        return new IteratorIterable(iterator, true);
    }

    public static <E> BoundedIterator<E> boundedIterator(Iterator<? extends E> iterator, long max) {
        return boundedIterator(iterator, 0L, max);
    }

    public static <E> BoundedIterator<E> boundedIterator(Iterator<? extends E> iterator, long offset, long max) {
        return new BoundedIterator<>(iterator, offset, max);
    }

    public static <E> Iterator<E> chainedIterator(Collection<? extends Iterator<? extends E>> iterators) {
        return new IteratorChain(iterators);
    }

    public static <E> Iterator<E> chainedIterator(Iterator<? extends E>... iterators) {
        return new IteratorChain(iterators);
    }

    public static <E> Iterator<E> chainedIterator(Iterator<? extends E> iterator1, Iterator<? extends E> iterator2) {
        return new IteratorChain(iterator1, iterator2);
    }

    public static <E> Iterator<E> chainedIterator(final Iterator<? extends Iterator<? extends E>> iterators) {
        return new LazyIteratorChain<E>() { // from class: org.apache.commons.collections4.IteratorUtils.1
            @Override // org.apache.commons.collections4.iterators.LazyIteratorChain
            protected Iterator<? extends E> nextIterator(int count) {
                if (iterators.hasNext()) {
                    return (Iterator) iterators.next();
                }
                return null;
            }
        };
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Collection<Iterator<? extends E>> iterators) {
        return new CollatingIterator(comparator == null ? ComparatorUtils.NATURAL_COMPARATOR : comparator, iterators);
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E>... iterators) {
        return new CollatingIterator(comparator == null ? ComparatorUtils.NATURAL_COMPARATOR : comparator, iterators);
    }

    public static <E> Iterator<E> collatedIterator(Comparator<? super E> comparator, Iterator<? extends E> iterator1, Iterator<? extends E> iterator2) {
        return new CollatingIterator(comparator == null ? ComparatorUtils.NATURAL_COMPARATOR : comparator, iterator1, iterator2);
    }

    public static <E> boolean contains(Iterator<E> iterator, Object object) {
        return matchesAny(iterator, EqualPredicate.equalPredicate(object));
    }

    public static <E> ResettableIterator<E> emptyIterator() {
        return EmptyIterator.resettableEmptyIterator();
    }

    public static <E> ResettableListIterator<E> emptyListIterator() {
        return EmptyListIterator.resettableEmptyListIterator();
    }

    public static <K, V> MapIterator<K, V> emptyMapIterator() {
        return EmptyMapIterator.emptyMapIterator();
    }

    public static <E> OrderedIterator<E> emptyOrderedIterator() {
        return EmptyOrderedIterator.emptyOrderedIterator();
    }

    public static <K, V> OrderedMapIterator<K, V> emptyOrderedMapIterator() {
        return EmptyOrderedMapIterator.emptyOrderedMapIterator();
    }

    public static <E> Iterator<E> filteredIterator(Iterator<? extends E> iterator, Predicate<? super E> predicate) {
        Objects.requireNonNull(iterator, "iterator");
        Objects.requireNonNull(predicate, "predicate");
        return new FilterIterator(iterator, predicate);
    }

    public static <E> ListIterator<E> filteredListIterator(ListIterator<? extends E> listIterator, Predicate<? super E> predicate) {
        Objects.requireNonNull(listIterator, "listIterator");
        Objects.requireNonNull(predicate, "predicate");
        return new FilterListIterator(listIterator, predicate);
    }

    public static <E> E find(Iterator<E> it, Predicate<? super E> predicate) {
        return (E) find(it, predicate, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x0019, code lost:
    
        return r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:2:0x0005, code lost:
    
        if (r2 != null) goto L4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:4:0x000b, code lost:
    
        if (r2.hasNext() == false) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x000d, code lost:
    
        r0 = r2.next();
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x0015, code lost:
    
        if (r3.test(r0) == false) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0017, code lost:
    
        return r0;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static <E> E find(java.util.Iterator<E> r2, org.apache.commons.collections4.Predicate<? super E> r3, E r4) {
        /*
            java.lang.String r0 = "predicate"
            java.util.Objects.requireNonNull(r3, r0)
            if (r2 == 0) goto L19
        L7:
            boolean r0 = r2.hasNext()
            if (r0 == 0) goto L19
            java.lang.Object r0 = r2.next()
            boolean r1 = r3.test(r0)
            if (r1 == 0) goto L18
            return r0
        L18:
            goto L7
        L19:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.collections4.IteratorUtils.find(java.util.Iterator, org.apache.commons.collections4.Predicate, java.lang.Object):java.lang.Object");
    }

    public static <E> E first(Iterator<E> it) {
        return (E) get(it, 0);
    }

    public static <E> void forEach(Iterator<E> iterator, Closure<? super E> closure) {
        Objects.requireNonNull(closure, "closure");
        if (iterator != null) {
            while (iterator.hasNext()) {
                closure.accept(iterator.next());
            }
        }
    }

    public static <E> E forEachButLast(Iterator<E> iterator, Closure<? super E> closure) {
        Objects.requireNonNull(closure, "closure");
        if (iterator != null) {
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (!iterator.hasNext()) {
                    return element;
                }
                closure.accept(element);
            }
            return null;
        }
        return null;
    }

    public static <E> E get(Iterator<E> it, int i) {
        return (E) get(it, i, new IntFunction() { // from class: org.apache.commons.collections4.IteratorUtils$$ExternalSyntheticLambda0
            @Override // java.util.function.IntFunction
            public final Object apply(int i2) {
                return IteratorUtils.lambda$get$0(i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Object lambda$get$0(int ioob) {
        throw new IndexOutOfBoundsException("Entry does not exist: " + ioob);
    }

    static <E> E get(Iterator<E> iterator, int index, IntFunction<E> defaultSupplier) {
        int i = index;
        CollectionUtils.checkIndexBounds(i);
        while (iterator.hasNext()) {
            i--;
            if (i == -1) {
                return iterator.next();
            }
            iterator.next();
        }
        return defaultSupplier.apply(i);
    }

    public static Iterator<?> getIterator(Object obj) {
        if (obj == null) {
            return emptyIterator();
        }
        if (obj instanceof Iterator) {
            return (Iterator) obj;
        }
        if (obj instanceof Iterable) {
            return ((Iterable) obj).iterator();
        }
        if (obj instanceof Object[]) {
            return new ObjectArrayIterator((Object[]) obj);
        }
        if (obj instanceof Enumeration) {
            return new EnumerationIterator((Enumeration) obj);
        }
        if (obj instanceof Map) {
            return ((Map) obj).values().iterator();
        }
        if (obj instanceof NodeList) {
            return new NodeListIterator((NodeList) obj);
        }
        if (obj instanceof Node) {
            return new NodeListIterator((Node) obj);
        }
        if (obj instanceof Dictionary) {
            return new EnumerationIterator(((Dictionary) obj).elements());
        }
        if (obj.getClass().isArray()) {
            return new ArrayIterator(obj);
        }
        try {
            Method method = obj.getClass().getMethod("iterator", null);
            if (Iterator.class.isAssignableFrom(method.getReturnType())) {
                Iterator<?> it = (Iterator) method.invoke(obj, null);
                if (it != null) {
                    return it;
                }
            }
        } catch (ReflectiveOperationException e) {
        } catch (RuntimeException e2) {
        }
        return singletonIterator(obj);
    }

    public static <E> int indexOf(Iterator<E> iterator, Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        if (iterator != null) {
            int index = 0;
            while (iterator.hasNext()) {
                E element = iterator.next();
                if (!predicate.test(element)) {
                    index++;
                } else {
                    return index;
                }
            }
            return -1;
        }
        return -1;
    }

    public static boolean isEmpty(Iterator<?> iterator) {
        return iterator == null || !iterator.hasNext();
    }

    public static <E> ResettableIterator<E> loopingIterator(Collection<? extends E> collection) {
        return new LoopingIterator((Collection) Objects.requireNonNull(collection, "collection"));
    }

    public static <E> ResettableListIterator<E> loopingListIterator(List<E> list) {
        return new LoopingListIterator((List) Objects.requireNonNull(list, XmlErrorCodes.LIST));
    }

    public static <E> boolean matchesAll(Iterator<E> iterator, Predicate<? super E> predicate) {
        Objects.requireNonNull(predicate, "predicate");
        if (iterator == null) {
            return true;
        }
        while (iterator.hasNext()) {
            E element = iterator.next();
            if (!predicate.test(element)) {
                return false;
            }
        }
        return true;
    }

    public static <E> boolean matchesAny(Iterator<E> iterator, Predicate<? super E> predicate) {
        return indexOf(iterator, predicate) != -1;
    }

    public static NodeListIterator nodeListIterator(Node node) {
        return new NodeListIterator((Node) Objects.requireNonNull(node, "node"));
    }

    public static NodeListIterator nodeListIterator(NodeList nodeList) {
        return new NodeListIterator((NodeList) Objects.requireNonNull(nodeList, "nodeList"));
    }

    public static <E> Iterator<E> objectGraphIterator(E root, Transformer<? super E, ? extends E> transformer) {
        return new ObjectGraphIterator(root, transformer);
    }

    public static <E> Iterator<E> peekingIterator(Iterator<? extends E> iterator) {
        return PeekingIterator.peekingIterator(iterator);
    }

    public static <E> Iterator<E> pushbackIterator(Iterator<? extends E> iterator) {
        return PushbackIterator.pushbackIterator(iterator);
    }

    public static <E> ResettableIterator<E> singletonIterator(E object) {
        return new SingletonIterator(object);
    }

    public static <E> ListIterator<E> singletonListIterator(E object) {
        return new SingletonListIterator(object);
    }

    public static int size(Iterator<?> iterator) {
        int size = 0;
        if (iterator != null) {
            while (iterator.hasNext()) {
                iterator.next();
                size++;
            }
        }
        return size;
    }

    public static <E> SkippingIterator<E> skippingIterator(Iterator<E> iterator, long offset) {
        return new SkippingIterator<>(iterator, offset);
    }

    public static <E> Stream<E> stream(Iterable<E> iterable) {
        return iterable == null ? Stream.empty() : StreamSupport.stream(iterable.spliterator(), false);
    }

    public static <E> Stream<E> stream(Iterator<E> iterator) {
        return iterator == null ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false);
    }

    public static Object[] toArray(Iterator<?> iterator) {
        Objects.requireNonNull(iterator, "iterator");
        List<?> list = toList(iterator, 100);
        return list.toArray();
    }

    public static <E> E[] toArray(Iterator<? extends E> it, Class<E> cls) {
        Objects.requireNonNull(it, "iterator");
        Objects.requireNonNull(cls, "arrayClass");
        List list = toList(it, 100);
        return (E[]) list.toArray((Object[]) Array.newInstance((Class<?>) cls, list.size()));
    }

    public static <E> List<E> toList(Iterator<? extends E> iterator) {
        return toList(iterator, 10);
    }

    public static <E> List<E> toList(Iterator<? extends E> iterator, int estimatedSize) {
        if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }
        return (List) addAll(iterator, new ArrayList(estimatedSize));
    }

    public static <E> ListIterator<E> toListIterator(Iterator<? extends E> iterator) {
        Objects.requireNonNull(iterator, "iterator");
        return new ListIteratorWrapper(iterator);
    }

    public static <E> Set<E> toSet(Iterator<? extends E> iterator) {
        return toSet(iterator, 10);
    }

    public static <E> Set<E> toSet(Iterator<? extends E> iterator, int estimatedSize) {
        if (estimatedSize < 1) {
            throw new IllegalArgumentException("Estimated size must be greater than 0");
        }
        return (Set) addAll(iterator, new HashSet(estimatedSize));
    }

    public static <E> String toString(Iterator<E> iterator) {
        return toString(iterator, TransformerUtils.stringValueTransformer(), DEFAULT_TOSTRING_DELIMITER, CollectionUtils.DEFAULT_TOSTRING_PREFIX, CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
    }

    public static <E> String toString(Iterator<E> iterator, Transformer<? super E, String> transformer) {
        return toString(iterator, transformer, DEFAULT_TOSTRING_DELIMITER, CollectionUtils.DEFAULT_TOSTRING_PREFIX, CollectionUtils.DEFAULT_TOSTRING_SUFFIX);
    }

    public static <E> String toString(Iterator<E> iterator, Transformer<? super E, String> transformer, String delimiter, String prefix, String suffix) {
        Objects.requireNonNull(transformer, "transformer");
        Objects.requireNonNull(delimiter, "delimiter");
        Objects.requireNonNull(prefix, "prefix");
        Objects.requireNonNull(suffix, "suffix");
        StringBuilder stringBuilder = new StringBuilder(prefix);
        if (iterator != null) {
            while (iterator.hasNext()) {
                E element = iterator.next();
                stringBuilder.append(transformer.apply(element));
                stringBuilder.append(delimiter);
            }
            if (stringBuilder.length() > prefix.length()) {
                stringBuilder.setLength(stringBuilder.length() - delimiter.length());
            }
        }
        stringBuilder.append(suffix);
        return stringBuilder.toString();
    }

    public static <I, O> Iterator<O> transformedIterator(Iterator<? extends I> iterator, Transformer<? super I, ? extends O> transformer) {
        Objects.requireNonNull(iterator, "iterator");
        Objects.requireNonNull(transformer, "transformer");
        return new TransformIterator(iterator, transformer);
    }

    public static <E> Iterator<E> unmodifiableIterator(Iterator<E> iterator) {
        return UnmodifiableIterator.unmodifiableIterator(iterator);
    }

    public static <E> ListIterator<E> unmodifiableListIterator(ListIterator<E> listIterator) {
        return UnmodifiableListIterator.unmodifiableListIterator(listIterator);
    }

    public static <K, V> MapIterator<K, V> unmodifiableMapIterator(MapIterator<K, V> mapIterator) {
        return UnmodifiableMapIterator.unmodifiableMapIterator(mapIterator);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E>... iterators) {
        return new ZippingIterator<>(iterators);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E> a, Iterator<? extends E> b) {
        return new ZippingIterator<>(a, b);
    }

    public static <E> ZippingIterator<E> zippingIterator(Iterator<? extends E> a, Iterator<? extends E> b, Iterator<? extends E> c) {
        return new ZippingIterator<>(a, b, c);
    }

    private IteratorUtils() {
    }
}
