package kroki.app.generators.utils;

import java.util.ArrayList;

/**
 * Klasa koja sluzi za mapiranje perzistentih klasa iz UI profila na klase u freemarker sablonu.
 * Ova klasa sluzi samo kao pomocna klasa za lakse generisanje perzistentnog sloja i nije deo UI profila.
 * @author mrd
 */
public class EJBClass {
	
	private String classPackage;
	private String name;
	private String label;
	private ArrayList<Attribute> attributes;
	private ArrayList<ManyToOneAttribute> manyToOneAttributes;
	private ArrayList<OneToManyAttribute> oneToManyAttributes;
	
	public EJBClass(String classPackage, String name, String label,
			ArrayList<Attribute> attributes,
			ArrayList<ManyToOneAttribute> manyToOneAttributes,
			ArrayList<OneToManyAttribute> oneToManyAttributes) {
		super();
		this.classPackage = classPackage;
		this.name = name;
		this.label = label;
		this.attributes = attributes;
		this.manyToOneAttributes = manyToOneAttributes;
		this.oneToManyAttributes = oneToManyAttributes;
	}

	public String getClassPackage() {
		return classPackage;
	}

	public void setClassPackage(String classPackage) {
		this.classPackage = classPackage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public ArrayList<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(ArrayList<Attribute> attributes) {
		this.attributes = attributes;
	}

	public ArrayList<ManyToOneAttribute> getManyToOneAttributes() {
		return manyToOneAttributes;
	}

	public void setManyToOneAttributes(
			ArrayList<ManyToOneAttribute> manyToOneAttributes) {
		this.manyToOneAttributes = manyToOneAttributes;
	}

	public ArrayList<OneToManyAttribute> getOneToManyAttributes() {
		return oneToManyAttributes;
	}

	public void setOneToManyAttributes(
			ArrayList<OneToManyAttribute> oneToManyAttributes) {
		this.oneToManyAttributes = oneToManyAttributes;
	}
}