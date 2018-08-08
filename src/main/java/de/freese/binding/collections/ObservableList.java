/**
 * Created: 08.08.2018
 */

package de.freese.binding.collections;

import java.util.Collection;
import java.util.List;
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
    public boolean addAll(T...elements);

    /**
     * Hinzufügen eines {@link ListDataListener}s.
     *
     * @param listener {@link ListDataListener}
     */
    public void addListener(final ListDataListener listener);

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
    public boolean removeAll(T...elements);

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
    public boolean retainAll(T...elements);

    /**
     * Leeren der Liste und hinzufügen der Elemente.
     *
     * @param col {@link Collection}
     * @return boolean
     * @see List#set(int, Object)
     */
    public boolean setAll(Collection<? extends T> col);

    /**
     * Leeren der Liste und hinzufügen der Elemente.
     *
     * @param elements Object[]
     * @return boolean
     * @see List#set(int, Object)
     */
    public boolean setAll(T...elements);

    // public default FilteredList<E> filtered(Predicate<E> predicate) {
    // return new FilteredList<>(this, predicate);
    // }
    //
    // public default SortedList<E> sorted(Comparator<E> comparator) {
    // return new SortedList<>(this, comparator);
    // }

    /**
     * True, wenn die Events gefeuert werden sollen.
     *
     * @param listenerEnabled boolean
     */
    public void setListenerEnabled(final boolean listenerEnabled);
}
