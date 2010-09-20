import java.io.*;

class HashTest{

	static void printMenu() {
		String StrMenu = "+--- Hash tables ---\nr : Reset all\n";
		StrMenu = StrMenu + "H : Hash\nl : Lookup\ni : Insert\n";
		StrMenu = StrMenu + "d : Delete\nD : Dump hashtable\n";
		StrMenu = StrMenu + "q : Quit program\nh : show this text\n";
		System.out.print(StrMenu); System.out.flush();
	}

	static InfoT readType() throws IOException {
		char ch = getchar();
		switch (ch) {
		case 'i': return new InfoT('i');
		case 'c': return new InfoT('c');
		case 'd': return new InfoT('d');
		default: return new InfoT('e');
		}
	}

	static char getchar() throws IOException {
		BufferedReader myIn = new BufferedReader(
				new InputStreamReader(System.in));
		return (char) myIn.read();
	}

	public static String getLine() throws IOException {
		BufferedReader myIn = new BufferedReader(
				new InputStreamReader(System.in));
		return myIn.readLine();
	}

	static char c;
	static String Str;
	static int n;

	public static void main(String[] args) throws IOException {
		Hash ht = new Hash();
		ht.makeEmpty();
		printMenu();

		for (;;) {
			System.out.print("lab > "); System.out.flush();
			c = getchar();
			switch(c) {
			case 'r': ht.makeEmpty(); break;
			case 'H':
				System.out.print("Hash string: "); System.out.flush();
				Str = getLine();
				System.out.print("hash(" + Str + ") => ");
				System.out.print(ht.hashFun(Str).value + "\n");
				System.out.flush(); break;
			case 'l':
				System.out.print("Lookup string: ");
				System.out.flush();
				Str = getLine();
				System.out.print("lookup(" + Str + ") => ");
				System.out.print(ht.read(Str).type + "\n");
				System.out.flush(); break;
			case 'i':
				System.out.print("Insert string: "); System.out.flush();
				Str = getLine();
				System.out.print("with type: "); System.out.flush();
				ht.write(new MapletT(Str, readType()));
				System.out.flush(); break;
			case 'd':
				System.out.print("Delete string: ");
				System.out.flush();
				Str = getLine();
				ht.write(new MapletT(Str, new InfoT('e')));
				System.out.flush(); break;
			case 'D': ht.dump(); break;
			case 'q':
				System.out.println("Program terminated.");
				System.exit(0); break;
			case 'h': printMenu(); break;
			default:
				System.out.print("**** Sorry, No menu item named '");
			System.out.print(c + "'\n"); System.out.flush();
			}
		}
	}
}
