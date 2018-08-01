/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

import de.freese.binding.binds.IntegerBinding;
import de.freese.binding.value.ObservableIntegerValue;
import de.freese.binding.value.ObservableNumberValue;

/**
 * @author Thomas Freese
 */
public interface IntegerExpression extends NumberExpression<Integer>, ObservableIntegerValue
{
    /**
     * @see de.freese.binding.expression.NumberExpression#add(int)
     */
    @Override
    public default IntegerBinding add(final int value)
    {
        return (IntegerBinding) NumberExpression.super.add(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#add(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default IntegerBinding add(final ObservableNumberValue<? extends Number> other)
    {
        return (IntegerBinding) NumberExpression.super.add(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(int)
     */
    @Override
    public default IntegerBinding divide(final int value)
    {
        return (IntegerBinding) NumberExpression.super.divide(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default IntegerBinding divide(final ObservableNumberValue<? extends Number> other)
    {
        return (IntegerBinding) NumberExpression.super.divide(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(int)
     */
    @Override
    public default IntegerBinding multiply(final int value)
    {
        return (IntegerBinding) NumberExpression.super.multiply(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default IntegerBinding multiply(final ObservableNumberValue<? extends Number> other)
    {
        return (IntegerBinding) NumberExpression.super.multiply(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(int)
     */
    @Override
    public default IntegerBinding subtract(final int value)
    {
        return (IntegerBinding) NumberExpression.super.subtract(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default IntegerBinding subtract(final ObservableNumberValue<? extends Number> other)
    {
        return (IntegerBinding) NumberExpression.super.subtract(other);
    }
}
