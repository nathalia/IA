package br.usp.ia.model;

public class Attribute {
	private String name;
	private double value;
	
	private static final int Sunny = 0;
	private static final int Overcast = 1;
	private static final int Rain = 2;

	private static final int Hot = 0;
	private static final int Mild = 1;
	private static final int Cool = 2;
	
	private static final int High = 0;
	private static final int Normal = 1;
	
	private static final int Weak = 0;
	private static final int Strong = 1;
	
	private static final int PlayNo = 0;
	private static final int PlayYes = 1;
	
	public Attribute(String name, String strValue) {
		this.setName(name);
		if(strValue.equals("sunny"))
			this.setValue(Attribute.getSunny());
		else if(strValue.equals("overcast"))
			this.setValue(Attribute.getOvercast());
		else if(strValue.equals("rain"))
			this.setValue(Attribute.getRain());
		else if(strValue.equals("hot"))
			this.setValue(Attribute.getHot());
		else if(strValue.equals("mild"))
			this.setValue(Attribute.getMild());
		else if(strValue.equals("cool"))
			this.setValue(Attribute.getCool());
		else if(strValue.equals("high"))
			this.setValue(Attribute.getHigh());
		else if(strValue.equals("normal"))
			this.setValue(Attribute.getNormal());
		else if(strValue.equals("weak"))
			this.setValue(Attribute.getWeak());
		else if(strValue.equals("strong"))
			this.setValue(Attribute.getStrong());
		else if(strValue.equals("no"))
			this.setValue(Attribute.getPlayNo());
		else if(strValue.equals("yes"))
			this.setValue(Attribute.getPlayYes());
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static int getSunny() {
		return Sunny;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public static int getOvercast() {
		return Overcast;
	}

	public static int getRain() {
		return Rain;
	}

	public static int getHot() {
		return Hot;
	}

	public static int getMild() {
		return Mild;
	}

	public static int getCool() {
		return Cool;
	}

	public static int getHigh() {
		return High;
	}

	public static int getNormal() {
		return Normal;
	}

	public static int getWeak() {
		return Weak;
	}

	public static int getStrong() {
		return Strong;
	}

	public static int getPlayNo() {
		return PlayNo;
	}

	public static int getPlayYes() {
		return PlayYes;
	}

}
