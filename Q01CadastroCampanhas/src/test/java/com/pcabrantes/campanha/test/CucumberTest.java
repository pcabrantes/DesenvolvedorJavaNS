package com.pcabrantes.campanha.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 *
 *
 * @author Paulo Cesar Abrantes
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", tags = "@CadastrarCampanhaTest", glue = "com.pcabrantes.campanha.test.service")
public class CucumberTest {

}
