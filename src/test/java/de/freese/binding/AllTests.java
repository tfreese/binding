/**
 *
 */
package de.freese.binding;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * @author Thomas Freese
 */
@RunWith(Suite.class)
@SuiteClasses(
{
        TestStringBinding.class, TestBooleanBinding.class
})

public class AllTests
{
    /**
     * Erstellt ein neues {@link AllTests} Object.
     */
    public AllTests()
    {
        super();
    }
    // /**
    // * In der Methode werden alle Testklassen registriert die durch JUnit aufgerufen werden sollen.
    // *
    // * @return {@link Test}
    // */
    // public static Test suite()
    // {
    // TestSuite suite = new TestSuite("de.freese.bindin");
    //
    // suite.addTest(new JUnit4TestAdapter(TestStringBinding.class));
    //
    // return suite;
    // }

}
