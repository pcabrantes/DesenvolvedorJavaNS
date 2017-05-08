package com.pcabrantes.sociotorcedor.test;

import com.pcabrantes.sociotorcedor.Application;
import org.springframework.test.context.ContextConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Classe Genérica de Testes
 *
 * @author Paulo Cesar Abrantes
 */
@ContextConfiguration(classes = Application.class)
public class SpringTest {

    public static final Date HOJE = new Date();
    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");


}
