/**
 * Created: 08.08.2018
 */

package de.freese.binding.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import javax.swing.SwingUtilities;
import javax.swing.event.EventListenerList;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

/**
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
@SuppressWarnings("unchecked")
public abstract class AbstractObservableList<T> implements ObservableList<T>
{
    /**
     *
     */
    private final List<T> backend;

    /**
    *
    */
    private boolean listenerEnabled = true;

    /**
    *
    */
    private final transient EventListenerList listeners = new EventListenerList();

    /**
     * Erstellt ein neues {@link AbstractObservableList} Object.
     */
    public AbstractObservableList()
    {
        this(new ArrayList<>());
    }

    /**
     * Erstellt ein neues {@link AbstractObservableList} Object.
     *
     * @param backend {@link List}
     */
    public AbstractObservableList(final List<T> backend)
    {
        super();

        this.backend = Objects.requireNonNull(backend, "backend required");
    }

    /**
     * @see java.util.List#add(int, java.lang.Object)
     */
    @Override
    public void add(final int index, final T element)
    {
        getBackend().add(index, element);

        int realIndex = indexOf(element);

        if (realIndex != -1)
        {
            fireIntervalAdded(realIndex, realIndex);
        }
    }

    /**
     * @see java.util.List#add(java.lang.Object)
     */
    @Override
    public boolean add(final T e)
    {
        boolean value = getBackend().add(e);

        int index = indexOf(e);

        if (index != -1)
        {
            fireIntervalAdded(index, index);
        }

        return value;
    }

    /**
     * @see java.util.List#addAll(java.util.Collection)
     */
    @Override
    public boolean addAll(final Collection<? extends T> c)
    {
        if ((c == null) || (c.size() == 0))
        {
            return false;
        }

        getBackend().addAll(c);

        if (size() == 0)
        {
            fireIntervalAdded(0, 0);
        }
        else
        {
            fireIntervalAdded(0, size() - 1);
        }

        return c.size() != 0;
    }

    /**
     * @see java.util.List#addAll(int, java.util.Collection)
     */
    @Override
    public boolean addAll(final int index, final Collection<? extends T> c)
    {
        if ((c == null) || (c.size() == 0))
        {
            return false;
        }

        getBackend().addAll(index, c);

        if (size() == 0)
        {
            fireIntervalAdded(0, 0);
        }
        else
        {
            fireIntervalAdded(0, size() - 1);
        }

        return c.size() != 0;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#addAll(java.lang.Object[])
     */
    @Override
    public boolean addAll(final T...elements)
    {
        return addAll(Arrays.asList(elements));
    }

    /**
     * @see de.freese.binding.collections.ObservableList#addListener(javax.swing.event.ListDataListener)
     */
    @Override
    public void addListener(final ListDataListener listener)
    {
        getListeners().add(ListDataListener.class, listener);
    }

    /**
     * @see java.util.List#clear()
     */
    @Override
    public void clear()
    {
        int oldSize = size();

        getBackend().clear();

        if (oldSize > 0)
        {
            fireIntervalRemoved(0, oldSize - 1);
        }
    }

    /**
     * @see java.util.List#contains(java.lang.Object)
     */
    @Override
    public boolean contains(final Object o)
    {
        return getBackend().contains(o);
    }

    /**
     * @see java.util.List#containsAll(java.util.Collection)
     */
    @Override
    public boolean containsAll(final Collection<?> c)
    {
        return getBackend().containsAll(c);
    }

    /**
     * Benachrichtigt die Listener, dass sich die Struktur geaendert hat.<br>
     * Alle Listener werden im EDT benachrichtigt.
     *
     * @param startIndex int
     * @param endIndex int
     */
    protected void fireContentsChanged(final int startIndex, final int endIndex)
    {
        if (!isListenerEnabled())
        {
            return;
        }

        int start = Math.min(startIndex, endIndex);
        int end = Math.max(startIndex, endIndex);

        final ListDataListener[] listeners = getListeners().getListeners(ListDataListener.class);
        final ListDataEvent event = new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, start, end);

        Runnable runnable = () -> {
            for (int i = listeners.length - 1; i >= 0; i--)
            {
                listeners[i].contentsChanged(event);
            }
        };

        if (!SwingUtilities.isEventDispatchThread())
        {
            SwingUtilities.invokeLater(runnable);
        }
        else
        {
            runnable.run();
        }
    }

    /**
     * Benachrichtigt die Listener, dass neue Daten hinzugekommen sind.<br>
     * Alle Listener werden im EDT benachrichtigt.
     *
     * @param startIndex int
     * @param endIndex int
     */
    protected void fireIntervalAdded(final int startIndex, final int endIndex)
    {
        if (!isListenerEnabled())
        {
            return;
        }

        int start = Math.min(startIndex, endIndex);
        int end = Math.max(startIndex, endIndex);

        final ListDataListener[] listeners = getListeners().getListeners(ListDataListener.class);
        final ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, start, end);

        Runnable runnable = () -> {
            for (int i = listeners.length - 1; i >= 0; i--)
            {
                listeners[i].intervalAdded(event);
            }
        };

        if (!SwingUtilities.isEventDispatchThread())
        {
            SwingUtilities.invokeLater(runnable);
        }
        else
        {
            runnable.run();
        }
    }

    /**
     * Benachrichtigt die Listener, dass Daten weggefallen sind.<br>
     * Alle Listener werden im EDT benachrichtigt.
     *
     * @param startIndex int
     * @param endIndex int
     */
    protected void fireIntervalRemoved(final int startIndex, final int endIndex)
    {
        if (!isListenerEnabled())
        {
            return;
        }

        int start = Math.min(startIndex, endIndex);
        int end = Math.max(startIndex, endIndex);

        final ListDataListener[] listeners = getListeners().getListeners(ListDataListener.class);
        final ListDataEvent event = new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, start, end);

        Runnable runnable = () -> {
            for (int i = listeners.length - 1; i >= 0; i--)
            {
                listeners[i].intervalRemoved(event);
            }
        };

        if (!SwingUtilities.isEventDispatchThread())
        {
            SwingUtilities.invokeLater(runnable);
        }
        else
        {
            runnable.run();
        }
    }

    /**
     * @see java.util.List#get(int)
     */
    @Override
    public T get(final int index)
    {
        return getBackend().get(index);
    }

    /**
     * @return {@link List}<T>
     */
    protected List<T> getBackend()
    {
        return this.backend;
    }

    /**
     * @return {@link EventListenerList}
     */
    protected EventListenerList getListeners()
    {
        return this.listeners;
    }

    /**
     * @see java.util.List#indexOf(java.lang.Object)
     */
    @Override
    public int indexOf(final Object o)
    {
        return getBackend().indexOf(o);
    }

    /**
     * @see java.util.List#isEmpty()
     */
    @Override
    public boolean isEmpty()
    {
        return getBackend().isEmpty();
    }

    /**
     * @see de.freese.binding.collections.ObservableList#isListenerEnabled()
     */
    @Override
    public boolean isListenerEnabled()
    {
        return this.listenerEnabled;
    }

    /**
     * @see java.util.List#iterator()
     */
    @Override
    public Iterator<T> iterator()
    {
        return getBackend().iterator();
    }

    /**
     * @see java.util.List#lastIndexOf(java.lang.Object)
     */
    @Override
    public int lastIndexOf(final Object o)
    {
        return getBackend().lastIndexOf(o);
    }

    /**
     * @see java.util.List#listIterator()
     */
    @Override
    public ListIterator<T> listIterator()
    {
        return getBackend().listIterator();
    }

    /**
     * @see java.util.List#listIterator(int)
     */
    @Override
    public ListIterator<T> listIterator(final int index)
    {
        return getBackend().listIterator(index);
    }

    /**
     * @see java.util.List#remove(int)
     */
    @Override
    public T remove(final int index)
    {
        if ((index == -1) || (index >= size()))
        {
            return null;
        }

        T object = getBackend().remove(index);

        fireIntervalRemoved(index, index);

        return object;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#remove(int, int)
     */
    @Override
    public void remove(final int from, final int to)
    {
        for (int i = from; i < to; i++)
        {
            getBackend().remove(i);
        }

        fireIntervalRemoved(from, to);
    }

    /**
     * @see java.util.List#remove(java.lang.Object)
     */
    @Override
    public boolean remove(final Object o)
    {
        if (o == null)
        {
            return false;
        }

        int index = indexOf(o);

        if (index == -1)
        {
            return false;
        }

        boolean value = getBackend().remove(o);

        fireIntervalRemoved(index, index);

        return value;
    }

    /**
     * @see java.util.List#removeAll(java.util.Collection)
     */
    @Override
    public boolean removeAll(final Collection<?> c)
    {
        int oldSize = size();

        boolean value = getBackend().removeAll(c);

        if (oldSize > 0)
        {
            fireContentsChanged(0, oldSize - 1);
        }

        return value;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#removeAll(java.lang.Object[])
     */
    @Override
    public boolean removeAll(final T...elements)
    {
        return removeAll(Arrays.asList(elements));
    }

    /**
     * @see de.freese.binding.collections.ObservableList#removeListener(javax.swing.event.ListDataListener)
     */
    @Override
    public void removeListener(final ListDataListener listener)
    {
        getListeners().remove(ListDataListener.class, listener);
    }

    /**
     * @see java.util.List#retainAll(java.util.Collection)
     */
    @Override
    public boolean retainAll(final Collection<?> c)
    {
        int oldSize = size();

        boolean value = getBackend().retainAll(c);

        if (oldSize > 0)
        {
            fireContentsChanged(0, oldSize - 1);
        }

        return value;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#retainAll(java.lang.Object[])
     */
    @Override
    public boolean retainAll(final T...elements)
    {
        return retainAll(Arrays.asList(elements));
    }

    /**
     * @see java.util.List#set(int, java.lang.Object)
     */
    @Override
    public T set(final int index, final T element)
    {
        T oldObject = getBackend().set(index, element);

        fireContentsChanged(index, index);

        return oldObject;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#setAll(java.util.Collection)
     */
    @Override
    public boolean setAll(final Collection<? extends T> col)
    {
        int oldSize = size();

        getBackend().clear();
        boolean value = getBackend().addAll(col);

        if (oldSize > 0)
        {
            fireContentsChanged(0, oldSize - 1);
        }

        return value;
    }

    /**
     * @see de.freese.binding.collections.ObservableList#setAll(java.lang.Object[])
     */
    @Override
    public boolean setAll(final T...elements)
    {
        return setAll(Arrays.asList(elements));
    }

    /**
     * @see de.freese.binding.collections.ObservableList#setListenerEnabled(boolean)
     */
    @Override
    public void setListenerEnabled(final boolean listenerEnabled)
    {
        this.listenerEnabled = listenerEnabled;
    }

    /**
     * @see java.util.List#size()
     */
    @Override
    public int size()
    {
        return getBackend().size();
    }

    /**
     * @see java.util.List#subList(int, int)
     */
    @Override
    public List<T> subList(final int fromIndex, final int toIndex)
    {
        return getBackend().subList(fromIndex, toIndex);
    }

    /**
     * @see java.util.List#toArray()
     */
    @Override
    public Object[] toArray()
    {
        return getBackend().toArray();
    }

    /**
     * @see java.util.List#toArray(java.lang.Object[])
     */
    @SuppressWarnings("hiding")
    @Override
    public <T> T[] toArray(final T[] a)
    {
        return getBackend().toArray(a);
    }
}
