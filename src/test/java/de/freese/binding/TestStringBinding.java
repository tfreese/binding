/**
 * Created: 31.07.2018
 */

package de.freese.binding;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import de.freese.binding.binds.BooleanBinding;
import de.freese.binding.binds.IntegerBinding;
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
    public void testBindingConcat()
    {
        SimpleStringProperty p1 = new SimpleStringProperty();
        SimpleStringProperty p2 = new SimpleStringProperty();

        StringBinding binding = p1.concat(p2);
        Assert.assertEquals(null, binding.getValue());

        p1.setValue("a");
        p2.setValue("");
        Assert.assertEquals("a", binding.getValue());

        p2.setValue("-b");
        Assert.assertEquals("a-b", binding.getValue());
    }

    /**
    *
    */
    @Test
    public void testBindingIsEmpty()
    {
        SimpleStringProperty p1 = new SimpleStringProperty();

        BooleanBinding bindingEmpty = p1.isEmpty();
        BooleanBinding bindingNotEmpty = p1.isNotEmpty();

        Assert.assertEquals(true, bindingEmpty.get());
        Assert.assertEquals(false, bindingNotEmpty.get());

        p1.setValue("a");
        Assert.assertEquals(false, bindingEmpty.get());
        Assert.assertEquals(true, bindingNotEmpty.get());
    }

    /**
    *
    */
    @Test
    public void testBindingLength()
    {
        SimpleStringProperty p1 = new SimpleStringProperty();

        IntegerBinding binding = p1.length();
        Assert.assertEquals(0, binding.get());

        p1.setValue("a");
        Assert.assertEquals(1, binding.get());

        p1.setValue("a a ");
        Assert.assertEquals(4, binding.get());
    }

    /**
     *
     */
    @Test
    public void testProperty()
    {
        Property<String> p = new SimpleStringProperty();

        ChangeListener<String> listener = (observable, oldValue, newValue) -> Assert.assertEquals("TeSt", newValue);
        p.addListener(listener);
        p.setValue("TeSt");
        p.removeListener(listener);

        listener = (observable, oldValue, newValue) -> {
            Assert.assertEquals("TeSt", oldValue);
            Assert.assertEquals(null, newValue);
        };
        p.addListener(listener);
        p.setValue(null);
        p.removeListener(listener);
    }
}
