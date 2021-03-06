import java.util.Iterator;
import java.util.Scanner;

public class Menu {

	private int testint = 1;

	private Company company = new Company();
	private Client selectedClient;
	private User selectedUser;
	private Employee selectEmployee;
	private Printer selectedPrinter;
	private Document selectedDocument;

	/**
	 * 4
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Menu console = new Menu();
		console = console.mainMenu(console);
		System.out.println("Obrigado pela preferencia, volte sempre!");
	}

	private Menu mainMenu(Menu console) {
		System.out.println("Bem vindos a�gestao do servico de impressoras.");

		int selection = 0;

		do {
			System.out.println("[1] Clientes");
			System.out.println("[2] Funcionarios");
			System.out.println("[3] Relatorios");
			System.out.println("[4] Gerar conteudo");
			System.out.println("[5] Report");
			System.out.println("[6] Sair");

			System.out.print("Insira a sua escolha: ");
			// selection = testint++;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();
			switch (selection) {
			case 1:
				return console.submenuClient(console);
			case 2:
				return console.submenuEmployee(console);
			case 3:
				return console.submenuEmployee(console);
			case 4:
				this.generateContent();
				return console.mainMenu(console);
			case 5:
				return console.companyReportMenu(console);
			case 6:
				return console;
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 4);
		return console;
	}

	private Menu companyReportMenu(Menu console) {
		System.out.println("");
		System.out.println("====== INFORMACOES CLIENTES ======");
		System.out.println("Numero clientes : " + this.company.getClients().size());

		if (this.company.getClients().size() != 0) {
			for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
				System.out.println("");
				Client client = (Client) it.next();
				System.out.println("Name: " + client.name);
				System.out.println("Location: " + client.location);
				System.out.println("Utilizadores: " + client.getUsers().size());
				System.out.println("Impressoras: " + client.getPrinters().size());
				if (client.getNoFunctionalPrinters().size() != 0) {
					System.out.println("Impressoras avariadas: " + client.getPrinters().size());
				}
			}
		}
		System.out.println("");
		System.out.println("====== INFORMACOES EMPREGADOS ======");
		System.out.println("Numero empregados : " + this.company.getEmployees().size());
		if (this.company.getEmployees().size() != 0) {
			for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
				System.out.println("");
				Employee employee = (Employee) it.next();
				System.out.println("Name: " + employee.name);
				System.out.println("Problemas por resolver: " + employee.getProblemsToSolve().size());
				System.out.println("Problemas resolvidos: " + employee.getProblemsSolved().size());
			}
		}

		System.out.println("");
		System.out.println("");
		return console.mainMenu(console);

	}

	private Menu submenuClient(Menu console) {
		System.out.println("CLIENTES");

		int selection = 0;

		do {
			System.out.println("[1] Criar Cliente");
			System.out.println("[2] Apagar Cliente");
			System.out.println("[3] Selecionar Cliente");
			System.out.println("[4] Atras");

			System.out.print("Insira a sua escolha: ");
			// selection = ++testint;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

			switch (selection) {
			case 1:
				return console.submenuCreateClient(console);
			case 2:
				return console.submenuDeleteClient(console);
			case 3:
				return console.submenuSelectClient(console);
			case 4:
				return console.mainMenu(console);
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 4);
		return console;
	}

	private Menu submenuCreateClient(Menu console) {
		System.out.println("Criar cliente");

		System.out.println("Introduza o nome: \n");
		Scanner scanner_name = new Scanner(System.in);
		String name = scanner_name.nextLine();
		System.out.println("\n");
		System.out.println("Introduza a localizacao: \n");
		Scanner scanner_location = new Scanner(System.in);
		String location = scanner_location.nextLine();
		System.out.println("\n");
		this.company.addClient(new Client(name, location, this.company));
		for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
			Client client = (Client) it.next();
			System.out.println(client.toString());
		}

		return console.submenuClient(console);
	}

	private Menu submenuDeleteClient(Menu console) {
		System.out.println("Apagar cliente");

		int selection = 0;
		if (this.company.getClients().size() == 0) {
			System.out.println("Nao ha mais clientes para apagar");
			return console.submenuClient(console);
		}
		int i = 1;
		for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
			Client client = (Client) it.next();
			System.out.println("[" + i + "] " + client.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
			Client client = (Client) it.next();
			if (selection == i) {
				this.company.removeClient(client);
				break;
			}
			i++;
		}

		return console.submenuClient(console);
	}

	private Menu submenuSelectClient(Menu console) {
		System.out.println("Selecione um cliente");

		int selection = 0;
		if (this.company.getClients().size() == 0) {
			System.out.println("Nao ha clientes para selecionar, crie um primeiro");
			return console.submenuClient(console);
		}
		int i = 1;
		for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
			Client client = (Client) it.next();
			System.out.println("[" + i + "] " + client.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.company.getClients().iterator(); it.hasNext();) {
			Client client = (Client) it.next();
			if (selection == i) {
				this.selectedClient = client;
				break;
			}
			i++;
		}

		return console.submenuClientSelected(console);
	}

	private Menu submenuClientSelected(Menu console) {
		System.out.println("CLIENTE");

		int selection = 0;

		do {
			System.out.println("[1] Criar Utilizador");
			System.out.println("[2] Apagar Utilizador");
			System.out.println("[3] Selecionar Utilizador");
			System.out.println("[4] Criar Impressora");
			System.out.println("[5] Apagar Impressora");
			System.out.println("[6] Listar Impressoras");
			System.out.println("[7] Listar Impressoras para Manutencao");
			System.out.println("[8] Relat�rio");
			System.out.println("[9] Atras");
			System.out.print("Insira a sua escolha: ");
			// selection = ++testint;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

			switch (selection) {
			case 1:
				return console.submenuCreateUser(console);
			case 2:
				return console.submenuDeleteUser(console);
			case 3:
				return console.submenuSelectUser(console);
			case 4:
				return console.submenuCreatePrinter(console);
			case 5:
				return console.submenuDeletePrinter(console);
			case 6:
				return console.submenuListPrinter(console);
			case 7:
				return console.submenuListNoFunctionalPrinter(console);
			case 8:
				return console.clientReportMenu(console);
			case 9:
				return console.submenuClient(console);
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 8);
		return console;
	}

	private Menu clientReportMenu(Menu console) {
		System.out.println("");
		System.out.println("====== INFORMACOES CLIENTE ======");

		System.out.println("Name: " + this.selectedClient.name);
		System.out.println("Location: " + this.selectedClient.location);
		System.out.println("Utilizadores: " + this.selectedClient.getUsers().size());

		System.out.println("====== INFORMACOES UTILIZADORES ======");
		System.out.println("Numero utilizadores : " + this.selectedClient.getUsers().size());

		if (this.selectedClient.getUsers().size() != 0) {
			for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
				System.out.println("");
				User user = (User) it.next();
				System.out.println("Username: " + user.username);
				System.out.println("Balance: " + user.getBalance());
				if (this.selectedClient.userHasPrinter(user) != null) {
					System.out.println("Logado: sim");
					System.out.println("Impressora:" + user.loginToPrinter().location);
				} else {
					System.out.println("Logado: nao");
				}

				System.out.println("Numero documentos : " + user.getDocuments().size());
				if (user.getDocuments().size() != 0) {
					for (Iterator it2 = user.getDocuments().iterator(); it2.hasNext();) {
						System.out.println("");
						Document document = (Document) it2.next();
						System.out.println("Sheets: " + document.num_sheets);
						System.out.println("Titulo: " + document.title);
						System.out.println("Tipo : " + document.type);
						System.out.println("Pre�o por p�gina : " + document.title);
					}
				}

			}
		}

		System.out.println("");
		System.out.println("====== INFORMACOES IMPRESSORASS ======");
		System.out.println("Impressoras: " + this.selectedClient.getPrinters().size());
		if (this.selectedClient.getNoFunctionalPrinters().size() != 0) {
			System.out.println("Impressoras avariadas: " + this.selectedClient.getNoFunctionalPrinters().size());
		}

		if (this.selectedClient.getPrinters().size() != 0) {

			for (Iterator it = this.selectedClient.getPrinters().iterator(); it.hasNext();) {
				System.out.println("");
				Printer printer = (Printer) it.next();
				System.out.println("Name: " + printer.location);
				System.out.println("Funcional: " + (printer.functional ? "Sim" : "Nao"));
				System.out.println("Folhas: " + printer.sheets_remaining);
				System.out.println("Preto: " + printer.black_remaining);
				System.out.println("Magenta: " + printer.magenta_remaining);
				System.out.println("Amarelo: " + printer.yellow_remaining);
				System.out.println("Cyan: " + printer.cyan_remaining);
			}
		}

		System.out.println("");
		System.out.println("");
		return console.submenuClient(console);

	}

	private Menu submenuCreateUser(Menu console) {
		System.out.println("Criar utilizador");

		System.out.println("Introduza o username: \n");
		Scanner scanner_username = new Scanner(System.in);
		String username = scanner_username.nextLine();
		System.out.println("\n");
		System.out.println("Introduza a password: \n");
		Scanner scanner_password = new Scanner(System.in);
		String password = scanner_password.nextLine();
		System.out.println("\n");
		this.selectedClient.addUser(new User(username, password, this.selectedClient));
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			User user = (User) it.next();
			System.out.println(user.toString());
		}

		return console.submenuClientSelected(console);
	}

	private Menu submenuDeleteUser(Menu console) {
		System.out.println("Apagar utilizador");

		int selection = 0;
		if (this.selectedClient.getUsers().size() == 0) {
			System.out.println("Nao ha mais utilizadores para apagar");
			return console.submenuClientSelected(console);
		}
		int i = 1;
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			User user = (User) it.next();
			System.out.println("[" + i + "] " + user.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			User user = (User) it.next();
			if (selection == i) {
				this.selectedClient.removeUser(user);
				break;
			}
			i++;
		}

		return console.submenuClientSelected(console);
	}

	private Menu submenuSelectUser(Menu console) {
		System.out.println("Selecione um utilizador");

		int selection = 0;
		if (this.selectedClient.getUsers().size() == 0) {
			System.out.println("Nao ha utilizadores para selecionar, crie um primeiro");
			return console.submenuClientSelected(console);
		}
		int i = 1;
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			User user = (User) it.next();
			System.out.println("[" + i + "] " + user.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			User user = (User) it.next();
			if (selection == i) {
				this.selectedUser = user;
				break;
			}
			i++;
		}

		return console.submenuUserSelected(console);
	}

	private Menu submenuCreatePrinter(Menu console) {
		System.out.println("Criar impressora");

		System.out.println("Introduza a localizacao da impressora: \n");
		Scanner scanner_location = new Scanner(System.in);
		String location = scanner_location.nextLine();
		System.out.println("\n");
		this.selectedClient.addPrinter(new Printer(location, this.selectedClient));
		for (Iterator it = this.selectedClient.getUsers().iterator(); it.hasNext();) {
			Printer printer = (Printer) it.next();
			System.out.println(printer.toString());
		}

		return console.submenuClientSelected(console);
	}

	private Menu submenuDeletePrinter(Menu console) {
		System.out.println("Apagar impressora");

		int selection = 0;
		if (this.selectedClient.getPrinters().size() == 0) {
			System.out.println("Nao ha mais impressoras para apagar");
			return console.submenuClientSelected(console);
		}
		int i = 1;
		for (Iterator it = this.selectedClient.getPrinters().iterator(); it.hasNext();) {
			Printer printer = (Printer) it.next();
			System.out.println("[" + i + "] " + printer.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.selectedClient.getPrinters().iterator(); it.hasNext();) {
			Printer printer = (Printer) it.next();
			if (selection == i) {
				this.selectedClient.removePrinter(printer);
				break;
			}
			i++;
		}

		return console.submenuClientSelected(console);
	}

	private Menu submenuListPrinter(Menu console) {
		System.out.println("Pressione 0 para voltar atras");

		int selection = 0;

		do {
			int i = 1;
			for (Iterator it = this.selectedClient.getPrinters().iterator(); it.hasNext();) {
				Printer printer = (Printer) it.next();
				System.out.println("[" + i + "] " + printer.toString());
				i++;
			}
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

		} while (selection != 0);
		return console.submenuClientSelected(console);
	}

	private Menu submenuListNoFunctionalPrinter(Menu console) {
		System.out.println("Pressione 0 para voltar atras");

		int selection = 0;

		do {
			int i = 1;
			for (Iterator it = this.selectedClient.getNoFunctionalPrinters().iterator(); it.hasNext();) {
				Printer printer = (Printer) it.next();
				System.out.println("[" + i + "] " + printer.toString());
				i++;
			}
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

		} while (selection != 0);
		return console.submenuClientSelected(console);
	}

	private Menu submenuUserSelected(Menu console) {
		System.out.println("UTILIZADOR");

		int selection = 0;

		do {
			System.out.println("[1] Criar Documento");
			System.out.println("[2] Apagar Documento");
			System.out.println("[3] Login Impressora Livre");
			System.out.println("[4] Carregar Saldo");
			System.out.println("[5] Atras");

			System.out.print("Insira a sua escolha: ");
			// selection = ++testint;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

			switch (selection) {
			case 1:
				return console.submenuCreateDocument(console);
			case 2:
				return console.submenuDeleteDocument(console);
			case 3:
				return console.submenuLoginPrinter(console);
			case 4:
				return console.submenuAddBalance(console);
			case 5:
				return console.submenuClientSelected(console);
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 5);
		return console;
	}

	private Menu submenuCreateDocument(Menu console) {
		System.out.println("Criar documento");

		System.out.println("Introduza o numero de folhas do documento: \n");
		Scanner scanner_sheets = new Scanner(System.in);
		String sheets = scanner_sheets.nextLine();
		System.out.println("\n");
		System.out.println("Introduza o tipo do documento (1-preto e branco, 2-cor): \n");
		Scanner scanner_type = new Scanner(System.in);
		String type = scanner_type.nextLine();
		System.out.println("\n");
		System.out.println("Introduza o titulo do documento: \n");
		Scanner scanner_title = new Scanner(System.in);
		String title = scanner_title.nextLine();
		System.out.println("\n");
		if (type.equals("1"))
			type = "PB";
		else
			type = "Cor";
		this.selectedUser.addDocument(new Document(Integer.parseInt(sheets), type, title, this.selectedUser));
		for (Iterator it = this.selectedUser.getDocuments().iterator(); it.hasNext();) {
			Document document = (Document) it.next();
			System.out.println(document.toString());
		}

		return console.submenuUserSelected(console);
	}

	private Menu submenuDeleteDocument(Menu console) {
		System.out.println("Apagar documentos");

		int selection = 0;
		if (this.selectedUser.getDocuments().size() == 0) {
			System.out.println("Nao ha mais documentos para apagar");
			return console.submenuUserSelected(console);
		}
		int i = 1;
		for (Iterator it = this.selectedUser.getDocuments().iterator(); it.hasNext();) {
			Document document = (Document) it.next();
			System.out.println("[" + i + "] " + document.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.selectedUser.getDocuments().iterator(); it.hasNext();) {
			Document document = (Document) it.next();
			if (selection == i) {
				this.selectedUser.removeDocument(document);
				break;
			}
			i++;
		}

		return console.submenuUserSelected(console);
	}

	private Menu submenuLoginPrinter(Menu console) {
		if (this.selectedUser.getDocuments().size() == 0) {
			System.out.println("Nao ha documentos para imprimir, crie um primeiro");
			return console.submenuUserSelected(console);
		}

		this.selectedPrinter = this.selectedUser.loginToPrinter();
		if (this.selectedPrinter == null) {
			System.out.println("Nao ha impressoras para imprimir");
			return console.submenuUserSelected(console);
		}

		System.out.println("Selecione o documento a imprimir ou 0(zero) para fazer logout: ");

		this.selectedPrinter.login(this.selectedUser);

		int selection = 0;

		int i = 1;
		for (Iterator it = this.selectedUser.getDocuments().iterator(); it.hasNext();) {
			Document document = (Document) it.next();
			System.out.println("[" + i + "] " + document.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		if (selection == 0) {
			this.selectedPrinter.logout();
			return console.submenuUserSelected(console);
		}
		i = 1;
		for (Iterator it = this.selectedUser.getDocuments().iterator(); it.hasNext();) {
			Document document = (Document) it.next();
			if (selection == i) {
				this.selectedDocument = document;
				break;
			}
			i++;
		}

		String type = this.selectedDocument.getType();

		if (this.selectedUser.getBalance().intValue() < this.selectedDocument.getTotalPrice().intValue()) {
			System.out.println("Nao tem saldo suficiente para imprimir, carregue primeiro\n");
			return console.submenuUserSelected(console);
		}

		if (type == "PB") {
			if (this.selectedPrinter.possiblePrintBlackDocument(this.selectedDocument)) {
				this.selectedPrinter.printDocument(this.selectedDocument);
				System.out.println("Ficou com " + this.selectedUser.getBalance().intValue() + " de saldo.");
			}
		} else if (type == "Cor") {
			if (this.selectedPrinter.possiblePrintColorDocument(this.selectedDocument)) {
				this.selectedPrinter.printDocument(this.selectedDocument);
				System.out.println("Ficou com " + this.selectedUser.getBalance().intValue() + " de saldo.");
			}
		}

		return console.submenuUserSelected(console);
	}

	private Menu submenuAddBalance(Menu console) {
		System.out.println("Carregar saldo de impressao");

		System.out.println("Introduza o saldo que deseja carregar: \n");
		Scanner scanner_balance = new Scanner(System.in);
		String balance = scanner_balance.nextLine();
		System.out.println("\n");

		this.selectedUser.addToBalance(Integer.parseInt(balance));
		System.out.println("Ficou com " + this.selectedUser.getBalance().intValue() + " de saldo.");

		return console.submenuUserSelected(console);
	}

	private Menu submenuEmployee(Menu console) {
		System.out.println("FUNCIONARIOS");

		int selection = 0;

		do {
			System.out.println("[1] Criar Funcionario");
			System.out.println("[2] Apagar Funcionario");
			System.out.println("[3] Selecionar Funcionario");
			System.out.println("[4] Atras");

			System.out.print("Insira a sua escolha: ");
			// selection = ++testint;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

			switch (selection) {
			case 1:
				return console.submenuCreateEmployee(console);
			case 2:
				return console.submenuDeleteEmployee(console);
			case 3:
				return console.submenuSelectEmployee(console);
			case 4:
				return console.mainMenu(console);
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 4);
		return console;
	}

	private Menu submenuCreateEmployee(Menu console) {
		System.out.println("Criar funcionario");

		System.out.println("Introduza o nome: \n");
		Scanner scanner_name = new Scanner(System.in);
		String name = scanner_name.nextLine();
		System.out.println("\n");
		this.company.addEmployee(new Employee(name));
		for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
			Employee employee = (Employee) it.next();
			System.out.println(employee.toString());
		}

		return console.submenuEmployee(console);
	}

	private Menu submenuDeleteEmployee(Menu console) {
		System.out.println("Apagar funcionario");

		int selection = 0;
		if (this.company.getEmployees().size() == 0) {
			System.out.println("Nao ha mais funcionarios para apagar");
			return console.submenuEmployee(console);
		}
		int i = 1;
		for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
			Employee employee = (Employee) it.next();
			System.out.println("[" + i + "] " + employee.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
			Employee employee = (Employee) it.next();
			if (selection == i) {
				this.company.removeEmployee(employee);
				break;
			}
			i++;
		}

		return console.submenuEmployee(console);
	}

	private Menu submenuSelectEmployee(Menu console) {
		System.out.println("Selecione um funcionario");

		int selection = 0;
		if (this.company.getEmployees().size() == 0) {
			System.out.println("N�o ha funcionarios para selecionar, crie um primeiro");
			return console.mainMenu(console);
		}
		int i = 1;
		for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
			Employee employee = (Employee) it.next();
			System.out.println("[" + i + "] " + employee.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.company.getEmployees().iterator(); it.hasNext();) {
			Employee employee = (Employee) it.next();
			if (selection == i) {
				this.selectEmployee = employee;
				break;
			}
			i++;
		}

		return console.submenuEmployeeSelected(console);
	}

	private Menu submenuEmployeeSelected(Menu console) {
		System.out.println("FUNCIONARIO");

		int selection = 0;

		do {
			System.out.println("[1] Resolver problema");
			System.out.println("[2] Problemas resolvidos");
			System.out.println("[3] Atras");

			System.out.print("Insira a sua escolha: ");
			// selection = ++testint;
			Scanner input = new Scanner(System.in);
			selection = input.nextInt();

			switch (selection) {
			case 1:
				return console.submenuSelectProblem(console);
			case 2:
				return console.submenusolvedProblems(console);
			case 3:
				return console.mainMenu(console);
			default:
				System.out.println("A escolha e invalida!");
			}
		} while (selection != 3);
		return console;
	}

	private Menu submenuSelectProblem(Menu console) {
		System.out.println("Selecione um problema para resolver");

		int selection = 0;
		if (this.selectEmployee.getProblemsToSolve().size() == 0) {
			System.out.println("N�o ha problemas por resolver!");
			return console.submenuEmployeeSelected(console);
		}
		int i = 1;
		for (Iterator it = this.selectEmployee.getProblemsToSolve().iterator(); it.hasNext();) {
			Problem problem = (Problem) it.next();
			System.out.println("[" + i + "] " + problem.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;
		for (Iterator it = this.selectEmployee.getProblemsToSolve().iterator(); it.hasNext();) {
			Problem problem = (Problem) it.next();
			if (selection == i) {
				this.selectEmployee.solveProblem(problem);
				break;
			}
			i++;
		}

		return console.submenuEmployeeSelected(console);
	}

	private Menu submenusolvedProblems(Menu console) {

		int selection = 0;
		if (this.selectEmployee.getProblemsSolved().size() == 0) {
			System.out.println("Este funcion�rio nao resolveu nenhum problema!");
			return console.submenuEmployeeSelected(console);
		} else {
			System.out.println("Problemas Resolvidos por este funcion�rio!");

		}

		int i = 1;
		for (Iterator it = this.selectEmployee.getProblemsSolved().iterator(); it.hasNext();) {
			Problem problem = (Problem) it.next();
			System.out.println("[" + i + "] " + problem.toString());
			i++;
		}
		Scanner input = new Scanner(System.in);
		selection = input.nextInt();
		i = 1;

		return console.submenuEmployeeSelected(console);
	}

	private void generateContent() {
		Client client1 = new Client("feup", "porto", this.company);
		Client client2 = new Client("fep", "porto", this.company);
		User user1 = new User("Utilizador1", "password1", client1);
		User user2 = new User("Utilizador2", "password2", client1);
		User user3 = new User("Utilizador3", "password3", client1);
		User user4 = new User("Utilizador4", "password4", client2);
		User user5 = new User("Utilizador5", "password5", client2);
		User user6 = new User("Utilizador6", "password6", client2);
		user1.addToBalance(300);
		user2.addToBalance(100);
		user3.addToBalance(50);
		user4.addToBalance(500);
		Printer printer1 = new Printer("escrit�rio1", client1);
		Printer printer2 = new Printer("escrit�rio2", client1);
		Printer printer3 = new Printer("escrit�rio3", client1);
		Printer printer4 = new Printer("escrit�rio4", client1);
		Printer printer5 = new Printer("escrit�rio5", client2);
		Printer printer6 = new Printer("escrit�rio6", client2);
		Problem problem = new Problem("descricao", printer3, this.company.getEmployeeLessBusy());
		problem.solved = true;
		Problem problem1 = new Problem("descricao1", printer6, this.company.getEmployeeLessBusy());
		Problem problem2 = new Problem("descricao2", printer3, this.company.getEmployeeLessBusy());
		Problem problem3 = new Problem("descricao3", printer2, this.company.getEmployeeLessBusy());
		Problem problem4 = new Problem("descricao4", printer5, this.company.getEmployeeLessBusy());
		Document document1 = new Document(5, "PB", "titulo1", user1);
		Document document2 = new Document(10, "Cor", "titulo2", user3);
		Document document3 = new Document(91, "Cor", "titulo3", user4);
		Document document4 = new Document(6, "PB", "titulo4", user1);

	}

	private void relatorios() {

	}

}