class Hash {

	private static int hashTableSize;

	private static MapletT[] hashTable;

	Hash() {
		hashTableSize = 1000000;
		hashTable = new MapletT[hashTableSize];
	}

	Hash(int size) {
		hashTableSize = size;
		hashTable = new MapletT[hashTableSize];
	}

	static boolean equal(String n1, String n2) {
		return n1.equals(n2);
	}

	boolean isEmpty(MapletT maplet) {
		return maplet.info.type == 'e';
	}

	MapletT emptyMaplet() {
		return new MapletT();
	}

	HashIdxT hashFun(String n) 
	{
		int hophash = 0;
		for (int i=0; i < n.length(); i++)
		{
			hophash += (int)n.charAt(i);
		}
		hophash %= hashTableSize;
		return new HashIdxT( hophash);

	} // egen kod...

	InfoT read(String n) {
		int last = hashFun(n).value -1;
		InfoT out = new InfoT('e');
		
		for (int hash = last +1; //behöver kanske en kommentar?
		     (!isEmpty(hashTable[hash]))
			     && ( hash != last);
		     hash++)
		{
			if (n.equals(hashTable[hash].name))
			{
				out = hashTable[hash].info;
				break;
			}
		}
		return out;

	}

	void write(MapletT maplet) {
		if (!isEmpty(maplet)) 
		{ //lägg till
			int last = hashFun(maplet.name).value -1;
				for (int hash = last +1;
			     hash != last;
				     hash = (hash +1) %hashTableSize)
			{
				if (isEmpty(hashTable[hash]))
				{
					hashTable[hash] = maplet;
					break;
				}
			}
			



		}else  //ta bort
		{
			int first = hashFun(maplet.name).value;
			boolean foundDeletee = false;
				
				for (int i = 0; i < hashTableSize; i++)
				{
					if ((!foundDeletee) &&
					    (equal(hashTable[(i+first) % hashTableSize].name, maplet.name)))
					{
						hashTable[(i+first) % hashTableSize] = emptyMaplet();
						foundDeletee = true;
						i++;
					}
					
					if (foundDeletee)
						if (isEmpty(hashTable[(i+first) % hashTableSize]))
						{
							return;
						}
						else
						{
							MapletT tmp = hashTable[(i+first) % hashTableSize];
							hashTable[(i+first) % hashTableSize] = emptyMaplet();
							write(tmp);
							dump();
						}
				}
		}
		
		return;	
	} // dummy code
	
	void makeEmpty() {
		for (int i = 0; i < hashTableSize; i++)
			hashTable[i] = new MapletT();
	}

	void dump() {
		for (int i = 0; i<hashTableSize; i++) {
			System.out.print(i + ". " + hashTable[i].info.type);
			if (!isEmpty(hashTable[i])) {
				System.out.print(" " + hashTable[i].name + " (");
				System.out.print(hashFun(hashTable[i].name).value + ")");
			}
			else
				System.out.print(" -");
			System.out.print("\n"); System.out.flush();
		}
	}
}
