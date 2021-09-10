package src.com.exist.exercise02_new.model;

public class Cell implements Comparable<Cell>{
    private String key;
    private String value;
    
    public Cell(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
    public String getKey() {
        return key;
    }
    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public int compareTo(Cell cell) {
        return this.value.compareTo(cell.value);
    }
    
}
