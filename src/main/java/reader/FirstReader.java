package reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class FirstReader implements IReader {
    public static final String path;

    static {
        path = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "XML.xml";
    }

    Scanner scanner;
    BufferedReader reader;

    public FirstReader() throws FileNotFoundException {
        this.scanner = new Scanner(new File(path));
        this.reader = new BufferedReader(new FileReader(path));
    }

    public String getToken() {
        return this.scanner.nextLine();
    }

    public boolean hasNext() {
        return this.scanner.hasNextLine();
    }
}
