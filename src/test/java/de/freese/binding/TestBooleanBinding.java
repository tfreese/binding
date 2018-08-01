/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.property.Property;
import de.freese.binding.property.SimpleBooleanProperty;
import de.freese.binding.value.ChangeListener;

/**
 * @author Thomas Freese
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBooleanBinding
{
    /**
     * Erstellt ein neues {@link TestBooleanBinding} Object.
     */
    public TestBooleanBinding()
    {
        super();
    }

    /**
    *
    */
    @Test
    public void testBinding()
    {
        SimpleBooleanProperty property1 = new SimpleBooleanProperty();
        SimpleBooleanProperty property2 = new SimpleBooleanProperty();

        BooleanBinding binding = property1.and(property2);
        Assert.assertEquals(false, binding.getValue());

        property1.setValue(true);
        Assert.assertEquals(false, binding.getValue());

        property2.setValue(true);
        Assert.assertEquals(true, binding.getValue());
    }

    /**
     *
     */
    @Test
    public void testProperty()
    {
        Property<Boolean> property = new SimpleBooleanProperty();

        ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> Assert.assertEquals(false, newValue);
        property.addListener(listener);
        property.setValue(false);
        property.removeListener(listener);

        listener = (observable, oldValue, newValue) -> {
            Assert.assertEquals(false, oldValue);
            Assert.assertEquals(true, newValue);
        };
        property.addListener(listener);
        property.setValue(true);
        property.removeListener(listener);
    }
}
