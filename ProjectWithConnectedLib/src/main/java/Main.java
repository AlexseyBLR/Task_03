import analizator.FirstAnalizatorImpl;

public class Main {
    public static void main(String[] args) {
        FirstAnalizatorImpl analizator = new FirstAnalizatorImpl("E:\\EPAM_Tasks\\Task_03\\ProjectWithLibrary\\src\\main\\resources\\XML.xml");
        analizator.printAllTags();
    }
}
