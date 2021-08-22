/**
 * <p>Copyright: Copyright (c) 2019</p>
 *
 * <h3>License</h3>
 *
 * Copyright (c) 2019 by Jonatan Gomez-Perdomo. <br>
 * All rights reserved. <br>
 *
 * <p>Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * <ul>
 * <li> Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * <li> Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * <li> Neither the name of the copyright owners, their employers, nor the
 * names of its contributors may be used to endorse or promote products
 * derived from this software without specific prior written permission.
 * </ul>
 * <p>THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 *
 *
 * @author <A HREF="http://disi.unal.edu.co/profesores/jgomezpe"> Jonatan Gomez-Perdomo </A>
 * (E-mail: <A HREF="mailto:jgomezpe@unal.edu.co">jgomezpe@unal.edu.co</A> )
 * @version 1.0
 */
package speco.bit;
import java.util.Iterator;

import kopii.Copyable;

/**
 * <p>Title: BitArray</p>
 * 
 * <p>Description: An array of bits</p>
 */
public class BitArray implements Iterable<Boolean>, Copyable{
	/**
	 * Integer array used to store the bits
	 */
	protected int[] data;
 
	/**
	 * The number of bits in the bit array
	 */
	protected int n = 0;
  
	/**
	 * Constructor: Creates a bit array using the int array
	 * @param data The bits (int array) that will conform the bit array
	 * @param n The size of the bit array
	 */
	public BitArray(int[] data, int n){
		this.data = data;
		this.n = n;
	}
	
	/**
	 * Creates a bit array with <i>n</i> bits all set to <i>false</i>
	 * @param n Size of the bitarray
	 */
	public BitArray( int n ) {
		this.n = n;
		int m = getIndex(n) + 1;
		data = new int[m];
	}

	/**
	 * Constructor: Creates a bit array using the boolean values given in the array
	 * @param source The bits that will conform the bit array
	 */
	public BitArray(boolean[] source){
		this(source.length);
		for (int i = 0; i < n; i++) set(i, source[i]);
	}

	/**
	 * Constructor: Creates a bit array using the boolean values given in the string
	 * @param source The String with the bits that will conform the bit array (<i>'1'=true, '0'=false</i>
	 */
	public BitArray(String source) {
		this( source.length() );
		for (int i = 0; i < n; i++) set(i, (source.charAt(i) == '1'));
	}

	/**
	 * Returns the buffer position (the integer that contains the bit) of an specific bit
	 * <p>  m DIV INTEGER_SIZE </P>
	 * @param m The bit index
	 * @return The buffer position of an specific bit
	 */
	protected int getIndex (int  m){ return (m >>> speco.integer.IntUtil.DIV_MASK); }

	/**
	 * Returns the position of a specific bit in the integer that contains it.
	 * <p>  m MOD INTEGER_SIZE </p>
	 * @param m The bit index
	 * @return The position of a specific bit in the integer that contains it
	 */
	protected int getBit(int m){ return (m & speco.integer.IntUtil.MOD_MASK); }

	/**
	 * Sets a bit to a given value
	 * @param i Bit position
	 * @param v Value to set
	 * @return <i>true</i> if the bit could be set, <i>false</i> otherwise 
	 */
	public boolean set(int i, Boolean v) { return set(i,(boolean)v); }
	
	/**
	 * Sets a bit to a given value
	 * @param i Bit position
	 * @param v Value to set
	 * @return <i>true</i> if the bit could be set, <i>false</i> otherwise 
	 */
	public boolean set(int i, boolean v) {
		int m = getIndex(i);
		int p = getBit(i);
		int vmask = (speco.integer.IntUtil.HIGHEST_BIT >>> p);
		int dmask = vmask & data[m];
		if(v){ if (dmask == 0) data[m] |= vmask; }
		else { if (dmask != 0) data[m] ^= vmask;   }
		return true;
	}

	/**
	 * Returns the boolean at the given position
	 * @param i Bit position
	 * @return Boolean at position <i>i</i>
	 */
	public Boolean get(int i){
		int m = getIndex(i);
		int p = getBit(i);
		return (((speco.integer.IntUtil.HIGHEST_BIT >>> p) & data[m]) != 0);
	}

