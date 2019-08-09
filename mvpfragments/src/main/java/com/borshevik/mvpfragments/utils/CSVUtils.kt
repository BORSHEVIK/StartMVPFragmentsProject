package com.borshevik.mvpfragments.utils

import java.io.*


object CSVUtils {

    private val TAG = CSVUtils.javaClass.simpleName

    private val DEFAULT_SEPARATOR = ','

    private fun followCVSformat(value: String): String {

        var result = value
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"")
        }
        return result

    }

    @Throws(IOException::class)
    @JvmOverloads
    fun writeLine(w: Writer, values: List<String>, separators: Char = DEFAULT_SEPARATOR, customQuote: Char = ' ') {
        var separators = separators

        var first = true

        //default customQuote is empty

        if (separators == ' ') {
            separators = DEFAULT_SEPARATOR
        }

        val sb = StringBuilder()
        for (value in values) {
            if (!first) {
                sb.append(separators)
            }
            if (customQuote == ' ') {
                sb.append(followCVSformat(value))
            } else {
                sb.append(customQuote).append(followCVSformat(value)).append(customQuote)
            }

            first = false
        }
        sb.append("\n")
        w.append(sb.toString())
    }

    fun convertCSVFiletoList(inputStream: InputStream): LinkedHashSet<List<String>> {
        val result: LinkedHashSet<List<String>> = linkedSetOf<List<String>>()
        val reader = BufferedReader(InputStreamReader(inputStream))
        try {
            while (true) {
                val line = reader.readLine() ?: break
                val lineResult = line.split(",")
                //Log.d(TAG, line)
                result.add(lineResult)
            }
        } catch (ex: IOException) {
            throw RuntimeException("Error in reading CSV file: $ex")
        } finally {
            try {
                inputStream.close()
            } catch (e: IOException) {
                throw RuntimeException("Error while closing input stream: $e")
            }

        }
        return result
    }

}