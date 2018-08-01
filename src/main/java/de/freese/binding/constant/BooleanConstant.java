/**
 * Created: 31.07.2018
 */

package de.freese.binding.constant;

import de.freese.binding.expression.AbstractBooleanExpression;
import de.freese.binding.value.ChangeListener;

/**
 * @author Thomas Freese
 */
public class BooleanConstant extends AbstractBooleanExpression
{
    /**
     * @param value Boolean
     * @return {@link BooleanConstant}
     */
    public static BooleanConstant valueOf(final Boolean value)
    {
        return new BooleanConstant(value);
    }

    /**
     *
     */
    private final Boolean value;

    /**
     * Erstellt ein neues {@link BooleanConstant} Object.
     *
     * @param value {@link Boolean}
     */
    private BooleanConstant(final Boolean value)
    {
        super();

        this.value = value;
    }

    /**
     * @see de.freese.binding.expression.AbstractExpression#addListener(de.freese.binding.value.ChangeListener)
     */
    @Override
    public void addListener(final ChangeListener<? super Boolean> listener)
    {
        // NO-OP
    }

    /**
     * @see de.freese.binding.expression.AbstractExpression#fireValueChangedEvent(java.lang.Object, java.lang.Object)
     */
    @Override
    protected void fireValueChangedEvent(final Boolean oldValue, final Boolean newValue)
    {
        // NO-OP
    }

    /**
     * @see de.freese.binding.value.ObservableValue#getValue()
     */
    @Override
    public Boolean getValue()
    {
        return this.value;
    }

    /**
     * @see de.freese.binding.expression.AbstractExpression#removeListener(de.freese.binding.value.ChangeListener)
     */
    @Override
    public void removeListener(final ChangeListener<? super Boolean> listener)
    {
        // NO-OP
    }
}