	/**
	 * Returns a sub bit array of the bit array starting from the position start until the end of the bit array.
	 * <p>  A = 1000111001</p>
	 * <p>  A.subArray( 4 ) = 111001</p>
	 * <p>  A.subArray( 0 ) = 1000111001</p>
	 * <p>  A.subArray( 10 ) = empty bit array</p>
	 * @param start The start position
	 * @return A sub bit array of the bit array starting from the position start until the end of the bit array.
	 */
	public BitArray subArray(int start) {
		BitArray subArray = (BitArray)new BitArray(this.data.clone(), this.n);
		subArray.leftShift(start);
		subArray.n -= start;
		if (subArray.n < 0) { subArray.n = 0; }
		return subArray;
	}

	/**
	 * Returns the sub bit array of the bit array starting at the position start and the previous to the position end-1.
	 * If the end position is greater than the last position of the array then the function will return only the last bits.
	 * <p>  A = 1000111001</p>
	 * <p>  A.subArray( 4, 7 ) = 111</p>
	 * <p>  A.subArray( 0, 4 ) = 1000</p>
	 * <p>  A.subArray( 7, 11 ) = 001</p>
	 * @param start The start position of the substring
	 * @param end The end position + 1 of the subarray
	 * @return The sub bit array of the bit array starting at the position start and the previous to the position end-1.
	 */
	public BitArray subArray(int start, int end) {
		int length = end - start;
		BitArray subArray = subArray(start);
		if (subArray.n > length) { subArray.n = length; };
		subArray.rightSetToZero(subArray.n);
		return subArray;
	}

	/**
	 * Shifts the bit array to the left in k bits. The remaining bits will be set to zero.
	 * <p>  A = 1010011011</p>
	 * <p>  A.left_shift( 3 ) = 0011011000</p>
	 * @param k The number of bits to be shifted
	 */
	public void leftShift(int k) {
		if (data != null) {
			rightSetToZero(n);
			int startindex = getIndex(k);
			k = getBit(k);
			if (k > 0) {
				int supk = speco.integer.IntUtil.INTEGER_SIZE - k;
				int m = data.length - 1;
				int j = 0;
				for (int i = startindex; i < m; i++) {
					data[j] = (data[i] << k) | (data[i + 1] >>> supk);
					j++;
				}
				data[j] = (data[m] << k);
				for (int i = j + 1; i < m; i++) { data[i] = 0; }
			} else {
				int j = 0;
				for (int i = startindex; i < data.length; i++) {
					data[j] = data[i];
					j++;
				}
				for (int i = j; i < data.length; i++) { data[i] = 0; }
			}
		}
	}

	/**
	 * Shifts the bit array to the right in k bits. The remaining bits will be set to zero
	 * <p>  A = 1010011011</p>
	 * <p>  A.right_shift( 3 ) = 0001010011</p>
	 * @param k The number of bits to be shifted
	 */
	public void rightShift(int k) {
		if (data != null) {
			rightSetToZero(n);
			int startindex = getIndex(k);
			k = getBit(k);
			if (k > 0) {
				int supk = speco.integer.IntUtil.INTEGER_SIZE - k;
				//int m = data.length - 1;
				int j = data.length - 1 - startindex;
				for (int i = data.length - 1; i > startindex; i--) {
					data[i] = (data[j] >>> k) | (data[j - 1] << supk);
					j--;
				}
				data[startindex] = (data[0] >>> k);
				for (int i = 0; i < startindex; i++) { data[i] = 0; }
			} else {
				int j = data.length - 1 - startindex;
				for (int i = data.length - 1; i >= startindex; i--) {
					data[i] = data[j];
					j--;
				}
				for (int i = 0; i < startindex; i++) { data[i] = 0; }
			}
		}
	}

	/**
	 * Add the given bit array to the end of the bit array.
	 * <p>  A = 1001</p>
	 * <p>  B = 11001</p>
	 * <p>  A.add( B ) = 100111001</p>
	 * @param source The bit array to be added to the end
	 */
	public void add(BitArray source) {
		BitArray newArray = new BitArray (n + source.n);
		newArray.or(source);
		newArray.rightShift(n);
		newArray.or(this);
		data = newArray.data;
		n = newArray.n;
	}

	/**
	 * Add the given bit to the end of the bit array
	 * @param v The bit to be added
	 */
	public void add(boolean v) {
		if (data.length * speco.integer.IntUtil.INTEGER_SIZE == n) {
			int[] newdata = new int[data.length + 1];
			for (int i = 0; i < data.length; i++) newdata[i] = data[i];
			if (v)newdata[data.length] = speco.integer.IntUtil.HIGHEST_BIT;
			else newdata[data.length] = 0;
			data = newdata;
		} else  set(n, v);
		n++;
	}

	/**
	 * Removes the last bit in the bit array
	 */
	public void del(){ del(1); }

