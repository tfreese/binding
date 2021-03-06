/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import java.util.Objects;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import de.freese.binding.binds.AbstractBooleanBinding;
import de.freese.binding.binds.AbstractDoubleBinding;
import de.freese.binding.binds.AbstractFloatBinding;
import de.freese.binding.binds.AbstractIntegerBinding;
import de.freese.binding.binds.AbstractLongBinding;
import de.freese.binding.binds.AbstractStringBinding;
import de.freese.binding.binds.Binding;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.binds.DoubleBinding;
import de.freese.binding.binds.FloatBinding;
import de.freese.binding.binds.IntegerBinding;
import de.freese.binding.binds.LongBinding;
import de.freese.binding.binds.NumberBinding;
import de.freese.binding.binds.StringBinding;
import de.freese.binding.value.ObservableBooleanValue;
import de.freese.binding.value.ObservableDoubleValue;
import de.freese.binding.value.ObservableFloatValue;
import de.freese.binding.value.ObservableLongValue;
import de.freese.binding.value.ObservableNumberValue;
import de.freese.binding.value.ObservableStringValue;
import de.freese.binding.value.ObservableValue;

/**
 * Util-Klasse für die {@link Binding} Implementierungen.
 *
 * @author Thomas Freese
 */
public final class Bindings
{
    /**
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link NumberBinding}
     */
    public static NumberBinding<? extends Number> add(final ObservableNumberValue<? extends Number> ov1, final ObservableNumberValue<? extends Number> ov2)
    {
        final NumberBinding<? extends Number> binding;

        if ((ov1 instanceof ObservableDoubleValue) || (ov2 instanceof ObservableDoubleValue))
        {
            binding = createDoubleBinding((v1, v2) -> v1 + v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = createFloatBinding((v1, v2) -> v1 + v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = createLongBinding((v1, v2) -> v1 + v2, ov1, ov2);
        }
        else
        {
            binding = createIntegerBinding((v1, v2) -> v1 + v2, ov1, ov2);
        }

        return binding;
    }

    /**
     * @param ov1 {@link ObservableBooleanValue}
     * @param ov2 {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding and(final ObservableBooleanValue ov1, final ObservableBooleanValue ov2)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return ov1.get() && ov2.get();
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov1 {@link ObservableStringValue}
     * @param ov2 {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public static StringBinding concat(final ObservableStringValue ov1, final ObservableStringValue ov2)
    {
        return createStringBinding((v1, v2) -> {
            if ((v1 == null) && (v2 == null))
            {
                return null;
            }

            return v1 + v2;
        }, ov1, ov2);
    }

    /**
     * @param function {@link Predicate}
     * @param ov {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public static BooleanBinding createBooleanBinding(final Predicate<Object> function, final ObservableValue<?> ov)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return function.test(ov.getValue());
            }
        };

        ov.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param function {@link BinaryOperator}
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link DoubleBinding}
     */
    public static DoubleBinding createDoubleBinding(final BinaryOperator<Double> function, final ObservableNumberValue<? extends Number> ov1,
                                                    final ObservableNumberValue<? extends Number> ov2)
    {
        DoubleBinding binding = new AbstractDoubleBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractDoubleBinding#computeValue()
             */
            @Override
            protected double computeValue()
            {
                return function.apply(ov1.doubleValue(), ov2.doubleValue());
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param function {@link BinaryOperator}
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link FloatBinding}
     */
    public static FloatBinding createFloatBinding(final BinaryOperator<Float> function, final ObservableNumberValue<? extends Number> ov1,
                                                  final ObservableNumberValue<? extends Number> ov2)
    {
        FloatBinding binding = new AbstractFloatBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractFloatBinding#computeValue()
             */
            @Override
            protected float computeValue()
            {
                return function.apply(ov1.floatValue(), ov2.floatValue());
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param function {@link BinaryOperator}
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link IntegerBinding}
     */
    public static IntegerBinding createIntegerBinding(final BinaryOperator<Integer> function, final ObservableNumberValue<? extends Number> ov1,
                                                      final ObservableNumberValue<? extends Number> ov2)
    {
        IntegerBinding binding = new AbstractIntegerBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
             */
            @Override
            protected int computeValue()
            {
                return function.apply(ov1.intValue(), ov2.intValue());
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param function {@link BinaryOperator}
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link LongBinding}
     */
    public static LongBinding createLongBinding(final BinaryOperator<Long> function, final ObservableNumberValue<? extends Number> ov1,
                                                final ObservableNumberValue<? extends Number> ov2)
    {
        LongBinding binding = new AbstractLongBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractLongBinding#computeValue()
             */
            @Override
            protected long computeValue()
            {
                return function.apply(ov1.longValue(), ov2.longValue());
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param function {@link BinaryOperator}
     * @param ov1 {@link ObservableStringValue}
     * @param ov2 {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public static StringBinding createStringBinding(final BinaryOperator<String> function, final ObservableStringValue ov1, final ObservableStringValue ov2)
    {
        StringBinding binding = new AbstractStringBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractStringBinding#computeValue()
             */
            @Override
            protected String computeValue()
            {
                return function.apply(ov1.getValue(), ov2.getValue());
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link NumberBinding}
     */
    public static NumberBinding<? extends Number> divide(final ObservableNumberValue<? extends Number> ov1, final ObservableNumberValue<? extends Number> ov2)
    {
        final NumberBinding<? extends Number> binding;

        if ((ov1 instanceof ObservableDoubleValue) || (ov2 instanceof ObservableDoubleValue))
        {
            binding = createDoubleBinding((v1, v2) -> v1 / v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = createFloatBinding((v1, v2) -> v1 / v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = createLongBinding((v1, v2) -> v1 / v2, ov1, ov2);
        }
        else
        {
            binding = createIntegerBinding((v1, v2) -> v1 / v2, ov1, ov2);
        }

        return binding;
    }

    /**
     * Liefert einen leeren String "", wenn null.
     *
     * @param value String
     * @return String
     */
    private static String getValueSafe(final String value)
    {
        return value == null ? "" : value;
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding isBlank(final ObservableStringValue ov)
    {
        return createBooleanBinding(v -> getValueSafe((String) v).trim().isEmpty(), ov);
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding isEmpty(final ObservableStringValue ov)
    {
        return createBooleanBinding(v -> getValueSafe((String) v).isEmpty(), ov);
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding isNotBlank(final ObservableStringValue ov)
    {
        return createBooleanBinding(v -> !getValueSafe((String) v).trim().isEmpty(), ov);
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding isNotEmpty(final ObservableStringValue ov)
    {
        return createBooleanBinding(v -> !getValueSafe((String) v).isEmpty(), ov);
    }

    /**
     * @param ov {@link ObservableValue}
     * @param <T> Konkreter Typ
     * @return {@link BooleanBinding}
     */
    public static <T> BooleanBinding isNotNull(final ObservableValue<T> ov)
    {
        return createBooleanBinding(Objects::nonNull, ov);
    }

    /**
     * @param ov {@link ObservableValue}
     * @param <T> Konkreter Typ
     * @return {@link BooleanBinding}
     */
    public static <T> BooleanBinding isNull(final ObservableValue<T> ov)
    {
        return createBooleanBinding(Objects::isNull, ov);
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link de.freese.binding.binds.IntegerBinding}
     */
    public static IntegerBinding length(final ObservableStringValue ov)
    {
        IntegerBinding binding = new AbstractIntegerBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
             */
            @Override
            protected int computeValue()
            {
                return getValueSafe(ov.getValue()).length();
            }
        };

        ov.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link NumberBinding}
     */
    public static NumberBinding<? extends Number> multiply(final ObservableNumberValue<? extends Number> ov1, final ObservableNumberValue<? extends Number> ov2)
    {
        final NumberBinding<? extends Number> binding;

        if ((ov1 instanceof ObservableDoubleValue) || (ov2 instanceof ObservableDoubleValue))
        {
            binding = createDoubleBinding((v1, v2) -> v1 * v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = createFloatBinding((v1, v2) -> v1 * v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = createLongBinding((v1, v2) -> v1 * v2, ov1, ov2);
        }
        else
        {
            binding = createIntegerBinding((v1, v2) -> v1 * v2, ov1, ov2);
        }

        return binding;
    }

    /**
     * @param ov {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding not(final ObservableBooleanValue ov)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return !ov.get();
            }
        };

        ov.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov1 {@link ObservableBooleanValue}
     * @param ov2 {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding or(final ObservableBooleanValue ov1, final ObservableBooleanValue ov2)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return ov1.get() || ov2.get();
            }
        };

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov1 {@link ObservableNumberValue}
     * @param ov2 {@link ObservableNumberValue}
     * @return {@link NumberBinding}
     */
    public static NumberBinding<? extends Number> subtract(final ObservableNumberValue<? extends Number> ov1, final ObservableNumberValue<? extends Number> ov2)
    {
        final NumberBinding<? extends Number> binding;

        if ((ov1 instanceof ObservableDoubleValue) || (ov2 instanceof ObservableDoubleValue))
        {
            binding = createDoubleBinding((v1, v2) -> v1 - v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = createFloatBinding((v1, v2) -> v1 - v2, ov1, ov2);
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = createLongBinding((v1, v2) -> v1 - v2, ov1, ov2);
        }
        else
        {
            binding = createIntegerBinding((v1, v2) -> v1 - v2, ov1, ov2);
        }

        return binding;
    }

    /**
     * Erstellt ein neues {@link Bindings} Object.
     */
    private Bindings()
    {
        super();
    }
}
