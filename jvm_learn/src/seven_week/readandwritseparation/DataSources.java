package seven_week.readandwritseparation;

public enum DataSources {
    MASTER("master"),
    SLAVE("slave");
 
    private String key;
 
    DataSources(String key) {
        this.key = key;
    }
 
    public String getKey() {
        return key;
    }
}