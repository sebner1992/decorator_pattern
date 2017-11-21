package averkova_ebner;

import java.io.IOException;
import java.io.Reader;

public class MorseReader extends Reader
{
    private Reader reader;

    public MorseReader(Reader reader)
    {
        this.reader = reader;
    }

    @Override
    public void close() throws IOException
    {
        if (reader != null)
        {
            reader.close();
        }
    }

    /**
     * @param translatedCode
     * @param offset Offset at which to start storing characters
     * @param maxLength Maximum number of characters to read
     * @return number of characters read or -1 if the end of the stream has been reached
     * @throws IOException
     */
    @Override
    public int read(char[] translatedCode, int offset, int maxLength) throws IOException
    {
        int count = 0;
        String codeToCheck = "";

        if (maxLength == 0)
        {
            return 0;
        }

        //is reader ready?
        while (reader.ready())
        {
            final char currentChar = (char) reader.read();

            //morse code finished if space is reached
            if (currentChar == ' ')
            {
                final Character translatedChar = MorseCode.decryptMorse(codeToCheck);
                codeToCheck = ""; //reset code to check

                if (translatedChar != null)
                {
                    translatedCode[offset++] = translatedChar;

                    if (++count == maxLength)
                    {
                        return maxLength; //maxLength reached, no more space
                    }
                }
            }
            //else append morse code
            else
            {
                codeToCheck += currentChar;
            }
        }

        final Character translatedChar = MorseCode.decryptMorse(codeToCheck);

        if (translatedChar != null)
        {
            translatedCode[offset++] = translatedChar;

            return ++count; //return number of chars read
        }

        return -1; //end of stream
    }

}
