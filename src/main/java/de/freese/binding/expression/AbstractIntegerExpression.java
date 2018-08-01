/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

/**
 * @author Thomas Freese
 */
public abstract class AbstractIntegerExpression extends AbstractNumberExpression<Integer> implements IntegerExpression
{
    /**
     * Erstellt ein neues {@link AbstractIntegerExpression} Object.
     */
    public AbstractIntegerExpression()
    {
        super();
    }

    /**
     * @see de.freese.binding.value.ObservableIntegerValue#get()
     */
    @Override
    public int get()
    {
        return intValue();
    }
}
