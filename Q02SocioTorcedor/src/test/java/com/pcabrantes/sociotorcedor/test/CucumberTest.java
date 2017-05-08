package com.pcabrantes.sociotorcedor.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 *
 * @author Paulo Cesar Abrantes
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features",
        glue = "com.pcabrantes.sociotorcedor.test")
public class CucumberTest {

}
