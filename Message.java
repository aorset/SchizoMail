class Message{

private	String subject;
private String text;
private Mailbox recipient;
private Mailbox sender;

	Message(String subject, String text, Mailbox sender, Mailbox recipient){
		this.subject = subject;
		this.text = text;
		this.sender = sender;
		this.recipient = recipient;
	}

	String returnSubject(){
		return subject;
	}

	String returnText(){
		return text;
	}
	
	Mailbox returnRecipientMailbox(){
		return recipient;
	}

	Mailbox returnSenderMailbox(){
		return sender;
	}
	
}
