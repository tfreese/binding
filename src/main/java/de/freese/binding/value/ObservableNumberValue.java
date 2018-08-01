/**
 * Created: 01.08.2018
 */

package de.freese.binding.value;

/**
 * @author Thomas Freese
 * @param <T> Konkreter Typ
 */
public interface ObservableNumberValue<T extends Number> extends ObservableValue<T>
{
    /**
     * Liefert den double-Wert.
     *
     * @return double
     */
    public default double doubleValue()
    {
        return getValue() == null ? 0 : getValue().doubleValue();
    }

    /**
     * Liefert den float-Wert.
     *
     * @return float
     */
    public default float floatValue()
    {
        return getValue() == null ? 0 : getValue().floatValue();
    }

    /**
     * Liefert den int-Wert.
     *
     * @return int
     */
    public default int intValue()
    {
        return getValue() == null ? 0 : getValue().intValue();
    }

    /**
     * Liefert den long-Wert.
     *
     * @return long
     */
    public default long longValue()
    {
        return getValue() == null ? 0 : getValue().longValue();
    }
}
