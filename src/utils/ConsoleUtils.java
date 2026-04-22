package utils;

// Utility class for clearing the console in different environments (Windows, Linux, macOS)
public class ConsoleUtils {
    public static void clearConsole() {
        try {
            String os = System.getProperty( "os.name" ).toLowerCase();

            if ( os.contains( "win" ) ) {
                // Windows: use "cls" command
                new ProcessBuilder( "cmd", "/c", "cls" )
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                // Linux/macOS: use "clear" command
                new ProcessBuilder( "clear" )
                        .inheritIO()
                        .start()
                        .waitFor();
            }

        } catch ( Exception e ) {
            // Fallback if process-based clearing is not supported
            fallbackClear();
        }
    }

    // Fallback method using ANSI escape codes and extra spacing
    private static void fallbackClear() {
        // ANSI escape code fallback
        System.out.print( "\033[H\033[2J" );
        System.out.flush();

        // Extra blank lines if ANSI escape codes are not supported
        for ( int i = 0; i < 50; i++ ) {
            System.out.println();
        }
    }
}