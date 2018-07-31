/**
 * Created: 31.07.2018
 */

package de.freese.binding.value;

/**
 * Analog: javafx.beans.value.WritableValue
 *
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public interface WritableValue<T>
{
    /**
     * Liefert den aktuellen Wert.
     *
     * @return Object
     */
    public T getValue();

    /**
     * Setzt den aktuellen Wert.
     *
     * @param value Object
     */
    public void setValue(T value);
}
