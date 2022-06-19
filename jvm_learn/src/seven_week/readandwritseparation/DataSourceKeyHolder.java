package seven_week.readandwritseparation;

import java.util.LinkedList;

public class DataSourceKeyHolder {
 
    private static final ThreadLocal<LinkedList<String>> holder = new ThreadLocal<LinkedList<String>>() {
        @Override
        protected LinkedList<String> initialValue() {
            return new LinkedList<>();
        }
    };
 
    public static void set(String key) {
        holder.get().push(key);
    }
 
    public static void clear() {
        holder.get().pop();
    }
 
    public static String getCurrentKey() {
        if (holder.get().size() == 0)
            return null;
        return holder.get().getFirst();
    }
 
    public static boolean isNestedCall() {
        return holder.get().size() > 1;
    }
 
}