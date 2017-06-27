package mail_agent

class content 
    (content: String) 
    {
      var contenu : String = content
      
      trait MailStatus
      case class Failure(exception:Throwable) extends MailStatus
      case class Success(id:String) extends MailStatus

      // creer nom_message new content("contenu_message")
      object creer {
      
    }
}