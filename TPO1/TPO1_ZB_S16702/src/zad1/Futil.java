package zad1;

import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Futil {	
	private static OpenOption[] writeOptions = {
		StandardOpenOption.WRITE,
		StandardOpenOption.CREATE,
		StandardOpenOption.TRUNCATE_EXISTING,
	};

	public static void processDir(String dirName, String resultFileName) {
		Path dirPath = Paths.get(dirName);
		Path resultFilePath = Paths.get(resultFileName);
		
		try {
			FileChannel resultFileChannel = FileChannel.open(resultFilePath, Futil.writeOptions);
			
			BackupFileVisitor vis = new BackupFileVisitor(resultFileChannel);
			Files.walkFileTree(dirPath, vis);
			
			resultFileChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		
	}
}
