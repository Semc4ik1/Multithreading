public class FilePaths {

    String[] inputPath;
    String[] outputPath;

    public FilePaths(String[] args) {
        inputPath = new String[]{args[0]};
        outputPath = new String[]{args[1]};
    }

    public String[] getInputPath() {
        return inputPath;
    }

    public String[] getOutputPath() {
        return outputPath;
    }
}



