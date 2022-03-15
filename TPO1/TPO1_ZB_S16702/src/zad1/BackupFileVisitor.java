package zad1;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

public class BackupFileVisitor extends SimpleFileVisitor<Path> {
	private FileChannel resultFileChannel;
	private static OpenOption[] readOptions = {
		StandardOpenOption.READ,
	};
	
	
	public BackupFileVisitor(FileChannel resultFileChannel) {
		super();
		
		this.resultFileChannel = resultFileChannel;
	}

	@Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException
    {
		this.processFile(file);
        return super.visitFile(file, attrs);
    }
	
	private void processFile(Path file) throws IOException {
		FileChannel fileReadChannel = FileChannel.open(file, BackupFileVisitor.readOptions);
		
		int bufferSize = 2048;
        if (bufferSize > fileReadChannel.size()) {
           bufferSize = (int) fileReadChannel.size();
        }

		ByteBuffer fileReadByteBuffer = ByteBuffer.allocate(bufferSize);
		CharBuffer readCharBuffer;
		
		int bytesRead;
		do {
			// read
			bytesRead = fileReadChannel.read(fileReadByteBuffer);
			fileReadByteBuffer.flip();
			
			readCharBuffer = Charset.forName("CP1250").decode(fileReadByteBuffer);
			
			// write
			ByteBuffer fileWriteByteBuffer = Charset.forName("UTF-8").encode(readCharBuffer);
			this.resultFileChannel.write(fileWriteByteBuffer);
			
			
			// cleanup
			fileReadByteBuffer.clear();
			readCharBuffer.clear();
		} while (bytesRead > 0);
		
		fileReadChannel.close();
	}
}