	/**
	 * Removes the last k bits from the bit array
	 * @param k the number of bits to be removed
	 */
	public void del(int k) {
		n -= k;
		if (n > 0) {
			int m = getIndex(n);
			if (m + 1 < data.length) {
				int[] newdata = new int[m + 1];
				for (int i = 0; i <= m; i++) newdata[i] = data[i];
				data = newdata;
			}
		} else {
			n = 0;
			data = new int[1];
		}
	}

	/**
	 * Returns the number of bits in the array
	 * @return The number of bits in the array
	 */
	public int size() { return n; }

	/**
	 * Sets all the bits in the array to zero
	 */
	public void zero() { for (int i = 0; i < data.length; i++) data[i] = 0; }

	/**
	 * Set the last bits to zero starting to the given position.
	 * <p> A = 1000111011</p>
	 * <p> A.rightSetToZero( 6 ) = 1000110000</p>
	 * @param start The start position to be set to zero
	 */
	public void rightSetToZero(int start) {
		int m = getIndex(start);
		if (data != null && 0 <= m && m < data.length) {
			int r = getBit(start);
			int mask;
			if (r > 0) mask = speco.integer.IntUtil.ONE_BITS << (speco.integer.IntUtil.INTEGER_SIZE - r);
			else mask = 0;
			data[m] &= mask;
			for (int i = m + 1; i < data.length; i++) data[i] = 0;
		}
	}

	/**
	 * Set the last bits to one starting to the given position.
	 * <p> A = 1000110011</p>
	 * <p> A.rightSetToOne( 6 ) = 1000111111</p>
	 * @param start The start position to be set to one
	 */
	public void rightSetToOne(int start) {
		int m = getIndex(start);
		if (0 <= m && m < data.length) {
			int r = getBit(start);
			int mask = speco.integer.IntUtil.ONE_BITS >>> r;
			data[m] |= mask;
			for (int i = m + 1; i < data.length; i++)
				data[i] = speco.integer.IntUtil.ONE_BITS;
		}
	}

	/**
	 * Set the first bits to zero.
	 * <p> A = 1000111011</p>
	 * <p> A.leftSetToZero( 6 ) = 0000001011</p>
	 * @param end The number of bits to be set to zero
	 */
	public void leftSetToZero(int end) {
		int m = getIndex(end);
		if (0 <= m && m < data.length) {
			int r = getBit(end);
			int mask = speco.integer.IntUtil.ONE_BITS >>> r;
			data[m] &= mask;
			for (int i = 0; i < m; i++)  data[i] = 0;
		}
	}

	/**
	 * Set the first bits to one.
	 * <p> A = 1000111011</p>
	 * <p> A.leftSetToOne( 6 ) = 1111111011</p>
	 * @param end The number of bits to be set to one
	 */
	public void leftSetToOne(int end) {
		int m = getIndex(end);
		if (0 <= m && m < data.length) {
			int r = getBit(end);
			int mask = speco.integer.IntUtil.ONE_BITS << (speco.integer.IntUtil.INTEGER_SIZE - r);
			data[m] |= mask;
			for (int i = 0; i < m; i++) data[i] = speco.integer.IntUtil.ONE_BITS;
		}
	}

	/**
	 * Performs the and operator between the bit array and the given bit array.
	 * <p>  A = 10011001011</p>
	 * <p>  B = 0101010101</p>
	 * <p>  A.and( B ) = 00010001011</p>
	 * <p>  B.and( A ) = 0001000101</p>
	 * @param arg2 The array used to perform the and operator
	 */
	public void and(BitArray arg2) {
		arg2.rightSetToOne(arg2.n);
		int m = arg2.data.length;
		if (data.length < m) { m = data.length; }
		for (int i = 0; i < m; i++)  data[i] &= arg2.data[i];
	}

	/**
	 * Performs the or operator between the bit array and the given bit array.
	 * <p>  A = 10011001011</p>
	 * <p>  B = 0101010101</p>
	 * <p>  A.or( B ) = 11011101011</p>
	 * <p>  B.or( A ) = 1101110101</p>
	 * @param arg2 The array used to perform the or operator
	 */
	public void or(BitArray arg2) {
		arg2.rightSetToZero(arg2.n);
		int m = arg2.data.length;
		if (data.length < m) { m = data.length; }
		for (int i = 0; i < m; i++) data[i] |= arg2.data[i];
	}

