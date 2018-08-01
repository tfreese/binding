/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

/**
 * @author Thomas Freese
 */
public abstract class AbstractFloatExpression extends AbstractNumberExpression<Float> implements FloatExpression
{
    /**
     * Erstellt ein neues {@link AbstractFloatExpression} Object.
     */
    public AbstractFloatExpression()
    {
        super();
    }

    /**
     * @see de.freese.binding.value.ObservableIntegerValue#get()
     */
    @Override
    public float get()
    {
        return floatValue();
    }
}
