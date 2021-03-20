// Created: 31.07.2018
package de.freese.binding;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

/**
 * @author Thomas Freese
 */
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("TestSuite for Binding")
@SelectPackages("de.freese.binding")
public class TestSuite
{
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
