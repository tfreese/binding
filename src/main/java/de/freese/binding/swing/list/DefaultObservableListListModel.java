/**
 * Created: 10.08.2018
 */

package de.freese.binding.swing.list;

import javax.swing.ListModel;
import de.freese.binding.collections.ObservableList;

/**
 * Default-Implementierung eines {@link ListModel} für die {@link ObservableList}.
 *
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public class DefaultObservableListListModel<T> extends AbstractObservableListListModel<T>
{
    /**
     *
     */
    private static final long serialVersionUID = -578288830889934793L;

    /**
     * Erstellt ein neues {@link DefaultObservableListListModel} Object.
     *
     * @param list {@link ObservableList}
     */
    public DefaultObservableListListModel(final ObservableList<T> list)
    {
        super(list);
    }
}
