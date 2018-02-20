package analizator;

import analizator.AnalizatorException.AnalizatorException;
import entity.EnumClass.Type;
import entity.Tag;
import reader.FileReader;
import reader.ReaderException.ReaderException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizator implements IAnalizator {


    private static final String BEGIN_OF_WORD = "<";
    private static final String END_OF_WORD = ">";
    private static final String OPEN_TAG_EXPRESSION = "<{1}[a-z A-Z]+|[=\"0-9\"]+>{1}";
    private static final String CLOSE_TAG_EXPRESSION = "\\/[a-z A-Z]+";
    private static final String TEXT_TAG_EXPRESSION = ".+|[^< ^>]]";
    private static final int massLengthWithOneWord = 2;
    private static final String tagFromSpaces = "";
    private Pattern openP = Pattern.compile(OPEN_TAG_EXPRESSION);// openP - ну именуйте же вы нормально, ну потратьте чуть-чуть можговых усилий, определяя, чтовы все-таки делаете
    private Pattern closeP = Pattern.compile(CLOSE_TAG_EXPRESSION);
    private Pattern textP = Pattern.compile(TEXT_TAG_EXPRESSION);
    private List<Tag> tagsList = new ArrayList();

    private FileReader firstReader;// первый чтец - и что это поле значит?


    public Analizator(String path) throws AnalizatorException {
        try {
            firstReader = new FileReader(path);
        } catch (ReaderException e) {
            throw new AnalizatorException(e);
        }
        printTags();// ЧТО? какая еще печать на консоль в библиотеке? 
    }

    private void analyze() {
        while (firstReader.hasNext()) {
            createObject(firstReader.getToken());

        }

    }

    private String[] divideByChar(String fullString, String charType) {
        String[] wordMass = fullString.split(charType);
        return wordMass;
    }

    private String deleteSpaces(String fullString) {// deleteSpaces - читается как разделимые пробелы, ане делитель по пробелам
        // плюс чуть-чуть мышления и хардкоженный методм ожет превратиться в универсальный
        String string = fullString.replaceAll("  ", "");// "  " - розг на вас нет
        return string;
    }

    private void createObject(String readString) {//readString - xmlData
        // метод createObject - как понять, что этот метод, находясь в классе Analizator должен создавать, если даже у него возвращаемого типа нет
        String[] wordMass = divideByChar(readString, END_OF_WORD);
        if (wordMass.length < massLengthWithOneWord) {
            objCreator(this.tegType(readString), deleteSpaces(readString));
        } else {
            createObjFromMass(wordMass);
        }

    }

    private void createObjFromMass(String[] wordMass) {//FromMass - фром чего?
        //wordMass - словетсный масс?
        for (int i = 0; i < wordMass.length; ++i) {
            String[] mass = divideByChar(wordMass[i], BEGIN_OF_WORD);
            if (mass.length > 1) {
                mass[1] = "<" + mass[1] + ">";
            }
            for (int j = 0; j < mass.length; ++j) {
                if (!deleteSpaces(mass[j]).equals(tagFromSpaces)) // if который проверяет не состоит ли тег только из пробелов
                    objCreator(tegType(mass[j]), deleteSpaces(mass[j]));
            }
        }
    }

    private Type tegType(String string) {
        Matcher openM = this.openP.matcher(string);
        Matcher closeM = this.closeP.matcher(string);
        Matcher textM = this.textP.matcher(string);
        if (openM.find()) {
            return Type.OPEN;
        } else if (closeM.find()) {
            return Type.CLOSE;
        } else if (textM.find()) {
            return Type.TEXT;
        }
        return null;
    }

    private Tag objCreator(Type type, String content) {
        Tag tag = new Tag();
        tag.setContent(content);
        tag.setType(type);
        tagListCreate(tag);
        return tag;
    }

    private void tagListCreate(Tag tag) {// что-то по сигнатуре этого метода, не скажешь, что он что-то создает
        this.tagsList.add(tag);
    }

    public void printTags() {//слов нет
        analyze();// тем более слов нет - мето печати вызывает какой-то анализ, это как доярка, которая решает, что должны управлять государтсовм
        for (int i = 0; i < tagsList.size(); i++) {
            System.out.println(tagsList.get(i));
        }
    }

}
