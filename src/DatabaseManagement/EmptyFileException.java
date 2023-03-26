package DatabaseManagement;

public class EmptyFileException extends Exception {

    private String fileName;

    public EmptyFileException(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getMessage() {
        return "The file " + fileName + " is empty";
    }
}
