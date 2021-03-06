/**
 * Created: 08.08.2018
 */

package de.freese.binding.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import javax.swing.event.ListDataListener;

/**
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
@SuppressWarnings("unchecked")
public interface ObservableList<T> extends List<T>
{
    /**
     * Hinzufügen der Elemente.
     *
     * @param elements Object[]
     * @return boolean
     * @see List#add(Object)
     */
    public default boolean addAll(final T...elements)
    {
        return addAll(Arrays.asList(elements));
    }

    /**
     * Hinzufügen eines {@link ListDataListener}s.
     *
     * @param listener {@link ListDataListener}
     */
    public void addListener(final ListDataListener listener);

    /**
     * @param predicate {@link Predicate}
     * @return {@link FilteredObservableList}
     */
    public default FilteredObservableList<T> filtered(final Predicate<T> predicate)
    {
        return new FilteredObservableList<>(this, predicate);
    }

    /**
     * Liefert true, wenn die Events gefeuert werden.
     *
     * @return boolean
     */
    public boolean isListenerEnabled();

    /**
     * @param from int, inclusive
     * @param to int, exclusive
     */
    public void remove(int from, int to);

    /**
     * @param elements Object[]
     * @return boolean
     * @see List#remove(Object)
     */
    public default boolean removeAll(final T...elements)
    {
        return removeAll(Arrays.asList(elements));
    }

    /**
     * Entfernen eines {@link ListDataListener}s.
     *
     * @param listener {@link ListDataListener}
     */
    public void removeListener(final ListDataListener listener);

    /**
     * @param elements Object[]
     * @return boolean
     * @see List#retainAll(Collection)
     */
    public default boolean retainAll(final T...elements)
    {
        return retainAll(Arrays.asList(elements));
    }

    /**
     * Leeren der Liste und hinzufügen der Elemente.
     *
     * @param col {@link Collection}
     * @return boolean
     * @see List#set(int, Object)
     */
    public default boolean setAll(final Collection<? extends T> col)
    {
        clear();
        boolean value = addAll(col);

        return value;
    }

    /**
     * Leeren der Liste und hinzufügen der Elemente.
     *
     * @param elements Object[]
     * @return boolean
     * @see List#set(int, Object)
     */
    public default boolean setAll(final T...elements)
    {
        return setAll(Arrays.asList(elements));
    }

    /**
     * True, wenn die Events gefeuert werden sollen.
     *
     * @param listenerEnabled boolean
     */
    public void setListenerEnabled(final boolean listenerEnabled);

    /**
     * @param comparator {@link Comparator}
     * @return {@link SortedObservableList}
     */
    public default SortedObservableList<T> sorted(final Comparator<T> comparator)
    {
        return new SortedObservableList<>(this, comparator);
    }
}
