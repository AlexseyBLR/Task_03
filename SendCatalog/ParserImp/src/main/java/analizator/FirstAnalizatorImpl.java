package analizator;

import entity.EnumClass.Type;
import entity.Tag;
import reader.FirstReader;
import reader.reader_exception.ReaderException;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstAnalizatorImpl implements IAnalizator {


    private static final String BEGIN_OF_WORD = "<";
    private static final String END_OF_WORD = ">";
    private static final String OPEN_TAG_EXPRESSION = "<{1}[a-z A-Z]+|[=\"0-9\"]+>{1}";
    private static final String CLOSE_TAG_EXPRESSION = "\\/[a-z A-Z]+";
    private static final String TEXT_TAG_EXPRESSION = "[A-Z a-z 0-9]+";


    private List<Tag> tagsList = new ArrayList();

    private FirstReader firstReader;


    public FirstAnalizatorImpl(String path) {
        try {
            firstReader = new FirstReader(path);
        } catch (ReaderException e) {
            e.printStackTrace();
        }
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

    private String deleteSpaces(String fullString) {
        String string = fullString.replaceAll("    ", "");
        return string;
    }


    private void createObject(String readString) {
        String[] wordMass = divideByChar(readString, END_OF_WORD);
        if (wordMass.length < 2) {
            objCreator(this.tegType(readString), deleteSpaces(readString));
        } else {
            objCreatorFromMass(wordMass);
        }

    }

    private void objCreatorFromMass(String[] wordMass) {
        for (int i = 0; i < wordMass.length; ++i) {
            String[] mass = divideByChar(wordMass[i], BEGIN_OF_WORD);
            mass[1] = "<" + mass[1] + ">";
            for (int j = 0; j < mass.length; ++j) {
                this.objCreator(tegType(mass[j]), deleteSpaces(mass[j]));
            }
        }

    }

    private List<Tag> checkOnTextString(List<Tag> tagsList) {
        for (int i = 0; i < tagsList.size() - 1; ++i) {
            Tag tag = new Tag();
            if ((tagsList.get(i)).getType() == Type.TEXT && (tagsList.get(i + 1)).getType() == Type.TEXT) {
                tag.setType((tagsList.get(i)).getType());
                tag.setContent((tagsList.get(i)).getContent() + (tagsList.get(i + 1)).getContent());
                tagsList.set(i, tag);
            }
        }

        return tagsList;
    }

    private Type tegType(String string) {
        Pattern openP = Pattern.compile(OPEN_TAG_EXPRESSION);
        Pattern closeP = Pattern.compile(CLOSE_TAG_EXPRESSION);
        Pattern textP = Pattern.compile(TEXT_TAG_EXPRESSION);
        Matcher openM = openP.matcher(string);
        Matcher closeM = closeP.matcher(string);
        Matcher textM = textP.matcher(string);
        if (openM.find()) {
            return Type.OPEN;
        } else if (closeM.find()) {
            return Type.CLOSE;
        } else if (textM.find()) {
            return Type.TEXT;
        }
        return null;
    }

    private void objCreator(Type type, String content) {
        Tag tag = new Tag();
        tag.setContent(content);
        tag.setType(type);
        this.tagsList.add(tag);
    }

    public void printAllTags() {
        analyze();
        for (int i = 0; i < tagsList.size(); i++) {
            if (tagsList.get(i).getType() == Type.TEXT && tagsList.get(i).getContent().equals("")) {
                tagsList.remove(i);
            }
        }
        System.out.println(checkOnTextString(this.tagsList));
    }

}
