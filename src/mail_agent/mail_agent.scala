/**
 * mail_agent package handles the email's fields and does the sending of email
 * contains the following:
 * 
 * contact_list is a list of all saved contacts and itis declared as global 
 * because it is shared among classes from differents packages
 * 
 * Type_of_email trait, attachment, no_attachment classes, Attach abstract class,
 * ToWith, Without classes are all used to show in a DSL whether a file is attached or not to the email
 * Mail class this is a case class and contains all the fields members of an email
 * 
 * 
 * /
 */
import javax.mail._
import java.util.Properties
import System.{getProperty=>property, getProperties=>properties}
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import javax.activation.{FileDataSource, DataHandler}
import javax.mail.internet.{MimeMultipart, MimeBodyPart, InternetAddress, MimeMessage}
import javax.mail.{Session => MailSession, Message, Transport}

import scala.concurrent.{Future, ExecutionContext}
import scala.language.implicitConversions
import scala.collection.SortedMap

import javax.activation.{FileDataSource, DataHandler}
import javax.mail.internet.{MimeMultipart, MimeBodyPart, InternetAddress, MimeMessage}
import javax.mail.{Session => MailSession, Message, Transport}

import scala.concurrent.{Future, ExecutionContext}
import scala.language.implicitConversions
import javax.activation.{FileDataSource, DataHandler}
import javax.mail.internet.{MimeMultipart, MimeBodyPart, InternetAddress, MimeMessage}
import javax.mail.{Session => MailSession, Message, Transport}

import scala.concurrent.{Future, ExecutionContext}
import scala.language.implicitConversions

import JavaMail._
import com.sun.xml.internal.bind.v2.util.FlattenIterator


package object mail_agent{
  
 
  /*
  * Option[T] is a container for zero or one element of a given type
  * can ake two values: some(x) or None
  * 
  * */
  
  /* Implicit definition of Option to enable easie Option[] to passes the String directly  instead of some(value)*/
  
  implicit def Option[Value](v: Value): Option[Value] = if ( v == null ) None else Some(v)
  var contact_list = Map("synty" -> "syntshimbi4@gmail.com") 
  
  var attached: Boolean = false;
  

  
   /* 
    * Trait + Pattern Matching for classes
    * ************************************
    * classes for file attachment
    */
  
  trait Type_of_email
  case class attachment(s:String) extends Type_of_email
  case class no_attachment(st:String) extends Type_of_email
  
  sealed abstract class Attach{
    def With(t:Type_of_email):Unit={}
  }
  
  class ToWith extends Attach{
    def With(t:attachment):Unit={
     println("Email sent without attachment ")
   }  
  }
  class Without extends Attach{
     def With(t:no_attachment):Unit={
     println("Email sent without attachment ")
    }
  }

  
  /* Mail class in which typical mail'fields are defined as the class fields 
   * 
   * Pattern Matching on a class
   * 
   * */
  
   
   case class Mail 
   (from: (String),
    to: List[String],
    cc: Option[String]= None,
    bcc: Option[String] = None,
    subject: String, 
    message: Option[String],
   //richMessage: Option[String] = None,
    attachment:Option[(java.io.File)] = None)
 

  
 /*
  * Trait and Pattern Matching on classes
  * Status is the return type of the method a 
  * */
  trait Status
  case class NotSent(exception:Throwable) extends Status
  case class Sent(var ok:Int)extends Status
  
/*
 * Filtering  
 */
  //val evens = x.filter(_ % 2 == 0)
 /*@pre; contact_list is not emty
       * @post: a list of all filtered elements
       * */
        def filter(s:String):List[String]={
               var keys: Set[String] = contact_list.keySet
               var values: Iterable[String] = contact_list.values.drop(1)
               
               var filter: List[String]= values.filter { x => x.startsWith(s)}.toList
               filter
       

   }

/*
 * Object Send defined with a method a whose parameter is a mail type 
 * */
  /* Method a
   * @Pre: At least, the following fields of mail : from, to, subject fields, should be filled
   * @Post: Status trait: instance of class Sent on successful sent
   * 											instance of class NotSent on unsuccessful sent											
   * */
  
  
  class Mail_agent{
    object sends{
      def a(mail: Mail) : Attach={
        
       /*
        * connecting and setting fields
        * and sending the message
        * 
        * */ 
      { 
        val connection: Connection = new Connection
        //Setup a connection
        connection.setup()
        println(connection.session.getProperty("mail.smtp.port"),"connection established")  
         // Create a default MimeMessage object.
        val msg = new MimeMessage(connection.session)
         // Set From: header field of the header.
         msg.setFrom(new InternetAddress(mail.from))
          /*
         * code blocks for sending a message to all contact_list elements
         * 
         */
        
          if (mail.to.equals(contact_list)){   
          if(!( contact_list.isEmpty)){
               var keys: Set[String] = contact_list.keySet
               var values: Iterable[String] = contact_list.values.drop(1)
               //if (values.head.contains(""))
                 values.foreach(a => msg.addRecipient(Message.RecipientType.TO, new InternetAddress(a)))
          }
        }
          else
            // Set To: header field of the header
          mail.to foreach (a => msg.addRecipient(Message.RecipientType.TO, new InternetAddress(a)))
          
         
          // Set Subject: header field
         msg.setSubject(mail.subject)
         
          // Set Subject: header field
         msg.setSubject(mail.subject)

         // Now set the actual message
         msg.setText(mail.message.getOrElse("Oups!!!, the message is empty"))
         try{
         // SL'envoi du message requiert l'xactitude du port de la session
         //Les champs from et to ne doivent pas etre libre pour ne pas avoir d'exceptiona ce niveau
         Transport.send(msg)
       }
        catch {
         //case t: Throwable => t.printStackTrace() // TODO: handle error
          case t2 : javax.mail.MessagingException => 
            println("error: unable to establish connection:two possible reasons")
            println("the server is not connected or cannot accept connections")
            println("the port number set on the Connection.scala file doesn't correspond to your server's port number")
            println("The actual port number is: " , connection.session.getProperty("mail.smtp.port"))
         }       
      } 
         
         val to_with: ToWith = new ToWith
         
         val without:  Without = new Without
         
         if (mail.attachment.isDefined)
           {
 
              // Create a multipar message
             var  multi:Multipart = new MimeMultipart();
 
             // Create the message part
             var mbody:MimeBodyPart = new MimeBodyPart();
             //var file: String = "/home/manisha/file.txt";
             var src: FileDataSource = new FileDataSource(mail.attachment.get);
             
             
             mbody.setDataHandler(new DataHandler(src));
             mbody.setFileName(mail.attachment.get.getName);
             // Set text message part
             multi.addBodyPart(mbody);
             
           to_with
         }
          
         else
           without
             
     }
    }
   
    object sends_to_all{
      
    }
  }
  

 /*
  * Create a list of contact and save them in a Map 
  * 
  * */
case class Person(name:String, email:String)

class Contact{
  object add{  
  def a(person:Person): Map[String, String]={
   
   contact_list += (person.name -> person.email)   
   contact_list
  
 }
 
}
}


}

