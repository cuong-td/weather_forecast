package com.nab.weatherforecast.utilities

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class Crypto {
    private val keyStore = KeyStore.getInstance(ANDROID_KEY_STORE)

    init {
        keyStore.load(null)
    }

    private fun getSecretKey(alias: String): SecretKey {
        if (keyStore.containsAlias(alias)) {
            return keyStore.getKey(alias, null) as SecretKey
        }

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            val keyGenerator =
                KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE)
            keyGenerator.init(
                KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .setRandomizedEncryptionRequired(false)
                    .build()
            )
            return keyGenerator.generateKey()
        } else {
            // TODO:"VERSION.SDK_INT < M"
            throw UnsupportedOperationException()
        }
    }

    fun decrypt(alias: String, encryptedData: ByteArray, encryptionIv: ByteArray): String {
        val cipher: Cipher = Cipher.getInstance(TRANSFORMATION)
        val spec = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            GCMParameterSpec(128, encryptionIv)
        } else {
            TODO("VERSION.SDK_INT < KITKAT")
        }
        cipher.init(Cipher.DECRYPT_MODE, getSecretKey(alias), spec)
        return String(cipher.doFinal(encryptedData), charset("UTF-8"))
    }

    fun encrypt(alias: String, textToEncrypt: String, existedIv: ByteArray? = null): ByteArray {
        val cipher = Cipher.getInstance(TRANSFORMATION)
        if (existedIv == null) {
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias))
        } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(alias), GCMParameterSpec(128, existedIv))
        }
        return cipher.doFinal(textToEncrypt.toByteArray(charset("UTF-8")))
    }

    private fun String.decodeBase64() = Base64.decode(this, Base64.NO_WRAP)
    private fun ByteArray.encodeBase64(): String = Base64.encodeToString(this, Base64.NO_WRAP)

    companion object {
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val ANDROID_KEY_STORE = "AndroidKeyStore"
    }
}