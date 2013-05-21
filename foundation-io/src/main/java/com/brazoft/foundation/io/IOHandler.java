package com.brazoft.foundation.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import sun.security.action.GetPropertyAction;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class IOHandler {

    /**
     * @param path
     * @return Returns boolean status
     */
    public static boolean clearDirectory(String path) {
	return IOHandler.clearDirectory(new File(path));
    }

    /**
     * @param path
     * @return Returns boolean status
     */
    public static boolean clearDirectory(File path) {
	boolean execution;

	if (path.isFile()) {
	    path = path.getParentFile();
	}

	path.mkdirs();

	execution = true;

	for (File file : path.listFiles()) {
	    execution = execution && file.delete();
	}

	return execution;
    }

    /**
     * @param path
     * @return Returns if the path exists
     */
    public static boolean exist(String path) {
	return new File(path).exists();
    }

    /**
     * @param path
     * @return Returns a new File from path
     * @throws IOException
     */
    public static File newFile(String path)
	throws IOException {
	File file;

	file = new File(path);
	file.createNewFile();

	return file;
    }

    /**
     * @param prefix
     * @return Returns a new temporary File using prefix
     * @throws IOException
     */
    public static File newTemp(String prefix)
	throws IOException {
	return File.createTempFile(prefix, null);
    }

    /**
     * Create a new Directory from path
     * 
     * @param path
     */
    public static void newDirectory(String path) {
	IOHandler.newDirectory(new File(path));
    }

    /**
     * Create a new Directory from File
     * 
     * @param path
     */
    public static void newDirectory(File path) {
	path.mkdirs();
    }

    /**
     * @param path
     * @return Returns a list of available files in the path
     */
    public static File[] listFiles(String path) {
	File f;

	f = new File(path);

	return f.listFiles(new FileFilter());
    }

    /**
     * @param path
     * @return Returns a list of available directories in the path
     */
    public static File[] listDirectories(String path) {
	File f;

	f = new File(path);

	return f.listFiles(new DirectoryFilter());
    }

    /**
     * @param directory
     * @return Returns if directory path is empty
     */
    public static boolean isEmpty(String directory) {
	return IOHandler.isEmpty(new File(directory));
    }

    /**
     * @param directory
     * @return Returns if directory is empty
     */
    public static boolean isEmpty(File directory) {
	return directory.list().length == 0;
    }

    /**
     * @param file
     * @return Returns String literal readed from File
     * @throws IOException
     */
    public static byte[] read(File file)
	throws IOException {
	return IOHandler.read(new FileInputStream(file));
    }

    /**
     * @param is
     * @return Returns byte[] readed from InputStream
     * @throws IOException
     */
    public static byte[] read(InputStream is)
	throws IOException {
	return IOHandler.read(is, true);
    }

    /**
     * @param is
     * @param close
     * @return Returns byte[] readed from InputStream
     * @throws IOException
     */
    public static byte[] read(InputStream is, boolean close)
	throws IOException {
	BufferedInputStream bis;
	byte[] data;

	bis = new BufferedInputStream(is);

	try {
	    data = new byte[bis.available()];
	    bis.read(data);
	} finally {
	    if (close) {
		bis.close();
	    }
	}

	return data;
    }

    /**
     * @param is
     * @return Returns byte[] as char readed from InputStream
     * @throws IOException
     */
    public static byte[] readSerial(InputStream is)
	throws IOException {
	ByteArrayOutputStream output;
	int readed;

	output = new ByteArrayOutputStream();

	while ((readed = is.read()) > -1) {
	    output.write(readed);
	}

	return output.toByteArray();
    }

    /**
     * @param is
     * @return Returns byte[] as char readed from InputStream
     * @throws IOException
     */
    public static byte[] readSerialAsChar(InputStream is)
	throws IOException {
	int character;
	StringBuffer buffer;

	buffer = new StringBuffer();

	while ((character = is.read()) > -1) {
	    buffer.append((char)character);
	}

	return buffer.toString().getBytes();
    }

    /**
     * @param path
     * @param data
     * @throws IOException
     */
    public static void append(String path, byte[] data)
	throws IOException {
	IOHandler.append(new File(path), data);
    }

    /**
     * @param file
     * @param data
     * @throws IOException
     */
    public static void append(File file, byte[] data)
	throws IOException {
	RandomAccessFile output;

	output = new RandomAccessFile(file, "rw");

	try {
	    output.seek(output.length());
	    output.write(data);
	    output.writeBytes(IOHandler.getLineSeparator());
	} finally {
	    output.close();
	}
    }

    /**
     * Write content in a file
     * 
     * @param path
     * @param data
     * @throws IOException
     */
    public static void write(String path, byte[] data)
	throws IOException {
	IOHandler.write(new FileOutputStream(path), data);
    }

    /**
     * Write content in a file
     * 
     * @param f
     * @param data
     * @throws IOException
     */
    public static void write(File f, byte[] data)
	throws IOException {
	IOHandler.write(new FileOutputStream(f), data);
    }

    /**
     * Write content in a OutputStream
     * 
     * @param out
     * @param data
     * @throws IOException
     */
    public static void write(OutputStream out, byte[] data)
	throws IOException {
	try {
	    out.write(data);
	    out.flush();
	} finally {
	    out.close();
	}
    }

    /**
     * @param f
     * @param is
     * @throws IOException
     */
    public static void write(File f, InputStream is)
	throws IOException {
	FileOutputStream output;

	output = new FileOutputStream(f);
	try {
	    IOHandler.write(output, is);
	    output.flush();
	} finally {
	    output.close();
	}
    }

    /**
     * @param is
     * @param output
     * @throws IOException
     */
    public static void write(OutputStream output, InputStream is)
	throws IOException {
	byte[] buffer;
	int len;

	buffer = new byte[1024];
	while ((len = is.read(buffer)) > -1) {
	    output.write(buffer, 0, len);
	}
    }

    /**
     * Serialize object into File
     * 
     * @param o
     * @param f
     * @throws IOException
     */
    public static void writeObject(Object o, File f)
	throws IOException {
	IOHandler.writeObject(o, new FileOutputStream(f));
    }

    /**
     * Serialize object into File
     * 
     * @param o
     * @return Returns byte[]
     * @throws IOException
     */
    public static byte[] writeObject(Object o)
	throws IOException {
	ByteArrayOutputStream baos;

	baos = new ByteArrayOutputStream();

	IOHandler.writeObject(o, baos);

	return baos.toByteArray();
    }

    /**
     * @param o
     * @param output
     * @throws IOException
     */
    public static void writeObject(Object o, OutputStream output)
	throws IOException {
	ObjectOutputStream os;

	os = new ObjectOutputStream(output);

	try {
	    os.writeObject(o);
	    os.flush();
	} catch (Exception e) {
	    throw new IOException(e.getMessage());
	} finally {
	    os.close();
	}
    }

    /**
     * @param f
     * @return Returns Object serialized in a File
     * @throws IOException
     */
    public static Object readObject(File f)
	throws IOException {
	return IOHandler.readObject(new FileInputStream(f));
    }

    /**
     * @param input
     * @return Returns Object serialized in the InputStream
     * @throws IOException
     */
    public static Object readObject(InputStream input)
	throws IOException {
	ObjectInputStream is;

	is = new ObjectInputStream(input);

	try {
	    return is.readObject();
	} catch (Exception e) {
	    throw new IOException(e.getMessage());
	} finally {
	    is.close();
	}
    }

    /**
     * @param input
     * @param output
     * @throws IOException
     */
    public static void gzip(File input, File output)
	throws IOException {
	GZIPOutputStream gzip;

	gzip = new GZIPOutputStream(new FileOutputStream(output));

	try {
	    gzip.write(IOHandler.read(input));
	    gzip.flush();
	} finally {
	    gzip.close();
	}
    }

    /**
     * @param input
     * @param output
     * @throws IOException
     */
    public static void gunzip(File input, File output)
	throws IOException {
	IOHandler.write(output, IOHandler.gunzip(input));
    }

    /**
     * @param input
     * @return Returns byte[]
     * @throws IOException
     */
    public static byte[] gunzip(File input)
	throws IOException {
	GZIPInputStream gzip;

	gzip = new GZIPInputStream(new FileInputStream(input));

	try {
	    return IOHandler.readSerial(gzip);
	} finally {
	    gzip.close();
	}
    }

    /**
     * @param input
     * @param output
     * @throws IOException
     */
    public static void zip(File input, File output)
	throws IOException {
	ZipOutputStream zip;

	zip = new ZipOutputStream(new FileOutputStream(output));
	try {
	    zip.putNextEntry(new ZipEntry(input.getName()));
	    zip.write(IOHandler.read(input));
	    zip.closeEntry();
	    zip.flush();
	} finally {
	    zip.close();
	}
    }

    /**
     * @param input
     * @param output
     * @throws IOException
     */
    public static void unzip(File input, File output)
	throws IOException {
	ZipInputStream zip;

	zip = new ZipInputStream(new FileInputStream(input));
	try {
	    zip.getNextEntry();
	    IOHandler.write(output, zip);
	} finally {
	    zip.close();
	}
    }

    /**
     * @param root
     * @param output
     * @param files
     * @throws IOException
     */
    public static void zip(File output, File root, File[] files)
	throws IOException {
	ZipOutputStream zip;

	zip = new ZipOutputStream(new FileOutputStream(output));

	try {
	    for (File file : files) {
		zip.putNextEntry(new ZipEntry(IOHandler.getFileName(root, file)));
		zip.write(IOHandler.read(file));
		zip.closeEntry();
	    }
	    zip.flush();
	} finally {
	    zip.close();
	}
    }

    /**
     * @return Returns File
     */
    public static File getTempDirectory() {
	return new File(System.getProperty("java.io.tmpdir"));
    }

    /**
     * @param root
     * @param file
     * @return Returns file path from root
     */
    protected static String getFileName(File root, File file) {
	return file.getPath().replace(root.getPath() + File.separator, "");
    }

    /**
     * @param root
     * @param zip
     * @throws IOException
     */
    public static void unzipAll(File root, File zip)
	throws IOException {
	ZipInputStream input;
	ZipEntry entry;
	File output;

	input = new ZipInputStream(new FileInputStream(zip));

	if (!root.isDirectory()) {
	    throw new RuntimeException("Root file is not a directory. You could not unzip inside its root");
	}

	while ((entry = input.getNextEntry()) != null) {
	    output = new File(root, entry.getName());

	    if (entry.isDirectory()) {
		output.mkdirs();
	    } else {
		if (output.getParent() != null) {
		    output.getParentFile().mkdirs();
		}
		output.createNewFile();
		IOHandler.write(output, input);
	    }
	}
    }

    /**
     * @param file
     * @return Returns checksum to file
     * @throws IOException
     */
    public static long generateChecksum(File file)
	throws IOException {
	CheckedInputStream input;

	input = new CheckedInputStream(new FileInputStream(file), new Adler32());

	IOHandler.read(input);

	return input.getChecksum().getValue();
    }

    /**
     * @param file
     * @return Returns MD5 Hash from file
     * @throws IOException
     */
    public static long generateMD5(File file)
	throws IOException {
	MessageDigest message;
	BigInteger hash;

	try {
	    message = MessageDigest.getInstance("MD5");
	    message.update(IOHandler.read(file));
	    hash = new BigInteger(1, message.digest());

	    return hash.longValue();
	} catch (NoSuchAlgorithmException e) {
	    throw new IOException(e.getMessage());
	}
    }

    /**
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void move(File source, File destination)
	throws IOException {
	IOHandler.copy(source, destination);
	source.delete();
    }

    /**
     * @param file
     * @return Returns number of lines in a file
     * @throws IOException
     */
    public static int getLines(File file)
	throws IOException {
	RandomAccessFile access;
	LineNumberReader reader;
	long lines;

	access = new RandomAccessFile(file, "r");
	reader = new LineNumberReader(new FileReader(file));

	try {
	    lines = access.length();
	} finally {
	    access.close();
	}

	try {
	    reader.skip(lines);

	    return reader.getLineNumber();
	} finally {
	    reader.close();
	}
    }

    /**
     * @param source
     * @param destination
     * @throws IOException
     */
    public static void copy(File source, File destination)
	throws IOException {
	FileChannel read;
	FileChannel copy;

	read = new FileInputStream(source).getChannel();
	copy = new FileOutputStream(destination).getChannel();

	try {
	    copy.transferFrom(read, 0, read.size());
	} finally {
	    read.close();
	    copy.close();
	}
    }

    /**
     * @return Returns line separator
     */
    public static String getLineSeparator() {
	return (String)java.security.AccessController.doPrivileged(new GetPropertyAction("line.separator"));
    }
}