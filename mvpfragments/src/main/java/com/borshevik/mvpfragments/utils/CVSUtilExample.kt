package com.borshevik.mvpfragments.utils

import java.io.FileWriter
import java.util.*

object CVSUtilExample {

    /*Outp
       a,b,c,d
       "aaa","bb,b","cc,c"
       'aaa'|'bbb'|'cc,c'
       aaa,bbb,cc""c
     */
    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        val csvFile = "/Users/mkyong/csv/abc.csv"
        val writer = FileWriter(csvFile)

        CSVUtils.writeLine(writer, Arrays.asList("a", "b", "c", "d"))

        //custom separator + quote
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bb,b", "cc,c"), ',', '"')

        //custom separator + quote
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc,c"), '|', '\'')

        //double-quotes
        CSVUtils.writeLine(writer, Arrays.asList("aaa", "bbb", "cc\"c"))


        writer.flush()
        writer.close()
    }

    /*
    @Throws(Exception::class)
    @JvmStatic
    fun mains(args: Array<String>) {

        val csvFile = "/Users/mkyong/csv/developer.csv"
        val writer = FileWriter(csvFile)

        val developers = Arrays.asList<Developer>(
                Developer("mkyong", BigDecimal(120500), 32),
                Developer("zilap", BigDecimal(150099), 5),
                Developer("ultraman", BigDecimal(99999), 99)
        )

        //for header
        CSVUtils.writeLine(writer, Arrays.asList("Name", "Salary", "Age"))

        for (d in developers) {

            val list = ArrayList()
            list.add(d.getName())
            list.add(d.getSalary().toString())
            list.add(String.valueOf(d.getAge()))

            CSVUtils.writeLine(writer, list)

            //try custom separator and quote.
            //CSVUtils.writeLine(writer, list, '|', '\"')
        }

        writer.flush()
        writer.close()

    }
    */

}