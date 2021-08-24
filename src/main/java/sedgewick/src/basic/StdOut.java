package sedgewick.src.basic;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

public final class StdOut {
    private static final String CHARSET_NAME = "UTF-8";
    private static final Locale LOCALE = Locale.US;
    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }

    private StdOut() {}

    public static void println() {
        out.println();
    }

    public static void println(Object x) {
        out.println(x);
    }

    public static void println(char x) {
        out.println(x);
    }

    public static void println(float x) {
        out.println(x);
    }

    public static void println(double x) {
        out.println(x);
    }

    public static void println(int x) {
        out.println(x);
    }

    public static void println(long x) {
        out.println(x);
    }

    public static void println(short x) {
        out.println(x);
    }

    public static void println(byte x) {
        out.println(x);
    }

    public static void println(boolean x) {
        out.println(x);
    }

    public static void printf(String format, Object... args) {
        out.printf(format, args);
        out.flush();
    }

    public static void printf(Locale l, String format, Object... args) {
        out.printf(l,format,args);
        out.flush();
    }
}
