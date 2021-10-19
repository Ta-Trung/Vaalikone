package data;

public class Kysymys {
	private int number;
	private int id;
	private String kysymys;
	public Kysymys(String id, String kysymys) {
		// TODO Auto-generated constructor stub
		setId(id);
		this.kysymys=kysymys;
	}
	public Kysymys() {
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setId(String id) {
		try {
			this.id = Integer.parseInt(id);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public void setNumber(String number) {
		try {
			this.number = Integer.parseInt(number);
		}
		catch(NumberFormatException | NullPointerException e) {
			//Do nothing - the value of id won't be changed
		}
	}
	public String getKysymys() {
		return kysymys;
	}
	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}
}
