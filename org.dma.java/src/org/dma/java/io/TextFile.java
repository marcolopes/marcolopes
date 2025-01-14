/*******************************************************************************
 * Copyright 2008-2024 Marco Lopes (marcolopespt@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors
 * Marco Lopes (marcolopespt@gmail.com)
 *******************************************************************************/
package org.dma.java.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collection;

import org.dma.java.input.FieldFormat.SEPARATOR;

public class TextFile extends CustomFile {

	public final Charset charset;

	/** Uses JAVA DEFAULT charset */
	public TextFile(File file, String...more) {
		this(Charset.defaultCharset(), file, more);
	}

	/** @see CustomFile#CustomFile(File, String...) */
	public TextFile(Charset charset, File file, String...more) {
		super(file, more);
		this.charset=charset;
	}

	/** Uses JAVA DEFAULT charset */
	public TextFile(String pathname, String...more) {
		this(Charset.defaultCharset(), pathname, more);
	}

	/** @see CustomFile#CustomFile(String, String...) */
	public TextFile(Charset charset, String pathname, String...more) {
		super(pathname, more);
		this.charset=charset;
	}

	/** Uses JAVA DEFAULT charset */
	public TextFile(File file) {
		this(Charset.defaultCharset(), file);
	}

	/** @see CustomFile#CustomFile(File) */
	public TextFile(Charset charset, File file) {
		super(file);
		this.charset=charset;
	}


	/*
	 * Class FileReader
	 *
	 * Convenience class for reading character files.
	 *
	 * The constructors of this class assume that the default character encoding and the
	 * default byte-buffer size are appropriate. To specify these values yourself,
	 * construct an InputStreamReader on a FileInputStream.
	 *
	 * FileReader is meant for reading streams of characters.
	 * For reading streams of raw bytes, consider using a FileInputStream.
	 *
	 */
	/** WARNING: Uses JAVA DEFAULT charset */
	@Deprecated
	public String readText() {

		StringBuffer buffer=new StringBuffer(STRING_BUFFER_LENGTH);

		try{BufferedReader in=new BufferedReader(new FileReader(this));

			try{String line;
				while((line=in.readLine())!=null){
					buffer.append(buffer.length()==0 ? line : SEPARATOR.LINE.value+line);
				}
			}finally{
				in.close();
			}

		}catch(Exception e){
			System.err.println(e);
		}return buffer.toString();

	}


	/** WARNING: Uses JAVA DEFAULT charset */
	@Deprecated
	public String readText(int lines) {

		StringBuffer buffer=new StringBuffer(STRING_BUFFER_LENGTH);

		try{BufferedReader in=new BufferedReader(new FileReader(this));

			try{String line;
				while((line=in.readLine())!=null && lines--!=0){
					buffer.append(buffer.length()==0 ? line : SEPARATOR.LINE.value+line);
				}
			}finally{
				in.close();
			}

		}catch(Exception e){
			System.err.println(e);
		}return buffer.toString();

	}



	/*
	 * Class FileWriter
	 *
	 * Convenience class for writing character files.
	 *
	 * The constructors of this class assume that the default character encoding and
	 * the default byte-buffer size are acceptable. To specify these values yourself,
	 * construct an OutputStreamWriter on a FileOutputStream.
	 *
	 * Whether or not a file is available or may be created depends upon the underlying
	 * platform. Some platforms, in particular, allow a file to be opened for writing
	 * by only one FileWriter (or other file-writing object) at a time.
	 * In such situations the constructors in this class will fail if the file involved
	 * is already open.
	 *
	 * FileWriter is meant for writing streams of characters. For writing streams of raw bytes,
	 * consider using a FileOutputStream.
	 *
	 */
	/** WARNING: Uses JAVA DEFAULT charset */
	@Deprecated
	public boolean writeText(String text) {

		try{BufferedWriter out=new BufferedWriter(new FileWriter(this));

			try{out.write(text);
			}finally{
				out.close();
			}return true;

		}catch(Exception e){
			System.err.println(e);
		}return false;

	}



	/*
	 * Class InputStreamReader
	 *
	 * An InputStreamReader is a bridge from byte streams to character streams:
	 * It reads bytes and decodes them into characters using a specified charset.
	 * The charset that it uses may be specified by name or may be given explicitly,
	 * or the platform's default charset may be accepted.
	 *
	 * Each invocation of one of an InputStreamReader's read() methods may cause one
	 * or more bytes to be read from the underlying byte-input stream. To enable the
	 * efficient conversion of bytes to characters, more bytes may be read ahead from
	 * the underlying stream than are necessary to satisfy the current read operation.
	 *
	 * For top efficiency, consider wrapping an InputStreamReader within a BufferedReader.
	 * For example:
	 * BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
	 *
	 */
	/** Reads text lines from file */
	public String read(int lines) {

		StringBuffer buffer=new StringBuffer(STRING_BUFFER_LENGTH);

		try{BufferedReader in=new BufferedReader(
					new InputStreamReader(asInputStream(), charset));

			try{String line;
				while((line=in.readLine())!=null && lines--!=0){
					buffer.append(buffer.length()==0 ? line : SEPARATOR.LINE.value+line);
				}
			}finally{
				in.close();
			}

		}catch(Exception e){
			System.err.println(e);
		}return buffer.toString();

	}


	/** Reads text from file */
	public String read() {
		return read(-1);
	}



	/*
	 * Class OutputStreamWriter
	 *
	 * An OutputStreamWriter is a bridge from character streams to byte streams:
	 *
	 * Characters written to it are encoded into bytes using a specified charset.
	 * The charset that it uses may be specified by name or may be given explicitly,
	 * or the platform's default charset may be accepted.
	 *
	 * Each invocation of a write() method causes the encoding converter to be invoked
	 * on the given character(s). The resulting bytes are accumulated in a buffer
	 * before being written to the underlying output stream.
	 * The size of this buffer may be specified, but by default it is large enough
	 * for most purposes.
	 *
	 * Note that the characters passed to the write() methods are not buffered.
	 * For top efficiency, consider wrapping an OutputStreamWriter within a BufferedWriter
	 * so as to avoid frequent converter invocations.
	 * For example:
	 * Writer out=new BufferedWriter(new OutputStreamWriter(System.out));
	 *
	 */
	/** Writes text to file */
	public boolean write(String text, boolean append) {

		if (!text.isEmpty()) try{

			BufferedWriter out=new BufferedWriter(
					new OutputStreamWriter(asOutputStream(append), charset));

			try{out.write(text);
			}finally{
				out.close();
			}return true;

		}catch(Exception e){
			System.err.println(e);
		}return false;

	}


	/** Writes text to file */
	public boolean write(String text) {
		return write(text, false);
	}


	/** Appends text to file */
	public boolean append(String text) {
		return write(text, true);
	}


	public void append(File file) {
		append(new TextFile(file).read());
	}


	public void append(Collection<File> files) {
		for(File file: files) {
			append(file);
		}
	}


}
