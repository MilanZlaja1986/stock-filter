
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class Rights {
	
	public static void Copyrights(String path) throws IOException {
		List<File> files = Arrays.asList(new File(path).listFiles());

		for (File file : files) {
			if (!file.isFile())
				continue;

			if (!file.getName().endsWith(".java")) {
				files.remove(file);
			}

			BufferedReader br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				if (!line.contains("// Copyright Milan Zlatkovic, 2019") && (br.readLine() == null)) {
					BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
					writer.append("\n// Copyright Milan Zlatkovic, 2019");
					writer.close();
				}
			}

			br.close();

		}

	}

}
