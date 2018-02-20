package reader;

import reader.ReaderException.ReaderException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

// а закрывать твой сканер будет Пушкин? как можно совершить такую ошибку?
public class FileReader implements IReader {


    private Scanner scanner;// а мы оговаривали, что классом Scanner не пользуемся

    public FileReader(String path) throws ReaderException {
        try {
            this.scanner = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            throw new ReaderException(e);
        }
    }


    public String getToken() {
        return this.scanner.nextLine();
    }

    public boolean hasNext() {
        return this.scanner.hasNextLine();
    }
}
