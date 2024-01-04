package exercise;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;


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
               FileWriter fileWriter = new FileWriter(fileResult);
               fileWriter.write(combineResult);
               fileWriter.close();
           } catch (Exception e) {
               throw new IllegalStateException("NoSuchFileException");
           }
           Long size;
           //Проверяем, что файл записан
            try {
                String resultFile = Files.readString(Paths.get(fileResult));
                System.out.println("resultFile" + " " + resultFile);

            } catch (Exception e) {
                throw new IllegalStateException(e);
            }

            return fileResult;

        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "";
        });
        return result;

    }
    public static CompletableFuture<Long> getDirectorySize(String path) {
        CompletableFuture<Long> union = CompletableFuture.supplyAsync(() -> {

            Long size;

            try{
                 size = Files.walk(getFullPath(path), 1)
                         .filter(Files::isRegularFile)
                         .mapToLong(v -> {
                             try {
                                 return Files.size(v);
                             } catch (IOException e) {
                                 throw new RuntimeException(e);
                             }

                         })
                         .sum();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            return size;
        });

        return union;
    }
    public static Long getSize(String path) {

        Long size;
        try {
            size = Files.size(Paths.get(path));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return size;
    }

    // END

    public static void main(String[] args) throws Exception {
// BEGIN
        CompletableFuture<String> result = unionFiles("src/main/resources/file1.txt",
                "src/main/resources/file2.txt","src/main/resources/result.txt");
        CompletableFuture<Long> size = getDirectorySize("src/main/resources");
        System.out.println("result" + " " + result);
        System.out.println("size" + " " + size.get());

        System.out.println("size file" + " " + getSize( "src/main/resources/file2.txt"));


        // END
    }
}