	/**
	 * Performs the xor operator between the bit array and the given bit array.
	 * <p>  A = 10011001011</p>
	 * <p>  B = 0101010101</p>
	 * <p>  A.xor( B ) = 11001100001</p>
	 * <p>  B.xor( A ) = 1100110000</p>
	 * @param arg2 The array used to perform the xor operator
	 */
	public void xor(BitArray arg2) {
		int m = arg2.data.length;
		if (data.length < m) { m = data.length; }
		for (int i = 0; i < m; i++)  data[i] ^= arg2.data[i];
	}

	/**
	 * Flips all the bits in the bit array
	 * <p>  A = 10001001</p>
	 * <p>  A.not() = 01110110</p>
	 */
	public void not() {
		for (int i = 0; i < data.length; i++)
			data[i] ^= speco.integer.IntUtil.ONE_BITS;
	}

	/**
	 * Flips the bit given
	 * <p> A = 10011011</p>
	 * <p> A.not( 3 ) = 10001011</p>
	 * <p> A.not( 5 ) = 10011111</p>
	 * @param bit apply not
	 */
	public void not(int bit) {
		int m = getIndex(bit);
		int p = getBit(bit);
		data[m] ^= (speco.integer.IntUtil.HIGHEST_BIT >>> p);
	}

	/**
	 * Converts the bit array to a string
	 * <p>  A = 1000111</p>
	 * <p>  A.toString() = "1000111"</p>
	 * @return The String representation of the bit array
	 */
	public String toString() {
		String text = "";
		try{ 
			for (int i = 0; i < n; i++) if (get(i)){ text += "1"; } else{ text += "0"; } 
		}catch(Exception e){}	
		return text;
	}

	/**
	 * Compares a Array with other Object
	 * @param obj The bitarray to be compared with this bitarray
	 * @return <i>true</i> if the object is a bitarray with the same information than the bit array
	 * <i>false</i> otherwise
	 */
	public boolean equals(Object obj) {
		boolean flag = (obj instanceof BitArray);
		if (flag) {
			BitArray other = (BitArray) obj;
			int s = size();
			flag = (other.size() == s);
			if (flag && s > 0) {
				for (int i = 0; i < data.length - 1 && flag; i++) flag = (data[i] == other.data[i]);
				if(flag){
					try{
						for (int i = (data.length - 1) * speco.integer.IntUtil.INTEGER_SIZE; i < s && flag; i++)	flag = (get(i) == other.get(i));
					}catch(Exception e ){}
				}
			}
		}
		return flag;
	}

	// Special methods and attributes
	/**
	 * If the Array is using Gray code for representing the integer numbers
	 */
	public static boolean useGrayCode = false;

	/**
	 * Returns the integer at the given buffer position.
	 * @param i The buffer position
	 * @return The integer at the given buffer position.
	 */
	public int getInt (int i) {
		int x = data[i];
		if (useGrayCode) { x = speco.integer.IntUtil.grayToBinary(x); }
		return x;
	}

	/**
	 * Sets the integer value at the given buffer position.
	 * @param i The buffer position
	 * @param value The new integer for the given buffer position.
	 */
	public void setInt (int i, int value) {
		if (useGrayCode) data[i] = speco.integer.IntUtil.binaryToGray(value);
		else data[i] = value;
	}

	/**
	 * Gets the array which stores the bits
	 * @return The data of the bits array
	 */
	public int[] getData() { return data; }

	/**
	 * Sets the data of the bits array
	 * @param data The data of the bits array
	 */
	public void setData(int[] data) { this.data = data; }

	/**
	 * Removes all bits from the array
	 */
	public void clear() { n=0; }

	/**
	 * Creates an iterator for the bitarray
	 * @return An iterator for the bitarray
	 */
	@Override
	public Iterator<Boolean> iterator() {
		return new Iterator<Boolean>() {
			protected int pos=0;
			@Override
			public boolean hasNext(){ return pos<size(); }

			@Override
			public Boolean next() { return get(pos++); }
		};
	}
  
	/**
	 * Removes the bit at the given position
	 * @param index Bit to be removed
	 * @return <i>true</i> if the bit could be removed, <i>false</i> otherwise
	 */
	public boolean remove(int index) {
		del(index);
		return true;
	}

	/**
	 * Creates a bit array of the given length
	 * @param size Length of the Bit array
	 * @return A bit array of the given length
	 */
	public BitArray instance(int size) { return new BitArray(size); }

	/**
	 * Creates a clone of the Bit array
	 * @return A clone of the Bit array
	 */
	@Override
	public Copyable copy() { return new BitArray((int[])data.clone(), n); }
}