package com.cassandra.intellipaat.tutorial;

public class HashGenerator {
	private String name;
	
	
	
	public HashGenerator(String name) {
		super();
		this.name = name;
	}
	
	public int Hash_Prefix(int modulu) {
		if (null==name || 0==name.length()) return 0;
		int ret =  hashCode() % modulu;
		if (ret<0) ret=-1*ret;
		return ret;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashGenerator other = (HashGenerator) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
