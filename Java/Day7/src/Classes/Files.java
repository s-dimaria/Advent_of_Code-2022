package Classes;

public class Files {

    private String fileName;
    private long fileSize;

    public Files(String fileName, long fileSize) {
        this.fileName = fileName;
        this.fileSize = fileSize;
    }

    public long getFileSize() {
        return fileSize;
    }

    @Override
    public String toString() {
        return "Files{" +
                "fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                '}';
    }
}
