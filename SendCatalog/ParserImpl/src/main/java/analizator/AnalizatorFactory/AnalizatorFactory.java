package analizator.AnalizatorFactory;

import analizator.AnalizatorException.AnalizatorException;
import analizator.IAnalizator;
import analizator.TagsAnalizator;

public class AnalizatorFactory {
    public IAnalizator getFirstAnalizator(String path) throws AnalizatorException {
        return new TagsAnalizator(path);
    }

}
