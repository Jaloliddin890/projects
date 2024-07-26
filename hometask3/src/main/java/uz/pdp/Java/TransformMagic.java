package uz.pdp.Java;


import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
public class TransformMagic {
    private static final Logger logger  = Logger.getLogger(TransformMagic.class.getName());
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();

    static {
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);
        logger.addHandler(consoleHandler);
    }

    @Before("execution(* uz.pdp.Java.Transform.start())")
    public void first(){
        logger.log(Level.INFO,"Initial +++++++++++++++++++++ ");
    }

    @After("execution(* uz.pdp.Java.Transform.start())")
    @AfterThrowing("execution(* uz.pdp.Java.Transform.start())")
    public void after(){
        logger.log(Level.INFO,"After --------------------------- ");
    }

    @AfterThrowing("execution(* uz.pdp.Java.Transform.start())")
    public void exceptionInformation(){logger.log(Level.INFO, "Exception about Information ! ! ! ");}

}
