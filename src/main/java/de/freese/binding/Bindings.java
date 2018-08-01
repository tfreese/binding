/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import de.freese.binding.binds.AbstractBooleanBinding;
import de.freese.binding.binds.AbstractStringBinding;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.binds.StringBinding;
import de.freese.binding.value.ObservableBooleanValue;
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
     * @param o1 {@link ObservableBooleanValue}
     * @param o2 {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding and(final ObservableBooleanValue o1, final ObservableBooleanValue o2)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            {
                bind(o1, o2);
            }

            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return o1.get() && o2.get();
            }
        };

        // o1.addListener((observable, oldValue, newValue) -> binding.update());
        // o2.addListener((observable, oldValue, newValue) -> binding.update());
        //
        // binding.update();

        return binding;
    }

    /**
     * Verbindet zwei String-{@link ObservableValue} zu einem String-Binding.
     *
     * @param o1 {@link ObservableStringValue}
     * @param o2 {@link ObservableStringValue}
     * @return {@link StringBinding}
     */
    public static StringBinding concat(final ObservableStringValue o1, final ObservableStringValue o2)
    {
        StringBinding binding = new AbstractStringBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractStringBinding#computeValue()
             */
            @Override
            protected String computeValue()
            {
                String v1 = o1.getValue();
                String v2 = o2.getValue();

                if ((v1 == null) && (v2 == null))
                {
                    return null;
                }

                return v1 + v2;
            }
        };

        o1.addListener((observable, oldValue, newValue) -> binding.update());
        o2.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param o {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding not(final ObservableBooleanValue o)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return !o.get();
            }
        };

        o.addListener((observable, oldValue, newValue) -> binding.update());

        binding.update();

        return binding;
    }

    /**
     * @param o1 {@link ObservableBooleanValue}
     * @param o2 {@link ObservableBooleanValue}
     * @return {@link BooleanBinding}
     */
    public static BooleanBinding or(final ObservableBooleanValue o1, final ObservableBooleanValue o2)
    {
        BooleanBinding binding = new AbstractBooleanBinding()
        {
            /**
             * @see de.freese.binding.binds.AbstractBooleanBinding#computeValue()
             */
            @Override
            protected Boolean computeValue()
            {
                return o1.get() || o2.get();
            }
        };

        o1.addListener((observable, oldValue, newValue) -> binding.update());
        o2.addListener((observable, oldValue, newValue) -> binding.update());

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
