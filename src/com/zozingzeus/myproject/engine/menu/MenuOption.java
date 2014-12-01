package com.zozingzeus.myproject.engine.menu;

import com.zozingzeus.myproject.engine.interfaces.Action;

public class MenuOption {

	private String title;
	private Action action;

	public MenuOption(String title, Action action) {
		this.title = title;
		this.action = action;
	}

	public void action() {
		if (action != null) action.action();
	}

	public String getTitle() {
		return title;
	}

}
