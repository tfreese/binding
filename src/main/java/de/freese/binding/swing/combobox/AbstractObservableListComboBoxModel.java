package de.freese.binding.swing.combobox;

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import de.freese.binding.collections.ObservableList;
import de.freese.binding.swing.list.AbstractObservableListListModel;

/**
 * Basis ComboBoxModel, das intern eine {@link ObservableList} verwendet.
 *
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public abstract class AbstractObservableListComboBoxModel<T> extends AbstractObservableListListModel<T> implements ComboBoxModel<T>
{
    /**
     * Erweiterung des ComboboxEventListeners.
     *
     * @author Thomas Freese
     */
    private class ComboBoxEventListListener extends EventListListener
    {
        /**
         * Erstellt ein neues {@link ComboBoxEventListListener} Objekt.
         */
        public ComboBoxEventListListener()
        {
            super();
        }

        /**
         * Überschrieben, um sicherzustellen, das das selektierte Objekt in der ComboBox angepasst wird, wenn sich die Daten der {ObservableList} anpassen.
         *
         * @see de.freese.binding.swing.list.AbstractObservableListListModel.EventListListener#contentsChanged(javax.swing.event.ListDataEvent)
         */
        @Override
        public void contentsChanged(final ListDataEvent e)
        {
            AbstractObservableListComboBoxModel.this.selectedObject = null;
            super.contentsChanged(e);
        }
    }

    /**
     *
     */
    private static final long serialVersionUID = -5837879226873538114L;

    /**
     * Das momentan selektierte Objekt.
     */
    private Object selectedObject;

    /**
     * Erstellt ein neues {@link AbstractObservableListComboBoxModel} Objekt.
     *
     * @param list {@link ObservableList}
     */
    protected AbstractObservableListComboBoxModel(final ObservableList<T> list)
    {
        super(list);
    }

    /**
     * @see de.freese.binding.swing.list.AbstractObservableListListModel#createEventListener()
     */
    @Override
    protected EventListListener createEventListener()
    {
        return new ComboBoxEventListListener();
    }

    /**
     * Überschrieben, da beim Entfernen von Objekten auch das selektierte Objekt der ComboBox angepasst werden muss.
     *
     * @see de.freese.binding.swing.list.AbstractObservableListListModel#fireIntervalRemoved(java.lang.Object, int, int)
     */
    @Override
    protected void fireIntervalRemoved(final Object source, final int index0, final int index1)
    {
        if (this.selectedObject != null)
        {
            setSelectedItem(null);
        }

        super.fireIntervalRemoved(source, index0, index1);
    }

    /**
     * @see javax.swing.ComboBoxModel#getSelectedItem()
     */
    @Override
    public Object getSelectedItem()
    {
        return this.selectedObject;
    }

    /**
     * @see javax.swing.ComboBoxModel#setSelectedItem(java.lang.Object)
     */
    @Override
    public void setSelectedItem(final Object anItem)
    {
        int index = getList().indexOf(anItem);

        if (index != -1)
        {
            this.selectedObject = getList().get(index);
        }
        else
        {
            this.selectedObject = null;
        }

        fireContentsChanged(this, index, index);
    }
}
