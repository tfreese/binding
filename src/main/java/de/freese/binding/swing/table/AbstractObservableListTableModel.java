package de.freese.binding.swing.table;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import de.freese.binding.collections.ObservableList;

/**
 * TableModel das intern eine {@link ObservableList} verwendet.
 *
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public abstract class AbstractObservableListTableModel<T> extends AbstractListTableModel<T>
{
    /**
     * Listener auf der {@link ObservableList}.
     *
     * @author Thomas Freese
     */
    private class EventListListener implements ListDataListener
    {
        /**
         * Erstellt ein neues {@link EventListListener} Object.
         */
        public EventListListener()
        {
            super();
        }

        /**
         * @see javax.swing.event.ListDataListener#contentsChanged(javax.swing.event.ListDataEvent)
         */
        @Override
        public void contentsChanged(final ListDataEvent e)
        {
            // int firstRow = e.getIndex0();
            // int lastRow = e.getIndex1();
            //
            // fireTableRowsUpdated(firstRow, lastRow);
            // fireTableRowsUpdated(0, Integer.MAX_VALUE);

            fireTableDataChanged();
        }

        /**
         * @see javax.swing.event.ListDataListener#intervalAdded(javax.swing.event.ListDataEvent)
         */
        @Override
        public void intervalAdded(final ListDataEvent e)
        {
            int firstRow = e.getIndex0();
            int lastRow = e.getIndex1();

            fireTableRowsInserted(firstRow, lastRow);
        }

        /**
         * @see javax.swing.event.ListDataListener#intervalRemoved(javax.swing.event.ListDataEvent)
         */
        @Override
        public void intervalRemoved(final ListDataEvent e)
        {
            int firstRow = e.getIndex0();
            int lastRow = e.getIndex1();

            fireTableRowsDeleted(firstRow, lastRow);
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = -863675813360039937L;

    /**
     * Erstellt ein neues {@link AbstractObservableListTableModel} Objekt.
     *
     * @param columnCount int
     * @param list {@link ObservableList}
     */
    public AbstractObservableListTableModel(final int columnCount, final ObservableList<T> list)
    {
        super(columnCount, list);

        list.addListener(new EventListListener());
    }

    /**
     * @see de.freese.binding.swing.table.AbstractListTableModel#getList()
     */
    @Override
    protected ObservableList<T> getList()
    {
        return (ObservableList<T>) super.getList();
    }
}