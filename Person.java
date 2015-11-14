import java.util.HashMap;
import java.util.Scanner;

class Person{

private HashMap <String, Mailbox> mailboxes = new HashMap<>();
private String navn;

	Person(String navn){
	this.navn = navn;
	}

	String returnerNavn(){
	return navn;
	}
	
	HashMap<String, Mailbox> returnMailboxes(){
	return mailboxes;
	}
	
	void createMailbox(){
	Scanner inn = new Scanner(System.in);
	System.out.println("Hva heter mailboksen?");
	String mailboxNavn = inn.nextLine();
	Mailbox nyMailbox = new Mailbox(this, mailboxNavn);
	mailboxes.put(nyMailbox.returnMailBoxNavn(), nyMailbox);
	
	System.out.println(nyMailbox.returnMailBoxNavn() + " opprettet.");
	}

}
