package ie.gmit.sw;

public class MapKey {
	private int hash;

	public MapKey(int hash) {
		this.hash = hash;
	}

	@Override
	public int hashCode() {
		return this.hash;
	}
	
}
