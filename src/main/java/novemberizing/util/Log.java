package novemberizing.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import novemberizing.util.impl.ConsoleLog;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.Locale;

/**
 *
 * @author novemberizing
 * @since 2016/12/23
 */
@SuppressWarnings("unused, WeakerAccess, SameParameterValue")
public class Log {
    public static <T> String toJson(T o){
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        return gson.toJson(o);
    }

    public static String toJson(String name, Object o){
        GsonBuilder builder = new GsonBuilder().excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        String json = gson.toJson(o);
        return String.format(Locale.US, "{ \"%s\": %s }",name,json!=null && json.length()>0 ? json : "null");
    }

    public static String toJson(Object o,Gson gson){
        return gson.toJson(o);
    }

    private static Log __o = new Log();

    public static final int ERROR       = 0x00000001 <<  1;
    public static final int WARNING     = 0x00000001 <<  2;
    public static final int CAUTION     = 0x00000001 <<  3;
    public static final int NOTICE      = 0x00000001 <<  4;
    public static final int INFORMATION = 0x00000001 <<  5;
    public static final int FLOW        = 0x00000001 <<  6;
    public static final int VERBOSE     = 0x00000001 <<  7;
    public static final int DEBUG       = 0x00000001 <<  8;
    public static final int TAG         = 0x00000001 <<  29;
    public static final int HEADER      = 0x00000001 <<  30;

    public static Object e(String tag, Object o){ __o.___write(ERROR,tag,toJson(o),null); return o; }
    public static Object w(String tag, Object o){ __o.___write(WARNING,tag,toJson(o),null); return o; }
    public static Object c(String tag, Object o){ __o.___write(CAUTION,tag,toJson(o),null); return o; }
    public static Object n(String tag, Object o){ __o.___write(NOTICE,tag,toJson(o),null); return o; }
    public static Object i(String tag, Object o){ __o.___write(INFORMATION,tag,toJson(o),null); return o; }
    public static Object d(String tag, Object o){ __o.___write(DEBUG,tag,toJson(o),null); return o; }
    public static Object f(String tag, Object o){ __o.___write(FLOW,tag,toJson(o),null); return o; }
    public static Object v(String tag, Object o){ __o.___write(VERBOSE,tag,toJson(o),null); return o; }

    public static Object e(String tag, Object o, Throwable e){ __o.___write(ERROR,tag,toJson(o),e); return o; }
    public static Object w(String tag, Object o, Throwable e){ __o.___write(WARNING,tag,toJson(o),e); return o; }
    public static Object c(String tag, Object o, Throwable e){ __o.___write(CAUTION,tag,toJson(o),e); return o; }
    public static Object n(String tag, Object o, Throwable e){ __o.___write(NOTICE,tag,toJson(o),e); return o; }
    public static Object i(String tag, Object o, Throwable e){ __o.___write(INFORMATION,tag,toJson(o),e); return o; }
    public static Object d(String tag, Object o, Throwable e){ __o.___write(DEBUG,tag,toJson(o),e); return o; }
    public static Object f(String tag, Object o, Throwable e){ __o.___write(FLOW,tag,toJson(o),e); return o; }
    public static Object v(String tag, Object o, Throwable e){ __o.___write(VERBOSE,tag,toJson(o),e); return o; }

    public static String e(String tag, String msg){ __o.___write(ERROR,tag,msg,null); return msg; }
    public static String w(String tag, String msg){ __o.___write(WARNING,tag,msg,null); return msg; }
    public static String c(String tag, String msg){ __o.___write(CAUTION,tag,msg,null); return msg; }
    public static String n(String tag, String msg){ __o.___write(NOTICE,tag,msg,null); return msg; }
    public static String i(String tag, String msg){ __o.___write(INFORMATION,tag,msg,null); return msg; }
    public static String d(String tag, String msg){ __o.___write(DEBUG,tag,msg,null); return msg; }
    public static String f(String tag, String msg){ __o.___write(FLOW,tag,msg,null); return msg; }
    public static String v(String tag, String msg){ __o.___write(VERBOSE,tag,msg,null); return msg; }

