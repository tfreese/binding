/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.property.Property;
import de.freese.binding.property.SimpleBooleanProperty;
import de.freese.binding.value.ChangeListener;

/**
 * @author Thomas Freese
 */
@TestMethodOrder(MethodOrderer.MethodName.class)
class TestBooleanBinding
{
    /**
    *
    */
    @Test
    void testBinding()
    {
        SimpleBooleanProperty property1 = new SimpleBooleanProperty();
        SimpleBooleanProperty property2 = new SimpleBooleanProperty();

        BooleanBinding binding = property1.and(property2);
        assertEquals(false, binding.getValue());

        property1.setValue(true);
        assertEquals(false, binding.getValue());

        property2.setValue(true);
        assertEquals(true, binding.getValue());
    }

    /**
     *
     */
    @Test
    void testProperty()
    {
        Property<Boolean> property = new SimpleBooleanProperty();

        ChangeListener<Boolean> listener = (observable, oldValue, newValue) -> assertEquals(false, newValue);
        property.addListener(listener);
        property.setValue(false);
        property.removeListener(listener);

        listener = (observable, oldValue, newValue) -> {
            assertEquals(false, oldValue);
            assertEquals(true, newValue);
        };
        property.addListener(listener);
        property.setValue(true);
        property.removeListener(listener);
    }
}
