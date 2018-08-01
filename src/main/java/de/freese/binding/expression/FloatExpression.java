/**
 * Created: 01.08.2018
 */

package de.freese.binding.expression;

import de.freese.binding.binds.FloatBinding;
import de.freese.binding.value.ObservableFloatValue;
import de.freese.binding.value.ObservableNumberValue;

/**
 * @author Thomas Freese
 */
public interface FloatExpression extends NumberExpression<Float>, ObservableFloatValue
{
    /**
     * @see de.freese.binding.expression.NumberExpression#add(float)
     */
    @Override
    public default FloatBinding add(final float value)
    {
        return (FloatBinding) NumberExpression.super.add(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#add(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default FloatBinding add(final ObservableNumberValue<? extends Number> other)
    {
        return (FloatBinding) NumberExpression.super.add(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(float)
     */
    @Override
    public default FloatBinding divide(final float value)
    {
        return (FloatBinding) NumberExpression.super.divide(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#divide(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default FloatBinding divide(final ObservableNumberValue<? extends Number> other)
    {
        return (FloatBinding) NumberExpression.super.divide(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(float)
     */
    @Override
    public default FloatBinding multiply(final float value)
    {
        return (FloatBinding) NumberExpression.super.multiply(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#multiply(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default FloatBinding multiply(final ObservableNumberValue<? extends Number> other)
    {
        return (FloatBinding) NumberExpression.super.multiply(other);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(float)
     */
    @Override
    public default FloatBinding subtract(final float value)
    {
        return (FloatBinding) NumberExpression.super.subtract(value);
    }

    /**
     * @see de.freese.binding.expression.NumberExpression#subtract(de.freese.binding.value.ObservableNumberValue)
     */
    @Override
    public default FloatBinding subtract(final ObservableNumberValue<? extends Number> other)
    {
        return (FloatBinding) NumberExpression.super.subtract(other);
    }
}
