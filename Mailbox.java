import java.util.HashMap;
import java.util.Scanner;

class Mailbox{

	private HashMap <String, Message> messagesInbox = new HashMap<String, Message>();
	private HashMap <String, Message> messagesOutbox = new HashMap<String, Message>();
	private HashMap <String, Person> personer = new HashMap<String, Person>();

	private Person eier;
	private String mailboxNavn;
	private MailSystem mailsys;

	Mailbox(Person eierInn, String mailboxNavn){
	this.eier = eierInn;
	this.mailboxNavn = mailboxNavn;
	}

	String returnMailBoxNavn(){
		return mailboxNavn;
	}
	
	Person returnEier(){
		return eier;
	}
	
	HashMap<String, Message> returnMessagesInbox(){
		return messagesInbox;
	}
	HashMap<String, Message> returnMessagesOutbox(){
		return messagesOutbox;
	}
	
	void sendMessage(Message msgSent){
	messagesOutbox.put(msgSent.returnSubject(), msgSent);
	}

	void receiveMessage(Message msgReceived){
	messagesInbox.put(msgReceived.returnSubject(), msgReceived);
	}

}
