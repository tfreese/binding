/**
 * Created: 31.07.2018
 */

package de.freese.binding.binds;

import java.util.Objects;
import de.freese.binding.expression.AbstractBooleanExpression;
import de.freese.binding.property.Property;
import de.freese.binding.value.ChangeListener;
import de.freese.binding.value.ObservableValue;

/**
 * @author Thomas Freese
 */
public abstract class AbstractBooleanBinding extends AbstractBooleanExpression implements BooleanBinding
{
    /**
     *
     */
    private final ChangeListener<? super Object> listener = (observable, oldValue, newValue) -> update();

    /**
    *
    */
    private Boolean value = null;

    /**
     * Erstellt ein neues {@link AbstractBooleanBinding} Object.
     */
    public AbstractBooleanBinding()
    {
        super();
    }

    /**
     * @see de.freese.binding.binds.Binding#bind(de.freese.binding.value.ObservableValue[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public void bind(final ObservableValue<? extends Object>...dependencies)
    {
        for (ObservableValue<? extends Object> o : dependencies)
        {
            o.addListener(this.listener);
        }

        update();
    }

    /**
     * Ermittelt den neuen Wert des Bindings, falls sich einer der {@link Property} geändert hat.
     *
     * @return Boolean
     */
    protected abstract Boolean computeValue();

    /**
     * @see de.freese.binding.value.ObservableValue#getValue()
     */
    @Override
    public Boolean getValue()
    {
        return this.value;
    }

    /**
     * @see de.freese.binding.binds.Binding#unbind()
     */
    @Override
    public void unbind()
    {
        // TODO Auto-generated method stub

    }

    /**
     * @see de.freese.binding.binds.Binding#update()
     */
    @Override
    public void update()
    {
        Boolean old = this.value;
        this.value = computeValue();

        if (Objects.equals(old, this.value))
        {
            fireValueChangedEvent(old, this.value);
        }
    }
}
