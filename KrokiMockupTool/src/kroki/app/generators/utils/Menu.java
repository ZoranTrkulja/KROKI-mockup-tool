package kroki.app.generators.utils;

import java.util.ArrayList;

/**
 * Class that represents application menu (JMenu for desktop application or one {@code<li>} HTML element that contains list items for web application)
 * @author mrd
 */
public class Menu {
	
	String name;
	String label;
	ArrayList<Submenu> submenus;
	ArrayList<Menu> menus;
	
	public Menu() {
	}

	public Menu(String name, String label, ArrayList<Submenu> submenus, ArrayList<Menu> menus) {
		this.name = name;
		this.label = label;
		this.submenus = submenus;
		this.menus = menus;
	}

	public boolean addSubmenu(Submenu sub) {
		return submenus.add(sub);
	}
	
	public boolean addMenu(Menu menu) {
		return menus.add(menu);
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

	public ArrayList<Submenu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(ArrayList<Submenu> submenus) {
		this.submenus = submenus;
	}

	public ArrayList<Menu> getMenus() {
		return menus;
	}

	public void setMenus(ArrayList<Menu> menus) {
		this.menus = menus;
	}
}
