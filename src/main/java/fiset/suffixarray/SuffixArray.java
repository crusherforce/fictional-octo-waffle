package fiset.suffixarray;
/*
 **  Abstract class that captures the behavior of a suffix array.
 */

public abstract class SuffixArray {
    // Length of the suffix array
    protected final int N;

    // T is the text
    protected int[] T;

    // The sorted suffix array values
    protected int[] sa;

    // Longest Common Prefix array
    protected int[] lcp;

    private boolean constructedSa = false;
    private boolean constructedLcpArray = false;

    public SuffixArray(int[] text) {
        if (text == null) {
            throw new IllegalArgumentException("Text cannot be null.");
        }
        this.T = text;
        this.N = text.length;
        buildLcpArray();
    }

    public int getTextLength() {
        return T.length;
    }

    // returns the suffix array
    public int[] getSa() {
        buildSuffixArray();
        return sa;
    }

    // returns the LCP array
    public int[] getLcpArray() {
        buildLcpArray();
        return lcp;
    }

    protected void buildLcpArray() {
        if (constructedLcpArray) {
            return;
        }
        buildSuffixArray();
        kasai();
        constructedLcpArray = true;
    }

    protected void buildSuffixArray() {
        if (constructedSa) {
            return;
        }
        construct();
        constructedSa = true;
    }

    // Use Kasai algorithm to build LCP array
    // http://www.mi.fu-berlin.de/wiki/pub/ABI/RnaSeqP4/suffix-array.pdf
    private void kasai() {
        lcp = new int[N];
        int[] inv = new int[N];

        for (int i = 0; i < N; i++) {
            inv[sa[i]] = i;
        }

        for (int i = 0, len = 0; i < N; i++) {
            if (inv[i] > 0) {
                int k = sa[inv[i] - 1];
                while ((i + len < N) && (k + len < N) && T[i + len] == T[k + len]) {
                    len++;
                }
                lcp[inv[i]] = len;
                if (len > 0) {
                    len--;
                }
            }
        }
    }

    protected static int[] toIntArray(String s) {
        if (s == null) {
            return null;
        }
        int[] t = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            t[i] = s.charAt(i);
        }

        return t;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-----i-----SA-----LCP---Suffix\n");

        for (int i = 0; i < N; i++) {
            int suffixLen = N - sa[i];
            char[] suffixArray = new char[suffixLen];
            for (int j = sa[i], k = 0; j < N; j++, k++) {
                suffixArray[k] = (char) T[j];
            }
            String suffix = new String(suffixArray);
            String formattedStr = String.format("% 7d % 7d % 7d %s\n", i, sa[i], lcp[i], suffix);
            sb.append(formattedStr);
        }
        return sb.toString();
    }

    protected abstract void construct();
}
