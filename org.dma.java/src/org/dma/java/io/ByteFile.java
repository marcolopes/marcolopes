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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.codec.Charsets;
import org.apache.commons.codec.binary.Base64;

public class ByteFile extends CustomFile {

	/** @see CustomFile#CustomFile(File, String...) */
	public ByteFile(File file, String...more) {
		super(file, more);
	}

	/** @see CustomFile#CustomFile(String, String...) */
	public ByteFile(String pathname, String...more) {
		super(pathname, more);
	}

	/** @see CustomFile#CustomFile(File) */
	public ByteFile(File file) {
		super(file);
	}


	/*
	 * Class BufferedInputStream
	 *
	 * A BufferedInputStream adds functionality to another input stream-namely,
	 * the ability to buffer the input and to support the mark and reset methods.
	 * When the BufferedInputStream is created, an internal buffer array is created.
	 * As bytes from the stream are read or skipped, the internal buffer is refilled
	 * as necessary from the contained input stream, many bytes at a time.
	 * The mark operation remembers a point in the input stream and the reset operation
	 * causes all the bytes read since the most recent mark operation to be reread before
	 * new bytes are taken from the contained input stream.
	 */
	public byte[] read() {

		try{// Get the size of the file
			long length=length();

			// File is too large
			if (length > Integer.MAX_VALUE) throw new IOException("File is too large: "+getName());

			byte[] buffer=new byte[(int)length];

			BufferedInputStream in=new BufferedInputStream(asInputStream());

			try{int offset=0;
				int numRead=0;
				// Read in the bytes
				while(offset < length && (numRead=in.read(buffer, offset, buffer.length-offset)) > 0){
					offset+=numRead;
				}// Ensure all the bytes have been read in
				if (offset < buffer.length) throw new IOException("Could not completely read file: "+getName());

			}finally{
				in.close();
			}return buffer;

		}catch(Exception e){
			System.err.println(e);
		}return new byte[0];

	}


	public byte[] readFully() {

		try{long length=length();
			// File is too large
			if (length>Integer.MAX_VALUE) throw new IOException("File is too large: "+getName());

			byte[] buffer=new byte[(int)length];

			DataInputStream in=new DataInputStream(new BufferedInputStream(asInputStream()));

			try{in.readFully(buffer);
			}finally{
				in.close();
			}return buffer;

		}catch(Exception e){
			System.err.println(e);
		}return new byte[0];

	}

	public byte[] encodeBase64() {
		return Base64.encodeBase64(readFully());
	}

	/** @see Charsets#US_ASCII */
	public String encodeBase64String() {
		return Base64.encodeBase64String(readFully());
	}



	/*
	 * Class BufferedOutputStream
	 *
	 * The class implements a buffered output stream.
	 * By setting up such an output stream, an application can write bytes
	 * to the underlying output stream without necessarily causing a call to
	 * the underlying system for each byte written.
	 *
	 */
	public int write(byte[] bytes, boolean append) {

		try{BufferedOutputStream out=new BufferedOutputStream(asOutputStream(append));

			try{out.write(bytes);
			}finally{
				out.close();
			}return bytes.length;

		}catch(Exception e){
			System.err.println(e);
		}return 0;

	}


	public int write(byte[] bytes) {
		return write(bytes, false);
	}


	public int append(byte[] bytes) {
		return write(bytes, true);
	}


	public void append(File file) {
		append(new ByteFile(file).readFully());
	}


	public void append(Collection<File> files) {
		for(File file: files) {
			append(file);
		}
	}


}
