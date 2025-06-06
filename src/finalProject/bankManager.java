package finalProject;


public class bankManager {
	public static final int SIZE = 100;
private client[] clients;
public bankManager() {
	clients = new client [SIZE];
}
public String addClient(client c) {
    if (c == null) {
        return "Could not add client: Client is null.";
    }
    if (clients[clients.length - 1] != null) {
        return "Client list is full.";
    }
    for (int i = 0; i < clients.length; i++) {
        if (clients[i] == null) {
            clients[i] = c;
            sortClients(); 
            return "Client added successfully.";
        }
    }
    return "Unexpected error adding client.";
}

public String removeClient(client c) {
    if (c == null) {
        return "Sorry, not a valid client.";
    }
    if (clients[0] == null) {
        return "Sorry, the list is empty.";
    }
    for (int i = 0; i < clients.length; i++) {
        if (clients[i] != null && clients[i].equals(c)) {
            for (int j = i; j < clients.length - 1; j++) {
                clients[j] = clients[j + 1];
            }
            clients[clients.length - 1] = null;
            return "Client removed successfully.";
        }
    }
    return "Client not found.";
}

	 public void sortClients() {
	        boolean swapped = true;
	        while (swapped) {
	            swapped = false;
	            for (int i = 0; i < clients.length - 1; i++) {
	                if (clients[i] == null || clients[i + 1] == null) break;
	                if (clients[i].getAcct() > clients[i + 1].getAcct()) {
	                    client temp = clients[i];
	                    clients[i] = clients[i + 1];
	                    clients[i + 1] = temp;
	                    swapped = true;
	                }
	            }
	        }
	    }
public String showBalance(client c) {
	if(c == null) {
		return "please enter a client name";
	}

	for(int i = 0; i < clients.length; i++) {
		if(c.equals(clients[i])) {
			return clients[i].getName() + "'s balance is: " + clients[i].getBalance(); 
		}
	}
	return "could not find client";
}

public client[] getAllClients() {
	return clients;
}
}
