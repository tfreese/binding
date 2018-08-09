/**
 * Created: 09.08.2018
 */

package de.freese.binding.collections;

import java.util.List;
import java.util.Objects;

/**
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public class DefaultObservableList<T> extends AbstractObservableList<T>
{
    /**
     *
     */
    private final List<T> source;

    /**
     * Erstellt ein neues {@link DefaultObservableList} Object.
     *
     * @param source {@link List}
     */
    public DefaultObservableList(final List<T> source)
    {
        super();

        this.source = Objects.requireNonNull(source, "source required");
    }

    /**
     * @see de.freese.binding.collections.AbstractObservableList#doAdd(int, java.lang.Object)
     */
    @Override
    protected void doAdd(final int index, final T element)
    {
        getSource().add(index, element);
    }

    /**
     * @see de.freese.binding.collections.AbstractObservableList#doRemove(int)
     */
    @Override
    protected T doRemove(final int index)
    {
        return getSource().remove(index);
    }

    /**
     * @see de.freese.binding.collections.AbstractObservableList#doSet(int, java.lang.Object)
     */
    @Override
    protected T doSet(final int index, final T element)
    {
        return getSource().set(index, element);
    }

    /**
     * @see de.freese.binding.collections.AbstractObservableList#get(int)
     */
    @Override
    public T get(final int index)
    {
        return getSource().get(index);
    }

    /**
     * @return {@link List}
     */
    public final List<T> getSource()
    {
        return this.source;
    }

    /**
     * @see de.freese.binding.collections.AbstractObservableList#size()
     */
    @Override
    public int size()
    {
        return getSource().size();
    }
}