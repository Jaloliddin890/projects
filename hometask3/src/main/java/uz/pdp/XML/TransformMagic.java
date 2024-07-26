package uz.pdp.XML;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransformMagic {
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    private static final ConsoleHandler handler = new ConsoleHandler();

    static {
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    public void begin(){
        logger.log(Level.INFO, "begin");
    }
    public void end(){
        logger.log(Level.INFO, "end");
    }

}



//Transform nomli class yarating va uni ichida 1..10 gacha bo'lgan sonlarni ekranga chiqaruvchi start()
// nomli method yozing va ushbu method chaqirilganda method bajarilishidan oldin va keyin log tashlaydigan dastur yozing.
// XML Based konfiguratsiyadan foydalanib. Before, After va AfterReturning Advice laridan foydalaning.
//Transform class ni start method chaqirilganda endi exception sodir bo'lsa log tashlaydigan dastur yozing.
//Va Yuqoridagi Topshiriqlarni Java Config yordamida amalga oshiring.
//
//Examle:
//
//@Aspect
//@Component
//public class TransformAspect {
//    // ...
//}