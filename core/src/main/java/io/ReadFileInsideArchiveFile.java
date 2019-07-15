package io;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ReadFileInsideArchiveFile {

	/*
	 * Supports only single level of archiving (i.e. not supporting archive--> archive --> file).
	 * */
	public byte[] readFileInZipArchive2(String zipFileUrl, String pathToFileInsideZip) throws IOException {

		long start = System.currentTimeMillis();
		URI uri1 = URI.create("jar:file:/C:/Users/yn804c/Desktop/root.zip!/root/testFolder2/test1.zip");
		byte[] bytes1 = uri1.toURL().openStream().readAllBytes();
		long end = System.currentTimeMillis();
		System.out.println(uri1.getPath());
		System.out.println(uri1.toURL());
		System.out.println("time: " + (end - start));

		return bytes1;
	}

	/*
	 * Supports only single level of archiving (i.e. not supporting archive--> archive --> file).
	 * */
	public byte[] readFileInZipArchive(String zipFileUrl, String pathToFileInsideZip) throws IOException {

		long start = System.currentTimeMillis();

		Map<String, String> env = new HashMap<>();
		env.put("create", "true");
		URI uri = URI.create("jar:file:/C:/Users/yn804c/Desktop/root.zip");
		try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
			Path pathInZipfile = zipfs.getPath("/root/testFolder2/test1.zip");
			System.out.println(pathInZipfile);
			byte[] bytes = Files.readAllBytes(pathInZipfile);
			long end = System.currentTimeMillis();
			System.out.println("time: " + (end - start));
			return bytes;
		}
	} 
}


