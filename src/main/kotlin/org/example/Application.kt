package org.example

import java.io.File
import java.util.*
import javax.mail.*
import javax.mail.internet.InternetAddress
import javax.mail.internet.MimeMessage


class Application {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val props = System.getProperties()
            props.apply {
                setProperty("mail.smtp.host", "")
                setProperty("mail.smtp.port", "2255")
                setProperty("mail.debug", "true")
            }

            try {
                val session = Session.getDefaultInstance(props)

                val msg = MimeMessage(session)
                msg.setFrom(InternetAddress(""))
                msg.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("", false)
                )
                msg.subject = "Hello"
                val message = File("output.log").readText()

                msg.setText(message)
                msg.sentDate = Date()
                Transport.send(msg)
                println("Message sent.")
            } catch (mex: MessagingException) {
                mex.printStackTrace()
            }
        }
    }
}