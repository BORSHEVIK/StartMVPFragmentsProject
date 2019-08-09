package com.borshevik.mvpfragments.model

import android.text.TextUtils
import android.util.Xml
import com.borshevik.mvpfragments.utils.CSVUtils
import org.w3c.dom.Element
import timber.log.Timber
import java.io.*
import java.util.*
import javax.xml.parsers.DocumentBuilderFactory


data class SchoolPerson(val school: String,
                        val eventCode: String,
                        val psk: String,
                        val firstName: String,
                        val lastName: String,
                        val type: String,
                        val id: String,
                        val hubld: String,
                        val schoolStudentId: String,
                        val grade: String): Serializable {

    var GUID: String? = null
    var createFileTime: String? = null

    fun schoolPersonToXMLFile(filePath: String) {
        try {
            val fileOutputStream = FileOutputStream(filePath)
            val xmlSerializer = Xml.newSerializer()
            val writer = StringWriter()

            xmlSerializer.setOutput(writer)
            xmlSerializer.startDocument("UTF-8", true)

            xmlSerializer.startTag(null, TAG_SCHOOL_PERSON)

            xmlSerializer.startTag(null, TAG_SCHOOL)
            xmlSerializer.text(school)
            xmlSerializer.endTag(null, TAG_SCHOOL)

            xmlSerializer.startTag(null, TAG_EVENT_CODE)
            xmlSerializer.text(eventCode)
            xmlSerializer.endTag(null, TAG_EVENT_CODE)

            xmlSerializer.startTag(null, TAG_PSK)
            xmlSerializer.text(psk)
            xmlSerializer.endTag(null, TAG_PSK)

            xmlSerializer.startTag(null, TAG_FIRST_NAME)
            xmlSerializer.text(firstName)
            xmlSerializer.endTag(null, TAG_FIRST_NAME)

            xmlSerializer.startTag(null, TAG_LAST_NAME)
            xmlSerializer.text(lastName)
            xmlSerializer.endTag(null, TAG_LAST_NAME)

            xmlSerializer.startTag(null, TAG_TYPE)
            xmlSerializer.text(type)
            xmlSerializer.endTag(null, TAG_TYPE)

            xmlSerializer.startTag(null, TAG_ID)
            xmlSerializer.text(id)
            xmlSerializer.endTag(null, TAG_ID)

            xmlSerializer.startTag(null, TAG_HUBLD)
            xmlSerializer.text(hubld)
            xmlSerializer.endTag(null, TAG_HUBLD)

            xmlSerializer.startTag(null, TAG_SCHOOL_STYDENT_ID)
            xmlSerializer.text(schoolStudentId)
            xmlSerializer.endTag(null, TAG_SCHOOL_STYDENT_ID)

            xmlSerializer.startTag(null, TAG_GRADE)
            xmlSerializer.text(grade)
            xmlSerializer.endTag(null, TAG_GRADE)

            if (!TextUtils.isEmpty(GUID)) {
                xmlSerializer.startTag(null, TAG_GUID)
                xmlSerializer.text(GUID)
                xmlSerializer.endTag(null, TAG_GUID)
            }

            if (!TextUtils.isEmpty(createFileTime)) {
                xmlSerializer.startTag(null, TAG_CREATE_FILE_TIME)
                xmlSerializer.text(createFileTime)
                xmlSerializer.endTag(null, TAG_CREATE_FILE_TIME)
            }

            xmlSerializer.endTag(null, TAG_SCHOOL_PERSON)
            xmlSerializer.endDocument()
            xmlSerializer.flush()
            val dataWrite = writer.toString()
            fileOutputStream.write(dataWrite.toByteArray())
            fileOutputStream.close()

        } catch (e: FileNotFoundException) {
            Timber.e(e)

        } catch (e: IllegalArgumentException) {
            Timber.e(e)

        } catch (e: IllegalStateException) {
            Timber.e(e)

        } catch (e: IOException) {
            Timber.e(e)

        }
    }

    companion object {

        private val NAMESPACE = ""

        private val TAG_SCHOOL_PERSON = "SchoolPerson"

        private val TAG_SCHOOL = "School"
        private val TAG_EVENT_CODE = "EventCode"
        private val TAG_PSK = "PSK"
        private val TAG_FIRST_NAME = "FirstName"
        private val TAG_LAST_NAME = "LastName"
        private val TAG_TYPE = "Type"
        private val TAG_ID = "ID"
        private val TAG_HUBLD = "Hubld"
        private val TAG_SCHOOL_STYDENT_ID = "SchoolStydentID"
        private val TAG_GRADE = "Grade"
        private val TAG_GUID = "GUID"
        private val TAG_CREATE_FILE_TIME = "CreateFileTime"

        fun lineCSVToPerson(csvLine: List<String>): SchoolPerson {
            return SchoolPerson(csvLine[0], csvLine[1], csvLine[3], csvLine[4], csvLine[5],
                    csvLine[6], csvLine[7], csvLine[8], csvLine[9], csvLine[10])
        }

        fun linesCSVToPersonList(set: LinkedHashSet<List<String>>): List<SchoolPerson> {
            val persons = mutableListOf<SchoolPerson>()
            val repsonsArray = set.toList()
            for (i in 2 until repsonsArray.size + 1) {
                persons.add(lineCSVToPerson(repsonsArray[i - 1]))
            }
            return persons
        }

        fun schoolPersonToFile(filePath: String, schoolPerson: SchoolPerson) {

            val writer = FileWriter(filePath)

            CSVUtils.writeLine(writer, Arrays.asList("School", "EventCode", "EventKey", "PSK",
                    "First Name", "Last Name", "Type", "Id", "Hubld", "School Student ID", "Grade"))
            CSVUtils.writeLine(writer, Arrays.asList(schoolPerson.school, schoolPerson.eventCode, "",
                    schoolPerson.psk, schoolPerson.firstName, schoolPerson.lastName,
                    schoolPerson.type, schoolPerson.id, schoolPerson.hubld, schoolPerson.schoolStudentId,
                    schoolPerson.grade))

            writer.flush()
            writer.close()

        }

        fun parseFromXMLFile(filePath: String): SchoolPerson {
            val dbFactory = DocumentBuilderFactory.newInstance()
            val dBuilder = dbFactory.newDocumentBuilder()
            val doc = dBuilder.parse(File(filePath))

            val element = doc.getDocumentElement()
            element.normalize()

            return SchoolPerson(getValue(TAG_SCHOOL, element), getValue(TAG_EVENT_CODE, element),
                    getValue(TAG_PSK, element), getValue(TAG_FIRST_NAME, element),
                    getValue(TAG_LAST_NAME, element), getValue(TAG_TYPE, element),
                    getValue(TAG_ID, element), getValue(TAG_HUBLD, element),
                    getValue(TAG_SCHOOL_STYDENT_ID, element), getValue(TAG_GRADE, element)).also {
                val guid = getValue(TAG_GUID, element)
                if (guid != null) {
                    it.GUID = guid
                }

                val createFileTime = getValue(TAG_CREATE_FILE_TIME, element)
                if (createFileTime != null) {
                    it.createFileTime = createFileTime
                }
            }
        }

        private fun getValue(tag: String, element: Element): String {
            val nodeList = element.getElementsByTagName(tag).item(0).childNodes
            val node = nodeList.item(0)
            if (node == null) return "" else return node.nodeValue
        }
    }

}