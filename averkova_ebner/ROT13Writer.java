package averkova_ebner;

import java.io.IOException;
import java.io.Writer;

public class ROT13Writer extends Writer
{
    private Writer writer;

    public ROT13Writer(Writer writer)
    {
        this.writer = writer;
    }

    @Override
    public void close() throws IOException
    {
        if (writer != null)
        {
            writer.close();
        }
    }

    @Override
    public void flush() throws IOException
    {
        if (writer != null)
        {
            writer.flush();
        }
    }

    @Override
    public void write(char[] translatedCode, int offset, int maxLength) throws IOException
    {
        while (maxLength > 0)
        {
            writer.write(rot13(translatedCode[offset++]));
            maxLength--;
        }
    }

    /**
     * ROT13 algorithm
     *
     * @param c character to be encrypted
     * @return character 13 positions moved
     */
    private char rot13(char c)
    {

        if (c >= 'a' && c <= 'm')
            c += 13;
        else if (c >= 'A' && c <= 'M')
            c += 13;
        else if (c >= 'n' && c <= 'z')
            c -= 13;
        else if (c >= 'N' && c <= 'Z')
            c -= 13;
        return c;
    }

}
