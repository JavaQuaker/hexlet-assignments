package exercise;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    private static Path getFullPath(String filePath) {
        return Paths.get(filePath).toAbsolutePath().normalize();
    }

    public static CompletableFuture<String> unionFiles(String fileOne, String fileTwo, String fileResult)
            throws IOException {

        CompletableFuture<String> result = CompletableFuture.supplyAsync(() -> {

           try {
               String resultOne = Files.readString(Paths.get(fileOne));
               System.out.println("resOne" + resultOne);
               String resultTwo = Files.readString(Paths.get(fileTwo));
               String combineResult = resultOne +  " " + resultTwo;
               Files.writeString(Path.of(fileResult), combineResult);
           } catch (Exception e) {
               throw new IllegalStateException("NoSuchFileException");
           }

            return fileResult;

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "";
        });
        return result;

    }

    // END

    public static void main(String[] args) throws Exception {

        System.out.println(unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt",
                "src/main/resources/result.txt"));

        // BEGIN

        // END
    }
}

