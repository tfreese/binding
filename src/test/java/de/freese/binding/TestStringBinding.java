/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import de.freese.binding.binds.StringBinding;
import de.freese.binding.property.Property;
import de.freese.binding.property.SimpleStringProperty;
import de.freese.binding.value.ChangeListener;

/**
 * @author Thomas Freese
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestStringBinding
{
    /**
     * Erstellt ein neues {@link TestStringBinding} Object.
     */
    public TestStringBinding()
    {
        super();
    }

    /**
    *
    */
    @Test
    public void testBinding()
    {
        SimpleStringProperty property1 = new SimpleStringProperty();
        SimpleStringProperty property2 = new SimpleStringProperty();

        StringBinding binding = property1.concat(property2);
        Assert.assertEquals(null, binding.getValue());

        property1.setValue("a");
        property2.setValue("");
        Assert.assertEquals("a", binding.getValue());

        property2.setValue("-b");
        Assert.assertEquals("a-b", binding.getValue());
    }

    /**
     *
     */
    @Test
    public void testProperty()
    {
        Property<String> property = new SimpleStringProperty();

        ChangeListener<String> listener = (observable, oldValue, newValue) -> Assert.assertEquals("TeSt", newValue);
        property.addListener(listener);
        property.setValue("TeSt");
        property.removeListener(listener);

        listener = (observable, oldValue, newValue) -> {
            Assert.assertEquals("TeSt", oldValue);
            Assert.assertEquals(null, newValue);
        };
        property.addListener(listener);
        property.setValue(null);
        property.removeListener(listener);
    }
}
