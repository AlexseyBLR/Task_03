//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package analizator;

import entity.Tag;
import entity.EnumClass.Type;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import reader.FirstReader;

public class FirstAnalizatorImpl implements IAnalizator {
    private static final String BEGIN_OF_WORD = "<";
    private static final String END_OF_WORD = ">";
    private static final String OPEN_TAG_EXPRESSION = "<{1}[a-z A-Z]+|[=\"0-9\"]+>{1}";
    private static final String CLOSE_TAG_EXPRESSION = "\\/[a-z A-Z]+";
    private static final String TEXT_TAG_EXPRESSION = "[A-Z a-z 0-9]+";
    public List<Tag> tagsList = new ArrayList();
    FirstReader firstReader = new FirstReader();

    public FirstAnalizatorImpl() throws FileNotFoundException {
    }

    private String[] divideByChar(String fullString, String charType) {
        String[] wordMass = fullString.split(charType);
        return wordMass;
    }

    private String deleteSpace(String fullString) {
        String string = fullString.replaceAll("    ", "");
        return string;
    }

    public void analyze() {
        while(this.firstReader.hasNext()) {
            this.createObject(this.firstReader.getToken());
        }

    }

    private void createObject(String readString) {
        String[] wordMass = this.divideByChar(readString, ">");
        if (wordMass.length < 2) {
            this.objCreator(this.tegType(readString), this.deleteSpace(readString));
        } else {
            this.objCreatorFromMass(wordMass);
        }

    }

    private void objCreatorFromMass(String[] wordMass) {
        for(int i = 0; i < wordMass.length; ++i) {
            String[] mass = this.divideByChar(wordMass[i], "<");
            mass[1] = "<" + mass[1] + ">";

            for(int j = 0; j < mass.length; ++j) {
                this.objCreator(this.tegType(mass[j]), this.deleteSpace(mass[j]));
            }
        }

    }

    private List<Tag> checkOnTextString(List<Tag> tagsList) {
        for(int i = 0; i < tagsList.size() - 1; ++i) {
            Tag tag = new Tag();
            if (((Tag)tagsList.get(i)).getType() == Type.TEXT && ((Tag)tagsList.get(i + 1)).getType() == Type.TEXT) {
                tag.setType(((Tag)tagsList.get(i)).getType());
                tag.setContent(((Tag)tagsList.get(i)).getContent() + ((Tag)tagsList.get(i + 1)).getContent());
                tagsList.set(i, tag);
            }
        }

        return tagsList;
    }

    private Type tegType(String string) {
        Pattern openP = Pattern.compile("<{1}[a-z A-Z]+|[=\"0-9\"]+>{1}");
        Pattern closeP = Pattern.compile("\\/[a-z A-Z]+");
        Pattern textP = Pattern.compile("[A-Z a-z 0-9]+");
        Matcher openM = openP.matcher(string);
        Matcher closeM = closeP.matcher(string);
        Matcher textM = textP.matcher(string);
        if (openM.find()) {
            return Type.OPEN;
        } else if (closeM.find()) {
            return Type.CLOSE;
        } else {
            return textM.find() ? Type.TEXT : null;
        }
    }

    private void objCreator(Type type, String content) {
        Tag tag = new Tag();
        tag.setContent(content);
        tag.setType(type);
        this.tagsList.add(tag);
    }

    public void print() {
        System.out.println(this.checkOnTextString(this.tagsList));
    }
}
