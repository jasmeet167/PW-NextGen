package com.csc.fsg.life.convert;

/**
 *  Manages the converter that will be used when utilizing
 *  <code>CopyObjects</code> to send/receive data from the
 *  host system.
 */
public class ConverterManager
{    
    private static ConverterManager manager = null;
 
    private Converter converter = new CICSConverter();
    
    /**
     * Returns a singleton instance of ConverterManager.
     */
    public static ConverterManager getInstance()
    {
        if (manager == null)
            manager = new ConverterManager();
        
        return manager;
    }

    /**
     * Returns the Converter.
     */
    public Converter getConverter()
    {
        return converter;
    }
    
    /**
     * Sets the Converter.
     */
    public void setConverter(Converter converter)
    {
        this.converter = converter;
    }
}