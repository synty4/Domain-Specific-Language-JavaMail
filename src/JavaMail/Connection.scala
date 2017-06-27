package JavaMail
import javax.mail.{Session => MailSession, Message, Transport}
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties


/*
 *  Companion Object for Connection
 */

object Connection{
  def apply(p:Properties): Session = Session.getDefaultInstance(p);
  
}

/*
 * Coonection is used to establish a connection through a given session 
 * has one field: session
 * one method: setup() which sets up the connection to the SMTP protocol 
 * on port 2525 and from the localhost
 * These technical information has been hardcoded and encapsulated into the MailServer
 * so that he doesn't have to specify them neither to see them.
 * */
class Connection(){
  
  var session:Session = null;
 
  def setup(){ 
    // Get system properties  
    var p: Properties = System.getProperties();
    p.setProperty("mail.smtp.host", "localhost");
    p.setProperty("mail.smtp.port", "2525");
    p.setProperty("mail.smtp.ssl.enable", "SMTP");
    
   
    // Get the Session object with the properties set above
    session = Session.getInstance(p); 
    
    
  }
  
  
  }


