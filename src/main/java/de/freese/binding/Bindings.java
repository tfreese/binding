/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import de.freese.binding.binds.AbstractBooleanBinding;
import de.freese.binding.binds.AbstractDoubleBinding;
import de.freese.binding.binds.AbstractFloatBinding;
import de.freese.binding.binds.AbstractIntegerBinding;
import de.freese.binding.binds.AbstractLongBinding;
import de.freese.binding.binds.AbstractStringBinding;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.binds.IntegerBinding;
import de.freese.binding.binds.NumberBinding;
import de.freese.binding.binds.StringBinding;
import de.freese.binding.value.ObservableBooleanValue;
import de.freese.binding.value.ObservableFloatValue;
import de.freese.binding.value.ObservableIntegerValue;
import de.freese.binding.value.ObservableLongValue;
import de.freese.binding.value.ObservableNumberValue;
import de.freese.binding.value.ObservableStringValue;
import de.freese.binding.value.ObservableValue;

/**
 * Util-Klasse.
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

        if ((ov1 instanceof ObservableIntegerValue) || (ov2 instanceof ObservableIntegerValue))
        {
            binding = new AbstractIntegerBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
                 */
                @Override
                protected int computeValue()
                {
                    return ov1.intValue() + ov2.intValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = new AbstractLongBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractLongBinding#computeValue()
                 */
                @Override
                protected long computeValue()
                {
                    return ov1.longValue() + ov2.longValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = new AbstractFloatBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractFloatBinding#computeValue()
                 */
                @Override
                protected float computeValue()
                {
                    return ov1.floatValue() + ov2.floatValue();
                }
            };
        }
        else
        {
            binding = new AbstractDoubleBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractDoubleBinding#computeValue()
                 */
                @Override
                protected double computeValue()
                {
                    return ov1.doubleValue() + ov2.doubleValue();
                }
            };
        }

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

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
     * Verbindet zwei String-{@link ObservableValue} zu einem String-Binding.
     *
     * @param ov1 {@link ObservableStringValue}
     * @param ov2 {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public static StringBinding concat(final ObservableStringValue ov1, final ObservableStringValue ov2)
    {
        StringBinding binding = new AbstractStringBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractStringBinding#computeValue()
             */
            @Override
            protected String computeValue()
            {
                String v1 = ov1.getValue();
                String v2 = ov2.getValue();

                if ((v1 == null) && (v2 == null))
                {
                    return null;
                }

                return v1 + v2;
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

        if ((ov1 instanceof ObservableIntegerValue) || (ov2 instanceof ObservableIntegerValue))
        {
            binding = new AbstractIntegerBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
                 */
                @Override
                protected int computeValue()
                {
                    return ov1.intValue() / ov2.intValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = new AbstractLongBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractLongBinding#computeValue()
                 */
                @Override
                protected long computeValue()
                {
                    return ov1.longValue() / ov2.longValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = new AbstractFloatBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractFloatBinding#computeValue()
                 */
                @Override
                protected float computeValue()
                {
                    return ov1.floatValue() / ov2.floatValue();
                }
            };
        }
        else
        {
            binding = new AbstractDoubleBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractDoubleBinding#computeValue()
                 */
                @Override
                protected double computeValue()
                {
                    return ov1.doubleValue() / ov2.doubleValue();
                }
            };
        }

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

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
    public static BooleanBinding isEmpty(final ObservableStringValue ov)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return getValueSafe(ov.getValue()).isEmpty();
            }
        };

        ov.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param ov {@link ObservableStringValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding isNotEmpty(final ObservableStringValue ov)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return !getValueSafe(ov.getValue()).isEmpty();
            }
        };

        ov.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
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

        if ((ov1 instanceof ObservableIntegerValue) || (ov2 instanceof ObservableIntegerValue))
        {
            binding = new AbstractIntegerBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
                 */
                @Override
                protected int computeValue()
                {
                    return ov1.intValue() * ov2.intValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = new AbstractLongBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractLongBinding#computeValue()
                 */
                @Override
                protected long computeValue()
                {
                    return ov1.longValue() * ov2.longValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = new AbstractFloatBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractFloatBinding#computeValue()
                 */
                @Override
                protected float computeValue()
                {
                    return ov1.floatValue() * ov2.floatValue();
                }
            };
        }
        else
        {
            binding = new AbstractDoubleBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractDoubleBinding#computeValue()
                 */
                @Override
                protected double computeValue()
                {
                    return ov1.doubleValue() * ov2.doubleValue();
                }
            };
        }

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

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

        if ((ov1 instanceof ObservableIntegerValue) || (ov2 instanceof ObservableIntegerValue))
        {
            binding = new AbstractIntegerBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractIntegerBinding#computeValue()
                 */
                @Override
                protected int computeValue()
                {
                    return ov1.intValue() - ov2.intValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableLongValue) || (ov2 instanceof ObservableLongValue))
        {
            binding = new AbstractLongBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractLongBinding#computeValue()
                 */
                @Override
                protected long computeValue()
                {
                    return ov1.longValue() - ov2.longValue();
                }
            };
        }
        else if ((ov1 instanceof ObservableFloatValue) || (ov2 instanceof ObservableFloatValue))
        {
            binding = new AbstractFloatBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractFloatBinding#computeValue()
                 */
                @Override
                protected float computeValue()
                {
                    return ov1.floatValue() - ov2.floatValue();
                }
            };
        }
        else
        {
            binding = new AbstractDoubleBinding()
            {
                /**
                 * @see de.freese.binding.binds.AbstractDoubleBinding#computeValue()
                 */
                @Override
                protected double computeValue()
                {
                    return ov1.doubleValue() - ov2.doubleValue();
                }
            };
        }

        ov1.addListener((observable, oldValue, newValue) -> binding.update());
        ov2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

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
