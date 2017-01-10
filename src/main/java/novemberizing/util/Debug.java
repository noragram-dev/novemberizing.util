package novemberizing.util;

/**
 *
 * @author novemberizing, i@novemberizing.net
 * @since 2017. 1. 4
 */
@SuppressWarnings("unused")
public class Debug {
    public static void On(Exception e){
        if(e!=null){
            Log.e("debug", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
