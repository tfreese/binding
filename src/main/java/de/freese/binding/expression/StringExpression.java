/**
 * Created: 31.07.2018
 */

package de.freese.binding.expression;

import de.freese.binding.Bindings;
import de.freese.binding.binds.StringBinding;
import de.freese.binding.constant.StringConstant;
import de.freese.binding.value.ObservableStringValue;

/**
 * @author Thomas Freese
 */
public interface StringExpression extends ObservableStringValue
{
    /**
     * @param observable {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public default StringBinding concat(final ObservableStringValue observable)
    {
        return Bindings.concat(this, observable);
    }

    /**
     * @param value String
     * @return {@link StringBinding}
     */
    public default StringBinding concat(final String value)
    {
        return concat(StringConstant.valueOf(value));
    }

    /**
     * Liefert einen leeren String "", wenn null.
     *
     * @return String
     */
    public default String getValueSafe()
    {
        final String value = getValue();

        return value == null ? "" : value;
    }
}
