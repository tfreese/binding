/**
 * Created: 31.07.2018
 */

package de.freese.binding.expression;

/**
 * @author Thomas Freese
 */
public abstract class AbstractBooleanExpression extends AbstractExpression<Boolean> implements BooleanExpression
{
    /**
     * Erstellt ein neues {@link AbstractBooleanExpression} Object.
     */
    public AbstractBooleanExpression()
    {
        super();
    }

    /**
     * @see de.freese.binding.value.ObservableBooleanValue#get()
     */
    @Override
    public boolean get()
    {
        return getValue() == null ? false : getValue();
    }
}
