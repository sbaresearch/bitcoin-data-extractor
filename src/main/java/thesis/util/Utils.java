package thesis.util;

import thesis.exception.ServiceException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Utils {

    /**
     * Given a binary representation of a block header,
     * return the respective block hash by computing a double sha256 hash
     * as described in https://en.bitcoin.it/wiki/Block_hashing_algorithm
     *
     * @param bytes - binary representation of the blockheader
     * @return - hash of the given block
     * @throws ServiceException
     */
    public static byte[] doubleSha256Hash(byte[] bytes) throws ServiceException {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(bytes, 0, bytes.length);
            byte[] hash1 = messageDigest.digest();

            return reverseBytes(messageDigest.digest(hash1));
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e.getMessage());
        }
    }

    public static byte[] doubleSha256Hash(byte[] bytes, int offset, int length) throws ServiceException {

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

            messageDigest.update(bytes, offset, length);
            return messageDigest.digest(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e.getMessage());
        }
    }
/**
    public static byte[] parseMergeMinedBlock(byte[] bytes){
        byte[] bytesforMMBlock = bytes;
        int mmCursor = this.payloadCursor;
        if(bytesforMMBlock == null)
        {
            if(payload != null && payload.length > (Block.HEADER_SIZE+1))
            {
                mmCursor = cursor;
                bytesforMMBlock = this.payload;
            }
        }

        mmBlock = new BlockMergeMined(params, bytesforMMBlock, mmCursor, this);
    }
*/
    /**
     * Returns the given bytes in reversed order
     * @param bytes - bytes to be reversed
     * @return - reversed bytes
     */
    public static byte[] reverseBytes(byte[] bytes) {

        byte[] reverse = new byte[bytes.length];

        for (int i = 0; i < bytes.length; i++) {
            reverse[i] = bytes[bytes.length - 1 - i];
        }

        return reverse;
    }


    public static String nmcToBtcAddress(String nmcAddr) throws ServiceException {

        byte[] nmcAddrBytes = Base58.decode(nmcAddr);

        byte[] dataBytes = Arrays.copyOfRange(nmcAddrBytes, 1, nmcAddrBytes.length-5);

        String test = Base58.encode(dataBytes);
        byte[] btcAddress = new byte[nmcAddrBytes.length];

        System.arraycopy(dataBytes, 0, btcAddress, 1, dataBytes.length);

        byte[] version = {0};
        byte[] checksum = doubleSha256Hash(btcAddress, 0, dataBytes.length +1);
        System.arraycopy(checksum, 0, btcAddress, dataBytes.length+1, 4);

        return Base58.encode(btcAddress);

    }
}
