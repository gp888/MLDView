package com.gp.mldview.ether.utils

import com.google.common.collect.ImmutableList
import com.gp.mldview.App
import org.bitcoinj.crypto.ChildNumber
import org.bitcoinj.crypto.HDUtils.parsePath
import org.bitcoinj.wallet.DeterministicKeyChain
import org.bitcoinj.wallet.DeterministicSeed
import org.web3j.crypto.*
import org.web3j.utils.Numeric
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.security.SecureRandom
import java.util.*


object Web3Utils {

    val BIP44_ETH_ACCOUNT_ZERO_PATH: ImmutableList<ChildNumber> = ImmutableList.
    of(ChildNumber(44, true),
            ChildNumber(60, true),
            ChildNumber.ZERO_HARDENED, ChildNumber.ZERO)

    fun populateWordList():List<String> {
        try {
            val fis = App.app.assets?.open("en-mnemonic-word-list.txt")
            return readAllLines(fis!!)
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return emptyList()
    }

    @Throws(IOException::class)
    fun readAllLines(inputStream: InputStream): List<String> {
        val br = BufferedReader(InputStreamReader(inputStream))
        val data: MutableList<String> = ArrayList()
        var line: String
        while (br.readLine().also { line = it } != null) {
            data.add(line)
        }
        return data
    }

    /**
     * web3j生成助记词
     * 能用
     */
    fun makeMnemonic(): String {
        val initialEntropy = ByteArray(16)
        SecureRandom().nextBytes(initialEntropy)
        return MnemonicUtils.generateMnemonic(initialEntropy)
    }

    //根据生成的助记词, 生成一些列的种子, 运用了 BIP32确定性钱包算法(deterministic wallet algorithm)
    fun createETHWalletFromWords(words: String): TianWallet {
        val wordsList = (words.split("\\s+".toRegex()).dropLastWhile { it.isEmpty() }).toMutableList()
        val deterministicSeed = DeterministicSeed(wordsList, null, "", 0)
        val deterministicKeyChain = DeterministicKeyChain.builder().seed(deterministicSeed).build()
        //m / purpose' / coin_type' / account' / change / address_index
        val DERIVATION_PATH = "M/44H/60H/0H/0/0"

        val privateKey = deterministicKeyChain.getKeyByPath(parsePath(DERIVATION_PATH), true).privKey

        val ecKeyPair = ECKeyPair.create(privateKey)
        val address = Keys.getAddress(ecKeyPair)
        return TianWallet("0x$address", Numeric.toHexStringWithPrefix(ecKeyPair.privateKey), Numeric.toHexStringWithPrefix(ecKeyPair.publicKey), words)
    }
}