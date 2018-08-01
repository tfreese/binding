/**
 * Created: 31.07.2018
 */

package de.freese.binding.binds;

import de.freese.binding.property.Property;
import de.freese.binding.value.ChangeListener;
import de.freese.binding.value.ObservableValue;

/**
 * Analog: javafx.beans.binding.Binding
 *
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public interface Binding<T> extends ObservableValue<T>
{
    // /**
    // * Registrieren des Bindings an den Dependencies.
    // *
    // * @param dependencies {@link ObservableValue}
    // */
    // @SuppressWarnings("unchecked")
    // public void bind(ObservableValue<? extends Object>...dependencies);

    // /**
    // * De-Register von aktuellen Listenern.
    // */
    // public void unbind();

    /**
     * Aktualisiert das Value durch die Werte der registrierten {@link Property}<br>
     * und feuert die {@link ChangeListener}, falls die Werte unterschiedlich sind.
     */
    public void update();
}
