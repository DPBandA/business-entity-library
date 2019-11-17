/*
LabelPrint - A general purpose energy label printing application 
Copyright (C) 2018  D P Bennett & Associates Limited

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.

You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <https://www.gnu.org/licenses/>.

Email: info@dpbennett.com.jm
 */
package jm.com.dpbennett.business.entity.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Security {

    public static String KEY = "9k#y@&K8$3d!4^8a";

    public static String encrypt(String plainText) {
        try {
            Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encrypted = cipher.doFinal(plainText.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);

        } catch (InvalidKeyException | NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException e) {
            System.out.println(e);

            return "";
        }

    }

    public static String decrypt(String encryptedText) {
        String decryptedText = "";

        try {
            Key aesKey = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");

            cipher.init(Cipher.DECRYPT_MODE, aesKey);

            byte[] enc = Base64.getDecoder().decode(encryptedText);

            decryptedText = new String(cipher.doFinal(enc));

        } catch (NoSuchAlgorithmException
                | BadPaddingException | IllegalBlockSizeException
                | NoSuchPaddingException | InvalidKeyException e) {

            System.out.println(e);
        } finally {
            return decryptedText;
        }

    }

}