    public static String e(String tag, String msg, Throwable e){ __o.___write(ERROR,tag,msg,e); return msg; }
    public static String w(String tag, String msg, Throwable e){ __o.___write(WARNING,tag,msg,e); return msg; }
    public static String c(String tag, String msg, Throwable e){ __o.___write(CAUTION,tag,msg,e); return msg; }
    public static String n(String tag, String msg, Throwable e){ __o.___write(NOTICE,tag,msg,e); return msg; }
    public static String i(String tag, String msg, Throwable e){ __o.___write(INFORMATION,tag,msg,e); return msg; }
    public static String d(String tag, String msg, Throwable e){ __o.___write(DEBUG,tag,msg,e); return msg; }
    public static String f(String tag, String msg, Throwable e){ __o.___write(FLOW,tag,msg,e); return msg; }
    public static String v(String tag, String msg, Throwable e){ __o.___write(VERBOSE,tag,msg,e); return msg; }

    private static String ___str(int type){
        switch(type){
            case ERROR:         return "ERROR";
            case WARNING:       return "WARNING";
            case CAUTION:       return "CAUTION";
            case NOTICE:        return "NOTICE";
            case INFORMATION:   return "INFORMATION";
            case FLOW:          return "FLOW";
            case DEBUG:         return "DEBUG";
            case VERBOSE:       return "VERBOSE";
            default:            return "UNKNOWN";
        }
    }

    public static void add(Logger o){ if(o!=null && !__o.__loggers.contains(o)){ __o.__loggers.add(o); } }
    public static void depth(int depth){ __o.__depth = depth; }
    public static void enable(int types){ __o.__types |= types; }
    public static void disable(int types){ __o.__types &= (~types); }

    private int __types = (ERROR | WARNING | CAUTION | NOTICE | INFORMATION | FLOW | VERBOSE | DEBUG | HEADER | TAG);
    private int __depth = 4;
    private LinkedList<Logger> __loggers = new LinkedList<>();
    private ConsoleLog __console = null;

    private static String ___str(StackTraceElement e){
        return String.format(Locale.getDefault(),"%s:%d:%s:%s",
                e.getFileName(),
                e.getLineNumber(),
                e.getClassName(),
                e.getMethodName());
    }

    private void ___write(int type, String tag, String message, Throwable e){
        if(__loggers.size()>0 && (__types & type)==type)
        {
            if((__types & TAG)!=TAG) {
                tag = "";
            }
            if((__types & HEADER)==HEADER) {
                StackTraceElement[] elements = Thread.currentThread().getStackTrace();
                Calendar calendar = Calendar.getInstance();

                String str = String.format(Locale.getDefault(),
                        "[%04d/%02d/%02d %02d:%02d:%02d.%03d] %s %s:%d:%s %s %s%s",
                        calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE),
                        calendar.get(Calendar.SECOND),
                        calendar.get(Calendar.MILLISECOND),
                        ___str(type),
                        ___str(elements[__depth]),
                        Thread.currentThread().getId(),
                        Thread.currentThread().getName(),
                        tag,
                        message,
                        e != null ? " exception: " + ___str(e.getStackTrace()[0]) + " " + e.getMessage() : "");
                if(__loggers.size()>0) {
                    for (Logger o : __loggers) {
                        o.write(type, tag, str);
                    }
                } else {
                    if(__console==null){ __console = new ConsoleLog(); }
                    __console.write(type, tag, str);
                }
            } else {
                String str;
                if(tag!=null && tag.length()>0){
                    str = tag + " " + message;
                } else {
                    str = message;
                }
                if(__loggers.size()>0) {
                    for (Logger o : __loggers) {
                        o.write(type, tag, str);
                    }
                } else {
                    if(__console==null){ __console = new ConsoleLog(); }
                    __console.write(type, tag, str);
                }
            }
        }
    }

    public Log(){}
}
