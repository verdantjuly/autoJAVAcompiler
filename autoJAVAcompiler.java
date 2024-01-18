import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class autoJAVAcompiler {

    static String direcotry = "./src";
    Integer Second = 100;
    static String shell = "./compile.sh";
    static HashMap<String, String> cache = new HashMap<String, String>();
    static Boolean isCompile;

    public static void fileChangeDetector(File[] files) {
        isCompile = false;
        for (int i = 0; i < files.length; i++) {
            autoJAVAcompiler.readFile(files[i].getPath());

        }

        if (isCompile == true) {
            ProcessBuilder pb = new ProcessBuilder(shell);

            try {
                pb.start();
                System.out.println("파일 변화 감지");

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void readFile(String fileName) {

        try {
            String code = new String(Files.readAllBytes(Paths.get(fileName)));
            if (!cache.get(fileName).equals(code)) {
                cache.put(fileName, code);
                isCompile = true;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File dir = new File(direcotry);
        File[] files = dir.listFiles();

        for (int i = 0; i < files.length; i++) {
            cache.put(files[i].getPath(), "");
        }
        Timer timer = new Timer("Timer");
        long delay = 0;
        long period = 500;
        TimerTask task = new TimerTask() {
            public void run() {
                autoJAVAcompiler.fileChangeDetector(files);
            }
        };
        timer.scheduleAtFixedRate(task, delay, period);
    }

}
