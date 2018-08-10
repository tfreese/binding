/**
 * Created: 10.08.2018
 */

package de.freese.binding.swing.combobox;

import de.freese.binding.collections.ObservableList;

/**
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public class DefaultObservableListComboBoxModel<T> extends AbstractObservableListComboBoxModel<T>
{
    /**
     *
     */
    private static final long serialVersionUID = -1454539310336157473L;

    /**
     * Erstellt ein neues {@link DefaultObservableListComboBoxModel} Object.
     *
     * @param list {@link ObservableList}
     */
    public DefaultObservableListComboBoxModel(final ObservableList<T> list)
    {
        super(list);
    }
}
