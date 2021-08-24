package sedgewick.src.basic;

import java.io.BufferedInputStream;
import java.util.*;
import java.util.regex.Pattern;

public final class StdIn {
    private static final String CHARSET_NAME = "UTF-8";
    private static final Locale LOCALE = Locale.US;

    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");

    private static Scanner scanner;

    static {
        resync();
    }

    private static void resync() {
        setScanner(new Scanner(new BufferedInputStream(System.in), CHARSET_NAME));
    }

    private static void setScanner(Scanner s) {
        StdIn.scanner = s;
        StdIn.scanner.useLocale(LOCALE);
    }

    private StdIn() {
    }

    public static boolean isEmpty() {
        return !scanner.hasNext();
    }

    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public static boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public static String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException nsee) {
            line = null;
        }
        return null;
    }

    public static char readChar() {
        try {
            scanner.useDelimiter(EMPTY_PATTERN);
            String ch = scanner.next();
            assert ch.length() == 1 : "StdIn::readChar() error";
            scanner.useDelimiter(WHITESPACE_PATTERN);
            return ch.charAt(0);
        } catch (NoSuchElementException nsee) {
            throw new NoSuchElementException("Attempt to read 'char' from the input but no more tokens available");
        }
    }

    public static String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }

        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public static String readString() {
        try {
            return scanner.next();
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("Attempt to read 'String' from the input but no more tokens available");
        }
    }

    public static int readInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'int' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'int' from the input but no more tokens available");
        }
    }

    public static double readDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'double' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'double' from the input but no more tokens available");
        }
    }

    public static float readFloat() {
        try {
            return scanner.nextFloat();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'float' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'float' from the input but no more tokens available");
        }
    }

    public static long readLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'long' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'long' from the input but no more tokens available");
        }
    }

    public static short readShort() {
        try {
            return scanner.nextShort();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'short' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'short' from the input but no more tokens available");
        }
    }

    public static byte readByte() {
        try {
            return scanner.nextByte();
        } catch (InputMismatchException ime) {
            String token = scanner.next();
            throw new InputMismatchException("Attempt to read 'byte' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'byte' from the input but no more tokens available");
        }
    }

    public static boolean readBoolean() {
        try {
            String token = readString();
            if ("true".equalsIgnoreCase(token) || "1".equals(token)) {
                return true;
            }
            if ("false".equalsIgnoreCase(token) || "0".equals(token)) {
                return false;
            }
            throw new InputMismatchException("Attempt to read 'boolean' from the input but the next token is " + token);
        } catch (NoSuchElementException nsse) {
            throw new NoSuchElementException("\"Attempt to read 'boolean' from the input but no more tokens available");
        }
    }

    public static String[] readAllStrings() {
        String[] tokens = WHITESPACE_PATTERN.split(readAll());

        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }

        String[] decapitokens = new String[tokens.length - 1];
        System.arraycopy(tokens, 1, decapitokens, 0, tokens.length - 1);
        return decapitokens;
    }

    public static String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[0]);
    }

    public static int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }
        return vals;
    }

    public static double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Double.parseDouble(fields[i]);
        }
        return vals;
    }

    public static long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Long.parseLong(fields[i]);
        }
        return vals;
    }

    public static float[] readAllFloats() {
        String[] fields = readAllStrings();
        float[] vals = new float[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Float.parseFloat(fields[i]);
        }
        return vals;
    }

}
