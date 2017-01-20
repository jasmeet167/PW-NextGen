package com.csc.fsg.life.convert;

import java.io.Serializable;

/* Modifications: T0160, T0175 */

/**
 * Generic interface for ServiceManager converter.  Converters are responsible
 * for converting data to/from the host system format.
 */
public interface Converter
    extends Serializable
{
    /** Data types */
    public static final int ALPHANUM = 0;
    public static final int NUMERIC = 1;
    public static final int SNUMERIC = 2;
    public static final int BINARY = 3;
    public static final int SBINARY = 4;
    public static final int PACKED = 5;
    public static final int SPACKED = 6;
    public static final int POINTER = 7;
    public static final int MIXEDCASE = 8;
    
    /**
     * Convert the data to the appropriate host system format.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param scale     scale/precision
     * @param data      data to be converted
     * @return The data converted to host format.
     */
    public byte[] convertToHost(int datatype, int length, int scale, String data);
    
    /**
     * Convert the data to the appropriate host system format.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param data      data to be converted
     * @return The data converted to host format.
     */
    public byte[] convertToHost(int datatype, int length, char[] data);

    /**
     * Convert the data from the host system format.
     * 
     * @param datatype  type of data
     * @param length    length of the data
     * @param scale     scale/precision
     * @param data      data to be converted
     * @return The data converted to server format (ASCII / UNICODE)
     */
    public String convertFromHost(int datatype, int length, int scale, byte[] data);
}
