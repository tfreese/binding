/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

/**
 * @author Thomas Freese
 */
public abstract class AbstractDoubleExpression extends AbstractNumberExpression<Double> implements DoubleExpression
{
    /**
     * Erstellt ein neues {@link AbstractDoubleExpression} Object.
     */
    public AbstractDoubleExpression()
    {
        super();
    }

    /**
     * @see de.freese.binding.value.ObservableIntegerValue#get()
     */
    @Override
    public double get()
    {
        return doubleValue();
    }
}
