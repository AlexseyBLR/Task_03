import analizator.AnalizatorException.AnalizatorException;
import analizator.AnalizatorFactory.AnalizatorFactory;

import java.io.File;

public class Main {
    public static void main(String[] args) throws AnalizatorException {
        AnalizatorFactory factory = new AnalizatorFactory();
        factory.getFirstAnalizator("src" + File.separator + "main" + File.separator + "resources" + File.separator + "Config.xml");
    }
}
