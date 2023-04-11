package com.betika.runner;


import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.*;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("@target/rerun.txt")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "com.betika.steps")
//@ConfigurationParameter(key = "cucumber.glue", value = "com.betika.steps")
@ConfigurationParameter(key= PLUGIN_PUBLISH_ENABLED_PROPERTY_NAME, value = "true")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME  ,
        value = "pretty, json:target/cucumber_rerun.json, html:target/cucumber_rerun.html, rerun:target/rerun.txt" )
//@ConfigurationParameter(key= EXECUTION_DRY_RUN_PROPERTY_NAME , value = "false")
public class FailedTestRunner {
}
