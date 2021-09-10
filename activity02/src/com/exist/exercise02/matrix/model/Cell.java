package src.com.exist.exercise02.matrix.model;

public class Cell implements Comparable<Cell>{
    private String key;
    private String content;

    public Cell(String content) {
        this.content = content;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int compareTo(Cell cell) {
        return this.content.compareTo(cell.content);
    }

}
