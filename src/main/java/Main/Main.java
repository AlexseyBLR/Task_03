package Main;

import analizator.FirstAnalizatorImpl;
import reader.FirstReader;

import java.io.IOException;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        new FirstReader();
        FirstAnalizatorImpl analizator = new FirstAnalizatorImpl();
        analizator.analyze();
        analizator.print();
    }
}
