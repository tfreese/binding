/**
 * Created: 31.07.2018
 */

package de.freese.binding.expression;

import de.freese.binding.Bindings;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.value.ObservableBooleanValue;

/**
 * @author Thomas Freese
 */
public interface BooleanExpression extends ObservableBooleanValue
{
    /**
     * @param observable {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public default BooleanBinding and(final ObservableBooleanValue observable)
    {
        return Bindings.and(this, observable);
    }

    /**
     * @return {@link BooleanBinding}
     */
    public default BooleanBinding not()
    {
        return Bindings.not(this);
    }

    /**
     * @param observable {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public default BooleanBinding or(final ObservableBooleanValue observable)
    {
        return Bindings.or(this, observable);
    }
}
