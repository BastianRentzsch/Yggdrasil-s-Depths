package utils;

public class ConsoleUtils {

    public static void clearConsole() {
        try {
            String os = System.getProperty( "os.name" ).toLowerCase();

            if ( os.contains( "win" ) ) {
                // Windows
                new ProcessBuilder( "cmd", "/c", "cls" )
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                // Linux / macOS
                new ProcessBuilder( "clear" )
                        .inheritIO()
                        .start()
                        .waitFor();
            }

        } catch ( Exception e ) {
            // Fallback (works in most IDEs/unsupported terminals)
            fallbackClear();
        }
    }

    private static void fallbackClear() {
        // ANSI escape code fallback
        System.out.print( "\033[H\033[2J" );
        System.out.flush();

        // Extra spacing if ANSI not supported
        for ( int i = 0; i < 50; i++ ) {
            System.out.println();
        }
    }
}
