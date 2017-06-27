package JavaMail
import  mail_agent._
import java.io.File;

object Main {
  def main(args: Array[String]): Unit = {
    
  
  // Les Objets modele  : nom_de_votre_objet.contenu
      val objet_amical = new content("Saluuut");
      val objet_officiel = new content("Salutation");
      val objet3 = new content("Demande d'information");
      val objet4 = new content("Consultation sur rendez-vous");
      val objet_vide = new content("");
  
   // Les messages modele  : nom_de_votre_message.contenu
      val message_amical = new content("Coucou, toi là, ça va hein?");
      val message_officiel = new content("Bonsoir, Bien cordialement");
      val message3 = new content("Coucou, toi là, ça va hein?");
      val message4 = new content("Salut, toi là, ça va hein?");
      val message_vide = new content("");
      
      
   // Liste de contacts
      new Contact{
     var contact_list = add a new Person("syntyche", "syntshimbi@gmail.com")
      contact_list ++= add a new Person("aav",    "aav@gmail.com")
      contact_list ++= add a new Person("aw",    "aaw@gmail.com")
      contact_list ++= add a new Person("ax",    "aax@gmail.com")
      contact_list ++= add a new Person("y",    "ay@gmail.com")
      contact_list ++= add a new Person("z",    "az@gmail.com")
      contact_list ++= add a new Person("",     "")
      contact_list ++= add a new Person("",     "")
      //println(contact_list)
    }
      
      
  /* print out all members of the contact_lis*/
  //println(contact_list.values.toList)
  

     
   /*
    * Email with model messages
    * Ex: Empty message
    */
    /*new Mail_agent{ sends a new Mail(from = "syntshimbi4@gmail.com", 
        to = List("a@gmail.com", "b@gmail.com"),
        cc = None,
        bcc = None,
        subject = objet_vide.contenu,
        message = message_vide.contenu,
        attachment = None
        )
    }
    * */
    
     /*
    * Email with model messages
    * Ex: Message_officiel
    */
    /*new Mail_agent{ sends a new Mail(
        from = "syntshimbi4@gmail.com", 
        to = List("a@gmail.com", "b@gmail.com"),
        cc = None,
        bcc = None,
        subject = objet_officiel.contenu,
        message = message_officiel.contenu,
        attachment = None
        )
    }
    */
    
     /*
    * Email to all members of the contact_list
    */
   /* new Mail_agent{ sends a new Mail(
        from = "syntshimbi4@gmail.com", 
        to = contact_list.values.drop(1).toList,
        cc = None,
        bcc = None,
        subject = "Email without attachment",
        message = "Hi, I cannot find the file I told you about.",
        attachment = None
        )
    }
    
    */
   /*
    * Email to all filtered members of the contact_list
    */
      /*
    new Mail_agent{ sends a new Mail(
        from = "syntshimbi4@gmail.com", 
        to = filter("a"),
        cc = None,
        bcc = None,
        subject = "Email without attachment",
        message = "Hi, I cannot find the file I told you about.",
        attachment = None
        )
    }
    */
      /*
    * Email without attachment
    */
    /*new Mail_agent{ sends a new Mail(
        from = "syntshimbi4@gmail.com", 
        to = List("x@gmail.com", "y@gmail.com"),
        cc = None,
        bcc = None,
        subject = "Email without attachment",
        message = "Hi, I cannot find the file I told you about.",
        attachment = None
        )
    }
    * 
    */
      /*
    * Email with attachment
    */
    new Mail_agent{ sends a new Mail(
        from = "syntshimbi4@gmail.com", 
        to = List("x@gmail.com", "y@gmail.com"),
        cc = Option(""),
        bcc = Option(""),
        subject = "Email with attachment",
        message = "To whom it may concern, please find attached a copie of my recommandation lettr",
        attachment = Option(new File("src\\resources\\MyFile.txt"))
        ) With new attachment("") 
    }

  
      
    
     
  }
 

}