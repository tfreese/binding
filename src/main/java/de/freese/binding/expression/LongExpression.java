/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

import de.freese.binding.binds.LongBinding;
import de.freese.binding.value.ObservableLongValue;
import de.freese.binding.value.ObservableNumberValue;

/**
 * @author Thomas Freese
 */
public interface LongExpression extends NumberExpression<Long>, ObservableLongValue
{
    /**
     * @see de.freese.binding.expression.NumberExpression#add(long)
     */
    @Override
    public default LongBinding add(final long value)
    {
        return (LongBinding) NumberExpression.super.add(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#add(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default LongBinding add(final ObservableNumberValue<? extends Number> other)
    {
        return (LongBinding) NumberExpression.super.add(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(long)
     */
    @Override
    public default LongBinding divide(final long value)
    {
        return (LongBinding) NumberExpression.super.divide(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default LongBinding divide(final ObservableNumberValue<? extends Number> other)
    {
        return (LongBinding) NumberExpression.super.divide(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(long)
     */
    @Override
    public default LongBinding multiply(final long value)
    {
        return (LongBinding) NumberExpression.super.multiply(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default LongBinding multiply(final ObservableNumberValue<? extends Number> other)
    {
        return (LongBinding) NumberExpression.super.multiply(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(long)
     */
    @Override
    public default LongBinding subtract(final long value)
    {
        return (LongBinding) NumberExpression.super.subtract(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default LongBinding subtract(final ObservableNumberValue<? extends Number> other)
    {
        return (LongBinding) NumberExpression.super.subtract(other);
    }
}
