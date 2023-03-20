package de.cronos.awesometesting.suites;

import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages("de.cronos.awesometesting.service")
@ExcludePackages("de.cronos.awesometesting.suites")
public class AllServiceTests {
}
