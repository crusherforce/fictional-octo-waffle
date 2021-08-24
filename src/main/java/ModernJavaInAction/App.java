package ModernJavaInAction;

public class App {
    public enum Flags {
        Streams,
        SupplierExamples,
        ParallelStreams
    }
    /**
    All the public methods can be called from the main function
     */
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        Flags flag = Flags.ParallelStreams;

        switch(flag) {
            case Streams:
                Streams.execute();
                break;
            case SupplierExamples:
                SupplierExamples.execute();
                break;
            case ParallelStreams:
                ParallelStreams.execute();
                break;
        }
    }
}
