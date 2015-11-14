import java.util.HashMap;
import java.util.Scanner;

class MailSystem{

private HashMap <String, Person> personer = new HashMap<>();

MailSystem(){
		Mailbox sender = null;
		Scanner inn = new Scanner(System.in);
		
		String menyValg = "1";
		while(!menyValg.equals("0")){
		System.out.println("*****************************");
		System.out.print(" Velkommen til mailsystemet");
		if(sender != null){
			System.out.print(", " + sender.returnEier().returnerNavn() + "!");
		}
		System.out.println("");
		System.out.println("*****************************");
		System.out.println("");
		System.out.println("1) Opprett person");
		System.out.println("2) Opprett mailboks");
		System.out.println("3) Logg inn");
		System.out.println("4) Skriv melding");
		System.out.println("5) Les innboks");
		System.out.println("0) Avslutt");

		menyValg = inn.nextLine();

			if(menyValg.equals("1")){
				opprettPerson();

			}
			if(menyValg.equals("2")){
				opprettMailbox();
			}
			if(menyValg.equals("3")){
				sender = logginnMailbox();
			}
			if(menyValg.equals("4")){
				skrivMelding(sender);	
			}
		
			if(menyValg.equals("5")){			
				lesInnboks(sender);
			}
		}
	}


	Mailbox logginnMailbox(){
		boolean personExists = false;
		boolean mailboxExists = false;
		
		Scanner inn = new Scanner(System.in);
		System.out.println("Skriv inn ditt navn for å logge inn:");
		String personInput = inn.nextLine();
		if(!personer.containsKey(personInput)){
			System.out.println("Denne personen finnes ikke i systemet.");
			return null;
		}
		Person personNavn = personer.get(personInput);
		
		System.out.println("Hei, " + personNavn.returnerNavn() + ", du har følgende mailbokser:");
		for(String m : personNavn.returnMailboxes().keySet()){
			System.out.println("> " + m);
		}
				
		System.out.println("Skriv inn navn på mailbox for å logge inn:");
		String senderInput = inn.nextLine();
		if(!personNavn.returnMailboxes().containsKey(senderInput)){
			System.out.println(personNavn.returnerNavn() + " har ingen mailboks som heter " + senderInput);
			return null;
		}
		Mailbox sender = personNavn.returnMailboxes().get(senderInput);
		return sender;
	}
	
	void opprettPerson(){
		Scanner inn = new Scanner(System.in);
		System.out.println("Hva heter personen?");
		Person p = new Person(inn.nextLine());
		if(!personer.containsKey(p.returnerNavn())){
			personer.put(p.returnerNavn(), p);
		}else{
				System.out.println("Personen finnes");
		}
	}
	
	void opprettMailbox(){
		System.out.println("Hva heter personen du vil opprette mailboks for?");
		Scanner inn = new Scanner(System.in);
		Person personNavn = personer.get(inn.nextLine());
		if(personNavn == null){
			System.out.println("Personen finnes ikke.");
			return;
		}
		personNavn.createMailbox();
	}
	
	void skrivMelding(Mailbox sender){
		Message nySkrevetMail;
		Person personNavn;
		Mailbox nyMailbox;
		Mailbox recipient = null;

		Scanner inn = new Scanner(System.in);
		boolean recipientPersonExist = false;
		boolean recipientMailboxExist = false;
		boolean senderMailboxExist = false;
		Person recipientPerson;
						
		System.out.println("Emne:");
		String subject = inn.nextLine();
		System.out.println("Tekst:");
		String text = inn.nextLine();
		System.out.println("Hvem vil du sende meldingen til?");
		String recString = inn.nextLine();
				
		if(!personer.containsKey(recString)){
			System.out.println("Personen finnes ikke i mailsystemet.");
			return;
		}
				
		recipientPerson = personer.get(recString);
		recipientPersonExist = true;
		
		System.out.println("Hvilken av denne personens mailbokser vil du sende til?");
		for(String m : recipientPerson.returnMailboxes().keySet()){
			System.out.println(m);
		}
		String recipientMailboxInput = inn.nextLine();

		if(recipientPerson.returnMailboxes().containsKey(recipientMailboxInput)){
			recipientMailboxExist = true;
			System.out.println(recipientMailboxInput + " finnes. ");
			recipient = recipientPerson.returnMailboxes().get(recipientMailboxInput);		
		}else{
			return;
		}
		if(sender != null){
			senderMailboxExist = true;
		}							
		if(senderMailboxExist == false){
			System.out.println("Du er ikke innlogget. Logg inn:");
			sender = logginnMailbox();
			senderMailboxExist = true;
		}
		
		if(recipientPersonExist == true && recipientMailboxExist == true && senderMailboxExist == true){
			Message newMessage = new Message(subject, text, sender, recipient);
			sender.sendMessage(newMessage);
			recipient.receiveMessage(newMessage);
			System.out.println("Opprettet melding, la den i " + sender.returnMailBoxNavn() + " sin utboks, lot " + recipient.returnMailBoxNavn() + " motta mailen");
		}
	}
	
	void lesInnboks(Mailbox sender){
		Scanner inn = new Scanner(System.in);
	
		if(sender == null){
			System.out.println("Må logge inn først.");
			System.out.println("Brukere:");
			for(Person p : personer.values()){
				System.out.println(p.returnerNavn());
			}
		}else{
			System.out.println("Hei, " + sender.returnEier().returnerNavn() + "! Meldinger i innboksen din:");
			for(Message m : sender.returnMessagesInbox().values()){
				System.out.println("> " + m.returnSubject());
			}
			System.out.println("Skriv inn emne på meldingen du vil lese:");
			Message fetchMail = sender.returnMessagesInbox().get(inn.nextLine());
			System.out.println("Emne:");
			System.out.println(fetchMail.returnSubject());
			System.out.println("Tekst: ");
			System.out.println(fetchMail.returnText());
			
		}
	}
}

